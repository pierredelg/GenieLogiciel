/**
 * Affichage d'une tour en affichant le diametre des disques.
 */
public class AffichageSimple implements Affichage {

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
