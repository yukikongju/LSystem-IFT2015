package lindenmayer;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class GUI extends JPanel implements Observer{ 
    final static int HEIGHT = 600;
    final static int WIDTH = 600;
    
    private TurtleModel turtle;
    private Point2D position;
    
     public GUI(TurtleModel turtle){
         this.turtle = turtle;
         this.position = turtle.getPosition();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        int x = (int) (position.getX() + turtle.getStep() * Math.cos(Math.toRadians(turtle.getAngle())));
        int y = (int) (position.getY() + turtle.getStep() * Math.sin(Math.toRadians(turtle.getAngle())));

        g.drawLine((int) position.getX(),(int) position.getY(), x, y);
        
        System.out.println(turtle.getPosition());
        
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    @Override
    public void update(Observable o, Object o1) {        // update turtle and position
        position = turtle.getPosition();
//        System.out.println("update: " + position); // update works
        repaint(); // Problem: repaint doesn't call paintComponent bc repaint should be called in JFrame, not JPanel
    }
 
}
