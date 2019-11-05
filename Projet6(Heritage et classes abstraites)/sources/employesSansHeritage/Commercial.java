package employesSansHeritage;

public class Commercial {

    private double ca , salaireFixe;

    private String nom;

    public Commercial(String nom) {
        this.salaireFixe = 1500;
        this.nom = nom;
    }

    public double salaireHebdo() {
        return salaireFixe + ca/100;
    }

    public void setTravail(double x) {
        this.ca = x;
    }

    @Override
    public String toString() {
        return nom;
    }
}
