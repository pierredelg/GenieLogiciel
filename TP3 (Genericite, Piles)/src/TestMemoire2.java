public class TestMemoire2
{
    /** Un test dans lequel on crée 10000 instances non référencées de ObjetMortel.
     */
    public static void main (String [] args) 
    {
	for (int i=0; i<10000; i++) 
	    {
		System.out.println(new ObjetMortel()) ;
	    }
    }

    //Aucun objet n'est désalouer
    //la mémoire utilisée n'est pas saturée donc inutile de désalouer
}