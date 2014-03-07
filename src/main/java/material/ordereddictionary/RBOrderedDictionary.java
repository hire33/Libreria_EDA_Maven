package material.ordereddictionary;

import material.tree.binarysearchtree.FactoryRBNode;
import material.tree.binarysearchtree.RBTree;

/**
 *
 * @author Asus
 * @param <K>
 * @param <V>
 */
public class RBOrderedDictionary<K,V> extends AbstractTreeOrderedDictionary<K,V> {

    //Constructores.
    
    /**
     *
     */
    public RBOrderedDictionary (){
        super(new RBTree<> (null, null, new FactoryRBNode<BSTDictEntry<K,V>> ()));
    }
    
}

