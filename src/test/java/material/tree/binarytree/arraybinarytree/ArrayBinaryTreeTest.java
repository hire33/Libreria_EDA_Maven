package material.tree.binarytree.arraybinarytree;

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
public class ArrayBinaryTreeTest {
    
    private ArrayBinaryTree<Integer> instance;
    private static final int RAIZ = 20;
    
    /**
     *
     */
    public ArrayBinaryTreeTest() {}
    
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
        instance = new ArrayBinaryTree <> (); 
    }
    
    /**
     *
     */
    @After
    public void tearDown() {}

     /**
     * Test of size method, of class RankedArrayBinaryTree.
     */
    @Test
    public void testSize() {
    
        instance.addRoot(RAIZ); 
        Position<Integer> a = instance.insertLeft(instance.root(), 30);
        Position<Integer> b = instance.insertRight(instance.root(), 40);
        Position<Integer> c = instance.insertRight(b, 50);
        
        assertTrue(instance.size()==4);

    }

    /**
     * Test of isEmpty method, of class RankedArrayBinaryTree.
     */
    @Test
    public void testIsEmpty() {
    
        assertTrue(instance.isEmpty());
        
        instance.addRoot(RAIZ); 
    
        assertFalse(instance.isEmpty());
    
    }

    /**
     * Test of iterator method, of class RankedArrayBinaryTree.
     */
    @Test
    public void testIterator() {
    
        instance.addRoot(RAIZ); 
        
        Iterator<Integer> it = instance.iterator();
        
        Integer elem = it.next();
        
        assertTrue(elem==RAIZ);
    
    }

    /**
     * Test of positions method, of class RankedArrayBinaryTree.
     */
    @Test
    public void testPositions() {
    
        instance.addRoot(RAIZ); 
        Position<Integer> a = instance.insertLeft(instance.root(), 30);
        Position<Integer> b = instance.insertRight(instance.root(), 40);
        Position<Integer> c = instance.insertRight(b, 50);
        
        List<Position<Integer>> pos = (List<Position<Integer>>) instance.positions();
        
        for(Position<Integer> v : pos){
        }
    
    }

    /**
     * Test of replace method, of class RankedArrayBinaryTree.
     */
    @Test
    public void testReplace() {
    
        instance.addRoot(RAIZ); 
        Position<Integer> a = instance.insertLeft(instance.root(), 30);
        Position<Integer> b = instance.insertRight(instance.root(), 40);
        Position<Integer> c = instance.insertRight(b, 50);
    
        Integer last = instance.replace(b, 80);    
    
        List<Position<Integer>> pos = (List<Position<Integer>>) instance.positions();
        
        for(Position<Integer> v : pos){
        }
        
        assertTrue(b.element()==80);
        assertTrue(last==40);
        
    }

    /**
     * Test of root method, of class RankedArrayBinaryTree.
     */
    @Test
    public void testRoot() {
    
        instance.addRoot(RAIZ); 
        
        BTPos<Integer> a = instance.checkPosition(instance.root());
        
        assertTrue(a.element()==RAIZ);
    
    }

    /**
     * Test of parent method, of class RankedArrayBinaryTree.
     */
    @Test
    public void testParent() {
        
        instance.addRoot(RAIZ); 
        Position<Integer> a = instance.insertLeft(instance.root(), 30);
        Position<Integer> b = instance.insertRight(instance.root(), 40);
        Position<Integer> c = instance.insertRight(b, 50);
        
        BTPos<Integer> parent = instance.checkPosition(instance.parent(b));
        
        assertTrue(parent.element()==20);
        
    }

    /**
     * Test of children method, of class RankedArrayBinaryTree.
     */
    @Test
    public void testChildren() {
        
        System.out.println("children");
        
        instance.addRoot(RAIZ); 
        Position<Integer> a = instance.insertLeft(instance.root(), 30);
        Position<Integer> b = instance.insertRight(instance.root(), 40);
        Position<Integer> c = instance.insertRight(b, 50);
    
        Position<Integer> prueba = instance.root();
        
        List<Position<Integer>> pos = (List<Position<Integer>>) instance.children(prueba);
        
        for(Position<Integer> v : pos){
        }
     
    }

    /**
     * Test of isInternal method, of class RankedArrayBinaryTree.
     */
    @Test
    public void testIsInternal() {
        
        instance.addRoot(RAIZ); 
        Position<Integer> a = instance.insertLeft(instance.root(), 30);
        Position<Integer> b = instance.insertRight(instance.root(), 40);
        Position<Integer> c = instance.insertRight(b, 50);
        
        assertTrue(instance.isInternal(b));
        
    }

    /**
     * Test of isLeaf method, of class RankedArrayBinaryTree.
     */
    @Test
    public void testIsLeaf() {
        
        instance.addRoot(RAIZ); 
        Position<Integer> a = instance.insertLeft(instance.root(), 30);
        Position<Integer> b = instance.insertRight(instance.root(), 40);
        Position<Integer> c = instance.insertRight(b, 50);
        
        assertTrue(instance.isLeaf(c));
    
    
    }

    /**
     * Test of isRoot method, of class RankedArrayBinaryTree.
     */
    @Test
    public void testIsRoot() {
        
        instance.addRoot(RAIZ); 
        Position<Integer> a = instance.insertLeft(instance.root(), 30);
        Position<Integer> b = instance.insertRight(instance.root(), 40);
        Position<Integer> c = instance.insertRight(b, 50);
        
        assertTrue(instance.isRoot(instance.root()));
            
    }
    
    /**
     * Test of left method, of class RankedArrayBinaryTree.
     */
    @Test
    public void testLeft() {
        
        instance.addRoot(RAIZ); 
        Position<Integer> a = instance.insertLeft(instance.root(), 30);
        Position<Integer> b = instance.insertRight(instance.root(), 40);
        Position<Integer> c = instance.insertRight(b, 50);
        
        Position <Integer> prueba = instance.left(instance.root());
        
        assertTrue(prueba==a);

         
    }

    /**
     * Test of right method, of class RankedArrayBinaryTree.
     */
    @Test
    public void testRight() {
        
        instance.addRoot(RAIZ); 
        Position<Integer> a = instance.insertLeft(instance.root(), 30);
        Position<Integer> b = instance.insertRight(instance.root(), 40);
        Position<Integer> c = instance.insertRight(b, 50);
        
        Position <Integer> prueba = instance.right(b);
        
        assertTrue(prueba==c);
    
    }

    /**
     * Test of hasLeft method, of class RankedArrayBinaryTree.
     */
    @Test
    public void testHasLeft() {
        
        instance.addRoot(RAIZ); 
        Position<Integer> a = instance.insertLeft(instance.root(), 30);
        Position<Integer> b = instance.insertRight(instance.root(), 40);
        Position<Integer> c = instance.insertRight(b, 50);
        
        assertTrue(instance.hasLeft(instance.root()));
             
    }

    /**
     * Test of hasRight method, of class RankedArrayBinaryTree.
     */
    @Test
    public void testHasRight() {
        
        instance.addRoot(RAIZ); 
        Position<Integer> a = instance.insertLeft(instance.root(), 30);
        Position<Integer> b = instance.insertRight(instance.root(), 40);
        Position<Integer> c = instance.insertRight(b, 50);
        
        assertTrue(instance.hasRight(b));
        
        
    }
    
    /**
     * Test of sibling method, of class RankedArrayBinaryTree.
     */
    @Test
    public void testSibling() {
        
        System.out.println("sibling");
        
        instance.addRoot(RAIZ); 
        Position<Integer> a = instance.insertLeft(instance.root(), 30);
        Position<Integer> b = instance.insertRight(instance.root(), 40);
        Position<Integer> c = instance.insertRight(b, 50);
        
        Position <Integer> prueba = a;
        
        BTPos<Integer> hermano = instance.checkPosition(instance.sibling(prueba));
    
    }
    
    /**
     * Test of remove method, of class LinkedBinaryTree.
     */
    @Test
    public void testRemove() {

        System.out.println("remove");
        
        instance.addRoot(RAIZ); 
        Position<Integer> a = instance.insertLeft(instance.root(), 30);
        Position<Integer> b = instance.insertRight(instance.root(), 40);
        Position<Integer> c = instance.insertRight(b, 50);
        
        int sol = instance.remove(c);
        
        Position<Integer> d = instance.insertRight(b, 50);
        Position<Integer> e = instance.insertLeft(d, 80);
        
        int sol1 = instance.remove(e);
        int sol2 = instance.remove(d);
        
        List<Position<Integer>> pos = (List<Position<Integer>>) instance.positions();
        
        for(Position<Integer> v : pos){
        }
        
        assertTrue(pos.size()==3);
        
    }

    /**
     * Test of attach method, of class LinkedBinaryTree.
     */
    @Test
    public void testAttach() {
    
        instance.addRoot(RAIZ); 
        Position<Integer> a = instance.insertLeft(instance.root(), 30);
        Position<Integer> b = instance.insertRight(instance.root(), 40);
        Position<Integer> c = instance.insertRight(b, 50);
    
        ArrayBinaryTree <Integer> subInstance1 = new ArrayBinaryTree();
        subInstance1.addRoot(60);
        
        ArrayBinaryTree <Integer> subInstance2 = new ArrayBinaryTree();
        subInstance2.addRoot(70);
        Position<Integer> d = subInstance2.insertLeft(subInstance2.root(), 80);
           
        instance.attach(a, subInstance1, subInstance2);
        
        List<Position<Integer>> pos = (List<Position<Integer>>) instance.positions();
        
        for(Position<Integer> v : pos){
        }
        
        assertTrue(instance.size()==7);
    
    }
            
}
