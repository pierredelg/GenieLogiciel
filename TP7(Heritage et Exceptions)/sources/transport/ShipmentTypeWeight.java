package transport;

public abstract class ShipmentTypeWeight extends Shipment{



    public ShipmentTypeWeight(int distance,int limite) {
        super(distance,limite);
    }

    public void add(Good good) throws IllegalStateException {
        if(quantity + good.getWeight() < limite){
            goods.add(good);
            quantity += good.getWeight();
        }else{
            throw new IllegalStateException("quantité dépassée");
        }
    }


}
