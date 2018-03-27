package JeuParking;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mvc.VueControleur;
import java.util.Observable;
import java.util.Observer;


public class VueControleurJeuParking extends Application{

    //region ATTRIBUTS
    ModeleJeuParking modele;
    private int nbColonnes, nbLignes;
    private int nbcoup = 0, nbmax=0;

    private BorderPane border; // gestion du placement (permet de placer le champ Text affichage en haut, et GridPane gPane au centre)
    private Label txt1; // Texte
    private Label txt2; // Texte
    private Font fontTitres; // Mise en forme texte
    private Font fontBoutons; // Mise en forme texte
    //endregion


    @Override
    public void start(Stage primaryStage){

        //region - Elements de la fenetre de jeu

        // Taille plateau
        nbColonnes=8;
        nbLignes=8;
        // Divise la fenetre en 5 parties
        border = new BorderPane();
        // Mise en forme texte
        fontTitres=new Font("Arial",16);
        fontBoutons=new Font("Arial",15);
        // Textes + Mise en forme + Placement
        txt1=new Label(" Règles du jeu : \n Sortez la pièce rouge du plateau \n Cliquez sur une pièce puis utilisez les touches directionelles pour les déplacer");
        txt1.setFont(fontTitres);
        border.setTop(txt1);
        txt2=new Label(nbcoup+" coups");
        txt2.setFont(fontTitres);
        border.setBottom(txt2);
        // Modele
        modele = new ModeleJeuParking(nbColonnes,nbLignes); // Initialisation du modele (création plateau et initialisation des var du modele)
        // Vue
        VueControleur vue = new VueControleur(modele.getModelePlateau(),500,500);
        border.setCenter(vue);
        // GridPane
        GridPane gPaneBoutons = new GridPane(); // permet le placement des boutons sur la droite
        // Scene
        Scene scene = new Scene(border, Color.LIGHTBLUE);
        // Stage
        primaryStage.setTitle("Le Super Rush Hour!");
        primaryStage.setScene(scene);
        primaryStage.show();

        //endregion



        //region -  Modification des textes
        modele.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                if(modele.isPartieFinie()){
                    if(nbcoup<=nbmax){
                        txt1.setText("\n Felicitation! Vous avez fini le niveau avec un minimum de coup !\n  ");
                        txt1.setTextFill(Color.rgb(0,176,80));
                    }
                    else {
                        txt1.setText("\n Felicitation! Vous avez fini le niveau !\n  ");
                    }
                    border.setTop(txt1);
                }
            }
        });
        //endregion

        //region - Selection d'une piece

        // on definit les controlleurs des cases du plateau
        // On touche aux élement du gridPane contenu dans VueController et déjà initalisés
        for(int x=0;x<nbColonnes;x++) {
            for (int y = 0; y < nbLignes; y++) {
                int indexNode=x*nbLignes+y; //calcul de l'index du node a partir de x et y
                Node n=vue.getgPane().getChildren().get(indexNode);
                int finalX = x;
                int finalY = y;
                // permet de selectionner une piece et de mettre son id dans la var du modele @idPieceSelected
                n.setOnMouseClicked((MouseEvent event) -> {
                    modele.selectionnerPiece(finalX, finalY); //finalX et finalY permet d'avoir une variable finale (qui n'est pas modifiable)
                });
            }
        }
        //endregion

        //region - Mouvement des pieces
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event){
                nbcoup+=1;
                txt2.setText(nbcoup+" coups");
                border.setBottom(txt2);
                if(nbcoup>nbmax){
                    txt2.setTextFill(Color.rgb(255,0,0));
                }
                switch (event.getCode()){ // déplacement de la piece selectionnée
                    case RIGHT: modele.deplacerPiece("droite"); break;
                    case LEFT: modele.deplacerPiece("gauche");break;
                    case DOWN: modele.deplacerPiece("bas");break;
                    case UP: modele.deplacerPiece("haut");break;
                    default:break;
                }
            }
        });
        //endregion

        //region - Boutons

        // NIVEAU 1
        Button btn_Niveau1=new Button("Niveau 1");
        btn_Niveau1.setFont(fontBoutons);
        btn_Niveau1.setOnAction(mousebutton -> {
            modele.initialiserNiveau1();
            txt1.setText(" Règles du jeu : \n Sortez la pièce rouge du plateau \n Cliquez sur une pièce puis utilisez les touches directionelles pour les déplacer");
            border.setTop(txt1);
            nbcoup=0;
            nbmax=12;
            txt2.setText(nbcoup+" coups ");
            txt2.setTextFill(Color.rgb(0,0,0));
            border.setBottom(txt2);
        });

        // NIVEAU 2
        Button btn_Niveau2=new Button("Niveau 2");
        btn_Niveau2.setFont(fontBoutons);
        btn_Niveau2.setOnAction(mousebutton -> {
            modele.initialiserNiveau2();
            txt1.setText(" Règles du jeu : \n Sortez la pièce rouge du plateau \n Cliquez sur une pièce puis utilisez les touches directionelles pour les déplacer");
            border.setTop(txt1);
            nbcoup=0;
            nbmax=18;
            txt2.setText(nbcoup+" coups ");
            txt2.setTextFill(Color.rgb(0,0,0));
            border.setBottom(txt2);
        });

        // NIVEAU 3
        Button btn_Niveau3=new Button("Niveau 3");
        btn_Niveau3.setFont(fontBoutons);
        btn_Niveau3.setOnAction(mousebutton -> {
            modele.initialiserNiveau3();
            txt1.setText(" Règles du jeu : \n Sortez la pièce rouge du plateau \n Cliquez sur une pièce puis utilisez les touches directionelles pour les déplacer");
            border.setTop(txt1);
            nbcoup=0;
            nbmax=12;
            txt2.setText(nbcoup+" coups ");
            txt2.setTextFill(Color.rgb(0,0,0));
            border.setBottom(txt2);
        });

        //Quitter le jeu

        Button btn_close;
        btn_close = new Button("Quitter le jeu");
        btn_close.setFont(fontBoutons);
        btn_close.setOnAction(event -> {
            Platform.exit();
            System.exit(0);
        });

        // Placement des boutons dans le GridPane, et placement du GridPane au BorderPane
        gPaneBoutons.add(btn_Niveau1,0,1);
        gPaneBoutons.add(btn_Niveau2,0,2);
        gPaneBoutons.add(btn_Niveau3,0,3);
        gPaneBoutons.add(btn_close,0,4);
        border.setRight(gPaneBoutons);

        //endregion


    }



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }


}
