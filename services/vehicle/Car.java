package src.main.services.vehicle;

import src.main.enums.VehicleType;

public class Car extends Vehicle {
    public Car(String licenseNumber) {
        super(VehicleType.CAR, licenseNumber);
    }

}