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

public class TurtleUI extends JPanel implements Observer{ // deprecated?: JPanel
    
    private TurtleModel turtle;
    private Point2D position;

    public TurtleUI(TurtleModel turtle) {
        this.turtle = turtle;
        this.position = turtle.getPosition();
        turtle.addObserver(this);
        setSize(300,300); // to change
        setVisible(true);
    }
    
    public void draw(Point2D point){
        Graphics2D g = (Graphics2D) getGraphics();
        
        int x = (int) (position.getX() + turtle.getStep() * Math.cos(Math.toRadians(turtle.getAngle())));
        int y = (int) (position.getY() + turtle.getStep() * Math.sin(Math.toRadians(turtle.getAngle())));

//        g.drawLine((int) position.getX(),(int) (GUI.HEIGHT - position.getY()), x, (GUI.HEIGHT - y));
//        g.drawLine((int) position.getX(),(int) (HEIGHT - position.getY()), x, (HEIGHT - y));
        g.drawLine((int) position.getX(),(int) (position.getY()), x, (y));

        
//        System.out.println(turtle.getPosition());
        
    }
    
    @Override
    public void update(Observable o, Object o1) {
        position = turtle.getPosition();
//        this.repaint();
            draw(position);
    }

  
    
    
   
}
