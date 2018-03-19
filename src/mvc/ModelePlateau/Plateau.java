package mvc.ModelePlateau;


import mvc.ExceptionsDuProjet.*;

import java.awt.*;
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

    public Piece recupPiece(int numPiece) throws exceptionIDPieceDontExist{
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

    public Piece getPiece(int idPiece){
        //l'identifiant est censé etre l'index de la piece dans le tableau de Piece + 1
        // le +1 sert a ne pas avoir un identifianrt a 0
        assert (idPiece>0 && idPiece<=pieces.size());
        return pieces.get(idPiece-1);
    }

    public void deplacerPiece(int numPiece, String direction, int nbcase) throws exceptionDeplacementPieceFigee { // avec l'id de la piece, la deplacer jusqu'a ce que son pivot atteigne posX, posY
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
                case "droite": // Droite
                    p.setPosAbsolueX(p.getPosAbsolueX()+nbcase);
                    break;
                case "gauche": // Gauche
                    p.setPosAbsolueX(p.getPosAbsolueX()-nbcase);
                    break;
                case "bas": // Bas
                    p.setPosAbsolueY(p.getPosAbsolueY()+nbcase);
                    break;
                case "haut": // Haut
                    p.setPosAbsolueY(p.getPosAbsolueY()-nbcase);
                    break;
                default:
                    break;
            }
        }
        else {
            throw new exceptionDeplacementPieceFigee();
        }
    }

    public void deplacerPiece(int numPiece, String direction) throws Exception { // avec l'id de la piece, la deplacer jusqu'a ce que son pivot atteigne posX, posY
        int[][] etatplateau=etatDuPlateau();
        Piece p = null;
        try {
            p = recupPiece(numPiece);
        }
        catch(Exception e){
            System.out.println("exception ID don't exist captée");
            return;
        }
        assert p != null;
        //si la piece est figée
        if(p.getEstFigee()) throw new exceptionDeplacementPieceFigee();
        if(!p.peutAllerDirection(direction))throw new exceptionDeplacementPieceFigee();

        int[][] etatPlateauSansP=etatDuPlateauSansUnePiece(p.getId());
        Piece pieceTest=new Piece(p.getMatriceBoolPiece(),p.getPosAbsolueX(),p.getPosAbsolueY());

        switch(direction){
            case "droite": // Droite
                pieceTest.setPosAbsolueX(p.getPosAbsolueX()+1);
                break;
            case "gauche": // Gauche
                pieceTest.setPosAbsolueX(p.getPosAbsolueX()-1);
                break;
            case "bas": // Bas
                pieceTest.setPosAbsolueY(p.getPosAbsolueY()+1);
                break;
            case "haut": // Haut
                pieceTest.setPosAbsolueY(p.getPosAbsolueY()-1);
                break;
            default:
                break;
        }
        if(deplacementPossible(pieceTest,etatPlateauSansP)){
            p.setPosAbsolueX(pieceTest.getPosAbsolueX());
            p.setPosAbsolueY(pieceTest.getPosAbsolueY());
        }else{
            throw new Exception("deplacement impossible");
        }

    }

    private boolean deplacementPossible(Piece p,int[][] etatPlateauSansP){
        for(Case c:
                p.positionsAbsolues()){
            int x=c.getX();
            int y=c.getY();
            //la piece ne sort pas du plateau
            if(x<0 || x>=nbCasesX || y<0 || y>nbCasesY) return false;
            else{
                //la piece n'occupe pas une case deja occupée par un autre piece
                if(etatPlateauSansP[x][y]!=0){
                    return false;
                }
            }
        }
        return true;
    }




    public void ajouterPiece(int posX,int posY, boolean[][] FormeDeLaPiece) throws Exception{
        ajouterPiece(posX,posY,FormeDeLaPiece,0,0);
    }

    public void ajouterPiece(int posX_input,int posY_input, boolean[][] FormeDeLaPiece, int pivotX, int pivotY) throws Exception{
        ajouterPiece(posX_input,posY_input,FormeDeLaPiece,pivotX,pivotY,"");
    }

    public void ajouterPiece(int posX_input,int posY_input, boolean[][] FormeDeLaPiece,
                             int pivotX, int pivotY, String deplacementsPossibles,Color couleur) throws Exception{
        try{
            ajouterPiece(posX_input,posY_input,FormeDeLaPiece,pivotX,pivotY,deplacementsPossibles);

            pieces.get(pieces.size()-1).setCouleur(couleur); //risqué?
        }
        catch(Exception ex){
            throw ex;
        }
    }
    public void ajouterPiece(int posX_input,int posY_input, boolean[][] FormeDeLaPiece,
                             int pivotX, int pivotY, String deplacementsPossibles) throws Exception{
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

        //l'id de la piece est liée a sa place dans la tableau de pieces afin de facilement retrouver une piece
        //cependant la valeur 0 correspond a l'id d'une case vide, donc on ajoute 1 pour eviter la confusion
        int idPiece=pieces.size()+1;

        Piece p = new Piece(FormeDeLaPiece,posX,posY,pieces.size()+1);
        p.setDeplacementsPossibles(deplacementsPossibles);
        pieces.add(p);
    }



    /**
     * definit les déplacements possibles de la piece
     * Si Instructions contient "horizontal" la piece peut se deplacer horizontalement
     * Si Instructions contient "vertical" la piece peut se deplacer verticalement
     * si Instruction ne contient pas ces mots clés, aucun déplacemnts n'est autorisé
     * @param id int
     * @param instructions String
     */
    public void setDeplacementsPossiblesPiece(int id, String instructions){
        try{
            Piece p=recupPiece(id);
            p.setDeplacementsPossibles(instructions);
        } catch (mvc.ExceptionsDuProjet.exceptionIDPieceDontExist exceptionIDPieceDontExist) {
            exceptionIDPieceDontExist.printStackTrace();
        }

    }


    public void pivoterPiece(int idPiece, boolean sensHoraire) throws exceptionChevauchementDePiece
    {
        Piece pieceSelected=null;
        try{
            pieceSelected=this.recupPiece(idPiece);
        }catch (exceptionIDPieceDontExist e){
            e.printStackTrace();
        }

        /*
        if(pieceSelected!=null){
            ArrayList<Case> formePiecePivote=pieceSelected.pivoter();

            int[][] etatPlateau=etatDuPlateau();
            for (Piece p:
                 pieces) {
                if(p.getId()!=idPiece){
                    for(Case c:
                            p.getForme())
                        if(c.getX()==)
                }
            }
        }
        */
    }

    public Color getCouleurDePiece(int idPiece){
        Piece p=getPiece(idPiece);
        return p.getCouleur();

    }

    /**
     * représente l'état du plateau sous la forme d'un tableau 2D de Int
     * @return int[][]
     */
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

    /**
     * retourne l'etat du plateau sous la forme d'un tableau 2d en ignorant une piece.
     * Cela sert pour tester les collisions lors du deplacment/pivot d'une piece
     * @param idPiece  id de la piece a ignore
     * @return int[][]
     */
    public int[][] etatDuPlateauSansUnePiece(int idPiece) {
        int[][] resultat=new int[nbCasesX][nbCasesY];
        for(int x=0;x<nbCasesX;x++){
            for(int y=0;y<nbCasesX;y++){
                resultat[x][y]=0;
            }
        }
        for (Piece p:
                pieces) {
            if(p.getId()!=idPiece){
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
        }
        return resultat;
    }


    /**
     *supprime toutes les pieces du plateau
     */
    public void clearPieces() {
        pieces.clear();
    }
}


