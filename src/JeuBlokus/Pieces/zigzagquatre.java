package JeuBlokus.Pieces;

import JeuBlokus.BuilderPiece;

public class zigzagquatre extends BuilderPiece {
    public zigzagquatre() {
        boolean[][] b = {   {false,true,true},
                            {true,true,false}};

        this.nom="zigzagquatre";
        this.forme=b;
    }
}
