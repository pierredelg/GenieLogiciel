package employesAvecHeritage;

/**
 * Classe représentant un commercial.
 * @author DELGRANGE Pierre
 */
public class Commercial extends EmployeQuelconque{

    /**
     * Constructeur d'un commercial.
     * @param nom - le nom du commercial.
     */
    public Commercial(String nom) {
        super(nom);
    }

    /**
     * Méthode permettant de mettre à jour le chiffre d'affaire éffectué par un commercial.
     * @param chiffreAffaire - le chiffre d'affaire effectué par le commercial.
     */
    public void setTravail(double chiffreAffaire){
        caTotal += chiffreAffaire;
        super.setTravail(chiffreAffaire);
    }
}
