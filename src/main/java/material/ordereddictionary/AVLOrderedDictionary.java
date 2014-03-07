package material.ordereddictionary;

import material.tree.binarysearchtree.AVLTree;
import material.tree.binarysearchtree.FactoryAVLNode;

public class AVLOrderedDictionary<K,V> extends AbstractTreeOrderedDictionary<K,V> {
    
    //Constructores.
    
    public AVLOrderedDictionary (){
        super(new AVLTree<> (null, null, new FactoryAVLNode<BSTDictEntry<K,V>>()));
    }
    
}
