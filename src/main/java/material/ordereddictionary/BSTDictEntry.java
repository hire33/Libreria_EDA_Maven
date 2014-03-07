package material.ordereddictionary;

import material.maps.*;
import material.tree.Position;

/**
 *
 * @author Asus
 * @param <K>
 * @param <V>
 */
public class BSTDictEntry<K, V> implements Entry<K, V> {

    //Atributos.
    
    /**
     *
     */
    protected K key;
    /**
     *
     */
    protected V value;
    /**
     *
     */
    protected Position<Entry<K,V>> pos;

    //Constructores.

    /**
     *
     * @param key
     * @param value
     * @param pos
     */
    public BSTDictEntry(K key, V value, Position<Entry<K, V>> pos) {
        this.key = key;
        this.value = value;
        this.pos = pos;
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

    /**
     *
     * @return
     */
    public Position<Entry<K, V>> getPos() {
        return pos;
    }

    /**
     *
     * @param pos
     */
    public void setPos(Position<Entry<K, V>> pos) {
        this.pos = pos;
    }
    
    //Métodos.

    /**
     * Entry visualization.
     */
    @Override
    public String toString() {
        return "(" + key + "," + value + ")";
    }
    
    @Override
    public boolean equals (Object o){
        BSTDictEntry<K,V> entry;
        try{
            entry = (BSTDictEntry<K,V>) o;
        } catch (ClassCastException cCE){
            return false;
        }
        return ( (entry.getKey()==this.getKey()) && (entry.getValue().equals(this.getValue())) );    
    }
    
}