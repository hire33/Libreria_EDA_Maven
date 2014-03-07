package material.tree.binarysearchtree;

import java.util.logging.Logger;
import material.tree.binarytree.linkedbinarytree.BTNode;

/** Nested class for the nodes of a red-black tree */
public class RBNode<E> extends BTNode<E> {
	/**
     *
     */
    protected boolean isRed; // we add a color field to a BTNode

	/** Preferred constructor */
	RBNode(E element, BTNode<E> parent,
			BTNode<E> left, BTNode<E> right) {
		super(element, parent, left, right);
		isRed = false;
	}

	/**
     *
     * @return
     */
    public boolean isRed() {
		return isRed;
	}

	/**
     *
     */
    public void makeRed() {  
		isRed = true;
	}

	/**
     *
     */
    public void makeBlack() {
		isRed = false;
	}

	/**
     *
     * @param color
     */
    public void setColor(boolean color) {
		isRed = color;
	}
    private static final Logger LOG = Logger.getLogger(RBNode.class.getName());
}
