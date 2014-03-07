package material.tree.binarysearchtree;

import java.util.Comparator;
import material.ordereddictionary.BSTDictEntry;

public class ODFindComparator<E> implements Comparator<E>{

    /**
     * Comparador adaptado a diccionarios ordenados y arboles binarios de búsqueda.
     */
    
    @Override
    public int compare(E a, E b) {
        if (a instanceof Comparable<?>) {
            return ((Comparable<E>) a).compareTo(b);
        } else {
            BSTDictEntry e1;
            try{
                e1 = (BSTDictEntry) a;
            } catch (ClassCastException cCE){
                return -2;
            }
            BSTDictEntry e2;
            try{
                e2 = (BSTDictEntry) b;
            } catch (ClassCastException cCE){
                return -2;
            }   
            if (e1.getKey().equals(e2.getKey())) {
                return 0;
            } else if (e1.getKey().hashCode() > e2.getKey().hashCode()) {
                return 1;
            } else {
                return -1;
            }   
           
        }
        
    }
    
}
