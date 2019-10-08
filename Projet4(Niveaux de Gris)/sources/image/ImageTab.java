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

        if (x >= 0 && x < niveauGris.length && y >= 0 && y < niveauGris.length) {
            return niveauGris[x][y];
        }
        return null;
    }

    @Override
    public void definirPoint(int x, int y, NiveauGris gris) {
        if (x >= 0 && x < niveauGris.length && y >= 0 && y < niveauGris.length) {
            this.niveauGris[x][y] = gris;
        }
    }

    @Override
    public void allumer(int x, int y) {
        if (x >= 0 && x < niveauGris.length && y >= 0 && y < niveauGris.length) {
            niveauGris[x][y] = NiveauGris.NOIR;
        }
    }

    @Override
    public void eteindre(int x, int y) {
        if (x >= 0 && x < niveauGris.length && y >= 0 && y < niveauGris.length) {
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

        int nbPoint = largeur() * hauteur();
        int nbBlanc = compterPoints(NiveauGris.BLANC);
        int nbClair = compterPoints(NiveauGris.GRIS_CLAIR);
        int nbMoyen = compterPoints(NiveauGris.GRIS_MOYEN);
        int nbFonce = compterPoints(NiveauGris.GRIS_FONCE);
        int nbNoir = compterPoints(NiveauGris.NOIR);

        double moyennePoints = ((nbBlanc*0) + (nbClair*1) + (nbMoyen*2) + (nbFonce*3) + (nbNoir*4))/nbPoint;

        return NiveauGris.deNiveau((int)moyennePoints);

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