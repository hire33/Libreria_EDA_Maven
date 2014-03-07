package practica2;

/**
 *
 * @author Asus
 */
public class Informe {
    
    //Atributos.
    
    private int expediente;
    private double problemas, examen, asistencia, voluntarios;
    
    //Constructores.
    
    /**
     *
     * @param expediente
     * @param asistencia
     * @param problemas
     * @param examen
     * @param voluntarios
     */
    public Informe (int expediente, double asistencia, double problemas, double examen, double voluntarios) {
        this.asistencia = asistencia;
        this.voluntarios = voluntarios;
        this.expediente = expediente;
        this.problemas = problemas;
        this.examen = examen;
    }
    
    //Getter & Setter.

    /**
     *
     * @return
     */
    public double getProblemas() {
        return problemas;
    }

    /**
     *
     * @param problemas
     */
    public void setProblemas(double problemas) {
        this.problemas = problemas;
    }

    /**
     *
     * @return
     */
    public double getExamen() {
        return examen;
    }

    /**
     *
     * @param examen
     */
    public void setExamen(double examen) {
        this.examen = examen;
    }

    /**
     *
     * @return
     */
    public double getAsistencia() {
        return asistencia;
    }

    /**
     *
     * @param asistencia
     */
    public void setAsistencia(double asistencia) {
        this.asistencia = asistencia;
    }

    /**
     *
     * @return
     */
    public double getVoluntarios() {
        return voluntarios;
    }

    /**
     *
     * @param voluntarios
     */
    public void setVoluntarios(double voluntarios) {
        this.voluntarios = voluntarios;
    }

    /**
     *
     * @return
     */
    public int getExpediente() {
        return expediente;
    }

    /**
     *
     * @param expediente
     */
    public void setExpediente(int expediente) {
        this.expediente = expediente;
    }

}
