package lindenmayer;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import lindenmayer.Symbol.Seq;

import org.json.*;

public class LSystem extends AbstractLSystem {
    
    private String file;
    private int rounds;
    private HashMap<Character, Symbol> alphabet;
//    private Sequence axiom;
    private Seq axiom;

//    private HashMap<Symbol, List<Sequence>> rules;

//    private HashMap<Symbol, String> actions;
    
    private TurtleModel turtle;
    
    /** TODO: Constructor **/
    public LSystem(String file, int rounds) throws IOException{
        this.rounds = rounds;
        this.file = file;
        this.alphabet = new HashMap<>();
//        this.rules = new HashMap<>();
//        this.actions = new HashMap<>();
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
//        System.out.println(this.alphabet);
        
        // Set axiom
//        System.out.println(axiom);
        this.setAxiom(axiom);
//        System.out.println(this.axiom);
        
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
//        sym.
    }

    @Override
    public void setAction(Symbol sym, String action) {
//        this.actions.put(sym, action);
          sym.setAction(action);
    }

    @Override
    public void setAxiom(String str) {
//        char character = str.charAt(0);
//        Symbol sym = getSymbolFromCharacter(character);
        System.out.println(str);
        this.axiom = getSequenceFromString(str);
//        System.out.println(this.axiom.toString());
    }

    @Override
    public Symbol.Seq getAxiom() {
        return this.axiom;
    }

     public Sequence rewrite(Symbol sym) { // untested
//        if(this.rules.containsKey(sym)){
            // get random rule
//            Random random = new Random();
//            List<Sequence> allRules = this.rules.get(sym);
//            int index = random.nextInt(allRules.size()) % allRules.size(); // verify randomness
//            return allRules.get(index);
//        }
//        // return the same key if no mappings
//        return getSequenceFromSymbol(sym);\
        return null;
    }
    
    @Override
    public void tell(Turtle turtle, Symbol sym) {
//        String action = getActionFromSymbol(sym);
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

    public Sequence applyRules(Sequence seq, int n) { //replace Symbol.Seq by this.axiom
//        if(n>=this.rounds) return seq;
//        
//        Symbol dummy = new Symbol('F');
//        Sequence newSequence = dummy.new Sequence();
//        
//        
//        Iterator iter = seq.getSequences().iterator();
////        Iterator<Symbol> iter = seq.iterator();
//        
//        while(iter.hasNext()){
//            Symbol symbol = (Symbol) iter.next();
//            Sequence substitution = rewrite(symbol);
//            newSequence.concatToSequence(substitution);
//        }
//
//        n++;
//        System.out.println(newSequence);
//
//        turtleCalculation(newSequence); // call Turtle calculation
////        System.out.println("");
//        
//        return applyRules(newSequence, n); // new sequence
        return null;

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

//    private String getActionFromSymbol(Symbol sym) {
//        return (String) this.actions.get(sym);
//    }

    private void readRulesFromJSONFile(JSONObject rules) {
        Iterator<String> keys = rules.keys();
        while(keys.hasNext()){ // iterate through each axiom in rules
            String symbol = keys.next();
            char character = symbol.charAt(0);
            Symbol sym = getSymbolFromCharacter(character);
            JSONArray allExpansions = rules.getJSONArray(symbol);
            
            ArrayList<Sequence> allRules = new ArrayList<>();

            for (int i = 0; i < allExpansions.length(); i++) {
                String singleExpansion = allExpansions.getString(i);
                Symbol.Seq symbolExpansion = getSequenceFromString(singleExpansion);

//                allRules.add(symbolExpansion);
            }
//            this.rules.put(sym, allRules);
        }
    }

     private Symbol.Seq getSequenceFromString(String expansion) {
        Sequence sequence = new Sequence();
        
        for(int i = 0; i<expansion.length(); i++){
            Symbol symbol = getSymbolFromCharacter(expansion.charAt(i));
//            System.out.println(symbol.getClass());
            sequence.concat(symbol);
            
        }
        
        
//         ArrayList<Symbol> symbolExpansion = new ArrayList<>();
//        for(int j=0; j<expansion.length(); j++){
//            Symbol temp = getSymbolFromCharacter(expansion.charAt(j));
//            symbolExpansion.add(temp);
//        }
//        Sequence seq = sym.new Sequence(symbolExpansion);
        return sequence;
    }
    
     private Sequence getSequenceFromSymbol(Symbol sym) {
        ArrayList<Symbol> sequence = new ArrayList<>();
        sequence.add(sym);
//        return sym.new Sequence(sequence);
        return null;
    }

    @Override
    public Symbol.Seq applyRules(Symbol.Seq seq, int n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void turtleCalculation(Sequence sequence) {
//        Iterator iter = sequence.getSequences().iterator();
        
//        while(iter.hasNext()){
//            Symbol symbol = (Symbol) iter.next();
////            System.out.println(symbol);
//            tell(this.turtle, symbol);
//        }
    }

    @Override
    public Rectangle2D tell(Turtle turtle, Symbol sym, int rounds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
