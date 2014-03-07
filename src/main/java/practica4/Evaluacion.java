package practica4;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Asus
 */
public class Evaluacion {
    
    //Atributos.
    
    private List<Requisito> listaRequisitos;
    
    //Constructores.

    /**
     *
     * @param listaRequisitos
     */
    public Evaluacion(List<Requisito> listaRequisitos) {
        this.listaRequisitos = listaRequisitos;
    }
    
    //Getter & Setter.

    /**
     *
     * @return
     */
    public List<Requisito> getListaRequisitos() {
        return Collections.unmodifiableList(listaRequisitos);
    }

    /**
     *
     * @param listaRequisitos
     */
    public void setListaRequisitos(List<Requisito> listaRequisitos) {
        this.listaRequisitos = listaRequisitos;
    }
       
}
