package designPatterns.simplefactory;

public class Jeep implements ICar {

    @Override
    public void run() {
        System.out.println("这是一辆Jeep在行驶");
    }
}
