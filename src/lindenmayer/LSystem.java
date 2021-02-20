package lindenmayer;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import lindenmayer.Symbol.Seq;
import lindenmayer.Symbol;
import lindenmayer.Symbol.Sequence;
import org.json.*;

public class LSystem extends AbstractLSystem {
    
    private String file;
    private int numIter;
    private HashMap<Character, Symbol> alphabet;
    private String axiom;
//    private HashMap<Symbol, List<Symbol.Seq>> rules; //(prof?)
    private HashMap<Symbol, List<Symbol>> rules;
    private HashMap<Symbol, String> actions;
    private int step;
    private double angle;
    private int[] start;
    
    private TurtleModel turtle;
    
    /** TODO: Constructor **/
    public LSystem(String file, int numIter) throws IOException{
        this.numIter = numIter;
        this.file = file;
        this.alphabet = new HashMap<>();
        this.rules = new HashMap<>();
        this.actions = new HashMap<>();
        this.start = new int[3];
        this.readJSONFile();
        this.initTurtleModel();
    }
    
//    public static void readJSONFile(String file, LSystem S, Turtle T) throws java.io.IOException {
//        // deprecated?
//    }
    
    private void readJSONFile() throws java.io.IOException {
        JSONObject input = new JSONObject(new JSONTokener(new java.io.FileReader(file))); // lecture de fichier JSON avec JSONTokener
        JSONArray alphabet = input.getJSONArray("alphabet");
        String axiom = input.getString("axiom");
        JSONObject rules = new JSONObject(input, "rules").getJSONObject("rules");
        JSONObject actions = new JSONObject(input, "actions").getJSONObject("actions");
        JSONObject parameters = new JSONObject(input, "parameters").getJSONObject("parameters");
        
        // Set axiom
        this.setAxiom(axiom);
        
        // add alphabet
        readAlphabetFromJSONFile(alphabet);
        
        // set actions
        readActionsFromJSONFile(actions);
        
        // add rules
        Iterator<String> keys = rules.keys();
        while(keys.hasNext()){
            String symbol = keys.next(); 
            // ISSUE: read expansion as string, not JSONArray
            String expansion = rules.get(symbol).toString();
            expansion = expansion.replace("\"", "");
            expansion = expansion.replace("[", "");
            expansion = expansion.replace("]", "");
            char character = symbol.charAt(0);
            Symbol sym = getSymbolFromCharacter(character);
            addRule(sym, expansion);
        }
        
        System.out.println(this.rules);
        
        // add parameters
        readParametersFromJSONFile(parameters);
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
        // ISSUE: not implemented liked the prof asked
        // 1. Get a list of symbol from the string expansion

        
        ArrayList<Symbol> symbolExpansion = new ArrayList<>();
        for(int i=0; i<expansion.length(); i++){
            Symbol temp = getSymbolFromCharacter(expansion.charAt(i));
            symbolExpansion.add(temp);
        }
        System.out.println(symbolExpansion);
        // 2. Add the rule to HashMap
        this.rules.put(sym, symbolExpansion);
        
        
        // the new interface (test)
        Symbol symbol = new Symbol('R'); // test with arbitrary char
        Symbol.Seq seq = symbol.new Sequence(symbolExpansion);
//        System.out.println(seq);
        seq.print();
    }

    @Override
    public void setAction(Symbol sym, String action) {
        this.actions.put(sym, action);
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
        // ISSUE: changer Turtle abstraction because we now have constructor
        String action = getActionFromSymbol(sym);
        switch(action){
            case "draw":
                this.turtle.draw();
                break;
            case "move":
                this.turtle.move();
                break;
            case "turnL":
                this.turtle.turnL();
                break;
            case "turnR":
                this.turtle.turnR();
                break;
            case "push":
                this.turtle.push();
                break;
            case "pop":
                this.turtle.pop();
                break;
            case "stay":
                this.turtle.stay();
                break;
            default:
                break;
        }
    }

    @Override
    public Symbol.Seq applyRules(Symbol.Seq seq, int n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rectangle2D tell(Turtle turtle, Symbol sym, int rounds) {
        // TODO: UI de la tortue
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void readParametersFromJSONFile(JSONObject parameters) {
        this.angle = parameters.getDouble("angle");
        this.step = parameters.getInt("step");
        JSONArray temp = parameters.getJSONArray("start");
        for (int i=0;i<temp.length();i++){ 
            int position = Integer.parseInt(temp.get(i).toString());
            this.start[i] = position;
        }     
    }

    private void readAlphabetFromJSONFile(JSONArray alphabet) {
        for(int i=0; i< alphabet.length(); i++){
                char character = alphabet.getString(i).charAt(0); // on ne peut pas cast directement avec (Character) alphabet.get(i)
                Symbol symbol = addSymbol(character);
            }    
    }

    private Symbol getSymbolFromCharacter(char character) {
        return (Symbol) this.alphabet.get(character);
    }

    private void readActionsFromJSONFile(JSONObject actions) {
        Iterator<String> actionKeys = actions.keys();
        while(actionKeys.hasNext()){
            String symbol = actionKeys.next();
            char character = symbol.charAt(0);
            String action = actions.getString(symbol);
            Symbol sym = getSymbolFromCharacter(character);
            setAction(sym, action);
        }
 }

    private void initTurtleModel() {
        // TODO: initialize the turtle with the json file
        turtle = new TurtleModel(this.start[0], this.start[1], this.start[2], 
                this.angle, this.step);
        // dummy 
//        Symbol symbol = getSymbolFromCharacter('R');
//        tell(turtle, symbol);
    }

    private String getActionFromSymbol(Symbol sym) {
        return (String) this.actions.get(sym);
    }

}
