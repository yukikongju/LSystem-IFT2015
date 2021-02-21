package lindenmayer;

import java.awt.geom.Point2D;
import java.util.Stack;

/**
 *
 * @author emuli
 */
public class TurtleModel implements Turtle {
    
    private double step, delta;
    private Stack<State> stack;
    private State currentState;
    
    public TurtleModel(double x, double y, double theta, double delta, double step) {
        // TODO: use prof function
        this.delta = delta;
        this.step = step;
        this.stack = new Stack<>();
        currentState = new State(x,y,theta);
    }
    
    public TurtleModel(){
        this.stack = new Stack<>();
        currentState = new State();
    }
    
    public class State{
        private double x,y;
        private double theta;

        public State(double x, double y, double theta) {
            this.x = x;
            this.y = y;
            this.theta = theta;
        }

        private State() {
        }

        @Override
        public String toString() {
            String s = "["+x+", "+y+", "+theta+"]";
            return s;
        }
        
    }

    @Override
    public void draw() {
        currentState.x += step * Math.cos(Math.toRadians(currentState.theta));
        currentState.y += step * Math.sin(Math.toRadians(currentState.theta));
    }

    @Override
    public void move() {
        currentState.x += step * Math.cos(Math.toRadians(currentState.theta));
        currentState.y += step * Math.sin(Math.toRadians(currentState.theta));
    }

    @Override
    public void turnR() {
        currentState.theta -= delta;
    }

    @Override
    public void turnL() {
        currentState.theta += delta;
    }

    @Override
    public void push() {
        State temp = new State(currentState.x,currentState.y,currentState.theta);
        stack.push(temp);
//        System.out.println(temp);
    }

    @Override
    public void pop() {
        if(!stack.isEmpty()){
            this.currentState = stack.pop();
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
        currentState = new State(pos.getX(), pos.getY(), angle_deg);
    }

    @Override
    public Point2D getPosition() {
        Point2D position = new Point2D.Double(currentState.x, currentState.y);
        return position;
    }

    @Override
    public double getAngle() {
        return currentState.theta;
    }

    @Override
    public void setUnits(double step, double delta) {
        this.step = step;
        this.delta = delta;
    }
    
}
