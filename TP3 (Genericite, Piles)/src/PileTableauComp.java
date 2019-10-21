import java.util.Arrays;

public class PileTableauComp implements Pile<Comparable>{
    private Comparable [] elements ;  // les éléments contenus dans la pile
    private int nbElem = 0 ; // le nombre d’éléments dans la pile
    private String nom ;     // nom de la pile : information supplémentaire propre à PileTableau

    public PileTableauComp(String nom) {
        this.elements = new Comparable[MAX_ELEMENTS];
        this.nom = nom;
    }

    @Override
    public boolean vide() {
        return nbElem == 0;
    }

    @Override
    public boolean pleine() {
        return nbElem == Pile.MAX_ELEMENTS;
    }

    @Override
    public boolean peutEmpiler(Comparable x) {
        return !pleine();
    }

    @Override
    public Comparable sommet() {
        for(int i = 0; i < elements.length ; i++ ){
            if(elements[i] == null){
                return elements[i-1];
            }
        }
        return null;
    }

    @Override
    public Comparable depile() {
        Comparable element = elements[nbElements() - 1];
        elements[nbElements() - 1] = null;
        nbElem--;
        return element;
    }

    @Override
    public void empile(Comparable x) {
        if(peutEmpiler(x)) {
            elements[nbElements()] = x;
            nbElem++;
        }
    }

    @Override
    public void vider() {
        Arrays.fill(elements, null);
        nbElem = 0;
    }

    @Override
    public int nbElements() {
        return nbElem;
    }

    @Override
    public void deplacerUnElementVers(Pile<Comparable> p) {
        System.out.println("Pile de départ : " + this.getNom());
        p.empile(sommet());
        this.depile();
        System.out.println("Pile d'arrivée : " + p.getNom());
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return nom +" [ taille = " + nbElem +
                " ] elements = {" + Arrays.toString(elements) +
                '}';
    }

    public void trier(){
        Comparable temp = null;
        for(int i = 0 ; i < elements.length ; i++){
            for(int j = 1 ; j < (elements.length - i) ; j++){
                if(elements[j-1] != null && elements[j] != null && elements[j-1].compareTo(elements[j]) < 0 ){
                    temp = elements[j-1];
                    elements[j-1] = elements[j];
                    elements[j] = temp;
                }
            }
        }
    }
}
