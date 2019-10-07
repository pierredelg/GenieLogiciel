
Le package java.util et les collections
==============================
#TP5 / GL / LP DA2I
<br>
N.B. : avant de commencer… Vous pouvez désormais, à la place de la classe Clavier, utiliser java.util.Scanner qui peut être construit à partir de l’entrée standard (System.in) et dispose de méthodes nextLine(), nextInt(), nextDouble(), etc. Vous aurez également besoin de lire la documentation de String, Character et des classes de java.util pertinentes.
<br>

##Exercice 1: Majuscules et minuscules
Q1.
<br>
Écrivez un programme qui lit une phrase au clavier et affiche un mot sur deux en majuscules et un sur deux en minuscules (utilisez StringTokenizer). Exemple:
<br>
Entrez une phrase : Voici une bien belle phrase<br>
VOICI une BIEN belle PHRASE<br>
<br>
Rappel : majuscule et minuscule se disent respectivement uppercase et lowercase en anglais.<br>

##Exercice 2: Tri
Q2.
<br>
Écrivez un programme qui lit une phrase au clavier, stocke chacun des mots dans une ArrayList, trie cette liste dans l’ordre lexicographique (du code ASCII) et affiche son contenu. Vous pourrez utiliser la méthode statique sort de la classe java.util.Collections pour trier (attention, ne pas confondre la classe Collections et l’interface Collection!). Exemple:
<br>
Entrez une phrase : Voici une bien belle phrase<br>
Voici belle bien phrase une<br>
<br>

##Exercice 3: Renversements<br>
Q3.
<br>
Écrivez un programme qui lit une phrase au clavier et affiche ses lettres en majuscules, en ne gardant que les caractères qui sont des lettres. Vous stockerez les caractères d’abord dans l’ordre (avec ArrayList), puis dans l’ordre inverse (avec Stack). Exemple :<br>
<code>
Entrez une phrase : Voici une bien belle phrase<br>
VOICIUNEBIENBELLEPHRASE<br>
ESARHPELLEBNEIBENUICIOV<br>
</code>
Écrivez ensuite un programme qui dit si une phrase est un palindrome (un palindrome est une phrase qui se lit de gauche à droite ou de droite à gauche indifféremment, par exemple : Esope reste ici et se repose).<br>
Q4.
<br>
Écrivez un programme qui lit une phrase au clavier et affiche ses mots en ordre inverse (avec Stack). Exemple:<br>
<br>
Entrez une phrase : Voici une bien belle phrase<br>
phrase belle bien une Voici<br>
<br>
Q5.
<br>
Écrivez un programme qui lit une phrase au clavier et affiche ses mots dans l’ordre, en inversant les lettres de chaque mot.<br>
Exemple:<br>

Entrez une phrase : Voici une bien belle phrase<br>
icioV enu neib elleb esarhp<br>
<br>
##Exercice 4: Recensements
Q6.
<br>
Écrivez un programme qui lit une chaîne et affiche la liste des caractères qui sont présents dans cette chaîne. Utilisez pour cela uniquement la classe BitSet. Exemple:<br>
<br>
Entrez une phrase : Voici une bien belle phrase.<br>
. V a b c e h i l n o p r s u<br>
<br>
Q7.
<br>
Faites de même avec la classe HashSet qui représente un ensemble d’objets (la comparaison entre les éléments est l’égalité logique).<br>
Q8.
<br>
Écrivez un programme qui lit une chaîne et compte les caractères présents dans cette chaîne, en utilisant une HashMap (dictionnaire) qui associera à chaque caractère son nombre d’occurrences. On rappelle que HashMap n’est pas une collection, mais que ses méthodes keySet() et values() retournent des collections.<br>

##Exercice 5: Anagrammes
<br>
Un mot est l’anagramme d’un autre (et réciproquement) s’il possède les mêmes lettres en même nombre. Par exemple: niche, chien et Chine sont des anagrammes.<br>
Q9.
<br>
Écrivez un programme qui teste si deux mots passés en argument sur la ligne de commande sont anagrammes l’un de l’autre. Vous utiliserez pour cela la classe HashMap pour compter combien de fois chaque lettre apparaît dans un mot.<br>

##Exercice 6: Itérateurs
<br>
La classe implémentant l’interface Iterator pour la classe ArrayList n’est pas une classe publique du package java.util: on ne peut la manipuler que par les méthodes de son interface. Toutefois, rien n’empêche d’écrire son propre itérateur pour parcourir une ArrayList.<br>
Q10.
<br>
Écrivez une classe IteratorPourArrayList implémentant Iterator, qui fonctionne de la même façon que l’objet renvoyé par la méthode iterator() de ArrayList (sans s’en servir !). En voici un exemple d’utilisation :<br>
<code>
ArrayList l = new ArrayList() ;<br>
l.add(5) ; l.add(4) ; l.add("hello") ;<br>
for (int i=1; i<=2; i++) {<br>
    Iterator it = new IteratorPourArrayList(l) ;<br>
    System.out.println("Parcours numero "+i) ;<br>
    while (it.hasNext()) {<br>
        Object o = it.next() ;<br>
        System.out.println(o) ;<br>
        if (o.equals(4)) {<br>
            it.remove() ;<br>
            System.out.println("Je l’efface !") ;<br>
        }<br>
    }<br>
}<br>
</code>
Résultat:<br>
<code>
Parcours numero 1<br>
5<br>
4<br>
Je l’efface !<br>
hello<br>
Parcours numero 2<br>
5<br>
hello<br>
</code>
Q11.
<br>
Les itérateurs ne sont pas nécessairement liés à des collections. Écrivez, sur le modèle précédent, une classe BitSetIterator permettant de parcourir les indices associés à true dans un BitSet, par exemple de la façon suivante:<br>
<code>
BitSet b = new BitSet() ;<br>
int fib1=1, fib2=1 ;<br>
for (int i=0; i<5; i++) {<br>
    fib2 = fib1 + fib2 ;<br>
    fib1 = fib2 - fib1;<br>
    b.set(fib2) ;<br>
}<br>
for (int i=1; i<=2; i++) {<br>
    Iterator<Integer> it = new BitSetIterator(b) ;<br>
    System.out.println("Parcours numero "+i) ;<br>
    while (it.hasNext()) {<br>
        int i = it.next() ;<br>
        System.out.println(i) ;<br>
        if (i == 8) {<br>
            it.remove() ;<br>
            System.out.println("Je l’efface !") ;<br>
        }<br>
    }<br>
}<br>
</code>
<br>

Résultat:<br>
<code>
Parcours numero 1<br>
2<br>
3<br>
5<br>
8<br>
Je l’efface !<br>
13<br><br>
Parcours numero 2<br>
2<br>
3<br>
5<br>
13<br>
</code>
Auteur: Sébastien Picault

Email: cedric.lhoussaine@univ-lille.fr

Validate

