
# Les interfaces et le polymorphisme. Réalisation d’un « Dictionnaire »  

## TP4 / GL / LP DA2I / semestre 1  

##Le dictionnaire: définition  
  
Au sens courant, un dictionnaire est un ouvrage qui associe à des mots (appelés entrées) leur définition. Par analogie, le Dictionnaire au sens informatique (appelé aussi table associative) est une structure de données constituée d’un nombre quelconque d’associations entre des clefs et des valeurs, chaque clef n’étant présente qu’une seule fois. Exemple :  
  
```
{
  "chat" → "animal quadrupède qui ronronne",
  "TIGRE" → "animal quadrupède qui ronronne",
  true → "valeur booléenne"
}
```

(le terme à gauche de la flèche est la clef, le terme à droite la valeur ).  
  
Ces clefs et ces valeurs, comme on le voit, peuvent être a priori quelconques, bien qu’en pratique on trouve souvent des types uniformes. La seule chose qu’on impose est la suivante: une clef ne doit être présente qu’une seule fois dans le Dictionnaire. Cette unicité des clefs doit être vérifiée du point de vue de l’égalité logique (contenu des objets, vérifiée par la méthode equals).  
  
Le comportement général d’un dictionnaire peut donc être résumé par l’interface suivante :
  
```
/** L’interface Dictionnaire décrit les objets capables de stocker un ensemble d’associations
 * (clef, valeur), chaque clef n’étant présente qu’une seule fois. À noter : cette interface
 * utilise deux types génériques K et V pour le type des clefs et les valeurs respectivement.
 */
public interface Dictionnaire<K,V> {
    /** Teste si le dictionnaire ne contient aucune association */
    boolean estVide();
    /** Teste si le dictionnaire contient l’association assoc */
    boolean contient(Couple <K,V> assoc);
    /** Teste si le dictionnaire possède une association de clef c */
    boolean contientClef(K c);
    /** Teste si le dictionnaire possède une association de valeur v */
    boolean contientValeur(V v);
    /** Retourne le nombre d’associations du dictionnaire */
    int nbElements();
    /** Retourne l’association correspondant à la clef c spécifiée, null si absente */
    Couple<K,V> assocPour(K c);
    /** Retourne la valeur associée à le clef c, null si absente */
    V valeurPour(K c);
    /** Ajoute l’association assoc au dictionnaire (remplacement si clef présente) */
    void ajouter(Couple<K,V> assoc);
    /** Définit ou modifie la valeur v associée à la clef c dans le dictionnaire */
    void ajouter(K c, V v);
    /** Enlève l’association assoc du dictionnaire (si présente) */
    void enlever(Couple<K,V> assoc);
    /** Enlève l’entrée de clef c (si présente) */
    void enleverPour(K c);
}
```

On donne par ailleurs l’interface Couple qui va nous servir pour construire les associations clefs-valeurs du dictionnaire:  
  
```
/** L’interface Couple permet de caractériser tout objet qui contient des paires
* de valeurs, la première du type X, la seconde du type Y */
public interface Couple<X,Y> {
    X premier();
    Y second();
    void defPremier(X x);
    void defSecond(Y y);
    boolean equals(Object o);
}
```
  
## Exercices  
  
Dans ce qui suit, les fichiers Couple.java et Dictionnaire.java sont fournis sur Moodle (dans tp4.tgz).  
  
### Q1.  
  
Écrivez et testez une classe générique CoupleObj<K,V> qui implémente Couple<K,V>. 

### Q2.  
  
On veut maintenant réaliser une classe générique qui implémente Dictionnaire<K,V> au moyen d’un tableau de couples. Pour cela, écrivez d’abord une classe TabDict qui possède au moins les attributs et les méthodes (privés) suivants (cf. le fichier Squelette.java fourni):  

    Couple<K,V>[] associations: le tableau contenant toutes les associations;  
    int nbAssoc: le nombre d’associations effectivement présentes dans le tableau;  
    void resize(): remplace le tableau des associations par un nouveau tableau, de taille double, après avoir recopié dans ce dernier toutes les associations déjà mémorisées (en effet, comme un dictionnaire peut contenir un nombre quelconque d’éléments, il peut arriver, au cours des ajouts successifs, que le tableau devienne trop petit; il faut alors l’agrandir);  
    int indexOf(Couple<K,V> assoc): renvoie la première position de assoc dans le tableau si elle est présente, -1 sinon;
    int indexOfClef(K clef): renvoie la première position de l’association de clef clef dans le tableau si elle est présente, -1 sinon;  
    void add(Couple<K,V> assoc): ajoute assoc à la première position libre dans le tableau;  
    void remove(int i): enlève l’association stockée à l’indice i;  
    évidemment un constructeur et une méthode toString.  
  
Testez chacune des méthodes ci-dessus avant de continuer!  

### Q3.  
  
Ensuite, ajoutez à la déclaration de la classe TabDict la mention implements Dictionnaire<K,V> et écrivez les méthodes nécessaires, en vous appuyant sur celles définies ci-dessus comme «briques de base» (les méthodes publiques doivent se servir des méthodes privées plutôt que des attributs chaque fois que c’est possible).  
  
### Q4.  
  
Testez TabDict. En utilisant la classe TestDict fournie, on doit notamment obtenir un affichage de ce genre:  
  
> Un entier -> sa racine carrée
> 1 -> lui-même
> 4 -> 2
> 9 -> 3
> 16 -> 4
> 25 -> 5
> 36 -> 6
> 2 -> sqrt(2)
> 64 -> 8
> 81 -> 9
> 100 -> 10
  
### Q5.  
  
On souhaite maintenant que TabDict implémente en plus l’interface Iterable<K>, de façon à fournir un itérateur qui permette de parcourir l’ensemble des clefs. Décommentez le code de la méthode iterator() et écrivez une classe DictIterator, implémentant Iterator<K>, permettant de parcourir les clefs et d’en enlever du dictionnaire (Subtilité : cette classe doit évidemment garder une référence sur le dictionnaire qu’elle permet de parcourir, elle doit donc avoir un attribut dict. Mais elle ne dépend que d’un seul type générique K alors que Dictionnaire dépend de K et V. Pour résoudre ce problème on utilisera un attribut de type Dictionnaire<K,?> pour indiquer que, bien que le dictionnaire dépende de deux types génériques, seul le premier sera utilisé au sein de DictIterator). 

### Q6.  

Testez votre itérateur notamment en décommentant le code correspondant dans TestDict.  

### Q7.  
  
On souhaite maintenant regrouper dans un même package tout ce qui concerne le dictionnaire, à savoir:  

    * les interfaces Dictionnaire et Couple
    * les classes CoupleObj, TabDict et DictIterator
  
Créez un sous-répertoire dictionnaire de sources, placez-y les fichiers concernés après avoir rajouté en tête de chacun la mention suivante:  
  
> package dictionnaire;  
  
Modifiez ensuite les autres classes (tests notamment) pour qu’elles puissent fonctionner comme auparavant (attention: elles doivent rester dans sources). Qu’observe-t-on dans le répertoire classes ?  

### Q8.  
  
Déplacez le fichier package.html dans sources/dictionnaire, modifiez-le pour décrire le contenu du package, puis générez la documentation de tout le package grâce à la commande:  
  
> javadoc -sourcepath sources -d doc sources/*.java dictionnaire  

Qu’observe-t-on dans le répertoire doc ? Et dans les pages web ? Que peut-on dire des classes qui ne sont pas dans le package dictionnaire ? La classe DictIterator doit-elle être publique ? Pourquoi ?  
  
Auteur: Sébastien Picault

Email: cedric.lhoussaine@univ-lille.fr

Validate
