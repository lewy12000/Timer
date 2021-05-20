import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Car {
    String name;
    int km;
    float time;
    int fuel;

    public Car() {
        Random random = new Random();
        this.name = "Nissan";
        this.km = random.nextInt(50)+300;
        this.time = random.nextInt(5);
        this.fuel = random.nextInt(11)+30;
    }

    @Scheduled
    public void showCar(){
        System.out.println(name + " ridden " + km + "km in " + time + "h");
            km+=2;
            time+=0.25;
        if(fuel == 2 || fuel < 2){
            System.out.println("Car has 0 liters of fuel and must stop");
            System.exit(0);
        }
    }

    @Scheduled(period = 2500)
    public void showFuel(){
        System.out.println(name + " has " + fuel + " liters of fuel");
        fuel-=2;
        if(fuel == 2 || fuel < 2){
            System.out.println("Car has 0 liters of fuel and must stop");
            System.exit(0);
        }
    }

}
