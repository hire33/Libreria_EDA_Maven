package material.tree.binarysearchtree;

import material.tree.binarytree.linkedbinarytree.BTNode;

/**
 *
 * @author Asus
 * @param <E>
 */
public class AVLNode<E> extends BTNode<E> {

    //Atributos.
    
    /**
     *
     */
    protected int height; // we add a height field to a BTNode

    //Constructores.
    
    /**
     * Preferred constructor
     */
    public AVLNode (E element, BTNode<E> parent, BTNode<E> left, BTNode<E> right) {
        super(element, parent, left, right);
        height = 0;
        if (left != null) {
            height = Math.max(height, 1 + ((AVLNode<E>) left).getHeight());
        }
        if (right != null) {
            height = Math.max(height, 1 + ((AVLNode<E>) right).getHeight());
        }
    } // we assume that the parent will revise its height if needed

    //Getter & Setter.
    
    /**
     *
     * @param h
     */
    public void setHeight(int h) {
        height = h;
    }

    /**
     *
     * @return
     */
    public int getHeight() {
        return height;
    }

}
