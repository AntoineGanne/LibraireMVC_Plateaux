/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

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

    public void deplacementPiece(int numPiece, int direction, int nbcase){
        try {
            plat.deplacerPiece(numPiece,direction,nbcase);
        }
        catch(Exception e){
            //e.printStackTrace();
        }

        setChanged();
        notifyObservers();
    }
}
