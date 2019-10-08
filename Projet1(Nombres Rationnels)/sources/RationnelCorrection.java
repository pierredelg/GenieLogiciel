/** La classe Rationnel représente un nombre rationnel (au sens
 * mathématique) sous la forme <I>n + a/b</I> avec : 
 * <UL><LI><i>n</i> entier relatif quelconque</LI>
 * <LI><i>a</i> entier positif</LI>
 * <LI><i>b</i> entier strictement positif </LI>
 * <LI><i>a/b < 1</i></LI>
 * <LI><i>a</i> et <i>b</i> premiers entre eux (la fraction
 * est simplifiée) </UL><P> Le nombre <I>n</I> est appelé <I>partie
 * entière</I> du rationnel, et la fraction <I>a/b</I> est sa
 * <I>partie décimale</I>.
 */
public class RationnelCorrection {
    // attributs (prives)
    private int n, a, b ;

    /** Constructeur créant un rationnel en calculant <I>n + a/b</I> */
    public Rationnel(int n, int a, int b) {
	// on recopie les parametres n, a et b dans les
	// attributs de l'instance (this.a, this.b et this.n)
	this.n = n ; this.a = a ; this.b = b ;
	// puis on met le rationnel sous la forme specifiee
	simplifier() ;
    }
    /** Constructeur créant un rationnel en calculant <I>a/b</I> */
    public Rationnel(int a, int b) {
	// appel du code du constructeur precedent
	this(0, a, b) ;
    }
    /** Constructeur créant un rationnel de valeur <I>n</I> */
    public Rationnel(int n) {
	// idem
	this(n, 0, 1) ;
    }
    /** Affiche le rationnel sous sa forme <I>n + a/b</I> */
    public String toString() {
	//	return "" + n + " + "+ a + "/" + b ;
	// version "améliorée" pour les zéros 
	// - ici en utilisant l'opérateur (?:) : (booléen?valeurTrue:valeurFalse)
	return (a==0?""+n:(n==0?a+"/"+b:n+" + "+a+"/"+b)) ;
    }
    /** Teste si le rationnel vaut zéro (il faut pour cela avoir 
     *  <I>bn + a = 0</I>) */
    public boolean estNul() {
	return (b*n + a) == 0 ;
    }
    /** Calcule l'inverse du rationnel courant s'il n'est pas nul
     * @return l'inverse de this s'il n'est pas nul (égal à zéro),
     * <code>null</code> sinon retourne la référence <code>null</code>
     * sinon */
    public Rationnel inverse() {
	if (estNul()) return null ;
	return new Rationnel(b, b*n+a) ;
    }

    /** Calcule et renvoie la somme du rationnel courant et du paramètre  
     * @param r un autre rationnel
     */
   public Rationnel ajouter(Rationnel r) {
	return new Rationnel (this.n + r.n,
			      this.a*r.b + this.b*r.a,
			      this.b * r.b) ;
    }
    /** Calcule et renvoie le produit du rationnel courant et du paramètre
     * @param r un autre rationnel
     */
    public Rationnel multiplier(Rationnel r) {
	int num1, num2 ;
	num1 = this.n*this.b + this.a ;
	num2 = r.n * r.b + r.a ;
	return new Rationnel(num1*num2, this.b*r.b) ;
    }
    /** Compare le rationnel courant et celui donné en paramètre. Renvoie un nombre
     * negatif si le paramètre est plus grand que l'instance courante, positif s'il
     * est plus petit, et zéro en cas d'égalité
     * @param r un autre rationnel */
    public int compare(Rationnel r) {
	int num1, num2 ;
	num1 = this.n*this.b + this.a ; // le numérateur de this
	num2 = r.n * r.b + r.a ;        // le numérateur de r
	// on retourne le signe de la différence : pour être sûr de conserver
	// le bon signe on multiplie par le carré des dénominateurs
	return num1*r.b*r.b*this.b - num2*r.b*this.b*this.b ;
    }

    // calcul du PGCD de deux entiers (positifs) par l'algorithme d'Euclide :
    // le PGCD de a et b est a si b est nul, et sinon c'est le PGCD
    // de b et du reste de la division de a par b
    // N.B. : cette méthode est récursive, mais on peut faire le même calcul
    // par une méthode itérative
    private static int pgcd (int a, int b) {
	if (b == 0) return a ;
	if (b == 1) return 1 ;
	return pgcd(b, a % b) ;
    }

    // méthode de simplification pour faire passer le rationnel sous sa
    // forme canonique (conforme aux spécifications)
    private void simplifier() {	
	// première étape : se "débarasser" du signe - éventuel de b
	if (b < 0) {
	    a = -a ; 
	    b = -b ;
	}
	// deuxième étape : obtenir une fraction a/b dans [0,1[
	if (a > 0) {
	    n = n + a/b ;
	    a = a % b ;
	} else {
	    n = n - (-a)/b - 1 ;
	    a = b - (-a) % b ;
	}
	// troisième étape : réduire la fraction 
	int p = pgcd(a, b) ;
	a = a / p ; 
	b = b / p ;
    }

    /** Renvoie la partie entière du rationnel courant */
    public int partieEntiere() { return n ; }
    /** Renvoie la partie décimale (nombre rationnel) du rationnel courant */
    public Rationnel partieDecimale() { return new Rationnel(a, b) ; }
}

