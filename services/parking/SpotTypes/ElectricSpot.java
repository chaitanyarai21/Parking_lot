package src.main.services.Parking.SpotTypes;

import src.main.enums.ParkingSpotTypes;
import src.main.services.panels.ElectricPanel;

public class ElectricSpot extends ParkingSpot {
    private ElectricPanel electricPanel = null;

    public ElectricSpot(String number) {
        super(ParkingSpotTypes.ELECTRIC, number);
        this.electricPanel = new ElectricPanel();
    }
    public long getChargingAmount(){
       return this.electricPanel.getPayedAmount();
    }
}
