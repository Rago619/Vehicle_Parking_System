import java.util.*;

public class ParkingLot {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";



    private static final int CAR_LOTS_CAPACITY = 100;
    private static final int TRUCK_LOTS_CAPACITY = 100;
    private static final int BIKE_LOTS_CAPACITY = 100;

    private List<Car> carLots = new ArrayList<>(CAR_LOTS_CAPACITY);
    private List<Truck> truckLots = new ArrayList<>(TRUCK_LOTS_CAPACITY);
    private List<Bike> bikeLots = new ArrayList<>(BIKE_LOTS_CAPACITY);
    private int currentTruckAvailability;
    private int currentCarAvailability;
    private int currentBikeAvailability;

    public ParkingLot(){
        this.currentTruckAvailability = TRUCK_LOTS_CAPACITY;
        this.currentCarAvailability = CAR_LOTS_CAPACITY;
        this.currentBikeAvailability = BIKE_LOTS_CAPACITY;
    }

    public Vehicle getVehicle(String plate) {
        for (Car car : carLots) {
            if (car.getPlate().equals(plate)) {
                return car;
            }
        }
        for (Truck truck  : truckLots) {
            if (truck.getPlate().equals(plate)) {
                return truck;
            }
        }
        for (Bike bike : bikeLots) {
            if (bike.getPlate().equals(plate)) {
                return bike;
            }
        }
        return null;
    }

    public boolean isAvailable(Vehicle vehicle) {
        int ans = -1;
        if (vehicle.getType().equals("Car")) {
            if (currentCarAvailability > 0) {
                currentCarAvailability --;
                System.out.println(ANSI_CYAN+"Car Lots available amount: " + currentCarAvailability);

                carLots.add((Car)vehicle);
                ans = carLots.size();
                return true;
            }
        }
        else if (vehicle.getType().equals("Truck")) {
            if (currentTruckAvailability > 0) {
                currentTruckAvailability --;
                System.out.println("Car Lots available amount: " + currentTruckAvailability);

                truckLots.add((Truck)vehicle);
                ans = truckLots.size();

                return true;
            }
        }
        else if (vehicle.getType().equals("Bike")) {
            if (currentBikeAvailability > 0) {
                currentBikeAvailability --;
                System.out.println("Bike Lots available amount: "+ANSI_PURPLE+ currentBikeAvailability);

                bikeLots.add((Bike)vehicle);
                ans = bikeLots.size();
                return true;
            }
        }
        return false;
    }

    public boolean load(Vehicle vehicle) {
        if (!isAvailable(vehicle)) {
            return false;
        }
        vehicle.getTicket().setEnterTime(System.currentTimeMillis());
        System.out.println(ANSI_BLUE+"VEHICLE TYPE: " + vehicle.getType() + "\nVEHICLE NUMBER: " + vehicle.getPlate()
                + "\nENTRY TIME: "+ANSI_RESET+ new Date(vehicle.getTicket().getEnterTime()));
        return true;
    }

    public boolean isPaid(Vehicle vehicle) {
        int rate = vehicle.getTicket().getRate();
        System.out.println(ANSI_YELLOW+"RATE IS : " + rate + " dollars / hour. Less than 1 hour counts as 1 hour.");
        vehicle.getTicket().setExitTime(System.currentTimeMillis());
        System.out.println("You are leaving at "+ANSI_RESET+ new Date(vehicle.getTicket().getExitTime()));
        long parkingTime = vehicle.getTicket().getExitTime() - vehicle.getTicket().getEnterTime();
//        int secs = new Date(parkingTime).getSeconds();
//        int mins = new Date(parkingTime).getMinutes();
//        int hours = new Date(parkingTime).getHours();
        int secs = (int) (parkingTime / 1000) % 60 ;
        int mins = (int) ((parkingTime / (1000 * 60)) % 60);
        int hours   = (int) ((parkingTime / (1000 * 60 * 60)) % 24);
        System.out.println(ANSI_CYAN+"PARKING TIME: "+ hours + "h " + mins + "mins "
                + secs + "secs.");
        if (secs != 0 || mins != 0) {
            hours ++;
        }
        int amount = hours * rate;
        System.out.println("YOU NEED TO PAY : "+ amount + " dollars."+ANSI_RESET);
        return true;
    }

    public boolean unload(Vehicle vehicle) {
        if (!isPaid(vehicle)) {
            return false;
        }
        if (vehicle.getType().equals("Car")) {
            currentCarAvailability ++;
            System.out.println(ANSI_RED+"Car Lots available amount: " + currentCarAvailability);
            carLots.remove((Car)vehicle);
        }
        else if (vehicle.getType().equals("Truck")) {
            currentTruckAvailability ++;
            System.out.println("Truck Lots available amount: " + currentTruckAvailability);
            truckLots.remove((Truck)vehicle);
        }
        else if (vehicle.getType().equals("Bike")) {
            currentBikeAvailability ++;
            System.out.println("Bike Lots available amount: "+ANSI_RESET + currentBikeAvailability);
            bikeLots.remove((Bike)vehicle);
        }
        return true;
    }
}
