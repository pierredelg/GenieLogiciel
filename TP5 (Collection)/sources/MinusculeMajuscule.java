import java.util.Scanner;
import java.util.StringTokenizer;

public class MinusculeMajuscule {

    public static String demandeUtilisateur(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez une phrase : ");
        return sc.nextLine();
    }


    public static void main(String[] args) {
        StringTokenizer st = new StringTokenizer(demandeUtilisateur());
        String result = "";
        boolean min = false;
        while (st.hasMoreTokens()){

            if(min) {
                result += st.nextToken().toLowerCase() + " ";
                min = false;
            }
            else {
                result += st.nextToken().toUpperCase() + " ";
                min = true;
            }
        }

        System.out.println(result);
    }
}
