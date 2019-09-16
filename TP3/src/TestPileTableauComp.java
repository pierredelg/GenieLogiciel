public class TestPileTableauComp {
        public static void main(String [] arg) {
	PileTableauComp p1 = new PileTableauComp("La pile d'entiers") ;
	System.out.println("Entrez 5 entiers :"); 
	for (int i=1; i<=5; i++)
	    p1.empile(new Integer(Clavier.readInt())) ;
	System.out.println(p1) ;
	System.out.println("Je trie cette pile...") ;
	p1.trier() ; 
	System.out.println(p1) ;
	PileTableauComp p2 = new PileTableauComp("La pile de chaînes") ;
	System.out.println("Entrez 4 chaines :"); 
	for (int i=1; i<=4; i++)
	    p2.empile(Clavier.readString()) ;
	System.out.println(p2) ;
	System.out.println("Je trie cette pile...") ;
	p2.trier() ; 
	System.out.println(p2) ;
	System.out.println("Entrez 9 entiers correspondant à des rationnels :"); 
	PileTableauComp p3 = new PileTableauComp("La pile de rationnels") ;
	for (int i=1; i<=3; i++)
	    p3.empile(new Rationnel(Clavier.readInt(), Clavier.readInt(), Clavier.readInt())) ;
	System.out.println(p3) ;
	System.out.println("Je trie cette pile...") ;
	p3.trier() ; 
	System.out.println(p3) ;
	PileTableauComp p4 = new PileTableauComp("Une pile d'objets comparables") ;
	while (!p3.vide())
	    p4.empile(p3.depile()) ;
	while (!p2.vide())
	    p4.empile(p2.depile()) ;
	while (!p1.vide())
	    p4.empile(p1.depile()) ;
	System.out.println(p4) ;
	System.out.println("Je vais essayer de trier cette pile... appuyez sur ENTRÉE") ;
	Clavier.readString() ;
	p4.trier() ;
	System.out.println(p4) ;	
    }

    /*
    * Pour le trie des objets de différentes classes rejette une exception ClassCastException
    * car il ne peut pas comparer deux objet différend.
    * */
}