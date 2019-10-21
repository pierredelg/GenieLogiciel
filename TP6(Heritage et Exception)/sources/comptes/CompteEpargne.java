package comptes;

public abstract class CompteEpargne extends CompteGenerique{

    protected double tauxInteret;

    public CompteEpargne(double tauxInteret){
        this.tauxInteret = tauxInteret;
    }

    public double getTauxInteret(){
        return montant * tauxInteret;
    }
}
