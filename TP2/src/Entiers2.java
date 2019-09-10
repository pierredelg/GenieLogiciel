public class Entiers2 {
    public static void main(String[] args) {
        tester(100) ;
        System.out.println();
        tester(1000) ;
    }
    public static void tester(int valeur) {
        Integer a = valeur ;
        int b = valeur ;
        Integer c = valeur ;
        System.out.println(a == b) ;
        System.out.println(b == c) ;
        System.out.println(a == c) ;
    }
}