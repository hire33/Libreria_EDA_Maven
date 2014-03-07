package material.tree.linkedtree;

import java.util.List;
import material.tree.Position;

public class TreeNode<E> implements Position<E> {
	
    //Atributos.
    
    private E element; // element stored at this node
    private TreeNode<E> parent; // adjacent node
    private List<TreeNode<E>> children; // children nodes

    //Constructores.
    
    /**
     * Main constructor
     */
    public TreeNode(E element, TreeNode<E> parent, List<TreeNode<E>> children) {

        setElement(element);
        setParent(parent);
        setChildren(children);

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
    public void setElement(E o) {
        element = o;
    }

    /**
     * Returns the children of this position
     */
    public List<TreeNode<E>> getChildren() {
        return children;
    }

    /**
     * Sets the right child of this position
     */
    public void setChildren(List<TreeNode<E>> c) {
        children = c;
    }

    /**
     * Returns the parent of this position
     */
    public TreeNode<E> getParent() {
        return parent;
    }

    /**
     * Sets the parent of this position
     */
    public void setParent(TreeNode<E> v) {
        parent = v;
    }
        
}

