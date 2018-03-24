package JeuParking;

import mvc.Modele;

import java.util.Observable;
import java.util.Random;
import java.awt.*;

import java.awt.*;
import java.util.HashMap;

public class ModeleJeuParking extends Observable{
    public Modele getModelePlateau() {
        return modelePlateau;
    }

    private Modele modelePlateau;
    private int idPieceSelected;

    public boolean isPartieFinie() {
        return partieFinie;
    }

    private boolean partieFinie;

    public ModeleJeuParking(int nbColonnes,int nbLignes){
        modelePlateau=new Modele(nbColonnes,nbLignes);
        idPieceSelected=0;
        partieFinie=false;
        // bordure :
        setContours();

    }



    /**
     *
     * @param posX
     * @param posY
     */
    public void selectionnerPiece(int posX,int posY)
    {
        this.idPieceSelected=modelePlateau.selectionnerPiece(posX,posY);
    }

    public void deplacerPiece(String direction){
        if(idPieceSelected !=0){
            try{
                modelePlateau.deplacementPiece(idPieceSelected,direction);
            } catch(Exception e){

            }
        }
        // pour tester la fin de partie on teste simplement si une piece est dans le trou des contours,
        //etant donnée que seule la piece principale peut y acceder
        if(modelePlateau.selectionnerPiece(7,3)!=0){
            partieFinie=true;
            setChanged();
            notifyObservers();
        }
    }

    public void initialiserNiveauVide(){
        modelePlateau.clearPieces();
        setContours();
    }

    public void initialiserNiveau1(){
        modelePlateau.clearPieces();
        setContours();
        //piece principale
        modelePlateau.posePiece(1,3,new boolean[][]{{true},{true}},0,0,"horizontal",Color.RED);

        //autres pieces
        modelePlateau.posePiece(3,2,new boolean[][]{{true},{true}},0,0,"horizontal");
        modelePlateau.posePiece(3,6,new boolean[][]{{true},{true}},0,0,"horizontal");
        modelePlateau.posePiece(5,2,new boolean[][]{{true},{true}},0,0,"horizontal");
        modelePlateau.posePiece(5,5,new boolean[][]{{true},{true}},0,0,"horizontal");
        modelePlateau.posePiece(1,1,new boolean[][]{{true},{true}},0,0,"horizontal");

        modelePlateau.posePiece(1,4,new boolean[][]{{true,true}},0,0,"vertical");
        modelePlateau.posePiece(2,4,new boolean[][]{{true,true,true}},0,0,"vertical");
        modelePlateau.posePiece(4,3,new boolean[][]{{true,true}},0,0,"vertical");
        modelePlateau.posePiece(3,3,new boolean[][]{{true,true,true}},0,0,"vertical");
        modelePlateau.posePiece(6,3,new boolean[][]{{true,true}},0,0,"vertical");
    }

    public void initialiserNiveau2(){
        modelePlateau.clearPieces();
        setContours();
        //piece principale
        modelePlateau.posePiece(1,3,new boolean[][]{{true},{true}},0,0,"horizontal",Color.RED);

        //autres pieces
        modelePlateau.posePiece(2,1,new boolean[][]{{true},{true}},0,0,"horizontal");
        modelePlateau.posePiece(2,2,new boolean[][]{{true},{true},{true}},0,0,"horizontal");
        modelePlateau.posePiece(2,5,new boolean[][]{{true},{true}},0,0,"horizontal");

        modelePlateau.posePiece(1,1,new boolean[][]{{true,true}},0,0,"vertical");
        modelePlateau.posePiece(1,4,new boolean[][]{{true,true}},0,0,"vertical");
        modelePlateau.posePiece(3,3,new boolean[][]{{true,true}},0,0,"vertical");
        modelePlateau.posePiece(5,1,new boolean[][]{{true,true}},0,0,"vertical");
        modelePlateau.posePiece(4,3,new boolean[][]{{true,true,true}},0,0,"vertical");
        modelePlateau.posePiece(6,1,new boolean[][]{{true,true,true}},0,0,"vertical");
    }

    public void initialiserNiveau3() {
        modelePlateau.clearPieces();
        setContours();
        //piece principale
        modelePlateau.posePiece(1, 3, new boolean[][]{{true}, {true}}, 0, 0, "horizontal", Color.RED);
        //autres pieces
        modelePlateau.posePiece(2,1,new boolean[][]{{true},{true}},0,0,"horizontal");
        modelePlateau.posePiece(1,2,new boolean[][]{{true},{true}},0,0,"horizontal");
        modelePlateau.posePiece(3,2,new boolean[][]{{true},{true}},0,0,"horizontal");
        modelePlateau.posePiece(4,5,new boolean[][]{{true},{true}},0,0,"horizontal");
        modelePlateau.posePiece(5,4,new boolean[][]{{true},{true}},0,0,"horizontal");
        modelePlateau.posePiece(4,6,new boolean[][]{{true},{true},{true}},0,0,"horizontal");

        modelePlateau.posePiece(3,3,new boolean[][]{{true,true}},0,0,"vertical");
        modelePlateau.posePiece(3,5,new boolean[][]{{true,true}},0,0,"vertical");
        modelePlateau.posePiece(4,3,new boolean[][]{{true,true}},0,0,"vertical");
        modelePlateau.posePiece(6,2,new boolean[][]{{true,true}},0,0,"vertical");
        modelePlateau.posePiece(1,4,new boolean[][]{{true,true,true}},0,0,"vertical");

    }

    private void setContours(){
        modelePlateau.posePiece(7,0,new boolean[][]{{true,true,true}},0,0,"",Color.BLACK);
        modelePlateau.posePiece(7,4,new boolean[][]{{true,true,true,true}},0,0,"",Color.BLACK);
        modelePlateau.posePiece(0,0,new boolean[][]{{true,true,true,true,true,true,true,true}},0,0,"",Color.BLACK);
        modelePlateau.posePiece(1,0,new boolean[][]{{true},{true},{true},{true},{true},{true}},0,0,"",Color.BLACK);
        modelePlateau.posePiece(1,7,new boolean[][]{{true},{true},{true},{true},{true},{true}},0,0,"",Color.BLACK);
    }


}
