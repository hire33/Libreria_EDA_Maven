package material.tree.linkedtree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import material.tree.Position;
import material.tree.Tree;
import material.tree.exceptions.BoundaryViolationException;
import material.tree.exceptions.EmptyTreeException;
import material.tree.exceptions.InvalidPositionException;
import material.tree.exceptions.NonEmptyTreeException;

public class LinkedTree<E> implements Tree<E> {
    
    //Atributos.
    
    protected TreeNode<E> root; // reference to the root
    protected int size; // number of nodes

    //Constructores.
    
    /**
     * Creates an empty tree.
     */
    public LinkedTree() {
        this.root = null; // start with an empty tree
        this.size = 0;
    }

    //Métodos heredados de Tree.
    
    /**
     * Returns the number of nodes in the tree.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns whether the tree is empty.
     */
    @Override
    public boolean isEmpty() {
        return (this.size == 0);
    }
    
    /**
     * Returns an iterator of the elements stored at the nodes
     */
    @Override
    public Iterator<E> iterator() {
        Iterable<Position<E>> positions = this.positions();
        List<E> elements = new ArrayList<>();
        for (Position<E> pos : positions) {
            elements.add(pos.element());
        }
        return elements.iterator(); // An iterator of elements
    }
    
    /**
     * Returns an iterable collection of the tree nodes.
     */
    @Override
    public Iterable<Position<E>> positions() {
        List<Position<E>> positions = new ArrayList<>();
        if (size != 0) {
            preorderPositions((Position<E>) this.root(), positions);
        } // assign positions in
        // preorder
        return positions;
    }
    
    /**
     * Replaces the element at a node.
     */
    @Override
    public E replace(Position<E> p, E e) throws InvalidPositionException {
        TreeNode<E> node = checkPosition(p);
        E temp = p.element();
        node.setElement(e);
        return temp;
    }
    
    /**
     * Returns the root of the tree.
     */
    @Override
    public Position<E> root() throws EmptyTreeException {
        if (this.root == null) {
            throw new EmptyTreeException("The tree is empty");
        }
        return this.root;
    }

    /**
     * Returns the parent of a node.
     */
    @Override
    public Position<E> parent(Position<E> p) throws InvalidPositionException,
            BoundaryViolationException {
        TreeNode<E> node = this.checkPosition(p);
        Position<E> parentPos = node.getParent();
        if (parentPos == null) {
            throw new BoundaryViolationException("No parent");
        }
        return parentPos;
    }

    /**
     * Returns an iterable collection of the children of a node.
     */
    @Override
    public Iterable<? extends Position<E>> children(Position<E> p) {
        TreeNode<E> node = this.checkPosition(p);
        return node.getChildren();
    }

    /**
     * Returns whether a node is internal.
     */
    @Override
    public boolean isInternal(Position<E> v) throws InvalidPositionException {
        return !this.isLeaf(v);
    }

    /**
     * Returns whether a node is external.
     */
    @Override
    public boolean isLeaf(Position<E> p) throws InvalidPositionException {
        TreeNode<E> node = this.checkPosition(p); // auxiliary method
        return node.getChildren().isEmpty();
    }

    /**
     * Returns whether a node is the root.
     */
    @Override
    public boolean isRoot(Position<E> p) throws InvalidPositionException {
        this.checkPosition(p);
        return ((TreeNode<E>) p == this.root());
    }

    //Métodos adicionales.
    
    /**
     * Adds a root node to an empty tree
     */
    public Position<E> addRoot(E e) throws NonEmptyTreeException {
        if (!this.isEmpty()) {
            throw new NonEmptyTreeException("Tree already has a root");
        }
        this.size = 1;
        this.root = createNode(e, null, new ArrayList<TreeNode<E>>());
        return this.root;
    }

    /**
     * Adds a new child to a given parent.
     */
    public Position<E> add(Position<E> posParent, E element)
            throws InvalidPositionException {
        TreeNode<E> parent = this.checkPosition(posParent);
        TreeNode<E> newNode = this.createNode(element, null, new ArrayList<TreeNode<E>> ());
        parent.getChildren().add(newNode);
        newNode.setParent(parent);
        this.size++;
        return newNode;
    }
    
    public Position<E> add(E element, Position<E> posParent)
        throws InvalidPositionException {
        return this.add(posParent, element);
    }

    /**
     * Swap the elements at two nodes
     */
    public void swapElements(Position<E> p1, Position<E> p2)
            throws InvalidPositionException {
        TreeNode <E> node1 = this.checkPosition(p1);
        TreeNode <E> node2 = this.checkPosition(p2);
        E aux = node1.element();
        node1.setElement(node2.element());
        node2.setElement(aux);
    }

    /**
     * Remove an element in a given position.
     */
    public E remove(Position<E> p) throws InvalidPositionException {
        TreeNode<E> node = this.checkPosition(p);
        E elem = node.element();
        if(this.isRoot(p)){
            this.root=null;
            this.size=0;
            return elem;
        }
        int tamañoSubArbol = this.nodesInTree(p);
        TreeNode<E> parent = node.getParent();
        parent.getChildren().remove(node);
        this.size-=tamañoSubArbol;
        return elem;
    }

    //Métodos auxiliares.
    
    /**
     * If v is a good tree node, cast to TreePosition, else throw exception
     */
    public TreeNode<E> checkPosition(Position<E> p)
            throws InvalidPositionException {
        if (p == null || !(p instanceof TreeNode)) {
            throw new InvalidPositionException("The position is invalid");
        }

        TreeNode<E> node = (TreeNode<E>) p;

        if ((node.getParent() == null) && (this.root != node)) {
            throw new InvalidPositionException("The position was removed");
        }

        return node;

    }

    /**
     * Creates a new tree node
     */
    protected TreeNode<E> createNode(E element, TreeNode<E> parent,
            List<TreeNode<E>> children) {
        return new TreeNode<>(element, parent, children);
    }

    /**
     * Creates a list storing the the nodes in the subtree of a node, ordered
     * according to the preorder traversal of the subtree.
     */
    public void preorderPositions(Position<E> p, List<Position<E>> pos)
            throws InvalidPositionException {
        pos.add(p);
        for (Position<E> w : children(p)) {
            preorderPositions(w, pos);
        } // recurse on each child
    }
    
    //Métodos creados por el autor.
    
    private int nodesInTree (Position<E> root) throws InvalidPositionException {
        this.checkPosition(root);
        List<Position<E>> rec = new ArrayList <> ();
        this.preorderPositions(root, rec);
        return rec.size();
    }

}
