package ca.qc.bdeb.sim.tp2walasheima;

import javafx.geometry.Point2D;

public class Antagonistes extends ElementsJeu{
    final static int WIDTH = 120;
    final static int HEIGHT = 104;
    private Point2D position;
    private Point2D vitesse;
    private Point2D acceleration;
    private Point2D taille;

    public Antagonistes(Point2D position, Point2D vitesse, Point2D acceleration, Point2D taille) {// a modifier
        this.position = position;
        this.vitesse = vitesse;
        this.acceleration = acceleration;
        this.taille = taille;
    }

    //boucle pour personnage reviens constamment



}
