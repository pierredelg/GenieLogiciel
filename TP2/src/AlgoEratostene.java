import java.util.ArrayList;
import java.util.List;

public class AlgoEratostene {

    private List<Integer> liste;

    private AlgoEratostene(int n){
        liste = new ArrayList<>();
        for(int i = 2;i < n ; i++){
            liste.add(i);
        }
        for(int i= 0; i < liste.size();i++){
            supprimeMultiple(i);
        }
        System.out.println(liste.toString());
    }

    private void supprimeMultiple(int multiple){
        for(int i= 0; i < liste.size() ;i++){
            if(multiple != 0 && multiple != liste.get(i) && liste.get(i) % multiple == 0){
                liste.remove(liste.get(i));
            }
        }
    }

    public static void main(String[] args) {
        AlgoEratostene a = new AlgoEratostene(25);
    }
}
