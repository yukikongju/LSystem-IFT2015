/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lindenmayer;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.*;


/**
 *
 * @author emuli
 */
public class LSystem extends AbstractLSystem {
    
    private String file;
    private HashMap<Character, Symbol> alphabet;
    
    private String axiom;
    private HashMap<Symbol, List<Symbol.Seq>> rules, actions;
    private int steps;
    private double angle;
    private int[] start;
    private TurtleUI turtle;
    
    /** TODO: Constructor **/
    public LSystem(String file) throws IOException{
        this.file = file;
        this.alphabet = new HashMap<>();
        this.rules = new HashMap<>();
        this.actions = new HashMap<>();
        this.readJSONFile();
    }
    
//    public static void readJSONFile(String file, LSystem S, Turtle T) throws java.io.IOException {
//        // deprecated?
//    }
    
    private void readJSONFile() throws java.io.IOException {
        JSONObject input = new JSONObject(new JSONTokener(new java.io.FileReader(file))); // lecture de fichier JSON avec JSONTokener
        JSONArray alphabet = input.getJSONArray("alphabet");
        String axiom = input.getString("axiom");
        JSONObject rules = new JSONObject(input, "rules").getJSONObject("rules");
        JSONObject actions = new JSONObject(input, "actions");
        JSONObject parameters = new JSONObject(input, "parameters");
        
        // Set axiom
        this.setAxiom(axiom);
        
        // read and add alphabet
        for(int i=0; i< alphabet.length(); i++){
            char character =  alphabet.get(i).toString().charAt(0); // on ne peut pas cast directement avec (Character) alphabet.get(i)
            Symbol symbol = addSymbol(character);
        }
        
    }

    @Override
    public Symbol addSymbol(char sym) {
        // add symbol to alphabet and return symbol
        Symbol symbol = new Symbol(sym);
        this.alphabet.put(sym, symbol);
        return symbol;
    }

    @Override
    public void addRule(Symbol sym, String expansion) {
        // add rule with its expansion
    }

    @Override
    public void setAction(Symbol sym, String action) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAxiom(String str) {
        this.axiom = str;
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
