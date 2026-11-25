package ca.qc.bdeb.sim.tp2walasheima;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Antagonistes extends ElementsJeu{

    final static int WIDTH = 120;
    final static int HEIGHT = 104;

    Image image;
    Random r = new Random();

    public Antagonistes(Point2D position, Image image) {
        super(position);
        this.velocite = velocite;
        this.taille = taille;
        this.image = image;
        this.acceleration = new Point2D(-500,0);//accélération vers la gauche


        double y = (0.2 + r.nextDouble() * (0.8 - 0.2) )*HEIGHT;//position aléatoire en y entre 1/5 et 4/5 inclus

        double tailleX= (50 + r.nextDouble()) * (HEIGHT - 50);//hauteur
        double tailleY= (tailleX * WIDTH) / HEIGHT;//largeur dépend de la hauteur pour que ce soit proportionnelle

        //double velociteX= - (100 * Math.pow(numeroNiveau,0.33) +200 ); UMMM numeornIVEAU est en rouge alors j'ai mis en commentaire pour l'instant
        //Vitesse verticale : random entre 0 et 100px/s, random vers le bas ou vers le haut


        int numeroPoisson = r.nextInt(5);
        image = new Image("poisson" + numeroPoisson+ ".png");// huhhhh



    }

    @Override
    public void draw(GraphicsContext context) {
        context.drawImage(image, position.getX(),position.getY(),taille.getX(),taille.getY());

    }

}
