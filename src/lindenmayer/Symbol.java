package lindenmayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * * Symbol in an L-system's alphabet. * * @author Mikl&oacute;s
 * Cs&#369;r&ouml;s
 */
public class Symbol {

    private final char value;
    private ArrayList<Symbol.Seq> rules;
    private String action;

    public Symbol(char c) {
        this.value = c;
        rules = new ArrayList<>();
    }
    
    @Override
    public String toString() {
        return Character.toString(value);
    }
    
    public void setAction(String action){
        this.action = action;
    }

    public void addRule(Symbol.Seq rule){
        this.rules.add(rule);
    }

    public String getAction() {
        return action;
    }

    public Seq getRule() {
        if(rules.isEmpty()){
            return null;
        } else {
            Random random = new Random();
            int index = random.nextInt(rules.size()) % rules.size(); // verify randomness
            return rules.get(index);
        }
        
    }

    /**
     * * Common interface to a string of symbols. *
     */
   
    public interface Seq extends Iterable<Symbol>{
        int size();
        void concat(Symbol symbol);
        void concat(Symbol.Seq sequence);
    }
    
}
