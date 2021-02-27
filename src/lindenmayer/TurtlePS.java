package lindenmayer;

import java.awt.geom.Point2D;

public class TurtlePS  { // implements Observable? // extends TurtleModel?
    
    private final static String FORMAT = "%.1f %.1f";
    
    public void draw(Point2D position) {
        printPosition(position);
        System.out.println(" L ");
    }

    public void pop(Point2D position) {
        System.out.println("stroke");
        printPosition(position);
        System.out.println(" newpath M ");
    }

    public void push(Point2D position) {
        System.out.println("stroke");
        printPosition(position);
        System.out.println(" newpath M ");
    }

    public void move(Point2D position) {
        printPosition(position);
        System.out.println(" M ");
    }
  
    protected void printPosition(Point2D position){
        System.out.printf(FORMAT, position.getX(),
                position.getY(), " ");
    }
    
}
