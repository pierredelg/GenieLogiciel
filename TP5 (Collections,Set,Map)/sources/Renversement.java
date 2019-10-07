import java.util.Scanner;
import java.util.Stack;

public class Renversement {

    public static String demandeUtilisateur(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez une phrase : ");
        return sc.nextLine();
    }

    public static void main(String[] args) {
        String phrase = demandeUtilisateur();
        String nouvellePhrase = "";
        for(int i = 0 ; i < phrase.length(); i++){
            char c = phrase.charAt(i);
            if((c >= 65 && c <= 90 )||(c >= 97 && c <= 122)){
                nouvellePhrase += c;
            }
        }
        nouvellePhrase = nouvellePhrase.toUpperCase();
        System.out.println(nouvellePhrase);
        Stack<Character> stack = new Stack<>();
        for(int i = 0 ; i < nouvellePhrase.length() ; i++){
            stack.push(nouvellePhrase.charAt(i));
        }
        String inverse = new String();
        while(!stack.empty()){
            inverse += stack.pop();
        }
        System.out.println(inverse);

        if(phrase.equals(inverse)){
            System.out.println("Cette phrase est un palindrome");
        }
    }
}
