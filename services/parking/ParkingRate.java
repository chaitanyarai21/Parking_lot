package src.main.services.Parking;

public class ParkingRate {

    private final int firstHourCost = 50;
    private final int secondAndThirdHourCost = 10;
    private final int remainingHourCost = 5;

    double calculatePrice(ParkingTicket ticket) {
        int duration = (int) Math.ceil((ticket.getExitTime() - ticket.getEntryTime()) / (1000*3600));
        double cost = firstHourCost;
        duration--;
        if(duration<=0){
            return cost;
        }
        if(duration>2){
            cost+=2*secondAndThirdHourCost+(duration-2)*remainingHourCost;
        }
        else {
            cost += duration * secondAndThirdHourCost;
        }
        ticket.setTicketPrice(cost);
        return cost;
    }
}
