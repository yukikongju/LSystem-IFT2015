package lindenmayer;

import java.awt.geom.Point2D;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONFile {
    
    private String file;
    private JSONObject input;

    public JSONFile(String file) throws FileNotFoundException {
        this.file = file;
        input = new JSONObject(new JSONTokener(new java.io.FileReader(this.file)));
    }
    
    public TurtleUI getTurtleUI() throws FileNotFoundException{
        JSONObject parameters = new JSONObject(this.input, "parameters").getJSONObject("parameters");
        double angle = parameters.getDouble("angle");
        double step = parameters.getDouble("step");
        JSONArray temp = parameters.getJSONArray("start");
        // Init Turtle 
        Point2D position = new Point2D.Double(temp.getDouble(0), temp.getDouble(1));
        TurtleUI turtle = new TurtleUI();
        turtle.init(position, temp.getDouble(2));
        turtle.setUnits(step, angle);
        return turtle;
    }
    
    public TurtlePS getTurtlePS() throws FileNotFoundException{
        JSONObject parameters = new JSONObject(this.input, "parameters").getJSONObject("parameters");
        double angle = parameters.getDouble("angle");
        double step = parameters.getDouble("step");
        JSONArray temp = parameters.getJSONArray("start");
        // Init Turtle 
        Point2D position = new Point2D.Double(temp.getDouble(0), temp.getDouble(1));
        TurtlePS turtle = new TurtlePS();
        turtle.init(position, temp.getDouble(2));
        turtle.setUnits(step, angle);
        return turtle;
    }
    
    public LSystem getLSystem(){
        LSystem lsystem = new LSystem();
        
        // add alphabet
        readAlphabetFromJSONFile(lsystem);
        
        // Set axiom
        readAxiomFromJSONFile(lsystem);
        
        // set actions
        readActionsFromJSONFile(lsystem);
        
        // add rules
        readRulesFromJSONFile(lsystem);
        
        return lsystem;
    }
    
    private void readRulesFromJSONFile(LSystem lsystem) {
        JSONObject rules = new JSONObject(input, "rules").getJSONObject("rules");
        Iterator<String> keys = rules.keys();
        while(keys.hasNext()){ // iterate through each axiom in rules
        String symbol = keys.next();
        char character = symbol.charAt(0);
        Symbol sym = lsystem.getSymbolFromCharacter(character);
        JSONArray allExpansions = rules.getJSONArray(symbol);
        for (int i = 0; i < allExpansions.length(); i++) {
            String rule = allExpansions.getString(i);
            lsystem.addRule(sym, rule);
        }
    }
}

    private void readActionsFromJSONFile(LSystem lsystem) {
        JSONObject actions = new JSONObject(input, "actions").getJSONObject("actions");

        Iterator<String> actionKeys = actions.keys();
        while(actionKeys.hasNext()){
            String symbol = actionKeys.next();
            char character = symbol.charAt(0);
            String action = actions.getString(symbol);
            Symbol sym = lsystem.getSymbolFromCharacter(character);
            lsystem.setAction(sym, action);
        }
    }
      
       
    private void readAlphabetFromJSONFile(LSystem lsystem) {
        JSONArray alphabet = input.getJSONArray("alphabet");
        for(int i=0; i< alphabet.length(); i++){
            char character = alphabet.getString(i).charAt(0); // on ne peut pas cast directement avec (Character) alphabet.get(i)
            Symbol symbol = lsystem.addSymbol(character);
        }    
    }
    
    private void readAxiomFromJSONFile(LSystem lsystem) {
        String axiom = input.getString("axiom");
        lsystem.setAxiom(axiom);
    }
}
