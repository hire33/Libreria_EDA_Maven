package material.ordereddictionary;

import java.util.ArrayList;
import java.util.List;
import material.maps.Entry;
import material.tree.Position;
import material.tree.binarysearchtree.RBNode;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RBOrderedDictionaryTest {
    
    private RBOrderedDictionary<Integer, String> instance;
    
    public RBOrderedDictionaryTest() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {
        instance = new RBOrderedDictionary();
    }
    
    @After
    public void tearDown() {}

    /**
     * Test of size method, of class RBOrderedDictionary.
     */
    @Test
    public void testSize() {
        
        System.out.println("size");
        
        instance.insert(20, "hola");
        instance.insert(40, "iglu");
        
        assertTrue(instance.size()==2);
        
    }

    /**
     * Test of isEmpty method, of class RBOrderedDictionary.
     */
    @Test
    public void testIsEmpty() {
        
        System.out.println("isEmpty");

        assertTrue(instance.isEmpty());
        
        instance.insert(20, "hola");
    
        assertFalse(instance.isEmpty());
        
    }

    /**
     * Test of find method, of class RBOrderedDictionary.
     */
    @Test
    public void testFind() {
        
        System.out.println("find");
        
        instance.insert(20, "hola");
        instance.insert(10, "abaco");
        instance.insert(40, "iglu");
        instance.insert(40, "isla");
        instance.insert(50, "mano");
        
        System.out.println(instance.entries());
        
        Entry prueba = instance.find(40);
        
        System.out.println("Entrada buscada: " + prueba);
                
    }

    /**
     * Test of findAll method, of class RBOrderedDictionary.
     */
    @Test
    public void testFindAll() {
        
        System.out.println("findAll");

        instance.insert(20, "hola");
        instance.insert(10, "abaco");
        instance.insert(40, "iglu");
        instance.insert(40, "isla");
        instance.insert(50, "mano");
        
        System.out.println(instance.entries());
        
        //Uso del método toString por defecto.
        
        System.out.println("Entrada buscadas: " + instance.findAll(40));
    
    }
    
    /**
     * Test of findRange method, of class BSTOrderedDictionary.
     */
    @Test
    public void testFindRange() {
        
        System.out.println("findRange");

        instance.insert(20, "hola");
        instance.insert(10, "abaco");
        instance.insert(40, "iglu");
        instance.insert(40, "isla");
        instance.insert(50, "mano");
        
        System.out.println(instance.entries());
        
        //Uso del método toString por defecto.
        
        System.out.println("Entradas buscadas: " + instance.findRange(9, 2));
    
    }

    /**
     * Test of insert method, of class RBOrderedDictionary.
     */
    @Test
    public void testInsert() {
        
        System.out.println("insert");

        instance.insert(20, "hola");
        instance.insert(10, "abaco");
        instance.insert(40, "iglu");
        instance.insert(40, "isla");
        instance.insert(50, "mano");
                
        System.out.println(instance.entries());
        
    }

    /**
     * Test of remove method, of class RBOrderedDictionary.
     */
    @Test
    public void testRemove() {
        
        System.out.println("remove\n");
        
        Entry<Integer, String> tR1 = instance.insert(20, "hola");
        Entry<Integer, String> tR2 = instance.insert(10, "abaco");
        Entry<Integer, String> tR3 = instance.insert(40, "iglu");
        Entry<Integer, String> tR4 = instance.insert(40, "isla");
        Entry<Integer, String> tR5 = instance.insert(50, "mano");
        
        instance.remove(tR1);
        System.out.println("Entrada borrada: " + tR1);
        imprimirRB();
        
        instance.remove(tR5);
        System.out.println("Entrada borrada: " + tR5);
        imprimirRB();
        
        instance.remove(tR4);
        System.out.println("Entrada borrada: " + tR4);
        imprimirRB();
        
        instance.remove(tR3);
        System.out.println("Entrada borrada: " + tR3);
        imprimirRB();
        
        instance.remove(tR2);
        System.out.println("Entrada borrada: " + tR2);
        imprimirRB();
             
    }

    /**
     * Test of entries method, of class RBOrderedDictionary.
     */
    @Test
    public void testEntries() {
        
        System.out.println("entries");

        instance.insert(20, "hola");
        instance.insert(10, "abaco");
        instance.insert(40, "iglu");
        instance.insert(40, "isla");
        instance.insert(50, "mano");
                
        System.out.println(instance.entries());
        
    }
    
    private void imprimirRB (){
        
        //Salida por pantalla.
        
        List<Position<BSTDictEntry<Integer,String>>> posI = (List<Position<BSTDictEntry<Integer,String>>>) instance.binTree.positions();
        List<Position<BSTDictEntry<Integer,String>>> posP = new ArrayList<>();
        instance.binTree.binTree.preorderPositions(instance.binTree.binTree.root(), posP);
        
        for(Position<BSTDictEntry<Integer,String>> v : posI){
            RBNode<BSTDictEntry<Integer,String>> nodeAtV = (RBNode) instance.binTree.checkPosition(v);
            char isRed = nodeAtV.isRed()?'R':'B';
            System.out.print(v.element() + "[" + isRed + "]" + " ");
        }
        System.out.print("\nComprobando reestructuraciones...\n");
        for(Position<BSTDictEntry<Integer,String>> v : posP){
            System.out.print(v.element() + " ");
        }
        System.out.println("\n");   
             
        //FIN Salida por pantalla.
    
    }

}

