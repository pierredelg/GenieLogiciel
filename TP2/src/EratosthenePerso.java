/**
 * Algorithme "objet" du crible d'Ératosthène.
 */
public class EratosthenePerso {
    /**
     * Principe : on crée un générateur de nombres de 2 à N,
     * puis on le fait travailler.
     */
    public static void main (String [] argv) {
        GenerateurPerso g = new GenerateurPerso(100) ;
        g.travailler() ;
        System.out.println(g.toString()) ;
    }
}