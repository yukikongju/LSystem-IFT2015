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
//         LSystem lsystem = new LSystem(rounds);
        JSONFile jsonFile = new JSONFile(file);
        LSystem lsystem = jsonFile.getLSystem();
        TurtleUI turtleUI = jsonFile.getTurtleUI();
        TurtlePS turtlePS = jsonFile.getTurtlePS();
         
//        MainPS mainPS = new MainPS(file, rounds);
        MainPS mainPS = new MainPS(lsystem, turtlePS, rounds);
        mainPS.printPostScript();
        
        // https://stackoverflow.com/questions/1676187/why-is-paint-paintcomponent-never-called
        // We need Swing Utilities to make sure paint() doesn't run before the constructor
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                GUI gui;
//                try {
//                    gui = new GUI(file, rounds);
//                    gui.setVisible(true);
//                } catch (IOException ex) {
//                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
        
    }
    
}
