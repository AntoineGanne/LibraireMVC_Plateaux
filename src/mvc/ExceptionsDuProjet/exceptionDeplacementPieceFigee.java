package mvc.ExceptionsDuProjet;

public class exceptionDeplacementPieceFigee extends Exception {
    public exceptionDeplacementPieceFigee(){
        System.out.println("Déplacement impossible car piece figée");
    }
}
