package image;

import dictionnaire.correction.Couple;
import dictionnaire.correction.CoupleObj;
import dictionnaire.correction.Dictionnaire;
import dictionnaire.correction.TabDict;

public class ImageDict implements ImageGrise {

    private Dictionnaire<Couple<Integer,Integer>,NiveauGris> dictionnaire;
    private int largeur;
    private int hauteur;

    public ImageDict(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        dictionnaire = new TabDict<>();
        for(int i = 0; i < largeur; i++){
            for (int j = 0; j < hauteur;j++){
                dictionnaire.ajouter(new CoupleObj<>(i, j),NiveauGris.BLANC);
            }
        }

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
    public NiveauGris pointEn(int x, int y) {
        if (x >= 0 && x < largeur && y >= 0 && y < hauteur) {
            return dictionnaire.valeurPour(new CoupleObj<>(x, y));
        }
    }

    @Override
    public void definirPoint(int x, int y, NiveauGris gris) {
        if (x >= 0 && x < largeur && y >= 0 && y < hauteur) {
            dictionnaire.ajouter(new CoupleObj<>(x, y), gris);
        }
    }

    @Override
    public void allumer(int x, int y) {
        if (x >= 0 && x < largeur && y >= 0 && y < hauteur) {
            dictionnaire.ajouter(new CoupleObj<>(x, y), NiveauGris.NOIR);
        }
    }

    @Override
    public void eteindre(int x, int y) {
        if (x >= 0 && x < largeur && y >= 0 && y < hauteur) {
            dictionnaire.ajouter(new CoupleObj<>(x, y), NiveauGris.BLANC);
        }
    }

    @Override
    public void randomize() {
        for(int i = 0; i < largeur; i++){
            for (int j = 0; j < hauteur;j++){
                dictionnaire.ajouter(new CoupleObj<>(i, j),NiveauGris.randomize());
            }
        }
    }

    @Override
    public int compterPoints(NiveauGris gris) {
        int nombre = 0;
        for(int i = 0; i < largeur; i++){
            for (int j = 0; j < hauteur;j++){
               if(gris.code() == dictionnaire.valeurPour(new CoupleObj<>(i,j)).code()){
                   nombre++;
               }
            }
        }
        return nombre;
    }

    @Override
    public ImageGrise inverser() {
        ImageGrise imageGrise = new ImageTab(largeur(), hauteur());

        for (int i = 0; i < largeur(); i++) {
            for (int j = 0; j < hauteur(); j++) {
                imageGrise.definirPoint(i, j, dictionnaire.valeurPour(new CoupleObj<>(i,j)).inverser());
            }
        }
        return imageGrise;
    }

    @Override
    public ImageGrise eclaircir() {
        ImageGrise imageGrise = new ImageTab(largeur(), hauteur());

        for (int i = 0; i < largeur(); i++) {
            for (int j = 0; j < hauteur(); j++) {
                NiveauGris niveauGris = dictionnaire.valeurPour(new CoupleObj<>(i,j));
                if (!niveauGris.estBlanc()) {
                    imageGrise.definirPoint(i, j, niveauGris.eclaircir());
                }
            }
        }
        return imageGrise;
    }

    @Override
    public ImageGrise assombrir() {
        ImageGrise imageGrise = new ImageTab(largeur(), hauteur());

        for (int i = 0; i < largeur(); i++) {
            for (int j = 0; j < hauteur(); j++) {
                NiveauGris niveauGris = dictionnaire.valeurPour(new CoupleObj<>(i,j));
                if (!niveauGris.estNoir()) {
                    imageGrise.definirPoint(i, j, niveauGris.assombrir());
                }
            }
        }

        return imageGrise;
    }

    @Override
    public ImageGrise dupliquer() {
        ImageGrise imageGrise = new ImageTab(largeur(), hauteur());

        for (int i = 0; i < largeur(); i++) {
            for (int j = 0; j < hauteur(); j++) {
                NiveauGris niveauGris = dictionnaire.valeurPour(new CoupleObj<>(i,j));
                imageGrise.definirPoint(i, j, niveauGris);
            }
        }

        return imageGrise;
    }

    @Override
    public ImageGrise ajouter(ImageGrise img) {
        ImageGrise imageGrise = new ImageTab(largeur(), hauteur());

        if (largeur() == img.largeur() && hauteur() == img.hauteur()) {
            for (int i = 0; i < largeur(); i++) {
                for (int j = 0; j < hauteur(); j++) {
                    NiveauGris niveauGris = dictionnaire.valeurPour(new CoupleObj<>(i,j));
                    imageGrise.definirPoint(i, j, niveauGris.ajouter(img.pointEn(i, j)));
                }
            }
        }
        return imageGrise;
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
