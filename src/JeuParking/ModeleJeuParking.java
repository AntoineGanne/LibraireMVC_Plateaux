package JeuParking;

import mvc.Modele;

public class ModeleJeuParking {
    private Modele modelePlateau;
    private int idPieceSelected;

    public ModeleJeuParking(int nbColonnes,int nbLignes){
        modelePlateau=new Modele(nbColonnes,nbLignes);
        idPieceSelected=0;
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

    public void deplacerPiece(int depX,int depY){
        
    }

}
