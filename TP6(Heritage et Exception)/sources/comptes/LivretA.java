package comptes;

public class LivretA extends CompteEpargne{

    public LivretA() {
        super(0.2);
    }

   public String toString() {
        return null;
    }

    @Override
    public void retirer(double somme) {
        if(somme > montant){
            super.retirer(somme);
        }
    }
}
