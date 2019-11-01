package calculatrice3;

import java.util.Stack;

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
    IF("IF"),
    DROP,
    DUP,
    SWAP,
    COUNT;

    /**
     * Le code de l'opération
     * par exemple :  "+" pour l'opération PLUS
     */
    private final String code_operation;

    /**
     * L'arité de l'opération, c'est à dire, le nombre d’opérandes exigé pour le calcul.
     */
    private int arite;

    /**
     * Constructeur sans parametre.
     * Construit un ordre dont l'arité est 0.
     */
    Operation(){
        this.arite = 0;
        code_operation = this.name();
    }

    /**
     * Constructeur qui construit une opération avec un code d'opération.
     * Et affecte l'arété de cette opération.
     * @param code - Le code de l'opération.
     */
    Operation(String code) {
        this.code_operation = code;

        if(this.ordinal() <= 4){
            this.arite = 2;
        }
        if(this.ordinal() >= 5 && this.ordinal() <= 7){
            this.arite = 1;
        }
        if(this.ordinal() == 8){
            this.arite = 3;
        }
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
     * Méthode permettant d'exécuter l'ordre ou l'opération demandée.
     * @param pile - La pile de résultat.
     * @return - La pile de résultat modifiée.
     * @throws CalculatriceException
     */
    public Stack<Double> execute(Stack<Double> pile) throws CalculatriceException {
        if(arite != 0){

            if(pile.empty()){
                throw new CalculatriceException("pas assez d'arguments");
            }
            if(arite == 1){
                double[] tab = {pile.pop()};
                pile.push(eval(tab));
            }
            if(arite == 2){
                double[] tab = new double[2];
                tab[0] = pile.pop();
                if(pile.empty()){
                    throw new CalculatriceException("pas assez d'arguments");
                }
                tab[1] = pile.pop();
                pile.push(eval(tab));
            }
            if(arite == 3){
                double[] tab = new double[3];
                for(int i = 0 ; i < tab.length ; i++){
                    if(pile.empty()){
                        throw new CalculatriceException("pas assez d’arguments");
                    }
                    tab[i] = pile.pop();
                }
                if(tab[0] != 0){
                    pile.push(tab[1]);
                }
                else{
                    pile.push(tab[2]);
                }
            }
        }
        else{

            if (this.code_operation.equals(Operation.COUNT.code_operation)) {
                pile.push((double) pile.size());
            }
            if(pile.empty()){
                throw new CalculatriceException("pile vide");
            }else {
                if (this.code_operation.equals(Operation.DROP.code_operation)) {
                    pile.pop();
                }
                if (this.code_operation.equals(Operation.DUP.code_operation)) {
                    double sommet = pile.pop();
                    pile.push(sommet);
                    pile.push(sommet);
                }
                if (this.code_operation.equals(Operation.SWAP.code_operation)) {
                    double premier = pile.pop();
                    double deuxieme = pile.pop();
                    pile.push(premier);
                    pile.push(deuxieme);
                }
            }
        }
        return pile;
    }

    /**
     * Méthode qui effectuer une opération selon la nature de l’opération.
     * @param operandes - Un tableau contenant les opérandes de l'opération à effectuée.
     * @return - Le résultat de l'opération.
     * @throws CalculatriceException
     */
    public double eval(double [] operandes) throws CalculatriceException {

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
                else{
                    throw new CalculatriceException("division pas 0");
                }
            }
            if (this.code_operation.equals(Operation.FOIS.code_operation)) {
                return operandes[0] * operandes[1];
            }
            if (this.code_operation.equals(Operation.PUISS.code_operation)) {
                return Math.pow(operandes[0], operandes[1]);
            }
        }
        throw new CalculatriceException("Trop d'arguments");
    }
}
