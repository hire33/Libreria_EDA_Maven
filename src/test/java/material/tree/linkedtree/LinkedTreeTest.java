package material.tree.linkedtree;

import java.util.List;
import material.tree.Position;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LinkedTreeTest {
    
    private LinkedTree<Integer> instance;
    private static final int RAIZ = 3;
        
    public LinkedTreeTest() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {
        instance = new LinkedTree <>();
        instance.addRoot(RAIZ);
    }
    
    @After
    public void tearDown() {}

    /**
     * Test of add method, of class LinkedTree.
     */
    @Test 
    public void testAdd() {
        
        System.out.println("add");
        
        instance.add(instance.root, 7);
          
        List<Position<Integer>> pos = (List<Position<Integer>>) instance.positions();
        
        for(Position<Integer> v : pos){
            System.out.print(v.element() + " ");
        }
        
        assertTrue(pos.get(1).element()==7);
        assertTrue(instance.size==2);
        
    }
    
    /**
     * Test of swapElements method, of class LinkedTree.
     */
    @Test //(expected = InvalidPositionException.class)
    public void testSwapElements() {
        
        System.out.println("swapElements");
        
        Position<Integer> w = instance.add(instance.root, 12);
        
        instance.swapElements(instance.root, w);
        
        //instance.swapElements(null, w);
        //instance.swapElements(instance.root, null);
        //instance.swapElements(null, null);
        
        List<Position<Integer>> pos = (List<Position<Integer>>) instance.positions();
        
        for(Position<Integer> v : pos){
            System.out.print(v.element() + " ");
        }
        
        assertTrue(instance.root.element()==12);
        assertTrue(w.element()==RAIZ);
        
    }

    /**
     * Test of remove method, of class LinkedTree.
     */
    @Test 
    public void testRemove() {
        
        System.out.println("remove");
        
        Position<Integer> a = instance.add(instance.root, 7);
        Position<Integer> b = instance.add(a, 5);
        Position<Integer> c = instance.add(a, 8);
        
        //instance.remove(instance.root());
        //instance.remove(c);
        instance.remove(a);
        
        List<Position<Integer>> pos = (List<Position<Integer>>) instance.positions();
        
        for(Position<Integer> v : pos){
            System.out.print(v.element() + " ");
        }
        
        //assertTrue(instance.isEmpty());
        //assertTrue(instance.size()==3);
        assertTrue(instance.size()==1);
             
    }

}
