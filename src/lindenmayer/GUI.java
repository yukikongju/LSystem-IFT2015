package lindenmayer;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import lindenmayer.Symbol.Seq;

public class GUI extends JFrame implements Observer {
    
    final static int HEIGHT = 600;
    final static int WIDTH = 800;
    
    private JPanel mainPanel, generationPanel, turtlePanel;
    private JLabel axiomLabel, roundsLabel;
    private JButton iterButton;
    private LSystem lsystem;
    private AbstractTurtle turtle;
    private int rounds;
    private String file;
    private JSONFile JSONFile;
    private Seq axiom;
    private TurtleUI turtleUI;
    
    public void paintComponents(Graphics2D g) throws IOException, InterruptedException{
        super.paintComponents(g);
        turtleUI = new TurtleUI(g);
        //        JSONFile.readJSONFile(file, turtleUI, lsystem);
        JSONFile.readJSONFile(file, turtleUI, lsystem);
        Rectangle2D rectangle2D = lsystem.tell(turtle, lsystem.getAxiom(), rounds);
        Thread.sleep(5000);
    }
    
    public GUI(String file, int rounds){
        this.file = file;
        this.rounds= rounds;
        setTitle("LSystem");
        setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    // deprecated
    public GUI(String file) throws IOException{
        // initialize obeserver and controler
        turtle = new TurtleUI();
        rounds = 0;
        lsystem = new LSystem(rounds); // to setup later
        JSONFile = new JSONFile();
        JSONFile.readJSONFile(file, turtle, lsystem);
        axiom = lsystem.getAxiom();
        
//        lsystem = new LSystem(file, rounds, turtle);
        turtle.addObserver(this);
        
        // init Windows
        setTitle("LSystem");
        setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // init panel
        mainPanel = new JPanel(new BorderLayout());
        generationPanel = new JPanel();
        turtlePanel = new JPanel();
        
        // init generation panel (show the sentence)
        axiomLabel = new JLabel("Axiom: " + axiom.toString());
        roundsLabel = new JLabel("Rounds: " + rounds);
        generationPanel.add(axiomLabel);
        generationPanel.add(roundsLabel);
        
        // init button
        iterButton = new JButton("Iterate");
        iterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // TODO: add incrementation with button
                rounds++;
                roundsLabel.setText("Rounds: " + rounds);
                axiom = lsystem.applyRule(axiom);
//                axiomLabel.setText("<html>" +"Axiom: " + axiom.toString() + "</html>");
                
                System.out.println(rounds);
                System.out.println(axiom.toString());
            }
        });
        
        // add panel to main frame
        add(turtlePanel, BorderLayout.CENTER);
        add(iterButton, BorderLayout.SOUTH);
        add(generationPanel, BorderLayout.NORTH);
       
        setVisible(true);

    }

    @Override
    public void update(Observable o, Object o1) {
        // TODO: update ui state every iteration + redraw screen
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

 
