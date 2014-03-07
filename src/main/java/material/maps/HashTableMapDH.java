package material.maps;
import java.util.List;
import material.maps.exceptions.InvalidKeyException;

/**
 *
 * @author Asus
 * @param <K>
 * @param <V>
 */
public class HashTableMapDH <K,V> extends AbstractHashTableMap <K,V> {
    
    //Atributos.
    
    private int auxiliarPrime;
    
    //Constructores.
    
    /**
     *
     * @param p
     * @param cap
     * @param auxiliarPrime
     */
    public HashTableMapDH(int p, int cap, int auxiliarPrime){
        super(p, cap);
        this.auxiliarPrime=auxiliarPrime;
    }
    
    //Métodos abstractos.

    @Override
    protected void rehash() {
        
        int doubleCapacity = this.capacity*2;
        
        AbstractHashTableMap<K,V> nuevoMapa = new HashTableMapDH<> (this.prime, doubleCapacity, this.auxiliarPrime);
        
        List <Entry<K,V>> listaEntradas = (List <Entry<K,V>>) this.entrySet();
                
        for (Entry<K,V> e : listaEntradas){
            nuevoMapa.put(e.getKey(), e.getValue());
        }
        
        //El nuevo objeto es el objeto local (this).
        
        this.bucket=nuevoMapa.bucket; 
        this.capacity=nuevoMapa.capacity; 
        this.scale=nuevoMapa.scale;
        this.shift=nuevoMapa.shift;
        
        //n, prime, auxiliarPrime y AVAILABLE se mantienen.
    }
 
    /**
     * Collision solved with double hashing.
     */
    @Override
     protected AbstractHashTableMap.HashEntryPosition findEntry(K key) throws InvalidKeyException {

        /**
         * Los problemas comentados para prueba cuadrática también se pueden dar
         * para este caso, pero con mucha menos probabilidad.
         */
        
        int avail = -1;
        checkKey(key);
        int i = hashValue(key);
        final int j = i;
        int k = 0; //probe number
        final int aux = auxiliarHashValue(key);
        do {
            Entry<K, V> e = bucket[i];
            if (e == null) {
                if (avail < 0) {
                    avail = i; // key is not in table
                }
                break;
            } else if (key.equals(e.getKey())) // we have found our key
            {
                return new AbstractHashTableMap.HashEntryPosition(i, AbstractHashTableMap.OperationType.found); // key found
            } else if (e == AVAILABLE) { // bucket is deactivated
                if (avail < 0) {
                    avail = i; // remember that this slot is available
                }    
            }
            k++;
            i = (j + k*aux) % capacity; // keep looking
        } while (i != j);
        return new AbstractHashTableMap.HashEntryPosition(avail, AbstractHashTableMap.OperationType.notFound); // first empty or available slot
    }
    
    private int auxiliarHashValue(K key) {
        return (int) (auxiliarPrime -  ( (Math.abs(key.hashCode() * scale + shift) % auxiliarPrime) % capacity) ) ;
    }
    
}
