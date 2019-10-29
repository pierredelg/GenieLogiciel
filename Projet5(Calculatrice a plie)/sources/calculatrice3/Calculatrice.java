package calculatrice3;


import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Classe représentant une calculatrice fonctionnant avec une pile en notation "Polonaise" c'est à dire postfixée.
 * @author DELGRANGE Pierre
 */
public class Calculatrice {

    private Stack<Double> pileResultat;

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
     * @throws CalculatriceException
     */
    public double calculer(String s) throws CalculatriceException {

        //On divise la chaine de caractere en parametre en token
        StringTokenizer stringTokenizer = new StringTokenizer(s);

        //On parcourt les tokens
        while (stringTokenizer.hasMoreTokens()) {

            String token  = stringTokenizer.nextToken();

            //Si le token est une opération
            if(hashMapOperation.containsKey(token)){

                //On vérifie le type d'operateur
                Operation operateur = hashMapOperation.get(token);
                if(operateur.ordinal() < 5){
                    operateur.setArite(2);
                }
                if(operateur.ordinal() > 4 && operateur.ordinal() < 8){
                    operateur.setArite(1);
                }
                if(operateur.ordinal() == 8){
                    operateur.setArite(3);
                }

                //On fait le calcul
                operateur.execute(pileResultat);
            }
            else{
                try {
                    double nombre = Double.parseDouble(token);
                    pileResultat.push(nombre);
                }catch (NumberFormatException e){
                    throw new CalculatriceException("opération inconnue");
                }

            }
        }
        //Lorsqu'il n'y a plus de token le résultat se trouve en haut de la pile
        if(!pileResultat.empty()) {
            if(pileResultat.size() > 1){
                throw new CalculatriceException("nombres en trop dans la pile de calcul");
            }
            return pileResultat.pop();
        }else{
            return 0;
        }
    }
}
