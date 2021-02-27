package lindenmayer;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.swing.SwingUtilities;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

//        int rounds = Integer.parseInt(args[1]);
//        String file = args[0];

         int rounds = 4;
         String file = "test/buisson.json";

         // Read JSON file
        JSONFile jsonFile = new JSONFile(file);
        LSystem lsystem = jsonFile.getLSystem();
        TurtleModel turtleModel = jsonFile.getTurtleModel();
        
        // https://stackoverflow.com/questions/1676187/why-is-paint-paintcomponent-never-called
        // We need Swing Utilities to make sure paint() doesn't run before the constructor
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI gui = new GUI(turtleModel);
                printPostScriptHeader();
                Rectangle2D rectangle2D = lsystem.tell(turtleModel, lsystem.getAxiom(), rounds);
                printPostScriptFooter(rectangle2D);
            }

            private void printPostScriptHeader(){
                System.out.println("%!PS-Adobe-3.0 EPSF-3.0");
                System.out.println("%%Title: L-system");
                System.out.println("%%Creator: "+ getClass().getName());
                System.out.println("%%BoundingBox: (atend)"); 
                System.out.println("%%EndComments");
                System.out.println("/M {moveto} bind def"); 
                System.out.println("/L {lineto} bind def"); 
                System.out.println("0.5 setlinewidth"); 
                System.out.println(turtleModel.getPosition().getX() + " " + 
                        turtleModel.getPosition().getY() + " " + " newpath  move to ");
    }
    
            private void printPostScriptFooter(Rectangle2D rectangle){
                System.out.println("stroke");
                System.out.println("%%Trailer");
                System.out.println("%%BoundingBox:" + 
                        (int) rectangle.getX() + " " + (int) rectangle.getY() + " " +
                        (int) (rectangle.getX() + rectangle.getWidth()) + " " +
                        (int) rectangle.getHeight()); 
                System.out.println("%%EOF");
            }

        });
        
    }
    
    
    
}
