package material.tree.LCRSTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import material.tree.Position;
import material.tree.Tree;
import material.tree.exceptions.BoundaryViolationException;
import material.tree.exceptions.EmptyTreeException;
import material.tree.exceptions.InvalidPositionException;
import material.tree.exceptions.NonEmptyTreeException;

public class LCRSTree <E> implements Tree <E> {
    
    //Atributos.
    
    protected LCRSNode <E> root;
    protected int size;
    
    //Constructores.
    
    /**
     * Creates an empty tree.
     */
    public LCRSTree() {
        this.root = null; // start with an empty tree
        this.size = 0;
    }
    
    //Métodos.

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return (this.size==0);
    }
    
    @Override
    public Iterator<E> iterator() {
        Iterable<Position<E>> positions = this.positions();
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
            preorderPositions((Position<E>) this.root(), positions);
        } // assign positions in
        // preorder
        return positions;
    }
    
    @Override
    public E replace(Position<E> v, E e) throws InvalidPositionException {
        LCRSNode<E> node = checkPosition(v);
        E temp = v.element();
        node.setElement(e);
        return temp;
    }
    
    @Override
    public Position<E> root() throws EmptyTreeException {
        if (this.root == null) {
            throw new EmptyTreeException("The tree is empty");
        }
        return this.root;
    }

    @Override
    public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
        LCRSNode<E> node = this.checkPosition(v);
        Position<E> parentPos = node.getParent();
        if (parentPos == null) {
            throw new BoundaryViolationException("No parent");
        }
        return parentPos;
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) throws InvalidPositionException {
        LCRSNode<E> node = this.checkPosition(v);
        LCRSNode<E> actualChild = node.getLeftChild();
        List<LCRSNode<E>> siblings = new ArrayList <> ();
        if(actualChild==null){
            return siblings;
        }
        while (actualChild.getRightSibling()!=null){
            siblings.add(actualChild);
            actualChild=actualChild.getRightSibling();
        }
        if(actualChild!=null){
            siblings.add(actualChild);
        }
        return siblings;
    }
    
    @Override
    public boolean isInternal(Position<E> v) throws InvalidPositionException {
        return !this.isLeaf(v);
    }

    @Override
    public boolean isLeaf(Position<E> v) throws InvalidPositionException {
        LCRSNode<E> node = this.checkPosition(v); 
        return (node.getLeftChild()==null);
    }

    @Override
    public boolean isRoot(Position<E> v) throws InvalidPositionException {
        this.checkPosition(v);
        return ((LCRSNode<E>) v == this.root());
    }
    
    //Métodos adicionales.
    
    public Position<E> addRoot(E e) throws NonEmptyTreeException {
        if (!this.isEmpty()) {
            throw new NonEmptyTreeException("Tree already has a root");
        }
        this.size = 1;
        this.root = createNode(e, null, null, null);
        return this.root;
    }

    public Position<E> add(Position<E> posParent, E element)
            throws InvalidPositionException {
        LCRSNode<E> parent = this.checkPosition(posParent);
        LCRSNode<E> newNode = this.createNode(element, null, null, null);
        if (parent.getLeftChild() == null) {
            parent.setLeftChild(newNode);
        } else {
            List<Position<E>> children = (List<Position<E>>) this.children(posParent);
            Position<E> l = children.get(children.size()-1);
            LCRSNode<E> lastChild = this.checkPosition(l);
            lastChild.setRightSibling(newNode);
        } 
        newNode.setParent(parent);
        this.size++;
        return newNode;
    }
    
        /**
     * Swap the elements at two nodes
     */
    public void swapElements(Position<E> p1, Position<E> p2)
            throws InvalidPositionException {
        LCRSNode <E> node1 = this.checkPosition(p1);
        LCRSNode <E> node2 = this.checkPosition(p2);
        E aux = node1.element();
        node1.setElement(node2.element());
        node2.setElement(aux);
    }

    /**
     * Remove an element in a given position.
     */
    public E remove(Position<E> p) throws InvalidPositionException {
        LCRSNode<E> node = this.checkPosition(p);
        E elem = node.element();
        if(this.isRoot(p)){
            this.root=null;
            this.size=0;
            return elem;
        }
        int tamañoSubArbol = this.nodesInTree(p);
        LCRSNode<E> parent = node.getParent();
        if(parent.getLeftChild()==node){
            LCRSNode<E> secondChild = node.getRightSibling();
            parent.setLeftChild(secondChild);
        }else{
            LCRSNode<E> leftSibling = this.leftSibling(node);
            leftSibling.setRightSibling(node.getRightSibling());
        }
        this.size-=tamañoSubArbol;
        return elem;
    }
    
    //Métodos auxiliares.
    
    public LCRSNode<E> checkPosition(Position<E> p)
            throws InvalidPositionException {
        if (p == null || !(p instanceof LCRSNode)) {
            throw new InvalidPositionException("The position is invalid");
        }

        LCRSNode<E> node = (LCRSNode<E>) p;

        if ((node.getParent() == null) && (this.root != node)) {
            throw new InvalidPositionException("The position was removed");
        }

        return node;

    }
    
    protected LCRSNode<E> createNode(E element, LCRSNode<E> parent, LCRSNode<E> rS,
            LCRSNode<E> lC) {
        return new LCRSNode<>(element, parent, rS, lC);
    }
    
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
        
    private LCRSNode<E> leftSibling(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
        LCRSNode<E> child = this.checkPosition(v);
        LCRSNode<E> parent = child.getParent();
        if(parent==null){
            throw new BoundaryViolationException("No parent");
        }
        List<Position<E>> children = (List<Position<E>>) this.children(parent);
        Iterator<Position<E>> it = children.iterator();
        Position<E> aux = null;
        boolean condicion = false;
        while(it.hasNext() && !condicion){
            Position<E> actualElement = it.next();
            LCRSNode<E> aE = this.checkPosition(actualElement);
            condicion=(aE.getRightSibling()==v); 
            if(condicion){
                aux = actualElement;
            }
        }
        if(aux==null){
            throw new BoundaryViolationException("No left sibling");
        }
        LCRSNode<E> leftChild = this.checkPosition(aux);
        return leftChild;  
    }

}
