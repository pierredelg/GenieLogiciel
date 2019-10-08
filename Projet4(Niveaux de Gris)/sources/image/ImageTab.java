package image;

public class ImageTab implements ImageGrise {

    private NiveauGris[][] niveauGris;

    public ImageTab(int largeur, int hauteur) {

        niveauGris = new NiveauGris[largeur][hauteur];

        for (int i = 0; i < niveauGris.length; i++) {

            for (int j = 0; j < niveauGris[0].length; j++) {

                this.eteindre(i, j);
            }
        }
    }

    @Override
    public int largeur() {
        return niveauGris.length;
    }

    @Override
    public int hauteur() {
        return niveauGris[0].length;
    }

    @Override
    public NiveauGris pointEn(int x, int y) {

        if (x >= 0 && x < largeur() && y >= 0 && y < hauteur()) {
            return niveauGris[x][y];
        }
        return null;
    }

    @Override
    public void definirPoint(int x, int y, NiveauGris gris) {
        if (x >= 0 && x < largeur() && y >= 0 && y < hauteur()) {
            this.niveauGris[x][y] = gris;
        }
    }

    @Override
    public void allumer(int x, int y) {
        if (x >= 0 && x < largeur() && y >= 0 && y < hauteur()) {
            niveauGris[x][y] = NiveauGris.NOIR;
        }
    }

    @Override
    public void eteindre(int x, int y) {
        if (x >= 0 && x < largeur() && y >= 0 && y < hauteur()) {
            niveauGris[x][y] = NiveauGris.BLANC;
        }
    }

    @Override
    public void randomize() {

        for (int i = 0; i < largeur(); i++) {
            for (int j = 0; j < hauteur(); j++) {
                niveauGris[i][j] = NiveauGris.randomizeNB();
            }
        }
    }

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
}