package JeuParking;

import mvc.Modele;

public class ModeleJeuParking {
    public Modele getModelePlateau() {
        return modelePlateau;
    }

    private Modele modelePlateau;
    private int idPieceSelected;

    public ModeleJeuParking(int nbColonnes,int nbLignes){
        modelePlateau=new Modele(nbColonnes,nbLignes);
        idPieceSelected=0;

        modelePlateau.posePiece(nbColonnes-4,nbLignes-5);
        modelePlateau.posePiece(2,1);
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
            modelePlateau.deplacementPiece(idPieceSelected,direction,1);
        }
    }

}
