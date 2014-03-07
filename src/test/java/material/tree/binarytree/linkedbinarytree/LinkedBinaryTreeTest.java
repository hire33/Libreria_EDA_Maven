package material.tree.binarytree.linkedbinarytree;

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
public class LinkedBinaryTreeTest {
    
    private LinkedBinaryTree<Integer> instance;
    private FactoryNode<Integer> nodeFactory = new FactoryNode<> ();
    private static final int RAIZ = 1;
    
    /**
     *
     */
    public LinkedBinaryTreeTest() {}
    
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
        instance = new LinkedBinaryTree <>(nodeFactory);
        instance.addRoot(RAIZ);
    }
    
    /**
     *
     */
    @After
    public void tearDown() {}

    /**
     * Test of sibling method, of class LinkedBinaryTree.
     */
    @Test
    public void testSibling() {
        
        System.out.println("sibling");
    
        Position<Integer> a = instance.insertRight(instance.root(), 2);
        Position<Integer> b = instance.insertRight(a, 3);
        Position<Integer> c = instance.insertRight(b, 5);
        Position<Integer> d = instance.insertLeft(b, 4);
        Position<Integer> e = instance.insertLeft(c, 7);
        
        BTNode<Integer> hermano = instance.checkPosition(instance.sibling(d));

    }

    /**
     * Test of remove method, of class LinkedBinaryTree.
     */
    @Test
    public void testRemove() {

        System.out.println("remove");
        
        Position<Integer> a = instance.insertRight(instance.root(), 2);
        Position<Integer> b = instance.insertRight(a, 3);
        Position<Integer> c = instance.insertRight(b, 5);
        Position<Integer> d = instance.insertLeft(b, 4);
        Position<Integer> e = instance.insertLeft(c, 7);
        
        int sol = instance.remove(c);
        
        List<Position<Integer>> pos = (List<Position<Integer>>) instance.positions();
        
        for(Position<Integer> v : pos){
        }
        
        assertTrue(pos.size()==5);
        
    }

    /**
     * Test of attach method, of class LinkedBinaryTree.
     */
    @Test
    public void testAttach() {
    
        Position<Integer> a = instance.insertRight(instance.root(), 2);
        Position<Integer> b = instance.insertRight(a, 3);
        Position<Integer> c = instance.insertRight(b, 5);
        Position<Integer> d = instance.insertLeft(b, 4);
        Position<Integer> e = instance.insertLeft(c, 7);
    
        LinkedBinaryTree <Integer> subInstance1 = new LinkedBinaryTree(nodeFactory);
        subInstance1.addRoot(6);
        
        LinkedBinaryTree <Integer> subInstance2 = new LinkedBinaryTree(nodeFactory);
        subInstance2.addRoot(8);
        Position<Integer> f = subInstance2.insertLeft(subInstance2.root(), 9);
        Position<Integer> g = subInstance2.insertRight(subInstance2.root(), 11);
    
        instance.attach(e, subInstance1, subInstance2);
        
        List<Position<Integer>> pos = (List<Position<Integer>>) instance.positions();
        
        for(Position<Integer> v : pos){
        }
        
        assertTrue(instance.size()==10);
    
    }

}
