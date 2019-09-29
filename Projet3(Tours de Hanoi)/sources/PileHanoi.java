import java.util.Arrays;

/**
 * Représente une tour de Hanoi (une pile composée de Disque).
 *
 */
public class PileHanoi implements Pile<Disque> {
    /**
     * Les éléments contenus dans la pile.
     */
    private Disque [] elements;

    /**
     * Le nombre d’éléments dans la pile.
     */
    private int nbElements = 0;

    /**
     * Le nom de la pile.
     */
    private String nom;

    /**
     * L'affichage de la pile.
     * Est initialisé en AffichageSimple.
     */
    private Affichage algoAffichage = new AffichageSimple();

    /**
     * Nombre de déplacements expérimentals.
     */
    private static int nombreDeDeplacementExperimental = 0;

    /**
     * Contructeur de la pile de Hanoi en renseignant uniquement le nom.
     * Peut contenir au maximum le MAX_ELEMENTS de l'interface Pile.
     * @param nom - Chaine de caractères représantant le nom de la pile.
     */
    public PileHanoi(String nom) {
        this.nom = nom;
        elements = new Disque[MAX_ELEMENTS];
    }

    /**
     * Contructeur de la pile de Hanoi en renseignant le nom et le type d'affichage souhaité.
     * Peut contenir au maximum le MAX_ELEMENTS de l'interface Pile.
     * @param nom - Chaine de caractères représantant le nom de la pile.
     * @param affichage - L'affichage souhaité pour cette pile (de type Affichage).
     */
    public PileHanoi(String nom, Affichage affichage) { // constructeur specifiant un algorithme d’affichage particulier
        this.nom = nom ;
        algoAffichage = affichage ;
        elements = new Disque[MAX_ELEMENTS];
    }

    /**
     * Permet de connaitre le nom de la pile.
     * @return - Le nom de la pile.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Le nombre de déplacements calculé de façon expérimental.
     * @return - Le nombre de déplacements.
     */
    public static int getNombreDeDeplacementExperimental() {
        return nombreDeDeplacementExperimental;
    }

    /**
     * Affichage des éléments de la pile.
     * Utilise la méthode affichage_tableau() de l'interface Affichage.
     * @return - Une chaine de caractère contenant l'affichage de la pile.
     */
    public String toString() {
        Disque [] lesDisques = elements; // les disques contenus dans la pile courante
        return nom + " : " + algoAffichage.affichage_tableau(lesDisques, nbElements) ;
    } // ...

    /**
     * Permet de savoir si la pile est vide.
     * @return true - Si la pile est vide.
     */
    @Override
    public boolean vide() {
        return nbElements == 0;
    }

    /**
     * Permet de savoir si la pile est pleine.
     * Si elle est arrivée au MAX_ELEMENTS de l'interface Pile.
     * @return
     */
    @Override
    public boolean pleine() {
        return nbElements == Pile.MAX_ELEMENTS;
    }

    /**
     * Vérifie que la pile puisse empiler un autre disque c'est à dire :
     * <ul>
     *     <li>Que la pile ne soit pas pleine.</li>
     *     <li>Que le diamètre du disque à empiler soit inférieur au disque au sommet.</li>
     * </ul>
     * @param disque - Le disque à empiler
     * @return true - Si la pile peut empiler un nouveau disque.
     */
    @Override
    public boolean peutEmpiler(Disque disque) {
        if(vide()){
            return true;
        }else {
            return !pleine() && disque.diametre() < sommet().diametre();
        }
    }

    /**
     * Permet de récupérer le disque au sommet de la pile.
     * @return - Le disque au sommet de la pile.
     */
    @Override
    public Disque sommet() {
        if(!vide()) {
            return elements[nbElements - 1];
        }
        return null;
    }

    /**
     * Permet d'enlever le disque au sommet de la pile.
     * @return - Le disque retiré.
     */
    @Override
    public Disque depile() {
        Disque element = elements[nbElements() - 1];
        elements[nbElements() - 1] = null;
        nbElements--;
        return element;
    }

    /**
     * Ajoute un disque sur la pile si c'est possible.
     * Sinon rien n'est fait.
     * @param disque - Le disque à empiler.
     */
    @Override
    public void empile(Disque disque) {
        if(peutEmpiler(disque)){
            elements[nbElements] = disque;
            nbElements++;
        }
    }

    /**
     * On vide complétement la pile.
     */
    @Override
    public void vider() {
        Arrays.fill(elements, null);
        nbElements = 0;
    }

    /**
     *
     * @return - Le nombre d'éléments présent dans la pile.
     */
    @Override
    public int nbElements() {
        return nbElements;
    }

    /**
     * Déplace un élément d'une pile vers l'autre si c'est possible.
     * Sinon rien n'est fait.
     * @param pile - La pile sur laquelle on déplace le disque.
     */
    @Override
    public void deplacerUnElementVers(Pile<Disque> pile) {
        if(pile.peutEmpiler(sommet())) {

            pile.empile(sommet());
            this.depile();

            //On ajoute un déplacement au nombre de déplacements
            nombreDeDeplacementExperimental++;
        }
    }

    /**
     * On déplace les disques de la pile de départ vers la pile de destination en passant si besoin par une pile
     * intermédiaire.
     * @param numeroDeDisque - Le nombre de disque.
     * @param pileDestination - La pile de destination.
     * @param pileIntermediaire - la pile intermédiaire.
     */
    public void deplacerDesDisques(int numeroDeDisque, Pile pileDestination, Pile pileIntermediaire){
        PileHanoi intermHanoi = (PileHanoi) pileIntermediaire;
        PileHanoi destHanoi = (PileHanoi) pileDestination;

        //Si c'est nul on ne fait rien
        if (numeroDeDisque != 0){

            //On appel la méthode pour déplacer n-1 disques de la pile de départ vers la pile intermédiaire
            deplacerDesDisques(numeroDeDisque-1, pileIntermediaire, destHanoi);

            //On affiche le déplacement du disque
            System.out.println("On déplace le disque " + numeroDeDisque + " de " + getNom() + " vers " + destHanoi.getNom());

            //On déplace le disque de la pile d'origine restant vers la pile de destination
            deplacerUnElementVers(destHanoi);

            //On appel la méthode pour déplacer les n-1 disques de la pile intermédiaire vers la pile de destination
            intermHanoi.deplacerDesDisques(numeroDeDisque-1, destHanoi, this);
        }
    }
}
