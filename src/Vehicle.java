import java.rmi.server.UnicastRemoteObject;

public abstract class Vehicle {
    private String plate;
    private String type;
    private Ticket ticket;

    public Vehicle(String plate, String type) {

        this.plate = plate;
        this.type = type;

        this.ticket = new Ticket(this);
    }

    public String getPlate() {
        return plate;
    }

    public Ticket getTicket() {
        return ticket;
    }


    public abstract String getType();
}