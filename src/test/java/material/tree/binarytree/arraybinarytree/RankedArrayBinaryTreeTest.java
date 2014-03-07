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

public class RankedArrayBinaryTreeTest {
    
    private RankedArrayBinaryTree<Integer> instance;
    private static final int RAIZ = 20;
    
    public RankedArrayBinaryTreeTest() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {
        instance = new RankedArrayBinaryTree <> ();      
    }
    
    @After
    public void tearDown() {}

    /**
     * Test of size method, of class RankedArrayBinaryTree.
     */
    @Test
    public void testSize() {
        
        System.out.println("size");
    
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
        
        System.out.println("isEmpty");
    
        assertTrue(instance.isEmpty());
        
        instance.addRoot(RAIZ); 
    
        assertFalse(instance.isEmpty());
    
    }

    /**
     * Test of iterator method, of class RankedArrayBinaryTree.
     */
    @Test
    public void testIterator() {
        
        System.out.println("iterator");
    
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
        
        System.out.println("positions");
    
        instance.addRoot(RAIZ); 
        Position<Integer> a = instance.insertLeft(instance.root(), 30);
        Position<Integer> b = instance.insertRight(instance.root(), 40);
        Position<Integer> c = instance.insertRight(b, 50);
        
        List<Position<Integer>> pos = (List<Position<Integer>>) instance.positions();
        
        for(Position<Integer> v : pos){
            System.out.print(v + " ");
        }
    
    }

    /**
     * Test of replace method, of class RankedArrayBinaryTree.
     */
    @Test
    public void testReplace() {
        
        System.out.println("replace");
    
        instance.addRoot(RAIZ); 
        Position<Integer> a = instance.insertLeft(instance.root(), 30);
        Position<Integer> b = instance.insertRight(instance.root(), 40);
        Position<Integer> c = instance.insertRight(b, 50);
    
        Integer last = instance.replace(b, 80);    
    
        List<Position<Integer>> pos = (List<Position<Integer>>) instance.positions();
        
        for(Position<Integer> v : pos){
            System.out.print(v + " ");
        }
        
        assertTrue(b.element()==80);
        assertTrue(last==40);
        
    }

    /**
     * Test of root method, of class RankedArrayBinaryTree.
     */
    @Test
    public void testRoot() {
        
        System.out.println("root");
    
        instance.addRoot(RAIZ); 
        
        BTPos<Integer> a = instance.checkPosition(instance.root());
        
        assertTrue(a.element()==RAIZ);
    
    }

    /**
     * Test of parent method, of class RankedArrayBinaryTree.
     */
    @Test
    public void testParent() {
        
        System.out.println("parent");
        
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
    
        System.out.print("Hijos de " + prueba + " : " );
        
        for(Position<Integer> v : pos){
            System.out.print(v + " ");
        }
     
    }

    /**
     * Test of isInternal method, of class RankedArrayBinaryTree.
     */
    @Test
    public void testIsInternal() {
        
        System.out.println("isInternal");
        
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
        
        System.out.println("isLeaf");
        
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
        
        System.out.println("isRoot");
        
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
        
        System.out.println("left");
        
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
        System.out.println("right");
        
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
        
        System.out.println("hasLeft");
        
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
        
        System.out.println("hasRight");
        
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
        
        System.out.println("Hermano de " + prueba + " : " + hermano);
    
    }
            
}
