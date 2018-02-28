package mvc.ModelePlateau;


import mvc.ExceptionsDuProjet.*;

import java.io.Console;
import java.util.ArrayList;

public class Plateau {
    private ArrayList<Piece> pieces;

    public int getNbCasesX() {
        return nbCasesX;
    }

    public int getNbCasesY() {
        return nbCasesY;
    }

    private int nbCasesX;
    private int nbCasesY;

    Plateau(){
        pieces=new ArrayList<Piece>();


    }

    public Plateau(int nbCasesX_in,int nbCasesY_in){
        this();
        nbCasesX=nbCasesX_in;
        nbCasesY=nbCasesY_in;
    }

    public void ajouterPiece(int posX,int posY) throws Exception{
        //boolean[][] FormeDeLaPiece={{true}}; // correspond a une seule case

        boolean[][] FormeDeLaPiece={{true,true},
                                   {true,false}};

        if(posX+2 >nbCasesX ){
            throw new exceptionPieceHorsPlateau();
        }

        Piece p = new Piece(FormeDeLaPiece,posX,posY);
        pieces.add(p);
    }

    public void ajouterPiece(int posX,int posY, boolean[][] FormeDeLaPiece) throws Exception{

        int tx=FormeDeLaPiece.length;
        int ty=0;
        if(tx!=0){
            ty=FormeDeLaPiece[0].length;
        }

        if(tx+posX<0 || tx+posX>nbCasesX || ty+posY<0 || ty+posY>nbCasesY){
            throw new exceptionPieceHorsPlateau();
        }

        Piece p = new Piece(FormeDeLaPiece,posX,posY);
        pieces.add(p);
    }

    public int[][] etatDuPlateau() {
        int[][] resultat=new int[nbCasesX][nbCasesY];
        for(int x=0;x<nbCasesX;x++){
            for(int y=0;y<nbCasesX;y++){
                resultat[x][y]=0;
            }
        }
        for (Piece p:
             pieces) {
            for(Case c:
                    p.positionsAbsolues()){
                //On verifie que le case soient bien dans les limites du plateau
                if (c.getX() < 0 || c.getX()>=nbCasesX || c.getY() < 0 || c.getY()>=nbCasesY ) {
                    System.out.println("warning: case dépassant les limites du tableau (fonction: etatDuPlateau()");
                }
                else
                {
                    resultat[c.getX()][c.getY()]=p.getId();
                }
            }
        }
        return resultat;
    }


}


