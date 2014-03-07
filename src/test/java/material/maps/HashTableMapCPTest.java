package material.maps;

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
public class HashTableMapCPTest {
    
    private AbstractHashTableMap<Integer,Integer> instance;
    
    /**
     *
     */
    public HashTableMapCPTest() {}
    
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
        instance = new HashTableMapCP (11);
    }
    
    /**
     *
     */
    @After
    public void tearDown() {}

    /**
     * Test of rehash method, of class HashTableMapCP.
     */
    @Test
    public void testRehash() {
        
        System.out.println("rehash");
        
        Integer a = instance.put(5, 1);
        System.out.println("Valor extraido: " + a);
        Integer b = instance.put(4, 2);
        System.out.println("Valor extraido: " + b);
        Integer c = instance.put(326, 3);
        System.out.println("Valor extraido: " + c);
        Integer d = instance.put(1_983, 4);
        System.out.println("Valor extraido: " + d);
        Integer e = instance.put(2_134, 5);
        System.out.println("Valor extraido: " + e);
        Integer f = instance.put(1_983, 6);
        System.out.println("Valor extraido: " + f);
        Integer g = instance.put(534, 29);
        
        Iterable<Entry<Integer,Integer>> listaEntradas = instance.entrySet();
        
        for(Entry<Integer, Integer> v : listaEntradas){
        }
        
        assertTrue(instance.capacity==22);
        
    }

    /**
     * Test of findEntry method, of class HashTableMapCP.
     */
    @Test
    public void testFindEntry() {
        
        System.out.println("findEntry");
        
        Integer a = instance.put(5, 1);
        System.out.println("Valor anterior: " + a);
        Integer b = instance.put(4, 2);
        System.out.println("Valor anterior: " + b);
        Integer c = instance.put(326, 3);
        System.out.println("Valor anterior: " + c);
        Integer d = instance.put(1_983, 4);
        System.out.println("Valor anterior: " + d);
        Integer e = instance.put(2_134, 5);
        System.out.println("Valor anterior: " + e);
        Integer f = instance.put(1_983, 6);
        System.out.println("Valor anterior: " + f);
        
        Integer g = instance.remove(2_134);
        System.out.println("Valor borrado: " + g);
        
        Integer busq = 326;
        Integer h = instance.get(busq);
        
        Iterable<Entry<Integer,Integer>> listaEntradas = instance.entrySet();
        
        for(Entry<Integer, Integer> v : listaEntradas){
        } 
    
    }

}
