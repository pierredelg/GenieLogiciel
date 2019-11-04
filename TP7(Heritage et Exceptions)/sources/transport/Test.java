package transport;

public class Test {

    public static void main(String[] args) {

        Good good = new Good(100, 20);
        Good good1 = new Good(1000000, 2000000);
        Good good2 = new Good(13, 2);
        Good good3 = new Good(1000, 0);
        Good good4 = new Good(15, 2);
        Good good5 = new Good(16, 20);

        Fluvial fluvial = new Fluvial(2400);
        Road road = new Road(100);
        Air air = new Air(10);
        Urgent urgent = new Urgent(20);

        try {


            fluvial.add(good3);
            System.out.println("Le cout du fluivial est = " + fluvial.cost());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage() + " pour le fluivial");
        }

        try {

            road.add(good);
            road.add(good2);
            road.add(good3);
            road.add(good4);
            road.add(good5);
            System.out.println("Le cout du routier est = " + road.cost());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage() + " pour le routier");
        }

        try {

            air.add(good1);
            System.out.println("Le cout de l'aérien est = " + air.cost());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage() + " pour l'aérien");
        }

        try {
            urgent.add(good);
            urgent.add(good2);
            System.out.println("Le cout de l'urgent est = " + urgent.cost());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage() + " pour l'urgent");
        }

    }
}
