package practica4;

import material.maps.Map;

/**
 *
 * @author Asus
 */
public class Ciudad {
    
    //Atributos.
    
    private String nombre;
    private Map <String, Double> mapaCategorias;
    
    //Constructores.

    /**
     *
     * @param nombre
     * @param mapaCategorias
     */
    public Ciudad(String nombre, Map<String, Double> mapaCategorias) {
        this.nombre = nombre;
        this.mapaCategorias = mapaCategorias;
    }
    
    //Getter & Setter.

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public Map<String, Double> getMapaCategorias() {
        return mapaCategorias;
    }

    /**
     *
     * @param mapaCategorias
     */
    public void setMapaCategorias(Map<String, Double> mapaCategorias) {
        this.mapaCategorias = mapaCategorias;
    }
     
}
