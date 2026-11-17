package ca.qc.bdeb.sim.tp2walasheima;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class PoissonGentil extends ElementsJeu{
    final static int WIDTH = 102;
    final static int HEIGHT = 90;
    private ImageView gontrand;
    private Point2D position;
    private Point2D taille;
    private Point2D velocite;
    private Point2D acceleration;

    public PoissonGentil(Point2D position){
        this.position = position;
        gontrand = new ImageView("gontrand.png");
        taille = new Point2D(WIDTH, HEIGHT);
        gontrand.setPreserveRatio(true);
        velocite = new Point2D(0,0); //vitesse initiale est nulle.
    }

    public Point2D getPosition(){ return position; }

    public Point2D getTaille(){ return taille; }

    public void draw(GraphicsContext context){
        context.drawImage(gontrand.getImage(), position.getX(), position.getY());
    }

    public void update(double deltaTemps, double x1, double y1){
        boolean haut = Input.isKeyPressed(KeyCode.UP);
        boolean bas = Input.isKeyPressed(KeyCode.DOWN);
        boolean droite = Input.isKeyPressed(KeyCode.RIGHT);
        boolean gauche = Input.isKeyPressed(KeyCode.LEFT);

        if(haut)
            velocite = new Point2D(velocite.getX(), 1000);
        else if(bas)
            velocite = new Point2D(velocite.getX(), -1000);
        else if(droite)
            velocite = new Point2D(1000, velocite.getY());
        else if (gauche)
            velocite = new Point2D(-1000, velocite.getY());
        else
            velocite = new Point2D(0, 0);

        acceleration = new Point2D(velocite.getX()/deltaTemps, velocite.getY()/deltaTemps);

        //positions finales après le update en x et y
        double x2 = x1 + velocite.getX()*deltaTemps + acceleration.getX()*1/2*Math.pow(deltaTemps, 2);
        double y2 = y1 + velocite.getY()*deltaTemps + acceleration.getY()*1/2*Math.pow(deltaTemps, 2);
        position = new Point2D(x2, y2);
    }
}
