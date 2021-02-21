/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lindenmayer;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author emuli
 */
public class JSONFile {
    
    private String file;
    private TurtleModel turtle;
    private LSystem lsystem;

    public JSONFile() throws IOException {
    }
    
    public void readJSONFile(String file, TurtleModel turtle, LSystem lsystem) throws java.io.IOException {
        this.file = file;
        this.turtle = turtle;
        this.lsystem = lsystem;
        JSONObject input = new JSONObject(new JSONTokener(new java.io.FileReader(this.file))); // lecture de fichier JSON avec JSONTokener
        JSONArray alphabet = input.getJSONArray("alphabet");
        String axiom = input.getString("axiom");
        JSONObject rules = new JSONObject(input, "rules").getJSONObject("rules");
        JSONObject actions = new JSONObject(input, "actions").getJSONObject("actions");
        JSONObject parameters = new JSONObject(input, "parameters").getJSONObject("parameters");
        
        // add alphabet
        readAlphabetFromJSONFile(alphabet);
        
        // Set axiom
        lsystem.setAxiom(axiom);
        
        // set actions
        readActionsFromJSONFile(actions);
        
        // add rules
        readRulesFromJSONFile(rules);
        
        // add parameters
        readParametersFromJSONFile(parameters);
    }
    
    
    private void readRulesFromJSONFile(JSONObject rules) {
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
    
      private void readActionsFromJSONFile(JSONObject actions) {
        Iterator<String> actionKeys = actions.keys();
        while(actionKeys.hasNext()){
            String symbol = actionKeys.next();
            char character = symbol.charAt(0);
            String action = actions.getString(symbol);
            Symbol sym = lsystem.getSymbolFromCharacter(character);
            lsystem.setAction(sym, action);
        }
 }
      
       private void readParametersFromJSONFile(JSONObject parameters) {
        double angle = parameters.getDouble("angle");
        double step = parameters.getDouble("step");
        JSONArray temp = parameters.getJSONArray("start");
        // Init Turtle from here?
        Point2D position = new Point2D.Double(temp.getDouble(0), temp.getDouble(1));
        turtle.init(position, temp.getDouble(2));
        turtle.setUnits(step, angle);
    }
       
    private void readAlphabetFromJSONFile(JSONArray alphabet) {
    for(int i=0; i< alphabet.length(); i++){
            char character = alphabet.getString(i).charAt(0); // on ne peut pas cast directement avec (Character) alphabet.get(i)
            Symbol symbol = lsystem.addSymbol(character);
        }    
}
    
}
