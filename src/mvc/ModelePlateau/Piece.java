package mvc.ModelePlateau;


import java.awt.*;
import java.util.Random;

import java.util.ArrayList;

public class Piece {
    private ArrayList<Case> forme;
    //private int  tailleX, tailleY;

    private int posAbsolueX,posAbsolueY;  //correspond au coin en haut a gauche de la forme de la pièce
    private int id;   //pour pouvoir differencier les differents pièces, a voir si c'est nécessaire
    private static int DEFAULT_ID=1;
    //private int pivotX;
    //private int pivotY;
    private boolean[] deplacementsPossible; //0=>droite ;1=>haut ;2=>gauche ;3=>bas
    private boolean estFigee;


    //couleur
    private Color couleur;
    private static float SATURATION=0.5f; //paramètre par defaut pour la création d'un couleur en HSB
    private static float BRIGHTNESS=0.8f; //idem

    //region GETTER/SETTER
    public int getId() {
        return id;
    }

    public boolean [][] getMatriceBoolPiece(){
        int tailleX=getTailleX();
        int tailleY=getTailleY();
        boolean[][] result = new boolean[tailleX][tailleY];
        for(int i=0;i<tailleX;i++){
            for(int j=0;j<tailleY;j++){
                result[i][j]=false;
            }
        }

        int  pivotX=getPivotX();
        int pivotY=getPivotY();
        for(int i=0;i<forme.size();i++){
            result[forme.get(i).getX()+pivotX][forme.get(i).getY()+pivotY]=true;
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

    /**
     * calcule la taille en X de la forme de la piece
     * @return
     */
    public int getTailleX(){
        int min=100;
        int max=-100;
        for(Case c: forme){
            int x=c.getX();
            max=(x>max? x:max);
            min=(x<min?x:min);
        }
        return max-min+1;
    }
    /**
     * calcule la taille en X de la forme de la piece
     * @return
     */
    public int getTailleY(){
        int min=100;
        int max=-100;
        for(Case c: forme){
            int y=c.getY();
            max=(y>max? y:max);
            min=(y<min?y:min);
        }
        return max-min+1;
    }

    public int getPivotX(){
        int min=100;
        for(Case c: forme){
            int x=c.getX();
            min=(x<min?x:min);
        }
        return -min;
    }
    public int getPivotY(){
        int min=100;
        for(Case c: forme){
            int y=c.getY();
            min=(y<min?y:min);
        }
        return -min;
    }


    //public int getPivotY() { return pivotY; }
    //public int getPivotX() { return pivotX; }

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

    public Color getCouleur() { return couleur; }
    public void setCouleur(Color couleur) { this.couleur = couleur; }
    /**
     * set les deplacement possibles selon les instructions données dans le String
     * les instructions sont censées contenir "horizontal" "vertical" "tous" "haut" "bas" "gauche" "droite" ou etre vide.
     * Il est possible d'utiliser plusieurs mots clés.
     * par defaut(aucun mot reconnu ou vide) tous les déplacement sont interdits
     * @param instructions String
     */
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
        if(instructions.contains("droite")){
            deplacementsPossible[0]=true;
        }
        if(instructions.contains("haut")){
            deplacementsPossible[1]=true;
        }
        if(instructions.contains("gauche")){
            deplacementsPossible[2]=true;
        }
        if(instructions.contains("bas")){
            deplacementsPossible[3]=true;
        }
        if(instructions.contains("tous")){
            for(int j=0;j<deplacementsPossible.length;j++){
                deplacementsPossible[j]=true;
            }
        }
    }

    /**
     * renvoit une collection de cases correspondant a la forme de la piece en position absolue (= relativement au plateau)
     * @return une collection de cases
     */
    public ArrayList<Case> getCasesPositionAbsolues(){
        ArrayList<Case> resultat=new ArrayList<>();
        for(Case c:forme){
            int newX=c.getX()+posAbsolueX;
            int newY=c.getY()+posAbsolueY;
            Case cResultat=new Case(newX,newY);
            resultat.add(cResultat);
        }
        return resultat;
    }
    //endregion


    //region CONSTRUCTEURS

    /**
     * constructeur sans paramètres,
     * initialise certains composant, notamment forme
     * @warning la géneration aléatoire de couleur semble ne pas marcher
     */
    public Piece(){
        forme=new ArrayList<Case>();

        Random rand=new Random();
        id=DEFAULT_ID;

        float randFloat=rand.nextFloat();
        couleur=Color.getHSBColor(randFloat,SATURATION, BRIGHTNESS);

        deplacementsPossible=new boolean[]{true,true,true,true};
    }


    /**
     * On crée la pièce en lui donnant un tableau 2D de boolean
     * Surement la méthode la plus simple pour rapidement créer des pièces personnalisée.
     * Le constructeur transforme la matrice en une collection de cases (les coordonnées des cases sont relatives)
     * @param matrice boolean[][] decrivant la forme de la piece
     */
    public Piece(boolean [][] matrice,int posAbsolueX_,int posAbsolueY_,int pivotX,int pivotY){
        this();

        this.posAbsolueX=posAbsolueX_;
        this.posAbsolueY=posAbsolueY_;
        //this.pivotX=pivotX_input;
        //this.pivotY=pivotY_input;

        // on récupere les tailles en x et y du tableau matrice
        int tx=matrice.length;
        int ty=0;
        if(tx!=0){
            ty=matrice[0].length;
        }
        else{
            System.out.println("erreur Piece()");
        }
        /*
        tailleX=tx;
        tailleY=ty;
        */
        //
        //initialisation de forme
        for(int x=0;x<tx;x++){
            for(int y=0;y<ty;y++){
                if(matrice[x][y])forme.add(new Case(x-pivotX,y-pivotY)) ;
            }
        }
    }

    /**
     * On crée la pièce en lui donnant un tableau 2D de boolean
     * Surement la méthode la plus simple pour rapidement créer des pièces personnalisée.
     * Le constructeur transforme la matrice en une collection de cases (les coordonnées des cases sont relatives)
     * L'identifiant est donné en paramètre pour essayer d'assurer l'unicité des identitifiants.
     * Le pivot est fixé comme etant le coin superieur gauche de la matrice
     * @param matrice boolean[][] decrivant la forme de la piece
     * @param posAbsolueX_ position absolue en X du pivot de la piece
     * @param posAbsolueY_ position absolue en Y du pivot de la piece
     * @param id_input  identifiant donné par la classe Plateau
     */
    public Piece(boolean [][] matrice,int posAbsolueX_,int posAbsolueY_,int id_input){
        this(matrice,posAbsolueX_,posAbsolueY_,0,0);
        id=id_input;
    }

    /**
     * On crée la pièce en lui donnant un tableau 2D de boolean
     * Surement la méthode la plus simple pour rapidement créer des pièces personnalisée.
     * Le constructeur transforme la matrice en une collection de cases (les coordonnées des cases sont relatives)
     * L'identifiant est donné en paramètre pour essayer d'assurer l'unicité des identitifiants
     * @param matrice boolean[][] decrivant la forme de la piece
     * @param posAbsX position absolue en X du pivot de la piece
     * @param posAbsY position absolue en Y du pivot de la piece
     * @param pivotX position relative a la matrice du pivot(piece centrale) en X
     * @param pivotY position relative a la matrice du pivot(piece centrale) en Y
     * @param id_input  identifiant donné par la classe Plateau
     */
    public Piece(boolean [][] matrice,int posAbsX,int posAbsY,int pivotX,int pivotY,int id_input){
        this(matrice,posAbsX,posAbsY,pivotX,pivotY);
        id=id_input;
    }

    /**
     * On crée la pièce en lui donnant un tableau 2D de boolean
     * Surement la méthode la plus simple pour rapidement créer des pièces personnalisée.
     * Le constructeur transforme la matrice en une collection de cases (les coordonnées des cases sont relatives)
     * L'identifiant est donné en paramètre pour essayer d'assurer l'unicité des identitifiants
     * @param matrice boolean[][] decrivant la forme de la piece
     * @param posAbsolueX_ position absolue en X du pivot de la piece
     * @param posAbsolueY_ position absolue en Y du pivot de la piece
     * @param id_input  identifiant donné par la classe Plateau
     * @param couleur_input Couleur de la piece
     */
    public Piece(boolean [][] matrice,int posAbsolueX_,int posAbsolueY_,int id_input, Color couleur_input){
        this(matrice,posAbsolueX_,posAbsolueY_,id_input);
        this.couleur=couleur_input;
    }

    //endregion




    /**
     * simule un pivotage anti-horaire de 90 degrés sans modifier la piece
     * (le plateau doit faire des tests avant de confirmer le pivotage)
     * @param sensHoraire si vrai alors pivot dans le sens horaire.
     * @param enPositiosAbsolues si vrai renvoit les cases en positions absolues.
     *                           Si non les renvoit en position relatives au pivot de la piece
     * @return les cases qui forment la version pivottée de la piece, en position relatives
     */
    public ArrayList<Case> simulationPivotement(boolean sensHoraire,boolean enPositiosAbsolues){
        ArrayList<Case> resultat=new ArrayList<Case>(forme.size());
        for (Case c:
             forme) {
            Case casemodifie=new Case(c);
            casemodifie.pivoterCase(sensHoraire);
            if(enPositiosAbsolues)casemodifie.add(posAbsolueX,posAbsolueY);
            resultat.add(casemodifie);
        }
        return resultat;
    }

    public void pivoter(boolean sensHoraire){
        for (Case c:
                forme) {
            c.pivoterCase(sensHoraire);
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

    /**
     * supprime la case de la piece a la position indiquée
     * @param posX position en X relativement a la grille du plateau
     * @param posY position en Y relativement a la grille du plateau
     */
    public void supprimerCase(int posX, int posY) {
        for(Case c:
                forme){
            if(posAbsolueX+c.getX()==posX && posAbsolueY+c.getY()==posY) forme.remove(c);
        }
    }

    /**
     * décale la case a la position donnée dans la direction donnée
     * @param posX position en X relativement a la grille du plateau
     * @param posY position en Y relativement a la grille du plateau
     * @param directionX direction en X : devrait etre -1,0 ou 1
     * @param directionY direction en Y : devrait etre -1,0 ou 1
     */
    public void decalerCase(int posX,int posY,short directionX,short directionY){
        for(Case c:
                forme){
            if(posAbsolueX+c.getX()==posX && posAbsolueY+c.getY()==posY) c.decaler(directionX,directionY);
        }
    }

}
