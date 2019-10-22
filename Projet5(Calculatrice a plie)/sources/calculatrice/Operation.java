package calculatrice;

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
