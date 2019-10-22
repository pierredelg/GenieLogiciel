package image;

import dictionnaire.correction.Couple;
import dictionnaire.correction.CoupleObj;
import dictionnaire.correction.Dictionnaire;
import dictionnaire.correction.TabDict;

  /*            ********************  REPONSES AUX QUESTIONS  ********************

    Question 4 :

    Cette classe (ImageDict) est plus lente car elle utilise un dictionnaire qui est un objet.
    Alors que la classe ImageTab utilise un tableau.
    L'accès au données du tableau est beaucoup plus rapide car il est direct.
    On ne passe pas par des méthodes pour accéder à son contenu.
    De plus, avec ImageTab, on utilise des int qui sont des types primitifs et donc l'accès aux données est aussi plus direct.
    Une solution pour accélérer le temps d'excution dans cette classe serait de
    remplacer le Couple par la classe Point qui utilise des int et implemente l'interface Couple.

    Question 6 :
    Avec image.Viewer, si on ajoute l'image du cheval à celle de l'éléphant, on obtient une image représentant un aigle.

    */

/**
 * Classe représentant une image en niveaux de gris avec un Dictionnaire
 * contenant les coordonnées de chaque pixel associées à un niveau de gris.
 */
public class ImageDict implements ImageGrise {

    /**
     *L'objet Dictionnaire associant un Couple de Integer avec un NiveauGris.
     */
    private Dictionnaire<Couple<Integer,Integer>,NiveauGris> dictionnaire;

    /**
     * La largeur de l'image.
     */
    private int largeur;

    /**
     * La hauteur de l'image.
     */
    private int hauteur;

    /**
     * Le constructeur de l'ImageDict. Avec en parametre la largeur et la hauteur de l'image.
     * @param largeur - La largeur de l'image.
     * @param hauteur - La hauteur de l'image.
     */
    public ImageDict(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        dictionnaire = new TabDict<>();
    }

    /**
     * Donne la largeur de l'image.
     * @return la largeur de l'image.
     */
    @Override
    public int largeur() {
        return largeur;
    }

    /**
     * La hauteur de l'image.
     * @return - La hauteur de l'image.
     */
    @Override
    public int hauteur() {
        return hauteur;
    }

    /**
     * Donne le niveau de gris d'une position dans l'image donnée en parametre.
     * @param x - La position en largeur.
     * @param y - La position en hauteur.
     * @return Le NiveauGris à la position donnée.
     */
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

    /**
     * Définie le niveau de gris (NiveauGris) sur une position dans l'image.
     * Si le niveau de gris est BLANC on retire le point de l'image dans le dictionnaire.
     * @param x  - La position en largeur.
     * @param y  - La position en hauteur.
     * @param gris - Le niveau de gris (NiveauGris)
     */
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

    /**
     * Définie le niveau de gris sur NOIR pour une position dans l'image.
     * @param x - La position en largeur.
     * @param y - La position en hauteur.
     */
    @Override
    public void allumer(int x, int y) {
        if (x >= 0 && x < largeur && y >= 0 && y < hauteur) {
            dictionnaire.ajouter(new CoupleObj<>(x, y), NiveauGris.NOIR);
        }
    }

    /**
     * Définie le niveau de gris à BLANC pour une position dans l'image.
     * Dans ce cas, supprime le niveau de gris à cette position.
     * @param x - La position en largeur.
     * @param y - La position en hauteur.
     */
    @Override
    public void eteindre(int x, int y) {
        dictionnaire.enleverPour(new CoupleObj<>(x,y));
    }

    /**
     * Définie un niveau de gris aléatoire pour chaque position dans l'image.
     * N'ajoute rien pour un niveau de gris BLANC.
     */
    @Override
    public void randomize() {
        for(int i = 0; i < largeur; i++){
            for (int j = 0; j < hauteur;j++){
                NiveauGris niveauGris = NiveauGris.randomize();
                if(!niveauGris.estBlanc()) {
                    dictionnaire.ajouter(new CoupleObj<>(i, j), niveauGris);
                }
            }
        }
    }

    /**
     * Compte le nombre de NiveauGris présent dans l'image.
     * @param gris - Le niveau de gris (NiveauGris)
     * @return - Le nombre de points possedant le niveau de gris donné en parametre.
     */
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

    /**
     * Donne une nouvelle image avec pour chaque point de l'image son niveau de gris inverse.
     * @return - Une nouvelle image contenant l'inverse des niveaux de gris présents dans l'image courante.
     */
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

    /**
     * Donne une nouvelle image avec pour chaque point de l'image son niveau de gris plus clair.
     * @return Une nouvelle image contenant une nuance plus claire des niveaux de gris présents dans l'image courante.
     */
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

    /**
     Donne une nouvelle image avec pour chaque point de l'image son niveau de gris plus foncé.
     * @return Une nouvelle image contenant une nuance plus foncée des niveaux de gris présents dans l'image courante.
     */
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

    /**
     * Duplique l'image courante.
     * @return Une nouvelle image identique à l'image courante.
     */
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

    /**
     * Donne une nouvelle image qui est l'addition de chaque point de l'image donnée en parametre avec l'image courante.
     * @param img - L'image (ImageGrise) à ajouter à l'image courante.
     * @return - Une nouvelle image qui est l'addition de chaque point de l'image donnée en parametre avec l'image courante
     */
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

    /**
     * Donne une nouvelle image qui est la soustraction de chaque point de l'image donnée en parametre à l'image courante.
     * @param img - L'image (ImageGrise) à soustraire à l'image courante.
     * @return - Une nouvelle image qui est la soustraction de chaque point de l'image donnée en parametre à l'image courante
     */
    @Override
    public ImageGrise soustraire(ImageGrise img) {
        ImageGrise imageGrise = new ImageDict(largeur(), hauteur());

        if (largeur() == img.largeur() && hauteur() == img.hauteur()) {
            for (int i = 0; i < largeur(); i++) {
                for (int j = 0; j < hauteur(); j++) {
                    NiveauGris niveauGris = dictionnaire.valeurPour(new CoupleObj<>(i,j));
                    if(niveauGris != null) {
                        imageGrise.definirPoint(i, j, niveauGris.soustraire(img.pointEn(i, j)));
                    }
                }
            }
        }
        return imageGrise;
    }

    /**
     * Donne une nouvelle image qui est le résultat du XOR de chaque point de l'image donnée en parametre avec l'image courante.
     * @param img - L'image (ImageGrise) avec laquelle on applique le XOR à l'image courante.
     * @return - Une nouvelle image qui est le résultat du XOR de chaque point de l'image donnée en parametre à l'image courante
     */
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

    /**
     * Donne une nouvelle image dont chaque point est présent à la fois dans l'image donnée en parametre et dans l'image courante pour la même position.
     * @param img - L'image (ImageGrise) avec laquelle faire l'intersection de l'image courante.
     * @return - Une nouvelle image dont chaque point est présent à la fois dans l'image donnée en parametre et dans l'image courante pour la même position.
     */
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

    /**
     * Donne le niveau moyen de gris dans l'image courante.
     * @return - Le niveau moyen de gris dans l'image courante.
     */
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

    /**
     * Donne une nouvelle image dans laquelle le contraste est augmenté pour chaque point de l'image.
     * On eclaircit les points clairs et on assombrit les points sombre.
     * @return - Une nouvelle image dont le contraste est augmenté.
     */
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

    /**
     * Donne une image dans laquelle on optimise l'utilisation de la mémoire selon le nombre de point BLANC.
     * Car une ImageDict ne contient aucun BLANC. Ainsi, une image contenant uniquement des blancs
     * sera chargée plus rapidement si c'est une ImageDict car le dictionnaire sera vide.
     * @return - Une ImageTab si l'image ne contient pas beaucoup de BLANC et une ImageDict si la proportion de BLANC est importante.
     */
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
            return imageTab;
        }
        return this;
    }
}
