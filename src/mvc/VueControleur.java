
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;


//import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javafx.event.EventHandler;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class VueControleur extends BorderPane {
    
    // modèle : ce qui réalise le calcule de l'expression
    private Modele m;

    private Rectangle[][] affichagePlateau;
    int tailleRectanglesX;
    int tailleRectanglesY;

    public GridPane getgPane() {
        return gPane;
    }

    private GridPane gPane;


    /**
     * initialise la vue du plateau
     * @param m_input le modele qui sera observé par la vue
     * @param taillePaneX  taille initiale en X de le vue dans l'application
     * @param taillePaneY  taille initiale en Y de le vue dans l'application
     */
    public VueControleur(Modele m_input,int taillePaneX,int taillePaneY) {
        
        // initialisation du modèle que l'on souhaite utiliser
        //m = new Modele();
        m=m_input;
        
        // gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();

        // permet de placer les diffrents boutons dans une grille
        gPane = new GridPane();
        
        int nbCasesX=m.getNbCasesX();
        int nbCasesY=m.getNbCasesY();

        //permet de stocker les rectangles qui composent l'affichage du plateau, et donc de changer leur couleur
        affichagePlateau=new Rectangle[nbCasesX][nbCasesY];
        



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

                for(int x=0;x<nbCasesX;x++) {
                    for (int y = 0; y < nbCasesY; y++) {
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
        tailleRectanglesX=taillePaneX/nbCasesX;
        tailleRectanglesY =taillePaneY/nbCasesY;

        for(int x=0;x<nbCasesX;x++){
            for(int y=0;y<nbCasesY;y++){
                Rectangle rect = new Rectangle();
                rect.setWidth(tailleRectanglesX);
                rect.setHeight(tailleRectanglesY);
                rect.setX(x*tailleRectanglesX);
                rect.setY(y* tailleRectanglesY);
                rect.setFill(Color.WHITE);

                rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    // si case
                    @Override
                    public void handle(MouseEvent event) {
                        m.posePiece((int)rect.getX()/20,(int)rect.getY()/20);
                    }

                });


                gPane.add(rect,x,y);


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
