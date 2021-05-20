import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Teoria {
    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("Nie podano klasy");
            System.exit(0);
        }
        Arrays.stream(args).forEach(Teoria :: findMethods);
    }

    public static void findMethods(String className){
        try {
            Class<?> myClass = Class.forName(className);
            Constructor<?> classConstructor = myClass.getConstructor();
            Object object = classConstructor.newInstance();

            List<Method> methods = Arrays.stream(object.getClass().getMethods())
                    .filter(m->m.getParameterCount()==0 && m.isAnnotationPresent(Scheduled.class))
                    .collect(Collectors.toList());
            methods.forEach(m ->{
                runMethod(m, object);
            });
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void runMethod(Method method, Object object){
        if(!method.isAccessible())
            method.setAccessible(true);

        Timer timer1 = new Timer();

        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    method.invoke(object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        },0, method.getDeclaredAnnotation(Scheduled.class).period());
    }

}
