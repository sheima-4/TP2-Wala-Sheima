package ca.qc.bdeb.sim.tp2walasheima;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.IOException;

public class JeuPoisson extends Application {
    final static int WIDTH = 900;
    final static int HEIGHT = 520;

    private void ecranAcceuil(Stage stage){
        var rootAcceuil = new BorderPane();
        var scene = new Scene(rootAcceuil, WIDTH, HEIGHT);
        rootAcceuil.setStyle("-fx-background-color: #2A7FFF");

        //Scene: Écran d'accueil -> Vbox ensuite Hbox(bouton)
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

        rootAcceuil.setTop(vbox);
        rootAcceuil.setBottom(hbox);

        // evenement ramene a ecraninfos
        buttonInfos.setOnAction(e -> { ecranInfos(stage); });
        buttonJouer.setOnAction(e -> { ecranJeu(stage); });
        //ferme programme quand touche esc appuyé
        toucheAppuyee(scene);

        stage.getIcons().add(new Image("gontrand.png"));
        stage.setTitle("Gontrand le Flétant");
        stage.setScene(scene);
        stage.show();
    }

    private void ecranInfos(Stage stage){
        //Scene: Écran Infos

        var rootInfos = new BorderPane();
        var scene = new Scene(rootInfos, WIDTH, HEIGHT);

        rootInfos.setStyle("-fx-background-color: #2A7FFF");

        var infos = new VBox();
        infos.setAlignment(Pos.TOP_CENTER);
        infos.setSpacing(20);

        Text titreJeux = new Text("Gontrand le Flétan");
        titreJeux.setStyle("-fx-font-size: 50px");

        var poissonEnnemi = new ImageView("poisson3.png");
        poissonEnnemi.setFitWidth(200);
        poissonEnnemi.setFitHeight(200);
        poissonEnnemi.setPreserveRatio(true);

        var textMembre1 = new HBox();
        textMembre1.setAlignment(Pos.CENTER);
        Text par = new Text("Par ");
        par.setStyle("-fx-font-size: 15px");

        Text nomMembre1 = new Text(" Wala Guemri ");
        nomMembre1.setStyle("-fx-font-size: 35px");
        textMembre1.getChildren().addAll(par, nomMembre1);

        var textMembre2 = new HBox();
        textMembre2.setAlignment(Pos.CENTER);
        Text et = new Text("et ");
        et.setStyle("-fx-font-size: 15px");
        Text nomMembre2 = new Text(" Sheima Mahdi ");
        nomMembre2.setStyle("-fx-font-size: 35px");
        textMembre2.getChildren().addAll(et, nomMembre2);


        Text explicatif = new Text("Travail remis à Nicolas Hurtuboise et Raouf Barbari. " +
                "Graphismes adaptés de https://game-icons.net/ et de https://openclipart.org/. " +
                "Développé dans le cadre du cours 420-SF3-RE - Développement d'applications dans un environnement graphique, " +
                "au Collège Bois-de-Boulogne. ");
        explicatif.setWrappingWidth(700);// texte revient à la ligne
        explicatif.setStyle("-fx-font-size: 16px");

        var buttonRetour = new Button("Retour");

        infos.getChildren().addAll(titreJeux, poissonEnnemi,textMembre1, textMembre2 , explicatif, buttonRetour);
        rootInfos.setCenter(infos);

        // evenement retour a ecran acceuil
        buttonRetour.setOnAction(e -> { ecranAcceuil(stage); });

        stage.setScene(scene);
        stage.show();
    }

    private void ecranJeu(Stage stage){
        var canvas = new Canvas(WIDTH, HEIGHT);
        var rootJeu = new BorderPane();
        var scene = new Scene(rootJeu, WIDTH, HEIGHT);

        rootJeu.setCenter(canvas);
        rootJeu.setStyle("-fx-background-color: #2A7FFF");

        scene.setOnKeyPressed(e-> {// evenement touche espace retour ecran acceuil
            if(e.getCode() == KeyCode.SPACE)
                ecranAcceuil(stage);
        });

        var context = canvas.getGraphicsContext2D();

        Partie partie = new Partie();

        toucheAppuyee(scene);

        //mouvements
        AnimationTimer timer = new AnimationTimer() {
            private long dernierTime = System.nanoTime();

            @Override
            public void handle(long time) {
                double deltaTemps = (time - dernierTime)*1e-9;
                partie.update(deltaTemps);

                partie.draw(context);
                partie.draw(context);


                dernierTime = time;
            }
        };
        timer.start();

        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void start(Stage stage) throws IOException {
        var root = new BorderPane();
        var scene = new Scene(root, WIDTH, HEIGHT);
        var canvas = new Canvas(WIDTH, HEIGHT);

        ecranAcceuil(stage);
    }


    public void toucheAppuyee(Scene scene){

        //QUESTION POUR PROF: dans diapo ils ont mis plus que 2 lignes dans le event
        //ecq c'est correct si c'est déjà dans une méthode à l'extérieur du start?
        //pcq je vais quand même pas faire deux méthodes juste pour ça non?
        scene.setOnKeyPressed((e) -> {
                if(e.getCode() == KeyCode.ESCAPE)
                    Platform.exit();
                else
                    Input.setKeyPressed(e.getCode(), true);
        });

        scene.setOnKeyReleased((e) -> {
            Input.setKeyPressed(e.getCode(), false);
        });
    }

    /*
    public String getNomToucheAppuyee(KeyCode code){
       return code.getName();
    }
     */

    //JSP COMMENT but i should find a way to get which key was pressed so I can use it
    //for the update() method.
}

/*méthode event si utilisatrice clique sur bouton ESC
    public void escape(KeyCode code){
        if (code == KeyCode.ESCAPE)
            Platform.exit();
        else
            Input.setKeyPressed(code, true);
    }

     */