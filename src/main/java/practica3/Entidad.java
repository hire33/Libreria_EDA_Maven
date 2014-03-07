package practica3;

public class Entidad {
    
    //Atributos.
    
    private String nombre;
    private Integer frecuencia;
    
    //Constructores.
    
    public Entidad () {
        this.nombre="";
        this.frecuencia=0;
    }
    
    public Entidad(String nombre, Integer frecuencia) {
        this.nombre = nombre;
        this.frecuencia = frecuencia;
    }

    //Getter & Setter.
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }
    
    //Métodos.
    
    @Override
    public String toString(){        
        return ("<" + this.getNombre() + ", " + this.getFrecuencia().toString() + ">");
    }
    
}
