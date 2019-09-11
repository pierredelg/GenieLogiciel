import java.util.Objects;

public class Rationnel {

    private int n;
    private int a;
    private int b;

    public Rationnel(int n,int a,int b){
        this.n = n;
        this.a = a;
        this.b = b;
    }

    public Rationnel(int a, int b){
        this.a = a;
        this.b = b;
    }

    public Rationnel(int n){
        this.n = n;
    }

    @Override
    public String toString() {
        return n + " + " + a + " / " + b;
    }

    public boolean estNul(){
        return n == 0 && a == 0;
    }

    public Rationnel inverse(){
        int nInverse = 0;
        int aInverse = 0;
        int bInverse = 0;

        if(a != 0 && b != 0){
            bInverse = a;
            aInverse = b;
        }

        if(n != 0){
            nInverse = 1/n;
        }

        return new Rationnel(nInverse,aInverse,bInverse);
    }

    public Rationnel ajouter(Rationnel r){
        int nAjout = 0;
        int aAjout = 0;
        int bAjout = 0;

        if(r.n != 0 && r.a == 0 && r.b == 0){
            nAjout = this.n + r.n;
        }


        return new Rationnel(nAjout,aAjout,bAjout);
    }

    public Rationnel multiplier(Rationnel r){
        int nMultiplier = 0;
        int aMultiplier = 0;
        int bMultiplier = 0;

        return new Rationnel(nMultiplier,aMultiplier,bMultiplier);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()){
            return false;
        }

        Rationnel rationnel = (Rationnel) o;

        return n == rationnel.n && a == rationnel.a && b == rationnel.b;
    }
}
