import java.util.Iterator ;

public class TestDict {
    public static void main(String [] arg) {
        TabDict d = new TabDict() ;
        d.ajouter(new CoupleObj("Un entier", "sa racine carrée")) ;
        for (int i=1; i<=10; i++)
            d.ajouter(i*i, i) ;
        d.ajouter(new CoupleObj(2, "sqrt(2)")) ;
        d.enleverPour(49) ;
        d.ajouter(1, "lui-même") ;
        System.out.println(d) ;

        // CI-DESSOUS LES TESTS À RAJOUTER APRÈS AVOIR
        // IMPLÉMENTÉ DANS TabDict L'INTERFACE ITERABLE
        
         Iterator it = d.iterator() ;
         while (it.hasNext()) {
             Object o = it.next() ;
             System.out.println(o) ;
             if (o.equals(36)) {
                 System.out.println("J'enlève "+o) ;
                 it.remove() ;
             }
         }
         for (Object o: d)
             System.out.println(d.assocPour(o)) ;
        
    }
}
