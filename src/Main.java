import java.util.Scanner;

public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void welcomeCenter(ParkingLot parkingLot) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(ANSI_PURPLE+"**************** Hi! Welcome To Our Advanced Parking Lot ****************"+ANSI_RESET);
        System.out.println(ANSI_GREEN+"Please choose your purpose:");
        System.out.println("press 1 for parking");
        System.out.println("press 2 for getting your vehicle."+ANSI_RESET);
        boolean hasNextInt = scanner.hasNextInt();
        if (!hasNextInt) {
            System.out.println(ANSI_RED+"Not a valid number. Please try again!"+ANSI_RESET);
            scanner.next();
        }
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println(ANSI_YELLOW+"You've chosen to park your vehicle");
            enterParkingLot(parkingLot);
        }
        else if (choice == 2) {
            System.out.println("You've chosen to get your vehicle"+ANSI_RESET);
            leaveParkingLot(parkingLot);
        }
        else {
            System.out.println(ANSI_RED+"Not a valid number. Please try again!"+ANSI_RESET);
            welcomeCenter(parkingLot);
        }
    }

    public static void enterParkingLot(ParkingLot parkingLot) {
        Scanner scanner = new Scanner(System.in);
        Vehicle vehicle;
        System.out.println(ANSI_CYAN+"Please choose your Vehicle");
        System.out.println("press 1 for CAR");
        System.out.println("press 2 for TRUCK");
        System.out.println("press 3 for BIKE"+ANSI_RESET);
        boolean hasNextInt = scanner.hasNextInt();
        if (!hasNextInt) {
            System.out.println(ANSI_RED+"Not a valid number. Please try again!"+ANSI_RESET);
            scanner.next();
        }
        int choice = scanner.nextInt();
        if (choice != 1 && choice != 2 && choice != 3) {
            System.out.println(ANSI_RED+"Not a valid number. Please try again!"+ANSI_RESET);
            enterParkingLot(parkingLot);
        }
        System.out.println(ANSI_GREEN+"Please enter you vehicle number:"+ANSI_RESET);
        String plate = scanner.next();
        if (choice == 1) {
            vehicle = new Car(plate, "Car");
        }
        else if(choice == 2){
            vehicle = new Truck(plate, "Truck");
        }
        else {
            vehicle = new Bike(plate,"Bike");
        }
        if (parkingLot.load(vehicle)) {
            System.out.println(ANSI_PURPLE+"Parked Successfully!"+ANSI_RESET);
        } else {
            System.out.println(ANSI_RED+"Sorry, our parking lot is full now. Please try again later."+ANSI_RESET);
        }
    }

    public static void leaveParkingLot(ParkingLot parkingLot) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(ANSI_GREEN+"Please enter your Vehicle number: "+ANSI_RESET);
        String plate = scanner.next();
        Vehicle vehicle = parkingLot.getVehicle(plate);
        if (vehicle == null) {
            System.out.println(ANSI_RED+"Vehicle not found. Press 1 to try again. Press 0 to Main menu."+ANSI_RESET);
            //welcomeCenter(parkingLot);

            boolean hasNextInt = scanner.hasNextInt();
            if (!hasNextInt) {
                System.out.println(ANSI_RED+"Not a valid number. Please try again!"+ANSI_RESET);
                scanner.next();
            }
            int choice = scanner.nextInt();
            if (choice == 1) {
                leaveParkingLot(parkingLot);
            } else {
                welcomeCenter(parkingLot);
            }
        } else {
            if (parkingLot.unload(vehicle)) {
                System.out.println(ANSI_YELLOW+"Thank you for visiting. Have a nice day!  :)"+ANSI_RESET);
            } else {
                System.out.println(ANSI_GREEN+"Please Pay before you leave. Thank you!"+ANSI_RESET);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ParkingLot parkingLot = new ParkingLot();
        int flag = 1;
        while(flag == 1){
            welcomeCenter(parkingLot);
            Thread.sleep(3000);
        }
    }
}


