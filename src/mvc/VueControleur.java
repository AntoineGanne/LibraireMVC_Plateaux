
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;


//import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.Blend;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;
import javafx.scene.shape.*;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.w3c.dom.css.Rect;

/**
 *
 * @author freder
 */
public class VueControleur extends Application {
    
    // modèle : ce qui réalise le calcule de l'expression
    Modele m;

    Rectangle[][] affichagePlateau;



    @Override
    public void start(Stage primaryStage) {
        
        // initialisation du modèle que l'on souhaite utiliser
        m = new Modele();
        
        // gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();
        
        // permet de placer les diffrents boutons dans une grille
        GridPane gPane = new GridPane();
        
        int column = 0;
        int row = 0;

        //permet de stocker les rectangles qui composent l'affichage du plateau, et donc de changer leur couleur
        affichagePlateau=new Rectangle[m.getNbCasesX()][m.getNbCasesX()];
        
        /*
        affichage = new Text("");
        affichage.setFont(Font.font ("Verdana", 20));
        affichage.setFill(Color.RED);
        border.setTop(affichage);
        */

        // la vue observe les "update" du modèle, et réalise les mises à jour graphiques
        m.addObserver(new Observer() {
            /**
             * On met a jour la couleur de chaque rectangle, selon l'etat du plateau dans le modele
             * @param o
             * @param arg
             */
            @Override
            public void update(Observable o, Object arg) {
                int[][] plateau=m.getEtatDuPlateau();
                int nbCasesX=m.getNbCasesX();
                int nbCaseY=m.getNbCasesY();

                for(int x=0;x<nbCasesX;x++) {
                    for (int y = 0; y < nbCaseY; y++) {
                        Rectangle rect=affichagePlateau[x][y];
                        switch (plateau[x][y]){
                            case 0:
                                rect.setFill(Color.WHITE);
                                break;
                            case 1:
                                rect.setFill(Color.GRAY);
                                break;
                            case 2:
                                rect.setFill(Color.BLACK);
                                break;
                            case 3:
                                rect.setFill(Color.GREEN);
                                break;
                            default:
                                rect.setFill(Color.PINK);
                        }

                    }
                }
            }
        });


        //////// Création des cases du plateau + controlleurs //////////////////////////////
        int nbCasesX=m.getNbCasesX();
        int nbCaseY=m.getNbCasesY();

        for(int x=0;x<nbCasesX;x++){
            for(int y=0;y<nbCaseY;y++){
                Rectangle rect = new Rectangle();
                rect.setWidth(30);
                rect.setHeight(30);
                rect.setX(x*20);
                rect.setY(y*20);
                rect.setFill(Color.WHITE);

                rect.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        m.posePiece((int)rect.getX()/20,(int)rect.getY()/20);
                    }

                });
                /*
                Text t=new Text("t");
                t.setWrappingWidth(30);
                t.setFont(Font.font ("Verdana", 20));
                t.setTextAlignment(TextAlignment.CENTER);
                */


                gPane.add(rect,x,y);
                //gPane.add(t,x+20,y);


                affichagePlateau[x][y]=rect;
            }
        }

        ///////////////////////////////////////////////////////////////////////////////////


        

        //gPane.setGridLinesVisible(true);
        
        border.setCenter(gPane);
        
        Scene scene = new Scene(border, Color.LIGHTBLUE);
        
        primaryStage.setTitle("Le Super Plateau!");
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