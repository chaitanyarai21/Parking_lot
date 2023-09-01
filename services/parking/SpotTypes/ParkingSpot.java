package src.main.services.Parking.SpotTypes;

import src.main.enums.ParkingSpotTypes;
import src.main.services.vehicle.Vehicle;

public class ParkingSpot {
    private final String number;
    private Vehicle vehicle;
    private boolean isUnderMaintenance;
    private final ParkingSpotTypes type;


    public ParkingSpot(ParkingSpotTypes type, String number) {
        this.type = type;
        this.number = number;
        this.isUnderMaintenance = false;
        this.vehicle = null;
    }

    public boolean isAvailable() {
        return !isUnderMaintenance && vehicle == null;
    }

    public void assignVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void removeVehicle() {
        this.vehicle = null;
    }

    public boolean isUnderMaintenance() {
        return isUnderMaintenance;
    }

    public void setUnderMaintenance(boolean underMaintenance) {
        isUnderMaintenance = underMaintenance;
    }

    public String getNumber() {
        return number;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpotTypes getType() {
        return type;
    }

}
