package src.main.services.vehicle;

import src.main.enums.VehicleType;

public class Van extends Vehicle{
    public Van(String licenseNumber) {
        super(VehicleType.VAN, licenseNumber);
    }
}