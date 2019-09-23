/**
 * Classe représentant un nombre rationnel sous la forme (n + a/b) avec :
 * <ul>
 *      <li> n est un entier relatif (appelé partie entière du nombre rationnel)</li>
 *      <li> a un entier positif</li>
 *      <li> b un entier strictement positif</li>
 *      <li> a < b (de sorte que la fraction ab, appelée partie décimale du rationnel, soit comprise entre 0 inclus et 1 exclus)</li>
 *      <li> a et b sont premiers entre eux (c'est-à-dire qu'il n'existe pas d'entier d>1 qui soit un diviseur à la fois de a et de b).</li>
 * </ul>
 *
 * @author DELGRANGE Pierre
 */
public class Rationnel implements Comparable<Rationnel> {

    private int n;
    private int a;
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
     *  Vérifie si le nombre est nul ( égal à 0 ).
     * @return true - Si le nombre est égal à 0.
     */
    public boolean estNul(){
        return n == 0 && a == 0;
    }

    public int getPartieEntiere() {
        return n;
    }

    public double getPartieDecimale() {
        return (double) a / (double) b;
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
     *  Permet de simplifier le rationnel de façon à ce que
     *  le numérateur  et le dénominateur de la partie décimale soit positif.
     *
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

            //exemple : -5/15 ou 5/-15
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
     *  *      <li> a < b </li>
     *  *      <li> 0 < (a/b) < 1 </li>
     *  *      <li> a et b sont premiers entre eux (c'est-à-dire qu'il n'existe pas d'entier d>1 qui soit un diviseur à la fois de a et de b).</li>
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
     *     <li> r < 0 - Si l'instance courante est plus petite que le rationnel en parametre.</li>
     *     <li> r = 0 - Si l'instance courante est égale au rationnel en parametre en parametre.</li>
     *     <li> r > 0 - Si l'instance courante est plus grande que le rationnel en parametre.</li>
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

    public static void main(String[] args) {
        Rationnel r1 = new Rationnel(0,1,2);
        System.out.println("Rationnel 1  = " + r1);
        System.out.println("Rationnel 1 est nul : " + r1.estNul());
        System.out.println("Inverse r1: "+ r1.inverse());

        System.out.println();

        Rationnel r2 = new Rationnel(-6,1,2);
        System.out.println("Rationnel 2  = " + r2);
        System.out.println("Rationnel 2 est nul : " + r2.estNul());
        System.out.println("Inverse r2: "+ r2.inverse());

        System.out.println();

        System.out.println("Addition r1 + r2 : "+ r1.ajouter(r2));
        System.out.println("Multiplication r1 * r2 : "+ r1.multiplier(r2));

        System.out.println();
        System.out.println();


        Rationnel r3 = new Rationnel(1,2,3);
        System.out.println("Rationnel 3  = " + r3);
        System.out.println("Rationnel 3 est nul : " + r3.estNul());
        System.out.println("Inverse r3: "+ r3.inverse());

        System.out.println();

        Rationnel r4 = new Rationnel(2,2,-5);
        System.out.println("Rationnel 4  = " + r4);
        System.out.println("Rationnel 4 est nul : " + r4.estNul());
        System.out.println("Inverse r4: "+ r4.inverse());

        System.out.println();

        System.out.println("Addition r3 + r4 : "+ r3.ajouter(r4));
        System.out.println("Multiplication r3 * r4 : "+ r3.multiplier(r4));
        System.out.println();
        System.out.println();

        Rationnel r5 = new Rationnel(-4,1,2);
        System.out.println("Rationnel 5  = " + r5);
        System.out.println("Rationnel 5 est nul : " + r5.estNul());
        System.out.println("Inverse r5: "+ r5.inverse());
        System.out.println("n = " + r5.getPartieEntiere());
        System.out.println("decimale = " + r5.getPartieDecimale());

        System.out.println();

        Rationnel r6 = new Rationnel(1,1,-2);
        System.out.println("Rationnel 6  = " + r6);
        System.out.println("Rationnel 6 est nul : " + r6.estNul());
        System.out.println("Inverse r6: "+ r6.inverse());

        System.out.println();

        System.out.println("Addition r5 + r6 : "+ r5.ajouter(r6));
        System.out.println("Multiplication r5 * r6 : "+ r5.multiplier(r6));
        System.out.println();
        System.out.println();

        System.out.println(new Rationnel(3, 4, 5)) ;
        System.out.println();
        System.out.println(new Rationnel(5, 4, 3)) ;
        System.out.println();
        System.out.println(new Rationnel(-2, 3)) ;
        System.out.println();
        System.out.println(new Rationnel(3, -2)) ;
        System.out.println();
        System.out.println(new Rationnel(3, 6, -2)) ;
        System.out.println();
        System.out.println(new Rationnel(3)) ;
        System.out.println();
        System.out.println(new Rationnel(1,3)) ;
        System.out.println();
        System.out.println(new Rationnel(5,3));
        System.out.println();
        System.out.println(new Rationnel(12,5));
        System.out.println();
        System.out.println(new Rationnel(28,6));
        System.out.println();
        System.out.println((new Rationnel(0,1,2).equals(new Rationnel(0,40,80))));
        System.out.println();
        System.out.println((new Rationnel(0,-1,2).equals(new Rationnel(0,40,-80))));
        System.out.println();
        System.out.println((new Rationnel(5).equals(new Rationnel(0,30,6))));
        System.out.println();
        System.out.println((new Rationnel(0,-1,2).equals(new Rationnel(0,40,-80))));



    }

}
