public abstract class MiembroBanda {
    private String nombreArtistico;
    private String localidad;
    private int anosEnLaAgrupacion;
    private double puntosBase;

    public MiembroBanda(String nombreArtistico, String localidad,
                        int anosEnLaAgrupacion, double puntosBase) {
        this.nombreArtistico = nombreArtistico;
        this.localidad = localidad;
        this.anosEnLaAgrupacion = anosEnLaAgrupacion;
        this.puntosBase = puntosBase;
    }

    public abstract double calcularRendimiento();
    public abstract boolean estaDisponible();

    public void sumarAnos() {
        anosEnLaAgrupacion++;
    }

    public void sumarPuntosBase(double puntos) {
        if (puntos > 0) puntosBase += puntos;
    }

    // Getters y setters
    public String getNombreArtistico()        { return nombreArtistico; }
    public String getLocalidad()              { return localidad; }
    public int getAnosEnLaAgrupacion()        { return anosEnLaAgrupacion; }
    public double getPuntosBase()             { return puntosBase; }
    public void setNombreArtistico(String n)  { nombreArtistico = n; }
    public void setLocalidad(String l)        { localidad = l; }
    public void setAnosEnLaAgrupacion(int a)  { anosEnLaAgrupacion = a; }
    public void setPuntosBase(double p)       { puntosBase = p; }
}