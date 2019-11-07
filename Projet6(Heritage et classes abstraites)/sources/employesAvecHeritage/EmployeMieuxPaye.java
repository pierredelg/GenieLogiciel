package employesAvecHeritage;

/**
 * Classe représentant un employé mieux payé.
 */
public class EmployeMieuxPaye extends EmployeAvecHSup{

    /**
     * Constructeur de l'employé mieux payé.
     * La majoration est de 1.40.
     * @param nom - Le nom de l'employé.
     */
    public EmployeMieuxPaye(String nom) {
        super(nom, 1.40);
    }
}
