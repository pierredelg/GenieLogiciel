import java.util.Scanner;

/**
 * Représentation d'un arbre binaire de chaine de caractères
 * Le but est de un programme qui reconnaît des animaux en posant des questions.
 * La machine possède au départ très peu de connaissances et apprend progressivement en jouant.
 */
public class NoeudArbre {

    private String noeud;
    private NoeudArbre noeudArbreGauche;
    private NoeudArbre noeudArbreDroit;
    private Scanner  scanner =  new Scanner(System.in);

    private static NoeudArbre derniereFeuille;

    public NoeudArbre(){}

    public NoeudArbre(String noeud){
        this.noeud = noeud;
    }

    public String getNoeud() {
        return noeud;
    }

    public void setNoeud(String noeud) {
        this.noeud = noeud;
    }

    public NoeudArbre getNoeudArbreGauche() {
        return noeudArbreGauche;
    }

    public void setNoeudArbreGauche(NoeudArbre noeudArbreGauche) {
        this.noeudArbreGauche = noeudArbreGauche;
    }

    private NoeudArbre getNoeudArbreDroit() {
        return noeudArbreDroit;
    }

    private void setNoeudArbreDroit(NoeudArbre noeudArbreDroit) {
        this.noeudArbreDroit = noeudArbreDroit;
    }

    /**
     * Vérifie si le noeud est une feuille.
     * Une feuille est un noeud dont les noeuds à droite et à gauche sont vide.
     *
     * @return true - Si le noeud est une feuille.
     */
    public boolean estUneFeuille(){
        return noeudArbreGauche == null && noeudArbreDroit == null;
    }

    /**
     * Affichage de chaque noeud de l'arbre en préfixe (Noeud central, Noeud Gauche , Noeud Droit)
     * @return Une chaine de caractères représentant chaque noeuds en préfixe.
     */
    @Override
    public String toString() {

        String result = "";

        if(noeud != null){
            result += noeud + " ";
        }
        if(noeudArbreGauche != null){
            result += noeudArbreGauche.toString() + " ";
        }
        if(noeudArbreDroit != null){
            result += noeudArbreDroit + " ";
        }
        return result;
    }

    /**
     * Recherche de l'animal par l'utilisateur.
     * On parcourt l'arbre suivant les réponses de l'utilisateur.
     * A la fin l'ordinateur propose un animal en fonction des réponses de l'utilisateur.
     * @return true - Si l'ordinateur trouve l'animal.
     */
    private boolean rechercherAnimal(){

        String reponse;

        //Si c'est une
        if(estUneFeuille()) {
            System.out.println("Est-ce " + this.noeud + " ?");
        }else{
            System.out.println(this.noeud);
        }
        reponse = scanner.nextLine();

        if(estUneFeuille() && testOui(reponse)){
            return true;
        }
        if(estUneFeuille() && testNon(reponse)){
            derniereFeuille = this;
            return false;
        }

        if(getNoeudArbreGauche() != null && testNon(reponse)){
            return getNoeudArbreGauche().rechercherAnimal();
        }

        if(getNoeudArbreDroit() != null && testOui(reponse)){
            return getNoeudArbreDroit().rechercherAnimal();
        }
        return false;

    }

    /**
     * Lorsque l'ordinateur ne trouve pas l'animal de l'utilisateur,
     * Il apprend en demandant à l'utilisateur :
     * <ul>
     *     <li>Le nom de l'animal choisi</li>
     *     <li>Une question qui le différencie de l'animal proposé par l'ordinateur</li>
     *     <li>La réponse à la précédente question qui définie le nouvel animal</li>
     * </ul>
     */
    public void apprendre(){

        String dernierAnimal = derniereFeuille.getNoeud();

        System.out.println("Qu’est-ce que c’est ?");
        String nouvelAnimal;
        nouvelAnimal = scanner.nextLine();

        System.out.println( nouvelAnimal  + " ! Je ne connais pas cet animal. Donnez-moi une question qui permette de différencier " + nouvelAnimal +  " d’ " + derniereFeuille + " ?");
        String nouvelleQuestion = scanner.nextLine();
        derniereFeuille.setNoeud(nouvelleQuestion);

        System.out.println("Quelle doit être la réponse pour " + nouvelAnimal + " ?");
        String nouvelleRéponse = scanner.nextLine();

        if(testOui(nouvelleRéponse)) {
            derniereFeuille.setNoeudArbreDroit(new NoeudArbre(nouvelAnimal));
            derniereFeuille.setNoeudArbreGauche(new NoeudArbre(dernierAnimal));
        }
        if(testNon(nouvelleRéponse)){
            derniereFeuille.setNoeudArbreDroit(new NoeudArbre(dernierAnimal));
            derniereFeuille.setNoeudArbreGauche(new NoeudArbre(nouvelAnimal));
        }


    }

    /**
     * Permet de lancer le jeu à partir du premier noeud de l'arbre.
     * Lorsque le jeu est terminé, on propose de rejouer.
     */
    public void jouer() {
        String reponse;
        do {
            if (rechercherAnimal()) {
                System.out.println("Trouvé !");
            } else {
                System.out.println("Perdu ! ");
                apprendre();
            }
            System.out.println("Arbre complet : " + this.toString());
            System.out.println("Voulez-vous rejouer ?");
             reponse = scanner.nextLine();

        }while(testOui(reponse));
    }

    /**
     * On teste une chaine de caractères afin qu'elle soit égale à oui / Oui / OUI
     * @param reponse - La chaine de caractères à tester.
     * @return true - Si la chaine de caractères correspond à oui/Oui/OUI
     */
    private boolean testOui(String reponse){
        return reponse != null && (reponse.equals("oui") || reponse.equals("Oui") || reponse.equals("OUI"));
    }

    /**
     * On teste une chaine de caractères afin qu'elle soit égale à non / Non / NON
     * @param reponse - La chaine de caractères à tester.
     * @return true - Si la chaine de caractères correspond à non/Non/NON
     */
    private boolean testNon(String reponse){
        return reponse != null && (reponse.equals("non") || reponse.equals("Non") || reponse.equals("NON"));
    }

    /**
     *
     * @param animal
     */
    private void definir(String animal){

    }

    /**
     *  Remplit l'arbre selon un tableau de chaine de caractères
     * @param parametres - Tableau de chaine de caractères
     * @param indexDebut
     * @return
     */
    private NoeudArbre remplirArbre(String[] parametres, int indexDebut){

        if(parametres.length == indexDebut){
            return new NoeudArbre(parametres[indexDebut]);
        }
//        return new NoeudArbre()
//
//
//
//        for (int i = indexDebut; i < args.length ; i= i+3){
//            if(args[i] != null){
//                this.setNoeud();new NoeudArbre(args[i]);
//                if( args[i+1] != null){
//                    n1.setNoeudArbreGauche(new NoeudArbre(args[i+1]));
//                    if(args[i+2] != null){
//                        n1.setNoeudArbreDroit(new NoeudArbre(args[i+2]));
//                    }
//                }
//            }
//        }
        return null;
    }

    public static void main(String[] args) {

        NoeudArbre n1 = new NoeudArbre();

        if(args.length == 0) {

            n1 = new NoeudArbre("Est-ce un mammifère ?");
            NoeudArbre n2 = new NoeudArbre("un crocodile");
            NoeudArbre n3 = new NoeudArbre("Est-ce qu'il aboie ?");

            n1.setNoeudArbreGauche(n2);
            n1.setNoeudArbreDroit(n3);

            NoeudArbre n4 = new NoeudArbre("un cheval");
            NoeudArbre n5 = new NoeudArbre("un chien");

            n3.setNoeudArbreGauche(n4);
            n3.setNoeudArbreDroit(n5);

        }else{

            if(args[1].equals("--definir")){
                n1.definir(args[2]);
            }else {
                n1 = n1.remplirArbre(args,0);
            }

        }

        n1.jouer();
    }
}
