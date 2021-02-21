package lindenmayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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

    public int getNumRules(){
        return rules.size();
    }
    
    @Override
    public String toString() {
        return Character.toString(value);
    }
    
    public void setAction(String action){
        this.action = action;
    }

    public String getAction() {
        return action;
    }
    
    

    /**
     * * Common interface to a string of symbols. *
     */
   
    public interface Seq extends Iterable<Symbol>{
//        void concatToSequence(Sequence concatenation);
//        void replaceSymbolBySequence(Sequence substitution);
        int size();
        
        void concat(Symbol symbol);
        void concat(Symbol.Seq sequence);
        
        
        
    }
    
//    public class Sequence implements Symbol.Seq{
//        private List<Symbol> sequences;
//        
//        public Sequence(ArrayList<Symbol> sequences){
//            this.sequences = sequences;
//        }
//        
//        
//        
//        public Sequence(){
//            this.sequences = new LinkedList<>();
//        }
//
////        public ArrayList<Symbol> getSequences() {
////            return sequences;
////        }
//
////        @Override
////        public void concatToSequence(Sequence concatenation){
////            this.sequences.addAll(concatenation.getSequences());
////        }
//
//        @Override
//        public Iterator<Symbol> iterator() {
//            //TODO
//            Iterator<Symbol> temp = sequences.iterator();
//            while(temp.hasNext()){
////                System.out.print(temp.next());
//                temp.next();
//            }
//            return temp;
//        }
//        
//
//        @Override
//        public String toString() {
//            String s = "";
//            for(int i=0; i<this.sequences.size();i++){
//                s += this.sequences.get(i);
//            }
//            return s;
//        }
//
//        @Override
//        public void concatSymbol(Symbol symbol) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void concatSequence(Seq sequence) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//    }
    
}
