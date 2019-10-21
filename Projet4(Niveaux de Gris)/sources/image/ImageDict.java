package image;

import dictionnaire.correction.Couple;
import dictionnaire.correction.CoupleObj;
import dictionnaire.correction.Dictionnaire;
import dictionnaire.correction.TabDict;

/**
 * Classe représentant une image en niveaux de gris avec un Dictionnaire
 * contenant les coordonnées de chaque pixel associées à un niveau de gris.
 */
public class ImageDict implements ImageGrise {

    /*Réponse question 4 :
        Cette classe est plus lente car elle utilise un dictionnaire qui est un objet.
        Alors que la classe ImageTab utilise un tableau.
        L'accès au données du tableau est beaucoup plus rapide car il est direct.
        On ne passe pas par des méthodes pour accéder à son contenu.
        Une solution pour accélérer le temps d'excution dans cette classe serait de
         remplacer le Couple par la classe Point.
    */
    private Dictionnaire<Couple<Integer,Integer>,NiveauGris> dictionnaire;
    private int largeur;
    private int hauteur;

    public ImageDict(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        dictionnaire = new TabDict<>();
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
        NiveauGris niveauGris = null;
        if (x >= 0 && x < largeur && y >= 0 && y < hauteur) {
            niveauGris = dictionnaire.valeurPour(new CoupleObj<>(x, y));
            if(niveauGris == null){
                niveauGris = NiveauGris.BLANC;
            }
        }
        return niveauGris;
    }

    @Override
    public void definirPoint(int x, int y, NiveauGris gris) {
        CoupleObj <Integer,Integer> coupleObj = new CoupleObj<>(x, y);
        NiveauGris niveauGris = dictionnaire.valeurPour(coupleObj);
        if(gris.estBlanc() && niveauGris != null){
            dictionnaire.enleverPour(coupleObj);
        }else {
            if (x >= 0 && x < largeur && y >= 0 && y < hauteur) {
                dictionnaire.ajouter(new CoupleObj<>(x, y), gris);
            }
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
        dictionnaire.enleverPour(new CoupleObj<>(x,y));
    }

    @Override
    public void randomize() {
        for(int i = 0; i < largeur; i++){
            for (int j = 0; j < hauteur;j++){
                NiveauGris niveauGris = NiveauGris.BLANC;
                while(niveauGris.estBlanc()){
                    niveauGris = NiveauGris.randomize();
                }
                dictionnaire.ajouter(new CoupleObj<>(i, j),niveauGris);
            }
        }
    }

    @Override
    public int compterPoints(NiveauGris gris) {
        int nombre = 0;

        for(int i = 0; i < largeur; i++){
            for (int j = 0; j < hauteur;j++){
                NiveauGris niveauGris = dictionnaire.valeurPour(new CoupleObj<>(i,j));
               if((niveauGris != null && gris.code() == niveauGris.code()) || (gris.estBlanc() && niveauGris == null)){
                   nombre++;
               }
            }
        }
        return nombre;
    }

    @Override
    public ImageGrise inverser() {
        ImageGrise imageGrise = new ImageDict(largeur(), hauteur());

        for (int i = 0; i < largeur(); i++) {
            for (int j = 0; j < hauteur(); j++) {
                NiveauGris niveauGris = dictionnaire.valeurPour(new CoupleObj<>(i,j));
                if(niveauGris != null) {
                    imageGrise.definirPoint(i, j,niveauGris.inverser());
                }else{
                    imageGrise.definirPoint(i, j,NiveauGris.NOIR);
                }
            }
        }
        return imageGrise;
    }

    @Override
    public ImageGrise eclaircir() {
        ImageGrise imageGrise = new ImageDict(largeur(), hauteur());

        for (int i = 0; i < largeur(); i++) {
            for (int j = 0; j < hauteur(); j++) {
                NiveauGris niveauGris = dictionnaire.valeurPour(new CoupleObj<>(i,j));
                if (niveauGris != null) {
                    imageGrise.definirPoint(i, j, niveauGris.eclaircir());
                }
            }
        }
        return imageGrise;
    }

    @Override
    public ImageGrise assombrir() {
        ImageGrise imageGrise = new ImageDict(largeur(), hauteur());

        for (int i = 0; i < largeur(); i++) {
            for (int j = 0; j < hauteur(); j++) {
                NiveauGris niveauGris = dictionnaire.valeurPour(new CoupleObj<>(i,j));
                if(niveauGris != null && !niveauGris.estNoir()) {
                    imageGrise.definirPoint(i, j, niveauGris.assombrir());

                }
                if (niveauGris == null){
                    imageGrise.definirPoint(i, j,NiveauGris.GRIS_CLAIR);
                }
            }
        }

        return imageGrise;
    }

    @Override
    public ImageGrise dupliquer() {
        ImageGrise imageGrise = new ImageDict(largeur(), hauteur());

        for (int i = 0; i < largeur(); i++) {
            for (int j = 0; j < hauteur(); j++) {
                NiveauGris niveauGris = dictionnaire.valeurPour(new CoupleObj<>(i,j));
                if (niveauGris != null) {
                    imageGrise.definirPoint(i, j, niveauGris);
                }
            }
        }

        return imageGrise;
    }

    @Override
    public ImageGrise ajouter(ImageGrise img) {
        ImageGrise imageGrise = new ImageDict(largeur(), hauteur());

        if (largeur() == img.largeur() && hauteur() == img.hauteur()) {
            for (int i = 0; i < largeur(); i++) {
                for (int j = 0; j < hauteur(); j++) {
                    NiveauGris niveauGris = dictionnaire.valeurPour(new CoupleObj<>(i,j));
                    if(niveauGris != null) {
                        imageGrise.definirPoint(i, j, niveauGris.ajouter(img.pointEn(i, j)));
                    }else{
                        imageGrise.definirPoint(i,j,img.pointEn(i, j));
                    }
                }
            }
        }
        return imageGrise;
    }

    @Override
    public ImageGrise soustraire(ImageGrise img) {
        ImageGrise imageGrise = new ImageDict(largeur(), hauteur());

        if (largeur() == img.largeur() && hauteur() == img.hauteur()) {
            for (int i = 0; i < largeur(); i++) {
                for (int j = 0; j < hauteur(); j++) {
                    NiveauGris niveauGris = dictionnaire.valeurPour(new CoupleObj<>(i,j));
                    if(niveauGris != null) {
                        imageGrise.definirPoint(i, j, img.pointEn(i, j).soustraire(niveauGris));
                    }
                }
            }
        }
        return imageGrise;
    }

    @Override
    public ImageGrise XOR(ImageGrise img) {
        ImageGrise imageGrise = new ImageDict(largeur(), hauteur());

        if (largeur() == img.largeur() && hauteur() == img.hauteur()) {
            for (int i = 0; i < largeur(); i++) {
                for (int j = 0; j < hauteur(); j++) {
                    NiveauGris niveauGris = dictionnaire.valeurPour(new CoupleObj<>(i,j));
                    if(niveauGris != null) {
                        imageGrise.definirPoint(i, j, niveauGris.XOR(img.pointEn(i, j)));
                    }else{
                        imageGrise.definirPoint(i, j, NiveauGris.BLANC.XOR(img.pointEn(i, j)));
                    }
                }
            }
        }
        return imageGrise;
    }

    @Override
    public ImageGrise intersection(ImageGrise img) {
        ImageGrise imageGrise = new ImageDict(largeur(), hauteur());

        if (largeur() == img.largeur() && hauteur() == img.hauteur()) {

            for (int i = 0; i < largeur(); i++) {
                for (int j = 0; j < hauteur(); j++) {
                    NiveauGris niveauGris = dictionnaire.valeurPour(new CoupleObj<>(i,j));
                    if (niveauGris != null && niveauGris.code() == img.pointEn(i, j).code()) {
                        imageGrise.definirPoint(i, j, niveauGris);
                    }
                }
            }
        }
        return imageGrise;
    }

    @Override
    public NiveauGris niveauMoyen() {

        int nbPoints = largeur() * hauteur();
        int nbPointsClairs = compterPoints(NiveauGris.GRIS_CLAIR);
        int nbPointsMoyens = compterPoints(NiveauGris.GRIS_MOYEN);
        int nbPointsFonces = compterPoints(NiveauGris.GRIS_FONCE);
        int nbPointsNoirs = compterPoints(NiveauGris.NOIR);

        int moyennePoints = 0;

        if(nbPoints != 0) {
            moyennePoints = (nbPointsClairs + (nbPointsMoyens * 2) + (nbPointsFonces * 3) + (nbPointsNoirs * 4)) /  nbPoints;
        }

        return NiveauGris.deNiveau(moyennePoints);

    }

    @Override
    public ImageGrise augmenterContraste() {
        ImageGrise imageGrise = new ImageDict(largeur(), hauteur());

        NiveauGris niveauGrisMoyen = niveauMoyen();

        for (int i = 0; i < largeur(); i++) {
            for (int j = 0; j < hauteur(); j++) {
                NiveauGris niveauGris = dictionnaire.valeurPour(new CoupleObj<>(i,j));
                if(niveauGris != null) {
                    if (niveauGris.compareTo(niveauGrisMoyen) > 0) {
                        imageGrise.definirPoint(i, j, niveauGris.assombrir());
                    } else {
                        if (niveauGris.compareTo(niveauGrisMoyen) < 0) {
                            imageGrise.definirPoint(i, j, niveauGris.eclaircir());
                        } else {
                            imageGrise.definirPoint(i, j, niveauGris);
                        }
                    }
                }
            }
        }
        return imageGrise;
    }

    @Override
    public ImageGrise optimiser() {

        double nbDeBlancs = compterPoints(NiveauGris.BLANC);
        double nbDePoints = largeur() * hauteur();
        double proportion = nbDeBlancs/nbDePoints;
        if(proportion < 0.1){
            ImageTab imageTab = new ImageTab(largeur(),hauteur());
            for(int i = 0;i < largeur();i++){
                for(int j = 0; j < hauteur();j++){
                    NiveauGris niveauGris = pointEn(i,j);
                    if (niveauGris != null) {
                        imageTab.definirPoint(i, j, niveauGris);
                    }
                    else{
                        imageTab.definirPoint(i, j, NiveauGris.BLANC);
                    }
                }
            }
        }
        return this;
    }
}
