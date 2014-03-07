package material.maps;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HashTableMapCPTest {
    
    private AbstractHashTableMap<Integer,Integer> instance;
    
    public HashTableMapCPTest() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {
        instance = new HashTableMapCP (11);
    }
    
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
        Integer d = instance.put(1983, 4);
        System.out.println("Valor extraido: " + d);
        Integer e = instance.put(2134, 5);
        System.out.println("Valor extraido: " + e);
        Integer f = instance.put(1983, 6);
        System.out.println("Valor extraido: " + f);
        Integer g = instance.put(534, 29);
        System.out.println("Valor extraido: " + g);
        
        Iterable<Entry<Integer,Integer>> listaEntradas = instance.entrySet();
        
        for(Entry<Integer, Integer> v : listaEntradas){
            System.out.print("[" + instance.hashValue(v.getKey()) + "]" + v + " ");
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
        Integer d = instance.put(1983, 4);
        System.out.println("Valor anterior: " + d);
        Integer e = instance.put(2134, 5);
        System.out.println("Valor anterior: " + e);
        Integer f = instance.put(1983, 6);
        System.out.println("Valor anterior: " + f);
        
        Integer g = instance.remove(2134);
        System.out.println("Valor borrado: " + g);
        
        Integer busq = 326;
        Integer h = instance.get(busq);
        System.out.println("Valor asociado a la clave " + busq + ": " + h);
        
        Iterable<Entry<Integer,Integer>> listaEntradas = instance.entrySet();
        
        for(Entry<Integer, Integer> v : listaEntradas){
            System.out.print("[" + instance.hashValue(v.getKey()) + "]" + v + " ");
        } 
    
    }

}
