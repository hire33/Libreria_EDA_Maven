package material.tree.binarytree.arraybinarytree;

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
public class ArrayBinaryTree<E> implements BinaryTree<E> {

    //Atributos.
    
    /**
     *
     */
    protected ArrayList<BTPos<E>> tree; // indexed list of tree positions
    /**
     *
     */
    protected int size;
    /**
     *
     */
    protected int currentRoot; //Util para el método remove.

    //Constructores.
    
    /**
     *
     */
    public ArrayBinaryTree() {
        this.tree = new ArrayList<>();
        this.size = 0;
        this.currentRoot = 0;
    }

    //Métodos heredados de Tree.
    
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }
    
    @Override
    public Iterator<E> iterator() {
        Iterable<Position<E>> positions = positions();
        List<E> elements = new ArrayList<>();
        for (Position<E> pos : positions) {
            elements.add(pos.element());
        }
        return elements.iterator(); // An iterator of elements
    }
    
    @Override
    public Iterable<Position<E>> positions() {
        List<Position<E>> positions = new ArrayList<>();
        if (size != 0) {
            preorderPositions(root(), positions);
        }
        return positions;
    }
    
    @Override
    public E replace(Position<E> v, E e) throws InvalidPositionException {
        BTPos<E> node = checkPosition(v);
        E temp = v.element();
        node.setElement(e);
        return temp;
    }
    
    @Override
    public Position<E> root() throws EmptyTreeException {
        if (tree.isEmpty()) {
            throw new EmptyTreeException("The tree is empty");
        }
        return tree.get(currentRoot);
    }
    
    @Override
    public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
        BTPos<E> node = this.checkPosition(v);
        int parent = node.getParent();
        if (parent == -1) {
            throw new BoundaryViolationException("No parent");
        }
        return tree.get(parent);
    }
    
    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) throws InvalidPositionException {
        List<Position<E>> children = new ArrayList<>();
        if (hasLeft(v)) {
            children.add(left(v));
        }
        if (hasRight(v)) {
            children.add(right(v));
        }
        return children;
    }
    
    @Override
    public boolean isInternal(Position<E> v) throws InvalidPositionException {
        checkPosition(v); // auxiliary method
        return (hasLeft(v) || hasRight(v));
    }
    
    @Override
    public boolean isLeaf(Position<E> v) throws InvalidPositionException {
        return !isInternal(v);
    }
    
    @Override
    public boolean isRoot(Position<E> v) throws InvalidPositionException {
        checkPosition(v);
        return (v == root());
    }
    
    //Métodos heredados de BinaryTree.
    
    @Override
    public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
        BTPos<E> node = checkPosition(v);
        int leftPos = node.getLeft();
        if (leftPos == -1) {
            throw new BoundaryViolationException("No left child");
        }
        return tree.get(leftPos);
    }
    
    @Override
    public Position<E> right(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
        BTPos<E> node = checkPosition(v);
        int rightPos = node.getRight();
        if (rightPos == -1) {
            throw new BoundaryViolationException("No right child");
        }
        return tree.get(rightPos);
    }
    
    @Override
    public boolean hasLeft(Position<E> v) throws InvalidPositionException {
        BTPos<E> node = checkPosition(v);
        return (node.getLeft() != -1);
    }
    
    @Override
    public boolean hasRight(Position<E> v) throws InvalidPositionException {
        BTPos<E> node = checkPosition(v);
        return (node.getRight() != -1);
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
        BTPos<E> node = this.checkPosition(p);
        BTPos<E> parent = tree.get(node.getParent());
        int siblingPos;
        if(parent.getLeft()==node.index()){
            siblingPos = parent.getRight();
        } else {
            siblingPos = parent.getLeft();
        }
        if(siblingPos==-1){
            throw new BoundaryViolationException ("The node has no sibling");
        }
        BTPos<E> child = tree.get(siblingPos);
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
        BTPos<E> r = createNode(e, 0);
        tree.add(r);
        return tree.get(0);
    }
        
    /**
     *
     * @param p
     * @param e
     * @return
     * @throws InvalidPositionException
     */
    public Position<E> insertLeft(Position<E> p, E e)
            throws InvalidPositionException {
        BTPos<E> parent = this.checkPosition(p);
        int leftPos = parent.getLeft();
        if (leftPos != -1) {
            throw new InvalidPositionException("Node already has a left child");
        }
        BTPos<E> w = createNode(e, this.size());
        w.setParent(parent.index());
        parent.setLeft(w.index());
        tree.add(w);
        size++;
        return w;
    }
    
    /**
     *
     * @param p
     * @param e
     * @return
     * @throws InvalidPositionException
     */
    public Position<E> insertRight(Position<E> p, E e)
            throws InvalidPositionException {
        BTPos<E> parent = checkPosition(p);
        int rightPos = parent.getRight();
        if (rightPos != -1) {
            throw new InvalidPositionException("Node already has a right child");
        }
        BTPos<E> ww = createNode(e, this.size());
        ww.setParent(parent.index());
        parent.setRight(ww.index());
        tree.add(ww);
        size++;
        return ww;
    }
    
    /**
     * Swap the elements at two nodes
     */
    public void swapElements(Position<E> p1, Position<E> p2)
            throws InvalidPositionException {
        BTPos<E> node1 = checkPosition(p1);
        BTPos<E> node2 = checkPosition(p2);
        E temp = p2.element();
        node2.setElement(p1.element());
        node1.setElement(temp);
    }
    
    /**
     * Removes a node with zero or one child.
     */
    public E remove(Position<E> p) throws InvalidPositionException {
        
        /** 
         * El algoritmo para recolocar los indices en caso de que se realice
         * un borrado por el principio o por el medio es demasiado complejo.
         */
        
        BTPos<E> node = this.checkPosition(p);
        int leftChild = node.getLeft();
        int rigthChild = node.getRight();
        if((leftChild!=-1) && (rigthChild!=-1)){
            throw new InvalidPositionException("Cannot remove node with two children");
        }
        int child;
        if(leftChild!=-1){
            child = leftChild;
        } else if (rigthChild!=-1){
            child = rigthChild;
        } else {
            child = -1;
        }
        E elem = node.element();
        if (node==root()) {
            if(this.isLeaf(p)){
                tree.clear(); //Eliminamos los nodos basura.
            } else {
                this.currentRoot=child;
                BTPos<E> childPos = tree.get(child);
                childPos.setParent(-1);
            }
        } else {
            int posParent = node.getParent();
            BTPos<E> parent = tree.get(posParent);
            if(child!=-1){
                BTPos<E> childPos = tree.get(child);
                childPos.setParent(posParent);
            }
            if(parent.getLeft()==node.index()){
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }    
        }
        this.size--;
        if(!this.isEmpty()){
            BTPos<E> garbage = this.createNode(null, -1);
            tree.set(node.index(), garbage);
            if(node.index()==tree.size()-1){
                tree.remove(node.index());
            }
        }  
        return elem;
    }
    
    /**
     * Attaches two trees to be subtrees of a leaf node.
     */
    public void attach(Position<E> p, BinaryTree<E> t1, BinaryTree<E> t2)
            throws InvalidPositionException {
        if (!isLeaf(p)) {
            throw new InvalidPositionException("Node is not leaf");
        }
        attachLeft(p, t1);
        attachRight(p, t2);  
    }
        
    // Auxiliary methods
    
    /**
     *
     * @param p
     * @return
     * @throws InvalidPositionException
     */
    protected BTPos<E> checkPosition(Position<E> p)
            throws InvalidPositionException {
        if (p == null || !(p instanceof BTPos)) {
            throw new InvalidPositionException("The position is invalid");
        }
        return (BTPos<E>) p;
    }
    
    /**
     *
     * @param element
     * @param i
     * @return
     */
    protected BTPos<E> createNode(E element, int i) {
        return new BTPos<>(element, i);
    }
    
    /**
     *
     * @param v
     * @param pos
     * @throws InvalidPositionException
     */
    protected void preorderPositions(Position<E> v, List<Position<E>> pos)
            throws InvalidPositionException {
        pos.add(v);
        if (hasLeft(v)) {
            preorderPositions(left(v), pos); // recurse on left child
        }
        if (hasRight(v)) {
            preorderPositions(right(v), pos); // recurse on right child
        }
    }
    
    private void attachLeft(Position<E> p, BinaryTree<E> subTree) {
        attachLeftAux(p, subTree, subTree.root());
    }
    
    private void attachLeftAux(Position<E> p, BinaryTree<E> subTree, Position<E> currentPos) {
        BTPos<E> leftRoot = this.checkPosition(currentPos);
        Position<E> newRoot = insertLeft(p, leftRoot.element());
        if(subTree.hasLeft(currentPos)){
            attachLeftAux(newRoot, subTree, subTree.left(currentPos));
        }
        if(subTree.hasRight(currentPos)){
            attachRightAux(newRoot, subTree, subTree.right(currentPos));
        }  
    }

    private void attachRight(Position<E> p, BinaryTree<E> subTree) {
        attachRightAux(p, subTree, subTree.root());
    }
    
    private void attachRightAux(Position<E> p, BinaryTree<E> subTree, Position<E> currentPos) {
        BTPos<E> rightRoot = this.checkPosition(currentPos);
        Position<E> newRoot = insertRight(p, rightRoot.element());
        if(subTree.hasLeft(currentPos)){
            attachLeftAux(newRoot, subTree, subTree.left(currentPos));
        }
        if(subTree.hasRight(currentPos)){
            attachRightAux(newRoot, subTree, subTree.right(currentPos));
        }
    }

}
