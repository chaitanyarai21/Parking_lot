package src.main.services.vehicle;

import src.main.enums.VehicleType;

public abstract class Vehicle {
    private String licenseNumber;
    private final VehicleType type;

    public Vehicle(VehicleType type, String licenseNumber) {
        this.type = type;
        this.licenseNumber = licenseNumber;
    }


    public String getLicenseNumber() {
        return licenseNumber;
    }

    public VehicleType getType() {
        return type;
    }

}