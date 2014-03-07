package material.maps;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HashTableMapSCTest {
    
    private HashTableMapSC<Integer,Integer> instance;
    
    public HashTableMapSCTest() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {
        instance = new HashTableMapSC(5);
    }
    
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
        System.out.println("Valor extraido: " + c);

        Iterable<Entry<Integer,Integer>> listaEntradas = instance.entrySet();
        
        for(Entry<Integer, Integer> v : listaEntradas){
            System.out.print("[" + instance.hashValue(v.getKey()) + "]" + v + " ");
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
        System.out.println("Valor extraido: " + a);
        
        assertFalse(instance.isEmpty());
        
        Iterable<Entry<Integer,Integer>> listaEntradas = instance.entrySet();
        
        for(Entry<Integer, Integer> v : listaEntradas){
            System.out.print("[" + instance.hashValue(v.getKey()) + "]" + v + " ");
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
        Integer d = instance.put(1983, 4);
        System.out.println("Valor extraido: " + d);
        Integer e = instance.put(2134, 5);
        System.out.println("Valor extraido: " + e);
        Integer f = instance.put(1983, 6);
        System.out.println("Valor extraido: " + f);
        
        Iterable<Entry<Integer,Integer>> listaEntradas = instance.entrySet();
        
        for(Entry<Integer, Integer> v : listaEntradas){
            System.out.print("[" + instance.hashValue(v.getKey()) + "]" + v + " ");
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
        Integer d = instance.put(1983, 4);
        System.out.println("Valor extraido: " + d);
        Integer e = instance.put(2134, 5);
        System.out.println("Valor extraido: " + e);
        Integer f = instance.put(1983, 6);
        System.out.println("Valor extraido: " + f);
        
        Integer g = instance.get(1883);
        System.out.println("Valor extraido: " + g);
        assertTrue(g==null);
        
        Integer busq = 326;
        Integer h = instance.get(busq);
        System.out.println("Valor asociado a la clave " + busq + ": " + h);
        assertTrue(h==3);
             
        Iterable<Entry<Integer,Integer>> listaEntradas = instance.entrySet();
        
        for(Entry<Integer, Integer> v : listaEntradas){
            System.out.print("[" + instance.hashValue(v.getKey()) + "]" + v + " ");
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
        Integer d = instance.put(1983, 4);
        System.out.println("Valor extraido: " + d);
        Integer e = instance.put(2134, 5);
        System.out.println("Valor extraido: " + e);
        Integer f = instance.put(1983, 6);
        System.out.println("Valor extraido: " + f);
        
        Integer g = instance.remove(1883);
        System.out.println("Valor extraido: " + g);
        assertTrue(g==null);
        
        Integer h = instance.remove(326);
        System.out.println("Valor extraido: " + h);
        assertTrue(h==3);
             
        Iterable<Entry<Integer,Integer>> listaEntradas = instance.entrySet();
        
        for(Entry<Integer, Integer> v : listaEntradas){
            System.out.print("[" + instance.hashValue(v.getKey()) + "]" + v + " ");
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
        Integer d = instance.put(1983, 4);
        System.out.println("Valor extraido: " + d);
        Integer e = instance.put(2134, 5);
        System.out.println("Valor extraido: " + e);
        Integer f = instance.put(1983, 6);
        System.out.println("Valor extraido: " + f);
        
        Iterable<Integer> listaClaves = instance.keySet();
        
        for(Integer v : listaClaves){
            System.out.print(v + " ");
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
        Integer d = instance.put(1983, 4);
        System.out.println("Valor extraido: " + d);
        Integer e = instance.put(2134, 5);
        System.out.println("Valor extraido: " + e);
        Integer f = instance.put(1983, 6);
        System.out.println("Valor extraido: " + f);
        
        Iterable<Integer> listaValores = instance.values();
        
        for(Integer v : listaValores){
            System.out.print(v + " ");
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
        Integer d = instance.put(1983, 4);
        System.out.println("Valor extraido: " + d);
        Integer e = instance.put(2134, 5);
        System.out.println("Valor extraido: " + e);
        Integer f = instance.put(1983, 6);
        System.out.println("Valor extraido: " + f);
        
        Iterable<Entry<Integer,Integer>> listaEntradas = instance.entrySet();
        
        for(Entry<Integer, Integer> v : listaEntradas){
            System.out.print("[" + instance.hashValue(v.getKey()) + "]" + v + " ");
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
        Integer d = instance.put(1983, 4);
        System.out.println("Valor extraido: " + d);
        Integer e = instance.put(2134, 5);
        System.out.println("Valor extraido: " + e);
        Integer f = instance.put(1983, 6);
        System.out.println("Valor extraido: " + f);
        
        Iterable<Entry<Integer,Integer>> listaEntradas = instance.entrySet();
        
        for(Entry<Integer, Integer> v : listaEntradas){
            System.out.print("[" + instance.hashValue(v.getKey()) + "]" + v + " ");
        }
        
        assertTrue(instance.capacity==10);
    
    }

}
