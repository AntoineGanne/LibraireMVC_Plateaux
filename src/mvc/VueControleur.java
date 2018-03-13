
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;


//import java.awt.*;
import java.awt.*;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class VueControleur extends BorderPane {
    
    // modèle : ce qui réalise le calcule de l'expression
    private Modele m;

    private Rectangle[][] affichagePlateau;



    public VueControleur(Modele m_input) {
        
        // initialisation du modèle que l'on souhaite utiliser
        //m = new Modele();
        m=m_input;
        
        // gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();

        // permet de placer les diffrents boutons dans une grille
        GridPane gPane = new GridPane();
        
        int column = 0;
        int row = 0;

        //permet de stocker les rectangles qui composent l'affichage du plateau, et donc de changer leur couleur
        affichagePlateau=new Rectangle[m.getNbCasesX()][m.getNbCasesY()];
        
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
                        if(plateau[x][y]==0){
                            rect.setFill(Color.WHITE);
                        }
                        else{
                            rect.setFill(Color.hsb(plateau[x][y],0.8,0.8));
                        }
                        /*
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
                            case 4:
                                rect.setFill(Color.BURLYWOOD);
                            case 5:
                                rect.setFill(Color.hsb(5,0.5,0.7));
                            default:
                                rect.setFill(Color.PINK);
                        }
                        */

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
/*
                rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    // si case
                    @Override
                    public void handle(MouseEvent event) {
                        m.posePiece((int)rect.getX()/20,(int)rect.getY()/20);
                    }

                });
                */

                gPane.add(rect,x,y);
                //gPane.add(t,x+20,y);


                affichagePlateau[x][y]=rect;
            }
        }

        ///////////////////////////////////////////////////////////////////////////////////


        

        //gPane.setGridLinesVisible(true);
        
        this.setCenter(gPane);
        /*
        Scene scene = new Scene(border, Color.LIGHTBLUE);
        
        primaryStage.setTitle("Le Super Plateau!");
        primaryStage.setScene(scene);
        primaryStage.show();
        */
    }

    
}
