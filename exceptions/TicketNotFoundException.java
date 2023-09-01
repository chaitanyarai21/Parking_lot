package src.main.exceptions;

public class TicketNotFoundException extends Exception{
    public TicketNotFoundException() {
        super("Ticket Not found!");
    }
}
