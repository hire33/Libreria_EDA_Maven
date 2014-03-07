package material.maps;

/**
 *
 * @author Asus
 * @param <K>
 * @param <V>
 */
public class HashEntry<K, V> implements Entry<K, V> {

    //Atributos.
    
    /**
     *
     */
    protected K key;
    /**
     *
     */
    protected V value;

    //Constructores.
    
    /**
     *
     * @param k
     * @param v
     */
    public HashEntry(K k, V v) {
        key = k;
        value = v;
    }

    //Getter & Setter.
    
    @Override
    public V getValue() {
        return value;
    }

    @Override
    public K getKey() {
        return key;
    }

    /**
     *
     * @param val
     * @return
     */
    public V setValue(V val) {
        V oldValue = value;
        value = val;
        return oldValue;
    }
    
    //Métodos.
    
    @Override
    public boolean equals (Object o){
        HashEntry<K,V> entry;
        try{
            entry = (HashEntry<K,V>) o;
        } catch (ClassCastException cCE){
            return false;
        }
        return ( (entry.getKey()==this.getKey()) && (entry.getValue().equals(this.getValue())) );    
    }

    /**
     * Entry visualization.
     */
    @Override
    public String toString() {
        return "(" + key + "," + value + ")";
    }
    
}