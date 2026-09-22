package ProgramacionObject.Relacion3;

public class Embarcacion {
    private String matriculaEmbarcacion;
    private String nombreEmbarcacion;
    private double eslora;
    private String tipoEmbarcacion;
    private String propietario;
    private int anioFabricacionEmbarcacion;
    public double valorEstimado;

    public Embarcacion(String matriculaEmbarcacion, String nombreEmbarcacion, double eslora, String tipoEmbarcacion, String propietario, int anioFabricacionEmbarcacion, double valorEstimado) {
        this.matriculaEmbarcacion = matriculaEmbarcacion;
        this.nombreEmbarcacion = nombreEmbarcacion;
        this.eslora = eslora;
        this.tipoEmbarcacion = tipoEmbarcacion;
        this.propietario = propietario;
        this.anioFabricacionEmbarcacion = anioFabricacionEmbarcacion;
        this.valorEstimado = valorEstimado;
    }

    // Getters y setters para los campos privados
    public String getMatriculaEmbarcacion() {
        return matriculaEmbarcacion;
    }

    public String getnombreEmbarcacion() {
        return nombreEmbarcacion;
    }

    public double getEslora() {
        return eslora;
    }

    public String gettipoEmbarcacion() {
        return tipoEmbarcacion;
    }

    public String getPropietario() {
        return propietario;
    }

    public int getanioFabricacionEmbarcacion() {
        return anioFabricacionEmbarcacion;
    }

    //-===============================Setters======================================================//

    public void setnombreEmbarcacion(String nombreEmbarcacion) {
        this.nombreEmbarcacion = nombreEmbarcacion;
    }
    public void setEslora(double eslora) {
        this.eslora = eslora;
    }
    public void settipoEmbarcacion(String tipoEmbarcacion) {
        this.tipoEmbarcacion = tipoEmbarcacion;
    }
    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }
    public void setanioFabricacionEmbarcacion(int anioFabricacionEmbarcacion) {
        this.anioFabricacionEmbarcacion = anioFabricacionEmbarcacion;
    }
    public void setValorEstimado(double valorEstimado) {
        this.valorEstimado = valorEstimado;
    }

    //===============================toString======================================================//
 public String toString() {
        return "Embarcacion{" +
                "matriculaEmbarcacion='" + matriculaEmbarcacion + '\'' +
                ", nombreEmbarcacion='" + nombreEmbarcacion + '\'' +
                ", eslora=" + eslora +
                ", tipoEmbarcacion='" + tipoEmbarcacion + '\'' +
                ", propietario='" + propietario + '\'' +
                ", anioFabricacionEmbarcacion=" + anioFabricacionEmbarcacion +
                ", valorEstimado=" + valorEstimado +
                '}';
    }

    
}
