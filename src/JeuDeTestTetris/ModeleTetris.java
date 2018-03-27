package JeuDeTestTetris;

import mvc.ExceptionsDuProjet.exceptionChevauchementDePiece;
import mvc.Modele;

import java.awt.*;
import java.sql.Timestamp;
import java.time.Clock;
import java.util.Calendar;
import java.util.Observable;

import static java.lang.Math.floor;



public class ModeleTetris extends Observable implements Runnable{
    private Modele modelePrincipal;
    private Modele modeleProchainePiece;
    private int idPieceEnMouvement;

    private static int TAILLE_PIECE_X_MAX=5;
    private static int TAILLE_PIECE_Y_MAX=5;



    private int score;
    private boolean partieFinie;

    //attributs prochaine piece
    private boolean[][] forme;
    private int pivotX , pivotY;
    private Color clr;

    /**
     * Constructeur du modele
     * @param nbColonnes nombre de colonnes
     * @param nbLignes  nombre de lignes
     */
    public  ModeleTetris(int nbColonnes,int nbLignes){
        modelePrincipal =new Modele(nbColonnes,nbLignes);
        modeleProchainePiece=new Modele(TAILLE_PIECE_X_MAX,TAILLE_PIECE_Y_MAX);
        miseAJourProchainePiece();
        partieFinie=false;

        new Thread(this).start();
    }

    /**
     * thread du modèle:
     * fait descendre la piece toutes les 200 millisecondes
     */
    public void run(){
        //  la boucle infini sert a ce que le thread marche pour les parties suivantes
        while(true){
            if(!partieFinie){
                try{Thread.sleep(200);}
                catch (InterruptedException e){e.printStackTrace();}
                if(idPieceEnMouvement!=0 )descendrePiece();
            }
        }
    }


    /**
     * initialise une nouvelle partie
     */
    public void nouvellePartie(){
        modelePrincipal.clearPieces();
        nouvellePiece();
        score=0;
        partieFinie=false;

        miseAJourProchainePiece();

        setChanged();
        notifyObservers();

    }

    /**
     * selectionne une piece aléatoire
     */
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

    /**
     * crée une nouvelle piece(dont les attributs ont été auparavant definit par miseAJourProchainePiece()
     */
    private void nouvellePiece(){
        int nbCasesX= modelePrincipal.getNbCasesX();
        int x=(int)floor(nbCasesX/2);
        try {
            modelePrincipal.posePiece(x,pivotY,forme,pivotX,pivotY,"horizontal bas", clr);
        } catch (mvc.ExceptionsDuProjet.exceptionPieceHorsPlateau exceptionPieceHorsPlateau) {
            exceptionPieceHorsPlateau.printStackTrace();
        } catch (mvc.ExceptionsDuProjet.exceptionChevauchementDePiece exceptionChevauchementDePiece) {
            //si l'emplacement de création de la piece est déjà pris alors la partie est finie
            partieFinie=true;
        }
        idPieceEnMouvement= modelePrincipal.selectionnerDernierPieceAdded();
        miseAJourProchainePiece();

        gestionDeLigneRemplie();


    }


    /**
     * verifie si une ligne du plateau est remplie,
     * appelle la fonction de suppression de ligne le cas écheant
     */
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

    /**
     *
     * @param numLigne  coordonnée de la ligne a tester
     * @return vrai si la ligne de coordonnée numLigne est remplie
     */
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
        } catch (mvc.ExceptionsDuProjet.exceptionDeplacementPieceFigee ignored) {

        }
    }
    public void deplacerPieceAGauche(){
        try {
            modelePrincipal.deplacementPiece(idPieceEnMouvement,"gauche");
        } catch (mvc.ExceptionsDuProjet.exceptionDeplacementPieceFigee ignored) {

        }
    }

    /**
     * descend la piece correspondant a idPieceEnMouvement
     * @return vrai si le deplacement a reussit, faut sinon;
     */
    public boolean descendrePiece(){
        try {
            if(!partieFinie)modelePrincipal.deplacementPiece(idPieceEnMouvement,"bas");
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


    //GETTER
    public Modele getModelePrincipal() {
        return modelePrincipal;
    }
    public boolean isPartieFinie() {
        return partieFinie;
    }

    public int getScore() {
        return score;
    }
    public Modele getModeleProchainePiece() {
        return modeleProchainePiece;
    }

}
