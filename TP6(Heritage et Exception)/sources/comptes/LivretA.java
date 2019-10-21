package comptes;

public class LivretA extends CompteEpargne{

    public LivretA() {
        super(0.2);
    }

    @Override
    public void retirer(double somme) {
        if(somme > montant){
            super.retirer(somme);
        }
    }
}
