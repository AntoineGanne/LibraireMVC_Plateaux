package mvc.ModelePlateau;


import java.util.Random;

import java.util.ArrayList;

public class Piece {
    private ArrayList<Case> forme;
    private int  tailleX, tailleY;
    private boolean estFigee;
    private int posAbsolueX,posAbsolueY;  //correspond au coin en haut a gauche de la forme de la pièce
    private int id;   //pour pouvoir differencier les differents pièces, a voir si c'est nécessaire
    //private int pivotX,pivotY;
    private boolean[] deplacementsPossible; //0=>droite ;1=>haut ;2=>gauche ;3=>bas

    //region GETTER/SETTER
    public int getId() {
        return id;
    }


    public boolean [][] getMatriceBoolPiece(){
        boolean[][] result = new boolean[tailleX][tailleY];
        for(int i=0;i<tailleX;i++){
            for(int j=0;j<tailleY;j++){
                result[i][j]=false;
            }
        }

        for(int i=0;i<forme.size();i++){
            result[forme.get(i).getX()][forme.get(i).getY()]=true;
        }

        return result;
    }

    public boolean getEstFigee() {
        return estFigee;
    }
    public void setEstFigee(boolean estFigee) {
        this.estFigee = estFigee;
    }

    public ArrayList<Case> getForme() {
        return forme;
    }
    public void setForme(ArrayList<Case> forme) {
        this.forme = forme;
    }

    public int getPosAbsolueX() {
        return posAbsolueX;
    }
    public void setPosAbsolueX(int posAbsolueX) {
        this.posAbsolueX = posAbsolueX;
    }

    public int getPosAbsolueY() {
        return posAbsolueY;
    }
    public void setPosAbsolueY(int posAbsolueY) {
        this.posAbsolueY = posAbsolueY;
    }
    //endregion


    //region CONSTRUCTEURS
    Piece(){
        tailleX=3;
        tailleY=3;
        forme=new ArrayList<Case>();

        Random rand=new Random();
        id=rand.nextInt(253)+1;

        deplacementsPossible=new boolean[]{true,true,true,true};
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
    //endregion




    /**
     * simule un pivotage anti-horaire de 90 degrés sans modifier la piece
     * (le plateau doit faire des tests avant de confirmer le pivotage)
     * @param sensHoraire si vrai alors pivot dans le sens horaire.
     * @return les cases qui forment la version pivottée de la piece
     */
    public ArrayList<Case> pivoter(boolean sensHoraire){
        ArrayList<Case> resultat=new ArrayList<Case>(forme.size());
        for (Case c:
             forme) {
            Case casemodifie=new Case(c);
            casemodifie.pivoterCase(sensHoraire);
            resultat.add(casemodifie);
        }
        return resultat;
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

    public boolean peutAllerADroite(){
        return deplacementsPossible[0];
    }
    public boolean peutAllerEnHaut(){
        return deplacementsPossible[1];
    }
    public boolean peutAllerAGauche(){
        return deplacementsPossible[2];
    }
    public boolean peutAllerEnBas(){
        return deplacementsPossible[3];
    }
    public boolean peutAllerDirection(String direction){
        switch(direction){
            case "droite": // Droite
                return peutAllerADroite();
            case "gauche": // Gauche
                return peutAllerAGauche();
            case "bas": // Bas
                return peutAllerEnBas();
            case "haut": // Haut
                return peutAllerEnHaut();
            default:
                return false;
        }
    }

    public void setDeplacementsPossibles(String instructions){
        for(int i=0;i<deplacementsPossible.length;i++) deplacementsPossible[i]=false;
        if(instructions.contains("horizontal")){
            deplacementsPossible[0]=true;
            deplacementsPossible[2]=true;
        }
        if(instructions.contains("vertical")){
            deplacementsPossible[1]=true;
            deplacementsPossible[3]=true;
        }
    }




}
