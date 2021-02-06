/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lindenmayer;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.HashSet;
import org.json.*;

/**
 *
 * @author emuli
 */
public class LSystem extends AbstractLSystem {
    
    private String file;
    private HashSet alphabet, axiom;
    private HashMap rules, actions;
    private int steps;
    private double angle;
    private int[] position;
    //private Turtle turtle;
    private TurtleUI turtle;
    
    /** TODO: Constructor **/
    public LSystem(String file){
        this.file = file;
    }
    
    public static void readJSONFile(String file, LSystem S, Turtle T) throws java.io.IOException {
        
    }

    @Override
    public Symbol addSymbol(char sym) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addRule(Symbol sym, String expansion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAction(Symbol sym, String action) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAxiom(String str) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Symbol.Seq getAxiom() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Symbol.Seq rewrite(Symbol sym) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tell(Turtle turtle, Symbol sym) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Symbol.Seq applyRules(Symbol.Seq seq, int n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rectangle2D tell(Turtle turtle, Symbol sym, int rounds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
