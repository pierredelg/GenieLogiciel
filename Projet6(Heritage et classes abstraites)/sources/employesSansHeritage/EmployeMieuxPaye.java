package employesSansHeritage;

public class EmployeMieuxPaye {
    private double temps_travail, taux_horaire, majoration, heures ;
    private String nom ;

    public EmployeMieuxPaye(String nom) {
        this.temps_travail = 35;
        this.taux_horaire = 7.5;
        this.majoration = 1.40;
        this.nom = nom;
    }

    public double salaireHebdo(){
        if (heures > temps_travail)
            return temps_travail * taux_horaire
                    + (heures - temps_travail) * taux_horaire * majoration;
        return heures * taux_horaire;
    }

    public void setTravail(double x){
        this.heures = x;
    }

    @Override
    public String toString() {
        return nom;
    }
}
