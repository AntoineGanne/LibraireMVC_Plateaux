/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import mvc.ModelePlateau.Plateau;

import java.awt.*;
import java.util.Observable;


public class Modele extends Observable {

    public int[][] getEtatDuPlateau() {
        return plat.etatDuPlateau();
    }

    public int getNbCasesX() {
        return plat.getNbCasesX();
    }
    public int getNbCasesY() {
        return plat.getNbCasesY();
    }

    private int[][] etatDuPlateau;
    private Plateau plat;


    Modele()
    {
        plat=new Plateau(30,30);
    }
    public Modele(int nbColonnes, int nbLignes)
    {
        plat=new Plateau(nbColonnes,nbLignes);
    }


    public void posePiece(int posX,int posY){
        try {
            plat.ajouterPiece(posX,posY,new boolean[][]{{false, true,false},{true,true ,true},{false, true,false}},1,1);
        }
        catch(Exception e){
            //e.printStackTrace();
        }

        setChanged();
        notifyObservers();
    }

    public void posePiece(int posX,int posY, boolean[][] FormeDeLaPiece,
                          int pivotX, int pivotY){
        try {
            plat.ajouterPiece(posX,posY,FormeDeLaPiece,pivotX,pivotY);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        setChanged();
        notifyObservers();
    }


    public void posePiece(int posX,int posY, boolean[][] FormeDeLaPiece,
                          int pivotX, int pivotY, String deplacementsPossibles){
        try {
            plat.ajouterPiece(posX,posY,FormeDeLaPiece,pivotX,pivotY,deplacementsPossibles);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        setChanged();
        notifyObservers();
    }

    /**
     * crée une piece selon les paramètres donnés
     * @param posX position en X relativement a l'origine du plateau du pivot de la piece
     * @param posY position en Y relativement a l'origine du plateau du pivot de la piece
     * @param FormeDeLaPiece boolean[][] decrivant la forme de la piece (true correspond a une case remplie)
     * @param pivotX position en X du pivot relativement a l'origine (FormeDeLaPiece[0][0])
     * @param pivotY position en Y du pivot relativement a l'origine (FormeDeLaPiece[0][0])
     * @param deplacementsPossibles String decrivant les déplacement possibles de la piece.
     *                              mots clés reconnus:"vertical" "horizontal" "tous".
     *                              par défaut (si aucun mot clé reconnu): aucun mouvement autorisé
     * @param couleur  Couleur de la piece
     */
    public void posePiece(int posX,int posY, boolean[][] FormeDeLaPiece,
                          int pivotX, int pivotY, String deplacementsPossibles,Color couleur)
    {
        try {
            plat.ajouterPiece(posX,posY,FormeDeLaPiece,pivotX,pivotY,deplacementsPossibles,couleur);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        setChanged();
        notifyObservers();
    }

    /**
     * renvoit l'id de la piece située en posX,posY  si il y a bien une piece
     * revoit 0 sinon
     * @param posX position en X
     * @param posY position en Y
     * @return l'id de la piece située en posX,posY  si il y a bien une piece
     */
    public int selectionnerPiece(int posX,int posY){
        etatDuPlateau=getEtatDuPlateau();
        return etatDuPlateau[posX][posY];
    }


    /**
     * deplace d'une case la piece dont l'id est numPiece et dans la direction indiquée par direction
     * @param numPiece id de la piece
     * @param direction mot clé de la direction
     */
    public void deplacementPiece(int numPiece, String direction){
        try {
            plat.deplacerPiece(numPiece,direction);
        }
        catch(Exception e){
            //e.printStackTrace();
        }

        setChanged();
        notifyObservers();
    }

    /**
     * recupere la couleur de la piece ayant l'id donné en paramètre.
     * surtout utile a la vue.
     * @param idPiece
     * @return couleur d'une piece
     */
    public Color getCouleurDePiece(int idPiece){
        return plat.getCouleurDePiece(idPiece);
    }

    /**
     * permet d'appliquer des regles de deplacements possibles a une piece
     * @param id id de la piece
     * @param instructions  String contenant les mots clés correspondant aux deplacements possibles
     */
    public void setDeplacementsPossiblesPiece(int id,String instructions){
        plat.setDeplacementsPossiblesPiece(id,instructions);
    }

    /**
     * supprime toutes les pieces du plateau
     */
    public void clearPieces(){
        plat.clearPieces();
        setChanged();
        notifyObservers();
    }

    /**
     * vide la case => supprime la case de la piece qui y est présente (si il y a bien un piece a cet endroit)
     * @param posX
     * @param posY
     */
    public void clearCase(int posX,int posY){
        etatDuPlateau=getEtatDuPlateau();
        if(etatDuPlateau[posX][posY]!=0){
            int  idPiece=etatDuPlateau[posX][posY];
            plat.clearCase(posX,posY,idPiece);
            setChanged();
            notifyObservers();
        }
    }
}
