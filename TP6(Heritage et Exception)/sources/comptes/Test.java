package comptes;

public class Test {
    public static void main(String[] args) {
        LivretA livretA = new LivretA();
        CompteCourant compteCourant = new CompteCourant(10);
        PEL pel = new PEL();
        pel.ajouter(100);

        try {
            compteCourant.operationsMensuelles();
            livretA.operationsMensuelles();
            pel.operationsMensuelles();
        } catch (DepassementDecouvertExc | VersementsInsuffisantsExc depassementDecouvertExc) {
            depassementDecouvertExc.printStackTrace();
        }
    }
}
