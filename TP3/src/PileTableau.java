import java.util.Arrays;

public class PileTableau<E> implements Pile<E>{

    private E [] elements ;  // les éléments contenus dans la pile
    private int nbElem = 0 ; // le nombre d’éléments dans la pile
    private String nom ;     // nom de la pile : information supplémentaire propre à PileTableau

    private PileTableau(String nom) {
        this.elements = (E[]) new Object[MAX_ELEMENTS];
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
    public boolean peutEmpiler(E x) {
        return !pleine();
    }

    @Override
    public E sommet() {
        for(int i = 0; i < elements.length ; i++ ){
            if(elements[i] == null){
                return elements[i-1];
            }
        }
        return null;
    }

    @Override
    public E depile() {
        E element = elements[nbElements() - 1];
        elements[nbElements() - 1] = null;
        nbElem--;
        return element;
    }

    @Override
    public void empile(E x) {
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
    public void deplacerUnElementVers(Pile<E> p) {
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

    public static void main(String[] args) {
        PileTableau<Integer> pileInteger = new PileTableau<>("Pile Integer");
        PileTableau<String> pileString = new PileTableau<>("Pile String");
        PileTableau<Rationnel> pileRationnel = new PileTableau<>("Pile Rationnel");

        pileInteger.empile(0);
        pileInteger.empile(1);
        pileInteger.empile(2);
        pileInteger.empile(3);

        System.out.println(pileInteger);

        pileInteger.depile();

        System.out.println(pileInteger);

        System.out.println("est vide ? = "+pileInteger.vide());

        System.out.println("est pleine ? =" + pileInteger.pleine());

        System.out.println("on vide");

        pileInteger.vider();

        System.out.println("est vide ? = "+pileInteger.vide());

        PileTableau<Comparable> test = new PileTableau<>("Pile Comparable");
        test.empile(1);
        test.empile("salut");
        test.empile(new Rationnel(1));

        System.out.println(test);


    }
}
