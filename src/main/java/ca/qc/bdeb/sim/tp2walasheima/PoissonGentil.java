package ca.qc.bdeb.sim.tp2walasheima;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

public class PoissonGentil extends ElementsJeu{
    final static int WIDTH = 102;
    final static int HEIGHT = 90;
    private ImageView gontrand;
    private Point2D position;
    private Point2D taille;

    public PoissonGentil(Point2D position){
        position = this.position;
        gontrand = new ImageView("gontrand.png");
        taille = new Point2D(WIDTH, HEIGHT);
    }
}
