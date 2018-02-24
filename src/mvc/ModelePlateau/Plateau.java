package mvc.ModelePlateau;

import com.sun.org.apache.xpath.internal.operations.Bool;

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

    public void ajouterPiece(int posX,int posY){
        //boolean[][] FormeDeLaPiece={{true}}; // correspond a une seule case

        boolean[][] FormeDeLaPiece={{true,true},
                                   {true,false}};

        Piece p = new Piece(FormeDeLaPiece,posX,posY);
        pieces.add(p);
    }

    public int[][] etatDuPlateau(){
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
