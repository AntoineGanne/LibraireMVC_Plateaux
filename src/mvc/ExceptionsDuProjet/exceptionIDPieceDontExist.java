package mvc.ExceptionsDuProjet;

public class exceptionIDPieceDontExist extends Exception{
    public exceptionIDPieceDontExist(){
        System.out.println("l'ID de la piece n'existe pas dans le plateau");
    }
}
