import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Représentation d'un arbre binaire de chaine de caractères
 * Le but est de un programme qui reconnaît des animaux en posant des questions.
 * La machine possède au départ très peu de connaissances et apprend progressivement en jouant.
 */
public class NoeudArbre {

    /**
     * Chaine de caractères représentant le noeud de l'arbre.
     */
    private String noeud;

    /**
     * Arbre gauche.
     */
    private NoeudArbre noeudArbreGauche;

    /**
     * Arbre droit.
     */
    private NoeudArbre noeudArbreDroit;

    private static NoeudArbre derniereFeuille;

    public NoeudArbre(){}

    /**
     * Constructeur de l'arbre NoeudArbre.
     * Les arbres droit et gauche sont initialisés à null.
     * @param noeud - La chaine de caractère représentant le noeud.
     */
    public NoeudArbre(String noeud){
        this.noeud = noeud;
    }

    /**
     * Constructeur complet de l'arbre NoeudArbre.
     * @param noeud - La chaine de caractère représentant le noeud.
     * @param arbreGauche - l'arbre gauche.
     * @param arbreDroit - l'arbre droit.
     */
    public NoeudArbre(String noeud, NoeudArbre arbreGauche,NoeudArbre arbreDroit){
        this.noeud = noeud;
        this.noeudArbreGauche = arbreGauche;
        this.noeudArbreDroit = arbreDroit;
    }

    /**
     * Affichage de chaque noeud de l'arbre en préfixe (Noeud central, Noeud Gauche , Noeud Droit)
     * @return Une chaine de caractères représentant chaque noeuds en préfixe.
     */
    @Override
    public String toString() {

        if(noeud == null){
            return "";
        }
        if(estUneFeuille()){
            return noeud;
        }
        return noeud + " " + noeudArbreGauche + " " + noeudArbreDroit;
    }

    /**
     * Vérifie si le noeud est une feuille.
     * Une feuille est un noeud dont les noeuds à droite et à gauche sont vide.
     *
     * @return true - Si le noeud est une feuille.
     */
    public boolean estUneFeuille(){
        return noeud != null && noeudArbreGauche == null && noeudArbreDroit == null;
    }

    /**
     * Vérifie si l'arbre est vide.
     * @return true - Si l'arbre est vide.
     */
    public boolean estVide(){
        return noeud == null;
    }

    /**
     * Recherche de l'animal par l'utilisateur.
     * On parcourt l'arbre suivant les réponses de l'utilisateur.
     * A la fin l'ordinateur propose un animal en fonction des réponses de l'utilisateur.
     * @return true - Si l'ordinateur trouve l'animal.
     */
    public boolean rechercherAnimal(){

        String reponse;

        if(estUneFeuille()) {
            System.out.println("Est-ce " + noeud + " ?");
        }else{
            System.out.println(noeud);
        }
        reponse = saisieOuiNon();

        if(estUneFeuille() && testOui(reponse)){
            return true;
        }
        if(estUneFeuille() && testNon(reponse)){
            derniereFeuille = this;
            return false;
        }

        if(noeudArbreGauche != null && testNon(reponse)){
            return noeudArbreGauche.rechercherAnimal();
        }

        if(noeudArbreDroit != null && testOui(reponse)){
            return noeudArbreDroit.rechercherAnimal();
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

        Scanner  scanner =  new Scanner(System.in);

        String dernierAnimal = null;

        if(derniereFeuille != null) {
            dernierAnimal = derniereFeuille.noeud;
        }

        System.out.println("Qu’est-ce que c’est ?");
        String nouvelAnimal;
        nouvelAnimal = scanner.nextLine();

        System.out.println( nouvelAnimal  + " ! Je ne connais pas cet animal. Donnez-moi une question qui permette de différencier " + nouvelAnimal +  " d’ " + derniereFeuille + " ?");
        String nouvelleQuestion = scanner.nextLine();
        derniereFeuille.noeud = nouvelleQuestion;

        System.out.println("Quelle doit être la réponse pour " + nouvelAnimal + " ?");
        String nouvelleReponse = saisieOuiNon();

        if(testOui(nouvelleReponse)) {
            derniereFeuille.noeudArbreDroit = new NoeudArbre(nouvelAnimal);
            derniereFeuille.noeudArbreGauche = new NoeudArbre(dernierAnimal);
        }
        if(testNon(nouvelleReponse)){
            derniereFeuille.noeudArbreDroit = new NoeudArbre(dernierAnimal);
            derniereFeuille.noeudArbreGauche = new NoeudArbre(nouvelAnimal);
        }
    }

    /**
     * Permet de lancer le jeu à partir du premier noeud de l'arbre.
     * Lorsque le jeu est terminé, on propose de rejouer.
     */
    public void jouer(String fichierDeSortie) {
        String reponse;
        do {
            if (rechercherAnimal()) {
                System.out.println("Trouvé !");
            } else {
                System.out.println("Perdu ! ");
                apprendre();
                if(fichierDeSortie != null) {
                    //ecrireFichier(fichierDeSortie, this);
                }
            }
            System.out.println("Arbre complet : " + this.toString());
            System.out.println("Voulez-vous rejouer ?");
             reponse = saisieOuiNon();

        }while(testOui(reponse));

        System.out.println("**********Fin du Jeu***********");
        System.out.println("\n       Merci d'avoir jouer");
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
     * Permet d'effectuer la saisie de données par l'utilisateur.
     * En vérifiant que la chaine de caractere correspond à 'oui' ou 'non'.
     * @return La chaine de caractère correspondant à 'oui' ou 'non'.
     */
    private String saisieOuiNon(){
        Scanner  scanner =  new Scanner(System.in);

        String reponse = null;
        do{
            if(reponse != null){
                System.out.println("Merci de répondre par oui ou non : ");
            }
            reponse = scanner.nextLine();
        }while(!testNon(reponse) && !testOui(reponse));
        return reponse;
    }

    /**
     *  Remplit l'arbre selon un tableau de chaine de caractères.
     * @param parametres - Tableau de chaine de caractères.
     * @param index - index de départ.
     */
    private void remplirArbre(String[] parametres, int index){

       for (int i = index;i< parametres.length;i++){
           ajouterUnElement(parametres[i]);
       }

    }

    /**
     * Ajoute une chaine de caractère dans l'arbre
     * @param parametre - La chaine de caractère à insérer.
     */
    private void ajouterUnElement(String parametre) {

        if(this.estVide()){
            this.noeud = parametre;
        }else {
            if (this.noeudArbreGauche == null) {
                this.noeudArbreGauche = new NoeudArbre(parametre);
            } else {
                if (this.noeudArbreDroit == null) {
                    this.noeudArbreDroit = new NoeudArbre(parametre);
                }else{
                    this.noeudArbreDroit.ajouterUnElement(parametre);
                }
            }
        }
    }

    /**
     * Permet d'afficher le chemin vers le noeud donné en parametre.
     * @param animal - L'animal donné en parametre.
     * @return la chaine de carctères avec le chemin trouvé.
     */
    public String defini(String animal){

        if(this.noeud.contains(animal)) {
            return " => " + this.noeud;
        }
        if(this.noeudArbreGauche != null && this.noeudArbreDroit != null) {

            if(this.noeudArbreGauche.noeud.contains(animal)){
                return this.noeud + " -> " + " non ; " + this.noeudArbreGauche.noeud;
            }

            if(this.noeudArbreDroit.noeud.contains(animal)){
                return this.noeud + " -> " + " oui ; " + this.noeudArbreDroit.noeud;
            }

            if (this.noeudArbreGauche.estUneFeuille() && !this.noeudArbreGauche.noeud.contains(animal)) {
                return this.noeud + " -> " + " oui ; " + this.noeudArbreDroit.defini(animal);
            }

            if (this.noeudArbreDroit.estUneFeuille() && !this.noeudArbreDroit.noeud.contains(animal)) {
                return this.noeud + " -> " + " non ; " + this.noeudArbreGauche.defini(animal);
            }
        }
        return " => pas d'animal";
    }

    private String enleveGuillemet(String chaine){
        //chaine.substring()
        return null;
    }

    public static void main(String[] args) {

        NoeudArbre n1 = new NoeudArbre();

        if(args.length == 0) {
            String [] arbre = {"Est-ce un mammifère","un crocodile","Est-ce qu'il aboie ?","un cheval","un chien"};
        }

        if(args[0].equals("--definir")){

            n1.remplirArbre(args,2);

            //On récupere l'animal en deuxieme parametre sans les guillemets
            String animal = (String) args[1].subSequence(0,(args[1].length()));

            //On affiche le résultat du chemin trouvé par définir
            System.out.println(n1.defini(animal));
        }
        System.out.println(args[0]);
        System.out.println(args[1]);
        System.out.println(args[args.length -1]);

        System.err.println(n1);

        if(args[0].contains(".txt")){

          /*  String fichierDentree = args[0].split("<")[1].substring(0,args[0].split("<")[1].length()-1);
            String fichierDeSortie = args[1].split(">")[1];
            String[] arbreTexte = n1.lireTexte(fichierDentree).split("\"");
            arbreTexte = n1.enleveEspace(arbreTexte);
            n1.remplirArbre(arbreTexte,0);
            try {
                System.setErr(new PrintStream(new FileOutputStream(new File(fichierDeSortie))));
            } catch (FileNotFoundException e) {
                System.out.println("Fichier introuvable");
            }
            System.err.println(n1);
            System.out.println(n1);
            n1.ecrireFichier(fichierDeSortie,n1);*/
        }

      /*  n1.remplirArbre(args,0);
        System.out.println(n1);

        n1.jouer(null);
*/
        //$(<arbreInitial.txt) 2>arbreFinal.txt
        //--definir "caniche" "Est-ce un mammifère ?" "Est-ce un crustacé ?" "Est-ce un poisson ?" "un aigle" "un thon" "Est-ce qu'il est brun ?" "une crevette" "un homard" "Est-ce qu’il aboie ?" "Est-ce qu'il miaule ?" "un lapin" "un chat" "Est-ce qu'il est grand ?" "un caniche" "un st-bernard"

    }
}
