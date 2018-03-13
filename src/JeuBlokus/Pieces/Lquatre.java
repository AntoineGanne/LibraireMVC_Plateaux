package JeuBlokus.Pieces;

import JeuBlokus.BuilderPiece;

public class Lquatre extends BuilderPiece {
    public Lquatre() {
        boolean[][] b = {   {false,false,true},
                            {true,true,true}};

        this.nom="Lquatre";
        this.forme=b;
    }
}
