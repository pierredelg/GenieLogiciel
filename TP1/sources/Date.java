/**
*Classe permettant de définir une date
*
*/
public class Date
{
    // les attributs des instances
    private int jour, mois, annee ;
    // les attributs de la classe
    public static final String [] NOM_DES_MOIS =
    { "Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet",
      "Aout", "Septembre", "Octobre", "Novembre", "Decembre" } ;
    
    public static final String [] NOM_DES_MOIS_E =
    { "January", "Febrary", "March", "April", "May", "June", "July",
      "August", "September", "October", "November", "December" } ;
    
    public Date(int jour, int mois, int annee)
    {
        this.jour = jour ;
        this.mois = mois ;
        this.annee = annee ;
    }

    /*Méthode d'affichage*/
    public String toString()
    {
        return jour + " " + NOM_DES_MOIS[mois-1] + " " + annee ;
    }

    public String toStringE(){
            
        return jour + " " + NOM_DES_MOIS_E[mois-1] + " " + annee ;
    }

    /*Test*/
    public static void main (String arg[]){
            int jour,mois,annee;

            System.out.println("Jour : ");
            jour=Clavier.readInt();
            System.out.println("Mois : ");
            mois=Clavier.readInt();
            System.out.println("Année : ");
            annee=Clavier.readInt();
            Date date = new Date(jour,mois,annee);
           
            if(arg[0].equals("-anglais")){
                System.out.println("The date is : " + date.toStringE() );
            }else{
                System.out.println("La date est : " + date );
            }
    }
}
