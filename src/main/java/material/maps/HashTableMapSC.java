package material.maps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import material.maps.exceptions.InvalidKeyException;

/**
 *
 * @author Asus
 * @param <K>
 * @param <V>
 */
public class HashTableMapSC<K, V> implements Map<K, V> {

    //Atributos (6).
    /**
     *
     */
    protected List<HashEntry<K, V>>[] bucket;// bucket array
    /**
     *
     */
    protected int n = 0; // number of entries in the dictionary
    /**
     *
     */
    protected int prime,
    /**
     *
     */
    capacity; // prime factor and capacity of bucket array
    /**
     *
     */
    protected long scale,
    /**
     *
     */
    shift; // the shift and scaling factors

    //Constructores.
    /**
     * Creates a hash table with prime factor 109345121 and capacity 1000.
     */
    public HashTableMapSC() {
        this(109_345_121, 1_000); // reusing the constructor HashTableMapSC(int p,
        // int cap)
    }

    /**
     * Creates a hash table with prime factor 109345121 and given capacity.
     */
    public HashTableMapSC(int cap) {
        this(109_345_121, cap); // reusing the constructor HashTableMapSC(int p, int
        // cap)
    }

    /**
     * Creates a hash table with the given prime factor and capacity.
     */
    public HashTableMapSC(int p, int cap) {

        //n ya esta inicializada.

        bucket = new List[cap];
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new ArrayList<>();
        }
        prime = p;
        capacity = cap;
        java.util.Random rand = new java.util.Random();
        scale = rand.nextInt(prime - 1) + 1;
        shift = rand.nextInt(prime);

    }

    //Métodos que heredan de Map.
    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return (n == 0);
    }

    @Override
    public V put(K key, V value) throws InvalidKeyException {
        V oldValue = null;
        if (n / capacity >= 0.75) { //factor de carga para encadenamiento separado.
            rehash(); // rehash to keep the load factor <= 0.75
        }
        checkKey(key);
        int i = hashValue(key);
        if (!bucket[i].isEmpty()) {
            int listIndex = getIndexWithKey(bucket[i], key);
            if (listIndex != -1) {
                oldValue = bucket[i].get(listIndex).getValue();
                bucket[i].remove(listIndex);
            }
        }
        bucket[i].add(new HashEntry<>(key, value));
        n++;
        return oldValue;
    }

    @Override
    public V get(K key) throws InvalidKeyException {
        checkKey(key);
        int i = hashValue(key);
        if (bucket[i].isEmpty()) {
            return null;
        }
        int listIndex = getIndexWithKey(bucket[i], key);
        if (listIndex == -1) {
            return null;
        }
        return bucket[i].get(listIndex).getValue();
    }

    @Override
    public V remove(K key) throws InvalidKeyException {
        checkKey(key);
        int i = hashValue(key);
        if (bucket[i].isEmpty()) {
            return null;
        }
        int listIndex = getIndexWithKey(bucket[i], key);
        if (listIndex == -1) {
            return null;
        }
        V oldValue = bucket[i].get(listIndex).getValue();
        bucket[i].remove(listIndex);
        n--;
        return oldValue;
    }

    @Override
    public Iterable<K> keySet() {
        List<K> keys = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            if (!bucket[i].isEmpty()) {
                for(int j = 0; j < bucket[i].size(); j++){
                    keys.add(bucket[i].get(j).getKey());
                }                
            }
        }
        return keys;
    }

    @Override
    public Iterable<V> values() {
        List<V> values = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            if (!bucket[i].isEmpty()) {
                for(int j = 0; j < bucket[i].size(); j++){
                    values.add(bucket[i].get(j).getValue());
                }                
            }
        }
        return values;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        List<Entry<K, V>> entries = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            if (!bucket[i].isEmpty()) {
                for(int j = 0; j < bucket[i].size(); j++){
                    entries.add(bucket[i].get(j));
                }                
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

    //Métodos adicionales.
    
    /**
     *
     */
    protected void rehash() {
        int doubleCapacity = this.capacity*2;
        
        HashTableMapSC<K,V> nuevoMapa = new HashTableMapSC<> (this.prime, doubleCapacity);
        
        List <Entry<K,V>> listaEntradas = (List <Entry<K,V>>) this.entrySet();
                
        for (Entry<K,V> e : listaEntradas){
            nuevoMapa.put(e.getKey(), e.getValue());
        }
        
        //El nuevo objeto es el objeto local (this).
        
        this.bucket=nuevoMapa.bucket; 
        this.capacity=nuevoMapa.capacity; 
        this.scale=nuevoMapa.scale;
        this.shift=nuevoMapa.shift;
        
        //n y prime se mantienen.  
    }

    //Métodos hechos por el autor.
    
    private int getIndexWithKey(List<HashEntry<K, V>> list, K key) {
        checkKey(key);
        if (list == null) {
            throw new NullPointerException("La lista no esta inicializada.");
        }
        int cont = 0;
        Iterator<HashEntry<K, V>> it = list.iterator();
        while (it.hasNext()) {
            HashEntry<K, V> actual = it.next();
            if (actual.getKey().equals(key)) { 
                //El uso del equals nos evita la aparición de claves repetidas.
                return cont;
            }
            cont++;
        }
        return -1;
    }

}
