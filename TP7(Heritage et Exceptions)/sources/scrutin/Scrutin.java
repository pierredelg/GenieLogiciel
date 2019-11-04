package scrutin;

import java.util.List;

public interface Scrutin {

    /**
     * fournit les bulletins de votes soumis a ce scrutin.
     * @return l'ensemble des bulletins de votes proposés au cours de ce scrutin
     */
    public List<BulletinVote> getBulletinsVotePossibles();

    /** ajoute un vote (bulletin) au scrutin
     * @param vote le vote ajouté
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
