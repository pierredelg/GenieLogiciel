package transport;

public class Good{

    protected int weight;
    protected int volume;

    public Good(int weight, int volume){
        this.volume = volume;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public int getVolume() {
        return volume;
    }
}