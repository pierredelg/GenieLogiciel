import java.util.Scanner;

/** Programme permettant de manipuler et d'expérimenter le problème
 *  des tours de Hanoï */
public class Hanoi 
{
    // Les trois piles représentant les tours de Hanoï
    private static PileHanoi a, b, c ;

    private static int NB_DISQUES;

    // Initialisation des tours pour n disques, placés au début en A
    private static void init(int n) 
    {
	a = new PileHanoi("A", new AffichageJoliePerso('='));
	b = new PileHanoi("B",new AffichageJoliePerso('=')) ;
	c = new PileHanoi("C",new AffichageJoliePerso('=')) ;
	for (int i=n; i>0; i--)
	    a.empile(new DisqueHanoi(i)) ;
    }

    // Affichage des trois tours
    private static void affiche() 
    {
		System.out.println("\nEtat des tours : ");
	System.out.print(a);
	System.out.print(b);
	System.out.print(c);
    }

    // Pour le mode interactif, le choix de la pile est donné par le joueur
    // en toutes lettres ("A", "B", "C"). -> retourne la pile correspondante
    private static PileHanoi analyse(String r) 
    {
	if (r.equalsIgnoreCase("A"))
	    return a ;
	if (r.equalsIgnoreCase("B"))
	    return b ;
	return c ;
    }
    public static void nombreDeDepacementTheorique(int nombreDeDisque){
		System.out.println("Le nombre de déplacements théorique pour "+ nombreDeDisque +" disques est de " + (Math.pow(2, nombreDeDisque) - 1) + " déplacements.");
	}

	public static void resoudreAuto(PileHanoi a, PileHanoi b, PileHanoi c){
    	a.deplacerDesDisques(NB_DISQUES,b,c);
	}

	// Méthode principale du programme.
    public static void main (String [] arg) {

		// le nombre de disques (on peut aussi le demander au joueur)
		System.out.println("Avec combien de disque souhaitez-vous jouer ?");
		NB_DISQUES = Clavier.readInt();

		//On affiche le nombre de déplacements théoriques
		nombreDeDepacementTheorique(NB_DISQUES);

		// initialisation des piles
		init(NB_DISQUES);

		//Si l'argument auto est entré on lance la résolution automatique
		if (arg.length != 0 && arg[0].equals("--auto")) {
			resoudreAuto(a, b, c);
			System.out.println("\nLe nombre de déplacements calculé de façon expérimental est de "+ PileHanoi.getNombreDeDeplacementExperimental());
		} else {

			boolean fini = false ;
			String rep ;
			PileHanoi depart, arrivee ;

			do {
				// on commence par afficher les tours
				affiche() ;
				// on demande au joueur la tour de départ (A, B, C)
				System.out.print("Déplacer de : ") ;
				rep = Clavier.readString() ;
				if (rep.equalsIgnoreCase("STOP"))
				fini = true ;
				// on en déduit l'objet correspondant
				depart = analyse(rep) ;
				if (!fini)
				{
					// même chose pour la tour d'arrivée
					System.out.print("Vers : ") ;
					rep = Clavier.readString() ;
					if (rep.equalsIgnoreCase("STOP"))
					fini = true ;
					arrivee = analyse(rep) ;
					// on effectue le déplacement si c'est possible
					if (arrivee.peutEmpiler(depart.sommet()))
					depart.deplacerUnElementVers(arrivee) ;
					else
					System.out.println("Impossible !") ;

					//On affiche le nombre de déplacements effectués
					System.out.println("Nombre de déplacements : " + PileHanoi.getNombreDeDeplacementExperimental());
				}
				// et on continue tant que le joueur n'a pas dit STOP
			} while (!fini) ;
			System.out.println("OK, c'est fini !") ;
		}
	}
}
