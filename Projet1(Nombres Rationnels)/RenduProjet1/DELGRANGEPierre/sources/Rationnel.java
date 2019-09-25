/**
 * Classe représentant un nombre rationnel sous la forme (n + a/b) avec :
 * <ul>
 *      <li> n est un entier relatif (appelé partie entière du nombre rationnel)</li>
 *      <li> a un entier positif</li>
 *      <li> b un entier strictement positif</li>
 *      <li> a &lt; b (de sorte que la fraction ab, appelée partie décimale du rationnel, soit comprise entre 0 inclus et 1 exclus)</li>
 *      <li> a et b sont premiers entre eux (c'est-à-dire qu'il n'existe pas d'entier d &gt; 1 qui soit un diviseur à la fois de a et de b).</li>
 * </ul>
 *
 * @author DELGRANGE Pierre
 */
public class Rationnel implements Comparable<Rationnel> {

    /**
    * Partie entière du rationnel.
    */
    private int n;
    
    /**
    * Numérateur de la partie décimale du rationnel.
    */
    private int a;

    /**
    * Dénominateur de la partie décimale du rationnel.
    */
    private int b;

    /**
     * Constructeur complet d'un nombre rationnel
     * @param n partie entière du nombre rationnel
     * @param a numérateur de la partie décimale du nombre rationnel
     * @param b dénominateur de la partie décimale du nombre rationnel
     */
    public Rationnel(int n,int a,int b){

        this.n = n;
        this.a = a;
        this.b = b;
        simplifierRationnel();
    }
    
    /**
     * Constructeur d'un nombre rationnel sans partie entière
     * @param a numérateur de la partie décimale du nombre rationnel
     * @param b dénominateur de la partie décimale du nombre rationnel
     */
    public Rationnel(int a, int b){

        this.a = a;
        this.b = b;
        simplifierRationnel();
    }

    /**
     * Constructeur d'un nombre rationnel sans partie décimale
     * @param n partie entière du nombre rationnel
     */
    public Rationnel(int n){

        this.n = n;
        this.b = 1;
    }

    /**
     * Représente le nombre rationnel sous la forme ( n + a / b )
     * @return le nombre rationnel n + a / b
     */
    @Override
    public String toString() {

        return n + " + " + a + " / " + b;
    }

    /**
    * Donne la partie entière du rationnel courant.
    * @return La partie entière du rationnel.
    */
    public int getPartieEntiere() {

        return n;
    }

    /**
    * Donne la valeur de la partie décimale du rationnel courant.
    * @return La valeur décimale du rationnel.
    */
    public double getPartieDecimale() {

        return (double) a / (double) b;
    }

      /**
     * Vérifie si le nombre est nul ( égal à 0 ).
     * @return true - Si le nombre est égal à 0.
     */
    public boolean estNul(){

        return n == 0 && a == 0;
    }

    /**
     * Calcule et renvoie le nombre rationnel inverse de l'instance courante.
     * @return le nombre rationnel inverse de l'instance courante
     */
    public Rationnel inverse(){

        //Si le rationnel est nul alors il n'a pas d'inverse
        if(estNul()){
            return null;
        }
        //Si la partie entière est nulle alors on inverse la partie décimale (a/b en b/a)
        if(n == 0 && a != 0 && b != 0){
            return new Rationnel(b,a);
        }
        //Si la partie décimale est nulle alors on inverse la partie entière (1/n)
        if(n != 0 && a == 0 && b == 0){
            return new Rationnel(1/n);
        }

        //On transforme le rationnel en une fraction
        int numerateur = n * b + a;
        int denominateur = b;

        //On crée un nouveau rationnel en inversant le dénominateur et le numérateur
        Rationnel inverse = new Rationnel(0,denominateur,numerateur);

        //On le simplifie
        inverse.simplifierRationnel();

        return inverse;
    }

    /**
     * Additionne l'instance courante avec un autre nombre rationnel.
     * @param rationnel - Le rationnel à ajouter à l'instance courante this.
     * @return Un nouveau rationnel résultat de l'addition.
     */
    public Rationnel ajouter(Rationnel rationnel){

        int nAjout, aAjout, bAjout;

        //Si un rationnel est nul on retourne l'autre rationnel
        if(estNul()){
            return rationnel;
        }
        if(rationnel.estNul()){
            return this;
        }

        //On ajoute les parties entières
        nAjout = this.n + rationnel.n;

        //On ajoute les parties décimales
        Rationnel resultAjoutPartieDecimale = ajoutPartieDecimale(this,rationnel);
        nAjout = nAjout + resultAjoutPartieDecimale.n;
        aAjout = resultAjoutPartieDecimale.a;
        bAjout = resultAjoutPartieDecimale.b;

        //On simplifie le Rationnel
        Rationnel rationnelSimplifie = new Rationnel(nAjout ,aAjout,bAjout);
        rationnelSimplifie.simplifierRationnel();

        return rationnelSimplifie;
    }

    /**
     * Multiplie l'instance courante avec un autre nombre rationnel.
     * @param rationnel - Le rationnel à multiplier avec l'instance courante this.
     * @return Un nouveau rationnel résultat de la multiplication.
     */
    public Rationnel multiplier(Rationnel rationnel){

        int nMultiplier, aMultiplier, bMultiplier;

        //Si l'un des Rationnels est nul le résultat est nul
        if(this.estNul() || rationnel.estNul()){
            return new Rationnel(0);
        }

        //On multiplie
        nMultiplier = 0;
        aMultiplier = (n * b + a) * (rationnel.n * rationnel.b + rationnel.a);
        bMultiplier =  b * rationnel.b;

        //On simplifie le Rationnel
        Rationnel rationnelSimplifie = new Rationnel(nMultiplier,aMultiplier,bMultiplier);
        rationnelSimplifie.simplifierRationnel();

        return rationnelSimplifie;
    }

    /**
     * Vérifie l'égalité de résultat d'un objet avec l'instance courante this.
     * @param objet - l'objet à tester
     * @return true - Si les deux nombres sont égaux.
     */
    @Override
    public boolean equals(Object objet) {

        //Si c'est le même objet, on retourne vrai
        if (this == objet) {
            return true;
        }
        //Si l'objet n'est pas de la meme classe
        if (objet == null || getClass() != objet.getClass()){
            return false;
        }

        Rationnel rationnel = (Rationnel) objet;

        //On simplifie les rationnels
        this.simplifierRationnel();
        rationnel.simplifierRationnel();

        //Si les attributs sont égaux on retourne vrai
        if( n == rationnel.n && a == rationnel.a && b == rationnel.b){
            return true;
        }

        Double valeurRationnel1 = (double)this.n + (this.a / this.b);
        Double valeurRationnel2 = (double)rationnel.n + (rationnel.a / rationnel.b);

        //Si les deux valeurs des rationnels sont égales ont retourne vrai
        if(valeurRationnel1.equals(valeurRationnel2)){
            return true;
        }
        return false;
    }

    /**
     * On ajoute la partie décimale composant le premier Rationnel (rationnel 1) avec la partie décimale du second Rationnel (rationnel 2).
     * @param rationnel1 Le premier Rationnel.
     * @param rationnel2 Le second Rationnel.
     * @return Un nouvel objet Rationnel avec le résultat de la multiplication.
     */
    private Rationnel ajoutPartieDecimale(Rationnel rationnel1, Rationnel rationnel2){

        int aResult, bResult;

        //Si les dénominateurs sont égaux ont ajoute les numérateurs
        if(rationnel1.b == rationnel2.b){
            aResult = rationnel1.a + rationnel2.a;
            bResult = rationnel1.b;
        }else{
            //Si les dénominateurs sont différents ont multiplie les numérateurs avec les dénominateurs
            aResult = rationnel1.a * rationnel2.b + rationnel2.a * rationnel1.b;
            bResult = rationnel1.b * rationnel2.b;
        }
        return new Rationnel(0,aResult,bResult);
    }

    /**
     * Permet de simplifier le rationnel de façon à ce que
     * le numérateur  et le dénominateur de la partie décimale soit positif.
     */
    private void simplifieNegatif(){

        //numérateur ET dénominateur négatif
        if(a < 0 && b < 0){
            a = -a;
            b = -b;
        }

        //numérateur OU dénominateur négatif
        if(a < 0 || b < 0){
            if( b < 0){
                a = -a;
                b = -b;
            }
            a = (n*b) + a;
            n = 0;

            //exemple : -10/6
            while((a < 0 && -a > b)){
                n += -1;
                a = a + b;
            }

            //exemple : -5/15
            if(a < 0 && -a < b){
                n += -1;
                a = b + a;
            }
        }

    }

    /**
     * On simplifie le Rationnel de façon à ce qu'il respecte ces contraintes:
     * <ul>
     *    <li> n est un entier relatif (appelé partie entière du nombre rationnel)</li>
     *  *      <li> a un entier positif </li>
     *  *      <li> b un entier strictement positif </li>
     *  *      <li> a &lt; b </li>
     *  *      <li> 0 &lt; (a/b) &lt; 1 </li>
     *  *      <li> a et b sont premiers entre eux (c'est-à-dire qu'il n'existe pas d'entier d &gt; 1 qui soit un diviseur à la fois de a et de b).</li>
     * </ul>
     */
    private void simplifierRationnel(){

        simplifieNegatif();

        //Si le numérateur est à 0 on met le dénominateur à 1
        //Exemple : 2 + 0/4 -> 2 + 0/1
        if(a == 0){
            b = 1;
        }

        //Si le numérateur et le dénominateur sont égaux
        //exemple : 0 + 2/2 -> 1 + 0/1
        // OU si le numérateur est plus grand que le dénominateur et que le numérateur est un multiple du dénominateur
        //exemple : 0 + 15/5 -> 3 + 0/1
        if(b != 0 && a == b || (a > b && b != 0 && a % b == 0)){
            n += a/b;
            a = 0;
            b = 1;
        }

        //Si le numérateur est plus petit que le dénominateur et que le dénominateur est multiple du numérateur
        //exemple : 0 + 5/15 -> 0 + 1/3
        if(a < b && a != 0 && b % a == 0){
            a = 1;
            b = b/a;

        }

        //Si le numérateur est plus grand que le dénominateur et que le dénominateur n'est pas multiple du numérateur
        //exemple : 1 + 17/5 -> 4 + 2/5
        if(a > b && b != 0 && a % b != 0){
            n += a/b;
            a = a%b;
        }
    }

    /**
     * Comparaison de l'instance courante avec un autre Rationnel.
     * @param rationnel Le rationnel à comparer.
     * @return l'entier r :
     * <ul>
     *     <li> r &lt; 0 - Si l'instance courante est plus petite que le rationnel en parametre.</li>
     *     <li> r = 0 - Si l'instance courante est égale au rationnel en parametre.</li>
     *     <li> r &gt; 0 - Si l'instance courante est plus grande que le rationnel en parametre.</li>
     * </ul>
     */
    @Override
    public int compareTo(Rationnel rationnel) {

        //Si les deux parties entières sont égales
        if(this.n != rationnel.n){
            return this.n - rationnel.n;
        }
        //Si les dénominateurs des parties décimales sont égaux
        if(this.a != rationnel.a && this.b == rationnel.b){
            return this.a - rationnel.a;
        }
        //Si les numérateurs des parties décimales sont égaux
        if(this.a == rationnel.a && this.b != rationnel.b){
            return rationnel.b - this.b;
        }
        //Les numérateurs et dénominateurs sont différents
        if(this.a != rationnel.a){
            Double premier = (double) this.a /(double) this.b;
            Double deuxieme = (double) rationnel.a / (double) rationnel.b;
            return premier.compareTo(deuxieme);
        }
        return 0;
    }

    public static void main(String[] args){}
}
