package material.ordereddictionary;

import material.maps.Entry;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AVLOrderedDictionaryTest {
    
    private AVLOrderedDictionary<Integer, String> instance;
    
    public AVLOrderedDictionaryTest() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {
        instance = new AVLOrderedDictionary();
    }
    
    @After
    public void tearDown() {}

    /**
     * Test of size method, of class AVLOrderedDictionary.
     */
    @Test
    public void testSize() {
        
        System.out.println("size");
        
        instance.insert(20, "hola");
        instance.insert(40, "iglu");
        
        assertTrue(instance.size()==2);
        
    }

    /**
     * Test of isEmpty method, of class AVLOrderedDictionary.
     */
    @Test
    public void testIsEmpty() {
        
        System.out.println("isEmpty");

        assertTrue(instance.isEmpty());
        
        instance.insert(20, "hola");
    
        assertFalse(instance.isEmpty());
        
    }

    /**
     * Test of find method, of class AVLOrderedDictionary.
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
     * Test of findAll method, of class AVLOrderedDictionary.
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
     * Test of findRange method, of class AVLOrderedDictionary.
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
        
        System.out.println("Entradas buscadas: " + instance.findRange(12, 39));
    
    }

    /**
     * Test of insert method, of class AVlOrderedDictionary.
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
     * Test of remove method, of class AVLOrderedDictionary.
     */
    @Test
    public void testRemove() {
        
        System.out.println("remove");
        
        Entry<Integer, String> tR1 = 
        instance.insert(20, "hola");
        instance.insert(10, "abaco");
        Entry<Integer, String> tR2 = 
        instance.insert(40, "iglu");
        instance.insert(40, "isla");
        instance.insert(50, "mano");
        
        instance.remove(tR1);
        System.out.println("Entrada borrada: " + tR1);
        instance.remove(tR2);
        System.out.println("Entrada borrada: " + tR2);
        
        System.out.println(instance.entries());
        
    }

    /**
     * Test of entries method, of class AVLOrderedDictionary.
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

}

