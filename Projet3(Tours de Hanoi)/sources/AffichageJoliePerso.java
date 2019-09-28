/**
 * Affiche les piles de disques.
 */
public class AffichageJoliePerso implements Affichage{
    /**
     * Le motif de représentation. De base le motif est : *
     *
     * */
    private char motif;

    /**
     * Constructeur simple avec le motif de base.
     */
    public AffichageJoliePerso(){
        this.motif = '*';
    }

    /**
     * Constructeur avec le choix du motif.
     * NB : Pour un meilleur affichage, il est préférable de ne pas utiliser le caractère '#'
     * ou le caractère '|' car ils sont utilisés pour représenter les tours.
     * @param motif - Le caractere représentant le motif.
     */
    public AffichageJoliePerso(char motif){
        this.motif = motif;
    }

    /**
     * Affiche le tableau de Disque avec un motif selectionné.
     * @param d - Le tableau de Disque
     * @param n - Le nombre d'éléments dans le tableau.
     * @return Une chaine de caractères représentant la pile de Disque.
     */
    @Override
    public String affichage_tableau(Disque[] d, int n) {

        //On initialise le résultat par un passage à la ligne.
        String resultat = "\n";
        resultat += ajouterEspaceDepart(diametreMax(d), 0);
        resultat += " |\n";

        //On parcourt le tableau de Disque de la fin au debut.
        for (int i = n - 1; i >= 0; i--) {

            /*Pour chaque élément du tableau on ajoute un nombre d'espace si nécessaire
            de façon à ce que chaque disque soit centré*/
            // On ajoute +1 au diametre max pour le symbole représentant le centre de la tour.
            resultat += ajouterEspaceDepart(diametreMax(d)+1,d[i].diametre());

            //Pour chaque disque on ajoute le nombre de motif correspondant au double de son diametre
            for (int j = 0; j < d[i].diametre() * 2; j++) {
                //Si on arrive au centre on ajoute le centre de la tour
                if(j == d[i].diametre()){
                    resultat += "|" + motif;
                }else {
                    resultat += motif;
                }
            }
            resultat += "\n";

        }
        //On ajoute la base de la pile.
        resultat += ajouterBase(diametreMax(d));

        return resultat;
    }

    /**
     * Permet d'ajouter les espaces nécéssaires pour centrer un disque sur la tour.
     * @param diametreMax - Le diametre maximum des disques présents dans la tour.
     * @param diametreDuDisque - Le diametre du disque en cours.
     * @return Une chaine de caractères avec le nombre d'espace necessaire pour centrer le disque.
     */
    private String ajouterEspaceDepart(int diametreMax,int diametreDuDisque){
        String resultat = "";

        for(int i = 0; i < diametreMax - diametreDuDisque;i ++ ){

            resultat += " ";
        }
        return resultat;
    }

    /**
     * Ajoute la base de la tour.
     * Représentée avec le caractère '#'.
     * @param diametreMax - Le diametre maximum des disques présents dans la tour.
     * @return La chaine de caractère représentant la base de la tour.
     */
    private String ajouterBase(int diametreMax){
        String resultat = "###";
        for(int i = 0; i < diametreMax * 2;i++){
            resultat += "#";
        }
        return resultat + "\n";
    }

    /**
     * Permet de calculer le diametre maximum des disques présents dans la tour.
     * @param tour - Le tableau de disque représentant la tour.
     * @return Le diametre maximum des disques présents dans la tour.
     */
    private int diametreMax(Disque[] tour){

        int diametreMax = 0;

        if(tour != null) {
            for (int i = 0; i < tour.length; i++) {
                if (tour[i] != null && tour[i].diametre() > diametreMax) {
                    diametreMax = tour[i].diametre();
                }
            }
        }
        return diametreMax;
    }
}
