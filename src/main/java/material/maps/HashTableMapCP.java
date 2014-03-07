package material.maps;
import java.util.List;
import material.maps.exceptions.InvalidKeyException;

/**
 *
 * @author Asus
 * @param <K>
 * @param <V>
 */
public class HashTableMapCP <K,V> extends AbstractHashTableMap <K,V> {
    
    //Atributos.
    
    private int constant1;
    private int constant2;
    
    //Constructores.
    
    /**
     *
     */
    public HashTableMapCP(){
        super();
        constant1=0;
        constant2=1;
    }
    
    /**
     *
     * @param cap
     */
    public HashTableMapCP(int cap){
        super(cap);
        constant1=0;
        constant2=1;
    }
    
    /**
     *
     * @param p
     * @param cap
     */
    public HashTableMapCP(int p, int cap){
        this(p, cap, 0, 1);
    }
    
    /**
     *
     * @param p
     * @param cap
     * @param constant1
     * @param constant2
     */
    public HashTableMapCP(int p, int cap, int constant1, int constant2){
        super(p, cap);
        this.constant1=constant1;
        this.constant1=constant2;
    }
    
    
    //Métodos abstractos.

    @Override
    protected void rehash() {
        
        int doubleCapacity = this.capacity*2;
        
        AbstractHashTableMap<K,V> nuevoMapa = new HashTableMapCP<> (this.prime, doubleCapacity);
        
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
     * Collision solved with cuadratic probe.
     */
    @Override
    protected AbstractHashTableMap.HashEntryPosition findEntry(K key) throws InvalidKeyException {
        
        /**
         * Para este metodo existen dos problemas que no aparecen para prueba
         * lineal : que nunca se vuelva a la posicion donde empezamos a buscar 
         * o que alguno de los elementos de la tabla se quede sin visitar.
         */
         
        int avail = -1;
        checkKey(key);
        int i = hashValue(key);
        final int j = i;
        int k = 0; //probe number
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
            i = (j + constant1*k + constant2*(k*k)) % capacity; // keep looking
        } while (i != j);
        return new AbstractHashTableMap.HashEntryPosition(avail, AbstractHashTableMap.OperationType.notFound); // first empty or available slot
    }

//    /**
//     * Collision solved with cuadratic probe
//     */
//    @Override
//    protected AbstractHashTableMap.HashEntryPosition findEntry(K key) throws InvalidKeyException {
//         
//        /*
//         * Intente solucionar los dos problemas antes mencionados con esta
//         * implementacion del metodo findKey.
//         */
//    
//        boolean [] visitados = new boolean[this.capacity];
//        int avail = -1;
//        checkKey(key);
//        int i = hashValue(key);
//        int num = i;
//        int j = 0; //probe number
//        do {
//            Entry<K, V> e = bucket[i];
//            if (e == null) {
//                if (avail < 0) {
//                    avail = i; // key is not in table
//                }
//                break;
//            } else if (key.equals(e.getKey())) // we have found our key
//            {
//                return new AbstractHashTableMap.HashEntryPosition(i, AbstractHashTableMap.OperationType.found); // key found
//            } else if (e == AVAILABLE) { // bucket is deactivated
//                if (avail < 0) {
//                    avail = i; // remember that this slot is available
//                }
//               if (!visitados[i]){
//                   visitados[i]=true;
//               }else{
//                   num = disponible(visitados);
//                   j=-1;
//               }    
//            
//            }
//            j++;
//            i = (num + constant1*j + constant2*(j*j)) % capacity; // keep looking
//        } while (!todosVisitados(visitados));
//        return new AbstractHashTableMap.HashEntryPosition(avail, AbstractHashTableMap.OperationType.notFound); // first empty or available slot
//    }
//    
//    private boolean todosVisitados(boolean[] visitados) {
//        
//        boolean todosVisitados = true;
//        int i=0;
//        while(i<visitados.length && todosVisitados){
//            todosVisitados = (visitados[i]==true);
//            i++;
//        }
//        return todosVisitados;
//     
//    }
//
//    private int disponible(boolean[] visitados) {
//       
//        int toReturn = -1;
//        
//        boolean yaVisitado = true;
//        
//        int i = 0;
//        while(i<visitados.length && yaVisitado){
//            yaVisitado=visitados[i];
//            if(yaVisitado){
//                i++;
//            } else {
//                return i;
//            }
//        }
//        
//        return toReturn;
//         
//    }
    
}