package image;

import dictionnaire.correction.*;

/** La classe <code>ImageDict2</code> représente une image en niveaux
 *  de gris au moyen d'un dictionnaire dont les clefs sont des coordonnées
 *  (classe <code>Point</code>) et les valeurs les niveaux de gris associés.
 *  Lors de l'instanciation, il suffit de créer un dictionnaire vide puisqu'on
 *  ne stocke que les niveau de gris autres que blanc. L'absence de la clef
 *  (x, y) dans le dictionnaire signifie que le point (x, y) est blanc.
 *  Cette classe utilise <code>ArrayListDict</code>.
 *  @see image.NiveauGris
 *  @see image.Point
 *  @see dictionnaire.correction.TabDict
 */
public class ImageDict2 implements ImageGrise {
private int largeur, hauteur;
private Dictionnaire<Point,NiveauGris> points;

public ImageDict2(int w, int h) {
        largeur = w;
        hauteur = h;
        this.initialiserPoints();
}

private boolean correct(int x, int y) {
        return ((x >= 0) && (x < largeur) && (y >= 0) && (y < hauteur));
}

private void initialiserPoints() {
        points = new ArrayListDict<Point,NiveauGris>();
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
        if (points.contientClef(new Point(x, y)))
                return points.valeurPour(new Point(x, y));
        return NiveauGris.BLANC;
}

public void definirPoint(int x, int y, NiveauGris gris) {
        if (this.correct(x,y)) {
                if (gris.equals(NiveauGris.BLANC))
                        points.enleverPour(new Point(x, y));
                else
                        points.ajouter(new Point(x, y), gris);
        }
}

public void randomize() {
        for (int y=0; y<hauteur(); y++)
                for (int x=0; x<largeur(); x++)
                        this.definirPoint(x, y, this.pointEn(x,y).randomizeNB());
}

public void allumer(int x, int y) {
        if (this.correct(x,y))
                this.definirPoint(x, y, NiveauGris.NOIR);
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
        ImageGrise result = new ImageDict2(largeur, hauteur);
        for (int y=0; y<hauteur; y++)
                for (int x=0; x<largeur; x++)
                        result.definirPoint(x, y, this.pointEn(x,y).inverser());
        return result;
}

public ImageGrise eclaircir() {
        ImageGrise result = new ImageDict2(largeur, hauteur);
        for (int y=0; y<hauteur; y++)
                for (int x=0; x<largeur; x++)
                        result.definirPoint(x, y, this.pointEn(x,y).eclaircir());
        return result;
}

public ImageGrise assombrir() {
        ImageGrise result = new ImageDict2(largeur, hauteur);
        for (int y=0; y<hauteur; y++)
                for (int x=0; x<largeur; x++)
                        result.definirPoint(x, y, this.pointEn(x,y).assombrir());
        return result;
}

public ImageGrise dupliquer() {
        ImageGrise result = new ImageDict2(largeur, hauteur);
        for (int y=0; y<hauteur; y++)
                for (int x=0; x<largeur; x++)
                        result.definirPoint(x, y, this.pointEn(x,y));
        return result;
}

public ImageGrise ajouter(ImageGrise img) {
        ImageGrise result = new ImageDict2(largeur, hauteur);
        if (this.incompatible(img))
                return result;
        for (int y=0; y<hauteur; y++)
                for (int x=0; x<largeur; x++)
                        result.definirPoint(x, y,
                                            this.pointEn(x,y).ajouter(img.pointEn(x,y)));
        return result;
}

public ImageGrise soustraire(ImageGrise img) {
        ImageGrise result = new ImageDict2(largeur, hauteur);
        if (this.incompatible(img))
                return result;
        for (int y=0; y<hauteur; y++)
                for (int x=0; x<largeur; x++)
                        result.definirPoint(x, y,
                                            this.pointEn(x,y).soustraire(img.pointEn(x,y)));
        return result;
}

public ImageGrise XOR(ImageGrise img) {
        ImageGrise result = new ImageDict2(largeur, hauteur);
        if (this.incompatible(img))
                return result;
        for (int y=0; y<hauteur; y++)
                for (int x=0; x<largeur; x++)
                        result.definirPoint(x, y,
                                            this.pointEn(x,y).XOR(img.pointEn(x,y)));
        return result;
}

public ImageGrise intersection(ImageGrise img) {
        ImageGrise result = new ImageDict2(largeur, hauteur);
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
        ImageGrise result = new ImageDict2(largeur, hauteur);
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
