package transport;

public abstract class ShipmentTypeVolume extends Shipment{

    public ShipmentTypeVolume(int distance,int limite) {
        super(distance,limite);
    }

    public void add(Good good) throws IllegalStateException {
        if(quantity + good.getVolume() < limite){
            goods.add(good);
            quantity += good.getVolume();
        }else{
            throw new IllegalStateException("quantité dépassée");
        }
    }
}
