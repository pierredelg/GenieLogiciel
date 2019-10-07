/**
 * La Structure Filtre forme une liste chainée de Filtre
 */
public class FiltrePerso {

    private int multiple;

    private FiltrePerso filtrePersoSuivant;

    private String result = "";
    
    FiltrePerso(int i) {
        this.multiple = i;
    }

    boolean filtrer(int i) {

        if(i == multiple){
            result += i + "\n";
            return true;
        }
        if(i % multiple == 0){
            return true;
        }
        if(filtrePersoSuivant != null){
            return filtrePersoSuivant.filtrer(i);
        }
        filtrePersoSuivant = new FiltrePerso(multiple + 1);
        result += i + "\n";
        return false;
    }
    public String toString(){
        if(filtrePersoSuivant != null){
            result += filtrePersoSuivant.toString();
        }
        return result;
    }
}
