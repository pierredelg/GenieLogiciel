package calculatrice2;

public enum Operation {
    PLUS("+"), MOINS("-"), FOIS("*"), DIV("/"),PUISS("^");

    private final String code_operation;

    Operation(String code) {
        this.code_operation = code;
    }

    @Override
    public String toString() {
        return  code_operation;
    }

    public double eval(double [] operandes){

        if(operandes.length == 1){
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
