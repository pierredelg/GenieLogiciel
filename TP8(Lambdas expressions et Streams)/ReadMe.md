#Lambda expressions et streams
#TP8 / GL / LP DA2I / semestre 1
Commencer par récupérer le cours sur Moodle et le relire intégralement!

##Lambda expressions et interfaces fonctionnelles
On considère la classe incomplète suivante:

```

public class Lambda {
    // Déclaration de la méthode calcul

    public static void main(String[] args){
        System.out.println("Il y a " + calcul(args,x->x.equals("coucou")) + " fois la chaîne \"coucou\" en argument");
    }
}

```

Ce programme doit afficher le nombre de fois que la chaîne coucou est passée en argument.

Définissez votre propre interface fonctionnelle et la méthode calcul qui complètent ce programme.
En alternative à votre propre interface, quelle interface de java.util.function pourriez-vous utilisez ? Essayez.
Complétez, en une ligne, votre programme pour qu'il affiche en plus le nombre d'arguments commençant par un tiret -.

### Manipulation de base des streams

Créez une stream de chaîne de caractères et affichez en les éléments.
Comment n'afficher que les chaînes non vides ?
Comment trouver (et afficher) la première chaîne de longueur 5 ?
Écrire une lambda expression qui transforme une chaîne "chaine" en "C...e", i.e. qui ne garde que les premier et dernier caractère, en mettant le premier en majuscule et en remplaçant les caractère intermédiaires par la chaîne .... Écrire ensuite un programme qui utilise cette lambda expression pour l'appliquer à chaque mot d'une stream de chaîne de caractères et l'afficher. On fera en sorte que les mots de moins de 2 caractères sont ignorés.
Transformer une stream de chaînes de caractères en la triant suivant la longueur de ces chaînes.
Même question mais trié suivant l'ordre lexicographique.
Lisez la documentation de la classe Files et trouvez un moyen de récupérer dans une stream la suite des lignes d'un fichier texte.
Afficher les 15 premières lignes de ce fichier à partir de la stream.
Afficher la plus longue ligne.
Afficher combien de lignes contiennent le chaîne "begin".

### Des listes et tableaux aux streams

Le programme suivant remplie un tableau avec les 50 premiers carrés parfaits à l'aide d'une itération. Réécrivez ce code pour remplir le même tableau avec des carrés parfaits mais en utilisant une stream au lieu d'une itération.

```

int[] carres = new int[50];
for (int i = 0; i < carre.length; i++) {
    carres[i] = i * i;
}

```

Même question pour le calcul de la moyenne des volumes des blocs contenus dans un tableau desBlocs:

```

double total = 0;
for (Bloc bloc : desBlocs) {
    total = total + bloc.volume();
}
double average = 0;
if (desBlocs.size() > 0) { average = total / desBlocs.size(); }

```

### Suites
Écrire un programme qui, pour un entier n passé en paramètre, affiche les n premiers carrés parfaits qui sont des palindromes (tels que 4 et 121). Faites en sorte que votre programme fonctionne pour n'importe quel n!
Écrire un programme qui produit une stream infinie qui contient les factorielles 1!,2!,3!, etc. et qui affiche les n premiers (n étant un paramètre de votre programme). Indication: On produira dans un premier temps la stream [1,1!],[2,2!], etc..
Terminaisons
Étant donnée une stream de chaînes de caractères, afficher, sous forme d'une chaîne de caractères, ses n premiers éléments séparés par des virgules. Vous proposerez deux solutions:

d'abord avec la méthode collect, puis
puis avec la méthode reduce (attention: la chaîne obtenue ne doit ni
commencer ni terminer par une virgule!).

Étant donnée une stream de chaînes de caractères, afficher la liste des mots commençant par la lettre 'a' . Indication: voir la méthode Collectors.groupBy de l'API java.
Étant donnée une stream de chaînes de caractères, afficher combien de mots de chaque longueur celle-ci comporte.
Auteur: Cédric Lhoussaine

Email: cedric.lhoussaine@univ-lille.fr

Validate