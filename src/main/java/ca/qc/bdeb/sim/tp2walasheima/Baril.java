package ca.qc.bdeb.sim.tp2walasheima;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;

//quand poisson tape baril il change de projectile
public class Baril extends ElementsJeu{
    private ImageView baril;

    public Baril(Point2D position){
        super(position);
        baril = new ImageView("baril.png");
    }

    @Override
    public void draw(GraphicsContext context) {

    }
}
