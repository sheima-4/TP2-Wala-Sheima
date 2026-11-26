package ca.qc.bdeb.sim.tp2walasheima;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public class Antagonistes extends ElementsJeu{

    final static int WIDTH = 120;
    final static int HEIGHT = 104;

    Image image;
    Random r = new Random();

    public Antagonistes(Point2D position, double numeroNiveau) {

        //position aléatoire en y entre 1/5 et 4/5 inclus et commence par la droite
        super(new Point2D(JeuPoisson.WIDTH,(0.2 + Math.random() * (0.8 - 0.2) )*JeuPoisson.HEIGHT));// Math.random car c'est une méthode statique qui peut être utilisé dans un super

        int numeroPoisson = r.nextInt(1,6);
        image = new Image("poisson" + numeroPoisson+ ".png");

        //hauteur
        double tailleY= 50 +( r.nextDouble() * (WIDTH - 50));

        //largeur dépend de la hauteur pour que ce soit proportionnelle
        double tailleX= (tailleY * WIDTH) / HEIGHT;

        taille = new Point2D(tailleX, tailleY);

        double velociteX=- (100 * Math.pow(numeroNiveau,0.33) +200 );
        double velociteY=r.nextDouble(1,101);

        //détermine si le poisson va vers le haut ou vers le bas dépendemment du signe de la vitesse en y
        if(r.nextBoolean()==true){
            velociteY= velociteY;//vitesse en y reste positif
        } else {
            velociteY= -velociteY;// vitesse en y est négatif
        }

        velocite = new Point2D(velociteX, velociteY);

        //accélération vers la gauche
        acceleration = new Point2D(-500,0);

    }

    public boolean antagonisteSortEcran(){
        return getDroite()<0;
    }

    public void update(double deltaTemps){

        updatePhysique(deltaTemps);

    }


    @Override
    public void draw(GraphicsContext context) {
        context.drawImage(image, position.getX(),position.getY(),taille.getX(),taille.getY());

    }

}
