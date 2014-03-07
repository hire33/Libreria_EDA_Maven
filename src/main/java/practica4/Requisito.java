package practica4;

/**
 *
 * @author Asus
 */
public class Requisito {
    
    //Atributos.
    
    private String criterio;
    private Double rangoInf;
    private Double rangoSup;
    
    //Constructores.

    /**
     *
     * @param criterio
     * @param rangoInf
     * @param rangoSup
     */
    public Requisito(String criterio, Double rangoInf, Double rangoSup) {
        this.criterio = criterio;
        this.rangoInf = rangoInf;
        this.rangoSup = rangoSup;
    }
    
    //Getter & Setter.

    /**
     *
     * @return
     */
    public String getCriterio() {
        return criterio;
    }

    /**
     *
     * @param criterio
     */
    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    /**
     *
     * @return
     */
    public Double getRangoInf() {
        return rangoInf;
    }

    /**
     *
     * @param rangoInf
     */
    public void setRangoInf(Double rangoInf) {
        this.rangoInf = rangoInf;
    }

    /**
     *
     * @return
     */
    public Double getRangoSup() {
        return rangoSup;
    }

    /**
     *
     * @param rangoSup
     */
    public void setRangoSup(Double rangoSup) {
        this.rangoSup = rangoSup;
    }
    
}
