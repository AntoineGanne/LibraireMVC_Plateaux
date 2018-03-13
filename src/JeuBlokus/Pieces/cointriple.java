package JeuBlokus.Pieces;

import JeuBlokus.BuilderPiece;

public class cointriple extends BuilderPiece{
    public cointriple() {
        boolean[][] b = {   {true,true},
                            {false,true}};

        this.nom="cointriple";
        this.forme=b;
    }
}
