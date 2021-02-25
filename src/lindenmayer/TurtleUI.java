/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lindenmayer;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 *
 * @author emuli
 */
public class TurtleUI extends AbstractTurtle{
    
    Graphics2D graphics;

    public TurtleUI(Graphics2D graphics) {
        this.graphics = graphics;
    }

    TurtleUI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void draw() {
        Point2D position = getPosition();
        int x = (int) (position.getX() + getStep() * Math.cos(Math.toRadians(getAngle()) ));
        int y = (int) (position.getY() + getStep() * Math.sin(Math.toRadians(getAngle()) ));
        
        graphics.drawLine((int) position.getX(), (int) position.getY(), x, y);
    }

    
   
}
