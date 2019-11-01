package calculatrice;

/**
 * Enumeration représentant une opération.
 * Avec un nom et un code opération
 * exemple : PLUS ("+")
 * @author DELGRANGE Pierre
 */
public enum Operation {

    PLUS("+"),
    MOINS("-"),
    FOIS("*"),
    DIV("/"),
    PUISS("^");

    /**
     * Le code de l'opération
     * par exemple :  "+" pour l'opération PLUS
     */
    private final String code_operation;

    /**
     * Constructeur qui construit une opération avec un code d'opération.
     * @param code - Le code de l'opération.
     */
    Operation(String code) {
        this.code_operation = code;
    }

    @Override
    public String toString() {
        return  code_operation;
    }

    /**
     * Méthode qui effectuer une opération selon la nature de l’opération.
     * @param x - une opérande
     * @param y - une opérande
     * @return - Le résultat de l'opération.
     */
    public double eval(double x, double y){

        if(this.code_operation.equals(Operation.PLUS.code_operation)){
            return x + y;
        }
        if(this.code_operation.equals(Operation.MOINS.code_operation)){
            return x - y;
        }
        if(this.code_operation.equals(Operation.DIV.code_operation)){
            if(y != 0) {
                return x / y;
            }
        }
        if(this.code_operation.equals(Operation.FOIS.code_operation)){
            return x * y;
        }
        if(this.code_operation.equals(Operation.PUISS.code_operation)){
            return Math.pow(x,y);
        }
        return 0;
    }
}
