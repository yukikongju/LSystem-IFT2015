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
        
        // 1. Read JSON file
        String file = "test/buisson.json";
        String file2 = "test/hexamaze.json";
        String file3 = "test/herbe.json";
        String file4 = "test/sierpinski.json";

        // 2. Read num iterations
//        int rounds = Integer.parseInt(args[0]);

        int rounds = 3;
        
        // 3. Initialize LSystem
        MainPS mainPS = new MainPS(file4, rounds);
        mainPS.printPostScript();
        
//        https://stackoverflow.com/questions/1676187/why-is-paint-paintcomponent-never-called
        // We need Swing Utilities to make sure paint(0 doesn't run before the constructor
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI gui;
                try {
                    gui = new GUI(file, rounds);
                    gui.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }
    
}
