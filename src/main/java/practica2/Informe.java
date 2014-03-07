package practica2;

public class Informe {
    
    //Atributos.
    
    private int expediente;
    private double problemas, examen, asistencia, voluntarios;
    
    //Constructores.
    
    public Informe (int expediente, double asistencia, double problemas, double examen, double voluntarios) {
        this.asistencia = asistencia;
        this.voluntarios = voluntarios;
        this.expediente = expediente;
        this.problemas = problemas;
        this.examen = examen;
    }
    
    //Getter & Setter.

    public double getProblemas() {
        return problemas;
    }

    public void setProblemas(double problemas) {
        this.problemas = problemas;
    }

    public double getExamen() {
        return examen;
    }

    public void setExamen(double examen) {
        this.examen = examen;
    }

    public double getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(double asistencia) {
        this.asistencia = asistencia;
    }

    public double getVoluntarios() {
        return voluntarios;
    }

    public void setVoluntarios(double voluntarios) {
        this.voluntarios = voluntarios;
    }

    public int getExpediente() {
        return expediente;
    }

    public void setExpediente(int expediente) {
        this.expediente = expediente;
    }

}
