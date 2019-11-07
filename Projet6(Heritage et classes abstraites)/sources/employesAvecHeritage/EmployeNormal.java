package employesAvecHeritage;

/**
 * Classe représentant un employé normal.
 */
public class EmployeNormal extends EmployeAvecHSup{

    /**
     * Constructeur d'un employé normal.
     * La majoration est de 1.25.
     * @param nom - Le nom de l'employé
     */
    public EmployeNormal(String nom) {
        super(nom, 1.25);
    }
}