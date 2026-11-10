package ca.qc.bdeb.sim.tp2walasheima;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class JeuPoisson extends Application {
    final static int WIDTH = 700;
    final static int HEIGHT = 500;

    @Override
    public void start(Stage stage) throws IOException {
        var root = new Pane();
        var scene = new Scene(root, WIDTH, HEIGHT);
        var canvas = new Canvas(WIDTH, HEIGHT);

        root.getChildren().add(canvas);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}

/*
TESTING 1
    var context = canvas.getGraphicContext();
    var p1 = new PoissonGentil(new Point2D(40, 50);
    p1.draw(context);
    context.strokeRect(p1.getPosition().getX(), p1.getPosition().getY(),
                       p1.getTaille().getX(), p1.getTaille().getY());

    LAST LINE MIGHT BE USEFUL FOR DEBUG METHOD
 */