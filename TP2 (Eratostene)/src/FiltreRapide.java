/**
 * Un filtre de valeur p "élimine" tous les nombres multiples de p (cf. filtrer)
 */
public class FiltreRapide {

    // valeur filtrante
    private int valeur ;
    // filtre suivant
    private FiltreRapide suivant = null ;

    /** 
     * crée un filtre de valeur filtrante n
     */
    public FiltreRapide(int n) {valeur = n ;}
    
    /** 
     * indique si le filtre est le dernier de la liste
     */
    public boolean estDernier() { return suivant == null ; }
    /**
     * affiche la valeur filtrante du filtre et, s'il n'est pas le dernier,
     * la valeur des autres filtres
     */
    public String toString() 
    { 
	if (estDernier()) 
	    return ""+valeur ;
	else
	    return this.affichage().toString() ;
    }

    // méthode privée destinée à construire rapidement une chaîne de caractères
    // représentant la succession des filtres, au moyen d'un StringBuffer
    private StringBuffer affichage()
    {
	StringBuffer resultat ;
	if (estDernier())
	    resultat = new StringBuffer();
	else
	    resultat = suivant.affichage() ;
	return resultat.insert(0,valeur+"\t") ;
    }

    /** 
     * le filtrage d'un entier n consiste à "éliminer" n s'il est multiple
     * de p ; si ce n'est pas le cas, de deux choses l'une : soit le filtre
     * courant a un suivant, auquel cas on demande à ce filtre suivant de
     * poursuivre le filtrage de n, soit le filtre courant est le dernier,
     * auquel cas n (qui n'est multiple d'aucune valeur <= p) est premier :
     * il faut donc créer comme filtre suivant un filtre de valeur n
     */
    public void filtrer(int n) 
    {
	if (n % valeur != 0) 
	    if (estDernier()) 
		suivant = new FiltreRapide(n) ;
	    else suivant.filtrer(n) ;
    }
}
