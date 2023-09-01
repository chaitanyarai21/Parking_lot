package src.main.services.panels;

public class ElectricPanel {
    private long payedAmount =0;
    private long chargingStartTime;
    private long chargingEndTime;


    public boolean cancelCharging() {
        chargingEndTime = System.currentTimeMillis();
        payedAmount += (chargingEndTime - chargingStartTime) * 10;
        return true;
    }
    public Boolean startCharging(){
        chargingStartTime = System.currentTimeMillis();
        return true;
    }
    public long getPayedAmount(){
        return this.payedAmount;
    }
}