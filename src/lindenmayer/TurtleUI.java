package lindenmayer;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class TurtleUI extends TurtleModel{
    
    Graphics2D graphics;

    public void setGraphics(Graphics2D graphics) {
        this.graphics = graphics;
    }

    @Override
    public void draw() {
        // TODO: change position with rectangle (utiliser transformation linéaire)
        // ne pas calculer 2 fois
        
        super.draw();
        Point2D position = getPosition();
        int x = (int) (position.getX() + getStep() * Math.cos(Math.toRadians(getAngle()) ));
        int y = (int) (position.getY() + getStep() * Math.sin(Math.toRadians(getAngle()) ));
        
        graphics.drawLine((int) position.getX(), (int) position.getY(), x, y);
    }

  
    
    
   
}
