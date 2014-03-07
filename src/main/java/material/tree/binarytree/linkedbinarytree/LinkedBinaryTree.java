package material.tree.binarytree.linkedbinarytree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import material.tree.Position;
import material.tree.binarytree.BinaryTree;
import material.tree.exceptions.BoundaryViolationException;
import material.tree.exceptions.EmptyTreeException;
import material.tree.exceptions.InvalidPositionException;
import material.tree.exceptions.NonEmptyTreeException;

/**
 *
 * @author Asus
 * @param <E>
 */
public class LinkedBinaryTree<E> implements BinaryTree<E> {

    //Atributos.
    
    /**
     *
     */
    protected BTNode<E> root; // reference to the root
    /**
     *
     */
    protected int size; // number of nodes
    /**
     *
     */
    protected FactoryNode<E> nodeFactory;

    //Constructores.
    
    /**
     * Creates an empty binary tree.
     */
    public LinkedBinaryTree(FactoryNode<E> nodeFactory) {
        this.root = null; // start with an empty tree
        this.size = 0;
        this.nodeFactory = nodeFactory;
    }

    //Métodos heredados de Tree.
    
    /**
     * Returns the number of nodes in the tree.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns whether the tree is empty.
     */
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }
    
    /**
     * Returns an iterator of the elements stored at the nodes
     */
    @Override
    public Iterator<E> iterator() {
        Iterable<Position<E>> positions = positions();
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
            preorderPositions(root(), positions);
        }
        return positions;
    }
    
    /**
     * Replaces the element at a node.
     */
    @Override
    public E replace(Position<E> p, E e) throws InvalidPositionException {
        BTNode<E> node = checkPosition(p);
        E temp = p.element();
        node.setElement(e);
        return temp;
    }
    
    /**
     * Returns the root of the tree.
     */
    @Override
    public Position<E> root() throws EmptyTreeException {
        if (root == null) {
            throw new EmptyTreeException("The tree is empty");
        }
        return root;
    }

    /**
     * Returns the parent of a node.
     */
    @Override
    public Position<E> parent(Position<E> p) throws InvalidPositionException,
            BoundaryViolationException {
        BTNode<E> node = checkPosition(p);
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
    public Iterable<? extends Position<E>> children(Position<E> p)
            throws InvalidPositionException {
        List<Position<E>> children = new ArrayList<>();
        if (hasLeft(p)) {
            children.add(left(p));
        }
        if (hasRight(p)) {
            children.add(right(p));
        }
        return children;
    }

    /**
     * Returns whether a node is internal.
     */
    @Override
    public boolean isInternal(Position<E> v) throws InvalidPositionException {
        checkPosition(v); // auxiliary method
        return (hasLeft(v) || hasRight(v));
    }

    /**
     * Returns whether a node is external.
     */
    @Override
    public boolean isLeaf(Position<E> p) throws InvalidPositionException {
        return !isInternal(p);
    }

    /**
     * Returns whether a node is the root.
     */
    @Override
    public boolean isRoot(Position<E> p) throws InvalidPositionException {
        checkPosition(p);
        return (p == root());
    }
    
    //Métodos heredados de BinaryTree.
    
    /**
     * Returns the left child of a node.
     */
    @Override
    public Position<E> left(Position<E> p) throws InvalidPositionException,
            BoundaryViolationException {
        BTNode<E> node = checkPosition(p);
        Position<E> leftPos = node.getLeft();
        if (leftPos == null) {
            throw new BoundaryViolationException("No left child");
        }
        return leftPos;
    }

    /**
     * Returns the right child of a node.
     */
    @Override
    public Position<E> right(Position<E> p) throws InvalidPositionException,
            BoundaryViolationException {
        BTNode<E> node = checkPosition(p);
        Position<E> rightPos = node.getRight();
        if (rightPos == null) {
            throw new BoundaryViolationException("No right child");
        }
        return rightPos;
    }

    /**
     * Returns whether a node has a left child.
     */
    @Override
    public boolean hasLeft(Position<E> p) throws InvalidPositionException {
        BTNode<E> node = checkPosition(p);
        return (node.getLeft() != null);
    }

    /**
     * Returns whether a node has a right child.
     */
    @Override
    public boolean hasRight(Position<E> p) throws InvalidPositionException {
        BTNode<E> node = checkPosition(p);
        return (node.getRight() != null);
    }
    
    // Additional accessor method
    
    /**
     * Return the sibling of a node
     */
    public Position<E> sibling(Position<E> p) throws InvalidPositionException,
            BoundaryViolationException {
        if(this.isRoot(p)){
            throw new BoundaryViolationException ("A root has no sibling");
        }
        BTNode<E> node = this.checkPosition(p);
        BTNode<E> parent = node.getParent();
        BTNode<E> child;
        if(parent.getLeft()==node){
            child = parent.getRight();
        } else {
            child = parent.getLeft();
        }
        if(child==null){
            throw new BoundaryViolationException ("The node has no sibling");
        }
        return (Position<E>) child;
    }

    // Additional update methods
    
    /**
     * Adds a root node to an empty tree
     */
    public Position<E> addRoot(E e) throws NonEmptyTreeException {
        if (!isEmpty()) {
            throw new NonEmptyTreeException("Tree already has a root");
        }
        size = 1;
        root = nodeFactory.createNode(e, null, null, null);
        return root;
    }

    /**
     * Inserts a left child at a given node.
     */
    public Position<E> insertLeft(Position<E> p, E e)
            throws InvalidPositionException {
        BTNode<E> node = checkPosition(p);
        Position<E> leftPos = node.getLeft();
        if (leftPos != null) {
            throw new InvalidPositionException("Node already has a left child");
        }
        BTNode<E> ww = nodeFactory.createNode(e, node, null, null);
        node.setLeft(ww);
        size++;
        return ww;
    }

    /**
     * Inserts a right child at a given node.
     */
    public Position<E> insertRight(Position<E> p, E e)
            throws InvalidPositionException {
        BTNode<E> node = checkPosition(p);
        Position<E> rightPos = node.getRight();
        if (rightPos != null) {
            throw new InvalidPositionException("Node already has a right child");
        }
        BTNode<E> w = nodeFactory.createNode(e, node, null, null);
        node.setRight(w);
        size++;
        return w;
    }

    /**
     * Removes a node with zero or one child.
     */
    public E remove(Position<E> p) throws InvalidPositionException {
        BTNode<E> node = this.checkPosition(p);
        BTNode<E> leftChild = node.getLeft();
        BTNode<E> rigthChild = node.getRight();
        if((leftChild!=null) && (rigthChild!=null)){
            throw new InvalidPositionException("Cannot remove node with two children");
        }
        BTNode<E> child;
        if(leftChild!=null){
            child = leftChild;
        } else if (rigthChild!=null){
            child = rigthChild;
        } else {
            child = null;
        }
        if (node==root) {
            this.root=child;
        } else {
            BTNode<E> parent = node.getParent();
            if(child!=null){
                child.setParent(parent);
            }
            if(parent.getLeft()==node){
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }    
        }
        this.size--;
        return node.element();
    }

    /**
     * Swap the elements at two nodes
     */
    public void swapElements(Position<E> p1, Position<E> p2)
            throws InvalidPositionException {
        BTNode<E> node1 = checkPosition(p1);
        BTNode<E> node2 = checkPosition(p2);
        E temp = p2.element();
        node2.setElement(p1.element());
        node1.setElement(temp);
    }

    /**
     * Attaches two trees to be subtrees of a leaf node.
     */
    public void attach(Position<E> p, BinaryTree<E> t1, BinaryTree<E> t2)
            throws InvalidPositionException {
        
        //Esta claro que ninguno de los dos subárboles puede estar vacío.
        
        if (!isLeaf(p)) {
            throw new InvalidPositionException("Node is not leaf");
        }
        BTNode<E> node = this.checkPosition(p);
        BTNode<E> leftRoot = this.checkPosition(t1.root());
        BTNode<E> rigthRoot = this.checkPosition(t2.root());
        leftRoot.setParent(node);
        rigthRoot.setParent(node);
        node.setLeft(leftRoot);
        node.setRight(rigthRoot);
        List<Position<E>> recLeft = (List<Position<E>>) t1.positions();
        List<Position<E>> recRight = (List<Position<E>>) t2.positions();
        int sizeLeft = recLeft.size();
        int sizeRight = recRight.size();
        this.size = size + sizeLeft + sizeRight;  
    }
    
    /**
     *
     * @param newRoot
     */
    public void changeRoot (Position<E> newRoot){
        this.root=this.checkPosition(newRoot);
    }

    /**
     * Expand an external node into an internal node with two external node
     * children
     */
    protected void expandLeaf(Position<E> p, E e1, E e2)
            throws InvalidPositionException {
        if (!isLeaf(p)) {
            throw new InvalidPositionException("Node is not external");
        }
        insertLeft(p, e1);
        insertRight(p, e2);
    }

    /**
     * Remove an external node v and replace its parent with v's sibling
     */
    protected void removeAboveLeaf(Position<E> p)
            throws InvalidPositionException {
        if (!isLeaf(p)) {
            throw new InvalidPositionException("Node is not external");
        }
        if (isRoot(p)) {
            remove(p);
        } else {
            Position<E> u = parent(p);
            remove(p);
            remove(u);
        }
    }

    // Auxiliary methods
    
    /**
     * If v is a good binary tree node, cast to BTPosition, else throw exception
     */
    protected BTNode<E> checkPosition(Position<E> p)
            throws InvalidPositionException {
        if (p == null || !(p instanceof BTNode)) {
            throw new InvalidPositionException("The position is invalid");
        }
        return (BTNode<E>) p;
    }

    /**
     * Creates a new binary tree node
     */
    protected BTNode<E> createNode(E element, BTNode<E> parent, BTNode<E> left,
            BTNode<E> right) {
        return new BTNode<>(element, parent, left, right);
    }

    /**
     * Creates a list storing the the nodes in the subtree of a node, ordered
     * according to the preorder traversal of the subtree.
     */
    public void preorderPositions(Position<E> v, List<Position<E>> pos)
            throws InvalidPositionException {
        pos.add(v);
        if (hasLeft(v)) {
            preorderPositions(left(v), pos); // recurse on left child
        }
        if (hasRight(v)) {
            preorderPositions(right(v), pos); // recurse on right child
        }
    }

    /**
     * Creates a list storing the the nodes in the subtree of a node, ordered
     * according to the inorder traversal of the subtree.
     */
    public void inorderPositions(Position<E> v, List<Position<E>> pos)
            throws InvalidPositionException {
        if (hasLeft(v)) {
            inorderPositions(left(v), pos); // recurse on left child
        }
        pos.add(v);
        if (hasRight(v)) {
            inorderPositions(right(v), pos); // recurse on right child
        }
    }

}
