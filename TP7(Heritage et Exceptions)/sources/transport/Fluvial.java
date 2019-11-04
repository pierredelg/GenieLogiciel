package transport;

public class Fluvial extends ShipmentTypeWeight {

    public Fluvial(int distance) {
        super(distance,30000);
    }

    @Override
    public int cost() {
        return (int) (distance * Math.sqrt(quantity));
    }
}
