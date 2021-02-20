package lindenmayer;

import java.io.IOException;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // 1. Read JSON file
        String file = "test/buisson.json";
        String file2 = "test/hexamaze.json";
        String file3 = "test/herbe.json";
        
        // 2. Read num iterations
        int numIter = 3;
        
        // 3. Initialize LSystem
        LSystem system = new LSystem(file3, numIter);
    }
    
}
