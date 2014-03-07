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
public class RankedArrayBinaryTree<E> implements BinaryTree<E> {

    //Atributos.
    
    /**
     *
     */
    protected ArrayList<BTPos<E>> tree; // indexed list of tree positions
    /**
     *
     */
    protected int size;

    //Constructores.
    
    /**
     *
     */
    public RankedArrayBinaryTree() {
        this.tree = new ArrayList<>();
        this.size = 0;
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
        return tree.get(0);
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
        int newArrayIndex = arrayPos(1);
        BTPos<E> r = createNode(e, newArrayIndex);
        tree.add(r);
        return tree.get(arrayPos(1));
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
        BTPos<E> parent = checkPosition(p);
        int leftPos = parent.getLeft();
        if (leftPos != -1) {
            throw new InvalidPositionException("Node already has a left child");
        }
        int newIndex = rank(parent.index())*2;
        if(newIndex>tree.size()){
            expandTree(tree);
        }
        int newArrayIndex = arrayPos(newIndex);
        BTPos<E> w = createNode(e, newArrayIndex);
        w.setParent(parent.index()); 
        parent.setLeft(newArrayIndex);
        tree.set(newArrayIndex, w);
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
        int newIndex = rank(parent.index())*2+1;
        if(newIndex>tree.size()){
            expandTree(tree);
        }
        int newArrayIndex = arrayPos(newIndex);
        BTPos<E> ww = createNode(e, newArrayIndex);
        ww.setParent(parent.index());
        parent.setRight(newArrayIndex);    
        tree.set(newArrayIndex, ww);
        size++;
        return ww;
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

    private int arrayPos (int rank) {
        rank-=1;
        return rank;
    }
    
    private int rank (int arrayPos) {
        arrayPos+=1;
        return arrayPos;
    }

    private void expandTree(ArrayList<BTPos<E>> tree) {
        
        int contador=tree.size()+1;
         
        for(int i=0; i<contador; i++){
            //Nodos basura.
            tree.add(createNode(null,-1));
        }
    
    }

    //Posible extensión de la clase.
    
//    /**
//     * Attaches two trees to be subtrees of a leaf node.
//     */
//    public void attach(Position<E> p, BinaryTree<E> t1, BinaryTree<E> t2)
//            throws InvalidPositionException {
//        
//        //Esta claro que ninguno de los dos subárboles puede estar vacío.
//        
//        if (!isLeaf(p)) {
//            throw new InvalidPositionException("Node is not leaf");
//        }
//        expandLeft(p, t1.root());
//        expandRight(p, t2.root());  
//    }
//    
//    /**
//     * Removes a node with zero or one child.
//     */
//    public E remove(Position<E> p) throws InvalidPositionException {
//        BTNode<E> node = this.checkPosition(p);
//        BTNode<E> leftChild = node.getLeft();
//        BTNode<E> rigthChild = node.getRight();
//        if((leftChild!=null) && (rigthChild!=null)){
//            throw new InvalidPositionException("Cannot remove node with two children");
//        }
//        BTNode<E> child;
//        if(leftChild!=null){
//            child = leftChild;
//        } else if (rigthChild!=null){
//            child = rigthChild;
//        } else {
//            child = null;
//        }
//        if (node==root) {
//            this.root=child;
//        } else {
//            BTNode<E> parent = node.getParent();
//            if(child!=null){
//                child.setParent(parent);
//            }
//            if(parent.getLeft()==node){
//                parent.setLeft(child);
//            } else {
//                parent.setRight(child);
//            }    
//        }
//        this.size--;
//        return node.element();
//    }
//    
//    private void expandLeft(Position<E> p, Position<E> root) {
//        BTPos<E> leftRoot = this.checkPosition(root);
//        Position<E> newRoot = insertLeft(p, leftRoot.element());
//        if(this.hasLeft(root)){
//            Position<E> leftChild = this.left(root);
//            expandLeft(newRoot, leftChild);
//        }
//        if(this.hasRight(root)){
//            Position<E> rightChild = this.right(root);
//            expandRight(newRoot, rightChild);
//        }  
//    }
//
//    private void expandRight(Position<E> p, Position<E> root) {
//        BTPos<E> rightRoot = this.checkPosition(root);
//        Position<E> newRoot = insertRight(p, rightRoot.element());
//        if(this.hasLeft(root)){
//            Position<E> leftChild = this.left(root);
//            expandLeft(newRoot, leftChild);
//        }
//        if(this.hasRight(root)){
//            Position<E> rightChild = this.right(root);
//            expandRight(newRoot, rightChild);
//        }
//    }

}
