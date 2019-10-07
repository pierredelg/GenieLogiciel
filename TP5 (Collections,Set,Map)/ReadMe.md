
# Le package java.util et les collections

## TP5 / GL / LP DA2I
<br>
N.B. : avant de commencer… Vous pouvez désormais, à la place de la classe Clavier, utiliser java.util.Scanner qui peut être construit à partir de l’entrée standard (System.in) et dispose de méthodes nextLine(), nextInt(), nextDouble(), etc. Vous aurez également besoin de lire la documentation de String, Character et des classes de java.util pertinentes.
<br>

### Exercice 1: Majuscules et minuscules
Q1.
<br>
Écrivez un programme qui lit une phrase au clavier et affiche un mot sur deux en majuscules et un sur deux en minuscules (utilisez StringTokenizer). Exemple:

> Entrez une phrase : Voici une bien belle phrase
> VOICI une BIEN belle PHRASE

Rappel : majuscule et minuscule se disent respectivement uppercase et lowercase en anglais.<br>

### Exercice 2: Tri
Q2.
<br>
Écrivez un programme qui lit une phrase au clavier, stocke chacun des mots dans une ArrayList, trie cette liste dans l’ordre lexicographique (du code ASCII) et affiche son contenu. Vous pourrez utiliser la méthode statique sort de la classe java.util.Collections pour trier (attention, ne pas confondre la classe Collections et l’interface Collection!). Exemple:

> Entrez une phrase : Voici une bien belle phrase
> Voici belle bien phrase une

### Exercice 3: Renversements
Q3.
<br>
Écrivez un programme qui lit une phrase au clavier et affiche ses lettres en majuscules, en ne gardant que les caractères qui sont des lettres. Vous stockerez les caractères d’abord dans l’ordre (avec ArrayList), puis dans l’ordre inverse (avec Stack). Exemple :<br>

> Entrez une phrase : Voici une bien belle phrase
> VOICIUNEBIENBELLEPHRASE
> ESARHPELLEBNEIBENUICIOV

Écrivez ensuite un programme qui dit si une phrase est un palindrome (un palindrome est une phrase qui se lit de gauche à droite ou de droite à gauche indifféremment, par exemple : Esope reste ici et se repose).<br>
Q4.
<br>
Écrivez un programme qui lit une phrase au clavier et affiche ses mots en ordre inverse (avec Stack). Exemple:<br>
<br>
> Entrez une phrase : Voici une bien belle phrase
> phrase belle bien une Voici
<br>
Q5.
<br>
Écrivez un programme qui lit une phrase au clavier et affiche ses mots dans l’ordre, en inversant les lettres de chaque mot.<br>
Exemple:<br>

> Entrez une phrase : Voici une bien belle phrase
> icioV enu neib elleb esarhp

### Exercice 4: Recensements
Q6.
<br>
Écrivez un programme qui lit une chaîne et affiche la liste des caractères qui sont présents dans cette chaîne. Utilisez pour cela uniquement la classe BitSet. Exemple:<br>
<br>

> Entrez une phrase : Voici une bien belle phrase.<br>
> . V a b c e h i l n o p r s u<br>

<br>
Q7.
<br>
Faites de même avec la classe HashSet qui représente un ensemble d’objets (la comparaison entre les éléments est l’égalité logique).<br>

Q8.
<br>
Écrivez un programme qui lit une chaîne et compte les caractères présents dans cette chaîne, en utilisant une HashMap (dictionnaire) qui associera à chaque caractère son nombre d’occurrences. On rappelle que HashMap n’est pas une collection, mais que ses méthodes keySet() et values() retournent des collections.<br>

### Exercice 5: Anagrammes
<br>
Un mot est l’anagramme d’un autre (et réciproquement) s’il possède les mêmes lettres en même nombre. Par exemple: niche, chien et Chine sont des anagrammes.<br>

Q9.
<br>
Écrivez un programme qui teste si deux mots passés en argument sur la ligne de commande sont anagrammes l’un de l’autre. Vous utiliserez pour cela la classe HashMap pour compter combien de fois chaque lettre apparaît dans un mot.<br>

### Exercice 6: Itérateurs
<br>
La classe implémentant l’interface Iterator pour la classe ArrayList n’est pas une classe publique du package java.util: on ne peut la manipuler que par les méthodes de son interface. Toutefois, rien n’empêche d’écrire son propre itérateur pour parcourir une ArrayList.<br>

Q10.
<br>
Écrivez une classe IteratorPourArrayList implémentant Iterator, qui fonctionne de la même façon que l’objet renvoyé par la méthode iterator() de ArrayList (sans s’en servir !). En voici un exemple d’utilisation :<br>

```
ArrayList l = new ArrayList() ;
l.add(5) ; l.add(4) ; l.add("hello") ;
for (int i=1; i<=2; i++) {
    Iterator it = new IteratorPourArrayList(l) ;
    System.out.println("Parcours numero "+i) ;
    while (it.hasNext()) {
        Object o = it.next() ;
        System.out.println(o) ;
        if (o.equals(4)) {
            it.remove() ;
            System.out.println("Je l’efface !") ;
        }
    }
}
```
Résultat:

Parcours numero 1

> 5
> 4
> Je l’efface !
> hello
> Parcours numero 2
> 5
> hello

Q11.
<br>
Les itérateurs ne sont pas nécessairement liés à des collections. Écrivez, sur le modèle précédent, une classe BitSetIterator permettant de parcourir les indices associés à true dans un BitSet, par exemple de la façon suivante:<br>

```
BitSet b = new BitSet() ;
int fib1=1, fib2=1 ;
for (int i=0; i<5; i++) {
    fib2 = fib1 + fib2 ;
    fib1 = fib2 - fib1;
    b.set(fib2) ;
}
for (int i=1; i<=2; i++) {
    Iterator<Integer> it = new BitSetIterator(b) ;
    System.out.println("Parcours numero "+i) ;
    while (it.hasNext()) {
        int i = it.next() ;
        System.out.println(i) ;
        if (i == 8) {
            it.remove() ;
            System.out.println("Je l’efface !") ;
        }
    }
}
```
<br>

Résultat:<br>
<br>
Parcours numero 1<br>

> 2
> 3
> 5
> 8
> Je l’efface !
> 13

Parcours numero 2

> 2
> 3
> 5
> 13

Auteur: Sébastien Picault

Email: cedric.lhoussaine@univ-lille.fr

Validate

