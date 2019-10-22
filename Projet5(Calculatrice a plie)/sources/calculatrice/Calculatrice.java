package calculatrice;

import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Classe représentant une calculatrice fonctionnant avec une pile en notation "Polonaise" c'est à dire postfixée.
 */
public class Calculatrice {

    private Stack<Double> resultat;

    private HashMap<String,Operation> operations;

    public Calculatrice() {
        this.resultat = new Stack<>();
        this.operations = new HashMap<>();
        for(Operation o :Operation.values()){
            operations.put(o.toString(),o);
        }
    }


    public double calculer(String s) throws CalculatriceException{
        //On divise la chaine de caractere en parametre en token
        StringTokenizer st = new StringTokenizer(s);
        //On parcourt les tokens
        while (st.hasMoreTokens()) {

            String token  = st.nextToken();
            //Si le token est une opération
            if(operations.containsKey(token)){

                if(resultat.size() > 2) {
                    //On dépile les deux dernieres opérandes
                    double a = resultat.pop();
                    double b = resultat.pop();
                    Operation o = operations.get(token);
                    //On fait le calcul
                    double resultatOp = o.eval(a,b);
                    //On empile le résultat
                    resultat.push(resultatOp);
                }
            }
            else{
                //Si ce n'est pas une opération on empile l'opérande
                resultat.push(Double.valueOf(token));
            }
        }
        //Lorsqu'il n'y a plus de token le résultat se trouve en haut de la pile
        return resultat.pop();

    }
}
