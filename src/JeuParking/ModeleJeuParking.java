package JeuParking;

import mvc.Modele;

import java.awt.*;

public class ModeleJeuParking {
    public Modele getModelePlateau() {
        return modelePlateau;
    }

    private Modele modelePlateau;
    private int idPieceSelected;

    public ModeleJeuParking(int nbColonnes,int nbLignes){
        modelePlateau=new Modele(nbColonnes,nbLignes);
        idPieceSelected=0;
        modelePlateau.posePiece(nbColonnes-4,nbLignes-5,new boolean[][]{{true,true,true}},0,0,"vertical", Color.GRAY);

    }

    public void PosePiece(){

        modelePlateau.posePiece(2,1,new boolean[][]{{true},{true},{true}},0,0,"horizontal",Color.GREEN);
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
    }



}
