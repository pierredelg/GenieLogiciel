import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Tri {


    public static String demandeUtilisateur(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez une phrase : ");
        return sc.nextLine();
    }

    public static void main(String[] args) {
        StringTokenizer st = new StringTokenizer(demandeUtilisateur());
        ArrayList<String> list = new ArrayList<>();
        while(st.hasMoreTokens()){
            list.add(st.nextToken());
        }
        Collections.sort(list);

        System.out.println(list);

    }
}
