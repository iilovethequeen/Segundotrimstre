package ProgramacionObject.Relacion3;

public class Amarre {

    private int numeroAmarre;
    private double longitudMaximaAmarre;
    private boolean ocupado;
    private double precioDia;
    private String tipoAmarre;

    public Amarre(int numeroAmarre, double longitudMaximaAmarre, boolean ocupado, double precioDia, String tipoAmarre) {
        this.numeroAmarre = numeroAmarre;
        this.longitudMaximaAmarre = longitudMaximaAmarre;
        this.ocupado = ocupado;
        this.precioDia = precioDia;
        this.tipoAmarre = tipoAmarre;
    }

    // Getters y setters para los campos privados
    public int getNumeroAmarre() {
        return numeroAmarre;
    }

    public double getlongitudMaximaAmarre() {
        return longitudMaximaAmarre;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public double getPrecioDia() {
        return precioDia;
    }

    public String gettipoAmarre() {
        return tipoAmarre;
    }

    public void setPrecioDia(double precioDia) {
        this.precioDia = precioDia;
    }

    public void settipoAmarre(String tipoAmarre) {
        this.tipoAmarre = tipoAmarre;
    }

    public String toString() {
        return "Amarre{" +
                "numeroAmarre=" + numeroAmarre +
                ", longitudMaximaAmarre=" + longitudMaximaAmarre +
                ", ocupado=" + ocupado +
                ", precioDia=" + precioDia +
                ", tipoAmarre='" + tipoAmarre + '\'' +
                '}';
    }




}
