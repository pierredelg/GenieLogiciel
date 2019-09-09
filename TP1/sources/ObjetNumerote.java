public class ObjetNumerote{

    public static int numero = 0;

    public ObjetNumerote(){
        numero++;        
    }
    public String toString(){
            return "numero = " + this.numero;
    }

    public static void main (String argv[]){
        for(int i= 0; i < 10;i++ ){
            System.out.println(new ObjetNumerote());
        }       
    }
        
}
