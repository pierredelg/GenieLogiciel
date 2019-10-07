import java.util.Iterator;

public class DictIterator<K> implements Iterator<K> {

    Dictionnaire<K,?> dictionnaire;

    public DictIterator(Dictionnaire<K, ?> dictionnaire) {
        this.dictionnaire = dictionnaire;
    }

    /**
     *
     * @return true - si la collection possède encore des éléments à itérer.
     */
    @Override
    public boolean hasNext() {
        return !dictionnaire.estVide();
    }

    /**
     *  Retourne l'élément suivant.
     * @return - l'élément suivant.
     */
    @Override
    public K next() {
        return null;
    }
}
