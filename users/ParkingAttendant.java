package src.main.users;
//import account, ParkingAttendantPrtal

import src.main.portals.ParkingAttendantPortal;
import src.main.services.Parking.ParkingTicket;
import src.main.services.models.Account;

public class ParkingAttendant extends Account {
    public ParkingAttendant(String userName)
    {
        super(userName);
    }
    public Boolean processTicket(ParkingTicket ticket){
        ParkingAttendantPortal.processPayment(ticket);
        return true;
    }
}
