package comptes ;
public interface Compte
{
    /** Retourne le numéro identifiant le compte */
    int getNumero() ;
    /** Retourne le nombre de mois écoulés depuis l’ouverture */
    int getAnciennete() ;
    /** Retourne le numéro du compte et le montant */
    String toString() ;
    /** Effectue un ajout de la somme spécifiée sur le compte */
    void ajouter(double somme) ;
    /** Tente d’effectuer un retrait de la somme spécifiée sur le compte */
    void retirer(double somme) ;
    /** Effectue les opérations mensuelles de maintenance du compte. Cette méthode est
     * susceptible de déclencher des exceptions si des anomalies sont détectées. */
    void operationsMensuelles() throws DepassementDecouvertExc, VersementsInsuffisantsExc ;
}