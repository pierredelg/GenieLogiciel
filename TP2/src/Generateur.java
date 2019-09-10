/**
 * Générateur de nombres pour le crible d'Ératosthène dans
 * sa version objet.
 */

public class Generateur {

    // entier maximal jusqu'auquel on effectue la recherche de nombres premiers
    private int limite ;

    // filtre initial (de valeur 2) auquel on envoie successivement tous les
    // nombres à tester
    private Filtre initial ;
    
    /** 
     * crée un générateur de limite 100 
     */
    public Generateur () { limite = 100 ; initial = new Filtre(2) ;}
    /**
     * crée un générateur de limite n
     */
    public Generateur (int n) {limite = n ; initial = new Filtre(2) ;}

    /**
     * affichage de la liste des nombres premiers trouvés ; pour cela
     * on demande au filtre initial de s'afficher (à charge pour lui
     * de demander aux autres filtres d'afficher la suite)
     */
    public String toString() {
	return "Liste des nombres premiers compris entre 2 et "+limite+" : \n"+
	    initial + "\n" ;
    }

    /**
     * génère les nombres à tester de 2 à limite, et les envoie au filtre initial
     */
    public void travailler() {
	for (int i = 2; i<=limite; i++) 
	    initial.filtrer(i) ; 
    }
}
