package image;

import java.util.ArrayList;
import java.io.*;

/** Catalogue d'images en niveaux de gris (generees par des methodes statiques) :
 * quelques pieces honorables heraldiques (en noir et blanc) ainsi que des images
 * obtenue par des calculs parametriques (ellipses) */
class Exemples {
public static enum IMPLEMENTATION {
        IMAGE_TAB { public ImageMaker getFactory() {
                            return new ImageTabMaker();
                    }
        },
        IMAGE_DICT { public ImageMaker getFactory() {
                             return new ImageDictMaker();
                     }
        },
        IMAGE_DOUBLE_DICT { public ImageMaker getFactory() {
                                    return new ImageDoubleDictMaker();
                            }
        },
        IMAGE_DICT2 { public ImageMaker getFactory() {
                              return new ImageDict2Maker();
                      }
        },
        IMAGE_DOUBLE_DICT2 { public ImageMaker getFactory() {
                                     return new ImageDoubleDict2Maker();
                             }
        };
        public abstract ImageMaker getFactory();
}

private static ImageMaker factory = new ImageTabMaker();

abstract static class ImageMaker {
public abstract ImageGrise createImage(int w, int h);
}

public static class ImageTabMaker extends ImageMaker {
public ImageGrise createImage(int w, int h) {
        return new ImageTab(w, h);
}
}

public static class ImageDictMaker extends ImageMaker {
public ImageGrise createImage(int w, int h) {
        return new ImageDict(w, h);
}
}

public static class ImageDoubleDictMaker extends ImageMaker {
public ImageGrise createImage(int w, int h) {
        return new ImageDoubleDict(w, h);
}
}

public static class ImageDict2Maker extends ImageMaker {
public ImageGrise createImage(int w, int h) {
        return new ImageDict2(w, h);
}
}

public static class ImageDoubleDict2Maker extends ImageMaker {
public ImageGrise createImage(int w, int h) {
        return new ImageDoubleDict2(w, h);
}
}

/** A utiliser pour définir comment les images sont créées */
public static void setImplementation(IMPLEMENTATION i) {
        factory = i.getFactory();
}


public static ImageGrise readFromFile(String fileName) {
        ArrayList<String> lines = new ArrayList<String>();
        int w = 0;
        try {
                FileReader fileIn = new FileReader("images"+File.separator+fileName);
                BufferedReader in = new BufferedReader(fileIn);
                StringBuffer s = new StringBuffer();
                String line;
                while ((line = in.readLine()) != null) {
                        lines.add(line);
                        if (line.length() > w)
                                w = line.length();
                }
                fileIn.close();
        } catch (IOException e) {
                System.err.println("Erreur lors de la lecture de "+fileName);
                System.exit(1);
        }
        ImageGrise img = factory.createImage(w, lines.size());
        for (int y=0; y<lines.size(); y++) {
                String line = lines.get(y);
                for (int x=0; x<line.length(); x++)
                        img.definirPoint(x, y, NiveauGris.deCaractere(line.charAt(x)));
        }
        return img;
}


public static ImageGrise vide(int cote)
{
        return factory.createImage(cote, cote);
}

public static ImageGrise fasce()
{
        ImageGrise img = factory.createImage(21,21);
        for (int x=0; x<21; x++)
                for (int i=-2; i<=2; i++)
                        img.allumer(x, 10+i);
        return img;
}

public static ImageGrise pal()
{
        ImageGrise img = factory.createImage(21,21);
        for (int y=0; y<21; y++)
                for (int i=-2; i<=2; i++)
                        img.allumer(10+i, y);
        return img;
}

public static ImageGrise bande()
{
        ImageGrise img = factory.createImage(21,21);
        for (int x=0; x<21; x++)
                for (int i=-2; i<=2; i++)
                        img.allumer(Math.max(0,Math.min(20,x+i)), x);
        return img;
}

public static ImageGrise barre()
{
        ImageGrise img = factory.createImage(21,21);
        for (int x=0; x<21; x++)
                for (int i=-2; i<=2; i++)
                        img.allumer(Math.max(0,Math.min(20,20-(x+i))), x);
        return img;
}

public static ImageGrise ellipse1(int kx, int ky)
{
        ImageGrise img = factory.createImage(200,200);
        NiveauGris gris;
        int x, y;
        for (double angle=0; angle<2*Math.PI; angle+=0.01)
        {
                x = (int)(Math.cos(angle)*90);
                y = (int)(Math.sin(angle)*90);
                gris = NiveauGris.deLuminosite(Math.abs(Math.cos(angle*kx*ky)+1));
                img.definirPoint(x/kx+100, y/ky+100, gris.assombrir());
        }
        return img;
}

public static ImageGrise ellipse2(int phi)
{
        ImageGrise img = factory.createImage(200,200);
        NiveauGris gris;
        int x, y;
        for (double angle=0; angle<2*Math.PI; angle+=0.01)
        {
                x = (int)(Math.cos(angle+phi*Math.PI/180)*90);
                y = (int)(Math.sin(angle)*90);
                gris = NiveauGris.deLuminosite(Math.abs(Math.cos(angle*phi/Math.PI)+1));
                img.definirPoint(x/2+100, y+100, gris.assombrir());
        }
        return img;
}
}
