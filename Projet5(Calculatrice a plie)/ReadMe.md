#Une calculatrice (à pile !)
##Projet 5 / GL / LP DA2I / semestre 1
Pour ces exercices, partez des fichiers Operation.java et Test.java fournis dans pr5.tgz.

###Définition des opérations
Le fichier Operation.java contient une énumération: on rappelle qu’il s’agit d’une classe particulière qui possède un nombre limité d’instances, identifiées par leur nom. Le fichier Test.java permet d’afficher la liste de ces instances, obtenue via la méthode statique values() qui retourne un objet implémentant l’interface Iterable.

###Q1.
Lisez le code des deux fichiers, en notant la syntaxe utilisée pour le for. Compilez et exécutez Test.java.

###Q2.
On peut associer aux instances d’une énumération des attributs (constants) qui sont initialisés par un constructeur. Ajoutez à Operation un attribut private final String code_operation, un constructeur qui permette de donner une valeur à cet attribut, et modifiez la déclaration des instances en associant à chaque opération un symbole, par exemple en remplaçant PLUS par PLUS("+").

###Q3.
Redéfinissez la méthode toString() de Operation pour qu’elle affiche maintenant la valeur du code_operation.

###Q4.
On veut maintenant écrire dans Operation une méthode public double eval(double x, double y) qui, selon la nature de l’opération, effectue une addition, une soustraction, une multiplication ou une division entre x et y. Modifiez ensuite Test pour qu’il affiche le résultat de chaque opération appliquée aux nombres passés sur la ligne de commande, comme indiqué ici:

````

travail$ java Test 4 5
4.0 + 5.0 = 9.0
4.0 - 5.0 = -1.0
4.0 * 5.0 = 20.0
4.0 / 5.0 = 0.8

````
###Q5.
Ajoutez une opération PUISS("ˆ") qui calcule xy.

###Q6.
Modifiez la classe Test pour qu’elle lise sur la ligne de commande trois éléments (un nombre, un symbole d’opération et un nombre) et effectue le calcul demandé. Exemple (évidemment sous unix le symbole * doit être "échappé"):

````

travail$ java -cp classes/ Test 4 - 5
4.0 - 5.0 = -1.0
travail$ java -cp classes/ Test 4 "*" 5
4.0 * 5.0 = 20.0
Réalisation d’une calculatrice (pattern Commande)
Nous allons maintenant écrire une calculatrice miniature fonctionnant avec une pile, en notation "polonaise", c’est-à-dire postfixée. Ainsi, pour calculer l’expression (4∗5)/(3+2), on écrira : 2 3 + 5 4 * /. Les seuls mots (tokens) possibles sur la ligne de calcul sont donc soit des nombres, soit des symboles représentants les opérations connues. Un nombre est empilé, tandis qu’une opération a pour effet de dépiler deux nombres x et y, puis d’empiler le résultat de eval(x,y).

````

###Q7.
Écrivez une classe Calculatrice dans le package calculatrice, possédant les attributs suivants:

> * Stack<Double> resultat: la pile de nombres servant à faire le calcul.  
> * HashMap<String,Operation> operations un dictionnaire permettant de retrouver l’opération à partir de la chaîne de caractères qui la représente (son code_operation).  
> * Le constructeur (sans paramètres) de la calculatrice devra initialiser ces attributs de façon judicieuse.  

###Q8.
Écrivez dans Calculatrice une méthode public double calculer(String s) qui prend en paramètre une expression postfixée et retourne sa valeur. L’algorithme est le suivant:

 1) découper la chaîne en tokens
 2) pour chaque token:
 3) si c’est un symbole d’opération (i.e. s’il est dans le dictionnaire), dépiler les deux opérandes (x et y), faire le calcul et empiler le résultat;
 4) sinon, empiler la valeur numérique correspondant au token.
 5) lorsqu’il n’y a plus de tokens, le résultat est normalement au sommet de la pile.  
 
###Q9.
Testez votre calculatrice au moyen d’une classe Calculer dont la méthode main fait autant de calculs qu’il y a d’expressions sur la ligne de commande. Exemple:

````

travail$ java Calculer 32 "2 3 /" "2 3 + 5 4 * /" "3 2 ^"
32 = 32.0
2 3 / = 1.5
2 3 + 5 4 * / = 4.0
3 2 ^ = 8.0

```` 
   
Prise en compte de l’arité des opérations
Certaines opérations ne nécessitent pas deux opérandes : par exemple la racine carrée n’en requiert qu’une seule. Nous allons créer une deuxième version de notre calculatrice pour tenir compte de ces possibilités.

Avant de poursuivre, dupliquez votre package calculatrice en calculatrice2. Désormais nous travaillerons avec les classes de ce 2e package.

###Q10.
Modifiez Operation de façon à introduire l’arité des opérations, c’est-à-dire le nombre d’opérandes qu’elles exigent pour le calcul. Il faut alors modifier la signature de eval en prenant comme paramètre: double [] operandes. Modifiez en conséquence Calculatrice de façon à traiter des opérations d’arité quelconque. Vérifiez que tout marche comme avant.

###Q11.
Ajoutez maintenant les opérations SQRT("V", 1) correspondant à la racine carrée, et ABS("ABS", 1) qui calcule la valeur absolue. Voici un exemple d’exécution:

````
travail$ java Calculer2 "3 2 - ABS" "4 5 5 * * V" "2 5 V 1 + /"
3 2 - ABS = 1.0
4 5 5 * * V = 10.0
2 5 V 1 + / = 1.618033988749895    

````

###Q12.
Ajoutez maintenant une opération NOT("NOT", 1) qui transforme un nombre non nul en zéro, et zéro en 1, ainsi que IF("IF", 3) qui utilise trois opérandes x, y et z. Si x est non nul (signifiant true), on empile y, sinon z. Exemple d’exécution :

````
travail$ java Calculer2 "3 NOT NOT" "-1 1 5 6 * 30 - IF" "-1 1 5 6 * 40 - IF"
3 NOT NOT = 1.0
-1 1 5 6 * 30 - IF = -1.0
-1 1 5 6 * 40 - IF = 1.0    

````

###Instructions supplémentaires
On souhaite maintenant prendre en compte des instructions plus complexes qui peuvent manipuler directement la pile et pas simplement effectuer des calculs sur son contenu. En particulier on souhaite implémenter les instructions "classiques" suivantes:

DROP: dépile le sommet sans l’utiliser (poubelle)
DUP: duplique le sommet de la pile
SWAP: permute les deux éléments du sommet
COUNT: calcule le nombre d’éléments présents dans la pile et empile la valeur correspondante.
Avant de poursuivre, dupliquez votre package calculatrice2 en calculatrice3. Désormais nous travaillerons avec les classes de ce 3e package.

###Q13.

Ajoutez dans Operation un constructeur sans paramètres qui initialise code_operation automatiquement et arite à zéro.  
Ajoutez les quatre instructions mentionnées ci-dessus en utilisant ce nouveau constructeur.

###Q14.

Ajoutez dans Operation une méthode execute(Stack<Double> pile) qui effectue les opérations suivantes:

> * si l’arité de l’opération n’est pas nulle, 
>  * elle dépile le bon nombre d’opérandes, 
>  * les place dans un tableau, 
>  * et empile le résultat de la méthode eval, comme auparavant  
> * sinon, l’opération exécute les manipulations appropriées sur pile.

###Q15.
Modifiez Calculatrice pour prendre en compte ce nouveau fonctionnement (autrement dit, remplacez l’utilisation de eval par un appel à execute). Exemple:

````

$ java Calculer3 "1 COUNT COUNT COUNT COUNT * * * *" "COUNT COUNT SWAP DROP 5 SWAP /" "1 DUP DUP + SWAP 5 V + /"
1 COUNT COUNT COUNT COUNT * * * * = 24.0
COUNT COUNT SWAP DROP 5 SWAP / = 0.2
1 DUP DUP + SWAP 5 V + / = 1.618033988749895    

````

###Q16. QUESTION SUBSIDIAIRE
Ajoutez dans Calculatrice des tests et la levée d’une CalculatriceException pour signaler les diverses erreurs pouvant se produire si l’expression est incorrecte (pas assez d’arguments, opération inconnue, nombres en trop dans la pile de calcul, etc.).

Auteur: Sébastien Picault

Emacs 25.3.1 (Org mode 9.1.1)