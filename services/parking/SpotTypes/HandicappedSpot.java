package src.main.services.Parking.SpotTypes;

import src.main.enums.ParkingSpotTypes;

public class HandicappedSpot extends ParkingSpot {
    public HandicappedSpot(String number) {
        super(ParkingSpotTypes.HANDICAPPED, number);
    }
}
