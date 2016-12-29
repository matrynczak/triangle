package Triangle;

import java.awt.*;
import java.util.Random;

public class Triangle {

    protected int x;
    protected int y;
    protected int length;
    protected int height;
    private Color kolor;
    int dx;
    int dy;

    public Triangle(Dimension d){
        Random gen = new Random();
        x = d.width - gen.nextInt(d.width);
        y = d.height -gen.nextInt(d.height);
        length = 10 + gen.nextInt(100);
        height = 10 + gen.nextInt(100);
        kolor = new Color(gen.nextFloat(), gen.nextFloat(), gen.nextFloat());
        dx = -20 + gen.nextInt(20);
        dy = -20 + gen.nextInt(20);
    }

    public void paint(Graphics g){
        g.fillRoundRect(x, y, length, height, 4, 4);
        g.setColor(kolor);
    }

    public void moving(Dimension d) {
        x += dx;
        y += dy;

        if (x < 0 || x + length >= d.width)  dx = -dx;
        if (y < 0 || y + height >= d.height) dy = -dy;
    }

    public void rotate(Dimension d) {
        Random ran = new Random();
        x = ran.nextInt(d.width);
        y = ran.nextInt(d.height);
   }

    public void bigger(){
        length = length+15;
        height = height+15;
    }

    public void smaller(){
        length = length-15;
        height = height-15;
    }


    public boolean include(int x2, int y2) {
        double dist = (x2 - x)*(x2 -x) + (y2 - y)*(y2 - y);
        return Math.sqrt(dist) <= length;
    }

}
