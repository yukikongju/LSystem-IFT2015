/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lindenmayer;

import java.awt.geom.Rectangle2D;
import java.io.IOException;

/**
 *
 * @author emuli
 */
public class MainPS {

    private LSystem lsystem;
    private TurtleModel turtle;
    private JSONFile JSONFile;
    
    public MainPS(String file, int rounds) throws IOException{
        turtle = new TurtleModel();
        lsystem = new LSystem(rounds);
        JSONFile = new JSONFile();
        JSONFile.readJSONFile(file, turtle, lsystem);
        printPSHeader();
        Rectangle2D rectangle = lsystem.tell(turtle, lsystem.getAxiom(), rounds);
        printPSFooter(rectangle);
    }
    
    private void printPSHeader(){
        System.out.println("%!PS-Adobe-3.0 EPSF-3.0");
        System.out.println("%%Title: L-system");
        System.out.println("%%Creator: "+getClass().getName());
        System.out.println("%%BoundingBox: (atend)"); 
        System.out.println("%%EndComments");
        System.out.println("/M {moveto} bind def"); 
        System.out.println("/L {lineto} bind def"); 
        System.out.println("0.5 setlinewidth"); 
        System.out.println(turtle.getPosition().getX() + " " + 
                turtle.getPosition().getY() + " " + " newpath  move to ");
    }
    
    private void printPSFooter(Rectangle2D rectangle){
        System.out.println("stroke");
	System.out.println("%%Trailer");
        System.out.println("%%BoundingBox:" + 
                (int) rectangle.getX() + " " + (int) rectangle.getY() + " " +
                (int) (rectangle.getX() + rectangle.getWidth()) + " " +
                (int) rectangle.getHeight()); // TODO add bounding box
        System.out.println("%%EOF");
    }
    
    
}