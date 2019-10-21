package comptes;

public abstract class CompteGenerique implements Compte{

    protected int numero;

    protected double montant;

    protected int anciennete;

    @Override
    public int getNumero() {
        return this.numero;
    }

    @Override
    public int getAnciennete() {
        return this.anciennete;
    }

    @Override
    public void ajouter(double somme) {
        this.montant += somme;
    }

    @Override
    public void retirer(double somme) {
        this.montant = this.montant - somme;
    }

    @Override
    public void operationsMensuelles() throws DepassementDecouvertExc, VersementsInsuffisantsExc {
        anciennete++;
    }

}
