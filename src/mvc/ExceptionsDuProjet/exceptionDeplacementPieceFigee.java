package mvc.ExceptionsDuProjet;

public class exceptionDeplacementPieceFigee extends Exception {
    public exceptionDeplacementPieceFigee(){
        super("Déplacement impossible car piece figée");
    }
}
