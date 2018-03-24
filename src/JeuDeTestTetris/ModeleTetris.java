package JeuDeTestTetris;

import mvc.ExceptionsDuProjet.exceptionChevauchementDePiece;
import mvc.ExceptionsDuProjet.exceptionDeplacementPieceFigee;
import mvc.ExceptionsDuProjet.exceptionPieceHorsPlateau;
import mvc.Modele;
import java.awt.*;
import java.util.Observable;

import static java.lang.Math.floor;

public class ModeleTetris extends Observable{
    public Modele getM() {
        return m;
    }

    private Modele m;
    private int idPieceEnMouvement;

    public boolean isPartieFinie() {
        return partieFinie;
    }

    public int getScore() {
        return score;
    }

    private int score;
    private boolean partieFinie;

    //attributs prochaine piece
    private boolean[][] forme;
    private int pivotX , pivotY;
    Color clr;

    public  ModeleTetris(int nbColonnes,int nbLignes){
        m=new Modele(nbColonnes,nbLignes);
        miseAJourProchainePiece();
        partieFinie=false;
    }

    public void nouvellePartie(){
        m.clearPieces();
        nouvellePiece();
        score=0;

        miseAJourProchainePiece();
    }

    private void miseAJourProchainePiece(){
        int aleat = 1 + (int)(Math.random()*((7-1)+1));
        switch (aleat){
            case 1: // I
                forme = new boolean[][]{{true},{true},{true},{true}};
                pivotX=1;
                pivotY=0;
                clr = new Color(102,255,255);
                break;
            case 2: // O
                forme = new boolean[][]{{true,true},{true,true}};
                pivotX=0;
                pivotY=0;
                clr = new Color(232,232,8);
                break;
            case 3: // T
                forme=new boolean[][]{{true,false},{true,true},{true,false}};
                pivotX=1;
                pivotY=0;
                clr = new Color(153,51,255);
                break;
            case 4: // L
                forme = new boolean[][]{{true,true},{true,false},{true,false}};
                pivotX=0;
                pivotY=0;
                clr = new Color(255,153,51);
                break;
            case 5: // J
                forme = new boolean[][]{{true,false},{true,false},{true,true}};
                pivotX=2;
                pivotY=0;
                clr = new Color(51,51,255);
                break;
            case 6: // Z
                forme = new boolean[][]{{true,false},{true,true},{false,true}};
                pivotX=1;
                pivotY=0;
                clr = new Color(255,51,0);
                break;
            case 7: // S
                forme=new boolean[][]{{false,true},{true,true},{true,false}};
                pivotX=1;
                pivotY=0;
                clr = new Color(0,255,0);
                break;
            default: // T
                forme = new boolean[][]{{false,true},{true,true},{true,false}};
                pivotX=1;
                pivotY=0;
                clr = new Color(153,51,255);
                break;
        }
    }

    private void nouvellePiece(){
        int nbCasesX=m.getNbCasesX();
        int x=(int)floor(nbCasesX/2);
        try {
            m.posePiece(x,pivotY,forme,pivotX,pivotY,"horizontal bas", clr);
        } catch (mvc.ExceptionsDuProjet.exceptionPieceHorsPlateau exceptionPieceHorsPlateau) {
            exceptionPieceHorsPlateau.printStackTrace();
        } catch (mvc.ExceptionsDuProjet.exceptionChevauchementDePiece exceptionChevauchementDePiece) {
            finDePartie();
        }
        idPieceEnMouvement=m.selectionnerDernierPieceAdded();
        miseAJourProchainePiece();

        gestionDeLigneRemplie();
    }

    private void finDePartie() {
        partieFinie=true;

    }

    private void gestionDeLigneRemplie(){
        int nbColonnes=m.getNbCasesX();
        int nbLignes=m.getNbCasesY();
        //on parcourt les lignes depuis le bas du plateau

        int y=nbLignes-1;
        while(y>=0){
            if(ligneRemplie(y)){
                m.clearLigneEtDescendCases(y,idPieceEnMouvement);
                //on ne décremente pas y dans ce cas, car la ligne du dessus descend d'un cran
            }else{
                y--;
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



    public void deplacerPieceADroite(){
        try {
            m.deplacementPiece(idPieceEnMouvement,"droite");
        } catch (mvc.ExceptionsDuProjet.exceptionDeplacementPieceFigee e) {

        }
    }
    public void deplacerPieceAGauche(){
        try {
            m.deplacementPiece(idPieceEnMouvement,"gauche");
        } catch (mvc.ExceptionsDuProjet.exceptionDeplacementPieceFigee e) {

        }
    }
    public void descendrePiece(){
        try {
            m.deplacementPiece(idPieceEnMouvement,"bas");
        } catch (mvc.ExceptionsDuProjet.exceptionDeplacementPieceFigee e) {
            nouvellePiece();
            incrementerScore();
        }
    }
    public void pivoterPiece(){
        m.pivoterPiece(idPieceEnMouvement,true);
    }

    private void incrementerScore(){
        score++;
        setChanged();
        notifyObservers();
    }



}
