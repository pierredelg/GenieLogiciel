public class Lambda {

    // Déclaration de la méthode calcul
    //On reprends donc les deux arguments de la méthode calcul de la méthode main
    public static int calcul(String [] tab,Calcul c){
        int cpt = 0;
        for (String s : tab){
            //On appel la méthode de l'interface en parametre qui correspond à la lambda du main
            if (c.testLambda(s)){
                cpt++;
            }
        }
        return cpt;
    }

    public static void main(String[] args){
        //La lambda expression x.equals("coucou") est le deuxieme argument de la méthode calcul
        System.out.println("Il y a " + calcul(args,x->x.equals("coucou")) + " fois la chaîne \"coucou\" en argument");
    }
}