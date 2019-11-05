package dictionnaire;
import java.util.ArrayList;
import java.util.Iterator;

/** La classe ArrayListDict implémente un Dictionnaire au moyen d'un
 *  attribut ArrayList. Avantages : le redimensionnement est facilité,
 *  mais surtout la généricité est plus facile à prendre en compte que
 *  dans un tableau.
 */
public class ArrayListDict<K,V> implements Dictionnaire<K,V>
{
private ArrayList<Couple<K,V> > elems;

/** Construit un dictionnaire vide */
public ArrayListDict() {
        elems = new ArrayList<Couple<K,V> >();
}

/** Teste si le dictionnaire ne contient aucune association */
public boolean estVide() {
        return elems.isEmpty();
}

/** Teste si le dictionnaire contient l'association assoc */
public boolean contient(Couple <K,V> assoc) {
        return elems.contains(assoc);
}

/** Teste si le dictionnaire possède une association de clef c */
public boolean contientClef(K c) {
        for (Couple<K,V> cp: elems)
                if (cp.premier().equals(c))
                        return true;
        return false;
}

/** Teste si le dictionnaire possède une association de valeur v */
public boolean contientValeur(V v) {
        for (Couple<K,V> cp: elems)
                if (cp.second().equals(v))
                        return true;
        return false;
}

/** Retourne le nombre d'associations du dictionnaire */
public int nbElements() {
        return elems.size();
}

/** Retourne l'association correspondant à la clef c spécifiée,
 * null si absente */
public Couple<K,V> assocPour(K c) {
        for (Couple<K,V> cp: elems)
                if (cp.premier().equals(c))
                        return cp;
        return null;
}

/** Retourne la valeur associée à le clef c, null si absente */
public V valeurPour(K c) {
        for (Couple<K,V> cp: elems)
                if (cp.premier().equals(c))
                        return cp.second();
        return null;
}

/** Ajoute l'association assoc au dictionnaire (remplacement si clef présente) */
public void ajouter(Couple<K,V> assoc) {
        elems.add(assoc);
}

/** Définit ou modifie la valeur v associée à la clef c dans le dictionnaire */
public void ajouter(K c, V v) {
        elems.add(new CoupleObj<K,V>(c,v));
}

/** Enlève l'association assoc du dictionnaire (si présente) */
public void enlever(Couple<K,V> assoc) {
        elems.remove(assoc);
}

/** Enlève l'entrée de clef c (si présente) */
public void enleverPour(K c) {
        Iterator<Couple<K,V> > it = elems.iterator();
        boolean trouve = false;
        while ((it.hasNext()) && (!trouve)) {
                if (it.next().premier().equals(c)) {
                        trouve = true;
                        it.remove();
                }
        }
}

public String toString() {
        StringBuffer b = new StringBuffer();
        for (Couple<K,V> c: elems)
                b.append(c + "\n");
        return b.toString();
}
}
