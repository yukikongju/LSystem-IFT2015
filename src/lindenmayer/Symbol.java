package lindenmayer;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * * Symbol in an L-system's alphabet. * * @author Mikl&oacute;s
 * Cs&#369;r&ouml;s
 */
public class Symbol {

    private final char value;

    public Symbol(char c) {
        this.value = c;
    }

    @Override
    public String toString() {
        return Character.toString(value);
    }

    /**
     * * Common interface to a string of symbols. *
     */
   
    public interface Seq extends Iterable<Symbol>{
        // TODO
   
        void print();
    }
    
    public class Sequence implements Symbol.Seq{
//        private ArrayList<Symbol> sequences;
//        
//        @Override
//        public Iterator<Symbol> iterator() {
//            Iterator<Symbol> temp = sequences.iterator();
//            return temp;
//        }

        @Override
        public void print() {
            System.out.println("testintintti");
        }

        @Override
        public Iterator<Symbol> iterator() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
}
