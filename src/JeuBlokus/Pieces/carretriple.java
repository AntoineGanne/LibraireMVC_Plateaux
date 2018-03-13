package JeuBlokus.Pieces;

import JeuBlokus.BuilderPiece;

public class carretriple extends BuilderPiece{
    public carretriple() {
        boolean[][] b = {{true,true,true}};

        this.nom="carretriple";
        this.forme=b;
    }
}
