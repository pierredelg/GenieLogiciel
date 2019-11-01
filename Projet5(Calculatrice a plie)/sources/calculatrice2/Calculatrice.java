package calculatrice2;


import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Classe représentant une calculatrice fonctionnant avec une pile en notation "Polonaise" c'est à dire postfixée.
 * @author DELGRANGE Pierre
 */
public class Calculatrice {

    /**
     * La pile de nombres servant à faire le calcul
     */
    private Stack<Double> pileResultat;

    /**
     * Dictionnaire permettant de retrouver l’opération
     * à partir de la chaîne de caractères qui la représente (son code_operation)
     */
    private HashMap<String, Operation> hashMapOperation;

    /**
     * Constructeur de la calculatrice fonctionnant avec une Stack et une HashMap
     */
    public Calculatrice() {
        this.pileResultat = new Stack<>();
        this.hashMapOperation = new HashMap<>();
        for(Operation o : Operation.values()){
            hashMapOperation.put(o.toString(),o);
        }
    }

    /**
     * Méthode permettant de calculer à partir d'une chaine de caractère en parametre avec des operateurs et des operandes postfixée.
     * @param s - chaine de caractère avec des operateurs et des operandes postfixée
     * @return - Le résultat du calcul ou 0 si erreur dans la chaine envoyée.
     * @throws calculatrice.CalculatriceException
     */
    public double calculer(String s) throws CalculatriceException {
        //On divise la chaine de caractere en parametre en token
        StringTokenizer stringTokenizer = new StringTokenizer(s);
        //On parcourt les tokens
        while (stringTokenizer.hasMoreTokens()) {

            String token  = stringTokenizer.nextToken();
            //Si le token est une opération
            if(hashMapOperation.containsKey(token)){
                double a = 0,b = 0;
                Operation operateur = hashMapOperation.get(token);
                double resultatOp = 0;
                double[] tableau;
                if(operateur != null && (operateur.toString().equals("V") || operateur.toString().equals("ABS") || operateur.toString().equals("NOT"))) {
                    if(!pileResultat.empty()) {
                        a = pileResultat.pop();
                    }
                    tableau = new double[]{a};
                    resultatOp = operateur.eval(tableau);
                }else{

                    //On dépile les deux dernieres opérandes
                    if(!pileResultat.empty()){
                        a = pileResultat.pop();
                    }
                    if(!pileResultat.empty()) {
                        b = pileResultat.pop();
                    }

                    if(operateur != null && operateur.toString().equals("IF")){
                        if(a != 0){
                            resultatOp = b;
                        }else {
                            if (!pileResultat.empty()) {
                                resultatOp = pileResultat.pop();
                            }
                        }
                    }else{
                        tableau = new double[]{a, b};
                        if(operateur != null) {
                            //On fait le calcul
                            resultatOp = operateur.eval(tableau);
                        }
                    }
                }
                //On empile le résultat
                pileResultat.push(resultatOp);
            }
            else{
                //Si ce n'est pas une opération on empile l'opérande
                pileResultat.push(Double.valueOf(token));
            }
        }
        //Lorsqu'il n'y a plus de token le résultat se trouve en haut de la pile
        if(!pileResultat.empty()) {
            return pileResultat.pop();
        }else{
            return 0;
        }
    }
}
