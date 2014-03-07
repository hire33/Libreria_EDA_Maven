package material.tree.binarysearchtree;

import java.util.ArrayList;
import java.util.List;
import material.tree.Position;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RBTreeTest {
    
    private BinarySearchTree<Integer> instance;
    
    public RBTreeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {
        instance = new RBTree <>(new FactoryRBNode<Integer>());
    }
    
    @After
    public void tearDown() {}

    /**
     * Test of insert method, of class RBTree.
     */
    @Test
    public void testInsert() {
        
        System.out.println("insert");

//        System.out.println("Caso 1: El tio del nodo insertado es negro.");
//        
//        instance.insert(16);
//        instance.insert(15);
//        instance.insert(14);
//        
//        List<Position<Integer>> posI = (List<Position<Integer>>) instance.positions();
//        List<Position<Integer>> posP = new ArrayList<>();
//        instance.binTree.preorderPositions(instance.binTree.root(), posP);
//        
//        for(Position<Integer> v : posI){
//            RBNode<Integer> nodeAtV = (RBNode) instance.checkPosition(v);
//            char isRed = nodeAtV.isRed()?'R':'B';
//            System.out.print(v.element() + "[" + isRed + "]" + " ");
//        }
//        System.out.print("\nComprobando reestructuraciones...\n");
//        for(Position<Integer> v : posP){
//            System.out.print(v.element() + " ");
//        }
//        System.out.println("\n");
             
        System.out.println("Caso 2: El tio del nodo insertado es rojo.");
        
        instance.insert(4);
        instance.insert(2);
        instance.insert(7);
        instance.insert(6);
        
        List<Position<Integer>> posI = (List<Position<Integer>>) instance.positions();
        List<Position<Integer>> posP = new ArrayList<>();
        instance.binTree.preorderPositions(instance.binTree.root(), posP);
        
        for(Position<Integer> v : posI){
            RBNode<Integer> nodeAtV = (RBNode) instance.checkPosition(v);
            char isRed = nodeAtV.isRed()?'R':'B';
            System.out.print(v.element() + "[" + isRed + "]" + " ");
        }
        System.out.print("\nComprobando reestructuraciones...\n");
        for(Position<Integer> v : posP){
            System.out.print(v.element() + " ");
        }
        System.out.println("\n");       
    
    }

}
