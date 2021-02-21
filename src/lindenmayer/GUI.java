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
import javax.swing.JPanel;

public class GUI extends JFrame implements Observer {
    
    final static int HEIGHT = 600;
    final static int WIDTH = 800;
    
    private JPanel mainPanel, generationPanel, turtlePanel;
    private JButton iterButton;
    private LSystem lsystem;
    private TurtleModel turtle;
    private int rounds;
    private JSONFile JSONFile;
    
    public GUI(String file) throws IOException{
        // initialize obeserver and controler
        turtle = new TurtleModel();
        lsystem = new LSystem(rounds); // to setup later
        JSONFile = new JSONFile();
        JSONFile.readJSONFile(file, turtle, lsystem);
        
//        lsystem = new LSystem(file, rounds, turtle);
        turtle.addObserver(this);
        
//        this.lsystem = controller;
        rounds = 0;
        
        // init Windows
        setTitle("LSystem");
        setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // init panel
        mainPanel = new JPanel(new BorderLayout());
        generationPanel = new JPanel();
        turtlePanel = new JPanel();
        
        // init button
        iterButton = new JButton("Iterate");
        iterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // TODO: add incrementation with button
                rounds++;
                lsystem.applyRules(lsystem.getAxiom(), rounds);
                System.out.println(rounds);
            }
        });
        
        // add panel to main frame
        add(turtlePanel, BorderLayout.CENTER);
        add(iterButton, BorderLayout.NORTH);
        add(generationPanel, BorderLayout.SOUTH);
       
        setVisible(true);

    }

    @Override
    public void update(Observable o, Object o1) {
        // TODO: update ui state every iteration + redraw screen
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

 
