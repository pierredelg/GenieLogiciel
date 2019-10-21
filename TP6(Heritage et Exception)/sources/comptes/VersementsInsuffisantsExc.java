package comptes ;

/** Exception à lever dans le cas où les versements effectués
 * depuis le mois précédent sur un PEL sont insuffisants */
public class VersementsInsuffisantsExc extends Exception
{
    public VersementsInsuffisantsExc(int num_compte)
    {
        super("Les versements du mois écoulé sur le PEL no."
                + num_compte + " sont insuffisants") ;
    }
}
