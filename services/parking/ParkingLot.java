package src.main.services.Parking;

import src.main.enums.ParkingSpotTypes;
import src.main.enums.VehicleType;
import src.main.exceptions.FloorNotFoundException;
import src.main.exceptions.ParkingFullException;
import src.main.exceptions.TicketNotFoundException;
import src.main.services.Parking.SpotTypes.ParkingSpot;
import src.main.services.panels.EntrancePanel;
import src.main.services.panels.ExitPanel;
import src.main.services.vehicle.Vehicle;
import src.main.users.ParkingAttendant;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private String name;
    private String address;
    private ParkingRate parkingRate;
    public ParkingDisplayBoard  display= new ParkingDisplayBoard();

    private int compactSpotCount;
    private int largeSpotCount;
    private int motorcycleSpotCount;
    private int electricSpotCount;
    private int maxCompactCount;
    private int maxLargeCount;
    private int maxMotorcycleCount;
    private int maxElectricCount;

    private HashMap<String, EntrancePanel> entrancePanels = new HashMap<>();
    private HashMap<String, ExitPanel> exitPanels=new HashMap<>();
    private HashMap<String, ParkingFloor> parkingFloors = new HashMap<>();

    private HashMap<String, ParkingTicket> activeTickets = new HashMap<>();
    public ParkingAttendant parkingAttendant;

    private static ParkingLot parkingLot = null;


    public int getCompactSpotCount(){
        return this.compactSpotCount;
    }

    public int getLargeSpotCount() {
        return largeSpotCount;
    }

    public int getMotorcycleSpotCount() {
        return motorcycleSpotCount;
    }

    public int getElectricSpotCount() {
        return electricSpotCount;
    }

    public int getMaxCompactCount() {
        return maxCompactCount;
    }

    public int getMaxLargeCount() {
        return maxLargeCount;
    }

    public int getMaxMotorcycleCount() {
        return maxMotorcycleCount;
    }

    public int getMaxElectricCount() {
        return maxElectricCount;
    }


    private ParkingLot(String name, String address) {
        this.name = name;
        this.address = address;
        this.parkingAttendant = new ParkingAttendant("Attendant1");
        this.parkingRate = new ParkingRate();
        this.compactSpotCount = 0;
        this.largeSpotCount = 0;
        this.motorcycleSpotCount = 0;
        this.electricSpotCount = 0;
        this.maxCompactCount = 0;
        this.maxLargeCount = 0;
        this.maxMotorcycleCount = 0;
        this.maxElectricCount = 0;
    }

    public static ParkingLot getInstance() {
        if (parkingLot == null) {
            parkingLot = new ParkingLot("New","abcd");
        }
        return parkingLot;
    }

    public ParkingFloor getParkingFloor(String name){
        ParkingFloor floor = parkingFloors.get(name);
        return floor;
    }

    public synchronized ParkingTicket getNewParkingTicket (Vehicle vehicle) throws ParkingFullException{
        if (this.isFull(vehicle.getType())) {
            throw new ParkingFullException();
        }

        ParkingSpot ParkingSpot = getParkingSpotForVehicle(vehicle.getType());
        if (ParkingSpot == null)
            return null;

        ParkingTicket ticket = new ParkingTicket(vehicle,ParkingSpot);
        ParkingSpot.assignVehicle(vehicle);
        this.incrementSpotCount(vehicle.getType());
        this.activeTickets.put(ticket.getTicketNumber(), ticket);
        return ticket;
    }

    public Boolean isFull(VehicleType type) {
        if (type == VehicleType.TRUCK ) {
            return largeSpotCount >= maxLargeCount;
        }
        if (type == VehicleType.MOTORCYCLE) {
            return motorcycleSpotCount >= maxMotorcycleCount;
        }
        if (type == VehicleType.CAR) {
            return (compactSpotCount ) >= (maxCompactCount );
        }
        return ( electricSpotCount) >= ( maxElectricCount);
    }

    private void incrementSpotCount(VehicleType type) {
        if (type == VehicleType.TRUCK ) {
            largeSpotCount++;
        } else if (type == VehicleType.MOTORCYCLE) {
            motorcycleSpotCount++;
        } else if (type == VehicleType.CAR) {
                compactSpotCount++;
        } else {
                electricSpotCount++;
        }
    }

    private void decrementSpotCount(ParkingSpotTypes type) {
        if (type == ParkingSpotTypes.LARGE) {
            largeSpotCount--;
        }
        else if (type == ParkingSpotTypes.COMPACT) {
            compactSpotCount--;
        }
        else if (type == ParkingSpotTypes.MOTORCYCLE) {
            motorcycleSpotCount--;
        }
        else if (type == ParkingSpotTypes.ELECTRIC) {
            electricSpotCount--;
        }
    }


    public Boolean addParkingFloor(ParkingFloor floor) {

        parkingFloors.put(floor.ParkingFloorName(), floor);
        this.maxCompactCount += floor.getCompactSpotsCount();
        this.maxLargeCount += floor.getLargeSpotsCount();
        this.maxMotorcycleCount += floor.getMotorcycleSpotsCount();
        this.maxElectricCount += floor.getElectricSpotsCount();
        return true;
    }

    public void addParkingSpot(String floorName, ParkingSpot spot) throws FloorNotFoundException
    {
        ParkingFloor floor = getParkingFloor(floorName);
        if(floor == null)
            throw new FloorNotFoundException();
        floor.addParkingSpot(spot);
        switch (spot.getType()) {
            case COMPACT:
                this.maxCompactCount++;
                break;
            case LARGE:
                this.maxLargeCount++;
                break;
            case MOTORCYCLE:
                this.maxMotorcycleCount++;
                break;
            case ELECTRIC:
                this.maxElectricCount++;
                break;
    }}



    public void addEntrancePanel(EntrancePanel entrancePanel) {
        entrancePanels.put(entrancePanel.getId(),entrancePanel);
    }

    public void addExitPanel(ExitPanel exitPanel) {
        exitPanels.put(exitPanel.getId(), exitPanel);
    }

    private ParkingSpot getParkingSpotForVehicle(VehicleType vehicleType) {
        ParkingSpot ParkingSpot = null;
        for (Map.Entry<String, ParkingFloor> floor : parkingFloors.entrySet()) {
            ParkingSpot = floor.getValue().getSpot(vehicleType);
            if (ParkingSpot != null)
                break;
        }
        return ParkingSpot;
    }

    public double payAndCheckout(ParkingTicket ticket) throws TicketNotFoundException{
        if(ticket == null || !activeTickets.containsKey(ticket.getTicketNumber()))
            throw new TicketNotFoundException();
        ticket.setExitTime(System.currentTimeMillis());
        double price = parkingRate.calculatePrice(ticket);
        if(ticket.getParkingSpot().getType() == ParkingSpotTypes.ELECTRIC)
        {
            price += 10;
        }
        ticket.setTicketPrice(price);
        return price;
    }
    public void removeTicket(ParkingTicket ticket)
    {
        this.activeTickets.remove(ticket.getTicketNumber());
        ticket.getParkingSpot().removeVehicle();
        decrementSpotCount(ticket.getParkingSpot().getType());
    }
    public ParkingTicket getTicketByTicketNumber(String ticketNumber){
        if(activeTickets.containsKey(ticketNumber))
            return activeTickets.get(ticketNumber);
        else
            return null;
    }
}
