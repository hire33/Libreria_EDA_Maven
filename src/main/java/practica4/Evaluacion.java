package practica4;

import java.util.List;

public class Evaluacion {
    
    //Atributos.
    
    private List<Requisito> listaRequisitos;
    
    //Constructores.

    public Evaluacion(List<Requisito> listaRequisitos) {
        this.listaRequisitos = listaRequisitos;
    }
    
    //Getter & Setter.

    public List<Requisito> getListaRequisitos() {
        return listaRequisitos;
    }

    public void setListaRequisitos(List<Requisito> listaRequisitos) {
        this.listaRequisitos = listaRequisitos;
    }
       
}
