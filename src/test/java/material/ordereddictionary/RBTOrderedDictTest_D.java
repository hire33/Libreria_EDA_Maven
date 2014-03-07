package material.ordereddictionary;

import java.util.TreeSet;
import material.maps.Entry;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Asus
 */
public class RBTOrderedDictTest_D {

	/**
     *
     */
    @Test
	public void testSize() {
		OrderedDictionary <String,Integer> dict = new RBOrderedDictionary <>();
		assertEquals(dict.size(),0);
		dict.insert("Angel", 9_151_592);
		assertEquals(dict.size(),1);
		dict.insert("Angel", 9_151_591);
		assertEquals(dict.size(),2);
		dict.insert("Jose", 9_100_000);	
		assertEquals(dict.size(),3);
	}

	/**
     *
     */
    @Test
	public void testIsEmpty() {
		OrderedDictionary <String,Integer> dict = new RBOrderedDictionary <>();
		assertEquals(dict.isEmpty(),true);
		dict.insert("Angel", 9_151_592);
		assertEquals(dict.isEmpty(),false);
	}

	/**
     *
     */
    @Test
	public void testFind() {
		OrderedDictionary <String,Integer> dict = new RBOrderedDictionary <>();
		dict.insert("Angel", 9_151_592);
		dict.insert("Angel", 9_151_591);
		dict.insert("Jose", 9_100_000);
		Entry <String,Integer> contacto = dict.find("Angel");
		assertEquals(contacto.getValue().intValue(), 9_151_591);		
	}

	/**
     *
     */
    @Test
	public void testFindAll() {
		OrderedDictionary <String,Integer> dict = new RBOrderedDictionary <>();
		int [] telefono = {9_151_592, 9_151_591, 9_151_593};		
		dict.insert("Angel", telefono[0]);
		dict.insert("Angel", telefono[1]);
		dict.insert("Jose",  telefono[2]);
		TreeSet <Integer> cjtoTelefonos = new TreeSet <>();
		for (int cont = 0; cont < 3; cont++) {
                cjtoTelefonos.add(telefono[cont]);
            }
		
		Iterable<Entry <String,Integer>> it = dict.findAll("Angel");
		for (Entry <String,Integer> contacto : it) {
			assertEquals(cjtoTelefonos.contains(contacto.getValue()),true);		
		}
	}

	/**
     *
     */
    @Test
	public void testInsert() {
		OrderedDictionary <Integer,Integer> dict = new RBOrderedDictionary <>();
		for (int cont = 0; cont < 1_000; cont++) {
			dict.insert((int)(Math.random()*1_000), cont);
		}
	}

	/**
     *
     */
    @Test
	public void testRemove() {
		OrderedDictionary <String,Integer> dict = new RBOrderedDictionary <>();
		int [] telefono = {9_151_592, 9_151_591, 9_151_593};		
		dict.insert("Angel", telefono[0]);
		dict.insert("Angel", telefono[1]);
		dict.insert("Jose",  telefono[2]);
		Entry <String,Integer> e1 = dict.find("Jose");
		dict.remove(e1);
		Entry <String,Integer> f1 = dict.find("Jose");
		assertEquals(f1,null);		
		assertEquals(dict.size(),2);		
		Entry <String,Integer> e2 = dict.find("Angel");
		dict.remove(e2);
		assertEquals(dict.size(),1);		
		Entry <String,Integer> e3 = dict.find("Angel");
		dict.remove(e3);
		assertEquals(dict.size(),0);
	}

	/**
     *
     */
    @Test
	public void testRemoveUno() {
		OrderedDictionary <String,Integer> dict = new RBOrderedDictionary <>();
		dict.insert("Angel", 9_151_592);
		Entry <String,Integer> e = dict.find("Angel");
		dict.remove(e);
		Entry <String,Integer> f = dict.find("Angel");
		assertEquals(f,null);		
	}

	/**
     *
     */
    @Test
	public void testRemoveDos() {
		OrderedDictionary <String,Integer> dict = new RBOrderedDictionary <>();
		dict.insert("Jose", 9_151_590);
		dict.insert("Angel", 9_151_592);
		dict.insert("Angel", 9_151_591);
		Entry <String,Integer> e0 = dict.find("Jose");
		Entry <String,Integer> e1 = dict.find("Angel");
		Entry <String,Integer> e2 = dict.find("Angel");

		dict.remove(e0);
		dict.remove(e1);
		dict.remove(e2);
		Entry <String,Integer> f = dict.find("Angel");
		assertEquals(f,null);		
	}
	
	
}
