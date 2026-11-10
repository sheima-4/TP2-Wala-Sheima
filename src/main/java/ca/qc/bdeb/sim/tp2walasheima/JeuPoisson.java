package ca.qc.bdeb.sim.tp2walasheima;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class JeuPoisson extends Application {
    final static int WIDTH = 900;
    final static int HEIGHT = 520;

    @Override
    public void start(Stage stage) throws IOException {
        var root = new BorderPane();
        var scene = new Scene(root, WIDTH, HEIGHT);
        var canvas = new Canvas(WIDTH, HEIGHT);


        root.setStyle("-fx-background-color: #2A7FFF");

        //Scene: Écran d'accueil-> Vbox ensuite Hbox(bouton)
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_CENTER);

        var logo = new ImageView("logo.png");
        logo.setFitWidth(450);
        logo.setFitHeight(450);
        logo.setPreserveRatio(true);

        var hbox = new HBox();
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        hbox.setSpacing(10);// Fait l'espace entre les deux bouttons
        hbox.setPadding(new Insets(10));// Fait l'espace entre les bouttons et le bottom du BorderPane

        var buttonJouer = new Button("Jouer!");// doit mener a scene du jeu
        var buttonInfos = new Button("Infos");// doit mener a scene info
        buttonJouer.setPrefSize(55, 10);
        buttonInfos.setPrefSize(55, 10);

        hbox.getChildren().addAll(buttonJouer, buttonInfos);
        vbox.getChildren().addAll(logo);

        root.setTop(vbox);
        root.setBottom(hbox);

        //Scene: Écran jouer


        stage.getIcons().add(new Image("gontrand.png"));
        stage.setTitle("Gontrand le Flétant");
        stage.setScene(scene);
        stage.show();
    }
}
