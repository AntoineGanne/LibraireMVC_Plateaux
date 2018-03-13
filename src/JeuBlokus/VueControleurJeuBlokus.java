package JeuBlokus;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mvc.VueControleur;

public class VueControleurJeuBlokus extends Application{
    ModeleJeuBlokus modele;

    private int nbColonnes, nbLignes;

    @Override
    public void start(Stage primaryStage) {
        VueControleur vue = new VueControleur();

        // initialisation du modèle que l'on souhaite utiliser
        modele = new ModeleJeuBlokus(20,20);

        // gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();

        // permet de placer les diffrents boutons dans une grille
        GridPane gPane = new GridPane();

        nbColonnes=10;
        nbLignes=30;


        border.setCenter(vue);
        Scene scene = new Scene(border, Color.LIGHTBLUE);

        primaryStage.setTitle("Blokus");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
