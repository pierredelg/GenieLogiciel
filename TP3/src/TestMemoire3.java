public class TestMemoire3
{
    /** Un test dans lequel on crée 200000 instances non référencées de ObjetMortel.
     */
    public static void main (String [] args) 
    {
	for (long i=0; i<200000; i++) 
	    {
		System.out.println(new ObjetMortel()) ;
	    }
    }

    //oui elles sont désalouer mais beaucoup plus tard lors de la création de l'objet 186 659 le garbage collector desaloue l'objet 9480.
}
