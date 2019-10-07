import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class OrdreInverse {

    public static String demandeUtilisateur(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez une phrase : ");
        return sc.nextLine();
    }

    public static void main(String[] args) {
        String phrase = demandeUtilisateur();
        StringTokenizer st = new StringTokenizer(phrase);
        Stack<String> stack = new Stack<>();
        while(st.hasMoreTokens()){
            stack.push(st.nextToken());
        }

        String ordreInverse = "";

        while (!stack.empty()){
            ordreInverse += stack.pop() + " ";
        }
        System.out.println(ordreInverse);
    }
}
