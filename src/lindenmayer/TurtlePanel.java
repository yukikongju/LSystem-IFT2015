package lindenmayer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

public class TurtlePanel extends JPanel implements Observer{ // deprecated?: JPanel
    
    private TurtleModel turtle;
    private Point2D position;
    private int buffer = 25;

    public TurtlePanel(TurtleModel turtle) {
        this.turtle = turtle;
        this.position = turtle.getPosition();
        turtle.addObserver(this);
        setSize(300,300); // to change
        setVisible(true);
    }
    
//    public void draw(Point2D position){
//        Graphics2D g = (Graphics2D) getGraphics(); // get graphics from JPanel
//        
//        // set alpha
//        float alpha = 0.5f;
//        Color color = new Color(0, 0, 0, alpha);
//        g.setPaint(color);
//        
//        int x = (int) (position.getX() + turtle.getStep() * Math.cos(Math.toRadians(turtle.getAngle())));
//        int y = (int) (position.getY() + turtle.getStep() * Math.sin(Math.toRadians(turtle.getAngle())));
//
//        g.drawLine((int) position.getX(),(int) (GUI.HEIGHT - position.getY() - buffer) , x, (int)(GUI.HEIGHT - y - buffer));
//
//    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        // set alpha
//        float alpha = 0.5f;
//        Color color = new Color(0, 0, 0, alpha);
//        g.setPaint(color);
        
        int x = (int) (position.getX() + turtle.getStep() * Math.cos(Math.toRadians(turtle.getAngle())));
        int y = (int) (position.getY() + turtle.getStep() * Math.sin(Math.toRadians(turtle.getAngle())));

        g.drawLine((int) position.getX(),(int) (GUI.HEIGHT - position.getY() - buffer) , x, (int)(GUI.HEIGHT - y - buffer));
    }
    
    
    
    @Override
    public void update(Observable o, Object o1) {
//        Point2D position = turtle.getPosition();
//        this.repaint();
//        draw(position);
        position = turtle.getPosition();
        Graphics2D g = (Graphics2D) getGraphics(); // get graphics from JPanel

        paintComponent(g);
//        revalidate();
    }

  
    
    
   
}
