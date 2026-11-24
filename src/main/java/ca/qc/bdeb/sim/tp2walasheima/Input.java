package ca.qc.bdeb.sim.tp2walasheima;

import javafx.scene.input.*;
import java.util.*;

public class Input {
    //Ensemble des touches appuyées, pcq si on mettait pas un HashSet, ça va envoyer l'évènement en boucle.
    private static Set<KeyCode> touches = new HashSet<>();

    //.update() va demander si certaines touches sont appuyées ou non
    public static boolean isKeyPressed(KeyCode code){
        return touches.contains(code);
    }

    //écoute les évènements sur la scène et modifie l'état quand les touches changent
    public static void setKeyPressed(KeyCode code, boolean appuie){
        if (appuie){
            touches.add(code);
        }else
            touches.remove(code);
    }


}
