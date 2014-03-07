package practica3;

/**
 *
 * @author Asus
 */
public class Entidad {
    
    //Atributos.
    
    private String nombre;
    private Integer frecuencia;
    
    //Constructores.
    
    /**
     *
     */
    public Entidad () {
        this.nombre="";
        this.frecuencia=0;
    }
    
    /**
     *
     * @param nombre
     * @param frecuencia
     */
    public Entidad(String nombre, Integer frecuencia) {
        this.nombre = nombre;
        this.frecuencia = frecuencia;
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
    public Integer getFrecuencia() {
        return frecuencia;
    }

    /**
     *
     * @param frecuencia
     */
    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }
    
    //Métodos.
    
    @Override
    public String toString(){        
        return ("<" + this.getNombre() + ", " + this.getFrecuencia().toString() + ">");
    }
    
}
