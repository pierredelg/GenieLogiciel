public class AffichageSimple implements Affichage {
    @Override
    public String affichage_tableau(Disque[] d, int n) {
        String elementsPile = "";
        for(Disque disque : d){
            if (disque != null) {
                elementsPile += disque.diametre() + " ";
            }
        }
        return elementsPile;
    }
}
