import java.util.Objects;

/**
 * Objet représentant un couple d'objet.
 * Cette classe est générique.
 * */
public class CoupleObj<K,V> implements Couple<K,V>{

    private K premier;

    private V deuxieme;

    public CoupleObj(K k,V v){
        premier = k;
        deuxieme = v;
    }

    @Override
    public String toString() {
        return "{" + premier +
                " / " + deuxieme +
                '}';
    }

    @Override
    public K premier() {
        return premier;
    }

    @Override
    public V second() {
        return deuxieme;
    }

    @Override
    public void defPremier(K k) {
        this.premier = k;
    }

    @Override
    public void defSecond(V v) {
        this.deuxieme = v;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof CoupleObj)){
            return false;
        }

        CoupleObj<K, V> coupleObj = (CoupleObj<K, V>) object;

        return premier.equals(coupleObj.premier) && deuxieme.equals(coupleObj.deuxieme);
    }

}
