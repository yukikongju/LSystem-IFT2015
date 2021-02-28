package lindenmayer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

public class TurtlePanel extends JPanel implements Observer{ // deprecated?: JPanel
    
    private TurtleModel turtle;
    private int buffer = 50;
    private ArrayList<Line> lines = new ArrayList<>();

    public TurtlePanel(TurtleModel turtle) {
        this.turtle = turtle;
        turtle.addObserver(this);
        setSize(GUI.WIDTH,GUI.HEIGHT); // the size should be changed after the repaint()
        setVisible(true);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        
        // set alpha
        float alpha = 0.5f;
        Color color = new Color(0, 0, 0, alpha);
        g.setColor(color);
        
        for(final Line line: lines){
            g.drawLine((int) line.getStartX(), (int) line.getStartY(), 
                    (int) line.getEndX(), (int) line.getEndY());
        }
    }
    
    @Override
    public void update(Observable o, Object o1) {
        addLines(turtle.getPosition()); // comment this line if you don't want to draw
    }

    private void addLines(Point2D position) {
        // calculate next step to form the line
        int x = (int) (position.getX() + turtle.getStep() * Math.cos(Math.toRadians(turtle.getAngle())));
        int y = (int) (position.getY() + turtle.getStep() * Math.sin(Math.toRadians(turtle.getAngle())));

        Line line = new Line((int) position.getX(),(int) (GUI.HEIGHT - position.getY() - buffer) , x, (int)(GUI.HEIGHT - y - buffer));
        this.lines.add(line);
        
    }
    
    public class Line{
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Line(int startX, int startY, int endX, int endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }

        public int getStartX() {
            return startX;
        }

        public int getStartY() {
            return startY;
        }

        public int getEndX() {
            return endX;
        }

        public int getEndY() {
            return endY;
        }
        
    }

}
