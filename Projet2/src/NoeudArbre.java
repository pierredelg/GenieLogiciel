import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

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

    private Scanner  scanner =  new Scanner(System.in);

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
     * @return La chaine de caractères représentant le noeud.
     */
    public String getNoeud() {
        return noeud;
    }

    /**
     * Modifie la chaine de caractères représentant le noeud.
     * @param noeud - La nouvelle chaine de caractère
     */
    public void setNoeud(String noeud) {
        this.noeud = noeud;
    }

    /**
     * @return L'arbre gauche.
     */
    public NoeudArbre getNoeudArbreGauche() {
        return noeudArbreGauche;
    }

    /**
     * Modifie l'arbre gauche avec un nouvel arbre.
     * @param noeudArbreGauche - Le nouvel arbre gauche.
     */
    public void setNoeudArbreGauche(NoeudArbre noeudArbreGauche) {
        this.noeudArbreGauche = noeudArbreGauche;
    }

    /**
     * @return L'arbre droit.
     */
    public NoeudArbre getNoeudArbreDroit() {
        return noeudArbreDroit;
    }

    /**
     * Modifie l'arbre droit avec un nouvel arbre.
     * @param noeudArbreDroit - Le nouvel arbre.
     */
    private void setNoeudArbreDroit(NoeudArbre noeudArbreDroit) {
        this.noeudArbreDroit = noeudArbreDroit;
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
        return noeudArbreGauche == null && noeudArbreDroit == null;
    }

    /**
     * Vérifie si l'arbre est vide.
     * @return true - Si l'arbre est vide.
     */
    public boolean estVide(){
        return this.noeud == null;
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
            System.out.println("Est-ce " + this.noeud + " ?");
        }else{
            System.out.println(this.noeud);
        }
        reponse = saisieOuiNon();

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

        String dernierAnimal = null;

        if(derniereFeuille != null) {
            dernierAnimal = derniereFeuille.getNoeud();
        }

        System.out.println("Qu’est-ce que c’est ?");
        String nouvelAnimal;
        nouvelAnimal = scanner.nextLine();

        System.out.println( nouvelAnimal  + " ! Je ne connais pas cet animal. Donnez-moi une question qui permette de différencier " + nouvelAnimal +  " d’ " + derniereFeuille + " ?");
        String nouvelleQuestion = scanner.nextLine();
        derniereFeuille.setNoeud(nouvelleQuestion);

        System.out.println("Quelle doit être la réponse pour " + nouvelAnimal + " ?");
        String nouvelleReponse = saisieOuiNon();

        if(testOui(nouvelleReponse)) {
            derniereFeuille.setNoeudArbreDroit(new NoeudArbre(nouvelAnimal));
            derniereFeuille.setNoeudArbreGauche(new NoeudArbre(dernierAnimal));
        }
        if(testNon(nouvelleReponse)){
            derniereFeuille.setNoeudArbreDroit(new NoeudArbre(dernierAnimal));
            derniereFeuille.setNoeudArbreGauche(new NoeudArbre(nouvelAnimal));
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
                    ecrireFichier(fichierDeSortie, this);
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
    static Stack<String> pile = new Stack<>();

    public String defini(String animal){
        if(this.getNoeud().contains(animal)) {
            return " => " + this.getNoeud();
        }

        if(this.getNoeudArbreGauche() != null && this.getNoeudArbreGauche().getNoeud().contains(animal)){
            return this.getNoeud() + " -> " + " oui ; " + this.getNoeudArbreGauche().getNoeud();
        }

        if(this.getNoeudArbreDroit() != null && this.getNoeudArbreDroit().getNoeud().contains(animal)){
            return this.getNoeud() + " -> " + " oui ; " + this.getNoeudArbreDroit().getNoeud();
        }

        if(this.getNoeudArbreGauche().estUneFeuille() && !this.getNoeudArbreGauche().getNoeud().contains(animal)){
            return this.getNoeud() + " -> " + " oui ; " + this.getNoeudArbreDroit().defini(animal);
        }

        if(this.getNoeudArbreDroit().estUneFeuille() && !this.getNoeudArbreDroit().getNoeud().contains(animal)){
            return this.getNoeud() + " -> " + " non ; " + this.getNoeudArbreGauche().defini(animal);
        }

        return " => pas d'animal";
    }

    public boolean definir(String animal){

        if(this.getNoeud().contains(animal)) {
            pile.push("=>");
            pile.push(this.getNoeud());
            return true;
        }else{

            pile.push(this.getNoeud());
            pile.push("- > ");

            if(this.getNoeudArbreGauche() != null){
                pile.push("non");

                if(this.getNoeudArbreGauche().definir(animal)){

                }
            }
            if (this.getNoeudArbreDroit() != null) {
                pile.push("oui");

                if (this.getNoeudArbreDroit().definir(animal)) {

                }
            }

        }
        return false;
    }

    private static String lireTexte(String fichier){

        BufferedReader lecteurAvecBuffer = null;
        String text = "";
        try
        {
            lecteurAvecBuffer = new BufferedReader(new FileReader(fichier));
        }
        catch(FileNotFoundException exc)
        {
            System.out.println("Erreur d'ouverture");
        }

        try {
            String line = null;
            do {
                if (lecteurAvecBuffer != null) {
                    line = lecteurAvecBuffer.readLine();
                    if(line != null) {
                        text += line;
                    }
                }
            }while (line != null);

            if (lecteurAvecBuffer != null) {
                lecteurAvecBuffer.close();
            }
        }catch (IOException e){
            System.out.println("Erreur de lecture");
        }
        return text;
    }

    private static void ecrireFichier(String fichier, NoeudArbre noeudArbre){

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fichier);
        } catch (FileNotFoundException e) {
            System.out.println("Aucun fichier" + fichier);
        }

        if (writer != null) {
            writer.println(noeudArbre);
            writer.close();
        }
    }
    private static String [] enleveEspace(String [] tableau){

        ArrayList<String> liste = new ArrayList<>();
        for(int i  = 0; i < tableau.length;i++){
            if(!tableau[i].equals("") && !tableau[i].equals(" ")){
                liste.add(tableau[i]);
            }
        }
        String [] retour = new String[liste.size()];
        int i = 0;
        for(String s : liste){
            retour[i] = s;
            i++;
        }
        return retour;
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

            if(args[0].equals("--definir")){
                n1.remplirArbre(args,2);
                System.out.println("arbre = " + n1);
                String animal = (String) args[1].subSequence(0,(args[1].length()));
                System.out.println(n1.definir(animal));
            }
            if(args[0].contains("$(<")){
                String fichierDentree = args[0].split("<")[1].substring(0,args[0].split("<")[1].length()-1);
                String fichierDeSortie = args[1].split(">")[1];
                String[] arbreTexte = lireTexte(fichierDentree).split("\"");
                arbreTexte = enleveEspace(arbreTexte);
                n1.remplirArbre(arbreTexte,0);
                System.out.println(n1);
                ecrireFichier(fichierDeSortie,n1);
                n1.jouer(fichierDeSortie);
            }else {
                n1.remplirArbre(args,0);
                System.out.println(n1);
            }

        }

    }
}