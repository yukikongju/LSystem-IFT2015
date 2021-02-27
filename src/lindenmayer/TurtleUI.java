package lindenmayer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import static lindenmayer.GUI.HEIGHT;

public class TurtleUI extends JPanel implements Observer{ // deprecated?: JPanel
    
    private TurtleModel turtle;
    private Point2D position;

    public TurtleUI(TurtleModel turtle) {
        this.turtle = turtle;
        this.position = turtle.getPosition();
        turtle.addObserver(this);
        setSize(200,200); // to change
        setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        g.drawLine(1, 1, 50, 50);
        
        int x = (int) (position.getX() + turtle.getStep() * Math.cos(Math.toRadians(turtle.getAngle())));
        int y = (int) (position.getY() + turtle.getStep() * Math.sin(Math.toRadians(turtle.getAngle())));

        g.drawLine((int) position.getX(),(int) position.getY(), x, y);
        
        System.out.println(turtle.getPosition());
 
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(TurtleUI.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }
    
    @Override
    public void update(Observable o, Object o1) {
        position = turtle.getPosition();
        this.repaint();
    }

  
    
    
   
}
