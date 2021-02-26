package lindenmayer;

import java.awt.Color;
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

         int rounds = 3;
         String file = "test/buisson.json";

         // Read JSON file
        JSONFile jsonFile = new JSONFile(file);
        LSystem lsystem = jsonFile.getLSystem();
        TurtleUI turtleUI = jsonFile.getTurtleUI();
        TurtlePS turtlePS = jsonFile.getTurtlePS();
         
        // Print postscript 
        MainPS mainPS = new MainPS(lsystem, turtlePS, rounds);
        mainPS.printPostScript();
        
        // https://stackoverflow.com/questions/1676187/why-is-paint-paintcomponent-never-called
        // We need Swing Utilities to make sure paint() doesn't run before the constructor
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
//                new GUI(lsystem, turtleUI, rounds).setVisible(true);
                createAndShowGUI(lsystem, turtleUI, rounds);
            }

            private void createAndShowGUI(LSystem lsystem, TurtleUI turtleUI, int rounds) {
                JFrame frame = new JFrame("LSystem");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
                frame.add(new GUI(lsystem, turtleUI, rounds));
                frame.pack();
                frame.setSize(600,800);
                frame.setVisible(true);
             }
        });
        
    }
    
}
