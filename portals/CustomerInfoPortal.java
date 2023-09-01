package src.main.portals;

import src.main.services.Parking.ParkingTicket;
import src.main.util;

public class CustomerInfoPortal {
    private String id;
    public CustomerInfoPortal(String id)
    {
        this.id = id;
    }
    public void processPayment(ParkingTicket ticket)
    {
        util.processPayment(ticket);
    }


}