package material.tree.binarytree.linkedbinarytree;

public class FactoryNode<E> {

	public BTNode<E> createNode (E element, BTNode<E> parent,
			BTNode<E> left, BTNode<E> right){
			
		return new BTNode<> (element, parent, left, right);
	}

}
