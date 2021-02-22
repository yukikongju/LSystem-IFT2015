package lindenmayer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class GUI extends JFrame {
    final static int HEIGHT = 600;
    final static int WIDTH = 800;
    
    private LSystem lsystem;
    private int rounds;
    private String file;
    private JSONFile JSONFile;
    private TurtleUI turtleUI;
    
    public GUI(String file, int rounds) throws IOException{
        this.file = file;
        this.rounds= rounds;
        lsystem = new LSystem(rounds);
        JSONFile = new JSONFile();
        setTitle("LSystem");
        setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        turtleUI = new TurtleUI((Graphics2D) g);
        lsystem = new LSystem(rounds);
        try {
            JSONFile.readJSONFile(file, turtleUI, lsystem);
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        Rectangle2D rectangle2D = lsystem.tell(turtleUI, lsystem.getAxiom(), rounds);
        
//        Graphics2D g2d = (Graphics2D) g.create();
//        g2d.scale(2.5, 2.5);
        
//        System.out.println(rectangle2D.getWidth());
//        System.out.println(rectangle2D.getHeight());
//        setSize((int) rectangle2D.getWidth()*2, (int) rectangle2D.getHeight()*2);
        

        turtleUI.draw();
    }
    
}

 
