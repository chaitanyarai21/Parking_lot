package src.main.services.vehicle;

import src.main.enums.VehicleType;

public class Truck extends Vehicle {
    public Truck(String licenseNumber) {
        super(VehicleType.TRUCK, licenseNumber);
    }
}