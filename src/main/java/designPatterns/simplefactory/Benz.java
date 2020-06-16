package designPatterns.simplefactory;

public class Benz implements ICar {
    @Override
    public void run() {
        System.out.println("这是一辆奔驰在行驶");
    }
}
