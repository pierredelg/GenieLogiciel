package transport;

public class Road extends ShipmentTypeWeight {

    public Road(int distance) {
        super(distance,38000);
    }

    @Override
    public int cost() {
        return 4 * distance * quantity;
    }
}
