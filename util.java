package src.main;

import src.main.enums.ParkingTicketStatus;
import src.main.exceptions.TicketNotFoundException;
import src.main.exceptions.TransactionFailedException;
import src.main.services.Parking.ParkingLot;
import src.main.services.Parking.ParkingTicket;
import src.main.services.models.Payment;

public class util {
    public static void processPayment(ParkingTicket ticket)
    {
        if(ticket!=null && ticket.getTicketStatus() == ParkingTicketStatus.PAID)
        {
            System.out.println("Already Paid!!");
            return;
        }
        ParkingLot obj = ParkingLot.getInstance();
        double amount = 0;
        try {
            amount = obj.payAndCheckout(ticket);
        } catch (TicketNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        Payment pay = new Payment();
        try {
            pay.initiateTransaction(amount);
        } catch (TransactionFailedException e) {
            System.out.println(e.getMessage());
            return;
        }
        obj.removeTicket(ticket);
        ticket.changeStatusToPaid();
    }
}
