package Triangle;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.applet.*;

public class AppletGotowy extends Applet {
//    private Triangle[] trojkaty;
    ArrayList<Triangle> trojkaty = new ArrayList();
    private Mouse mouse;
    Random rnd = new Random();
    int N = rnd.nextInt(30) + 10;
    
    public void disappear(){
    	Iterator<Triangle> it = trojkaty.iterator();
    	while(it.hasNext()){
    		Triangle triangle = it.next();
    		if((triangle.x >= mouse.x && triangle.x  <= mouse.x + 45) && 
    				(triangle.y <= mouse.y && triangle.y >= mouse.y - 45))
    			it.remove();
    	}
    }
    
    public void gameOver(Graphics g){
    	Iterator<Triangle> it = trojkaty.iterator();
    	if(!it.hasNext()) 
    		g.drawString("KONIEC GRY!!!", 220, 200);
    }
    
    long startTime = System.currentTimeMillis();
    
    public void timer(Graphics g){
	Iterator<Triangle> it = trojkaty.iterator();
	if(!it.hasNext()) {
		int elapsedSeconds = (int)(System.currentTimeMillis() - startTime)/1000;
		g.drawString("TWÓJ WYNIK: " + String.valueOf(elapsedSeconds) + " SEKUND", 180, 300);
		}
    }
    
    @Override
    public void init() {
        Dimension d = getSize();
        mouse = new Mouse(d);
//        trojkaty = new Triangle(N);
        setSize(600,600);
        setBackground(Color.white);
        for (int i=0; i<N; i++)
//            trojkaty[i] = new Triangle(d);
        	trojkaty.add(new Triangle(d));

        addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1)
                    for (Triangle triangle : trojkaty)
                        if(triangle.include(e.getX(), e.getY()))
                            triangle.bigger();
                if(e.getButton() == MouseEvent.BUTTON3)
                    for (Triangle triangle : trojkaty)
                        if(triangle.include(e.getX(), e.getY()))
                            triangle.smaller();
                repaint();
            }
           });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                Dimension d = getSize();
                if(e.getKeyCode() == KeyEvent.VK_SPACE)
                    for(Triangle triangle : trojkaty)
                        triangle.rotate(d);
                repaint();

                if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                    mouse.moveRight();
                if(e.getKeyCode() == KeyEvent.VK_LEFT)
                    mouse.moveLeft();
                if(e.getKeyCode() == KeyEvent.VK_UP)
                    mouse.moveUp();
                if(e.getKeyCode() == KeyEvent.VK_DOWN)
                    mouse.moveDown();
            }
        });

        class Animation extends TimerTask{

        		@Override
        		public void run() {
                		Dimension d = getSize();
                		for (Triangle triangle : trojkaty)
                			triangle.moving(d);
                		disappear();
                        repaint();		
        		}      		
        }
        
        Timer go = new Timer();
    	go.schedule(new Animation(), 500, 70);
           }

    @Override
    public void paint(Graphics g) {
        for (Triangle triangle : trojkaty)
            triangle.paint(g);
        mouse.paint(g);
        gameOver(g);
        timer(g);
    }
}