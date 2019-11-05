package image;

import dictionnaire.Dictionnaire;
import dictionnaire.TabDict;

public abstract class ImageDictSuper extends ImageQuelconque{

    protected Dictionnaire points;


    public ImageDictSuper(int largeur, int hauteur) {
        super(largeur, hauteur);
    }

    @Override
    void initialiserPoints() {
        points = new TabDict();
    }

    public abstract NiveauGris pointEn(int x, int y);

    public abstract void definirPoint(int x, int y, NiveauGris gris);
}
