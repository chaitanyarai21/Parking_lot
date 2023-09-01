package src.main.services.vehicle;

import src.main.enums.VehicleType;

public class Electric extends Vehicle {
    public Electric(String licenseNumber) {
        super(VehicleType.ELECTRIC, licenseNumber);
    }
}