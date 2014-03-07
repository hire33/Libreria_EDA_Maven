package material.tree.binarysearchtree;

import material.tree.binarytree.linkedbinarytree.BTNode;
import material.tree.binarytree.linkedbinarytree.FactoryNode;

public class FactoryRBNode<E> extends FactoryNode<E> {

    @Override
    public BTNode<E> createNode (E element, BTNode<E> parent,
	BTNode<E> left, BTNode<E> right){			
	return new RBNode<> (element, parent, left, right);
    }

}
