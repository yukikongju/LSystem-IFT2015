package lindenmayer;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import lindenmayer.Symbol.Seq;

import org.json.*;

public class LSystem extends AbstractLSystem {
    
    private String file;
    private int rounds;
    private HashMap<Character, Symbol> alphabet;
    private Seq axiom;

    private TurtleModel turtle;
    
    /** TODO: Constructor **/
    public LSystem(String file, int rounds) throws IOException{
        this.rounds = rounds;
        this.file = file;
        this.alphabet = new HashMap<>();
        this.readJSONFile();
        this.axiom = this.applyRules(this.axiom, 0);
    }
    
    private void readJSONFile() throws java.io.IOException {
        JSONObject input = new JSONObject(new JSONTokener(new java.io.FileReader(this.file))); // lecture de fichier JSON avec JSONTokener
        JSONArray alphabet = input.getJSONArray("alphabet");
        String axiom = input.getString("axiom");
        JSONObject rules = new JSONObject(input, "rules").getJSONObject("rules");
        JSONObject actions = new JSONObject(input, "actions").getJSONObject("actions");
        JSONObject parameters = new JSONObject(input, "parameters").getJSONObject("parameters");
        
        // add alphabet
        readAlphabetFromJSONFile(alphabet);
        
        // Set axiom
        this.setAxiom(axiom);
        
        // set actions
        readActionsFromJSONFile(actions);
        
        // add rules
        readRulesFromJSONFile(rules);
        
        // add parameters
        readParametersFromJSONFile(parameters);
    }

    @Override
    public Symbol addSymbol(char sym) {
        Symbol symbol = new Symbol(sym);
        this.alphabet.put(sym, symbol);
        return alphabet.get(sym);
    }

    @Override
    public void addRule(Symbol sym, String expansion) {
        sym.addRule(getSequenceFromString(expansion));
    }

    @Override
    public void setAction(Symbol sym, String action) {
          sym.setAction(action);
    }

    @Override
    public void setAxiom(String str) {
        this.axiom = getSequenceFromString(str);
    }

    @Override
    public Symbol.Seq getAxiom() {
        return this.axiom;
    }

    @Override
     public Symbol.Seq rewrite(Symbol sym) { // untested
          Symbol.Seq rule = sym.getRule();
          if(rule == null){
              rule = new Sequence();
              rule.concat(sym);
              return rule;
          } return rule;
    }
    
    @Override
    public void tell(AbstractTurtle turtle, Symbol sym) {
        String action = sym.getAction();
        switch(action){
            case "draw":
                this.turtle.draw();
//                System.out.print("-draw-");
                break;
            case "move":
                this.turtle.move();
//                System.out.print("-move-");

                break;
            case "turnL":
                this.turtle.turnL();
//                System.out.print("-turnL-");
                break;
            case "turnR":
                this.turtle.turnR();
//                System.out.println("-turnR-");
                break;
            case "push":
                this.turtle.push();
//                System.out.println("-push-");
                break;
            case "pop":
                this.turtle.pop();
//                System.out.println("-pop-");
                break;
            case "stay":
                this.turtle.stay();
//                System.out.println("-stay-");
                break;
            default:
                break;
        }
    }

    @Override
    public Symbol.Seq applyRules(Symbol.Seq seq, int n) { //replace Symbol.Seq by this.axiom
        if(n>=this.rounds) return seq;
        Symbol.Seq newSequence = new Sequence();
        Iterator<Symbol> iter = seq.iterator();
        while(iter.hasNext()){
            Symbol symbol = (Symbol) iter.next();
            Symbol.Seq substitution = rewrite(symbol);
            newSequence.concat(substitution);
        }
        n++;
        turtleCalculation(newSequence); // call Turtle calculation
        return applyRules(newSequence, n); // new sequence

    }

//    @Override
    public Rectangle2D tell(TurtleModel turtle, Symbol.Seq seq, int rounds) {
        // TODO: UI de la tortue
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void readParametersFromJSONFile(JSONObject parameters) {
        double angle = parameters.getDouble("angle");
        double step = parameters.getDouble("step");
        double[] start = new double[3];
        JSONArray temp = parameters.getJSONArray("start");
        for (int i=0;i<temp.length();i++){ 
            int position = Integer.parseInt(temp.get(i).toString());
            start[i] = position;
        }     
        // Init Turtle from here?
        initTurtleModel(angle, step, start);
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

    private void initTurtleModel(double angle, double step, double[] start) {
        turtle = new TurtleModel(start[0], start[1], start[2], 
                angle, step);
    }

    private void readRulesFromJSONFile(JSONObject rules) {
        Iterator<String> keys = rules.keys();
        while(keys.hasNext()){ // iterate through each axiom in rules
            String symbol = keys.next();
            char character = symbol.charAt(0);
            Symbol sym = getSymbolFromCharacter(character);
            JSONArray allExpansions = rules.getJSONArray(symbol);
            
            for (int i = 0; i < allExpansions.length(); i++) {
                String rule = allExpansions.getString(i);
                addRule(sym, rule);
            }
        }
    }

     private Symbol.Seq getSequenceFromString(String expansion) {
        Sequence sequence = new Sequence();
        for(int i = 0; i<expansion.length(); i++){
            Symbol symbol = getSymbolFromCharacter(expansion.charAt(i));
            sequence.concat(symbol);
        }
        return sequence;
    }

    private void turtleCalculation(Symbol.Seq sequence) {
        Iterator iter = sequence.iterator();
        
        while(iter.hasNext()){
            Symbol symbol = (Symbol) iter.next();
            tell(this.turtle, symbol);
        }
    }

    @Override
    public Rectangle2D tell(AbstractTurtle turtle, Symbol sym, int rounds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
