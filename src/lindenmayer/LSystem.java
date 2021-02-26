package lindenmayer;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Iterator;
import lindenmayer.Symbol.Seq;

public class LSystem extends AbstractLSystem {
    
    private HashMap<Character, Symbol> alphabet;
    private Seq axiom;
    double minX = 0, maxX = 0, minY = 0, maxY = 0;

    private Rectangle2D rectangle2D;
    
    public LSystem() {
        this.alphabet = new HashMap<>();
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
        if(n == 0) return seq; // test
        Symbol.Seq newSequence = new Sequence();
        Iterator<Symbol> iter = seq.iterator();
        while(iter.hasNext()){
            Symbol symbol = (Symbol) iter.next();
            Symbol.Seq substitution = rewrite(symbol);
            newSequence.concat(substitution);
        }
        return applyRules(newSequence, n - 1);
    }

    public Symbol getSymbolFromCharacter(char character) {
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
        minX = Math.min(minX, turtle.getPosition().getX());
        maxX = Math.max(maxX, turtle.getPosition().getX());
        minY = Math.min(minY, turtle.getPosition().getY());
        maxY = Math.max(maxY, turtle.getPosition().getY());
        double largeur = maxX - minX;
        double hauteur = maxY - minY;
        Rectangle2D temp = new Rectangle2D.Double(minX, maxY, largeur, hauteur);
//    rectangle2D.add(turtle.getPosition());
//        return rectangle2D;
        return temp;
    }
    
}