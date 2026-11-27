package ca.qc.bdeb.sim.tp2walasheima;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.security.Key;

public class PoissonGentil extends ElementsJeu{
    final static int WIDTH = 102;
    final static int HEIGHT = 90;
    private ImageView gontrand;
    private double rayonX = WIDTH/2;
    private double rayonY = HEIGHT/2;

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
            acceleration = new Point2D(acceleration.getX(), -1000);
        else if (bas)
            acceleration = new Point2D(acceleration.getX(), 1000);
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

        gontrandSort();

        if(velocite.equals(0)){
            gontrand = new ImageView("gontrand.png");
        }else{
            gontrand = new ImageView("gontrand-avant.png");
        }


    }

    //pour éviter que Gontrand sort de l'écran
    public boolean gontrandSort(){
        boolean sort = false;
        if(getBas() >= JeuPoisson.HEIGHT) {
            sort = true;
            velocite = new Point2D(30, -30);
        } else if(getHaut() <= 0) {
            sort = true;
            velocite = new Point2D(30, +30);
        } else if(getDroite() >= JeuPoisson.WIDTH) {
            sort = true;
            velocite = new Point2D(-30, 30);
        } else if(getGauche() <= 0) {
            sort = true;
            velocite = new Point2D(+30, 30);
        }

        return sort;
    }

    public boolean enCollisionAvec(Antagonistes anta){
        var difference = position.subtract(anta.position);

        double distanceCarre = difference.dotProduct(difference);
        double sommeRayons =  this.rayonX + anta.getRayonX();

        return distanceCarre < sommeRayons * sommeRayons;
    }

    public void testCollision(Antagonistes anta){
        if (this.enCollisionAvec(anta)){
            gontrand = new ImageView("gontrand-outch.png");
            /*long dernierTime = System.nanoTime();
            long time = 0;

            double deltaTemps = (time - dernierTime) * 1e-9;
            while(deltaTemps <= 2){

            }
             */
        }
    }

    /*
    public void clignote(double deltaTemps){
        double temps = 0;
        while (temps < deltaTemps){
            gontrand.setOpacity(1);
            gontrand = new ImageView("gontrand-avant.png");
            temps += 0.25;
            gontrand = new ImageView("gontrand-outch.png");
            gontrand.setOpacity(0.5);
        }

    }
     */

}
