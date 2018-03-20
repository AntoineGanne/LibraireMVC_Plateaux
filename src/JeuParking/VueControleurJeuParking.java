package JeuParking;

import JeuDeTestTetris.ModeleTetris;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mvc.VueControleur;

import java.util.Observable;
import java.util.Observer;


public class VueControleurJeuParking extends Application{
    ModeleJeuParking modele;

    private int nbColonnes, nbLignes;

    // gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
    BorderPane border;

    //Textes
    Label textePrincipal;

    //Fonts
    Font fontTitres;

    @Override
    public void start(Stage primaryStage){
        nbColonnes=8;
        nbLignes=8;

        // initialisation du modèle que l'on souhaite utiliser
        modele = new ModeleJeuParking(nbColonnes,nbLignes);

        modele.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                if(modele.isPartieFinie()){
                    textePrincipal=new Label("Felicitation! Vous avez fini le niveau!");
                    border.setTop(textePrincipal);
                }
            }
        });

        //initialisation de la vue
        VueControleur vue = new VueControleur(modele.getModelePlateau(),500,500);

        //pour le test
        modele.PosePiece(); //a enlever

        // gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
        border = new BorderPane();

        // permet de placer des boutons
        GridPane gPaneBoutons = new GridPane();






        border.setCenter(vue);
        Scene scene = new Scene(border, Color.LIGHTBLUE);


        ///////// controleurs

        // on definit les controlleurs des cases du plateau
        // On touche aux élement du gridPane contenu dans VueController et déjà initalisés
        for(int x=0;x<nbColonnes;x++) {
            for (int y = 0; y < nbLignes; y++) {
                int indexNode=x*nbLignes+y; //calcul de l'index du node a partir de x et y
                Node n=vue.getgPane().getChildren().get(indexNode);
                int finalX = x;
                int finalY = y;
                n.setOnMouseClicked((MouseEvent event) -> {
                    modele.selectionnerPiece(finalX, finalY); //finalX et finalY permet d'avoir une variable finale (qui n'est pas modifiable)
                });
            }
        }

        // controller for the arrow keys
        // move the selected piece in the model in the
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

        //boutons
        Button bouton1=new Button("a droite");
        bouton1.setOnAction(mousebutton -> {
            modele.deplacerPiece("droite");
        });
        gPaneBoutons.add(bouton1,0,0);

        Button bouton2=new Button("a gauche");
        bouton2.setOnAction(mousebutton -> {
            modele.deplacerPiece("gauche");
        });
        gPaneBoutons.add(bouton2,0,1);

        vue.setRight(gPaneBoutons);
        /////////fin des controleurs

        textePrincipal=new Label("cliquez sur une pièce puis utilisez les touches directionelles pour les déplacer");
        fontTitres=new Font("Arial",30);
        textePrincipal.setFont(fontTitres);
        border.setTop(textePrincipal);


        primaryStage.setTitle("Le Super Rush Hour!");
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
