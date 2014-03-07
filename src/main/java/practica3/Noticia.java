package practica3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Asus
 */
public class Noticia {
    
    //Atributos.
    
    private String titulo;
    private List <Entidad> listaEntidades;
    private Entidad entidadMasNombrada;

    //Constructores.
    
    /**
     *
     */
    public Noticia (){
        this.titulo="";
        this.listaEntidades = new ArrayList<>();
        this.entidadMasNombrada = new Entidad ();
    }
    
    /**
     *
     * @param titulo
     * @param listaEntidades
     * @param entidadMasNombrada
     */
    public Noticia(String titulo, List <Entidad> listaEntidades, Entidad entidadMasNombrada) {
        this.titulo = titulo;
        this.listaEntidades = listaEntidades;
        this.entidadMasNombrada = entidadMasNombrada;
    }

    //Getter & Setter.
    
    /**
     *
     * @return
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     *
     * @param titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     *
     * @return
     */
    public List<Entidad> getListaEntidades() {
        return Collections.unmodifiableList(listaEntidades);
    }

    /**
     *
     * @param listaEntidades
     */
    public void setListaEntidades(List<Entidad> listaEntidades) {
        this.listaEntidades = listaEntidades;
    }

    /**
     *
     * @return
     */
    public Entidad getEntidadMasNombrada() {
        return entidadMasNombrada;
    }

    /**
     *
     * @param entidadMasNombrada
     */
    public void setEntidadMasNombrada(Entidad entidadMasNombrada) {
        this.entidadMasNombrada = entidadMasNombrada;
    }
    
    //Métodos.
    
    @Override
    public String toString(){
        String eN = "";
        for(Entidad e: this.getListaEntidades()){
            eN += e.toString() + " ";
        }
        String toReturn = "Noticia: \n" +
                "Titulo: " + this.getTitulo() + "\n" + 
                "Entidades nombradas: " + eN + "\n" +
                "Entidad mas nombrada: " + this.getEntidadMasNombrada().toString() + "\n" +
                "Formato de entidad: <Nombre de entidad, Frecuencia de apariciones>";
        return toReturn;
    }
      
}
