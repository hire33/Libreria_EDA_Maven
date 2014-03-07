package material.ordereddictionary;

import material.tree.binarysearchtree.FactoryRBNode;
import material.tree.binarysearchtree.RBTree;

public class RBOrderedDictionary<K,V> extends AbstractTreeOrderedDictionary<K,V> {

    //Constructores.
    
    public RBOrderedDictionary (){
        super(new RBTree<> (null, null, new FactoryRBNode<BSTDictEntry<K,V>> ()));
    }
    
}

