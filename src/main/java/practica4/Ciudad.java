package practica4;

import material.maps.Map;

public class Ciudad {
    
    //Atributos.
    
    private String nombre;
    private Map <String, Double> mapaCategorias;
    
    //Constructores.

    public Ciudad(String nombre, Map<String, Double> mapaCategorias) {
        this.nombre = nombre;
        this.mapaCategorias = mapaCategorias;
    }
    
    //Getter & Setter.

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Map<String, Double> getMapaCategorias() {
        return mapaCategorias;
    }

    public void setMapaCategorias(Map<String, Double> mapaCategorias) {
        this.mapaCategorias = mapaCategorias;
    }
     
}
