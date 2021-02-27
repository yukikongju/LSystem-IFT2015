package lindenmayer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import static lindenmayer.GUI.HEIGHT;

public class TurtleUI extends JPanel implements Observer{ // deprecated?: JPanel
    
    private TurtleModel turtle;
    private Point2D position;

    public TurtleUI(TurtleModel turtle) {
        this.turtle = turtle;
        this.position = turtle.getPosition();
        turtle.addObserver(this);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g); 
        g.drawLine(1, 1, 50, 50);
    }
    
    
    
    

    @Override
    public void update(Observable o, Object o1) {
        position = turtle.getPosition();
    }

  
    
    
   
}
