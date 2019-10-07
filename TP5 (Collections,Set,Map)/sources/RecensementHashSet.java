import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class RecensementHashSet {

    public static String demandeUtilisateur(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez une phrase : ");
        return sc.nextLine();
    }

    public static void main(String[] args) {
        HashSet<Character> set = new HashSet<>();
        String phrase = demandeUtilisateur();
        for (int i = 0; i < phrase.length(); i++){
            set.add(phrase.charAt(i));
        }
        String nouvellePhrase = "";
        for (Character c : set){
            nouvellePhrase += c + "";
        }
        System.out.println(nouvellePhrase);
    }
}
