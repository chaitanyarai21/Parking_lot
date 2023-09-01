package src.main.services.Parking;

import src.main.enums.ParkingSpotTypes;
import src.main.enums.VehicleType;
import src.main.portals.CustomerInfoPortal;
import src.main.services.Parking.SpotTypes.ParkingSpot;

import java.util.HashMap;
import java.util.Map;

public class ParkingFloor {
    private String name;
    private HashMap<String,ParkingSpot> handicappedSpots = new HashMap<>();
    private HashMap<String, ParkingSpot> compactSpots= new HashMap<>();
    private HashMap<String, ParkingSpot> largeSpots= new HashMap<>();
    private HashMap<String, ParkingSpot> motorcycleSpots= new HashMap<>();
    private HashMap<String, ParkingSpot> electricSpots=new HashMap<>();
    private HashMap<String, CustomerInfoPortal> infoPortals= new HashMap<>();
    private ParkingDisplayBoard displayBoard;
    public CustomerInfoPortal customerInfoPortal;

    public ParkingFloor(String name) {
        this.name = name;
       this.customerInfoPortal = new CustomerInfoPortal(name + " CustomerPortal");
    }

    public String ParkingFloorName(){
        return this.name;
    }

    public void addParkingSpot(ParkingSpot spot) {
        switch (spot.getType()) {
            case HANDICAPPED:
                handicappedSpots.put(spot.getNumber(), spot);
                break;
            case COMPACT:
                compactSpots.put(spot.getNumber(), spot);
                break;
            case LARGE:
                largeSpots.put(spot.getNumber(), spot);
                break;
            case MOTORCYCLE:
                motorcycleSpots.put(spot.getNumber(), spot);
                break;
            case ELECTRIC:
                electricSpots.put(spot.getNumber(), spot);
                break;
            default:
                System.out.println("Wrong parking spot type!");
        }
    }

    public ParkingSpot getSpot(VehicleType vehicleType) {
        ParkingSpotTypes parkingSpotType = pickCorrectSpot(vehicleType);
        HashMap<String, ParkingSpot> relevantParkingSpot = null;
        ParkingSpot Spot = null;
        switch (parkingSpotType) {
            case HANDICAPPED:
                relevantParkingSpot = handicappedSpots;
                break;
            case COMPACT:
                relevantParkingSpot = compactSpots;
                break;
            case LARGE:
                relevantParkingSpot = largeSpots;
                break;
            case MOTORCYCLE:
                relevantParkingSpot = motorcycleSpots;
                break;
            case ELECTRIC:
                relevantParkingSpot = electricSpots;
                break;

        }
        for (Map.Entry<String, ParkingSpot>parkingSpot : relevantParkingSpot.entrySet()) {
            if (parkingSpot.getValue().isAvailable()) {
                return parkingSpot.getValue();
            }
        }

        return null;
    }



    private ParkingSpotTypes pickCorrectSpot(VehicleType vehicleType) {
        if (vehicleType.equals(VehicleType.MOTORCYCLE))
            return ParkingSpotTypes.MOTORCYCLE;
        else if (vehicleType.equals(VehicleType.ELECTRIC))
            return ParkingSpotTypes.ELECTRIC;
        else if (vehicleType.equals(VehicleType.TRUCK))
            return ParkingSpotTypes.LARGE;
        else if (vehicleType.equals(VehicleType.CAR))
            return ParkingSpotTypes.COMPACT;

        return null;
    }

    public int getCompactSpotsCount() {
        return compactSpots.size();
    }

    public int getLargeSpotsCount() {
        return largeSpots.size();
    }

    public int getMotorcycleSpotsCount() {
        return motorcycleSpots.size();
    }

    public int getElectricSpotsCount() {
        return electricSpots.size();
    }
    public int getCompactFreeSpots(){
        int compactFreeSpotCount=0;
        for (Map.Entry<String, ParkingSpot>parkingSpot : compactSpots.entrySet()) {
            if (parkingSpot.getValue().isAvailable()) {
                compactFreeSpotCount++;
            }
        }
        return compactFreeSpotCount;
    }
    public int getLargeFreeSpots(){
        int largeFreeSpotCount=0;
        for (Map.Entry<String, ParkingSpot>parkingSpot : largeSpots.entrySet()) {
            if (parkingSpot.getValue().isAvailable()) {
                largeFreeSpotCount++;
            }
        }
        return largeFreeSpotCount;
    }
    public int getMotorCycleFreeSpots(){
        int motorCycleFreeSpotCount=0;
        for (Map.Entry<String, ParkingSpot>parkingSpot : motorcycleSpots.entrySet()) {
            if (parkingSpot.getValue().isAvailable()) {
                motorCycleFreeSpotCount++;
            }
        }
        return motorCycleFreeSpotCount;
    }
    public int getElectricFreeSpots(){
        int electricFreeSpotCount=0;
        for (Map.Entry<String, ParkingSpot>parkingSpot : electricSpots.entrySet()) {
            if (parkingSpot.getValue().isAvailable()) {
                electricFreeSpotCount++;
            }
        }
        return electricFreeSpotCount;
    }

    public void displayFloor(){

        System.out.println("Welcome to the Parking Floor "+ name);
        int compactFreeSpot = getCompactFreeSpots();
        System.out.println("Number of empty spot of Compact type vehicles = " + compactFreeSpot);
        int largeFreeSpot = getLargeFreeSpots();
        System.out.println("Number of empty spot of Large type vehicles = " + largeFreeSpot);
        int motorFreeSpot = getMotorCycleFreeSpots();
        System.out.println("Number of empty spot of Motor Cycle type vehicles = " + motorFreeSpot);
        int electricFreeSpot = getElectricFreeSpots();
        System.out.println("Number of empty spot of Electric type vehicles = " + electricFreeSpot);
    }

}