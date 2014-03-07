package material.tree.binarytree.arraybinarytree;

import material.tree.Position;

public class BTPos<E> implements Position<E> {

    //Atributos.
    
    E element; // element stored at this position
    int index; // index of this position in the array list
    int left, right, parent;

    //Constructores.
    
    public BTPos(E element, int index, int left, int right, int parent) {
        this.element = element;
        this.index = index;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
    
    public BTPos(E elt, int i) {
        this(elt, i, -1, -1, -1);
    }
    
    //Getter & Setter.

    /**
     * Returns the element stored at this position
     */
    @Override
    public E element() {
        return element;
    }

    /**
     * Sets the element stored at this position
     */
    public E setElement(E elt) {
        E temp = element;
        element = elt;
        return temp;
    }

    /**
     * Returns the left child of this position
     */
    public int getLeft() {
        return left;
    }

    /**
     * Sets the left child of this position
     */
    public void setLeft(int v) {
        left = v;
    }

    /**
     * Returns the right child of this position
     */
    public int getRight() {
        return right;
    }

    /**
     * Sets the right child of this position
     */
    public void setRight(int v) {
        right = v;
    }

    /**
     * Returns the parent of this position
     */
    public int getParent() {
        return parent;
    }

    /**
     * Sets the parent of this position
     */
    public void setParent(int v) {
        parent = v;
    }

    public int index() {
        return index;
    }

    public void setIndex(int i) {
        index = i;
    }
    
    //Métodos.
    
    @Override
    public String toString() {
        return ("[" + element + "," + index + "]");
    }

}
