package practica4;

public class Requisito {
    
    //Atributos.
    
    private String criterio;
    private Double rangoInf;
    private Double rangoSup;
    
    //Constructores.

    public Requisito(String criterio, Double rangoInf, Double rangoSup) {
        this.criterio = criterio;
        this.rangoInf = rangoInf;
        this.rangoSup = rangoSup;
    }
    
    //Getter & Setter.

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public Double getRangoInf() {
        return rangoInf;
    }

    public void setRangoInf(Double rangoInf) {
        this.rangoInf = rangoInf;
    }

    public Double getRangoSup() {
        return rangoSup;
    }

    public void setRangoSup(Double rangoSup) {
        this.rangoSup = rangoSup;
    }
    
}
