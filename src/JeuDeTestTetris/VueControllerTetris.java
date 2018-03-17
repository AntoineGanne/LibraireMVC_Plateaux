package JeuDeTestTetris;

import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.shape.*;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mvc.VueControleur;

public class VueControllerTetris extends Application {
    private ModeleTetris m;

    private int nbColonnes, nbLignes;

    @Override
    public void start(Stage primaryStage){


        // initialisation du modèle que l'on souhaite utiliser
        m = new ModeleTetris(20,20);

        //initialisation de la vue
        VueControleur vue = new VueControleur(m.getM(),500,500);

        // gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();

        // permet de placer les diffrents boutons dans une grille
        GridPane gPane = new GridPane();

        nbColonnes=10;
        nbLignes=40;




        border.setCenter(vue);
        Scene scene = new Scene(border, Color.LIGHTBLUE);

        primaryStage.setTitle("Le Super Tetris!");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }


}
