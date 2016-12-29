package Triangle;

import java.awt.*;
import java.util.*;

public class Mouse {
    protected int x;
    protected int y;
    private Random rnd = new Random();
    private Color mousekolor = Color.darkGray;

    public Mouse(Dimension d) {
        x = rnd.nextInt(d.width);
        y = rnd.nextInt(d.height);
        }

    public void paint(Graphics g){
        int xxPoints[] = {x, x+15, x+20, x+25, x+45};
        int yyPoints[] = {y, y-25, y-45, y-25, y};
        g.fillPolygon(xxPoints, yyPoints, 5);
        g.setColor(mousekolor);
    }

    public void moveRight(){
        x = x+5;
    }

    public void moveLeft(){
        x = x-5;
    }

    public void moveUp(){
        y = y-5;
    }

    public void moveDown(){
        y = y+5;
    }
}