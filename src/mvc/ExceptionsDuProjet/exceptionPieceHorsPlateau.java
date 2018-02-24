package mvc.ExceptionsDuProjet;

public class exceptionPieceHorsPlateau extends Exception {
    public exceptionPieceHorsPlateau(){
        System.out.println("au moins une case d'une Pièce est hors du plateau");
    }
}
