package src.main.services.panels;

import src.main.services.Parking.ParkingTicket;
import src.main.util;

public class ExitPanel {
    private String id;
    public ExitPanel(String id)
    {
        this.id = id;
    }
    public void processPayment(ParkingTicket ticket)
    {
        util.processPayment(ticket);
    }
    public String getId()
    {
        return this.id;
    }
}