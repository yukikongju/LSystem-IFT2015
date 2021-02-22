package lindenmayer;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class TurtleUI extends TurtleModel{
    
    Graphics2D graphics;

    public TurtleUI(Graphics2D graphics) {
        this.graphics = graphics;
    }

    TurtleUI() {
    }
    
    @Override
    public void draw() {
        super.draw();
        Point2D position = getPosition();
        int x = (int) (position.getX() + getStep() * Math.cos(Math.toRadians(getAngle()) ));
        int y = (int) (position.getY() + getStep() * Math.sin(Math.toRadians(getAngle()) ));
        
        graphics.drawLine((int) position.getX(), (int) position.getY(), x, y);
    }
   
}
