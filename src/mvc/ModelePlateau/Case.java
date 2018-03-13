package mvc.ModelePlateau;


public class Case {
    private int x;



    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    public Case(int x_,int y_){
        x=x_;
        y=y_;
    }
    Case(Case caseCopie){
        this.x=caseCopie.getX();
        this.y=caseCopie.getY();
    }

    /**
     * rotation anti-horaire de 90 degrés
     */
    public void pivoterCase(){
            int tempX=this.x;
            this.x=this.y;
            this.y=-tempX;
    }

}
