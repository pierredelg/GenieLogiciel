package employesAvecHeritage;

/**
 * Classe représentant un employé quelconque.
 * @author DELGRANGE Pierre
 */
public abstract class EmployeQuelconque {

    /**
     * Représente le nom de l'employé.
     */
    protected String nom;

    /**
     * Représente le taux horaire des heures supplémentaires.
     */
    protected double  taux_horaire;

    /**
     * Représente le taux de majoration.
     */
    protected double majoration;

    /**
     * Représente le nombre d'heures éffectuée par un employé.
     */
    private double heures;

    /**
     * Représente le chiffre d'affaire d'un employé.
     */
    private double ca;

    /**
     * Représente le chiffre d'affaire total enregistré par les commerciaux.
     */
    protected static double caTotal;

    /**
     * Représente le montant du salaire fixe.
     */
    private static final double SALAIRE_FIXE = 1500;

    /**
     * Représente le nombre d'heures de travail.
     */
    private static final double TEMPS_TRAVAIL = 35;


    /**
     * Constructeur principal d'un employé.
     * @param nom - Le nom de l'employé.
     */
    public EmployeQuelconque(String nom) {
        this.nom = nom;
    }

    /**
     * Méthode permettant le calcul du salaire hebdomadaire de l'employé.
     * @return - Le salaire hebdomadaire en euros.
     */
    public double salaireHebdo(){
        if(this instanceof Commercial) {
            return SALAIRE_FIXE + ca/100;
        }
        if(this instanceof Directeur){
            return SALAIRE_FIXE + caTotal * 0.004;
        }
        if(this instanceof EmployeAvecHSup){
            if (heures > TEMPS_TRAVAIL)
                return TEMPS_TRAVAIL * taux_horaire
                        + (heures - TEMPS_TRAVAIL) * taux_horaire * majoration;
            return heures * taux_horaire;
        }
        return 0;
    }

    /**
     * Méthode permettant de mettre à jour le temps de travail d'un employé.
     * @param x - le temps de travail de l'employé.
     */
    public void setTravail(double x) {
        if(this instanceof Commercial) {
            this.ca = x;
        }
        if(this instanceof EmployeAvecHSup){
            this.heures = x;
        }
    }

    /**
     * Affiche le nom de l'employé.
     * @return Le nom de l'employé
     */
    @Override
    public String toString() {
        return nom;
    }
}
