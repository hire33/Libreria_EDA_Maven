package material.tree.binarysearchtree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import material.tree.Position;
import material.tree.binarytree.linkedbinarytree.BTNode;
import material.tree.binarytree.linkedbinarytree.FactoryNode;
import material.tree.binarytree.linkedbinarytree.LinkedBinaryTree;
import material.tree.exceptions.BoundaryViolationException;
import material.tree.exceptions.InvalidPositionException;

public class BinarySearchTree <E> {

    //Atributos.
    
    public LinkedBinaryTree<E> binTree;
    protected Comparator<E> insertComparator; // comparator
    protected Comparator<E> findComparator; // comparator
    protected FactoryNode<E> nodeFactory;
    protected int size = 0; // number of entries

    //Constructores.
    
    /**
     * Creates a BinarySearchTree with a default comparator.
     */
    public BinarySearchTree(FactoryNode<E> nodeFactory) {
        this(new DefaultComparator(), new DefaultComparator(), nodeFactory);
    }

    /**
     * Creates a BinarySearchTree with the given comparator.
     */
    public BinarySearchTree(Comparator<E> c1, Comparator<E> c2, FactoryNode<E> nodeFactory) {
        if (c1 == null) {
            this.insertComparator = new ODInsertComparator<>(); 
        } else {
            this.insertComparator = c1;
        }
        if (c2 == null) {
            this.findComparator = new ODFindComparator<>(); 
        } else {
            this.findComparator = c2;
        }
        this.nodeFactory = nodeFactory; 
        this.binTree = new LinkedBinaryTree<>(nodeFactory);
        this.binTree.addRoot(null);
    }

    //Métodos relacionados con diccionarios.
    
    /**
     * Returns the number of elements in the tree.
     */
    public int size() {
        return size;
    }

    /**
     * Returns whether the tree is empty.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns a node containing the given value. Returns null if no such value
     * exists.
     */
    public Position<E> find(E value) {
        Position<E> curPos = treeSearch(value, this.binTree.root(), findComparator);
        if (this.binTree.isInternal(curPos)) {
            return curPos;
        }
        return null;
    }

    /**
     * Returns an iterable collection of all the nodes containing the given
     * value.
     */
    public Iterable<Position<E>> findAll(E value) {
        List<Position<E>> l = new ArrayList<>();
        addAll(l, this.binTree.root(), value);
        return l;
    }
    
    public Iterable<Position<E>> findRange(E minValue, E maxValue){
        List<Position<E>> l = new ArrayList<>();
        List<Position<E>> todas = (List<Position<E>>) this.positions();
        for(Position<E> pos: todas){
            E curValue = pos.element();
            int compMin = findComparator.compare(curValue, minValue);
            int compMax = findComparator.compare(curValue, maxValue);
            boolean overMin = ((compMin==0)||(compMin==1));
            boolean underMax = ((compMax==0)||(compMax==-1));
            if( overMin && underMax ){
                l.add(pos);
            }      
        }
        return l;  
    }
            
    /**
     * Inserts an element into the tree and returns the newly created node.
     */
    public Position<E> insert(E value) {
//        Position<E> insertPos = treeSearch(value, this.binTree.root(), insertComparator);
//        Position<E> newPos = insertAtLeaf(insertPos, value);
//        return newPos;
        Position<E> a = this.treeSearch(value, binTree.root(), insertComparator);
        while(!this.binTree.isLeaf(a)){
            a = this.treeSearch(value, this.binTree.right(a), insertComparator);
        }
        return this.insertAtLeaf(a, value);
    }

    /**
     * Removes and returns a given node.
     */
    public E remove(Position<E> pos) throws InvalidPositionException {
        this.checkPosition(pos); // may throw an InvalidPositionException
        E toReturn = pos.element(); // value to be returned        
        Position<E> remPos = getLeafToRemove(pos);
        removeLeaf(remPos);
        return toReturn;
    }

    /**
     * Returns an iterator containing all elements in the tree.
     */
    public Iterable<E> values() {
        List<E> entries = new ArrayList<>();
        Iterable<Position<E>> positer = this.binTree.positions();
        for (Position<E> cur : positer) {
            if (this.binTree.isInternal(cur)) {
                entries.add(cur.element());
            }
        }
        return entries;
    }

    //Métodos adicionales.
    
    /**
     * Returns an iterator containing all nodes in the tree.
     */
    public Iterable<Position<E>> positions() {
        
        Iterable<Position<E>> positer = this.binTree.positions();

        //Limpia las hojas.
        
        Iterator<Position<E>> it = positer.iterator();
        while (it.hasNext()) {
            Position<E> cur = it.next();
            if (!this.binTree.isInternal(cur)) {
                it.remove();
            }
        }
        return positer;
    
    }

    private Position<E> succesor(Position<E> pos) throws InvalidPositionException {       
        BTNode<E> root = this.checkPosition(pos);
        if (root.getRight() != null) {
            return this.minimun(root.getRight());
        }
        BTNode<E> aux = root.getParent();
        while ((aux != null) && (root == aux.getRight())) {
            root = aux;
            aux = aux.getParent();
        }
        return aux;
    }

    private Position<E> minimun(Position<E> pos) throws InvalidPositionException {
        BTNode<E> root = this.checkPosition(pos);
        while (root.getLeft() != null) {
            root = root.getLeft();
        }
        return root.getParent();
    }

    /**
     * Returns a leaf node to remove. left(pos)==leaf_node --> return left(pos)
     * right(pos)==leaf_node --> return right(pos) Other cases: lookfor
     * succ(pos), replaceElement and return left(succ(pos))
     */
    protected Position<E> getLeafToRemove(Position<E> pos) {

        if (this.binTree.isLeaf(binTree.left(pos))) {
            return this.binTree.left(pos);
        }
        if (this.binTree.isLeaf(binTree.right(pos))) {
            return this.binTree.right(pos);
        }
        Position<E> sucesor = this.succesor(pos);
        this.replaceValue(pos, sucesor.element());       
        return this.binTree.left(sucesor);

    }

    /**
     * Extracts the element stored at a given node of the tree.
     */
    protected E value(Position<E> position) {
        return position.element();
    }

    /**
     * Replaces the element at a node.
     */
    protected void replaceValue(Position<E> pos, E ent) {
        this.binTree.replace(pos, ent);
    }

    /**
     * Expand an external node into an internal node with two external node
     * children
     */
    protected void expandLeaf(Position<E> p, E e1, E e2)
            throws InvalidPositionException {
        if (!this.binTree.isLeaf(p)) {
            throw new InvalidPositionException("Node is not external");
        }
        this.binTree.insertLeft(p, e1);
        this.binTree.insertRight(p, e2);
    }

    /**
     * Remove an external node p and replace its parent with p's sibling
     */
    protected void removeAboveLeaf(Position<E> p)
            throws InvalidPositionException {

        Position<E> u = this.binTree.parent(p);
        
        //Método remove de la clase LinkedBinaryTree.
        
        this.binTree.remove(p);
        this.binTree.remove(u);

    }

    /**
     * Auxiliary method for inserting a value at an external node
     */
    protected Position<E> insertAtLeaf(Position<E> pos, E value) {
        this.expandLeaf(pos, null, null);
        this.binTree.replace(pos, value);
        size++;
        return pos;
    }

    /**
     * Auxiliary method for removing an external node and its parent
     */
    protected void removeLeaf(Position<E> v) {
        removeAboveLeaf(v);
        size--;
    }

    /**
     * Auxiliary method used by find, insert, and remove.
     */
    protected Position<E> treeSearch(E value, Position<E> pos, Comparator<E> comparator)
            throws InvalidPositionException, BoundaryViolationException {
        if (this.binTree.isLeaf(pos)) {
            return pos;
        } // key not found; return external node
        else {
            E curValue = pos.element();
            int comp = comparator.compare(value, curValue);
            if (comp < 0) {
                return treeSearch(value, this.binTree.left(pos), comparator);
            } // search left
            // subtree
            else if (comp > 0) {
                return treeSearch(value, this.binTree.right(pos), comparator);
            } // search
            // right
            // subtree
            
            //Solo para buscar, falla en insertar para elementos repetidos.
            
            return pos; // return internal node where key is found
        
        }
    }

    /**
     * Adds to L all values in the subtree rooted at pos having elements equal
     * to value.
     */
    protected void addAll(List<Position<E>> l, Position<E> pos, E value) {
        if (this.binTree.isLeaf(pos)) {
            return;
        }
        Position<E> p = treeSearch(value, pos, findComparator);
        if (!this.binTree.isLeaf(p)) { // we found an entry with key equal to k
            addAll(l, this.binTree.left(p), value);
            l.add(p); // add entries in inorder
            addAll(l, this.binTree.right(p), value);
        } // this recursive algorithm is simple, but it's not the fastest
    }

    /**
     * If p is a good tree node, cast to Position, else throw exception.
     */
    public BTNode<E> checkPosition(Position<E> p)
            throws InvalidPositionException {
        if (p == null || !(p instanceof BTNode)) {
            throw new InvalidPositionException(
                    "The position of the BTNode in the BST is not valid");
        }
        return (BTNode<E>) p;
    }

//    private Position<E> getPosToBegin(E minValue, Comparator<E> comparator) {
//        
//        Position <E> actual = this.minimun(this.binTree.root());
//        comprobarPosToBegin(actual);
//        E curValue = actual.element();
//        int comp = comparator.compare(curValue, minValue);
//        if(comp==0||comp==1){
//            return actual;
//        } else {
//            comprobarPosToBegin(this.succesor(actual));
//        }        
//    }
//
//    private void comprobarPosToBegin(Position<E> succesor) {
//        throw new UnsupportedOperationException("Not yet implemented");
//    }
}
