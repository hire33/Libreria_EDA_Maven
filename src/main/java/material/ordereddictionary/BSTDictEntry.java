package material.ordereddictionary;

import material.maps.*;
import material.tree.Position;

public class BSTDictEntry<K, V> implements Entry<K, V> {

    //Atributos.
    
    protected K key;
    protected V value;
    protected Position<Entry<K,V>> pos;

    //Constructores.

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

    public V setValue(V val) {
        V oldValue = value;
        value = val;
        return oldValue;
    }

    public Position<Entry<K, V>> getPos() {
        return pos;
    }

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