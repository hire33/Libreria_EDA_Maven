package material.tree.binarysearchtree;

import java.util.ArrayList;
import java.util.List;
import material.tree.Position;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AVLTreeTest {
    
    private BinarySearchTree<Integer> instance;
    
    public AVLTreeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {
        instance = new AVLTree <>(new FactoryAVLNode<Integer>());
    }
    
    @After
    public void tearDown() {}

    /**
     * Test of insert method, of class AVLTree.
     */
    @Test
    public void testInsert() {
        
        System.out.println("insert");

        instance.insert(44);
        instance.insert(17);
        instance.insert(78);
        instance.insert(50);
        instance.insert(62);
        
        List<Position<Integer>> posI = (List<Position<Integer>>) instance.positions();
        List<Position<Integer>> posP = new ArrayList<>();
        instance.binTree.preorderPositions(instance.binTree.root(), posP);
        
        for(Position<Integer> v : posI){
            System.out.print(v.element() + " ");
        }
        System.out.print("\nComprobando la reestructuracion...\n");
        for(Position<Integer> v : posP){
            System.out.print(v.element() + " ");
        }
        System.out.println("\n");
         
    }

    /**
     * Test of remove method, of class AVLTree.
     */
    @Test
    public void testRemove() {
        
        System.out.println("remove");
        
        instance.insert(44);
        Position<Integer> a = instance.insert(17);
        instance.insert(78);
        instance.insert(50);
        instance.insert(62);
        
        List<Position<Integer>> posI = (List<Position<Integer>>) instance.positions();
        List<Position<Integer>> posP = new ArrayList<>();
        instance.binTree.preorderPositions(instance.binTree.root(), posP);
        
        for(Position<Integer> v : posI){
            System.out.print(v.element() + " ");
        }
        System.out.print("\nComprobando la reestructuracion...\n");
        for(Position<Integer> v : posP){
            System.out.print(v.element() + " ");
        }
        System.out.println("\n");
        
        instance.remove(a);
        
        posI = (List<Position<Integer>>) instance.positions();
        posP = new ArrayList<>();
        instance.binTree.preorderPositions(instance.binTree.root(), posP);
        
        for(Position<Integer> v : posI){
            System.out.print(v.element() + " ");
        }
        System.out.print("\nComprobando la reestructuracion...\n");
        for(Position<Integer> v : posP){
            System.out.print(v.element() + " ");
        }
        System.out.println("\n");
    
    }

}
