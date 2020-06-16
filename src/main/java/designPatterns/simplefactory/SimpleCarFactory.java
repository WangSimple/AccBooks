package designPatterns.simplefactory;

public class SimpleCarFactory {
    public enum CarType{
        CAR_TYPE_BENZ,
        CAR_TYPE_JEEP;
    }
    public static ICar createCar(CarType type){
        switch (type){
            case CAR_TYPE_BENZ:
                return new Benz();
            case CAR_TYPE_JEEP:
                return new Jeep();
            default:
                return null;
        }
    }
}
