public class Ticket{
    private long enterTime;
    private long exitTime;
    private int rate;
    private static final int CAR_RATE = 15;
    private static final int TRUCK_RATE = 18;
    private static final int BIKE_RATE = 18;


    public Ticket(Vehicle vehicle) {
        if (vehicle.getType().equals("Car")) {
            rate = CAR_RATE;
        }
        else if (vehicle.getType().equals("Truck")) {
            rate = TRUCK_RATE;
        }
        else if (vehicle.getType().equals("Bike")) {
            rate = BIKE_RATE;
        }
    }

    public long getEnterTime() {
        return enterTime;
    }

    public long getExitTime() {
        return exitTime;
    }

    public void setEnterTime(long enterTime) {
        this.enterTime = enterTime;
    }

    public void setExitTime(long exitTime) {
        this.exitTime = exitTime;
    }

    public int getRate() {
        return rate;
    }
}