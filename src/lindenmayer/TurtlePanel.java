package lindenmayer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.shape.Line;
import javax.swing.JPanel;

public class TurtlePanel extends JPanel implements Observer{ // deprecated?: JPanel
    
    private TurtleModel turtle;
    private Point2D position;
    private int buffer = 50;
    private ArrayList<Line> lines = new ArrayList<>();

    public TurtlePanel(TurtleModel turtle) {
        this.turtle = turtle;
        this.position = turtle.getPosition();
        turtle.addObserver(this);
        setSize(300,300); // to change
        setVisible(true);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        for(final Line line: lines){
            g.drawLine((int) line.getStartX(), (int) line.getStartY(), 
                    (int) line.getEndX(), (int) line.getEndY());
        }
    }
    
    @Override
    public void update(Observable o, Object o1) {
        addLines(turtle.getPosition());
//        repaint();
    }

    private void addLines(Point2D position) {
        int x = (int) (position.getX() + turtle.getStep() * Math.cos(Math.toRadians(turtle.getAngle())));
        int y = (int) (position.getY() + turtle.getStep() * Math.sin(Math.toRadians(turtle.getAngle())));

        Line line = new Line((int) position.getX(),(int) (GUI.HEIGHT - position.getY() - buffer) , x, (int)(GUI.HEIGHT - y - buffer));
        this.lines.add(line);
        
    }

  
    
    
   
}
