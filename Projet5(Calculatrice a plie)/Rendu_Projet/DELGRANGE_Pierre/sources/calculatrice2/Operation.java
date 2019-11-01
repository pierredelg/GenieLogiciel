package calculatrice2;

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
    PUISS("^"),
    ABS("ABS"),
    SQRT("V"),
    NOT("NOT"),
    IF("IF");

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

    /**
     * Méthode permettant l'affichage de l'opération.
     * @return - L'opération représentée par son code_opération.
     */
    @Override
    public String toString() {
        return  code_operation;
    }

    /**
     * Méthode qui effectuer une opération selon la nature de l’opération.
     * @param operandes - Un tableau contenant les opérandes de l'opération à effectuée.
     * @return - Le résultat de l'opération.
     */
    public double eval(double [] operandes){

        if(operandes.length == 1){
            if (this.code_operation.equals(Operation.NOT.code_operation)) {
                if(operandes[0] == 0){
                    return 1;
                }
                else{
                    return 0;
                }
            }
            if (this.code_operation.equals(Operation.ABS.code_operation)) {
                if(operandes[0] < 0){
                    return - operandes[0];

                }else{
                    return operandes[0];
                }
            }
            if (this.code_operation.equals(Operation.SQRT.code_operation)) {
                return Math.sqrt(operandes[0]);
            }
            return operandes[0];
        }

        if(operandes.length == 2){

            if (this.code_operation.equals(Operation.PLUS.code_operation)) {
                return operandes[0] + operandes[1];
            }
            if (this.code_operation.equals(Operation.MOINS.code_operation)) {
                return operandes[0] - operandes[1];
            }
            if (this.code_operation.equals(Operation.DIV.code_operation)) {
                if (operandes[1] != 0) {
                    return operandes[0] / operandes[1];
                }
            }
            if (this.code_operation.equals(Operation.FOIS.code_operation)) {
                return operandes[0] * operandes[1];
            }
            if (this.code_operation.equals(Operation.PUISS.code_operation)) {
                return Math.pow(operandes[0], operandes[1]);
            }
        }
        return operandes[0];
    }
}
