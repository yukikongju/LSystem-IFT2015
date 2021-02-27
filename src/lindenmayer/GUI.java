package lindenmayer;

import java.awt.Graphics;
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
    
    TurtleUI turtlePanel;
    
     public GUI(TurtleModel turtle){
        this.turtle = turtle;
        turtlePanel = new TurtleUI(turtle);
        this.getContentPane().add(turtlePanel);
        this.setLocationRelativeTo(null);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        turtle.addObserver(this); // make gui observe any turtle changes
//        add(this);
        pack(); // might be an error
        setSize(WIDTH,HEIGHT);
        setVisible(true);
        
    }
    
    @Override
    public void update(Observable o, Object o1) {        // update turtle and position
//        repaint(); // Problem: repaint doesn't call paintComponent bc repaint should be called in JFrame, not JPanel
//        turtlePanel.repaint();
    }
 
}
