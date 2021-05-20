import java.util.Random;

public class Person {
    String name;
    int age;
    int height;

    public Person() {
        this.name = "Human";
        Random random = new Random();
        this.age = random.nextInt(11)+40;
        this.height = random.nextInt(50)+150;
    }

    @Scheduled
    public void printAge(){
        System.out.println(name + " is now " + age + "yo");
        if(age < 51)
            age+=1;
    }

    @Scheduled(period = 5000)
    public void printHeight(){
        System.out.println(name + " is " + height + " cm tall");
    }

}
