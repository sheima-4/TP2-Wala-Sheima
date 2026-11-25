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
        super(position);
        double y = (0.2 + r.nextDouble() * (0.8 - 0.2) )*JeuPoisson.HEIGHT;//position aléatoire en y entre 1/5 et 4/5 inclus
        position = new Point2D(JeuPoisson.WIDTH,y);// commence par la droite

        int numeroPoisson = r.nextInt(1,6);
        image = new Image("poisson" + numeroPoisson+ ".png");

        double tailleX= 50 +( r.nextDouble() * (WIDTH - 50));//hauteur
        double tailleY= (tailleX * WIDTH) / HEIGHT;//largeur dépend de la hauteur pour que ce soit proportionnelle
        taille = new Point2D(tailleX, tailleY);

        double velociteX=- (100 * Math.pow(numeroNiveau,0.33) +200 );
        double velociteY=r.nextDouble(1,101);

        if(r.nextBoolean()==true){//détermine si le poisson va vers le haut ou vers le bas dépendemment du signe de la vitesse en y
            velociteY= velociteY;//vitesse en y reste positif
        } else {
            velociteY= -velociteY;// vitesse en y est négatif
        }

        velocite = new Point2D(velociteX, velociteY);

        acceleration = new Point2D(-500,0);//accélération vers la gauche

    }


    public void update(double deltaTemps){

        updatePhysique(deltaTemps);

    }


    @Override
    public void draw(GraphicsContext context) {
        context.drawImage(image, position.getX(),position.getY(),taille.getX(),taille.getY());

    }

}
