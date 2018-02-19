/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import mvc.ModelePlateau.Plateau;

import java.util.Observable;

/**
 *
 * @author fred
 */
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
        plat=new Plateau(10,10);

    }

    public void posePiece(int posX,int posY){
        plat.ajouterPiece(posX,posY);

        setChanged();
        notifyObservers();
    }
    
    public void calc() {

        
        // notification de la vue, suite à la mise à jour du champ lastValue
        setChanged();
        notifyObservers();
    }



}