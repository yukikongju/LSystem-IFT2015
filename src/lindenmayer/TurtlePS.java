package lindenmayer;

public class TurtlePS extends TurtleModel{
    
    @Override
    public void draw() {
        super.draw(); 
        printPosition();
        System.out.println(" L ");
    }

    @Override
    public void pop() {
        super.pop(); 
        System.out.println("stroke");
        printPosition();
        System.out.println(" newpath M ");
    }

    @Override
    public void push() {
        super.push(); 
        System.out.println("stroke");
        printPosition();
        System.out.println(" newpath M ");
    }

    @Override
    public void move() {
        super.move(); 
        printPosition();
        System.out.println(" M ");
    }
    
    
    
    
    
    
    
}
