package ca.qc.bdeb.sim.tp2walasheima;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
