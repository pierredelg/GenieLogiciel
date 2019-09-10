/**
 * La Structure Filtre forme une liste chainée de Filtre
 */
public class Filtre {

    private int multiple;

    private Filtre filtreSuivant;

    private String result = "";
    
    Filtre(int i) {
        this.multiple = i;
    }

    public boolean filtrer(int i) {

        if(i == multiple){
            result += i + "\n";
            return true;
        }

        if(i % multiple == 0){
            return true;
        }
        if(filtreSuivant != null){
            return filtreSuivant.filtrer(i);
        }
        filtreSuivant = new Filtre(multiple + 1);
        result += i + "\n";
        return false;
    }
    public String toString(){
        if(filtreSuivant != null){
            result += filtreSuivant.toString();
        }
        return result;
    }
}
