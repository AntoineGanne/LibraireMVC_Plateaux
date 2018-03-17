package JeuParking;

import JeuDeTestTetris.ModeleTetris;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import mvc.VueControleur;

import java.security.Key;

public class VueControleurJeuParking extends Application{
    ModeleJeuParking modele;

    private int nbColonnes, nbLignes;

    @Override
    public void start(Stage primaryStage){


        // initialisation du modèle que l'on souhaite utiliser
        modele = new ModeleJeuParking(20,20);

        //initialisation de la vue
        VueControleur vue = new VueControleur(modele.getModelePlateau(),500,500);

        modele.PosePiece();

        // gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();

        // permet de placer les diffrents boutons dans une grille
        GridPane gPane = new GridPane();

        nbColonnes=20;
        nbLignes=20;




        border.setCenter(vue);
        Scene scene = new Scene(border, Color.LIGHTBLUE);


        ///////// controleurs
        /*
        for(int x=0;x<nbColonnes;x++){
            for(int y=0;y<nbLignes;y++){
                Rectangle rect = new Rectangle();
                rect.setWidth(30);
                rect.setHeight(30);
                rect.setX(x*20);
                rect.setY(y*20);
                rect.setFill(Color.TRANSPARENT);

                // si case
                rect.setOnMouseClicked(event -> {
                    modele.selectionnerPiece((int)rect.getX()/20,(int)rect.getY()/20);
                    System.out.println("detection clic");
                });

                gPane.add(rect,x,y);

            }
        }*/
        /*
        for(Node n:
                vue.getgPane().getChildren()){
                n.setOnMouseClicked((MouseEvent event) -> {
                modele.selectionnerPiece((int)event.getX()/20,(int) event.getY()/20);
                System.out.println("detection clic");
            });
        }
        */

        // on definit les controlleurs des cases du plateau
        // On touche aux élement du gridPane contenu dans VueController et déjà initalisés
        for(int x=0;x<nbColonnes;x++) {
            for (int y = 0; y < nbLignes; y++) {
                Node n=vue.getgPane().getChildren().get(x*nbColonnes+y);
                int finalX = x;
                int finalY = y;
                n.setOnMouseClicked((MouseEvent event) -> {
                    modele.selectionnerPiece(finalX, finalY); //finalX et finalY permet d'avoir une variable finale (qui n'est pas modifiable)
                    System.out.println("detection clic");
                });
            }
        }



        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case RIGHT: modele.deplacerPiece("droite"); break;
                    case LEFT: modele.deplacerPiece("gauche");break;
                    case DOWN: modele.deplacerPiece("bas");break;
                    case UP: modele.deplacerPiece("haut");break;
                    default:break;
                }
            }
        });


        //vue.setCenter(gPane);
        /////////fin des controleurs



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
