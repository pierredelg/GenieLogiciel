import calculatrice.* ;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Test {
    public static void main(String [] args) {
        if (args.length < 2) 
            System.err.println("Au moins deux nombres sur la ligne de commande !") ;
        else {
            if(args.length == 2) {
                // deux nombres passés sur la ligne de commande
                double x = new Double(args[0]);
                double y = new Double(args[1]);
                // la liste des opérations disponibles
                for (Operation o : Operation.values()) {
                    System.out.println(x + " " + o + " " + y + " = " + o.eval(x, y));
                }
            }
            if(args.length > 2) {
                double x = new Double(args[0]);
                String operateur = args[1];
                double y = new Double(args[2]);
                Operation o = null;
                if(operateur.contains(Operation.FOIS.toString())){
                    o=Operation.FOIS;
                }
                if(operateur.contains(Operation.MOINS.toString())){
                    o=Operation.MOINS;
                }
                if(operateur.contains(Operation.PLUS.toString())){
                    o=Operation.PLUS;
                }
                if(operateur.contains(Operation.DIV.toString())){
                    o=Operation.DIV;
                }
                if(operateur.contains(Operation.PUISS.toString())){
                    o=Operation.PUISS;
                }
                if(o != null) {
                    System.out.println(x + " " + o + " " + y + " = " + o.eval(x, y));
                }else{
                    System.out.println("Impossible de trouver l'opérateur");
                }
            }
        }
    }
}
