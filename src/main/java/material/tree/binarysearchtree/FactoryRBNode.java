package material.tree.binarysearchtree;

import material.tree.binarytree.linkedbinarytree.BTNode;
import material.tree.binarytree.linkedbinarytree.FactoryNode;

/**
 *
 * @author Asus
 * @param <E>
 */
public class FactoryRBNode<E> extends FactoryNode<E> {

    /**
     *
     * @param element
     * @param parent
     * @param left
     * @param right
     * @return
     */
    @Override
    public BTNode<E> createNode (E element, BTNode<E> parent,
	BTNode<E> left, BTNode<E> right){			
	return new RBNode<> (element, parent, left, right);
    }

}
