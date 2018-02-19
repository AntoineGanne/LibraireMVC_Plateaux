package mvc.ModelePlateau;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;

public class Plateau {
    ArrayList<Piece> pieces;

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

        boolean[][] btab={{true}};

        Piece p = new Piece(btab,0,0);
        pieces.add(p);
    }

    public Plateau(int nbCasesX_in,int nbCasesY_in){
        this();
        nbCasesX=nbCasesX_in;
        nbCasesY=nbCasesY_in;
    }

    public void ajouterPiece(int posX,int posY){
        boolean[][] btab={{true}};

        Piece p = new Piece(btab,posX,posY);
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
                resultat[c.getX()][c.getY()]=p.getId();
            }
        }
        return resultat;
    }
}
