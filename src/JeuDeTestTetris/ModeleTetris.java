package JeuDeTestTetris;

import mvc.ExceptionsDuProjet.exceptionChevauchementDePiece;
import mvc.ExceptionsDuProjet.exceptionPieceHorsPlateau;
import mvc.Modele;
import java.awt.*;
import java.util.Observable;

import static java.lang.Math.floor;



public class ModeleTetris extends Observable implements Runnable{
    public Modele getModelePrincipal() {
        return modelePrincipal;
    }

    private Modele modelePrincipal;
    private Modele modeleProchainePiece;
    private int idPieceEnMouvement;
    private Thread threadEnCours;

    private static int TAILLE_PIECE_X_MAX=5;
    private static int TAILLE_PIECE_Y_MAX=5;


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
        modelePrincipal =new Modele(nbColonnes,nbLignes);
        modeleProchainePiece=new Modele(TAILLE_PIECE_X_MAX,TAILLE_PIECE_X_MAX);
        miseAJourProchainePiece();
        partieFinie=false;

        threadEnCours=new Thread(this);
        threadEnCours.start();
    }

    public void run(){
        boolean pieceEnMouvement=true;
        while(!partieFinie){
            try{Thread.sleep(100);}
            catch (InterruptedException e){e.printStackTrace();}
            if(idPieceEnMouvement!=0 )pieceEnMouvement=descendrePiece();
        }
    }



    public void nouvellePartie(){
        modelePrincipal.clearPieces();
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

        modeleProchainePiece.clearPieces();
        int x=(int)floor(TAILLE_PIECE_X_MAX/2);
        try {
            modeleProchainePiece.posePiece(x,pivotY,forme,pivotX,pivotY,"horizontal bas", clr);
        } catch (mvc.ExceptionsDuProjet.exceptionPieceHorsPlateau | exceptionChevauchementDePiece exceptionPieceHorsPlateau) {
            exceptionPieceHorsPlateau.printStackTrace();
        }
    }

    private void nouvellePiece(){
        int nbCasesX= modelePrincipal.getNbCasesX();
        int x=(int)floor(nbCasesX/2);
        try {
            modelePrincipal.posePiece(x,pivotY,forme,pivotX,pivotY,"horizontal bas", clr);
        } catch (mvc.ExceptionsDuProjet.exceptionPieceHorsPlateau exceptionPieceHorsPlateau) {
            exceptionPieceHorsPlateau.printStackTrace();
        } catch (mvc.ExceptionsDuProjet.exceptionChevauchementDePiece exceptionChevauchementDePiece) {
            finDePartie();
        }
        idPieceEnMouvement= modelePrincipal.selectionnerDernierPieceAdded();
        miseAJourProchainePiece();

        gestionDeLigneRemplie();

        //new Thread(this).start();
    }

    private void finDePartie() {
        partieFinie=true;

    }

    private void gestionDeLigneRemplie(){
        int nbColonnes= modelePrincipal.getNbCasesX();
        int nbLignes= modelePrincipal.getNbCasesY();
        //on parcourt les lignes depuis le bas du plateau

        int y=nbLignes-1;
        while(y>=0){
            if(ligneRemplie(y)){
                modelePrincipal.clearLigneEtDescendCases(y,idPieceEnMouvement);
                //on ne décremente pas y dans ce cas, car la ligne du dessus descend d'un cran
            }else{
                y--;
            }
        }
    }


    private boolean ligneRemplie(int numLigne){
        for(int x = 0; x< modelePrincipal.getNbCasesX(); x++){
            if(modelePrincipal.selectionnerPiece(x,numLigne)==0) return false;
        }
        //si on a atteint la fin de la ligne sans trouver de case vide
        return true;
    }



    public void deplacerPieceADroite(){
        try {
            modelePrincipal.deplacementPiece(idPieceEnMouvement,"droite");
        } catch (mvc.ExceptionsDuProjet.exceptionDeplacementPieceFigee e) {

        }
    }
    public void deplacerPieceAGauche(){
        try {
            modelePrincipal.deplacementPiece(idPieceEnMouvement,"gauche");
        } catch (mvc.ExceptionsDuProjet.exceptionDeplacementPieceFigee e) {

        }
    }

    /**
     * descend la piece correspondant a idPieceEnMouvement
     * @return vrai si le deplacement a reussit, faut sinon;
     */
    public boolean descendrePiece(){
        try {
            modelePrincipal.deplacementPiece(idPieceEnMouvement,"bas");
            return true;
        } catch (mvc.ExceptionsDuProjet.exceptionDeplacementPieceFigee e) {
            nouvellePiece();
            incrementerScore();
            return false;
        }
    }
    public void pivoterPiece(){
        modelePrincipal.pivoterPiece(idPieceEnMouvement,true);
    }

    private void incrementerScore(){
        score++;
        setChanged();
        notifyObservers();
    }


    public Modele getModeleProchainePiece() {
        return modeleProchainePiece;
    }
}
