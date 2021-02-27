package lindenmayer;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

//        int rounds = Integer.parseInt(args[1]);
//        String file = args[0];

         int rounds = 4;
         String file = "test/herbe.json";

         // Read JSON file
        JSONFile jsonFile = new JSONFile(file);
        LSystem lsystem = jsonFile.getLSystem();
        TurtlePS turtlePS = jsonFile.getTurtlePS(); 
        TurtleModel turtleModel = jsonFile.getTurtleModel();
        
        // Print postscript 
        MainPS mainPS = new MainPS(lsystem, turtlePS, rounds); // to change
        mainPS.printPostScript();
        
        // https://stackoverflow.com/questions/1676187/why-is-paint-paintcomponent-never-called
        // We need Swing Utilities to make sure paint() doesn't run before the constructor
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI(lsystem, rounds);
            }

            private void createAndShowGUI(LSystem lsystem, int rounds) {
                JFrame frame = new JFrame("LSystem");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                GUI gui = new GUI(turtleModel);
                turtleModel.addObserver(gui);
                frame.add(gui);
                frame.pack(); // might be an error
                frame.setSize(600,600);
                frame.setVisible(true);
                Rectangle2D rectangle2D = lsystem.tell(turtleModel, lsystem.getAxiom(), rounds);

             }
        });
        
    }
    
}
