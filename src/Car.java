public class Car extends Vehicle {

    public Car(String plate, String type) {
        super(plate, type);
    }

    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }
}

