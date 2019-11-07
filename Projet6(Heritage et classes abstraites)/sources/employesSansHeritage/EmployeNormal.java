package employesSansHeritage;

/**
 * Classe représentant un employé normal.
 */
public class EmployeNormal {

    /**
     * Représente le nombre d'heures de travail.
     */
    private double temps_travail;

    /**
     * Représente le taux horaire des heures supplémentaires.
     */
    private double taux_horaire;

    /**
     * Représente le taux de majoration.
     */
    private double majoration;

    /**
     * Représente le nombre d'heures éffectuée par un employé.
     */
    private double heures;

    /**
     * Représente le nom de l'employé.
     */
    private String nom;

    /**
     * Constructeur d'un employé normal.
     * Le temps de travail est de 35 heures.
     * Le taux horaire des heures supplémentaire est de 7.5.
     * La majoration est de 1.25.
     * @param nom - Le nom de l'employé
     */
    public EmployeNormal(String nom) {
        temps_travail = 35;
        taux_horaire = 7.5;
        majoration = 1.25;
        this.nom = nom;
    }

    /**
     * Méthode permettant de mettre à jour le temps de travail d'un employé.
     * @param heures - le temps de travail de l'employé.
     */
    public void setTravail(double heures) {
        this.heures = heures;
    }

    /**
     * Méthode permettant le calcul du salaire hebdomadaire de l'employé.
     * @return - Le salaire hebdomadaire en euros.
     */
    public double salaireHebdo() {
        if (heures > temps_travail)
            return temps_travail * taux_horaire
                    + (heures - temps_travail) * taux_horaire * majoration;
        return heures * taux_horaire;
    }

    /**
     * Affiche le nom de l'employé.
     * @return Le nom de l'employé
     */
    public String toString() {
        return nom;
    }
}