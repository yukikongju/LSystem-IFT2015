package lindenmayer;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import lindenmayer.Symbol.Sequence;
//import lindenmayer.Symbol.Test;
import org.json.*;

public class LSystem extends AbstractLSystem {
    
    private String file;
    private int rounds;
    private HashMap<Character, Symbol> alphabet;
//    private String axiom;
//    private Symbol.Seq axiom;
    private Sequence axiom;

//    private HashMap<Symbol, List<Symbol.Seq>> rules; //(prof?)
//    private HashMap<Symbol, List<Symbol>> rules;
//    private HashMap<Symbol, List<Symbol.Seq>> rules;
        private HashMap<Symbol, List<Sequence>> rules;

    private HashMap<Symbol, String> actions;
    private int step;
    private double angle;
    private int[] start;
    
    private TurtleModel turtle;
    
    /** TODO: Constructor **/
    public LSystem(String file, int rounds) throws IOException{
        this.rounds = rounds;
        this.file = file;
        this.alphabet = new HashMap<>();
        this.rules = new HashMap<>();
        this.actions = new HashMap<>();
        this.start = new int[3];
        this.readJSONFile();
        this.initTurtleModel();
        this.applyRules(this.axiom, this.rounds);
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
        // add symbol to alphabet and return symbol
        Symbol symbol = new Symbol(sym);
        this.alphabet.put(sym, symbol);
        return symbol;
    }

    @Override
    public void addRule(Symbol sym, String expansion) {
        // TODO: refractor from readRulesFromJSONFile
    }

    @Override
    public void setAction(Symbol sym, String action) {
        this.actions.put(sym, action);
    }

    @Override
    public void setAxiom(String str) {
//        this.axiom = str;
//        System.out.println(str);
        char character = str.charAt(0);
        Symbol sym = getSymbolFromCharacter(character);
//        System.out.println(sym);
        this.axiom = getSequenceFromStringExpansion(sym, str);
//        System.out.println(this.axiom.toString());
    }

    @Override
    public Symbol.Seq getAxiom() {
        applyRules(axiom, step);
        return null; // to change
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public Symbol.Seq rewrite(Symbol sym) { // untested
//        if(this.rules.containsKey(sym)){
//            // get random rule
//            Random random = new Random();
//            List<Symbol.Seq> allRules = this.rules.get(sym);
////            System.out.println(allRules.size());
//            int index = random.nextInt(allRules.size()) % allRules.size(); // verify randomness
//            return allRules.get(index);
//        }
//        // return the same key if no mappings
//        return getSequenceFromSymbol(sym);
//    }

     public Sequence rewrite(Symbol sym) { // untested
        if(this.rules.containsKey(sym)){
            // get random rule
            Random random = new Random();
//            List<Symbol.Seq> allRules = this.rules.get(sym);
            List<Sequence> allRules = this.rules.get(sym);

//            System.out.println(allRules.size());
            int index = random.nextInt(allRules.size()) % allRules.size(); // verify randomness
            return allRules.get(index);
        }
        // return the same key if no mappings
        return getSequenceFromSymbol(sym);
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
    public Symbol.Seq applyRules(Symbol.Seq seq, int n) { //replace Symbol.Seq by this.axiom
//       1. traverse the sequence symbol by symbol
//       2. for each symbol, get the corresponding substitution with rewrite
//       3. append the substitution to the auxiliary
//       4. 

        Symbol dummy = new Symbol('F');
        Sequence newSequence = dummy.new Sequence();
        Iterator sequence = this.axiom.iterator();
//        System.out.println(sequence.getClass());
//        do{
//            Sequence substitution = rewrite(sequence.next());
//        }
        
//        do{
////            Symbol.Seq substitution = rewrite(sequence.next());
//        }while(sequence.hasNext());
        
        return null;

//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    }

    private String getActionFromSymbol(Symbol sym) {
        return (String) this.actions.get(sym);
    }

    private void readRulesFromJSONFile(JSONObject rules) {
        // TODO
//        1. get chacune des expansions
//        2. transformer chaque expansion as a Symbol.Seq
//        3. Add the symbol and all its expansion to this.rules
        
        Iterator<String> keys = rules.keys();
        while(keys.hasNext()){ // iterate through each axiom in rules
            String symbol = keys.next();
            char character = symbol.charAt(0);
            Symbol sym = getSymbolFromCharacter(character);
            JSONArray allExpansions = rules.getJSONArray(symbol);
            
//            ArrayList<Symbol.Seq> allRules = new ArrayList<>();
            ArrayList<Sequence> allRules = new ArrayList<>();


            for (int i = 0; i < allExpansions.length(); i++) {
                String singleExpansion = allExpansions.getString(i);
//                Symbol.Seq symbolExpansion = getSequenceFromStringExpansion(sym, singleExpansion);
                Sequence symbolExpansion = getSequenceFromStringExpansion(sym, singleExpansion);

                allRules.add(symbolExpansion);
            }
            this.rules.put(sym, allRules);
            
        }
    }

     private Sequence getSequenceFromStringExpansion(Symbol sym, String expansion) {
        ArrayList<Symbol> symbolExpansion = new ArrayList<>();
        for(int j=0; j<expansion.length(); j++){
            Symbol temp = getSymbolFromCharacter(expansion.charAt(j));
            symbolExpansion.add(temp);
        }
//        Symbol.Seq seq = sym.new Sequence(symbolExpansion); 
        Sequence seq = sym.new Sequence(symbolExpansion);
//        System.out.println(seq.iterator());
        return seq;
    }
    
//    private Symbol.Seq getSequenceFromStringExpansion(Symbol sym, String expansion) {
//        ArrayList<Symbol> symbolExpansion = new ArrayList<>();
//        for(int j=0; j<expansion.length(); j++){
//            Symbol temp = getSymbolFromCharacter(expansion.charAt(j));
//            symbolExpansion.add(temp);
//        }
////        Symbol.Seq seq = sym.new Sequence(symbolExpansion); 
//        Sequence seq = sym.new Sequence(symbolExpansion);
////        System.out.println(seq.iterator());
//        return seq;
//    }

//    private Symbol.Seq getSequenceFromSymbol(Symbol sym) {
//        ArrayList<Symbol> sequence = new ArrayList<>();
//        sequence.add(sym);
//        return sym.new Sequence(sequence);
//    }

     private Sequence getSequenceFromSymbol(Symbol sym) {
        ArrayList<Symbol> sequence = new ArrayList<>();
        sequence.add(sym);
        return sym.new Sequence(sequence);
    }
    
}
