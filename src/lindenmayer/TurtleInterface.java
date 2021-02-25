package lindenmayer;

import java.awt.geom.Point2D;
import java.util.Observable;
import java.util.Stack;

/**
 *
 * @author emuli
 */
public abstract class AbstractTurtle extends Observable implements TurtleModel  {
    
    private double step, delta;
    private Stack<State> stack;
    private State currentState;
    
    private final static String FORMAT = "%.1f %.1f";
    
    public AbstractTurtle(double x, double y, double theta, double delta, double step) {
        this.delta = delta;
        this.step = step;
        this.stack = new Stack<>();
        currentState = new State(x,y,theta);
    }
    
    public AbstractTurtle(){
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

<<<<<<< HEAD:src/lindenmayer/AbstractTurtle.java
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
    
=======
public interface TurtleInterface 
{
    /**
     * Draws a line of unit length
     */
    public void draw();
    /**
     * Moves by unit length, no drawing. 
     */
    public void move();
    /**
     * Turn right (clockwise) by unit angle.
     */
    public void turnR();
    /**
     * Turn left (counter-clockwise) by unit angle.
     */
    public void turnL();
    /**
     * Saves turtle state
     */
    public void push();
    /**
     * Recovers turtle state
     */
    public void pop();
    /**
     * Lets the turtle relax. 
     */
    public void stay();
    /**
     * initializes the turtle state (and clears the state stack)
     * @param pos turtle position
     * @param angle_deg angle in degrees (90=up, 0=right)
     */
    public void init(Point2D pos, double angle_deg);
    /**
     * Turtle position 
     * 
     * @return location of the turtle on the plane
     */
    public Point2D getPosition();
    /**
     * angle of the turtle's nose
     * @return angle in degrees
     */
    public double getAngle();
    /**
     * sets the unit step and unit angle
     * 
     * @param step length of an advance (move or draw)
     * @param delta unit angle change in degrees (for turnR and turnL)
     */
    public void setUnits(double step, double delta);
>>>>>>> 2f233aba890170965b85b1ade9a2159d0679610e:src/lindenmayer/TurtleInterface.java
}

