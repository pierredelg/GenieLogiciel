/** La classe NoeudArbre réalise le jeu des questions.<BR> Pour
* l'utiliser : <BR> Utilisation avec un arbre par défaut : <code>java
* -classpath ./classes NoeudArbre</code> <BR> Utilisation avec un
* arbre donné en notation préfixe : <BR> <code>java -classpath
* ./classes NoeudArbre "Est-ce un mammifère ?" "Est-ce qu'il a des
* pattes ?" "Est-ce qu'il vit dans l'eau ?" "un serpent" "un poisson"
* "Est-ce qu'il a huit pattes ?" "Est-ce qu'il a six pattes ?" "un
* crocodile" "une fourmi" "une araignée" "Est-ce qu'il aboie ?"
* "Est-ce qu'il rugit ?" "Est-ce qu'il vit dans l'eau ?" "un cheval"
* "Est-ce qu'il a des dents ?" "une baleine" "un dauphin" "un lion"
* "un chien"</code><BR>
* Sous Unix on peut utiliser la substitution par le contenu d'un fichier :<BR>
* <code>eval java NoeudArbre $(cat mon Arbre.txt)</code>
*/
public class NoeudArbreCorrection {

  private String contenu="" ; // le contenu du noeud
  private NoeudArbre oui=null, non=null ; // les deux sous-arbres

  // constructeur "de base"
  private NoeudArbre() {
    contenu = "" ;
  }

  /** Constructeur qui initialise le contenu */
  public NoeudArbre(String s) {
    contenu = s ;
  }

  /** Constructeur qui initialise le contenu et crée deux feuilles */
  public NoeudArbre(String quest, String non, String oui) {
    contenu = quest ;
    this.non = new NoeudArbre(non) ;
    this.oui = new NoeudArbre(oui) ;
  }

  /** Retourne le contenu du noeud courant */
  public String contenu() { return "\"" + contenu + "\"" ; }

  // teste si le sous-arbre oui est vide
  private boolean oui() { return (oui != null) ; }

  // teste si le sous-arbre non est vide
  private boolean non() { return (non != null) ; }


  /** Teste si le noeud courant est une feuille */
  public boolean estFeuille() {
    return !(oui() ||  non()) ;
  }

  /** Fixe la valeur des sous-arbres oui et non */
  public void sousArbres(NoeudArbre non, NoeudArbre oui) {
    this.non = non ;
    this.oui = oui ;
  }


  /** Parcours préfixe de l'arbre */
  public String parcoursPrefixe() {
    String s = "" ;
    if (non()) s += " " + non.parcoursPrefixe() ;
    if (oui()) s += " " + oui.parcoursPrefixe() ;
    return contenu() + s ;
  }

  /** Parcours infixe de l'arbre */
  public String parcoursInfixe() {
    String s = "" ;
    if (non()) s += non.parcoursInfixe() + " ";
    s += contenu() ;
    if (oui()) s += " " + oui.parcoursInfixe() ;
    return s ;
  }

  /** Parcours postfixe de l'arbre */
  public String parcoursPostfixe() {
    String s = "" ;
    if (non()) s += non.parcoursPostfixe() + " " ;
    if (oui()) s += oui.parcoursPostfixe() + " " ;
    return s + contenu() ;
  }

  /** Affichage (ici en parcours préfixe) */
  public String toString() {
    return parcoursPrefixe() ;
  }

  /** Poser des questions successivement */
  public void rechercherAnimal() {
    String rep = this.poserQuestion() ;
    if (this.estFeuille())
      if (rep.equalsIgnoreCase("oui"))
        this.victoire() ;
      else this.apprendre() ;
    else
      if (rep.equalsIgnoreCase("oui"))
        oui.rechercherAnimal() ;
      else non.rechercherAnimal() ;
  }

  // Ce qui se passe quand on a trouvé l'animal
  private void victoire() {
    System.out.println("J'ai trouvé !!!") ;
  }

  // Ce qui se passe quand on a perdu : il faut
  // apprendre à distinguer un nouvel animal
  private void apprendre() {
    NoeudArbre ancien, nouveau ;
    String message, rep ;
    // recupérer le nom du nouvel animal
    String nouvelAnimal = poserQuestion("Qu'est-ce que c'est ?") ;
    // créer deux nouvelles feuilles pour les deux animaux
    ancien = new NoeudArbre(contenu) ;
    nouveau = new NoeudArbre(nouvelAnimal) ;
    // recupérer la nouvelle question et la placer dans contenu
    message = nouvelAnimal + " ? Je ne connais pas cet animal.\n" +
              "Donnez-moi une question qui permette de differencier " +
              contenu + " d'"+ nouvelAnimal ;
    contenu = this.poserQuestion(message) ;
    // recupérer la "bonne" réponse pour le nouvel animal
    // pour savoir où placer les deux feuilles
    message = "Quelle doit être la réponse pour " + nouvelAnimal + " ?" ;
    rep = this.poserQuestion(message) ;
    if (rep.equalsIgnoreCase("oui")) sousArbres(ancien, nouveau) ;
    else sousArbres(nouveau, ancien) ;
  }

  // Calcul de la "forme" de la question
  private String question() {
    if (estFeuille()) return "Est-ce " + contenu + " ?" ;
    return contenu ;
  }

  /** Affichage d'une question et lecture d'une réponse */
  public String poserQuestion() {
    System.out.println(question()) ;
    return Clavier.readString() ;
  }

  /** Affichage d'une question et lecture d'une réponse */
  public String poserQuestion(String s) {
    System.out.println(s) ;
    return Clavier.readString() ;
  }

  /** Méthode d'ajout de noeuds prenant comme paramètres un tableau
  * de chaînes et une position initiale dans ce tableau.  La
  * première chaîne est placée sur le noeud courant (écrasement du
  * contenu).<BR> La valeur renvoyée est la position dans le
  * tableau à partir de laquelle les chaînes n'ont pas encore été
  * traitées. <BR> Le principe est le suivant : <UL><LI>si la
  * chaîne à la position courante se termine par '?', c'est une
  * question donc elle est suivie de deux sous-arbres. On commence
  * par les créer avec des contenus vides, puis on ajoute à celui
  * de gauche les noeuds à partir de la position courante ; on
  * récupère la nouvelle position à partir de laquelle poursuivre
  * le traitement, et on renouvelle alors l'opération pour le
  * sous-arbre de droite ; on retourne enfin la position des
  * données à traiter après construction des deux sous-arbres.</LI>
  * <LI>si la chaîne à la position courante ne se termine pas par
  * '?', c'est un nom d'animal donc une feuille. Il suffit alors
  * d'affecter le contenu et de retourner la position suivante dans
  * le tableau.</LI></UL>
  */
  public int ajouterNoeuds(String [] questions, int pos) {
    if (pos < questions.length)
    {
      String q = questions[pos] ;
      if (q.charAt(q.length()-1) == '?')
      // on a trouvé une question, donc on va
      // créer un noeud et des sous-arbres
      {
        contenu = q ; // on stocke la question
        // on initialise les sous-arbres
        non = new NoeudArbre() ;
        oui = new NoeudArbre() ;
        // on ajoute des noeuds à gauche en commençant
        // à l'indice suivant (pos+1), ce qui nous
        // donne ensuite l'indice des éléments restant
        // à traiter (npos)
        int npos = non.ajouterNoeuds(questions, pos+1) ;
        // on ajoute des noeuds à droite (même
        // principe) en commençant à npos
        return oui.ajouterNoeuds(questions, npos) ;
      }
      else { // c'est un animal donc une feuille
        contenu = q ;
        return pos + 1 ;
    }
  }
  return pos ;
}

// Méthode de classe : arbre renvoyé par defaut, contenant un
// noeud et deux feuilles
private static NoeudArbre initJeu() {
  return new NoeudArbre("Est-ce un mammifère ?", "un crocodile", "un chien") ;
}

// Méthode de classe : arbre obtenu a partir d'un tableau
// de chaînes de caractères
private static NoeudArbre construire(String [] arg) {
  // on crée d'abord un noeud racine vide
  NoeudArbre n = new NoeudArbre() ;
  // puis on utilise la méthode ajouterNoeuds en utilisant les
  // arguments de la ligne de commande
  n.ajouterNoeuds(arg, 0) ;
  return n;
}

/** La méthode <code>definir</code> permet de retrouver le chemin
* à suivre pour atteindre un animal dans l'arbre */
public String definir(String animal) {
  if (this.estFeuille()) {
    if (contenu.equals(animal)) return " => "+animal ;
    else return null ;
  } else {
    String s = oui.definir(animal) ;
    if (s != null) return contenu + " -> oui ; " + s;
    s = non.definir(animal) ;
    if (s != null) return contenu + " -> non ; " + s ;
    return null ;
  }
}

/** La méthode <code>main</code> de la classe NoeudArbre réalise le
* jeu des questions.<BR> Pour l'utiliser : <UL><LI> Utilisation avec
* un arbre par défaut : <code>java -classpath ./classes
* NoeudArbre</code></LI> <LI>Utilisation avec un arbre donné en
* notation préfixe : <BR> <code>java -classpath ./classes NoeudArbre
* "Est-ce un mammifère ?" "Est-ce qu'il a des pattes ?" "Est-ce qu'il
* vit dans l'eau ?" "un serpent" "un poisson" "Est-ce qu'il a huit
* pattes ?" "Est-ce qu'il a six pattes ?" "un crocodile" "une fourmi"
* "une araignée" "Est-ce qu'il aboie ?" "Est-ce qu'il rugit ?"
* "Est-ce qu'il vit dans l'eau ?" "un cheval" "Est-ce qu'il a des
* dents ?" "une baleine" "un dauphin" "un lion" "un
* chien"</code></LI></UL>
*/

public static void main(String [] arg) {
  // initialisation de l'abre
  NoeudArbre racine ;
  String [] tab = arg ;
  String animalADefinir = null ;
  if ((arg.length > 0) && (arg[0].equalsIgnoreCase("--definir"))) {
    animalADefinir = arg[1] ;
    tab = new String[arg.length-2] ;
    for (int i = 2; i<arg.length; i++)
    tab[i-2] = arg[i] ;
  }
  if (tab.length == 0)
  racine = initJeu() ;
  else racine = construire(tab) ;

  System.out.println("==============================") ;
  System.out.println("        Jeu des Questions") ;
  System.out.println("==============================") ;

  if (animalADefinir != null) {
    String resultat = racine.definir(animalADefinir) ;
    if (resultat != null)
    System.out.println(resultat) ;
    else
    System.out.println("\"" + animalADefinir + "\" n'est pas connu") ;
  }

  // jeu qui boucle jusqu'a ce qu'on tape "non"
  do {
    System.out.println(
    "Pensez à un animal, je vais essayer de le deviner...") ;
    racine.rechercherAnimal() ;
  } while
  ((racine.poserQuestion("Voulez-vous rejouer ?")).equalsIgnoreCase("oui")) ;

  // affichage de l'arbre final (sur la sortie d'erreur pour
  // faciliter la redirection)
  System.err.println(racine) ;
}
}
