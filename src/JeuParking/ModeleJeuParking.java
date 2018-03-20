package JeuParking;

import mvc.Modele;
import java.util.Random;
import java.awt.*;

import java.awt.*;
import java.util.HashMap;

public class ModeleJeuParking {
    public Modele getModelePlateau() {
        return modelePlateau;
    }

    private Modele modelePlateau;
    private int idPieceSelected;

    public ModeleJeuParking(int nbColonnes,int nbLignes){
        modelePlateau=new Modele(nbColonnes,nbLignes);
        idPieceSelected=0;
        // bordure :
        modelePlateau.posePiece(7,0,new boolean[][]{{true,true,true}},0,0,"",Color.BLACK);
        modelePlateau.posePiece(7,4,new boolean[][]{{true,true,true,true}},0,0,"",Color.BLACK);
        modelePlateau.posePiece(0,0,new boolean[][]{{true,true,true,true,true,true,true,true}},0,0,"",Color.BLACK);
        modelePlateau.posePiece(1,0,new boolean[][]{{true},{true},{true},{true},{true},{true}},0,0,"",Color.BLACK);
        modelePlateau.posePiece(1,7,new boolean[][]{{true},{true},{true},{true},{true},{true}},0,0,"",Color.BLACK);

        // pieces obstacles :
        Random rand=new Random();
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

    public void PosePiece(){
        modelePlateau.posePiece(1,3,new boolean[][]{{true},{true}},0,0,"horizontal",Color.RED);
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
            modelePlateau.deplacementPiece(idPieceSelected,direction);
        }
        if(modelePlateau.selectionnerPiece(7,3)!=0){
            modelePlateau.clearPieces();
        }
    }

    public void initialiserNiveau1(){

    }

/*
    private HashMap<String,Boolean[][]> poolDePiece =
            new HashMap<String, Boolean[][]>(){
                    {"H2",new Boolean[][]{{true},{true},{true}}}
            };
    */
}
