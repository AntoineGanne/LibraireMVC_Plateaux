package mvc.ExceptionsDuProjet;

public class exceptionPieceHorsPlateau extends Exception {
    public exceptionPieceHorsPlateau(){
       super("au moins une case d'une Pièce est hors du plateau");
    }
}
