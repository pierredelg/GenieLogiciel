/**
 * Affichage d'une tour en affichant le diametre des disques.
 */
public class AffichageSimple implements Affichage {

    /**
     * Affiche chaque diametre de disques.
     * @param d - Le tableau de Disque
     * @param n - Le nombre d'éléments dans le tableau.
     * @return Une chaine de caractères représentant la pile de Disque.
     */
    @Override
    public String affichage_tableau(Disque[] d, int n) {

        String elementsPile = "";

        for(Disque disque : d){
            //Pour chaque disque on affiche son diametre
            if (disque != null) {
                elementsPile += disque.diametre() + " ";
            }
        }
        return elementsPile;
    }
}
