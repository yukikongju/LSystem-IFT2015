package lindenmayer;

import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Iterator;
import lindenmayer.Symbol.Seq;

public class LSystem extends AbstractLSystem {
    
    private int rounds;
    private HashMap<Character, Symbol> alphabet;
    private Seq axiom;

    private Rectangle2D rectangle2D;
    
    public LSystem(int rounds) {
        this.alphabet = new HashMap<>();
        this.rounds = rounds;
        rectangle2D = new Rectangle2D.Double();
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
     public Symbol.Seq rewrite(Symbol sym) { 
          Symbol.Seq rule = sym.getRule();
          if(rule == null){
              rule = new Sequence();
              rule.concat(sym);
              return rule;
          } return rule;
    }
    
     private void updateTurtle(TurtleInterface turtle, String action){
         switch(action){
            case "draw":
                turtle.draw();
                break;
            case "move":
                turtle.move();
                break;
            case "turnL":
                turtle.turnL();
                break;
            case "turnR":
                turtle.turnR();
                break;
            case "push":
                turtle.push();
                break;
            case "pop":
                turtle.pop();
                break;
            case "stay":
                turtle.stay();
                break;
            default:
                break;
        }
     }
    
    @Override
    public void tell(TurtleModel turtle, Symbol.Seq seq) {
        Iterator<Symbol> iter = seq.iterator();
        while(iter.hasNext()){
            updateTurtle(turtle, iter.next().getAction());
        }
    }

    public Symbol.Seq applyRule(Symbol.Seq seq){ // apply rule on only one sentence
        Symbol.Seq newSequence = new Sequence();
        Iterator<Symbol> iter = seq.iterator();
        while(iter.hasNext()){
            Symbol symbol = (Symbol) iter.next();
            Symbol.Seq substitution = rewrite(symbol);
            newSequence.concat(substitution);
        }
        return newSequence;
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
        return applyRules(newSequence, n); // new sequence
    }

    Symbol getSymbolFromCharacter(char character) {
        return (Symbol) this.alphabet.get(character);
    }

     private Symbol.Seq getSequenceFromString(String expansion) {
        Sequence sequence = new Sequence();
        for(int i = 0; i<expansion.length(); i++){
            Symbol symbol = getSymbolFromCharacter(expansion.charAt(i));
            sequence.concat(symbol);
        }
        return sequence;
    }

    @Override
    public Rectangle2D tell(TurtleModel turtle, Symbol.Seq seq, int rounds) {
          if(rounds == 0 ){
            tell(turtle, seq);
        } else {
            Iterator<Symbol> iter = seq.iterator();
            if(iter == null){
                tell(turtle, seq, rounds - 1); 
            } else {
                while(iter.hasNext()){
                    tell(turtle, rewrite(iter.next()), rounds -1); 
                } 
            }
    }
        rectangle2D.add(turtle.getPosition());
//        System.out.println(rectangle2D.getX() + " " + rectangle2D.getY());
        return rectangle2D;
    }
    
}