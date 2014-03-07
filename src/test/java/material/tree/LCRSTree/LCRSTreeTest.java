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

public class LCRSTreeTest {
    
    private LCRSTree<Integer> instance;
    private static final int RAIZ = 3;
    
    public LCRSTreeTest() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {
        instance = new LCRSTree <> ();
        instance.addRoot(RAIZ);
    }
    
    @After
    public void tearDown() {}

    /**
     * Test of size method, of class LCRSTree.
     */
    @Test
    public void testSize() {
        
        System.out.println("size");
        
        assertTrue(instance.size()==1);

    }

    /**
     * Test of isEmpty method, of class LCRSTree.
     */
    @Test
    public void testIsEmpty() {
        
        System.out.println("isEmpty");
       
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
        
        System.out.println(elem);
        
        assertTrue(elem==RAIZ);

    }

    /**
     * Test of positions method, of class LCRSTree.
     */
    @Test
    public void testPositions() {
        
        System.out.println("positions");
        
        Position<Integer> a = instance.add(instance.root, 7);
        Position<Integer> b = instance.add(a, 5);
        Position<Integer> c = instance.add(a, 8);
        
        List<Position<Integer>> pos = (List<Position<Integer>>) instance.positions();
        
        for(Position<Integer> v : pos){
            System.out.print(v.element() + " ");
        }

    }

    /**
     * Test of replace method, of class LCRSTree.
     */
    @Test //(expected = InvalidPositionException.class)
    public void testReplace() {
        System.out.println("replace");
        
        Position<Integer> a = instance.add(instance.root, 7);
        Position<Integer> b = instance.add(a, 5);
        Position<Integer> c = instance.add(a, 8);
        
        Integer last = instance.replace(a, 4);
        //Integer last = instance.replace(null, 4);
        
        List<Position<Integer>> pos = (List<Position<Integer>>) instance.positions();
        
        for(Position<Integer> v : pos){
            System.out.print(v.element() + " ");
        }
        
        assertTrue(a.element()==4);
        assertTrue(last==7);
      
    }

    /**
     * Test of root method, of class LCRSTree.
     */
    @Test //(expected = EmptyTreeException.class)
    public void testRoot() {
        
        System.out.println("root");
       
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
        
        System.out.println("parent");
        
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
        
        System.out.println("children");
        
        Position<Integer> a = instance.add(instance.root, 7);
        Position<Integer> b = instance.add(a, 5);
        Position<Integer> c = instance.add(a, 8);
        
        List<Position<Integer>> pos = (List<Position<Integer>>) instance.children(a);
        
        for(Position<Integer> v : pos){
            System.out.print(v.element() + " ");
        }
        
    }

    /**
     * Test of isInternal method, of class LCRSTree.
     */
    @Test
    public void testIsInternal() {
        
        System.out.println("isInternal");
        
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
        
        System.out.println("isLeaf");
        
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
        
        System.out.println("isRoot");
        
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
        
        System.out.println("add");
        
        Position<Integer> a = instance.add(instance.root, 7);
        Position<Integer> b = instance.add(a, 5);
        Position<Integer> c = instance.add(a, 8);
        
        List<Position<Integer>> pos = (List<Position<Integer>>) instance.positions();
        
        for(Position<Integer> v : pos){
            System.out.print(v.element() + " ");
        }
        
        assertTrue(pos.get(1).element()==7);
        assertTrue(instance.size==4);
        
    }
    
    /**
     * Test of swapElements method, of class LCRSTree.
     */
    @Test 
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
     * Test of remove method, of class LCRSTree.
     */
    @Test 
    public void testRemove() {
        
        System.out.println("remove");
        
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
            System.out.print(v.element() + " ");
        }
        
        //assertTrue(instance.isEmpty());
        assertTrue(instance.size()==6);
             
    }
    
}