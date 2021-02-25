package lindenmayer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JPanel { 
    final static int HEIGHT = 600;
    final static int WIDTH = 800;
    
    private LSystem lsystem;
    private int rounds;
    private TurtleUI turtleUI;
    private JFrame frame;
    
    public GUI(LSystem lsystem, TurtleUI turtle, int rounds){
        this.turtleUI = turtle;
        this.lsystem = lsystem;
        this.rounds = rounds;
        frame = new JFrame();
        frame.setTitle("LSystem");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        turtleUI.setGraphics((Graphics2D) g);

        Rectangle2D rectangle2D = lsystem.tell(turtleUI, lsystem.getAxiom(), rounds);

//        System.out.println((int) rectangle2D.getWidth()+" " + (int) rectangle2D.getHeight());
//        this.setSize((int) rectangle2D.getWidth(), (int) rectangle2D.getHeight());
        turtleUI.draw();
//        Dimension dimension = new Dimension((int) rectangle2D.getWidth(), 
//                (int) rectangle2D.getHeight());
//        frame.setPreferredSize(dimension);
    }
    
}

 
