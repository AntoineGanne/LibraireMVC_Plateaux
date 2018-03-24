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
        int aleat = 1 + (int)(Math.random()*((7-1)+1));
        boolean[][] forme;
        int pivotX , pivotY;
        switch (aleat){
            case 1: // I
                forme = new boolean[][]{{true},{true},{true},{true}};
                pivotX=1;
                pivotY=0;
                break;
            case 2: // O
                forme = new boolean[][]{{true,true},{true,true}};
                pivotX=0;
                pivotY=0;
                break;
            case 3: // T
                forme=new boolean[][]{{true,false},{true,true},{true,false}};
                pivotX=1;
                pivotY=0;
                break;
            case 4: // L
                forme = new boolean[][]{{true,true},{true,false},{true,false}};
                pivotX=0;
                pivotY=0;
                break;
            case 5: // J
                forme = new boolean[][]{{true,false},{true,false},{true,true}};
                pivotX=2;
                pivotY=0;
                break;
            case 6: // Z
                forme = new boolean[][]{{true,false},{true,true},{false,true}};
                pivotX=1;
                pivotY=0;
                break;
            case 7: // S
                forme=new boolean[][]{{true,false},{true,true},{true,false}};
                pivotX=1;
                pivotY=0;
                break;
            default: // T
                forme = new boolean[][]{{false,true},{true,true},{true,false}};
                pivotX=1;
                pivotY=0;
                break;
        }
        int x=(int)floor(nbCasesX/2);
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
    public void descendrePiece(){
        m.deplacementPiece(idPieceEnMouvement,"bas");
    }
    public void pivoterPiece(){
        m.pivoterPiece(idPieceEnMouvement,true);
    }




}
