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
//        Iterator<Symbol> iterator();
        
        void print();
        void concatToSequence(Sequence concatenation);
        void replaceSymbolBySequence(Sequence substitution);
        
    }
    
    public class Sequence implements Seq{
        private ArrayList<Symbol> sequences;
        
        public Sequence(ArrayList<Symbol> sequences){
            this.sequences = sequences;
        }
        
        public Sequence(){
            this.sequences = new ArrayList<>();
        }

        public ArrayList<Symbol> getSequences() {
            return sequences;
        }

        @Override
        public void concatToSequence(Sequence concatenation){
            this.sequences.addAll(concatenation.getSequences());
        }

        @Override
        public Iterator<Symbol> iterator() {
            //TODO
            Iterator<Symbol> temp = sequences.iterator();
            while(temp.hasNext()){
//                System.out.print(temp.next());
                temp.next();
            }
            return temp;
        }
        
        @Override
        public void print() {
            //TODO
//            System.out.println("testintintti");
//            Iterator<Symbol> temp = sequences.iterator();
//            while(temp.hasNext()){
//                System.out.println(temp.next());
//            }
//            return temp;
        }

        @Override
        public String toString() {
            String s = "";
            for(int i=0; i<this.sequences.size();i++){
                s += this.sequences.get(i);
            }
            return s;
        }

        @Override
        public void replaceSymbolBySequence(Sequence substitution) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
    
}
