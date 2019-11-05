package image;

/**
 * Classe abstraite représentant une image quelconque.
 * @author DELGRANGE Pierre
 */
public abstract class ImageQuelconque implements ImageGrise{

    protected int largeur, hauteur;

    public ImageQuelconque(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
    }
    abstract void initialiserPoints();
    public abstract NiveauGris pointEn(int x, int y);
    public abstract void definirPoint(int x, int y, NiveauGris gris);


    protected boolean correct(int x, int y) {
        return ((x >= 0) && (x < largeur) && (y >= 0) && (y < hauteur));
    }

    protected boolean incompatible(ImageGrise img) {
        return (largeur != img.largeur()) || (hauteur != img.hauteur());
    }

    private ImageQuelconque newInstance(){
        if(this instanceof ImageTab) {
            return  new ImageTab(largeur, hauteur);
        }
        if(this instanceof ImageDict) {
            return new ImageDict(largeur, hauteur);
        }
        if(this instanceof ImageDoubleDict){
            return new ImageDoubleDict(largeur,hauteur);
        }
        return null;
    }

    @Override
    public int largeur() {
        return largeur;
    }

    @Override
    public int hauteur() {
        return hauteur;
    }

    @Override
    public void randomize() {
        for (int y=0; y<hauteur(); y++)
            for (int x=0; x<largeur(); x++)
                this.definirPoint(x, y, this.pointEn(x,y).randomizeNB());
    }
    @Override
    public void allumer(int x, int y) {
        if (this.correct(x,y))
            this.definirPoint(x, y, NiveauGris.NOIR);
    }
    @Override
    public void eteindre(int x, int y) {
        if (this.correct(x,y))
            this.definirPoint(x, y, NiveauGris.BLANC);
    }
    @Override
    public int compterPoints(NiveauGris gris) {
        int nombre = 0;
        for (int y=0; y<hauteur; y++)
            for (int x=0; x<largeur; x++)
                if (this.pointEn(x,y).equals(gris))
                    nombre++;
        return nombre;
    }


    @Override
    public NiveauGris niveauMoyen() {
        int s = 0;
        for (int n=0; n<=NiveauGris.values().length; n++)
            s += n * this.compterPoints(NiveauGris.deNiveau(n));
        return NiveauGris.deNiveau((int)(((double) s) / (largeur * hauteur)));
    }
    @Override
    public ImageGrise inverser() {
        ImageGrise result = newInstance();
        for (int y=0; y<hauteur; y++)
            for (int x=0; x<largeur; x++)
                result.definirPoint(x, y, this.pointEn(x,y).inverser());
        return result;
    }
    @Override
    public ImageGrise eclaircir() {
        ImageGrise result = newInstance();
        for (int y=0; y<hauteur; y++)
            for (int x=0; x<largeur; x++)
                result.definirPoint(x, y, this.pointEn(x,y).eclaircir());
        return result;
    }
    @Override
    public ImageGrise assombrir() {
        ImageGrise result= newInstance();
        for (int y=0; y<hauteur; y++)
            for (int x=0; x<largeur; x++)
                result.definirPoint(x, y, this.pointEn(x,y).assombrir());
        return result;
    }
    @Override
    public ImageGrise dupliquer() {
        ImageGrise result= newInstance();
        for (int y=0; y<hauteur; y++)
            for (int x=0; x<largeur; x++)
                result.definirPoint(x, y, this.pointEn(x,y));
        return result;
    }
    @Override
    public ImageGrise ajouter(ImageGrise img) throws ImagesIncompatiblesException {
        ImageGrise result= newInstance();
        if (this.incompatible(img))
            throw new ImagesIncompatiblesException("Image incompatible");
        for (int y=0; y<hauteur; y++)
            for (int x=0; x<largeur; x++)
                result.definirPoint(x, y,
                        this.pointEn(x,y).ajouter(img.pointEn(x,y)));
        return result;
    }
    @Override
    public ImageGrise soustraire(ImageGrise img) throws ImagesIncompatiblesException {
        ImageGrise result= newInstance();

        if (this.incompatible(img))
            throw new ImagesIncompatiblesException("Image incompatible");
        for (int y=0; y<hauteur; y++)
            for (int x=0; x<largeur; x++)
                result.definirPoint(x, y,
                        this.pointEn(x,y).soustraire(img.pointEn(x,y)));
        return result;
    }
    @Override
    public ImageGrise XOR(ImageGrise img) throws ImagesIncompatiblesException {
        ImageGrise result= newInstance();
        if (this.incompatible(img))
            throw new ImagesIncompatiblesException("Image incompatible");
        for (int y=0; y<hauteur; y++)
            for (int x=0; x<largeur; x++)
                result.definirPoint(x, y,
                        this.pointEn(x,y).XOR(img.pointEn(x,y)));
        return result;
    }
    @Override
    public ImageGrise intersection(ImageGrise img) throws ImagesIncompatiblesException {
        ImageGrise result= newInstance();
        if (this.incompatible(img))
            throw new ImagesIncompatiblesException("Image incompatible");
    for (int y=0; y<hauteur; y++)
            for (int x=0; x<largeur; x++)
                if (this.pointEn(x,y).equals(img.pointEn(x,y)))
                    result.definirPoint(x, y, this.pointEn(x,y));
        return result;
    }


    @Override
    public ImageGrise augmenterContraste() {
        NiveauGris courant, moyen;
        ImageGrise result= newInstance();
        moyen = this.niveauMoyen();
        for (int y=0; y<hauteur; y++)
            for (int x=0; x<largeur; x++)
            {
                courant = this.pointEn(x, y);
                if (courant.compareTo(moyen) > 0)
                    result.definirPoint(x, y, courant.assombrir());
                else
                    result.definirPoint(x, y, courant.eclaircir());
            }
        return result;
    }
    @Override
    public String toString() {
        String s = largeur + "x" + hauteur;
        for (int y=0; y<hauteur; y++)
        {
            s += "\n";
            for (int x=0; x<largeur; x++)
                s += this.pointEn(x, y);
        }
        return s;
    }

}
