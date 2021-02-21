package lindenmayer;

import java.awt.geom.Point2D;
import java.util.Observable;
import java.util.Stack;

/**
 *
 * @author emuli
 */
public abstract class TurtleModel extends Observable implements AbstractTurtle  {
    
    private double step, delta;
    private Stack<State> stack;
    private State currentState;
    
    private final static String FORMAT = "%.1f %.1f";
    
    public TurtleModel(double x, double y, double theta, double delta, double step) {
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
//        printPosition();
//        System.out.println(" M ");
    }

    @Override
    public void turnR() {
        currentState.theta -= delta;
    }

    @Override
    public void turnL() {
        currentState.theta += delta;
    }

    public double getStep() {
        return step;
    }

    @Override
    public void push() {
        State temp = new State(currentState.x,currentState.y,currentState.theta);
//        System.out.println("stroke");
        stack.push(temp);
//        printPosition();
//        System.out.println(" newpath M ");
    }

    @Override
    public void pop() {
        if(!stack.isEmpty()){
            this.currentState = stack.pop();
//            System.out.println("stroke");
//            printPosition();
//            System.out.println(" newpath M ");
        } else {
            System.out.println("An error has occured.");
        }
    }

    @Override
    public void stay() {
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

    protected void printPosition(){
        Point2D currentPosition = this.getPosition();
        System.out.printf(FORMAT, currentPosition.getX(),
                currentPosition.getY(), " ");
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
    
    public void majObserver(){
        // send updated value to ui
        // TODO: add majObserver() in tell()???
        setChanged();
        notifyObservers();
    }
    
}

