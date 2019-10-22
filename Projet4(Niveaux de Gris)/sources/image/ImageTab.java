package image;

/**
 * Classe représentant une image en niveaux de gris avec un tableau à deux dimensions de NiveauGris
 * contenant un niveau de gris dans chaques cases.
 * La premiere dimension représente la largeur de l'image.
 * La deuxieme dimension représente la hauteur de l'image.
 */
public class ImageTab implements ImageGrise {

    /**
     *Le tableau contenant les niveaux de gris.
     */
    private NiveauGris[][] niveauGris;

    /**
     /**
     * Le constructeur de l'ImageDict. Avec en parametre la largeur et la hauteur de l'image.
     * L'image est initialisé avec des niveaux de gris BLANC pour chaque point de l'image.
     * @param largeur - La largeur de l'image.
     * @param hauteur - La hauteur de l'image.
     */
    public ImageTab(int largeur, int hauteur) {

        niveauGris = new NiveauGris[largeur][hauteur];

        for (int i = 0; i < niveauGris.length; i++) {

            for (int j = 0; j < niveauGris[0].length; j++) {

                this.eteindre(i, j);
            }
        }
    }

    /**
     * Donne la largeur de l'image.
     * @return la largeur de l'image.
     */
    @Override
    public int largeur() {
        return niveauGris.length;
    }

    /**
     * La hauteur de l'image.
     * @return - La hauteur de l'image.
     */
    @Override
    public int hauteur() {
        return niveauGris[0].length;
    }

    /**
     * Donne le niveau de gris d'une position dans l'image donnée en parametre.
     * @param x - La position en largeur.
     * @param y - La position en hauteur.
     * @return Le NiveauGris à la position donnée.
     */
    @Override
    public NiveauGris pointEn(int x, int y) {

        if (x >= 0 && x < largeur() && y >= 0 && y < hauteur()) {
            return niveauGris[x][y];
        }
        return null;
    }

    /**
     * Définie le niveau de gris (NiveauGris) sur une position dans l'image.
     * @param x  - La position en largeur.
     * @param y  - La position en hauteur.
     * @param gris - Le niveau de gris (NiveauGris)
     */
    @Override
    public void definirPoint(int x, int y, NiveauGris gris) {
        if (x >= 0 && x < largeur() && y >= 0 && y < hauteur()) {
            this.niveauGris[x][y] = gris;
        }
    }

    /**
     * Définie le niveau de gris sur NOIR pour une position dans l'image.
     * @param x - La position en largeur.
     * @param y - La position en hauteur.
     */
    @Override
    public void allumer(int x, int y) {
        if (x >= 0 && x < largeur() && y >= 0 && y < hauteur()) {
            niveauGris[x][y] = NiveauGris.NOIR;
        }
    }

    /**
     * Définie le niveau de gris à BLANC pour une position dans l'image.
     * @param x - La position en largeur.
     * @param y - La position en hauteur.
     */
    @Override
    public void eteindre(int x, int y) {
        if (x >= 0 && x < largeur() && y >= 0 && y < hauteur()) {
            niveauGris[x][y] = NiveauGris.BLANC;
        }
    }

    /**
     * Définie un niveau de gris aléatoire pour chaque position dans l'image.
     */
    @Override
    public void randomize() {

        for (int i = 0; i < largeur(); i++) {
            for (int j = 0; j < hauteur(); j++) {
                niveauGris[i][j] = NiveauGris.randomizeNB();
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
        int nbDePoint = 0;

        for (int i = 0; i < largeur(); i++) {
            for (int j = 0; j < hauteur(); j++) {
                if (niveauGris[i][j].code() == gris.code()) {
                    nbDePoint++;
                }
            }
        }
        return nbDePoint;
    }

    /**
     * Donne une nouvelle image avec pour chaque point de l'image son niveau de gris inverse.
     * @return - Une nouvelle image contenant l'inverse des niveaux de gris présents dans l'image courante.
     */
    @Override
    public ImageGrise inverser() {

        ImageGrise imageGrise = new ImageTab(largeur(), hauteur());

        for (int i = 0; i < largeur(); i++) {
            for (int j = 0; j < hauteur(); j++) {
                imageGrise.definirPoint(i, j, niveauGris[i][j].inverser());
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
        ImageGrise imageGrise = new ImageTab(largeur(), hauteur());

        for (int i = 0; i < largeur(); i++) {
            for (int j = 0; j < hauteur(); j++) {
                if (!niveauGris[i][j].estBlanc()) {
                    imageGrise.definirPoint(i, j, niveauGris[i][j].eclaircir());
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
        ImageGrise imageGrise = new ImageTab(largeur(), hauteur());

        for (int i = 0; i < largeur(); i++) {
            for (int j = 0; j < hauteur(); j++) {
                if (!niveauGris[i][j].estNoir()) {
                    imageGrise.definirPoint(i, j, niveauGris[i][j].assombrir());
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
        ImageGrise imageGrise = new ImageTab(largeur(), hauteur());

        for (int i = 0; i < largeur(); i++) {
            for (int j = 0; j < hauteur(); j++) {
                imageGrise.definirPoint(i, j, niveauGris[i][j]);
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
        ImageGrise imageGrise = new ImageTab(largeur(), hauteur());

        if (largeur() == img.largeur() && hauteur() == img.hauteur()) {
            for (int i = 0; i < largeur(); i++) {
                for (int j = 0; j < hauteur(); j++) {
                    imageGrise.definirPoint(i, j, niveauGris[i][j].ajouter(img.pointEn(i, j)));
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
        ImageGrise imageGrise = new ImageTab(largeur(), hauteur());

        if (largeur() == img.largeur() && hauteur() == img.hauteur()) {
            for (int i = 0; i < largeur(); i++) {
                for (int j = 0; j < hauteur(); j++) {
                    imageGrise.definirPoint(i, j, img.pointEn(i, j).soustraire(niveauGris[i][j]));
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
        ImageGrise imageGrise = new ImageTab(largeur(), hauteur());

        if (largeur() == img.largeur() && hauteur() == img.hauteur()) {
            for (int i = 0; i < largeur(); i++) {
                for (int j = 0; j < hauteur(); j++) {
                    imageGrise.definirPoint(i, j, niveauGris[i][j].XOR(img.pointEn(i, j)));
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
        ImageGrise imageGrise = new ImageTab(largeur(), hauteur());

        if (largeur() == img.largeur() && hauteur() == img.hauteur()) {

            for (int i = 0; i < largeur(); i++) {
                for (int j = 0; j < hauteur(); j++) {
                    if (niveauGris[i][j].code() == img.pointEn(i, j).code()) {
                        imageGrise.definirPoint(i, j, niveauGris[i][j]);
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
        ImageGrise imageGrise = new ImageTab(largeur(), hauteur());

        NiveauGris niveauGrisMoyen = niveauMoyen();

        for (int i = 0; i < largeur(); i++) {
            for (int j = 0; j < hauteur(); j++) {
                if (niveauGris[i][j].compareTo(niveauGrisMoyen) > 0) {
                    imageGrise.definirPoint(i, j, niveauGris[i][j].assombrir());
                }else{
                    if(niveauGris[i][j].compareTo(niveauGrisMoyen) < 0){
                        imageGrise.definirPoint(i, j, niveauGris[i][j].eclaircir());
                    }else{
                        imageGrise.definirPoint(i, j, niveauGris[i][j]);
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
        if(proportion > 0.1){
             ImageDict imageDict = new ImageDict(largeur(),hauteur());
             for(int i = 0;i < largeur();i++){
                 for(int j = 0; j < hauteur();j++){
                     imageDict.definirPoint(i,j,this.pointEn(i,j));
                 }
             }
             return imageDict;
        }
        return this;
    }
}