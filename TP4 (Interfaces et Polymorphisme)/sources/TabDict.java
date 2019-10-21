/**
 * Cette classe représente un dictionnaire au sens informatique.
 * Elle associe deux objet par un systeme de clés unique et d'une valeur associée à cette clé.
 * @author DELGRANGE Pierre
 * */
public class TabDict<K,V>  implements  Dictionnaire<K,V> , Iterable<K>
{
    private static final int INIT_SIZE = 100 ;       // taille initiale du tableau
    private Couple<K,V> [] associations ;            // tableau contenant les associations
    private int nbAssoc ;	// nombre d'elements effectivement presents dans le dictionnaire

    /** Crée une instance de dictionnaire vide */
    public TabDict() {
        nbAssoc = 0 ;
        associations = (Couple<K,V>[]) new Couple[INIT_SIZE] ;
    }

    @Override
    public String toString() {
        String result;
        result =  "TabDict (taille = "+ nbAssoc + ") { " +"associations=";
        for (Couple couple : associations){
            if(couple != null){
                result += "\n" + couple;
            }
        }
        result += " }";
        return result;
    }

    // redimensionnement automatique du tableau en une taille double
    private void resize() {
        Couple<K,V> [] newAssociation = (Couple<K,V>[]) new Couple[INIT_SIZE * 2];
        int i = 0;
        for(Couple<K,V> couple: associations){
            newAssociation[i] = couple;
            i++;
        }
        this.associations = newAssociation;
    }

    // ajoute une association à la première position libre (après avoir 
    // redimensionné le tableau si nécessaire)
    private void add(Couple<K,V> assoc) {
        if(associations.length == nbAssoc){
            this.resize();
        }
        for(int i  = 0; i <= nbAssoc;i ++){
            if(associations[i] == null){
                associations[i] = assoc;
                nbAssoc++;
                break;
            }
        }
    }

    // enlève l'association à l'indice spécifié
    void remove(int index) {
        if(index >= 0 && index < nbAssoc && associations[index] != null) {
            associations[index] = null;
            nbAssoc--;
        }
    }

    // indice de l'association assoc ; -1 si elle est absente 
    private int indexOf(Couple<K,V> assoc) {
        for(int i  = 0; i < nbAssoc;i ++) {
            if(associations[i] != null && associations[i].equals(assoc)){
                return i;
            }
        }
        return -1;
    }
    
    // indice de l'association de clef c ; -1 si elle est absente
    private int indexOfClef(K c) {
        for(int i  = 0; i < nbAssoc;i ++) {
            if(associations[i] != null && associations[i].premier().equals(c)) {
                return i;
            }
        }
        return -1;
    }

    // méthode nécessaire pour l'itérateur :
    // retourne la clef située à l'indice i, null si i incorrect
    K clefPourIndex(int i) {
        if(i >= 0 && i < nbAssoc){
            return associations[i].premier();
        }
        return null;
    }
    
    // IMPLÉMENTATION DE L'INTERFACE Dictionnaire
    

    // IMPLÉMENTATION DE L'INTERFACE Iterable (2e partie du TP)
    // /** Itérateur permettant de parcourir les clefs (et d'en supprimer) */
     public DictIterator<K> iterator() {
         return new DictIterator<K>(this) ;
     }

    @Override
    public boolean estVide() {
        return nbAssoc == 0;
    }

    @Override
    public boolean contient(Couple<K, V> assoc) {
        return indexOf(assoc) != -1;
    }

    @Override
    public boolean contientClef(K c) {
        return indexOfClef(c) != -1;
    }

    @Override
    public boolean contientValeur(V v) {
        for(int i = 0 ; i <= nbAssoc ; i++){
            if(associations[i] != null && associations[i].second() != null && associations[i].second().equals(v)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int nbElements() {
        return nbAssoc;
    }

    @Override
    public Couple<K, V> assocPour(K c) {
        return indexOfClef(c) != -1 ? associations[indexOfClef(c)] : null;
    }

    @Override
    public V valeurPour(K c) {
        return indexOfClef(c) != -1 && associations[indexOfClef(c)] != null ? associations[indexOfClef(c)].second() : null;
    }

    @Override
    public void ajouter(Couple<K, V> assoc) {
        add(assoc);
    }

    @Override
    public void ajouter(K c, V v) {
        add(new CoupleObj<>(c,v));
    }

    @Override
    public void enlever(Couple<K, V> assoc) {
        remove(indexOf(assoc));
    }

    @Override
    public void enleverPour(K c) {
        remove(indexOfClef(c));
    }


    public static void main(String[] args) {
        TabDict tab = new TabDict();
        Couple<String,String> c1 = new CoupleObj<>("chat","ronronne");
        Couple<String,String> c2 = new CoupleObj<>("chien","aboie");
        Couple<String,String> c3 = new CoupleObj<>("cheval","hennie");
        Couple<String,String> c4 = new CoupleObj<>("pigeon","roucoule");
        Couple<String,String> c5 = new CoupleObj<>("loup","grogne");

        System.out.println("On ajoute c1,c2,c3,c4,c5");
        tab.add(c1);
        tab.add(c2);
        tab.add(c3);
        tab.add(c4);
        tab.add(c5);

        System.out.println(tab);

        System.out.println("On retire c5 ");
        tab.remove(4);
        System.out.println(tab);

        tab.resize();

        System.out.println("indice de c2 (indexOf)  = " + tab.indexOf(c2));
        System.out.println("indice de c5 (indexOf)  = " + tab.indexOf(c5));
        System.out.println("indice de c1 (indexOfCle)  = " + tab.indexOfClef(c1.premier()));
        System.out.println("indice de c5 (indexOfCle)  = " + tab.indexOfClef(c5.premier()));
        System.out.println("clé de 0 (clefPourIndex)  = " + tab.clefPourIndex(0));
        System.out.println("clé de 10 (clefPourIndex)  = " + tab.clefPourIndex(10));

        System.out.println();
        System.out.println("Tab 2");
        TabDict tab2 = new TabDict();
        tab2.ajouter(c1.premier(),c1.second());
        tab2.ajouter(c2);
        tab2.ajouter(c3);
        tab2.ajouter(c4);
        tab2.ajouter(c5);

        System.out.println(tab2);

        System.out.println("On enleve c5");
        tab2.enlever(c5);
        System.out.println(tab2);

        System.out.println("On enleve (enlever pour) c1");
        tab2.enleverPour(c1.premier());
        System.out.println(tab2);

        System.out.println("contient c1 = " + tab2.contient(c1));
        System.out.println("contient c3 = " + tab2.contient(c3));

        System.out.println("contient la clé de c1 = " + tab2.contientClef(c1.premier()));
        System.out.println("contient la clé de c2 = " + tab2.contientClef(c2.premier()));

        System.out.println("contient la valeur de c1 = " + tab2.contientValeur(c1.second()));
        System.out.println("contient la valeur de c1 = " + tab2.contientValeur(c1.second()));
    }
}
