package JeuDeTestTetris;

import mvc.Modele;

public class ModeleTetris {
    public Modele getM() {
        return m;
    }

    private Modele m;

    public  ModeleTetris(int nbColonnes,int nbLignes){
        m=new Modele(nbColonnes,nbLignes);
    }


}
