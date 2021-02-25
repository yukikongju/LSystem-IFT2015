package lindenmayer;

<<<<<<< HEAD
/*
 * Copyright 2021 Mikl&oacute;s Cs&#369;r&ouml;s.
=======
import java.awt.geom.Point2D;
import java.util.Stack;

/**
>>>>>>> 2f233aba890170965b85b1ade9a2159d0679610e
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
<<<<<<< HEAD

import java.awt.geom.Point2D;
/**
 * Turtle graphics interface. The turtle state is defined as its 
 * location on the plane and the orientation of its nose. 
 * Implementing classes are expected to initialize 
 * the turtle with position (0,0) and angle 0 by default.
 * The turtle moves and draws by unit-length steps, and turns left or right by a unit angle
 * (e.g., 30), which are set in {@link #setUnits(double, double) }. 
 * 
 * 
 * @author Mikl&oacute;s Cs&#369;r&ouml;s
 */

public interface TurtleModel 
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
=======
public abstract class TurtleModel implements TurtleInterface  {
    
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
        stack.push(temp);
    }

    @Override
    public void pop() {
        if(!stack.isEmpty()){
            this.currentState = stack.pop();
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
    
>>>>>>> 2f233aba890170965b85b1ade9a2159d0679610e
}
