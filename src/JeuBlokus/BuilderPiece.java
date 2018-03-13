package JeuBlokus;

public class BuilderPiece {
    protected String nom;
    protected boolean[][] forme;

    public BuilderPiece(){
        this.nom="";
        this.forme=new boolean[0][0];
    }
    public BuilderPiece(String nom,boolean[][] forme){
        this.nom=nom;
        this.forme=forme;
    }
}
