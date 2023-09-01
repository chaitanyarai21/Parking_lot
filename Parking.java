package src.main;
import src.main.enums.ParkingSpotTypes;
import src.main.services.Parking.ParkingFloor;
import src.main.services.Parking.ParkingLot;
import src.main.services.Parking.ParkingTicket;
import src.main.services.Parking.SpotTypes.ParkingSpot;
import src.main.services.panels.EntrancePanel;
import src.main.services.panels.ExitPanel;
import src.main.services.vehicle.Car;
import src.main.services.vehicle.Vehicle;
import src.main.users.Admin;
public class Parking {
    public static void main(String[] args)  {

        Admin admin = new Admin("Admin");
        ParkingLot parkingLot = ParkingLot.getInstance();
        Vehicle vehicle1 = new Car("UP93-BG-5667");
        Vehicle vehicle2 = new Car("MP34-VT-0007");
        EntrancePanel entrancePanel1 = admin.addEntrancePanel("Entrance1");
        ExitPanel exitPanel1 = admin.addExitPanel("Exit1");
        admin.addParkingFloor(new ParkingFloor("F1"));
        admin.addParkingFloor(new ParkingFloor("F2"));
        admin.addParkingFloor(new ParkingFloor("F3"));
        admin.addParkingSpot("F1",new ParkingSpot(ParkingSpotTypes.COMPACT,"1"));
        admin.addParkingSpot("F1",new ParkingSpot(ParkingSpotTypes.LARGE,"1"));
        admin.addParkingSpot("F1",new ParkingSpot(ParkingSpotTypes.LARGE,"2"));
        admin.addParkingSpot("F2",new ParkingSpot(ParkingSpotTypes.LARGE,"1"));
        admin.addParkingSpot("F2",new ParkingSpot(ParkingSpotTypes.ELECTRIC,"1"));
        admin.addParkingSpot("F2",new ParkingSpot(ParkingSpotTypes.MOTORCYCLE,"1"));
        admin.addParkingSpot("F3",new ParkingSpot(ParkingSpotTypes.ELECTRIC,"1"));
        admin.addParkingSpot("F3",new ParkingSpot(ParkingSpotTypes.MOTORCYCLE,"1"));
        admin.addParkingSpot("F4",new ParkingSpot(ParkingSpotTypes.COMPACT,"1"));
        System.out.println("\n----------------------*******----------------------");
        parkingLot.display.displayFull();
        System.out.println("----------------------*******----------------------\n");
        ParkingTicket ticket1 = entrancePanel1.printTicket(vehicle1);
        System.out.println("\n----------------------*******----------------------");
        parkingLot.display.displayFull();
        System.out.println("----------------------*******----------------------\n");
        ParkingTicket ticket2 = entrancePanel1.printTicket(vehicle2);
        System.out.println("\n----------------------*******----------------------\n");
        ParkingFloor floor = parkingLot.getParkingFloor("F1");
        parkingLot.parkingAttendant.processTicket(ticket1);
        floor.customerInfoPortal.processPayment(ticket1);
        exitPanel1.processPayment(ticket1);
        System.out.println("\n----------------------*******----------------------");
        parkingLot.display.displayFull();
        System.out.println("----------------------*******----------------------\n");
        exitPanel1.processPayment(ticket2);
    }
}
