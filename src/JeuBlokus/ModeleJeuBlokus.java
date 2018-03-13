package JeuBlokus;

import mvc.Modele;

public class ModeleJeuBlokus {
    private Modele modelePlateau;
    private int idPieceSelected;

    public ModeleJeuBlokus(int nbColonnes, int nbLignes){
        modelePlateau=new Modele(nbColonnes,nbLignes);
        idPieceSelected=0;
    }



}
