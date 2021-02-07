package lindenmayer;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private LSystem controller;
    private TurtleModel model;
    
    public GUI(LSystem controller, TurtleModel model){
        // initialize obeserver and controler
        this.model = model;
        model.addObserver(this);
        this.controller = controller;
        
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

 
