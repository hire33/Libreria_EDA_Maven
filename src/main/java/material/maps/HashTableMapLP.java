package material.maps;

import java.util.List;
import material.maps.exceptions.InvalidKeyException;

/**
 *
 * @author Asus
 * @param <K>
 * @param <V>
 */
public class HashTableMapLP <K,V> extends AbstractHashTableMap <K,V> {
    
    //Constructores.
    
    /**
     *
     */
    public HashTableMapLP(){
        super();
    }
    
    /**
     *
     * @param cap
     */
    public HashTableMapLP(int cap){
        super(cap);   
    }
    
    /**
     *
     * @param p
     * @param cap
     */
    public HashTableMapLP(int p, int cap){
        super(p, cap);
    }
    
    //Métodos abstractos.

    @Override
    protected void rehash() {
               
        int doubleCapacity = this.capacity*2;
        
        AbstractHashTableMap<K,V> nuevoMapa = new HashTableMapLP<> (this.prime, doubleCapacity);
        
        List <Entry<K,V>> listaEntradas = (List <Entry<K,V>>) this.entrySet();
                
        for (Entry<K,V> e : listaEntradas){
            nuevoMapa.put(e.getKey(), e.getValue());
        }
        
        //El nuevo objeto es el objeto local (this).
        
        this.bucket=nuevoMapa.bucket; 
        this.capacity=nuevoMapa.capacity; 
        this.scale=nuevoMapa.scale;
        this.shift=nuevoMapa.shift;
        
        //n, prime y AVAILABLE se mantienen.
    
    }

    /**
     * Collision solved with linear probe - returns index of found key or -(a +
     * 1), where a is * the index of the first empty or available slot found.
     * The index value is negative because it is needed to distinguish when the
     * key is in the table (positive) and when is not (negative)
     */
    @Override
    protected HashEntryPosition findEntry(K key) throws InvalidKeyException {
        int avail = -1;
        checkKey(key);
        int i = hashValue(key);
        final int j = i;
        do {
            Entry<K, V> e = bucket[i];
            if (e == null) {
                if (avail < 0) {
                    avail = i; // key is not in table
                }
                break;
            } else if (key.equals(bucket[i].getKey())) // we have found our key
            {
                return new AbstractHashTableMap.HashEntryPosition(i, AbstractHashTableMap.OperationType.found); // key found
            } else if (bucket[i] == AVAILABLE) { // bucket is deactivated
                if (avail < 0) {
                    avail = i; // remember that this slot is available
                }
            }
            i = (i + 1) % capacity; // keep looking
        } while (i != j);
        return new AbstractHashTableMap.HashEntryPosition(avail, AbstractHashTableMap.OperationType.notFound); // first empty or available slot
    }
    
}
