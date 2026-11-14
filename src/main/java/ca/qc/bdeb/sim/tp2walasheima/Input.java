package ca.qc.bdeb.sim.tp2walasheima;

import javafx.scene.input.*;
import java.util.*;

public class Input {
    //Ensemble des touches appuyées
    private static Set<KeyCode> touches = new HashSet<>();

    //NOTE TO SELF (WG) -- CHECK FONCTIONALITÉ 2 MÉTHODES. CODE TROUVÉ DANS NOTES DE COURS
    public static boolean isKeyPressed(KeyCode code){
        return touches.contains(code);
    }

    public static void setKeyPressed(KeyCode code, boolean appuie){
        if (appuie){
            touches.add(code);
        }else
            touches.remove(code);
    }


}
