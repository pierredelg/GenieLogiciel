public interface Pile<E> {
    int MAX_ELEMENTS = 100 ;    // nombre maximal d’éléments
    boolean vide() ;            // teste si la pile est vide
    boolean pleine() ;          // teste si la pile est pleine
    boolean peutEmpiler(E x) ;  // teste si la pile peut empiler x
    E sommet() ;                // référence de l’objet au sommet
    E depile() ;                // enlève et retourne l’objet au sommet
    void empile(E x) ;          // place un objet au sommet
    void vider() ;              // vide la pile
    int nbElements() ;          // compte le nombre d’éléments empilés
    void deplacerUnElementVers(Pile<E> p) ;// déplace un élément de la pile courante vers p
    String toString() ;         // affichage de tous les éléments depuis le sommet
    String getNom();            // retourne le nom de la pile
}