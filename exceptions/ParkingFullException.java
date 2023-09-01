package src.main.exceptions;

public class ParkingFullException extends Exception{
    public ParkingFullException() {
        super("Parking is Full!");
    }
}
