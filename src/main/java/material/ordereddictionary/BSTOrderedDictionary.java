package material.ordereddictionary;

import material.tree.binarysearchtree.BinarySearchTree;
import material.tree.binarytree.linkedbinarytree.FactoryNode;

public class BSTOrderedDictionary<K,V> extends AbstractTreeOrderedDictionary<K,V> {
        
    //Constructores.
    
    public BSTOrderedDictionary (){
        super(new BinarySearchTree<> (null, null, new FactoryNode<BSTDictEntry<K,V>> ()));
    }
          
}
