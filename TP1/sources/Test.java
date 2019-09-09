
public class Test
{
    public static void main (String [] args)
    {
        int i ;
        System.out.println("Il y a "+InstanceMemo.nombreInstances()+
                           " instances de la classe InstanceMemo") ;
        for (i=1; i<=4; i++) new InstanceMemo() ;
        System.out.println("Il y a "+InstanceMemo.nombreInstances()+
                           " instances de la classe InstanceMemo : ") ;
        InstanceMemo.afficherInstances() ;
        for (i=1; i<=10; i++) new InstanceMemo() ;
        System.out.println("Il y a "+InstanceMemo.nombreInstances()+
                           " instances de la classe InstanceMemo : ") ;
        InstanceMemo.afficherInstances() ;
    }
}
