package lindenmayer;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JLabel axiomLabel;
    private JButton iterButton;
    private LSystem lsystem;
    private TurtleModel turtle;
    private int rounds = 0;
    private JSONFile JSONFile;
    private Seq axiom;
    
    public GUI(String file) throws IOException{
        // initialize obeserver and controler
        turtle = new TurtleModel();
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
        generationPanel.add(axiomLabel);
        
        // init button
        iterButton = new JButton("Iterate");
        iterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // TODO: add incrementation with button
                rounds++;
                axiom = lsystem.applyRule(axiom);
                axiomLabel.setText("<html>" +"Axiom: " + axiom.toString() + "</html>");
//                System.out.println(rounds);
//                System.out.println(axiom.toString());
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

 
