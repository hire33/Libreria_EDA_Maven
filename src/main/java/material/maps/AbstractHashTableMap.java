package material.maps;

import java.util.ArrayList;
import java.util.List;
import material.maps.exceptions.InvalidKeyException;

abstract class AbstractHashTableMap <K,V> implements Map <K,V> {
      
    enum OperationType {
        found, notFound 
    };

    protected class HashEntryPosition {
        
        //Atributos.

        int index;
        AbstractHashTableMap.OperationType operation;

        //Constructores.
        
        public HashEntryPosition(int index, AbstractHashTableMap.OperationType operation) {
            super();
            this.index = index;
            this.operation = operation;
        }
    
    }
    
    //Atributos (7).
    
    protected HashEntry<K, V>[] bucket;// bucket array
    protected final Entry<K, V> AVAILABLE = new HashEntry<>(null, null);
    protected int n = 0; // number of entries in the dictionary
    protected int prime, capacity; // prime factor and capacity of bucket array
    protected long scale, shift; // the shift and scaling factors
   
    //Constructores.
      
    /**
     * Creates a hash table with prime factor 109345121 and capacity 1000.
     */
    public AbstractHashTableMap() {
        this(109345121, 1000); // reusing the constructor HashTableMap(int p,
        // int cap)
    }

    /**
     * Creates a hash table with prime factor 109345121 and given capacity.
     */
    public AbstractHashTableMap(int cap) {
        this(109345121, cap); // reusing the constructor HashTableMap(int p, int
        // cap)
    }

    /**
     * Creates a hash table with the given prime factor and capacity.
     */
    public AbstractHashTableMap(int p, int cap) {
       
        //AVAILABLE y n ya se encuentran inicializadas.
        
        bucket = (HashEntry<K,V>[]) new HashEntry[cap]; // safe cast using raw type 
        prime = p;
        capacity = cap;
        java.util.Random rand = new java.util.Random();
        scale = rand.nextInt(prime - 1) + 1;
        shift = rand.nextInt(prime);
    
    }
    
    //Métodos que heredan de Map.

    /**
     * Returns the number of entries in the hash table.
     */
    @Override
    public int size() {
        return n;
    }

    /**
     * Returns whether or not the table is empty.
     */
    @Override
    public boolean isEmpty() {
        return (n == 0);
    }

    /**
     * Returns the value associated with a key.
     */
    @Override
    public V get(K key) throws InvalidKeyException {
        AbstractHashTableMap.HashEntryPosition i = findEntry(key); // helper method for finding a key
        if (i.operation == AbstractHashTableMap.OperationType.notFound) {
            return null; // there is no value for this key, so return null
        }
        return bucket[i.index].getValue(); // return the found value in this case
    }

    /**
     * Put a key-value pair in the map, replacing previous one if it exists.
     */
    @Override
    public V put(K key, V value) throws InvalidKeyException {
        AbstractHashTableMap.HashEntryPosition i = findEntry(key); // find the appropriate spot for this entry
        if (i.operation == AbstractHashTableMap.OperationType.found) // this key has a previous value
        {
            return bucket[i.index].setValue(value); // set new value and return old
        } 
        else if (n >= capacity / 2) { //factor de carga para direccionamiento abierto.
            rehash(); // rehash to keep the load factor <= 0.5
            i = findEntry(key); // find again the appropriate spot for this
            // entry
        }
        bucket[i.index] = new HashEntry<>(key, value); // convert to proper
        // index
        n++;
        return null; // there was no previous value
    }

    /**
     * Removes the key-value pair with a specified key.
     */
    @Override
    public V remove(K key) throws InvalidKeyException {
        AbstractHashTableMap.HashEntryPosition i = findEntry(key); // find this key first
        if (i.operation == AbstractHashTableMap.OperationType.notFound) {
            return null; // nothing to remove
        }
        V toReturn = bucket[i.index].getValue();
        bucket[i.index] = (HashEntry<K, V>) AVAILABLE; // mark this slot as
        // reactivated
        n--;
        return toReturn;
    }

    /**
     * Returns an iterable object containing all of the keys.
     */
    @Override
    public Iterable<K> keySet() {
        List<K> keys = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            if ((bucket[i] != null) && (bucket[i] != AVAILABLE)) {
                keys.add(bucket[i].getKey());
            }
        }
        return keys;
    }
    
    /**
     * Returns an iterable object containing all of the values.
     */
    @Override
    public Iterable<V> values() {
        List<V> values = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            if ((bucket[i] != null) && (bucket[i] != AVAILABLE)) {
                values.add(bucket[i].getValue());
            }
        }
        return values;
    }
    
    /**
     * Returns an iterable object containing all of the entries.
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        List<Entry<K, V>> entries = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            if ((bucket[i] != null) && (bucket[i] != AVAILABLE)) {
                entries.add(bucket[i]);
            }
        }
        return entries;
    }
    
    //Métodos auxiliares.
    
     /**
     * Determines whether a key is valid.
     */
    protected void checkKey(K k) {
        if (k == null) {
            throw new InvalidKeyException("Invalid key: null.");
        }
    }

    /**
     * Hash function applying MAD method to default hash code.
     */
    protected int hashValue(K key) {
        return (int) ((Math.abs(key.hashCode() * scale + shift) % prime) % capacity);
    }
        
    //Métodos abstractos.
        
    abstract protected AbstractHashTableMap.HashEntryPosition findEntry(K key) throws InvalidKeyException; 
    
    /**
     * Doubles the size of the hash table and rehashes all the entries.
     */
    abstract protected void rehash(); 
    //Se declara abstracto debido a las distintas implementaciones del metodo findEntry.
    
    
}
