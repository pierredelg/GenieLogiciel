
Crible d'Ératosthène

TP2 / GL / LP DA2I / semestre 1

Le but de ce TP est de vous familiariser avec l'approche objet en comparant à la version classique (impérative) de l'algorithme du crible d'Ératosthène une version réalisée à l'aide d'objets. Une étude des performances de deux implantations objets est ensuite réalisée.
Algorithme impératif

Il ne devrait pas poser de problème majeur, une fois la structure de données correctement choisie.
Principe

On rappelle que l'algorithme du « crible d'Ératosthène » sert à établir la liste des nombres premiers compris entre 2 et N
. Son principe est le suivant : on range dans un tableau tous les candidats possibles, c'est-à-dire tous les entiers de 2 à N, puis on « raye » du tableau ceux qui sont multiples d'un nombre inférieur non encore rayé.
2 	3 	4 	5 	6 	7 	8 	9 	10 	11 	12 	13 	14 	15 	16 	17 	18 	19 	20 	21 	22 	23 	24 	25

Au départ, 2 n'est pas rayé, donc on élimine tous ses multiples :
2 	3 	4 	5 	6 	7 	8 	9 	10 	11 	12 	13 	14 	15 	16 	17 	18 	19 	20 	21 	22 	23 	24 	25

L'entier non rayé suivant est 3, dont on raye les multiples qui ne le sont pas déjà :
2 	3 	4 	5 	6 	7 	8 	9 	10 	11 	12 	13 	14 	15 	16 	17 	18 	19 	20 	21 	22 	23 	24 	25

… et ainsi de suite, de sorte que dans le tableau final, les seuls nombres non rayés sont les nombres premiers:
2 	3 	4 	5 	6 	7 	8 	9 	10 	11 	12 	13 	14 	15 	16 	17 	18 	19 	20 	21 	22 	23 	24 	25
Exercice

Écrire un programme en Java (une seule classe avec une méthode main) qui réalise cet algorithme et affiche la liste des nombres premiers compris entre 2 et N

.
Algorithme objet
Principe

On veut réaliser les mêmes opérations en utilisant deux sortes d'objets: d'une part, un générateur de nombres qui énumère tous les entiers de 2 à N
; d'autre part, des filtres de valeur pi que l'on place les uns à la suite des autres. Le générateur envoie les entiers x

successifs aux filtres qui réagissent de la façon suivante:

    si x

est multiple de pi , rien d'autre ne se passe, le filtre renvoie «faux» (pour dire que x
n'est pas premier);
sinon:

    si le filtre est suivi d'un autre, il demande à ce dernier de filtrer x

;
sinon il crée un nouveau filtre de valeur pi+1=x

        .

Exemple

    Situation initiale.

    Générateur 2...5

→ Filtre 2

Première étape. Le générateur commence son énumération avec 2:

Générateur 2...5
−→−−−filtrer 2 Filtre 2

Il ne se passe rien, puisque 2 est la valeur du filtre. Ce dernier renvoie donc seulement la valeur true pour indiquer que le nombre envoyé par le générateur est premier.

Étape suivante.

Générateur 3...5
−→−−−filtrer 3 Filtre 2

Cette fois 3 n'est pas multiple de 2; comme le filtre de valeur 2 est le dernier, il se crée un filtre suivant de valeur 3 et renvoie true (puisqu'aucun filtre n'a pu éliminer le nombre, c'est qu'il est premier). On obtient alors la structure suivante :

Générateur 4...5
→ Filtre 2 → Filtre 3

Étape suivante.

Générateur 4...5
−→−−−filtrer 4 Filtre 2 → Filtre 3

Le filtre 2 élimine son multiple 4, et renvoie donc false.

Étape suivante.

Générateur 5...5
−→−−−filtrer 5 Filtre 2 → Filtre 3

le filtre 2 ne peut éliminer 5, il demande donc au filtre 3 de filtrer 5:

Générateur 5...5
→ Filtre 2 −→−−−filtrer 5 Filtre 3

le filtre 3 ne peut pas non plus éliminer 5; comme il est le dernier il crée un nouveau filtre et renvoie true. On obtient finalement le crible suivant:

Générateur → Filtre 2
→ Filtre 3 → Filtre 5

Le fait d'utiliser des objets permet de réduire considérablement le code du programme principal:

/**
 * Algorithme "objet" du crible d'Ératosthène.
 */
public class Eratosthene {
    /**
     * Principe : on crée un générateur de nombres de 2 à N,
     * puis on le fait travailler.
     */
    public static void main (String [] argv) {
        Generateur g = new Generateur(10000) ;
        g.travailler() ;
        System.out.println(g.toString()) ;
    }
}

/**
 * Générateur de nombres pour le crible d'Ératosthène dans sa version objet.
 */
public class Generateur {
    // entier maximal jusqu'auquel on effectue la recherche de nombres premiers
    private int limite ;
    // filtre initial (de valeur 2) auquel on envoie tous les nombres à tester
    private Filtre initial ;
    /**
     * crée un générateur de limite n
     */
    public Generateur (int n) {
        limite = n ;
        initial = new Filtre(2) ;
    }
    /**
     * affichage de la liste des nombres premiers trouvés ; pour cela
     * on demande au filtre initial de s'afficher (à charge pour lui
     * de demander aux autres filtres d'afficher la suite)
     */
    public String toString() {
        return "Liste des nombres premiers compris entre 2 et "
            + limite + " : \n" + initial + "\n" ;
    }
    /**
     * génère les nombres à tester de 2 à limite, et les envoie au
     * filtre initial
     */
    public void travailler() {
        for (int i = 2; i<=limite; i++)
            initial.filtrer(i) ;
    }
}

Exercices

    Déterminez les attributs d'un filtre. Écrivez en conséquence un constructeur.

    Écrivez une méthode

    public void filtrer(int x);

    qui réalise la règle de filtrage expliquée auparavant.

    Écrivez enfin une méthode

    public String toString();

    qui construit une chaîne de caractères correspondant à toutes les valeurs des filtres depuis celui qui exécute la méthode.
    Quelle structure de données forment les instances de Filtre ?

Considérations de performance

Recupérez et décompressez dans votre répertoire l'archive fichiers-tp2.tgz de Moodle : vous disposez entre autres de deux versions objet de l'algorithme d'Ératosthène (Eratosthene.java et EratostheneRapide.java).

    Lisez et comparez le code de ces deux versions. Compilez et exécutez ces versions. Que se passe-t-il en pratique pour chacune des deux versions si l'on cherche à augmenter sensiblement la limite N

    (par exemple de 10 000 à 100 000 ou 200 000) ?
    Augmentez la taille de la pile disponible pour la machine virtuelle de l'ordre de 100 Mb (avec l'option -Xss) et regardez ce qui se passe pour chacune des versions. Comment peut-on expliquer la différence de performance observée ? (la lecture de l'API java pourra être utile).

    La commande unix time permet de mesurer le temps d'exécution d'un programme. Utilisez-la pour mesurer objectivement le temps d'exécution nécessaire à chaque version en fonction de la limite à atteindre (vous pourrez par exemple mesurer les temps d'exécutions pour des valeurs de N échelonnées entre 10 000, et 300 000). La commande time doit simplement être placée avant la ligne de commande dont on veut mesurer le temps d'exécution. Par exemple :

    $ time java -classpath classes Eratosthene

    On veut maintenant automatiser les mesures de temps d'exécution au moyen du petit script test.sh suivant(fourni dans l'archive sur Moodle):

    echo -e "N\tduree"
    for i in {10,20,30,40,50,75,100,125,150,175,200,250,300}000
    do
       echo -n -e "$i\t"
       (time -p java -classpath classes -Xss100M $1 $i) 2>&1 |grep user|cut -d' ' -f2
    done

    Utilisez-le comme suit:

    for i in Eratosthene{,Rapide} ; do ./test.sh $i >$i.csv ; done

    On génère ainsi deux fichiers de données au format CSV, qu'on peut exploiter au moyen de n'importe quel outil statistique, par exemple R (avec le script comparer.R fourni sur Moodle):

    $ R --no-save <comparer.R

    Le résultat est tracé dans le fichier comparaison.png.

Complément sur Integer

    Testez le programme ci-dessous. Expliquez pourquoi la relation == n'est pas transitive (normalement, si a=b

et b=c, alors a=c

    !).

     public class Entiers1 {
        public static void main(String[] args) {
            Integer a = new Integer(100) ;
            int b = 100 ;
            Integer c = new Integer(100) ;
            System.out.println(a == b) ;
            System.out.println(b == c) ;
            System.out.println(a == c) ;
        }
    }

    Testez le programme ci-dessous. Proposez une explication pour les résultats observés.

public class Entiers2 {
    public static void main(String[] args) {
        tester(100) ;
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

Auteur: Sébastien Picault

Email: cedric.lhoussaine@univ-lille1.fr

Validate
