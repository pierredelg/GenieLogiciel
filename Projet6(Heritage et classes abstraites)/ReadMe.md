# Héritage et classes abstraites
## Projet 6 / GL / LP DA2I / semestre 1
## Réécriture de classes existantes
### Héritage et réutilisation de code : un cas concret
###Q1.
Comparez attentivement le code des classes ImageTab et ImageDict (projet 4) tel qu’il vous est donné dans la correction. Regroupez tout le code commun dans une classe ImageQuelconque. Faites ensuite dériver ImageTab et ImageDict de ImageQuelconque (en vérifiant que tout fonctionne). ImageQuelconque doit-elle être abstraite ou concrète ? Pourquoi ?

### Q2.
Modifiez ImageDoubleDict en la faisant dériver de ImageQuelconque. Comparez ensuite le code de ImageDict et ImageDoubleDict, et créez une superclasse pour les deux si c’est possible.

## Gestion des exceptions
Jusqu’à présent, lorsqu’on cherchait à faire des opérations entre images de dimensions incompatibles, on renvoyait une valeur par défaut (par exemple null ou une image blanche). Le mieux dans ce genre de situations est de faire appel aux exceptions pour signaler le problème et interrompre le traitement normal.

### Q3.

Dans le package image modifié précédemment, ajoutez des mécanismes de levée et de capture d’exceptions pour les cas où l’on cherche à faire des opérations entre images incompatibles. Vous devez pour cela:

définir une exception ImagesIncompatiblesException dans le package image;
modifier la signature des méthodes concernées dans l’interface ImageGrise (le contrat est modifié puisqu’on décide de déclencher des exceptions en cas de problème plutôt que de renvoyer des valeurs par défaut);
modifier la signature et le code des méthodes concernées dans les classes qui réalisent l’implémentation;
prévoir des blocs de capture (try { ... } catch (...) { ... }) lors de l’appel de ces méthodes dans d’autres parties de votre application.
Le cas étrange du Phénix
Dans tout ce qui suit, on part de la hiérarchie de classes d’animaux donnée dans l'archive sur moodle. Le phénix est un animal imaginaire qui est le seul de son espèce (lorsqu’il se sent vieux, il s’immole par le feu et renaît de ses cendres). Hérodote, brillant élève de DA2I, a écrit la classe Phenix de la façon suivante:

```

public class Phenix extends Animal {
    private static Phenix le_seul_phenix;

    public static Phenix uniqueInstance() {
        if (le_seul_phenix == null)
            le_seul_phenix = new Phenix();
        return le_seul_phenix;
    }

    public Phenix(String nom) {
        super(nom);
    }

    public Phenix() {
        super("Le Phenix");
    }

    public String toString() {
        return super.toString() + " Je suis le SEUL phenix.";
    }
}  

```

Il prétend qu’il suffit d’utiliser la méthode de classe uniqueInstance() au lieu de new Phenix() pour instancier une seule fois cette classe.

### Q4.
Que se passe-t-il si l’on écrit le code suivant ? Hérodote a-t-il raison ?

Animal p1 = Phenix.uniqueInstance();
Animal p2 = Phenix.uniqueInstance();
System.out.println(p1);
System.out.println(p2);
System.out.println("C’est bien le même phénix : " + (p1 == p2));    
### Q5.
Pline, un ancien élève de DA2I, remarque qu’on peut toujours instancier la classe Phenix avec new, par exemple en écrivant:

Animal p1 = Phenix.uniqueInstance();
Animal p2 = new Phenix("Albert");
Que se passe-t-il alors lorsqu’on cherche à exécuter le même code de test que précédemment ?

### Q6.
Trouvez un (ou plusieurs) mécanisme(s) qui garantisse(nt) l’unicité du phénix dans tous les cas d’utilisation imaginables.

## Gestion de salaires
Dans une entreprise, il existe diverses catégories d’employés dont la rémunération hebdomadaire Sh est calculée de la façon suivante:

la plupart sont payés en fonction du nombre d’heures de travail par semaine: ils doivent effectuer 35h (payées à un certain taux horaire), et lorsqu’ils travaillent plus, les heures supplémentaires sont payées 25% au-dessus du taux horaire:  
	Sh=H∗τ si H≤35 (τ : taux horaire)  
	Sh=35∗τ+(H−35)∗τ∗1.25 sinon  
	certains employés sont soumis aux mêmes règles, mais leurs heures supplémentaires sont payées 40% au-dessus du taux horaire:  
	Sh=H∗τ si H≤35 (τ : taux horaire)  
	Sh=35∗τ+(H−35)∗τ∗1.40 sinon  
les commerciaux reçoivent chaque semaine une somme fixe, plus une prime représentant 1% du chiffre d’affaires qu’ils ont réalisé dans la semaine: Sh=Sfixe+C/100 (C: chiffre d’affaires de la semaine pour le commercial)

## Résolution du problème sans héritage

On a déjà écrit une des classes, dont on donne le code ci-dessous:

```

package employesSansHeritage ;
public class EmployeNormal {
    private double temps_travail, taux_horaire, majoration, heures ;
    private String nom ;
    public EmployeNormal(String nom) {
        temps_travail = 35;
        taux_horaire = 7.5;
        majoration = 1.25;
        this.nom = nom;
    }

    public void setTravail(double heures) {
        this.heures = heures;
    }

    public double salaireHebdo() {
        if (heures > temps_travail)
            return temps_travail * taux_horaire
                + (heures - temps_travail) * taux_horaire * majoration;
        return heures * taux_horaire;
    }

    public String toString() {
        return nom;
    }
}

```

### Q7.
Écrire sur ce modèle deux autres classes, EmployeMieuxPaye et Commercial, représentant respectivement chacune des deux autres catégories ci-dessus. Chacune devra posséder les méthodes public double salaireHebdo() et public void setTravail(double x) où x représente soit le nombre d’heures de travail de la semaine (pour les deux premières catégories d’employés), soit le chiffre d’affaires (pour Commercial).

### Q8.
Dans le code que vous avez écrit, y a-t-il des classes dont les méthodes et/ou les attributs sont identiques ? Comment peut-on éviter ces redondances ?

## Introduction de l’héritage
Les classes suivantes seront implantées dans le package employesAvecHeritage.

### Q9.
Écrivez une classe EmployeAvecHSup qui regroupe le code commun à EmployeNormal et EmployeMieuxPaye.

### Q10.
Récrivez maintentant les classes EmployeNormal et EmployeMieuxPaye en les faisant dériver de EmployeAvecHSup.

### Q11.
Écrivez maintenant une classe EmployeQuelconque qui regroupe le code commun à EmployeAvecHSup et Commercial.

### Q12.
Récrivez les classes EmployeAvecHSup, et Commercial pour les faire dériver de EmployeQuelconque.

### Q13.
Comment peut-on faire en sorte que EmployeQuelconque possède les méthodes void setTravail(double x) et double salaireHebdo() alors que le code de ces méthodes est différent dans EmployeAvecHSup et Commercial ?

### Q14.
Est-il judicieux que l’attribut temps_travail soit une variable d’instance ? Et les autres ?

## Le Directeur
On veut représenter le directeur de l’entreprise. Il fonctionne un peu comme le phénix: il doit être la seule instance de la classe Directeur. On veut accéder à cette instance avec une méthode de classe getDirecteur(). Son salaire hebdomadaire est calculé à partir d’une part fixe et de 0, 4% du chiffre d’affaires total réalisé par les commerciaux.

### Q12.
De quelle classe peut-on faire dériver Directeur ?

### Q13.
Que faut-il modifier dans la classe Commercial pour que le directeur «sache» quel a été le chiffre d’affaires total réalisé par ses commerciaux ? Écrivez ensuite la classe Directeur.

Auteur: Sébastien Picault

Emacs 25.2.1 (Org mode 9.1.1)
