package lindenmayer;

import java.awt.geom.Point2D;
import java.util.Stack;

/**
 *
 * @author emuli
 */
public class TurtleModel implements Turtle {
    
    private float x,y;
    private double theta, angle;
    private int step;
    private Stack<State> stack;
    private State currentState;
    
    /** TODO: Constructor **/
    public TurtleModel(float x, float y, double theta, double angle, int step) {
        this.x = x;
        this.y = y;
        this.theta = theta;
        this.angle = angle;
        this.step = step;
        this.stack = new Stack<>();
        // TODO: check if currentState is necessary + if we need to push first state
        State firstState = new State(this.x, this.y, this.theta);
        stack.push(firstState);
        this.currentState = firstState;
    }
    
    public class State{
        private float x,y;
        private double angle;

        public State(float x, float y, double angle) {
            this.x = x;
            this.y = y;
            this.angle = angle;
            // TODO: check the degrees to radians conversion
        }
        
    }

    @Override
    public void draw() {
        x += step * Math.cos(theta);
        y += step * Math.sin(theta);
        // TODO: Tracer la ligne
    }

    @Override
    public void move() {
        x += step * Math.cos(theta);
        y += step * Math.sin(theta);
    }

    @Override
    public void turnR() {
        theta -= angle;
    }

    @Override
    public void turnL() {
        theta += angle;
    }

    @Override
    public void push() {
        State temp = new State(x,y,theta);
        stack.push(temp);
    }

    @Override
    public void pop() {
        if(!stack.isEmpty()){
            State removedState = stack.pop();
            currentState = stack.peek();
        } else {
            // TODO: implement error message
            System.out.println("An error has occured.");
        }
    }

    @Override
    public void stay() {
        // do nothing
    }

    @Override
    public void init(Point2D pos, double angle_deg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Point2D getPosition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getAngle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUnits(double step, double delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
