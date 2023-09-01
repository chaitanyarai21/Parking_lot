package src.main.services.vehicle;

import src.main.enums.VehicleType;

public class MotorCycle extends Vehicle {
    public MotorCycle(String licenseNumber) {
        super(VehicleType.MOTORCYCLE, licenseNumber);
    }
}