package comptes;

public class PEL extends CompteEpargne{

    private double montantAncien;

    public PEL() {
        super(0.5);
    }

    @Override
    public void retirer(double somme) {
        if(somme > montant && getAnciennete() > 48){
           super.retirer(somme);
        }
    }

    @Override
    public void operationsMensuelles() throws DepassementDecouvertExc, VersementsInsuffisantsExc {
        if((this.montant - this.montantAncien) < 50){
            throw new VersementsInsuffisantsExc(this.numero);
        }
        this.montantAncien = this.montant;
        super.operationsMensuelles();
    }
}
