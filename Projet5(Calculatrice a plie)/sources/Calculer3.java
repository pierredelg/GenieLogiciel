import calculatrice3.* ;

public class Calculer3 {
    public static void main(String [] args) {
        Calculatrice c = new Calculatrice() ;
        try {
            for (int i=0; i<args.length; i++)
                System.out.println(args[i] + " = " + c.calculer(args[i])) ;
	    } catch (CalculatriceException e) {
            System.out.println(e.getMessage()) ;
	    }
    }
}
