package src.main.services.panels;

import src.main.exceptions.ParkingFullException;
import src.main.services.Parking.ParkingLot;
import src.main.services.Parking.ParkingTicket;
import src.main.services.vehicle.Vehicle;

public class EntrancePanel {
    private String id;

    public EntrancePanel(String id)
    {
        this.id = id;
    }
    public String getId()
    {
        return this.id;
    }
    public ParkingTicket printTicket(Vehicle vehicle) {
        ParkingLot obj = ParkingLot.getInstance();
        ParkingTicket ticket = null;
        try {
            ticket = obj.getNewParkingTicket(vehicle);
        } catch (ParkingFullException e) {
            System.out.println("Parking full for = " + vehicle.getType().toString());
            return null;
        }
        System.out.println("Ticket Generated : " + ticket.toString());
        return ticket;
    }
}