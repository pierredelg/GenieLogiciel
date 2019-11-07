package employesAvecHeritage;

/**
 * Classe représentant un directeur.
 * Implémentée en singleton.
 * @author DELGRANGE Pierre
 */
public class Directeur extends EmployeQuelconque{

    /**
     * Instance unique d'un directeur
     */
    private static Directeur directeur;

    /**
     * Constructeur privé d'un directeur.
     * @param nom - Le nom du directeur
     */
    private Directeur(String nom) {
        super(nom);
    }

    /**
     * Méthode permettant de récupérer l'instance du directeur.
     * @return - le directeur
     */
    public static Directeur getDirecteur() {
        if (directeur == null)
            directeur = new Directeur("directeur");
        return directeur;
    }
}
