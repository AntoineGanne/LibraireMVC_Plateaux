package JeuBlokus.Pieces;

import JeuBlokus.BuilderPiece;

public class carrequatre extends BuilderPiece{
    public carrequatre() {
        boolean[][] b = {   {true,true},
                            {true,true}};

        this.nom="carrequatre";
        this.forme=b;
    }
}
