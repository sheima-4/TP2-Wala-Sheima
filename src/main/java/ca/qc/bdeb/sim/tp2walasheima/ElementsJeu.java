package ca.qc.bdeb.sim.tp2walasheima;

import javafx.geometry.*;
import javafx.scene.canvas.GraphicsContext;

public abstract class ElementsJeu {
    public static final Point2D VELOCITE_INITIALE = new Point2D(0,0);
    public static final Point2D ACCELERATION_INITIALE = new Point2D(0,0);
    protected double HEIGHT;
    protected double WIDTH;

    //positions
    protected Point2D position;
    protected Point2D taille;

    //physique
    protected Point2D velocite;
    protected Point2D acceleration;

    public ElementsJeu(Point2D position){
        this.position = position;
    }

    public abstract void draw(GraphicsContext context);

    //update la vitesse selon l'accélération et la position selon la vitesse
    protected void updatePhysique(double deltaTemps){
        velocite = velocite.add(acceleration.multiply(deltaTemps));
        position = position.add(velocite.multiply(deltaTemps));
    }

    //vérifie la position de l'objet
    public double getHaut(){
        return position.getY();
    }

    public double getBas(){
        return position.getY() + taille.getY();
    }

    public double getGauche(){
        return position.getX();
    }

    public double getDroite(){
        return position.getX() + taille.getX();
    }

    public Point2D getCentre(){
        return position.add(taille.multiply(1/2.0));
    }

    //get les mesures des objets
    public double getHeight(){
        return HEIGHT;
    }

    public double getWidth(){
        return WIDTH;
    }

    public boolean enCollisionAvec(ElementsJeu eJ){
        var difference = position.subtract(eJ.position);

        double distanceCarre = difference.dotProduct(difference);
        double sommeRayons = this.WIDTH/2 + eJ.WIDTH/2;

        return distanceCarre < sommeRayons * sommeRayons;
    }
}
