package employesSansHeritage;

/**
 * Classe représentant un commercial.
 * @author DELGRANGE Pierre
 */
public class Commercial {

    private double ca;
    private double salaireFixe;

    private String nom;

    /**
     * Constructeur d'un commercial.
     * Le salaire fixe est de 1500 euros.
     * @param nom - le nom du commercial.
     */
    public Commercial(String nom) {
        this.salaireFixe = 1500;
        this.nom = nom;
    }

    /**
     * Méthode permettant le calcul du salaire hebdomadaire du commercial.
     * @return - Le salaire hebdomadaire en euros.
     */
    public double salaireHebdo() {
        return salaireFixe + ca/100;
    }

    /**
     * Méthode permettant de mettre à jour le chiffre d'affaire éffectué par un commercial.
     * @param chiffreAffaire - le chiffre d'affaire effectué par le commercial.
     */
    public void setTravail(double chiffreAffaire) {
        this.ca = chiffreAffaire;
    }

    /**
     * Affiche le nom du commercial.
     * @return Le nom de l'employé
     */
    @Override
    public String toString() {
        return nom;
    }
}
