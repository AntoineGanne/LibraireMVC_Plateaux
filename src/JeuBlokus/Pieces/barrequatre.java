package JeuBlokus.Pieces;

import JeuBlokus.BuilderPiece;

public class barrequatre extends BuilderPiece{
    public barrequatre() {
        boolean[][] b = {{true,true,true,true}};

        this.nom="barrequatre";
        this.forme=b;
    }
}
