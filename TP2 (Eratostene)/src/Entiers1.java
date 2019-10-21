public class Entiers1 {
    public static void main(String[] args) {
        Integer a = new Integer(100) ;
        int b = 100 ;
        Integer c = new Integer(100) ;
        System.out.println(a == b) ;//unboxing de a
        System.out.println(b == c) ;//unboxing de c
        System.out.println(a == c) ;//objet différent adresse différente
    }
}

