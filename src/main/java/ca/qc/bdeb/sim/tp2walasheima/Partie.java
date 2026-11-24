package ca.qc.bdeb.sim.tp2walasheima;

import javafx.geometry.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

import static ca.qc.bdeb.sim.tp2walasheima.JeuPoisson.HEIGHT;
import static ca.qc.bdeb.sim.tp2walasheima.JeuPoisson.WIDTH;

public class Partie {
    private PoissonGentil gontrand;

    Partie(){
        gontrand = new PoissonGentil(new Point2D(100, 45));
    }

    public void update(double deltaTemps, KeyCode code){
            gontrand.update(deltaTemps, code);
    }

    public void draw(GraphicsContext context){
        context.clearRect(0,0, WIDTH, HEIGHT);
        gontrand.draw(context);
    }
}
