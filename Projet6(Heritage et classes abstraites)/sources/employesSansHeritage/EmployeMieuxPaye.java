package employesSansHeritage;

/**
 * Classe représentant un employé mieux payé.
 */
public class EmployeMieuxPaye {
    private double temps_travail, taux_horaire, majoration, heures ;
    private String nom ;

    /**
     * Constructeur de l'employé mieux payé.
     * Le temps de travail est de 35 heures.
     * Le taux horaire est de 7.5.
     * La majoration est de 40%.
     * @param nom - Le nom de l'employé.
     */
    public EmployeMieuxPaye(String nom) {
        this.temps_travail = 35;
        this.taux_horaire = 7.5;
        this.majoration = 1.40;
        this.nom = nom;
    }

    /**
     * Méthode permettant le calcul du salaire hebdomadaire de l'employé.
     * @return - Le salaire hebdomadaire en euros.
     */
    public double salaireHebdo(){
        if (heures > temps_travail)
            return temps_travail * taux_horaire
                    + (heures - temps_travail) * taux_horaire * majoration;
        return heures * taux_horaire;
    }

    /**
     * Méthode permettant de mettre à jour le temps de travail d'un employé.
     * @param x - le temps de travail de l'employé.
     */
    public void setTravail(double x){
        this.heures = x;
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
