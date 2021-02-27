package lindenmayer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame implements Observer{ 
    final static int HEIGHT = 600;
    final static int WIDTH = 600;
    
    private TurtleModel turtle;
    
    TurtlePanel turtlePanel;
    
     public GUI(TurtleModel turtle){
        this.turtle = turtle;
        turtlePanel = new TurtlePanel(turtle);
        this.getContentPane().add(turtlePanel);
        
        // Set JFrame in the middle of the screen
//        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        turtle.addObserver(this); // make gui observe any turtle changes
        pack(); // might be an error
        setSize(WIDTH,HEIGHT);
        setVisible(true);
        
    }
    
    @Override
    public void update(Observable o, Object o1) {        // update turtle and position
        repaint(); // unecessary?
    }
 
}
