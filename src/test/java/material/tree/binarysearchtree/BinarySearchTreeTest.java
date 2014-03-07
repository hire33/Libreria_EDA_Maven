package material.tree.binarysearchtree;

import java.util.List;
import material.tree.Position;
import material.tree.binarytree.linkedbinarytree.FactoryNode;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Asus
 */
public class BinarySearchTreeTest {
    
    private BinarySearchTree<Integer> instance;
    
    /**
     *
     */
    public BinarySearchTreeTest() {}
    
    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {}
    
    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {}
    
    /**
     *
     */
    @Before
    public void setUp() {
        instance = new BinarySearchTree <>(new FactoryNode<Integer>());
    }
    
    /**
     *
     */
    @After
    public void tearDown() {}

    /**
     * Test of insert method, of class BinarySearchTree.
     */
    @Test
    public void testInsert() {

        instance.insert(20);
        instance.insert(10);
        instance.insert(40);
        instance.insert(50);
        
        List<Position<Integer>> pos = (List<Position<Integer>>) instance.positions();
        
        for(Position<Integer> v : pos){
        }
          
    }

    /**
     * Test of getLeafToRemove method, of class BinarySearchTree.
     */
    @Test
    public void testGetLeafToRemove() {
        
        Position<Integer> insert = instance.insert(20);
        instance.insert(10);
        Position<Integer> prueba = instance.insert(40);
        instance.insert(50);
        
        List<Position<Integer>> pos = (List<Position<Integer>>) instance.positions();
        
        for(Position<Integer> v : pos){
        }
        
        assertTrue(instance.getLeafToRemove(insert)==instance.binTree.left(prueba));
     
    }

}