package ca.qc.bdeb.sim.tp2walasheima;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.security.Key;

public class PoissonGentil extends ElementsJeu{
    final static int WIDTH = 102;
    final static int HEIGHT = 90;
    private ImageView gontrand;

    public PoissonGentil(Point2D position){
        super(position);
        gontrand = new ImageView("gontrand.png");
        taille = new Point2D(WIDTH, HEIGHT);
        gontrand.setPreserveRatio(true);
        velocite = VELOCITE_INITIALE;
        acceleration = ACCELERATION_INITIALE;
    }

    public Point2D getPosition(){ return position; }

    public Point2D getTaille(){ return taille; }

    public void draw(GraphicsContext context){
        context.drawImage(gontrand.getImage(), position.getX(), position.getY());
    }


    public void update (double deltaTemps){

        boolean haut = Input.isKeyPressed(KeyCode.UP);
        boolean bas = Input.isKeyPressed(KeyCode.DOWN);
        boolean droite = Input.isKeyPressed(KeyCode.RIGHT);
        boolean gauche = Input.isKeyPressed(KeyCode.LEFT);

        //peux pas aller plus vite de +-300 m/s
        velocite = new Point2D(Math.clamp(velocite.getX(), -300, 300),
                Math.clamp(velocite.getY(), -300, 300));

        if (haut)
            acceleration = new Point2D(acceleration.getX(), 1000);
        else if (bas)
            acceleration = new Point2D(acceleration.getX(), -1000);
        else if (droite)
            acceleration = new Point2D(1000, acceleration.getY());
        else if (gauche)
            acceleration = new Point2D(-1000, acceleration.getY());
        else if (Math.abs(velocite.getX()) > 0.5 || Math.abs(velocite.getY()) > 0.5) {
            // ralentir si la vitesse est un minimum élevée
            int signeX = velocite.getX() > 0 ? -1 : +1;
            int signeY = velocite.getY() > 0 ? -1 : +1;
            acceleration = new Point2D(signeX * 500, signeY * 500);
        } else {
            velocite = new Point2D(0, 0);
            acceleration = new Point2D(0, 0);
        }

        updatePhysique(deltaTemps);
    }

}
