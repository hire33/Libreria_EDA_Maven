package material.tree.binarysearchtree;

import java.util.Comparator;

public class DefaultComparator<E> implements Comparator<E> {

    /**
     * Compares two given elements
     *
     * @return a negative integer if <tt>a</tt> is less than <tt>b</tt>, zero if
     * <tt>a</tt> equals <tt>b</tt>, or a positive integer if <tt>a</tt> is
     * greater than <tt>b</tt>
     */
    @Override
    public int compare(E a, E b) {
        if (a instanceof Comparable<?>) {
            return ((Comparable<E>) a).compareTo(b);
        } else {
            if (a.hashCode() == b.hashCode()) {
                return 0;
            } else if (a.hashCode() > b.hashCode()) {
                return 1;
            } else {
                return -1;
            }
        }
    }

}
