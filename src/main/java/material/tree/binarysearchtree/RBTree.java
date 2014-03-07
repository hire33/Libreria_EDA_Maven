package material.tree.binarysearchtree;

import java.util.Comparator;
import java.util.logging.Logger;
import material.tree.Position;
import material.tree.exceptions.InvalidPositionException;

/**
 * Realization of a red-black Tree by extending a binary search tree.
 *
 * @author Michael Goodrich, Roberto Tamassia, Eric Zamore
 */
public class RBTree<E> extends BinarySearchTree<E> {

    //Constructores.
    
    /**
     *
     * @param nodeFactory
     */
    public RBTree(FactoryRBNode<E> nodeFactory) {
        super(nodeFactory);
    }

    /**
     *
     * @param c1
     * @param c2
     * @param nodeFactory
     */
    public RBTree(Comparator<E> c1, Comparator<E> c2, FactoryRBNode<E> nodeFactory) {
        super(c1, c2, nodeFactory);
    }

    //Métodos heredados de BST.
    
    /**
     * Inserts an element into the RBTree and returns the newly created
     * position.
     */
    @Override
    public Position<E> insert(E e) {
        Position<E> toReturn = super.insert(e);
        Position<E> posZ = toReturn; // start at the insertion position

        setRed(posZ);
        if (this.binTree.isRoot(posZ)) {
            setBlack(posZ);
        } else {
            remedyDoubleRed(posZ); // fix a double-red color violation
        }
        return toReturn;
    }

    /**
     * Remedies a double red violation at a given node caused by insertion.
     */
    protected void remedyDoubleRed(Position<E> posZ) {
        
        Position<E> posV;
        
        posV = this.binTree.parent(posZ);
        
        if(isPosRed(posV)){ //Comprobamos si existe de verdad el problema.
        
            Position<E> posU, posW;

            posU = this.binTree.parent(posV);
            posW = this.binTree.sibling(posV);   

            Restructurator<E> restructurator = new Restructurator<>(new FactoryRBNode<E>());

            if(!isPosRed(posW)) { //Caso 1: El tio del nodo insertado es negro.

                //Reestructuracion trinodo sobre el nodo insertado.

                posZ = restructurator.restructure(posZ, this);

                //Recoloreado del árbol.

                setBlack(posZ);
                setRed(this.binTree.left(posZ));
                setRed(this.binTree.right(posZ));

            } else { //Caso 2: El tio del nodo insertado es rojo.

                //Recoloreado del árbol.

                setBlack(posV);
                setBlack(posW);
                if(!this.binTree.isRoot(posU)){
                    setRed(posU);
                    
                    //Posible propagación del problema hacia la raíz.
                
                    remedyDoubleRed(posU); 
                    
                } 
                
            }
    
        }
    
    }

    /**
     * Removes and returns an entry from the dictionary.
     */
    @Override
    public E remove(Position<E> posV) throws InvalidPositionException {
        this.checkPosition(posV); // may throw an InvalidPositionException
        
        E toReturn = posV.element(); // entry to be returned
        Position<E> posW = getLeafToRemove(posV);
        Position <E> newPosV = this.binTree.parent(posW);
        boolean wasParentRed = isPosRed(newPosV);
        Position<E> posR = this.binTree.sibling(posW);
        removeLeaf(posW);

        /**
         * this.binTree.isRoot(posR) : Si v era la raíz del árbol y por tanto
         * el ultimo nodo en borrar, no aparece el problema del doble negro.
         */
        
        if (wasParentRed || isPosRed(posR) || this.binTree.isRoot(posR)) {
            setBlack(posR);
        } else {
            remedyDoubleBlack(posR);
        }

        return toReturn;
    }

    /**
     * Remedies a double black violation at a given node caused by removal.
     */
    protected void remedyDoubleBlack(Position<E> posR) {
                
        Position<E> posX, posY, posZ;
        boolean oldColor;
        posX = this.binTree.parent(posR);
        posY = this.binTree.sibling(posR);

        Restructurator<E> restructurator = new Restructurator<>(new FactoryRBNode<E>());

        if (!isPosRed(posY)) {
            posZ = redChild(posY);
            if (hasRedChild(posY)) { // Case 1: trinode restructuring
                oldColor = isPosRed(posX);
                posZ = restructurator.restructure(posZ, this);
                setColor(posZ, oldColor);
                setBlack(posR);
                setBlack(this.binTree.left(posZ));
                setBlack(this.binTree.right(posZ));
                return;
            }
            setBlack(posR);
            setRed(posY);
            if (!isPosRed(posX)) { // Case 2: recoloring
                if (!this.binTree.isRoot(posX)) {
                    remedyDoubleBlack(posX);
                }
                return;
            }
            setBlack(posX);
            return;
        } // Case 3: adjustment
        if (posY == this.binTree.right(posX)) {
            posZ = this.binTree.right(posY);
        } else {
            posZ = this.binTree.left(posY);
        }
        restructurator.restructure(posZ, this);
        setBlack(posY);
        setRed(posX);
        remedyDoubleBlack(posR);
    }

    /**
     * Returns whether a node is red.
     */
    protected boolean isPosRed(Position<E> position) {
        RBNode<E> node = (RBNode<E>) position;
        return node.isRed();
    }

    /**
     * Colors a node red.
     */
    protected void setRed(Position<E> position) {
        RBNode<E> node = (RBNode<E>) position;
        node.makeRed();
    }

    /**
     * Colors a node black.
     */
    protected void setBlack(Position<E> position) {
        RBNode<E> node = (RBNode<E>) position;
        node.makeBlack();
    }

    /**
     * Sets the color of a node.
     *
     * @param color <tt>true</tt> to color the node red, <tt>false</tt> to color
     * the node black
     */
    protected void setColor(Position<E> position, boolean color) {
        RBNode<E> node = (RBNode<E>) position;
        node.setColor(color);
    }

    /**
     * Returns a red child of a node.
     */
    protected Position<E> redChild(Position<E> position) {
        Position<E> child = this.binTree.left(position);
        if (isPosRed(child)) {
            return child;
        }
        child = this.binTree.right(position);
        if (isPosRed(child)) {
            return child;
        }
        return null;
    }

    /**
     * Returns whether a node has a red child.
     */
    protected boolean hasRedChild(Position<E> position) {
        if (isPosRed(this.binTree.left(position))
                || isPosRed(this.binTree.right(position))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * If v is a good tree node, cast to RBPosition, else throw exception
     */
    @Override
    public RBNode<E> checkPosition(Position<E> p)
            throws InvalidPositionException {
        if (p == null || !(p instanceof RBNode)) {
            throw new InvalidPositionException(
                    "The position of the RB node is not valid");
        }
        return (RBNode<E>) p;
    }
    private static final Logger LOG = Logger.getLogger(RBTree.class.getName());
}
