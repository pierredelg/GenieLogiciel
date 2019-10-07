import java.util.HashMap;
import java.util.Scanner;

public class RecensementHashMap {

    public static String demandeUtilisateur(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez une phrase : ");
        return sc.nextLine();
    }

    public static void main(String[] args) {
        HashMap<Character,Integer> map = new HashMap<>();

        String phrase = demandeUtilisateur();

        for(int i= 0 ; i < phrase.length(); i++){
            int nb = 0;
            char c = phrase.charAt(i);

            nb = map.get(c);

            if(nb != 0) {
                nb++;
                map.put(phrase.charAt(i), nb);
            }else{
                map.put(c,1);
            }
        }

        System.out.println(map);
    }
}
