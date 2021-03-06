package JeuDeTestTetris;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mvc.VueControleur;

import java.util.Observable;
import java.util.Observer;

public class VueControllerTetris extends Application implements Observer{
    private ModeleTetris modele;
    private VueControleur vue;

    private VueControleur vueProchainePiece;

    private int nbColonnes, nbLignes;

    //Textes
    private Label textePrincipal;
    private Label texteScore;
    //Fonts
    private Font fontTitres;
    private Font fontBoutons;

    Button btn_nouvellePartie;

    @Override
    public void start(Stage primaryStage){
        nbColonnes=10;
        nbLignes=25;

        // gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();

        // permet de placer les differents boutons dans une grille
        GridPane gPane = new GridPane();


        //Fonts
        fontTitres=new Font("Arial",20);
        fontBoutons=new Font("Arial",15);
        //labels
        textePrincipal=new Label(" Règles du jeu : \n " +
                "fleches gauche et droite pour deplacer la piece.\n " +
                "fleche haut pour pivoter la piece. \n"+
                "fleche bas pour accelerer la descente de la piece. \n"+
                "Vous gagnez un point pour chaque piece posée.");
        textePrincipal.setFont(fontTitres);
        border.setTop(textePrincipal);

        texteScore=new Label("Votre score: 0  \n" +
                "    ");
        texteScore.setFont(fontTitres);
        border.setBottom(texteScore);

        // initialisation du modèle que l'on souhaite utiliser
        modele= new ModeleTetris(nbColonnes,nbLignes);
        modele.addObserver(new Observer() {
            @Override
            public void  update(Observable o, Object arg) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        MiseAJour();
                    }
                });
            }
        });



        //initialisation de la vue
        vue = new VueControleur(modele.getModelePrincipal(),200,500);
        vueProchainePiece=new VueControleur(modele.getModeleProchainePiece(),100,100);


        border.setCenter(vue);
        gPane.add(vueProchainePiece,0,0);
        Scene scene = new Scene(border, Color.LIGHTBLUE);

        ////// Controleurs

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case RIGHT: modele.deplacerPieceADroite(); break;
                    case LEFT: modele.deplacerPieceAGauche();break;
                    case DOWN: modele.descendrePiece();break;
                    case UP: modele.pivoterPiece();break;
                    default:break;
                }
            }
        });
        //boutons
        Button btn_nouvellePartie;
        btn_nouvellePartie = new Button("Nouvelle Partie");
        btn_nouvellePartie.setFont(fontBoutons);
        btn_nouvellePartie.setOnAction(event -> {
            modele.nouvellePartie();
        });
        gPane.add(btn_nouvellePartie, 0, 1);


        Button btn_close;
        btn_close = new Button("Quitter le jeu");
        btn_close.setFont(fontBoutons);
        btn_close.setOnAction(event -> {
            Platform.exit();
            System.exit(0);
        });
        gPane.add(btn_close, 0, 2);

        ///////fin Controleurs
        border.setRight(gPane);


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


    /**
     * fait la mise a jour du score de la partie
     */
    private void MiseAJour(){
        if(modele.isPartieFinie()){
            texteScore.setText("C'est perdu! \n " +
                    "Vous avez fait un score de "+modele.getScore()+" points");
        }else {
            texteScore.setText("Votre score: "+modele.getScore()+" points. \n" +
                    "");
        }
    }


    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vue.MiseAJourVue();
            }
        });
    }
}
