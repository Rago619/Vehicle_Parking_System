public class Bike extends Vehicle {

    public Bike(String plate, String type) {
        super(plate, type);
    }

    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }
}
