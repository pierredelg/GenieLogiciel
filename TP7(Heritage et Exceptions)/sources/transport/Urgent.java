package transport;

public class Urgent extends Air {

    public Urgent(int distance) {
        super(distance);
    }

    @Override
    public int cost() {
        return 2 * super.cost();
    }
}
