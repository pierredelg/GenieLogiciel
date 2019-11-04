package transport;

public class Air extends ShipmentTypeVolume {

    public Air(int distance) {
        super(distance,80000);
    }

    @Override
    public int cost() {
        return 10 * (distance + 4) * quantity;
    }
}
