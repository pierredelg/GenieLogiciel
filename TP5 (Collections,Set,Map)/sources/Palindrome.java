import java.util.Scanner;
import java.util.Stack;

public class Palindrome {

    public static String demandeUtilisateur(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez une phrase : ");
        return sc.nextLine();
    }
    public static void main(String[] args) {

        String phrase = demandeUtilisateur();

        phrase = phrase.replace(" ","");
        phrase = phrase.toUpperCase();

        Stack<Character> stack = new Stack<>();
        for(int i = 0 ; i < phrase.length() ; i++){
            stack.push(phrase.charAt(i));
        }
        String inverse = new String();
        while(!stack.empty()){
            inverse += stack.pop();
        }

        if(phrase.equals(inverse)){
            System.out.println("Cette phrase est un palindrome");
        }else {
            System.out.println("Ce n'est pas un palindrome");
        }
    }
}
