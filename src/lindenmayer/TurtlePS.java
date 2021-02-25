/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lindenmayer;

/**
 *
 * @author emuli
 */
public class TurtlePS extends AbstractTurtle{
    
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
