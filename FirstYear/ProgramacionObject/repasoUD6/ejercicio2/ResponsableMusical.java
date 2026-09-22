import java.util.ArrayList;

public class ResponsableMusical extends MiembroBanda implements Coordinable, Revisable {
    private String repertorioSemana;
    private int numeroIntegrantesACargo;
    private ArrayList<MiembroBanda> integrantes;
    private int partiturasRevisadas;
    private int erroresDetectados;

    public ResponsableMusical(String nombreArtistico, String localidad,
                               int anosEnLaAgrupacion, double puntosBase,
                               String repertorioSemana) {
        super(nombreArtistico, localidad, anosEnLaAgrupacion, puntosBase);
        this.repertorioSemana = repertorioSemana;
        this.numeroIntegrantesACargo = 0;
        this.integrantes = new ArrayList<>();
        this.partiturasRevisadas = 0;
        this.erroresDetectados = 0;
    }

    @Override
    public double calcularRendimiento() {
        return getPuntosBase() + (getAnosEnLaAgrupacion() * 4)
                + (numeroIntegrantesACargo * 3)
                + (partiturasRevisadas * 2)
                - erroresDetectados;
    }

    @Override
    public boolean estaDisponible() {
        return erroresDetectados <= 5;
    }

    @Override
    public boolean asignarMiembro(MiembroBanda miembro) {
        if (miembro == null) return false;
        for (MiembroBanda m : integrantes) {
            if (m.getNombreArtistico().equals(miembro.getNombreArtistico())) return false;
        }
        integrantes.add(miembro);
        numeroIntegrantesACargo = integrantes.size();
        return true;
    }

    @Override
    public boolean quitarMiembro(String nombreArtistico) {
        for (MiembroBanda m : integrantes) {
            if (m.getNombreArtistico().equals(nombreArtistico)) {
                integrantes.remove(m);
                numeroIntegrantesACargo = integrantes.size();
                return true;
            }
        }
        return false;
    }

    @Override
    public void sumarPartiturasRevisadas() {
        partiturasRevisadas++;
    }

    @Override
    public void sumarErroresDetectados(int cantidad) {
        if (cantidad > 0) erroresDetectados += cantidad;
    }

    public double calcularRendimientoEquipo() {
        double total = 0;
        for (MiembroBanda m : integrantes) {
            total += m.calcularRendimiento();
        }
        return total;
    }

    // Getters y setters
    public String getRepertorioSemana()           { return repertorioSemana; }
    public int getNumeroIntegrantesACargo()        { return numeroIntegrantesACargo; }
    public int getPartiturasRevisadas()            { return partiturasRevisadas; }
    public int getErroresDetectados()              { return erroresDetectados; }
    public void setRepertorioSemana(String r)      { repertorioSemana = r; }
    public void setPartiturasRevisadas(int p)      { partiturasRevisadas = p; }
    public void setErroresDetectados(int e)        { erroresDetectados = e; }
}
