
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class LambdaTestPredicate {

    // Déclaration de la méthode calcul
    //On reprends donc les deux arguments de la méthode calcul de la méthode main
    public static int calcul(String [] tab, Predicate<String> c){
        int cpt = 0;
        for (String s : tab){
            //On appel la méthode de l'interface en parametre qui correspond à la lambda du main
            //ici la methode test predicate renvoie un booleen
            if (c.test(s)){
                cpt++;
            }
        }
        return cpt;
    }

    public static void main(String[] args){
        //La lambda expression x.equals("coucou") est le deuxieme argument de la méthode calcul
        System.out.println("Il y a " + calcul(args,x->x.equals("coucou")) + " fois la chaîne \"coucou\" en argument");
        System.out.println("Il y a " + calcul(args,x->x.charAt(0) == '-') + " fois une chaine commençant par '-' en argument");

        System.out.println("Stream 1");

        Stream.of("salut","ca","va").forEach(System.out::println);

        System.out.println("Stream 2");

        Stream.of("salut","","ca","va","").filter(s -> !s.isEmpty()).forEach(System.out::println);

        System.out.println("Stream 3");

        Stream.of("salut","ca","va").filter(s -> s.length() == 5).limit(1).forEach(System.out::println);

        System.out.println("Stream 4");

        Stream.of("salut","ca","va").filter(s -> s.length() > 2).forEach(t -> System.out.println(t.toUpperCase().charAt(0) + "..." + t.charAt(t.length()-1)));

        System.out.println("Stream 5");

        Stream.of("salut","ca","va","moi","a").sorted(Comparator.comparingInt(String::length)).forEach(System.out::println);

        System.out.println("Stream 6");

        Stream.of("z", "salut","ca","ba","va","moi","a").sorted().forEach(System.out::println);

        System.out.println("Stream 7");

        try {
            Files.lines(Paths.get("ReadMe.md")).limit(15).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}