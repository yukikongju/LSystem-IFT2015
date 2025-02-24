package lindenmayer;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;

public class GUI extends JFrame implements Observer{ 
    final static int HEIGHT = 600;
    final static int WIDTH = 600;
    
    private TurtleModel turtle;
    private TurtlePanel turtlePanel;
    
     public GUI(TurtleModel turtle){
        this.turtle = turtle;
        turtlePanel = new TurtlePanel(turtle);
        this.getContentPane().add(turtlePanel); 
        
        // Set JFrame in the middle of the screen
//        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//        this.setLocation((int) (dim.width/2) - (this.getSize().width/2), (int)(dim.height/2) - (this.getSize().height/2));
//        this.setLocation(dim.width/5, dim.height/5);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        turtle.addObserver(this); // make gui observe any turtle changes
        pack(); 
        setSize(WIDTH,HEIGHT);
        setVisible(true);
        
    }

//    public TurtlePanel getTurtlePanel() {
//        return turtlePanel;
//    }
    
    @Override
    public void update(Observable o, Object o1) {        // update turtle and position
//        repaint(); // could be removed
    }
 
}
