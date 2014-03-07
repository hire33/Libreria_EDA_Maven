package material.tree.linkedtree;

import material.tree.Position;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Asus
 */
public class LinkedTreeTest_D {


	/**
     *
     */
    @Test
	public void testSize() {
		LinkedTree <String> t = new LinkedTree <>();
		Position <String> p = t.addRoot("+");
		t.add("2",p);
		Position <String> h = t.add("*",p);
		t.add("3",h);
		t.add("5",h);
		assertEquals(t.size(), 5);
	}

	/**
     *
     */
    @Test
	public void testIsEmpty() {
		LinkedTree <String> t = new LinkedTree <>();
		assertEquals(t.isEmpty(), true);
	}

	/**
     *
     */
    @Test
	public void testPositions() {
		LinkedTree <String> t = new LinkedTree <>();
		Position <String> p = t.addRoot("+");
		t.add("2",p);
		t.add("3",p);
		String salida = "";
		for (Position <String> e : t.positions()) {
			salida += e.element();
		}
		assertEquals(salida, "+23");
	}

	/**
     *
     */
    @Test
	public void testRemove() {
		LinkedTree <String> t = new LinkedTree <>();
		Position <String> p = t.addRoot("+");
		Position <String> q = t.add("2",p);
		Position <String> h = t.add("*",p);
		t.add("3",h);
		t.add("5",h);
		t.remove(h);
		assertEquals(t.size(), 2);
		
	}

	/**
     *
     */
    @Test
	public void testSwapElements() {
		LinkedTree <String> t = new LinkedTree <>();
		Position <String> p = t.addRoot("+");
		Position <String> p1 = t.add("2",p);
		Position <String> p2 = t.add("3",p);
		t.swapElements(p1, p2);
		String salida = "";
		for (Position <String> e : t.positions()) {
			salida += e.element();
		}
		assertEquals(salida, "+32");
	}

}
