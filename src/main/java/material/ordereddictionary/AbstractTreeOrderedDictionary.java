package material.ordereddictionary;

import java.util.ArrayList;
import java.util.List;
import material.maps.Entry;
import material.maps.exceptions.InvalidEntryException;
import material.maps.exceptions.InvalidKeyException;
import material.tree.Position;
import material.tree.binarysearchtree.BinarySearchTree;

/**
 * Lo único que varia de una implementación de la clase AbstractTreeOrderedDictionary
 * a otra es el tipo de árbol binario de búsqueda que utilizamos.
 */
abstract class AbstractTreeOrderedDictionary<K,V> implements OrderedDictionary<K,V> {

    //Atributos.
    
    protected BinarySearchTree<BSTDictEntry<K,V>> binTree; 
    protected int size;
   
    //Constructores.
    
    AbstractTreeOrderedDictionary (BinarySearchTree<BSTDictEntry<K,V>> binTree){
        this.binTree = binTree;
        this.size = 0;
    }
    
    //Métodos.
    
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size==0);
    }

    @Override
    public Entry<K, V> find(K key) throws InvalidKeyException {
        this.checkKey(key);
        BSTDictEntry entrada = new BSTDictEntry<>(key, null, null);
        Position<BSTDictEntry<K,V>> pos = this.binTree.find(entrada);
        if(pos==null){
            throw new InvalidKeyException ("No existe la entrada buscada.");
        }
        entrada.setValue(pos.element().getValue());
        entrada.setPos(pos);
        return entrada;  
    }

    @Override
    public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
        this.checkKey(key);
        BSTDictEntry entrada = new BSTDictEntry<>(key, null, null);
        Iterable<Position<BSTDictEntry<K, V>>> findAll = this.binTree.findAll(entrada);
        List l = new ArrayList<>();
        for(Position<BSTDictEntry<K, V>> p : findAll){
            BSTDictEntry aux = p.element();
            aux.setPos(p);
            l.add(aux);
        }
        return l;   
    }
    
    @Override
    public Iterable<Entry<K,V>> findRange(K minkey, K maxkey) throws InvalidKeyException{
        this.checkKey(minkey);
        this.checkKey(maxkey);
        BSTDictEntry minEntrada = new BSTDictEntry<>(minkey, null, null);
        BSTDictEntry maxEntrada = new BSTDictEntry<>(maxkey, null, null);
        Iterable<Position<BSTDictEntry<K, V>>> listaEntradas = this.binTree.findRange(minEntrada, maxEntrada);
        List l = new ArrayList<>();
        for(Position<BSTDictEntry<K, V>> p : listaEntradas){
            BSTDictEntry aux = p.element();
            aux.setPos(p);
            l.add(aux);
        }
        return l; 
    };

    @Override
    public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
        
        /**
         * Tal y como esta hecho el método compare, se deberia poder introducir
         * una entrada con clave repetida sin problemas (siempre y cuando los 
         * valores sean distintos).
         */
        
        checkKey(key);
        BSTDictEntry entrada = new BSTDictEntry<>(key, value, null);
        Position<BSTDictEntry<K,V>> pos = this.binTree.insert(entrada);
        entrada.setPos(pos);
        this.size++;
        return entrada;       
    
    }

    @Override
    public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
        BSTDictEntry<K,V> toFind = (BSTDictEntry<K,V>) e;
        Position<BSTDictEntry<K,V>> pos = this.binTree.find(toFind);
        this.binTree.remove(pos);
        this.size--;
        return toFind;        
    }

    @Override
    public Iterable<Entry<K, V>> entries() {
        
        //Lo hago asi ante la imposibilidad de hacer un casting directo.
        
        Iterable<BSTDictEntry<K, V>> listaEntradas = this.binTree.values();
        List <Entry<K,V>> listaAdaptada = new ArrayList<> ();
        for(BSTDictEntry<K, V> v : listaEntradas){
            listaAdaptada.add(v);
        }
        return listaAdaptada;
    
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
    
}
