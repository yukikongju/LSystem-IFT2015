package lindenmayer;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import static lindenmayer.GUI.HEIGHT;

public class TurtleUI extends TurtleModel{ // deprecated?: JPanel
    
    Graphics2D graphics;
//    private int buffer = 10;

    public void setGraphics(Graphics2D graphics) {
        this.graphics = graphics;
    }

    @Override
    public void draw() {
        // TODO: change position with rectangle (utiliser transformation linéaire)
        // Ici, on ne recalcule pas la position, on obtient les positions du prochain states.
        
        super.draw();
        Point2D position = getPosition();
        int x = (int) (position.getX() + getStep() * Math.cos(Math.toRadians(getAngle())));
        int y = (int) (position.getY() + getStep() * Math.sin(Math.toRadians(getAngle())));

        
        
        graphics.drawLine((int) getPosition().getX(), (int) (HEIGHT - getPosition().getY()), x, HEIGHT - y);
//        graphics.drawLine((int) getPosition().getX(), (int) (HEIGHT - getPosition().getY()), 
//                (int) getPosition().getX(), (int) (HEIGHT - getPosition().getY()));

    }

//    @Override
//    public void turnL() { // mirror the image
//        super.turnR(); 
//    }
//
//    @Override
//    public void turnR() { // mirror the image
//        super.turnL(); 
//    }

  
    
    
   
}
