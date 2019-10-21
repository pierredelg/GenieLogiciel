package comptes;

public class CompteCourant extends CompteGenerique{

    private int montantDecouvert;

    private double agios;

    public CompteCourant(int montantDecouvert) {
        this.montantDecouvert = montantDecouvert;
    }

    @Override
    public void operationsMensuelles() throws DepassementDecouvertExc, VersementsInsuffisantsExc {

        //Si le solde du compte est négatif
        if(montant < 0 && (-montant) > montantDecouvert){
            //On calcule les agios pour le compte
            if(anciennete < 60){
                agios = 0.1 * montant;
            }
            else{
                agios = 0.06 * montant;
            }

            System.out.println("On retire les agios : " + agios);

            //On retire les agios au compte
            this.montant = this.montant + agios;

            //Si le montant restant est inférieur a découvert on rejette l'exception
            if(montant < montantDecouvert){
                throw new DepassementDecouvertExc(this.numero);
            }

        }
        super.operationsMensuelles();
    }
}
