package src.main.services.Parking;


public class ParkingDisplayBoard{
    public int getCompactFreeSpotCount(){
        int compactFreeSpot=ParkingLot.getInstance().getMaxCompactCount()-ParkingLot.getInstance().getCompactSpotCount();
        return compactFreeSpot;
    }
    public int getLargeFreeSpotCount(){
        int largeFreeSpot=ParkingLot.getInstance().getMaxLargeCount()-ParkingLot.getInstance().getLargeSpotCount();
        return largeFreeSpot;
    }
    public int getMotorCycleFreeSpotCount(){
        int motorFreeSpot=ParkingLot.getInstance().getMaxMotorcycleCount()-ParkingLot.getInstance().getMotorcycleSpotCount();
        return motorFreeSpot;
    }
    public int getElectricFreeSpotCount() {
        int electricFreeSpot = ParkingLot.getInstance().getMaxElectricCount() - ParkingLot.getInstance().getElectricSpotCount();
        return electricFreeSpot;
    }

    public void displayFull(){

        System.out.println("Welcome to the Parking lot.");
        int compactFreeSpot = getCompactFreeSpotCount();
        System.out.println("Number of empty spot of Compact type vehicles = " + compactFreeSpot);
        int largeFreeSpot = getLargeFreeSpotCount();
        System.out.println("Number of empty spot of Large type vehicles = " + largeFreeSpot);
        int motorFreeSpot = getMotorCycleFreeSpotCount();
        System.out.println("Number of empty spot of Motor Cycle type vehicles = " + motorFreeSpot);
        int electricFreeSpot = getElectricFreeSpotCount();
        System.out.println("Number of empty spot of Electric type vehicles = " + electricFreeSpot);
    }



}