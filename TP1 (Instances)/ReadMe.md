
# TP 1 : Prise en main des outils Java sous Linux Classes et instances.

## TP1 / GL / LP DA2I / semestre 1

Le but de cette séance de TP est de vous familiariser avec les outils de programmation en Java disponibles sous Linux : compilateur, interpréteur, documentation automatique. Vous apprendrez également à ranger vos fichiers dans les répertoires adéquats, à utiliser un éditeur digne de ce nom (Emacs ou VI) et à consulter l’API.  
  
### Environnement  
  
La plate-forme J2SDK fournit, entre autres, les outils suivants :  
  
    javac : le compilateur
    java : l’interpréteur ou machine virtuelle
    javadoc : l’utilitaire de génération de documentation
  
N’oubliez pas qu’une aide est toujours disponible pour chacune de ces commandes au moyen de la commande man:
  
    $ man javac
  
par exemple. Vous y trouverez le détail d’utilisation des options dont seules quelques-unes seront présentées ici.  
  
Pour éditer vos programmes sources, il est très fortement conseillé d’utiliser emacs (ou vi). Vous pourrez vous reporter au résumé des racourcis clavier ci-joint.  
  
Enfin, pour consulter la documentation de vos classes ou l’API Java, vous pouvez utiliser un navigateur web de votre choix.  
  
Rappel: Les applications écrites en Java sont « byte-compilées », c’est-à-dire que le compilateur produit du code portable (non spécifique à une machine ou à un système d’exploitation), le byte code. Celui-ci (contenu dans les fichiers .class) ne peut être exécuté directement, il faut l’interpréter au moyen d’une machine virtuelle:  

    Code source−→−−−−−−CompilateurByte code−→−−−−−−InterpréteurRésultat  

Soit en pratique en Java:  

    Code .java−→−−javacfichier .class−→−javaRésultat  
  
### Mise en pratique  
  
Créez un répertoire travail et des sous-répertoires sources, doc et classes.  
  
le répertoire sources est celui dans lequel seront placés les fichiers `*.java`;lors de la compilation, toutes les classes (fichiers `*.class`) seront placées dans le répertoire classes;enfin, la documentation générée automatiquement sera stockée dans doc.
  
### Exercices  
  
Partez de la classe Date ci-dessous:   
  
```
public class Date
{
    // les attributs des instances
    private int jour, mois, annee ;
    // les attributs de la classe
    public static final String [] NOM_DES_MOIS =
    { "Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet",
      "Aout", "Septembre", "Octobre", "Novembre", "Decembre" } ;
    public Date(int jour, int mois, int annee)
    {
        this.jour = jour ;
        this.mois = mois ;
        this.annee = annee ;
    }
    public String toString()
    {
        return jour + " " + NOM_DES_MOIS[mois-1] + " " + annee ;
    }
}
```
  
Tous les fichiers `*.java` doivent être placés dans le répertoire sources, de sorte qu’on doit avoir avant compilation l’arborescence suivante:  
  
>    travail  
>    |-- classes  
>    |-- doc  
>    ‘-- sources  
>        |-- Clavier.java  
>        ‘-- Date.java  
   
#### TODO Ajoutez une méthode  
```
    public static void main(String [] arg);
```
  
qui lit trois entiers au clavier et affiche la date correspondante en toutes lettres. Vous utiliserez pour cela la méthode public static int readInt() de la classe Clavier.  

#### TODO Compilation
  
Avant de compiler, lisez la page de manuel de javac. Vous aurez besoin des options suivantes:  
  
    -sourcepath <emplacement des sources>
    -d <répertoire de destination>
    -classpath <emplacement du byte-code>
  
si vous avez besoin de classes dont vous n’avez pas les sources (seulement le fichier `*.class`).  
  
Compilez ensuite en faisant en sorte de placer automatiquement les fichiers `*.class` dans le répertoire classes.  
  
#### TODO Exécution  
  
Avant d’exécuter, lisez la page de manuel de java et trouvez le moyen de lancer l’application (classe Date) à partir du répertoire travail. Vous aurez besoin de l’option `-classpath <emplacement du byte-code>`.  
  
#### TODO Documentation  

J2SDK autorise un format particulier de commentaire qui permet de générer automatiquement, à partir du code source, une documentation hypertexte au format HTML. Il s’agit du format javadoc. Tout commentaire javadoc est placé avant l’élément à commenter: voir l’exemple d’utilisation ci-dessous.  

```
    /**
    * Ici commence la documentation de la classe Machin.
    */
    public class Machin { ...
        ...
        /** cet attribut sert à ceci et cela */
        public int unAttribut ;
        /** cette méthode sert à autre chose */
        public void faireUnCalcul() { ... }
    }
```

    Ajoutez des commentaires au format javadoc pour décrire le rôle de votre classe, de ses attributs, de ses méthodes. Lisez ensuite les pages man de javadoc et placez toute la documentation de vos classes dans le répertoire doc. L’arborescence finale doit ressembler à ceci :

    travail  
    |-- classes  
    |   |-- Clavier.class  
    |   ‘-- Date.classtravail  
    |-- doc  
    |   |-- Date.html  
    |   |-- allclasses-frame.html  
    |   |-- allclasses-noframe.html  
    |   |-- constant-values.html  
    |   |-- deprecated-list.html  
    |   |-- help-doc.html  
    |   |-- index-all.html  
    |   |-- index.html  
    |   |-- overview-tree.html  
    |   |-- package-list  
    |   |-- packages.html  
    |   ‘-- stylesheet.css  
    ‘-- sources  
        |-- Clavier.java  
        ‘-- Date.java  

#### TODO Classes et instances.

On s'intéresse maintenant à la notion de variable ou de méthode de classe, opposée à la notion de variable ou de méthode d’instance. Le paramètre de la méthode main est un tableau de chaînes de caractères correspondant aux arguments de la ligne de commande Unix (ceux situés après le nom de la classe). Modifiez le code de Date pour que l’on puisse afficher la date soit en anglais, soit en français selon l’option indiquée.

Exemple d’exécution :

    java Date -francais

saisie des entiers, puis affichage sous la forme "5 Janvier 2008" par exemple.

    java Date -anglais

saisie des entiers, puis affichage sous la forme "5 January 2008" par exemple.
  
#### TODO Objets numérotés  
  
    Écrivez maintenant une classe ObjetNumerote dont les instances ont un attribut numero qui est automatiquement déterminé lors de l’instanciation: la première instance créée reçoit le numéro 1, la deuxième le numéro 2, etc.
#### TODO Mémorisation

    Écrivez une classe InstanceMemo qui est capable de mémoriser le nombre d’instances qui ont été créées depuis le début de l’exécution et d'en afficher les 10 dernières.
  
Exemple de tests à effectuer:  
  
```
    public class Test
    {
        public static void main (String [] args)
        {
            int i ;
            System.out.println("Il y a "+InstanceMemo.nombreInstances()+
                               " instances de la classe InstanceMemo") ;
            for (i=1; i<=4; i++) new InstanceMemo() ;
            System.out.println("Il y a "+InstanceMemo.nombreInstances()+
                               " instances de la classe InstanceMemo : ") ;
            InstanceMemo.afficherInstances() ;
            for (i=1; i<=10; i++) new InstanceMemo() ;
            System.out.println("Il y a "+InstanceMemo.nombreInstances()+
                               " instances de la classe InstanceMemo : ") ;
            InstanceMemo.afficherInstances() ;
        }
    }
    ```
  
Résultat attendu:  
  
    Il y a 0 instances de la classe InstanceMemo  
    Il y a 4 instances de la classe InstanceMemo :  
    Je suis l’instance numéro 1  
    Je suis l’instance numéro 2  
    Je suis l’instance numéro 3  
    Je suis l’instance numéro 4  
    Il y a 14 instances de la classe InstanceMemo :  
    Je suis l’instance numéro 5  
    Je suis l’instance numéro 6  
    Je suis l’instance numéro 7  
    Je suis l’instance numéro 8  
    Je suis l’instance numéro 9  
    Je suis l’instance numéro 10  
    Je suis l’instance numéro 11  
    Je suis l’instance numéro 12  
    Je suis l’instance numéro 13  
    Je suis l’instance numéro 14  
  
Auteur: Sébastien Picault

Email: cedric.lhoussaine@univ-lille1.fr

Validate
