package material.tree.binarysearchtree;

import java.util.ArrayList;
import java.util.List;
import material.tree.Position;
import material.tree.binarytree.linkedbinarytree.BTNode;
import material.tree.binarytree.linkedbinarytree.FactoryNode;

/**
 *
 * @author Asus
 * @param <E>
 */
public class Restructurator<E> {

        //Atributos.
    
	private FactoryNode<E> factory;
	
        //Constructores.
        
	/**
     *
     * @param factory
     */
    public Restructurator(FactoryNode<E> factory) {
		this.factory = factory;
	}
        
        //Métodos.
	
	/**
	 * Performs a tri-node restructuring.
	 * 
	 * @return the new root of the restructured subtree
	 */
	public Position<E> restructure(Position<E> posNode, BinarySearchTree<E> bst) {
		
            /**
             * Guardamos las posiciones de x, y, z y de los subarboles
             * afectados por la reestructuracion: s0,...,s3.
             */
            
            Position<E> x, y, z, s0, s1, s2, s3;
            
            x = posNode;
            y = bst.binTree.parent(x);
            z = bst.binTree.parent(y);
           
            s0 = bst.binTree.right(x);
            s1 = bst.binTree.left(x);
            s2 = bst.binTree.sibling(x);
            if(bst.binTree.left(z)==y){
                s3 = bst.binTree.right(z);
            } else {
                s3 = bst.binTree.left(z);
            }
             
            /**
             * Guardamos las posiciones a, b y c relacionadas con x, y, z y
             * las posiciones t0,...,t4 relacionadas con los subarboles
             * afectados por la reestructuracion: s0,...,s3.
             */
            
            Position<E> a = null, b = null, c = null, t0 = null, t1 = null, t2 = null, t3 = null;
            
            List<Position<E>> inorderPositions = new ArrayList <> ();
            bst.binTree.inorderPositions(z, inorderPositions);
            
            int contNodes = 0;
            int contTrees = 0;
            
            for(Position<E> pos : inorderPositions){               
                if(pos==x||pos==y||pos==z){
                    switch(contNodes){
                        case 0 : a=pos; break;
                        case 1 : b=pos; break;    
                        default : c=pos; break;    
                    }
                    contNodes++;                    
                } else if(pos==s0||pos==s1||pos==s2||pos==s3){
                    switch(contTrees){
                        case 0 : t0=pos; break;
                        case 1 : t1=pos; break;
                        case 2 : t2=pos; break;    
                        default : t3=pos; break;    
                    }
                    contTrees++;                    
                } else {
                    continue;
                }        
            }
            
            /**
             * Procedemos con la reestructuración en si.
             */
            
            BTNode<E> oldRoot = bst.checkPosition(z);
            BTNode<E> zParent = oldRoot.getParent();
            BTNode<E> nodeAtB = bst.checkPosition(b);
            
            //Colocando la nueva raiz.
            
            if(zParent==null){
                
                //b sera la nueva raiz general.
                
                bst.binTree.changeRoot(b);
                nodeAtB.setParent(null);
            
            } else {
                
                if(bst.binTree.left(zParent)==z){                   
                    zParent.setLeft(nodeAtB);
                } else {
                    zParent.setRight(nodeAtB);
                }
                
                nodeAtB.setParent(zParent);
                    
           }   
                                        
            //Recolocando el arbol.
                
            BTNode<E> nodeAtA = bst.checkPosition(a);
            BTNode<E> nodeAtC = bst.checkPosition(c);
            nodeAtB.setLeft(nodeAtA);
            nodeAtA.setParent(nodeAtB);
            nodeAtB.setRight(nodeAtC);
            nodeAtC.setParent(nodeAtB);
            BTNode<E> rootOfT0 = bst.checkPosition(t0);
            BTNode<E> rootOfT1 = bst.checkPosition(t1);
            nodeAtA.setLeft(rootOfT0);
            rootOfT0.setParent(nodeAtA);
            nodeAtA.setRight(rootOfT1);
            rootOfT1.setParent(nodeAtA);
            BTNode<E> rootOfT2 = bst.checkPosition(t2);
            BTNode<E> rootOfT3 = bst.checkPosition(t3);
            nodeAtC.setLeft(rootOfT2);
            rootOfT2.setParent(nodeAtC);
            nodeAtC.setRight(rootOfT3);
            rootOfT3.setParent(nodeAtC);
           
            return b;
		
	}

}
