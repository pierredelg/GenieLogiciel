package employesAvecHeritage;

/**
 * Classe représentant un employé avec la possibilité d'etre payé pour les heures supplémentaires.
 * @author DELGRANGE Pierre
 */
public abstract class EmployeAvecHSup extends EmployeQuelconque {

    /**
     * Constructeur de l'employé
     * @param nom - Le nom de l'employé
     */
    public EmployeAvecHSup(String nom,double majoration) {
        super(nom);
        this.majoration = majoration;
        this.taux_horaire = 7.5;
    }
}
