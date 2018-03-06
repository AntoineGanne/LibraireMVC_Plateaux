package mvc.ModelePlateau;


import java.util.Random;

import java.util.ArrayList;

public class Piece {
    private ArrayList<Case> forme;
    private int  tailleX, tailleY;
    private boolean estFigee;
    private int posAbsolueX,posAbsolueY;  //correspond au coin en haut a gauche de la forme de la pièce
    private int id;   //pour pouvoir differencier les differents pièces, a voir si c'est nécessaire
    private int pivotX,pivotY;  //pas tout de suite nécessaire en fait

    public int getId() {
        return id;
    }




    public boolean [][] getMatriceBoolPiece(){
        boolean[][] result = new boolean[tailleX][tailleY];
        for(int i=0;i<tailleX;i++){
            for(int j=0;j<tailleX;j++){
                result[i][j]=false;
            }
        }

        for(int i=0;i<forme.size();i++){
            result[forme.get(i).getX()][forme.get(i).getY()]=true;
        }

        return result;
    }

    Piece(){
        tailleX=3;
        tailleY=3;
        forme=new ArrayList<Case>();

        Random rand=new Random();
        id=rand.nextInt(253)+1;
    }


    /**
     * On crée la pièce en lui donnant un tableau 2D de boolean
     * Surement la méthode la plus simple pour rapidement créer des pièces personnalisée.
     * Le constructeur transforme la matrice en une collection de cases (les coordonnées des cases sont relatives)
     * @param matrice le tableau de boolean
     */
    Piece(boolean [][] matrice,int posAbsolueX_,int posAbsolueY_){
        this();

        posAbsolueX=posAbsolueX_;
        posAbsolueY=posAbsolueY_;

        // on récupere les tailles en x et y du tableau matrice
        int tx=matrice.length;
        int ty=0;
        if(tx!=0){
            ty=matrice[0].length;
        }
        else{
            System.out.println("erreur Piece()");
        }

        tailleX=tx;
        tailleY=ty;


        //

        //initialisation de forme
        for(int x=0;x<tx;x++){
            for(int y=0;y<ty;y++){
                if(matrice[x][y])forme.add(new Case(x,y)) ;
            }
        }
    }

    /**
     * permet de retrouver les positions absolues des cases de la pièce
     * @return une collection de cases.
     */
    public ArrayList<Case> positionsAbsolues(){
        ArrayList<Case> resultat=new ArrayList<Case>(1);
        for (Case c:
             forme) {
            resultat.add(new Case(c.getX()+posAbsolueX,c.getY()+posAbsolueY));
        }
        return resultat;
    }

}
