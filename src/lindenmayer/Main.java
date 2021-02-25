package lindenmayer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                new GUI(lsystem, turtleUI, rounds).setVisible(true);
            }
        });
        
    }
    
}
