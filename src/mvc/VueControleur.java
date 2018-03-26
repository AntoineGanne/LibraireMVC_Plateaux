
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;


//import java.awt.*;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Observable;
import java.util.Observer;


public class VueControleur extends BorderPane {
    
    // modèle : ce qui réalise le calcule de l'expression
    private Modele m;

    private Rectangle[][] affichagePlateau;

    private GridPane gPane;

    private static Color DEFAULT_COLOR=Color.WHITE;


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
                MiseAJourVue();

            }
        });


        //////// Création des cases du plateau //////////////////////////////
        int tailleRectanglesX = taillePaneX / nbCasesX;
        int tailleRectanglesY = taillePaneY / nbCasesY;
        int[][] etatDuPlateau=m.getEtatDuPlateau();

        for(int x=0;x<nbCasesX;x++){
            for(int y=0;y<nbCasesY;y++){
                Rectangle rect = new Rectangle();
                rect.setWidth(tailleRectanglesX);
                rect.setHeight(tailleRectanglesY);
                rect.setX(x* tailleRectanglesX);
                rect.setY(y* tailleRectanglesY);

                int id=etatDuPlateau[x][y];
                if(id==0){
                    rect.setFill(DEFAULT_COLOR);
                }else{
                    //on recupere la couleur de la piece a partir de son id
                    java.awt.Color c=m.getCouleurDePiece(id);
                    //on doit transformer la couleur recuperée (en java.awt) en une Color javaFX
                    rect.setFill(Color.rgb(c.getRed(),c.getGreen(),c.getBlue()));
                }

                gPane.add(rect,x,y);
                affichagePlateau[x][y]=rect;
            }
        }
        ///////////////////////////////////////////////////////////////////////////////////

        //gPane.setGridLinesVisible(true);
        this.setCenter(gPane);
    }

    /**
     * met a jour les rectangles qui composent la vue
     */
    public void MiseAJourVue(){
        int[][] plateau=m.getEtatDuPlateau();
        int nbCasesX=m.getNbCasesX();
        int nbCasesY=m.getNbCasesY();

        for(int x=0;x<nbCasesX;x++) {
            for (int y = 0; y < nbCasesY; y++) {
                Rectangle rect=affichagePlateau[x][y];
                if(plateau[x][y]==0){
                    rect.setFill(Color.WHITE);
                }
                else{
                    //on recupere la couleur de la piece a partir de son id
                    java.awt.Color c=m.getCouleurDePiece(plateau[x][y]);
                    //on doit transformer la couleur recuperée (en java.awt) en une Color javaFX
                    rect.setFill(Color.rgb(c.getRed(),c.getGreen(),c.getBlue()));
                }
            }
        }
    }

    public GridPane getgPane() {
        return gPane;
    }

}
