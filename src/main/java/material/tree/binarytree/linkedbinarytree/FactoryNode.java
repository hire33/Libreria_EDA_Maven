package material.tree.binarytree.linkedbinarytree;

/**
 *
 * @author Asus
 * @param <E>
 */
public class FactoryNode<E> {

	/**
     *
     * @param element
     * @param parent
     * @param left
     * @param right
     * @return
     */
    public BTNode<E> createNode (E element, BTNode<E> parent,
			BTNode<E> left, BTNode<E> right){
			
		return new BTNode<> (element, parent, left, right);
	}

}
