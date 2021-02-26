package lindenmayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JPanel { 
    final static int HEIGHT = 600;
    final static int WIDTH = 800;
    
    private LSystem lsystem;
    private int rounds;
    private TurtleUI turtleUI;
    
    public GUI(LSystem lsystem, TurtleUI turtle, int rounds){
        this.turtleUI = turtle;
        this.lsystem = lsystem;
        this.rounds = rounds;
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        turtleUI.setGraphics((Graphics2D) g);

        Rectangle2D rectangle2D = lsystem.tell(turtleUI, lsystem.getAxiom(), rounds);
//        g.drawRect((int) rectangle2D.getX(), (int) rectangle2D.getY(), (int) (rectangle2D.getX() + rectangle2D.getMaxX()),
//                (int) (rectangle2D.getY() + rectangle2D.getMaxY()));
//        g.drawRect((int) rectangle2D.getX(), (int) rectangle2D.getY(), (int) (rectangle2D.getMaxX()),
//                (int) (rectangle2D.getMaxY()));
        
//        Dimension dimension = new Dimension((int) rectangle2D.getMaxY(), (int) rectangle2D.getMaxY());
//        setPreferredSize(dimension);
//        rectangle2D.getMaxX();

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
//        System.out.println(rectangle2D.getMaxX() + " " + rectangle2D.getMaxY());

//        System.out.println((int) rectangle2D.getWidth()+" " + (int) rectangle2D.getHeight());
//        setSize((int) rectangle2D.getWidth(), (int) rectangle2D.getHeight());
//        turtleUI.draw();
//        Dimension dimension = new Dimension((int) rectangle2D.getWidth(), 
//                (int) rectangle2D.getHeight());
//        frame.setPreferredSize(dimension);
    }
 
//    public Dimension getPreferresSize(){
//        return new Dimension(600, 800);
//    }
    
}

 
