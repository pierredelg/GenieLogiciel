/**
 * La classe ObjetMortel crée des instances qui portent
 * toutes un numéro, attribué dans l'ordre croissant au 
 * moment de l'instanciation (comme ObjetNumerote).
 * <P>Cette version comporte une méthode <code>finalize</code>
 * pour montrer le fonctionnement du mécanisme de <i>garbage collector</i>
 */

public class ObjetMortel 
{    
    private static final String [] MSG = {
	"Hélas ! Je meurs !", "Pfff (dernier soupir)", "Arrrrrrrrrgh !", "Adieu, monde cruel !",
	"Qualis artifex pereo !", "Je sens comme une difficulté d'être", "Goodbye world !",
	"Mehr licht !", "Acta est fabula", "Ma mémoire se vide..." } ;

    // le numéro de l'instance
    private long numero ;
    
    // le compteur que la classe utilise pour attribuer les numéros
    public static long compteur = 0 ;

    /** Quand une instance est créée, on incrémente le compteur
     * (variable de classe) et on l'affecte au numero (variable
     * d'instance).
     */
    
    public ObjetMortel() 
    {
	compteur++ ;
	numero = compteur ;
    }

    /** Représentation de l'objet */
    public String toString() 
    {
	return "Je suis l'objet numéro "+numero ;
    }

    /** Cette méthode est appelée juste avant que l'espace memoire
     * occupé par l'instance courante ne soit désalloué par le garbage
     * collector.
     */
    protected void finalize()
    {
	// on affiche un message au hasard
	System.out.println("#" + numero + ":\t" + MSG[(int)(Math.random()*MSG.length)]) ;
    }
}
