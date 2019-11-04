# Héritage et exceptions.
## TP7 / GL / LP DA2I
## Transports
On souhaite modéliser en Java le calcul des coûts de transport de marchandise (goods) comportant deux champs entier protégés (weight et volume), deux méthods publique (getWeight() et getVolume()) et un constructeur Goods(int weight, int volume).  
  
Les marchandises sont transportées sous la forme de cargaisons (shipment). Les seules fonctionnalitées publiques des cargaisons sont:  
  
* add qui permet d'ajouter une marchandise dans cette cargaison si cela est encore possible, dans le cas contraire une exception IllegalStateException est levée;  
* cost qui retourne, sous la forme d'un nombre entier d'euros, le coût total du transport de cette cargaison.  
Une cargaison est également caractérisée par la distance sur laquelle elle est transportée.  
Ce renseignement est communiqué à la construction de la cargaison sous la forme d'un nombre entier de kilomètres. On précise qu'une cargaison ne peut réunir qu'une quantité limitée de marchandises (quantity): cette quantité est exprimée soit en termes de poids (weight) soit de volume (volume), selon le type de transport utilisé. Ce dernier influe aussi sur le calcul du coût de transport de la cargaison qui, de la même façon, dépend de la quantité des marchandises de la cargaison. On distingue donc plusieurs types de cargaisons selon le moyen de transport utilisé:  
  
Type de cargaison	Quantité	Coût	Limite
Fulvial (fluvial)	poids	distance×quantite−−−−−−−√	quantite<30000
Routière (road)	poids	4×distance×quantite	quantite<38000
Aérienne (air)	volume	10×distance+4×quantite	quantite<80000
Aérienne urgente (urgent)	volume	2× coût d'une cargaison aérienne	quantite<80000
1. Identifiez les caractéristiques communes des types de transport.
2. Proposez un hiérarchie de classes réunissant les caractéristiques communes.
3. Implantez l'ensemble des classes. On veillera bien sûr à ce que l'ajout d'une marchandise ne soit possible que si sa quantité n'excède pas la quantité disponible restante. Dans le cas contraire, une exception IllegalStateException sera déclanchée.
4. Implantez une classe vous permettant de tester les classes précédentes de façon interactive.

## Scrutins électoraux
On s'intéresse à la programmation de différents modes de scrutins électoraux.

Un scrutin consiste pour un électeur à exprimer son vote par un bulletin. Pour chaque scrutin, une liste de bulletins de votes possibles est proposée. Mais un électeur a en fait la possibilité de voter ce qu'il veut: il peut donc créer ses propres bulletins. Cependant lors de l'établissement des résultats du scrutin, les bulletins qui ne correspondent pas à l'un des votes possibles sont considérés comme des votes nuls.

En plus des votes possibles, un électeur a toujours à sa disposition un vote particulier: le vote blanc, qui permet d'exprimer que l'on refuse de choisir parmi les bulletins de votes possibles proposés. On peut en fait considérer que ce vote correspond au choix d'un bulletin de vote supplémentaire particulier, appelé blanc.

Il est possible de voter tant qu'un scrutin n'est pas clos. Une fois que celui-ci est clos, et pas avant, il est possible de déterminer le résultat de ce scrutin. La phase de dépouillement consiste alors à comptabiliser pour chaque bulletin (y compris les bulletins blanc et nul) le nombre de fois où il a été choisi. Le calcul du résultat, et notamment du vainqueur, dépend du type de scrutin. Dans ce sujet 2 modes de scrutin seront considérés:

le scrutin à la majorité des suffrages exprimés (type ScrutinMajoritaire): dans ce cas est déclaré vainqueur le vote qui aura obtenu plus de 50% des suffrages autres que les bulletins blancs ou nuls. Les bulletins nuls peuvent être considérés comme correspondant à un bulletin de vote particulier appelé nul.
le scrutin à majorité relative (type ScrutinRelatif) : est déclaré vainqueur le vote (autre que les bulletins blancs ou nuls) qui a reçu seul le plus de suffrages pour peu qu'au moins 15% des inscrits au scrutin aient exprimé ce vote.
Il peut bien sûr n'y avoir aucun vainqueur, dans chacun de ces cas.

Un scrutin est défini par l'interface suivante du paquetage scrutin:


```
public interface Scrutin {
    /**
     * fournit les bulletins de votes soumis a ce scrutin.
     * @return l'ensemble des bulletins de votes proposés au cours de ce scrutin
     */
    public List<BulletinVote> getBulletinsVotePossibles();

    /** ajoute un vote (bulletin) au scrutin
     * @param v le vote ajouté
     * @throws VoteClosException si le scrutin est clos
     */
    public void voteExprime(BulletinVote vote) throws VoteClosException;

    /** fournit le statut clos/non clos du scrutin
     * @return <tt>true</tt> ssi le scrutin est clos
     */
    public boolean estClos();

    /** Clot le scrutin, cette methode realise egalement le "depouillement" du scrutin : le decompte
     *  des suffages obtenus est realise.
     */
    public void clot();

    /** determine le vainqueur du scrutin, <tt>null</tt> si aucun 
     * @return le vainqueur du scrutin, <tt>null</tt> si aucun
     * @throws VoteNonClosException si le scrutin n'est pas clos
     */
    public BulletinVote getVainqueur() throws VoteNonClosException;

    /** affiche  pour chaque bulleint de vote plus les votes "nul" et "blanc", le nombre de suffrages obtenu
     * @throws VoteNonClosException si le bulletin n'est pas clos
     */
    public void afficheResultats() throws VoteNonClosException;
}

```

La méthode getBulletinsVotePossibles retourne l'ensemble des bulletins de vote possibles pour ce scrutin.
La méthode voteExprime(vote) permet d'indiquer qu'un électeur a choisi le bulletin de vote vote. Son bulletin vient donc s'ajouter aux votes exprimés pour le scrutin (dans “l'urne”). On ne s'occupe pas ici des électeurs ni du fait que chacun ne peut voter qu'une fois, ceci est supposé géré par ailleurs. Tant que le scrutin n'est pas clos, cette méthode accepte tous les bulletins de votes, qu'ils fassent ou non partie des votes “possibles”. Elle lève une exception ScrutinClosException si le scrutin est clos et n'a alors aucun effet.
Les méthodes getVainqueur() et afficheResultats() lèvent une exception ScrutinNonClosException si le scrutin n'est pas clos. Elles n'ont alors aucun autre effet. On suppose les classes d'exception créées et définies dans le paquetage scrutin.
La méthode clot clôt le scrutin et établit le résultat du scrutin (phase de dépouillement du scrutin). Les résultats sont mémorisés sous la forme d’une table de hachage associant à un objet BulletinVote le nombre de suffrages qu’il a reçus. Rappelons que si l’un des votes exprimés ne fait pas partie des bulletins de votes possibles pour le scrutin, il est alors considéré comme un vote nul.
La méthode afficheResultats affiche pour chaque bulletin de vote possible, ainsi que les bulletins nul et blanc, le nombre de suffrage reçus, une fois le scrutin clos.
Le résultat de getVainqueur est null s’il n’y a aucun vainqueur.
Toutes les classes que vous définirez devront appartenir au paquetage scrutin.
  
## Votes

Donnez le code de BulletinVote (classe ou interface ?).  
Définissez également 4 constantes correspondant à des bulletins de votes particuliers : les bulletins oui, non, blanc et nul.  

## Scrutins  

Donnez le diagramme des classes incluant ScrutinMajoritaire et ScrutinRelatif.
Implantez toutes les classes, sachant que le nombre d’inscrits (électeurs) et l’ensemble des bulletins (ou votes) possibles sont communiqués à la création d’une instance. Aux bulletins possibles doivent systématiquement être ajoutés les bulletins blanc et nul.  
Créez une classe contenant comme seule méthode une méthode main qui:  
crée un référendum, qui est un scrutin majoritaire dans lequel les bulletins de votes possibles sont les bulletins oui et non, on fixera le nombre de votants à 110;
ajoute 100 bulletins de vote choisis aléatoirement entre oui et non,
clôt le scrutin et annonce le vainqueur.  
Auteur: Jean-Christophe Routier

Email: cedric.lhoussaine@univ-lille1.fr
