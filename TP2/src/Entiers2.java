public class Entiers2 {
    public static void main(String[] args) {
        System.out.println("100 : ");
        tester(100) ;
        System.out.println("\n127 :");
        tester(127) ;
        System.out.println("\n128 : ");
        tester(128) ;
        System.out.println("\n-128 : ");
        tester(-128) ;
        System.out.println("\n-129 : ");
        tester(-129) ;
        System.out.println("\n1000 : ");
        tester(1000) ;
    }
    public static void tester(int valeur) {
        Integer a = valeur ;
        int b = valeur ;
        Integer c = valeur ;
        System.out.println(a == b) ;
        System.out.println(b == c) ;
        System.out.println(a == c) ;
        //Les Integers sont stockés en mémoire cache de la jvm (dans la classe IntegerCache) dans un tableau pour les valeurs de -128 à +127
        //Donc l'adresse est la meme pour cet intervalle
        //En dehors de cet intervalle un nouvel objet est créé donc l'adresse sera différente
        //explication : https://www.owasp.org/index.php/Java_gotchas
    }
}