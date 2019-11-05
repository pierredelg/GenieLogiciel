package image;

import dictionnaire.correction.*;

/** La classe <code>ImageDoubleDict2</code> représente une image en niveaux
 *  de gris au moyen d'un dictionnaire dont les clefs sont les abscisses des
 *  points de l'image, et les valeurs sont elles-mêmes des dictionnaires
 *  associant à chaque ordonnée le niveau de gris associé.
 *  La différence principale avec <code>ImageDict</code> est le temps d'accès
 *  aux données, de l'ordre de <code>largeur + hauteur</code> dans le pire cas,
 *  contre <code>largeur * hauteur</code> pour le pire cas dans <code>ImageDict</code>.
 *  <B> On remarquera que le code de toutes les méthodes de <code>ImageDict</code>
 *  et <code>ImageDoubleDict2</code> est identique, à l'exception de <code>pointEn</code>
 *  et <code>definirPoint</code>.</B>
 *  Cette classe utilise <code>ArrayListDict</code> pour gérer les dictionnaires.
 *  @see image.NiveauGris
 *  @see image.Point
 *  @see dictionnaire.correction.TabDict
 */
public class ImageDoubleDict2 implements ImageGrise {
private int largeur, hauteur;
private Dictionnaire<Integer,Dictionnaire<Integer,NiveauGris> > points;

public ImageDoubleDict2(int w, int h) {
        largeur = w;
        hauteur = h;
        this.initialiserPoints();
}

private boolean correct(int x, int y) {
        return ((x >= 0) && (x < largeur) && (y >= 0) && (y < hauteur));
}

private void initialiserPoints() {
        points = new ArrayListDict<Integer, Dictionnaire<Integer, NiveauGris> >();
}

private boolean incompatible(ImageGrise img) {
        return (largeur != img.largeur()) || (hauteur != img.hauteur());
}

public int largeur() {
        return largeur;
}
public int hauteur() {
        return hauteur;
}

public NiveauGris pointEn(int x, int y) {
        if (points.contientClef(x)) {
                Dictionnaire<Integer, NiveauGris> d = points.valeurPour(x);
                if (d.contientClef(y))
                        return d.valeurPour(y);
        }
        return NiveauGris.BLANC;
}

public void definirPoint(int x, int y, NiveauGris gris) {
        if (this.correct(x,y)) {
                if (gris.equals(NiveauGris.BLANC)) {
                        Dictionnaire<Integer,NiveauGris> d;
                        if (points.contientClef(x)) {
                                d = points.valeurPour(x);
                                d.enleverPour(y);
                                if (d.estVide())
                                        points.enleverPour(x);
                        }
                } else {
                        Dictionnaire<Integer,NiveauGris> d;
                        if (points.contientClef(x))
                                d = points.valeurPour(x);
                        else {
                                d = new ArrayListDict<Integer,NiveauGris>();
                                points.ajouter(x, d);
                        }
                        d.ajouter(y, gris);
                }
        }
}

public void allumer(int x, int y) {
        if (this.correct(x,y))
                this.definirPoint(x, y, NiveauGris.NOIR);
}

public void randomize() {
        for (int y=0; y<hauteur(); y++)
                for (int x=0; x<largeur(); x++)
                        this.definirPoint(x, y, this.pointEn(x,y).randomizeNB());
}

public void eteindre(int x, int y) {
        if (this.correct(x,y))
                this.definirPoint(x, y, NiveauGris.BLANC);
}

public int compterPoints(NiveauGris gris) {
        int nombre = 0;
        for (int y=0; y<hauteur; y++)
                for (int x=0; x<largeur; x++)
                        if (this.pointEn(x,y).equals(gris))
                                nombre++;
        return nombre;
}

public ImageGrise inverser() {
        ImageGrise result = new ImageDoubleDict2(largeur, hauteur);
        for (int y=0; y<hauteur; y++)
                for (int x=0; x<largeur; x++)
                        result.definirPoint(x, y, this.pointEn(x,y).inverser());
        return result;
}

public ImageGrise eclaircir() {
        ImageGrise result = new ImageDoubleDict2(largeur, hauteur);
        for (int y=0; y<hauteur; y++)
                for (int x=0; x<largeur; x++)
                        result.definirPoint(x, y, this.pointEn(x,y).eclaircir());
        return result;
}

public ImageGrise assombrir() {
        ImageGrise result = new ImageDoubleDict2(largeur, hauteur);
        for (int y=0; y<hauteur; y++)
                for (int x=0; x<largeur; x++)
                        result.definirPoint(x, y, this.pointEn(x,y).assombrir());
        return result;
}

public ImageGrise dupliquer() {
        ImageGrise result = new ImageDoubleDict2(largeur, hauteur);
        for (int y=0; y<hauteur; y++)
                for (int x=0; x<largeur; x++)
                        result.definirPoint(x, y, this.pointEn(x,y));
        return result;
}

public ImageGrise ajouter(ImageGrise img) {
        ImageGrise result = new ImageDoubleDict2(largeur, hauteur);
        if (this.incompatible(img))
                return result;
        for (int y=0; y<hauteur; y++)
                for (int x=0; x<largeur; x++)
                        result.definirPoint(x, y,
                                            this.pointEn(x,y).ajouter(img.pointEn(x,y)));
        return result;
}

public ImageGrise soustraire(ImageGrise img) {
        ImageGrise result = new ImageDoubleDict2(largeur, hauteur);
        if (this.incompatible(img))
                return result;
        for (int y=0; y<hauteur; y++)
                for (int x=0; x<largeur; x++)
                        result.definirPoint(x, y,
                                            this.pointEn(x,y).soustraire(img.pointEn(x,y)));
        return result;
}

public ImageGrise XOR(ImageGrise img) {
        ImageGrise result = new ImageDoubleDict2(largeur, hauteur);
        if (this.incompatible(img))
                return result;
        for (int y=0; y<hauteur; y++)
                for (int x=0; x<largeur; x++)
                        result.definirPoint(x, y,
                                            this.pointEn(x,y).XOR(img.pointEn(x,y)));
        return result;
}

public ImageGrise intersection(ImageGrise img) {
        ImageGrise result = new ImageDoubleDict2(largeur, hauteur);
        if (this.incompatible(img))
                return result;
        for (int y=0; y<hauteur; y++)
                for (int x=0; x<largeur; x++)
                        if (this.pointEn(x,y).equals(img.pointEn(x,y)))
                                result.definirPoint(x, y, this.pointEn(x,y));
        return result;
}

public String toString() {
        String s = largeur + "x" + hauteur;
        for (int y=0; y<hauteur; y++) {
                s += "\n";
                for (int x=0; x<largeur; x++)
                        s += this.pointEn(x, y);
        }
        return s;
}

public NiveauGris niveauMoyen() {
        int s = 0;
        for (int n=0; n<=NiveauGris.values().length; n++)
                s += n * this.compterPoints(NiveauGris.deNiveau(n));
        return NiveauGris.deNiveau((int)(((double) s) / (largeur * hauteur)));
}

public ImageGrise augmenterContraste() {
        NiveauGris courant, moyen;
        ImageGrise result = new ImageDoubleDict2(largeur, hauteur);
        moyen = this.niveauMoyen();
        for (int y=0; y<hauteur; y++)
                for (int x=0; x<largeur; x++) {
                        courant = this.pointEn(x, y);
                        if (courant.compareTo(moyen) > 0)
                                result.definirPoint(x, y, courant.assombrir());
                        else
                                result.definirPoint(x, y, courant.eclaircir());
                }
        return result;
}
}
