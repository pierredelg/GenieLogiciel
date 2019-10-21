package comptes ;

/** Exception à lever dans le cas d’un solde négatif
 * dépassant le découvert autorisé d’un compte courant */
public class DepassementDecouvertExc extends Exception
{
    public DepassementDecouvertExc(int num_compte)
    {
        super("Depassement du découvert autorisé sur le "
                + "compte no. " + num_compte) ;
    }
}