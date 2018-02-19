package mvc.ModelePlateau;

import com.sun.javafx.geom.Vec2d;

public class Case {
    private int x;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int y;

    Case(int x_,int y_){
        x=x_;
        y=y_;
    }

}
