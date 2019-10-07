import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class OrdreInverseMot {

    public static String demandeUtilisateur(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez une phrase : ");
        return sc.nextLine();
    }

    public static void main(String[] args) {
        String phrase = demandeUtilisateur();
        StringTokenizer st = new StringTokenizer(phrase);
        String nouvellePhrase = "";
        while(st.hasMoreTokens()) {
            Stack<Character> stack = new Stack<>();
            String mot = st.nextToken();
            for(int i=0;i < mot.length(); i++) {
                stack.push(mot.charAt(i));
            }
            String motInverse = "";
            while(!stack.empty()){
                motInverse += stack.pop();
            }
            nouvellePhrase += motInverse + " ";
        }
        System.out.println(nouvellePhrase);

    }
}
