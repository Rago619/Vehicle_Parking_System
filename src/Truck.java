public class Truck extends Vehicle {

    public Truck(String plate, String type) {
        super(plate, type);
    }

    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }
}