package designPatterns.simplefactory;

public class TMain {
    public static void main(String[] args){
        SimpleCarFactory.createCar(SimpleCarFactory.CarType.CAR_TYPE_JEEP).run();
        SimpleCarFactory.createCar(SimpleCarFactory.CarType.CAR_TYPE_BENZ).run();
    }
}
