package JeuDeTestTetris;

import mvc.Modele;

import static java.lang.Math.floor;

public class ModeleTetris {
    public Modele getM() {
        return m;
    }

    private Modele m;
    private int idPieceEnMouvement;

    public  ModeleTetris(int nbColonnes,int nbLignes){
        m=new Modele(nbColonnes,nbLignes);
    }

    public void nouvellePartie(){
        m.clearPieces();
        int nbCasesX=m.getNbCasesX();
        boolean[][] forme=new boolean[][]{{true,false},{true,true},{true,false}};
        int x=(int)floor(nbCasesX/2);
        int pivotX=1;
        int pivotY=0;
        m.posePiece(x,pivotY,forme,pivotX,pivotY,"horizontal bas");
        idPieceEnMouvement=m.selectionnerDernierPieceAdded();
    }

    private void gestionDeLigneRemplie(){
        int nbColonnes=m.getNbCasesX();
        int nbLignes=m.getNbCasesY();
        //on parcourt les lignes depuis le bas du plateau
        for(int y=nbLignes-1;y>=0;y--){
            if(ligneRemplie(y)){

            }
        }
    }


    private boolean ligneRemplie(int numLigne){
        for(int x=0;x<m.getNbCasesX();x++){
            if(m.selectionnerPiece(x,numLigne)==0) return false;
        }
        //si on a atteint la fin de la ligne sans trouver de case vide
        return true;
    }

    private void suppressionLigne(int ligne){
        
    }

    public void deplacerPieceADroite(){
        m.deplacementPiece(idPieceEnMouvement,"droite");
    }
    public void deplacerPieceAGauche(){
        m.deplacementPiece(idPieceEnMouvement,"gauche");
    }
    public void pivoterPiece(){
        m.pivoterPiece(idPieceEnMouvement,true);
    }
    public void descendrePiece(){
        m.deplacementPiece(idPieceEnMouvement,"bas");
    }



}
