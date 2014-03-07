package material.tree.LCRSTree;

import material.tree.Position;

/**
 *
 * @author Asus
 * @param <E>
 */
public final class LCRSNode<E> implements Position <E> {
    
    //Atributos.
    
    private E element;
    private LCRSNode<E> parent;
    private LCRSNode<E> rightSibling; //Sibling = Hermano.
    private LCRSNode<E> leftChild; //Child = Hijo.
    
    //Constructores.
    
    /**
     * Main constructor
     */
    public LCRSNode(E element, LCRSNode<E> parent, LCRSNode<E> rightSibling, LCRSNode<E> leftChild) {
        this.setElement(element);
        this.setParent(parent);
        this.setRightSibling(rightSibling);
        this.setLeftChild(leftChild);
    }
    
    //Getter & Setter.

    @Override
    public E element() {
        return element;
    }
    
    /**
     *
     * @param element
     */
    public void setElement(E element) {
        this.element = element;
    }

    /**
     *
     * @return
     */
    public LCRSNode<E> getParent() {
        return parent;
    }

    /**
     *
     * @param parent
     */
    public void setParent(LCRSNode<E> parent) {
        this.parent = parent;
    }

    /**
     *
     * @return
     */
    public LCRSNode<E> getRightSibling() {
        return rightSibling;
    }

    /**
     *
     * @param rightSibling
     */
    public void setRightSibling(LCRSNode<E> rightSibling) {
        this.rightSibling = rightSibling;
    }

    /**
     *
     * @return
     */
    public LCRSNode<E> getLeftChild() {
        return leftChild;
    }

    /**
     *
     * @param leftChild
     */
    public void setLeftChild(LCRSNode<E> leftChild) {
        this.leftChild = leftChild;
    }
    
}
