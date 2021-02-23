package lindenmayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUI extends JLabel {
    final static int HEIGHT = 600;
    final static int WIDTH = 800;
    
    private LSystem lsystem;
    private int rounds;
    private String file;
    private JSONFile JSONFile;
    private TurtleUI turtleUI;
    private JFrame frame;
    
    public GUI(String file, int rounds) throws IOException{
        this.file = file;
        this.rounds = rounds;
        lsystem = new LSystem(rounds);
        JSONFile = new JSONFile();
//        this.setBackground(Color.RED);
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
//        g.set(Color.GREEN);
        turtleUI = new TurtleUI((Graphics2D) g);
        lsystem = new LSystem(rounds);
        try {
            JSONFile.readJSONFile(file, turtleUI, lsystem);
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        Rectangle2D rectangle2D = lsystem.tell(turtleUI, lsystem.getAxiom(), rounds);

//        System.out.println((int) rectangle2D.getWidth()+" " + (int) rectangle2D.getHeight());
        this.setSize((int) rectangle2D.getWidth(), (int) rectangle2D.getHeight());
        turtleUI.draw();
//        setFocusable(true);
//        Dimension dimension = new Dimension((int) rectangle2D.getWidth(), 
//                (int) rectangle2D.getHeight());
//        frame.setPreferredSize(dimension);
    }
    
}

 
