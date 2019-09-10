/** 
 * Algorithme "objet" du crible d'Ératosthène.
 */

public class Eratosthene {

    /**
     * Principe : on crée un générateur de nombres de 2 à N, puis on
     * le fait travailler. On peut donner comme argument de la ligne
     * de commande la valeur de N (par défaut : 10000).
     */
    public static void main (String [] args) {
	int i = 10000 ;
	if (args.length > 0) 
	    i = (new Integer(args[0])).intValue() ;
	Generateur g = new Generateur(i) ;
	g.travailler() ;
	System.out.println(g.toString()) ;
    }
    
}
