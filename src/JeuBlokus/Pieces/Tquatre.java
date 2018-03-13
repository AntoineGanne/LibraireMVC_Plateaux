package JeuBlokus.Pieces;

import JeuBlokus.BuilderPiece;

public class Tquatre extends BuilderPiece {
    public Tquatre() {
        boolean[][] b = {   {false,true,false},
                            {true,true,true}};

        this.nom="Tquatre";
        this.forme=b;
    }
}
