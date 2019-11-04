package transport;

import java.util.ArrayList;
import java.util.List;

public abstract class Shipment {

    protected int distance;

    protected int limite;

    protected int quantity;

    protected List<Good> goods;

    public Shipment(int distance,int limite) {
        this.limite = limite;
        this.distance = distance;
        this.goods = new ArrayList<>();
    }

    /**
     * Ajoute une marchandise dans cette cargaison si cela est encore possible,
     * dans le cas contraire une exception IllegalStateException est levée.
     */
    abstract void add(Good good) throws IllegalStateException;

    /**
     * Calcule le coût total du transport de cette cargaison.
     * @return le coût total du transport.
     */
    abstract int cost();
}
