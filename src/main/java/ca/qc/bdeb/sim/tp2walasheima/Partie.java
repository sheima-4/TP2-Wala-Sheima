package ca.qc.bdeb.sim.tp2walasheima;

import javafx.geometry.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.Random;

import static ca.qc.bdeb.sim.tp2walasheima.JeuPoisson.HEIGHT;
import static ca.qc.bdeb.sim.tp2walasheima.JeuPoisson.WIDTH;

public class Partie {
    private PoissonGentil gontrand;
    ArrayList<Antagonistes> poissonsAntagonistes = new ArrayList<>();

    int numeroNiveau = 1;
    double nSeconde;
    double temps=0;


    Random r = new Random();//pour 1 a 5 poisson appararait au hasard

    private void AntagonistesHasard(){
        int nombreAntagonistes = r.nextInt(1,6);

        for(int i = 0; i < nombreAntagonistes; i++){
            poissonsAntagonistes.add(new Antagonistes(new Point2D(JeuPoisson.WIDTH,0),numeroNiveau));//bruhh
        }
    }

    Partie(){
        gontrand = new PoissonGentil(new Point2D(100, 45));

        nSeconde = 0.75 + (1 / Math.sqrt(numeroNiveau));
    }

    public void update(double deltaTemps){
        gontrand.update(deltaTemps);

        temps += deltaTemps;
        if(temps >= nSeconde){
            AntagonistesHasard();
            temps = 0;
        }

        for (int i=0;i< poissonsAntagonistes.size(); i++){
            poissonsAntagonistes.get(i).update(deltaTemps);
        }
    }

    public void draw(GraphicsContext context){
        context.clearRect(0,0, WIDTH, HEIGHT);
        gontrand.draw(context);

        for (int i = 0; i< poissonsAntagonistes.size(); i++){
            poissonsAntagonistes.get(i).draw(context);
        }
    }
}
