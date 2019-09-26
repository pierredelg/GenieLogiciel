import java.util.Scanner;

/**
 * Représentation d'un arbre binaire de chaine de caractères
 * Le but est de un programme qui reconnaît des animaux en posant des questions.
 * La machine possède au départ très peu de connaissances et apprend progressivement en jouant.
 * 
 * @author DELGRANGE Pierre
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

    /**
     * Constructeur pour un arbre vide. Les valeurs sont initialisée à null.
     */
    public NoeudArbre(){
    }

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

        //Si l'arbre est null on retourne une chaine vide
        if(estVide()){
            return "";
        }

        //Si le noeud est une feuille on retourne la feuille
        if(estUneFeuille()){
            return noeud;
        }

        //Sinon on affiche dans l'ordre préfixe (Noeud / arbre gauche / arbre droit) en rappelant la méthode toString()
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

        //Si on tombe sur une feuille c'est que la recherche est terminée
        if(estUneFeuille()) {
            System.out.println("Est-ce " + noeud + " ?");
        }else{
            System.out.println(noeud);
        }
        //On demande à l'utilisateur de répondre par oui ou non
        reponse = saisieOuiNonUtilisateur();


        if(estUneFeuille()) {
            //Si le noeud est une feuille:
            //On retourne true s'il confirme la proposition
            if (testOui(reponse)) {
                System.out.println("Trouvé !");
                return true;
            }
            //On retourne false si l'utilisateur répond par non
            if (testNon(reponse)) {
                System.out.println("Perdu ! ");
                apprendre();
                return false;
            }
        }

        //On recommence la méthode du coté de l'arbre correspondant à la réponse
        //Arbre gauche = non
        //Arbre droit = oui
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

        //On demande le nom du nouvel animal
        System.out.println("Qu’est-ce que c’est ?");
        String nouvelAnimal = scanner.nextLine();

        //On demande à l'utilisateur la nouvelle question sur cet animal
        System.out.println( nouvelAnimal  + " ! Je ne connais pas cet animal. Donnez-moi une question qui permette de différencier " + nouvelAnimal +  " d’ " + noeud + " ?");
        String nouvelleQuestion = scanner.nextLine();

        //On demande la réponse (oui ou non) à cette nouvelle question
        System.out.println("Quelle doit être la réponse pour " + nouvelAnimal + " ?");
        String nouvelleReponse = saisieOuiNonUtilisateur();


        //On place le dernier et le nouvel animal dans les arbres correspondant à la réponse

        //Si oui -> nouvel animal dans l'arbre droit
        if(testOui(nouvelleReponse)) {
            noeudArbreGauche = new NoeudArbre(noeud);
            noeudArbreDroit = new NoeudArbre(nouvelAnimal);
        }
        //Si non -> nouvel animal dans l'arbre gauche
        if(testNon(nouvelleReponse)){
            noeudArbreGauche = new NoeudArbre(nouvelAnimal);
            noeudArbreDroit = new NoeudArbre(noeud);
        }

        //On remplace le noeud courrant par la nouvelle question
        noeud = nouvelleQuestion;
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
    private String saisieOuiNonUtilisateur(){

        Scanner  scanner =  new Scanner(System.in);

        String reponse = null;

        //Tant que l'utilisateur ne reponds pas par oui ou non on recommence
        while(!testNon(reponse) && !testOui(reponse)){

            //Message d'erreur de saisie
            if(reponse != null){
                System.out.println("Merci de répondre par oui ou non : ");
            }
            reponse = scanner.nextLine();
        }
        return reponse;
    }

    /**
     *  Remplit l'arbre selon un tableau de chaine de caractères.
     * @param parametres - Tableau de chaine de caractères.
     * @param index - index de départ.
     */
    private void remplirArbre(String[] parametres, int index){

        //On ajoute chaque élément du tableau dans l'arbre
        for (int i = index;i< parametres.length;i++){
            ajouterUnElement(parametres[i]);
        }
    }

    /**
     * Ajoute une chaine de caractère dans l'arbre
     * @param element - La chaine de caractère à insérer.
     */
    private void ajouterUnElement(String element) {

        //Si l'arbre est vide on ajoute l'élément
        if(estVide()){
            noeud = element;
        }else {
            //Si l'arbre gauche est null on ajoute l'élément dans le nouvel arbre créé
            if (noeudArbreGauche == null) {
                noeudArbreGauche = new NoeudArbre(element);
            } else {
                //Si l'arbre droit est null on ajoute l'élément dans le nouvel arbre créé
                if (noeudArbreDroit == null) {
                    noeudArbreDroit = new NoeudArbre(element);
                }else{
                    //On ajoute l'élément dans l'arbre droit
                    noeudArbreDroit.ajouterUnElement(element);
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

        //Si le noeud correspond à l'animal
        if(noeud.equals(animal)){
            return " => " + noeud;
        }
        //Si l'arbre gauche est l'animal on retourne le chemin allant jusqu'a lui
        if(noeudArbreGauche != null && noeudArbreGauche.noeud.equals(animal)){
            return noeud + " -> non ; " + noeudArbreGauche.defini(animal);
        }
        //S'il ne le contient pas on cherche dans l'arbre droit
        if(noeudArbreGauche != null && noeudArbreGauche.estUneFeuille() && !noeudArbreGauche.noeud.equals(animal)){
            return noeud + " -> oui ; " + noeudArbreDroit.defini(animal);
        }
        //S'il n'est pas trouvé on envoie un message d'erreur
        return animal + " n'est pas présent dans l'arbre";
    }

    /**
     * Vérifie que des guillemets sont présent au début et à la fin de la chaine.
     * @param chaine - La chaine à vérifier.
     * @return true - Si la chaine contient des guillemets au début et à la fin.
     */
    private static boolean contientGuillemets(String chaine){
        return chaine.charAt(0) == '"' && chaine.charAt(chaine.length()-1) == '"';
    }

    /**
     * Retire le caractère de début et de fin de la chaine.
     * @param chaine La chaine à transformer.
     * @return La chaine sans son caractère de début et de fin.
     */
    private static String retireDebutFin(String chaine){
        return chaine.subSequence(0,chaine.length()).toString();
    }

    /**
     * Permet de lancer le jeu à partir du premier noeud de l'arbre.
     * Lorsque le jeu est terminé, on propose de rejouer tant que l'utilisateur répond oui à la question
     */
    public void jouer() {

        String reponse;
        do {
            //On lance la methode de recherche d'un animal
            rechercherAnimal();

            //On affiche l'arbre
            System.out.println("Arbre complet : " + this);

            //On propose à l'utilisateur de rejouer
            System.out.println("Voulez-vous rejouer ?");
            reponse = saisieOuiNonUtilisateur();

            //Tant que l'utilisateur répond oui on recommence
        }while(testOui(reponse));

        System.out.println("**********Fin du Jeu***********");
        System.out.println("\n       Merci d'avoir jouer");
    }


    public static void main(String[] args) {

        //On crée un nouvel arbre vide
        NoeudArbre n1 = new NoeudArbre();

        String [] arbre = null;

        if(args.length != 0) {
            arbre = args;
        }else{
            //S'il n'y a pas d'arguments. On crée un tableau de chaine de caracteres représentant un arbre de base
            arbre = new String[]{"Est-ce un mammifère ?", "un crocodile", "un chien"};
        }

        if(args.length != 0 && args[0].equals("--definir") && args.length > 2) {
            //On construit l'arbre en enlevant les deux premiers parametres
            n1.remplirArbre(args, 2);
        }else{
            n1.remplirArbre(arbre,0);
        }

        System.out.println(n1);

        if (args .length != 0 && args[0].equals("--definir")) {

            //Le deuxieme parametre est l'animal à définir
            String animalADefinir = args[1];

            if (contientGuillemets(args[1])) {
                //On récupere l'animal situé en deuxieme parametre sans les guillemets s'il en a
                animalADefinir = retireDebutFin(args[1]);
            }

            //On affiche le résultat du chemin trouvé par définir
            System.out.println(n1.defini(animalADefinir));

            System.exit(0);
        }else{

            //On lance le jeu
            n1.jouer();

            //Lorsque l'on a terminé on envoie l'arbre sur la sortie d'erreur pour sauvegarder
            System.err.println(n1);
        }
    }
}
