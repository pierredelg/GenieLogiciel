import java.util.concurrent.CompletableFuture;

/**
 * Classe représentant un nombre rationnel sous la forme (n + a/b)
 * avec
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
        int [] fraction = simplifierFraction(a,b);
        this.n = n + fraction[0];
        this.a = fraction[1];
        this.b = fraction[2];
    }
    /**
     * Constructeur d'un nombre rationnel sans partie entière
     * @param a numérateur de la partie décimale du nombre rationnel
     * @param b dénominateur de la partie décimale du nombre rationnel
     */
    public Rationnel(int a, int b){
        int [] fraction = simplifierFraction(a,b);
        this.n = fraction[0];
        this.a = fraction[1];
        this.b = fraction[2];
    }
    /**
     * Constructeur d'un nombre rationnel sans partie décimale
     * @param n partie entière du nombre rationnel
     */
    public Rationnel(int n){
        this.n = n;
    }

    /**
     * Représente le nombre rationnel sous la forme n + a / b
     * @return le nombre rationnel n + a / b
     */
    @Override
    public String toString() {
        return n + " + " + a + " / " + b;
    }

    /**
     *  Vérifie si le nombre est nul ( égal à 0).
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

        if(estNul()){
            return new Rationnel(0);
        }

        if(n == 0 && a != 0 && b != 0){
            return new Rationnel(b,a);
        }

        if(n != 0 && a == 0 && b == 0){
            return new Rationnel(1/n);
        }
        int [] fraction = switchRationnelFraction(this);

        int[] fraction1 = simplifierFraction(fraction[2], (fraction[0] * fraction[2] + fraction[1]));

        return new Rationnel(fraction1[0],fraction1[1],fraction1[2]);
    }

    /**
     * Additionne l'instance courante avec un autre nombre rationnel.
     * @param r - Le rationnel à ajouter à l'instance courante this.
     * @return Un nouveau rationnel résultat de l'addition.
     */
    public Rationnel ajouter(Rationnel r){

        if(this.estNul()){
            return r;
        }
        if(r.estNul()){
            return this;
        }

        int nAjout = 0;
        int aAjout = 0;
        int bAjout = 0;

        nAjout = this.n + r.n;

        int [] resultFraction = ajoutFraction(this.a,this.b,r.a,r.b);

        aAjout = resultFraction[1];

        bAjout = resultFraction[2];

        int[] fraction = simplifierFraction(aAjout, bAjout);

        return new Rationnel(nAjout + fraction[0],fraction[1],fraction[2]);
    }

    /**
     * Multiplie l'instance courante avec un autre nombre rationnel.
     * @param r - Le rationnel à multiplier avec l'instance courante this.
     * @return Un nouveau rationnel résultat de la multiplication.
     */
    public Rationnel multiplier(Rationnel r){

        if(this.estNul() || r.estNul()){
            return new Rationnel(0);
        }

        int nMultiplier = 0;
        int aMultiplier = 0;
        int bMultiplier = 0;

        nMultiplier = n * r.n;

        int [] resultMultiplierFraction = MultiplierFraction(a,b,r.a,r.b);

        aMultiplier = resultMultiplierFraction[1];
        bMultiplier =  resultMultiplierFraction[2];

        int[] fraction = simplifierFraction(aMultiplier, bMultiplier);

        return new Rationnel(nMultiplier + fraction[0],fraction[1],fraction[2]);
    }

    /**
     * Vérifie l'égalité de résultat d'un objet avec l'instance courante this.
     * Ne vérifie pas que les nombres sont exactement de la même forme.
     * @param objet - l'objet à tester
     * @return true - Si les deux nombres sont égaux.
     */
    @Override
    public boolean equals(Object objet) {
        if (this == objet) {
            return true;
        }

        if (objet == null || getClass() != objet.getClass()){
            return false;
        }

        Rationnel rationnel = (Rationnel) objet;

        return n == rationnel.n && a == rationnel.a && b == rationnel.b;
    }

    private int[] switchRationnelFraction(Rationnel rationnel){

        int [] rationelSimplifie = simplifierFraction(rationnel.a,rationnel.b);

        return new int[] {rationnel.n + rationelSimplifie[0],rationelSimplifie[1],rationelSimplifie[2]};
    }
    private Rationnel switchFractionRationnel(int a,int b){

        int[] fraction = simplifierFraction(a, b);

        return new Rationnel(fraction[0],fraction[1],fraction[2]);
    }

    private int[] ajoutFraction(int a1 , int b1, int a2, int b2){
        int aResult = 0;
        int bResult = 0;

        if(b1 == b2){
            aResult = a1 + a2;
            bResult = b1;
        }else{
            aResult = a1 * b2;
            bResult = b1 * a2;
        }
        return new int [] {0,aResult,bResult};
    }


    private int[] MultiplierFraction(int a1,int b1,int a2, int b2){
        return new int [] {0,a1 * a2,b1 * b2};
    }

    private int[] simplifierFraction(int a,int b){

        if(a == b){
            return new int[] {1,0,1};
        }

        if(a < b && a > 0 && b % a == 0){
            return new int[] {0,1,(b/a)};
        }

        if(a > b && b > 0 && a % b == 0){
            return new int[] {(a/b),0,1};
        }

        return new int[] {0,a,b};

    }

    public static void main(String[] args) {
        Rationnel r1 = new Rationnel(0,1,2);
        System.out.println("Rationnel 1  = " + r1);
        Rationnel r2 = new Rationnel(0,1,2);
        System.out.println("Rationnel 2  = " + r2);
        System.out.println("Inverse r1: "+ r1.inverse());
        System.out.println("Inverse r2: "+ r2.inverse());
        System.out.println("Addition r1 + r2 : "+ r1.ajouter(r2));
        System.out.println("Multiplication r1 * r2 : "+ r1.multiplier(r2));
        System.out.println();

        Rationnel r3 = new Rationnel(1,2,3);
        System.out.println("Rationnel 3  = " + r3);
        Rationnel r4 = new Rationnel(2,2,5);
        System.out.println("Rationnel 4  = " + r4);
        System.out.println("Inverse r3: "+ r3.inverse());
        System.out.println("Inverse r4: "+ r4.inverse());
        System.out.println("Addition r3 + r4 : "+ r3.ajouter(r4));
        System.out.println("Multiplication r3 * r4 : "+ r3.multiplier(r4));
        System.out.println();

        Rationnel r5 = new Rationnel(1,1,2);
        System.out.println("Rationnel 5  = " + r3);
        Rationnel r6 = new Rationnel(1,1,2);
        System.out.println("Rationnel 6  = " + r4);
        System.out.println("Inverse r3: "+ r3.inverse());
        System.out.println("Inverse r4: "+ r4.inverse());
        System.out.println("Addition r3 + r4 : "+ r3.ajouter(r4));
        System.out.println("Multiplication r3 * r4 : "+ r3.multiplier(r4));
        System.out.println();
    }

    @Override
    public int compareTo(Rationnel o) {

        if(this.n != o.n){
            return this.n - o.n;
        }
        if(this.a != o.a && this.b == o.b){
            return this.a - o.a;
        }
        if(this.a == o.a && this.b != o.b){
            return o.b - this.b;
        }
        if(this.a != o.a){
            Double premier = (double) this.a /(double) this.b;
            Double deuxieme = (double) o.a / (double) o.b;
            return premier.compareTo(deuxieme);
        }
        return 0;
    }
}
