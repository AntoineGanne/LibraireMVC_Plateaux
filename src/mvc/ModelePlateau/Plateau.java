package mvc.ModelePlateau;


import mvc.ExceptionsDuProjet.*;
import java.util.ArrayList;

public class Plateau {
    private ArrayList<Piece> pieces;
    private int nbCasesX;
    private int nbCasesY;

    public int getNbCasesX() {
        return nbCasesX;
    }
    public int getNbCasesY() {
        return nbCasesY;
    }



    Plateau(){
        pieces=new ArrayList<Piece>();


    }

    public Plateau(int nbCasesX_in,int nbCasesY_in){
        this();
        nbCasesX=nbCasesX_in;
        nbCasesY=nbCasesY_in;
    }

    public void ajouterPiece(int posX,int posY) throws Exception{
       ajouterPiece(posX,posY,new boolean[][]{{true}});
    }

    public Piece recupPiece(int numPiece) throws Exception{
        int[][] etatplateau=etatDuPlateau();
        boolean b_yaunepiece = false;
        Piece p = null;
        for(int i=0;i<pieces.size();i++){
            if(pieces.get(i).getId()==numPiece){
                b_yaunepiece=true;
                p = pieces.get(i);
            }
        }

        if(b_yaunepiece){
            return p;
        }
        else{
            throw new exceptionIDPieceDontExist();
        }
    }

    public void deplacerPiece(int numPiece, int direction, int nbcase) throws exceptionDeplacementPieceFigee { // avec l'id de la piece, la deplacer jusqu'a ce que son pivot atteigne posX, posY
        // direction : 1°Droite ; 2°Gauche ; 3°Bas ; 4°Haut
        int[][] etatplateau=etatDuPlateau();
        Piece p = null;
        try {
            p = recupPiece(numPiece);
        }
        catch(Exception e){
            System.out.println("exception ID don't exist captée");
        }

        boolean[][] matrice = p.getMatriceBoolPiece();

        if(!p.getEstFigee()){ // si droit au déplacement (piece non figée)
            switch(direction){
                case 1: // Droite
                    p.setPosAbsolueX(p.getPosAbsolueX()+nbcase);
                    break;
                case 2: // Gauche
                    p.setPosAbsolueX(p.getPosAbsolueX()-nbcase);
                    break;
                case 3: // Bas
                    p.setPosAbsolueY(p.getPosAbsolueY()+nbcase);
                    break;
                case 4: // Haut
                    p.setPosAbsolueY(p.getPosAbsolueY()-nbcase);
                    break;
            }
        }
        else {
            throw new exceptionDeplacementPieceFigee();
        }

    }


    public void ajouterPiece(int posX,int posY, boolean[][] FormeDeLaPiece) throws Exception{
        ajouterPiece(posX,posY,FormeDeLaPiece,0,0);
    }

    public void ajouterPiece(int posX_input,int posY_input, boolean[][] FormeDeLaPiece, int pivotX, int pivotY) throws Exception{
        int tx=FormeDeLaPiece.length;
        int ty=0;
        if(tx!=0){
            ty=FormeDeLaPiece[0].length;
        }

        int posX=posX_input-pivotX;
        int posY=posY_input-pivotY;

        if(tx+posX<0 || tx+posX>nbCasesX || ty+posY<0 || ty+posY>nbCasesY){
            throw new exceptionPieceHorsPlateau();
        }

        int[][] etatPlateau=etatDuPlateau();
        for(int i=0;i<tx;i++){
            for(int j=0;j<ty;j++){
                if(FormeDeLaPiece[i][j] && etatPlateau[i+posX][j+posY]!=0){
                    throw new exceptionChevauchementDePiece();
                }
            }
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


