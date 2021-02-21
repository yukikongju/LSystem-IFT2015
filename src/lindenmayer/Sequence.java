/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lindenmayer;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author emuli
 */
public class Sequence implements Symbol.Seq{

    private List<Symbol> sequences;
    
    public Sequence(){
        sequences = new LinkedList<>();
    }
    
//    @Override
//    public void concatToSequence(Symbol.Sequence concatenation) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public void concat(Symbol symbol) {
        sequences.add(symbol);
    }

    @Override
    public void concat(Symbol.Seq sequence) {
        Iterator<Symbol> iter = sequence.iterator();
        while(iter.hasNext()){
            sequences.add(iter.next());
        }
    }

    @Override
    public Iterator<Symbol> iterator() {
        return sequences.iterator();
    }

    @Override
    public int size() {
        return sequences.size();
    }
    
}
