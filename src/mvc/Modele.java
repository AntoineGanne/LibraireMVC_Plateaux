/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import mvc.ModelePlateau.Plateau;

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
                          int pivotX, int pivotY, String deplacementsPossibles)
    {
        try {
            plat.ajouterPiece(posX,posY,FormeDeLaPiece,pivotX,pivotY,deplacementsPossibles);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        setChanged();
        notifyObservers();
    }

    
    public int selectionnerPiece(int posX,int posY){
        etatDuPlateau=getEtatDuPlateau();
        return etatDuPlateau[posX][posY];
    }



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

    public void setDeplacementsPossiblesPiece(int id,String instructions){
        plat.setDeplacementsPossiblesPiece(id,instructions);
    }
}
