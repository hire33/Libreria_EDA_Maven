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
public class HashTableMapSCTest {
    
    private HashTableMapSC<Integer,Integer> instance;
    
    /**
     *
     */
    public HashTableMapSCTest() {}
    
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
        instance = new HashTableMapSC(5);
    }
    
    /**
     *
     */
    @After
    public void tearDown() {}

    /**
     * Test of size method, of class HashTableMapSC.
     */
    @Test
    public void testSize() {
        
        System.out.println("size");
        
        Integer a = instance.put(5, 1);
        System.out.println("Valor extraido: " + a);
        Integer b = instance.put(4, 2);
        System.out.println("Valor extraido: " + b);
        Integer c = instance.put(326, 3);

        Iterable<Entry<Integer,Integer>> listaEntradas = instance.entrySet();
        
        for(Entry<Integer, Integer> v : listaEntradas){
        }
        
        assertTrue(instance.size()==3);

    }

    /**
     * Test of isEmpty method, of class HashTableMapSC.
     */
    @Test
    public void testIsEmpty() {
        
        System.out.println("isEmpty");

        assertTrue(instance.isEmpty());
        
        Integer a = instance.put(4, 2);
        
        assertFalse(instance.isEmpty());
        
        Iterable<Entry<Integer,Integer>> listaEntradas = instance.entrySet();
        
        for(Entry<Integer, Integer> v : listaEntradas){
        }
              
    }

    /**
     * Test of put method, of class HashTableMapSC.
     */
    @Test
    public void testPut() {
        
        System.out.println("put");

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
        
        Iterable<Entry<Integer,Integer>> listaEntradas = instance.entrySet();
        
        for(Entry<Integer, Integer> v : listaEntradas){
        }
        
    }

    /**
     * Test of get method, of class HashTableMapSC.
     */
    @Test
    public void testGet() {
        
        System.out.println("get");

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
        
        Integer g = instance.get(1_883);
        System.out.println("Valor extraido: " + g);
        assertTrue(g==null);
        
        Integer busq = 326;
        Integer h = instance.get(busq);
        assertTrue(h==3);
             
        Iterable<Entry<Integer,Integer>> listaEntradas = instance.entrySet();
        
        for(Entry<Integer, Integer> v : listaEntradas){
        }
    
    }

    /**
     * Test of remove method, of class HashTableMapSC.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");

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
        
        Integer g = instance.remove(1_883);
        System.out.println("Valor extraido: " + g);
        assertTrue(g==null);
        
        Integer h = instance.remove(326);
        assertTrue(h==3);
             
        Iterable<Entry<Integer,Integer>> listaEntradas = instance.entrySet();
        
        for(Entry<Integer, Integer> v : listaEntradas){
        }
        
    }

    /**
     * Test of keySet method, of class HashTableMapSC.
     */
    @Test
    public void testKeySet() {
        
        System.out.println("keySet");

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
        
        Iterable<Integer> listaClaves = instance.keySet();
        
        for(Integer v : listaClaves){
        }
    
    }

    /**
     * Test of values method, of class HashTableMapSC.
     */
    @Test
    public void testValues() {
        
        System.out.println("values");
        
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
        
        Iterable<Integer> listaValores = instance.values();
        
        for(Integer v : listaValores){
        }
    
    }

    /**
     * Test of entrySet method, of class HashTableMapSC.
     */
    @Test
    public void testEntrySet() {
        
        System.out.println("entrySet");

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
        
        Iterable<Entry<Integer,Integer>> listaEntradas = instance.entrySet();
        
        for(Entry<Integer, Integer> v : listaEntradas){
        }
        
    }

    /**
     * Test of rehash method, of class HashTableMapSC.
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
        
        Iterable<Entry<Integer,Integer>> listaEntradas = instance.entrySet();
        
        for(Entry<Integer, Integer> v : listaEntradas){
        }
        
        assertTrue(instance.capacity==10);
    
    }

}
