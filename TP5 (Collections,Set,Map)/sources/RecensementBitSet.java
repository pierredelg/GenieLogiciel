import java.util.BitSet;
import java.util.Scanner;

public class RecensementBitSet {
    public static String demandeUtilisateur(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez une phrase : ");
        return sc.nextLine();
    }

    public static void main(String[] args) {
        BitSet bs = new BitSet();
        String phrase = demandeUtilisateur();
        for (int i = 0; i < phrase.length(); i++){
            bs.set(phrase.charAt(i));
        }
        for(int i = 0 ; i < bs.length();i++){
            if(bs.get(i)) {
                System.out.print((char) i + " ");
            }
        }
    }
}
