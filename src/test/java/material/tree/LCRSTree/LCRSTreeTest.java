package material.tree.LCRSTree;

import java.util.Iterator;
import java.util.List;
import material.tree.Position;
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
public class LCRSTreeTest {
    
    private LCRSTree<Integer> instance;
    private static final int RAIZ = 3;
    
    /**
     *
     */
    public LCRSTreeTest() {}
    
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
        instance = new LCRSTree <> ();
        instance.addRoot(RAIZ);
    }
    
    /**
     *
     */
    @After
    public void tearDown() {}

    /**
     * Test of size method, of class LCRSTree.
     */
    @Test
    public void testSize() {
        
        assertTrue(instance.size()==1);

    }

    /**
     * Test of isEmpty method, of class LCRSTree.
     */
    @Test
    public void testIsEmpty() {
       
        instance.remove(instance.root());
        
        assertTrue(instance.isEmpty());
        
    }

    /**
     * Test of iterator method, of class LCRSTree.
     */
    @Test
    public void testIterator() {
        
        System.out.println("iterator");
        
        Iterator<Integer> it = instance.iterator();
        
        Integer elem = it.next();
        
        assertTrue(elem==RAIZ);

    }

    /**
     * Test of positions method, of class LCRSTree.
     */
    @Test
    public void testPositions() {
        
        Position<Integer> a = instance.add(instance.root, 7);
        Position<Integer> b = instance.add(a, 5);
        Position<Integer> c = instance.add(a, 8);
        
        List<Position<Integer>> pos = (List<Position<Integer>>) instance.positions();
        
        for(Position<Integer> v : pos){
        }

    }

    /**
     * Test of replace method, of class LCRSTree.
     */
    @Test //(expected = InvalidPositionException.class)
    public void testReplace() {
        
        Position<Integer> a = instance.add(instance.root, 7);
        Position<Integer> b = instance.add(a, 5);
        Position<Integer> c = instance.add(a, 8);
        
        Integer last = instance.replace(a, 4);
        //Integer last = instance.replace(null, 4);
        
        List<Position<Integer>> pos = (List<Position<Integer>>) instance.positions();
        
        for(Position<Integer> v : pos){
        }
        
        assertTrue(a.element()==4);
        assertTrue(last==7);
      
    }

    /**
     * Test of root method, of class LCRSTree.
     */
    @Test //(expected = EmptyTreeException.class)
    public void testRoot() {
       
        //instance.remove(instance.root());
        //Position<Integer> z = instance.root();
        
        LCRSNode<Integer> a = instance.checkPosition(instance.root());
        
        assertTrue(a.element()==RAIZ);
        

    }

    /**
     * Test of parent method, of class LCRSTree.
     */
    @Test //(expected = BoundaryViolationException.class)
    public void testParent() {
        
        Position<Integer> a = instance.add(instance.root, 7);
        Position<Integer> b = instance.add(a, 5);
        Position<Integer> c = instance.add(a, 8);
        
        //Position <Integer> z = instance.parent(instance.root());
        
        LCRSNode<Integer> parent = instance.checkPosition(instance.parent(b));
        
        assertTrue(parent.element()==7);
        
    }

    /**
     * Test of children method, of class LCRSTree.
     */
    @Test
    public void testChildren() {
        
        Position<Integer> a = instance.add(instance.root, 7);
        Position<Integer> b = instance.add(a, 5);
        Position<Integer> c = instance.add(a, 8);
        
        List<Position<Integer>> pos = (List<Position<Integer>>) instance.children(a);
        
        for(Position<Integer> v : pos){
        }
        
    }

    /**
     * Test of isInternal method, of class LCRSTree.
     */
    @Test
    public void testIsInternal() {
        
        Position<Integer> a = instance.add(instance.root, 7);
        Position<Integer> b = instance.add(a, 5);
        Position<Integer> c = instance.add(a, 8);
        
        assertTrue(instance.isInternal(a));

    }

    /**
     * Test of isLeaf method, of class LCRSTree.
     */
    @Test
    public void testIsLeaf() {
        
        Position<Integer> a = instance.add(instance.root, 7);
        Position<Integer> b = instance.add(a, 5);
        Position<Integer> c = instance.add(a, 8);
        
        assertTrue(instance.isLeaf(b));

    }

    /**
     * Test of isRoot method, of class LCRSTree.
     */
    @Test
    public void testIsRoot() {
        
        Position<Integer> a = instance.add(instance.root, 7);
        Position<Integer> b = instance.add(a, 5);
        Position<Integer> c = instance.add(a, 8);
        
        assertTrue(instance.isRoot(instance.root()));

    }
    
    /**
     * Test of add method, of class LCRSTree.
     */
    @Test 
    public void testAdd() {
        
        Position<Integer> a = instance.add(instance.root, 7);
        Position<Integer> b = instance.add(a, 5);
        Position<Integer> c = instance.add(a, 8);
        
        List<Position<Integer>> pos = (List<Position<Integer>>) instance.positions();
        
        for(Position<Integer> v : pos){
        }
        
        assertTrue(pos.get(1).element()==7);
        assertTrue(instance.size==4);
        
    }
    
    /**
     * Test of swapElements method, of class LCRSTree.
     */
    @Test 
    public void testSwapElements() {
        
        Position<Integer> w = instance.add(instance.root, 12);
        
        instance.swapElements(instance.root, w);
        
        //instance.swapElements(null, w);
        //instance.swapElements(instance.root, null);
        //instance.swapElements(null, null);
        
        List<Position<Integer>> pos = (List<Position<Integer>>) instance.positions();
        
        for(Position<Integer> v : pos){
        }
        
        assertTrue(instance.root.element()==12);
        assertTrue(w.element()==RAIZ);
        
    }

    /**
     * Test of remove method, of class LCRSTree.
     */
    @Test 
    public void testRemove() {
        
        Position<Integer> a = instance.add(instance.root, 24);
        Position<Integer> b = instance.add(instance.root, 5);
        Position<Integer> c = instance.add(instance.root, 4);
        Position<Integer> d = instance.add(a, 8);
        Position<Integer> e = instance.add(a, 9);
        Position<Integer> f = instance.add(a, 10);
        Position<Integer> g = instance.add(b, 6);
        Position<Integer> h = instance.add(b, 7);
        Position<Integer> i = instance.add(b, 11);
        
        //instance.remove(instance.root());
        instance.remove(b);
               
        List<Position<Integer>> pos = (List<Position<Integer>>) instance.positions();
        
        for(Position<Integer> v : pos){
        }
        
        //assertTrue(instance.isEmpty());
        assertTrue(instance.size()==6);
             
    }
    
}