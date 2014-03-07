package material.ordereddictionary;

import material.tree.binarysearchtree.BinarySearchTree;
import material.tree.binarytree.linkedbinarytree.FactoryNode;

/**
 *
 * @author Asus
 * @param <K>
 * @param <V>
 */
public class BSTOrderedDictionary<K,V> extends AbstractTreeOrderedDictionary<K,V> {
        
    //Constructores.
    
    /**
     *
     */
    public BSTOrderedDictionary (){
        super(new BinarySearchTree<> (null, null, new FactoryNode<BSTDictEntry<K,V>> ()));
    }
          
}
