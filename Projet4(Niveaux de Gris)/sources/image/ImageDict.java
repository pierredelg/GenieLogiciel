package image;

import dictionnaire.correction.Couple;
import dictionnaire.correction.CoupleObj;
import dictionnaire.correction.Dictionnaire;
import dictionnaire.correction.TabDict;

public class ImageDict implements ImageGrise {

    Dictionnaire<Couple<Integer,Integer>,NiveauGris> dictionnaire;
    int longeur;
    int hauteur;

    public ImageDict(int longeur, int hauteur) {
        this.longeur = longeur;
        this.hauteur = hauteur;
        dictionnaire = new TabDict<>();
        for(int i = 0 ; i < longeur ; i++){
            for (int j = 0; j < hauteur;j++){
                dictionnaire.ajouter(new CoupleObj<>(i, j),NiveauGris.BLANC);
            }
        }

    }

    @Override
    public int largeur() {
        return 0;
    }

    @Override
    public int hauteur() {
        return 0;
    }

    @Override
    public NiveauGris pointEn(int x, int y) {
        return null;
    }

    @Override
    public void definirPoint(int x, int y, NiveauGris gris) {

    }

    @Override
    public void allumer(int x, int y) {

    }

    @Override
    public void eteindre(int x, int y) {

    }

    @Override
    public void randomize() {

    }

    @Override
    public int compterPoints(NiveauGris gris) {
        return 0;
    }

    @Override
    public ImageGrise inverser() {
        return null;
    }

    @Override
    public ImageGrise eclaircir() {
        return null;
    }

    @Override
    public ImageGrise assombrir() {
        return null;
    }

    @Override
    public ImageGrise dupliquer() {
        return null;
    }

    @Override
    public ImageGrise ajouter(ImageGrise img) {
        return null;
    }

    @Override
    public ImageGrise soustraire(ImageGrise img) {
        return null;
    }

    @Override
    public ImageGrise XOR(ImageGrise img) {
        return null;
    }

    @Override
    public ImageGrise intersection(ImageGrise img) {
        return null;
    }

    @Override
    public NiveauGris niveauMoyen() {
        return null;
    }

    @Override
    public ImageGrise augmenterContraste() {
        return null;
    }
}
