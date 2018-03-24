package mvc.ModelePlateau;


public class Case {
    private int x;
    private int y;
    // x=0 ; y=0 : case pivot
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
     * rotation anti-horaire de 90 degrés.
     * @param sensHoraire si vrai alors pivot dans le sens horaire.
     */
    public void pivoterCase(boolean sensHoraire){
            if(sensHoraire){
                int tempX=this.x;
                this.x=-this.y;
                this.y=tempX;
            }else{
                int tempX=this.x;
                this.x=this.y;
                this.y=-tempX;
            }
    }

    public void decaler(short directionX,short directionY){
        this.x=this.x+directionX;
        this.y=this.y+directionY;
    }

    public void add(int x_added,int y_added){
        this.x+=x_added;
        this.y+=y_added;
    }



}
