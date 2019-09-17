import java.util.Scanner;

public class NoeudArbre {
    private String noeud;
    private NoeudArbre noeudArbreGauche;
    private NoeudArbre noeudArbreDroit;
    public Scanner  scanner =  new Scanner(System.in);

    public static NoeudArbre derniereFeuille;

    public NoeudArbre(){

    }

    public NoeudArbre(String noeud){
        this.noeud = noeud;
    }
    public NoeudArbre (String noeud,NoeudArbre noeudArbreGauche,NoeudArbre noeudArbreDroit){
        this.noeud = noeud;
        this.noeudArbreGauche = noeudArbreGauche;
        this.noeudArbreDroit = noeudArbreDroit;
    }

    public boolean estUneFeuille(){
        return noeudArbreGauche == null && noeudArbreDroit == null;
    }

    public String getNoeud() {
        return noeud;
    }

    public void setNoeud(String noeud) {
        this.noeud = noeud;
    }

    public NoeudArbre getNoeudArbreGauche() {
        return noeudArbreGauche;
    }

    public void setNoeudArbreGauche(NoeudArbre noeudArbreGauche) {
        this.noeudArbreGauche = noeudArbreGauche;
    }

    public NoeudArbre getNoeudArbreDroit() {
        return noeudArbreDroit;
    }

    public void setNoeudArbreDroit(NoeudArbre noeudArbreDroit) {
        this.noeudArbreDroit = noeudArbreDroit;
    }

    @Override
    public String toString() {
        String result = "";

        if(noeud != null){
            result += noeud + " ";
        }
        if(noeudArbreGauche != null){
            result += noeudArbreGauche.toString() + " ";
        }
        if(noeudArbreDroit != null){
            result += noeudArbreDroit + " ";
        }
        return result;
    }

    public boolean rechercherAnimal(){

        String reponse;

        if(estUneFeuille()) {
            System.out.println("Est-ce " + this.noeud + " ?");
        }else{
            System.out.println(this.noeud);
        }
        reponse = scanner.nextLine();

        if(estUneFeuille() && testOui(reponse)){
            return true;
        }
        if(estUneFeuille() && testNon(reponse)){
            derniereFeuille = this;
            return false;
        }

        if(getNoeudArbreGauche() != null && testNon(reponse)){
            return getNoeudArbreGauche().rechercherAnimal();
        }

        if(getNoeudArbreDroit() != null && testOui(reponse)){
            return getNoeudArbreDroit().rechercherAnimal();
        }
        return false;

    }
    public void apprendre(){

        String dernierAnimal = derniereFeuille.getNoeud();

        System.out.println("Qu’est-ce que c’est ?");
        String nouvelAnimal;
        nouvelAnimal = scanner.nextLine();

        System.out.println( nouvelAnimal  + " ! Je ne connais pas cet animal. Donnez-moi une question qui permette de différencier " + nouvelAnimal +  " d’ " + derniereFeuille + " ?");
        String nouvelleQuestion = scanner.nextLine();
        derniereFeuille.setNoeud(nouvelleQuestion);

        System.out.println("Quelle doit être la réponse pour " + nouvelAnimal + " ?");
        String nouvelleRéponse = scanner.nextLine();

        if(testOui(nouvelleRéponse)) {
            derniereFeuille.setNoeudArbreDroit(new NoeudArbre(nouvelAnimal));
            derniereFeuille.setNoeudArbreGauche(new NoeudArbre(dernierAnimal));
        }
        if(testNon(nouvelleRéponse)){
            derniereFeuille.setNoeudArbreDroit(new NoeudArbre(dernierAnimal));
            derniereFeuille.setNoeudArbreGauche(new NoeudArbre(nouvelAnimal));
        }


    }
    public void jouer() {
        String reponse;
        do {
            if (rechercherAnimal()) {
                System.out.println("Trouvé !");
            } else {
                System.out.println("Perdu ! ");
                apprendre();
            }
            System.out.println("Arbre complet : " + this.toString());
            System.out.println("Voulez-vous rejouer ?");
             reponse = scanner.nextLine();

        }while(testOui(reponse));
    }
    public boolean testOui(String reponse){
        return reponse != null && (reponse.equals("oui") || reponse.equals("Oui") || reponse.equals("OUI"));
    }

    public boolean testNon(String reponse){
        return reponse != null && (reponse.equals("non") || reponse.equals("Non") || reponse.equals("NON"));
    }
    public void definir(String animal){

    }
    public NoeudArbre remplirArbre(String[] args, int indexDebut){

        if(args.length == indexDebut){
            return new NoeudArbre(args[indexDebut]);
        }
//        return new NoeudArbre()
//
//
//
//        for (int i = indexDebut; i < args.length ; i= i+3){
//            if(args[i] != null){
//                this.setNoeud();new NoeudArbre(args[i]);
//                if( args[i+1] != null){
//                    n1.setNoeudArbreGauche(new NoeudArbre(args[i+1]));
//                    if(args[i+2] != null){
//                        n1.setNoeudArbreDroit(new NoeudArbre(args[i+2]));
//                    }
//                }
//            }
//        }
        return null;
    }

    public static void main(String[] args) {
        NoeudArbre n1 = new NoeudArbre();
        if(args.length == 0) {
            n1 = new NoeudArbre("Est-ce un mammifère ?");
            NoeudArbre n2 = new NoeudArbre("un crocodile");
            n1.setNoeudArbreGauche(n2);
            NoeudArbre n3 = new NoeudArbre("Est-ce qu'il aboie ?");
            n1.setNoeudArbreDroit(n3);
            NoeudArbre n4 = new NoeudArbre("un cheval");
            n3.setNoeudArbreGauche(n4);
            NoeudArbre n5 = new NoeudArbre("un chien");
            n3.setNoeudArbreDroit(n5);
        }else{
            if(args[1].equals("--definir")){
                n1.definir(args[2]);
            }else {
                n1 = n1.remplirArbre(args,0);
            }

        }

        n1.jouer();


    }
}
