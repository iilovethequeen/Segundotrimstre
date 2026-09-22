public class Instrumentista extends MiembroBanda {
    private String instrumentoPrincipal;
    private int nivelTecnico;
    private int ensayosAsistidos;
    private int actuacionesDisponibles;

    public Instrumentista(String nombreArtistico, String localidad,
                          int anosEnLaAgrupacion, double puntosBase,
                          String instrumentoPrincipal, int nivelTecnico,
                          int ensayosAsistidos, int actuacionesDisponibles) {
        super(nombreArtistico, localidad, anosEnLaAgrupacion, puntosBase);
        this.instrumentoPrincipal = instrumentoPrincipal;
        this.nivelTecnico = nivelTecnico;
        this.ensayosAsistidos = ensayosAsistidos;
        this.actuacionesDisponibles = actuacionesDisponibles;
    }

    @Override
    public double calcularRendimiento() {
        return getPuntosBase() + (nivelTecnico * 5) + (ensayosAsistidos * 2);
    }

    @Override
    public boolean estaDisponible() {
        return actuacionesDisponibles > 0;
    }

    public void registrarEnsayo() {
        ensayosAsistidos++;
    }

    public void consumirActuacion() {
        if (actuacionesDisponibles > 0) actuacionesDisponibles--;
    }

    // Getters y setters
    public String getInstrumentoPrincipal()        { return instrumentoPrincipal; }
    public int getNivelTecnico()                   { return nivelTecnico; }
    public int getEnsayosAsistidos()               { return ensayosAsistidos; }
    public int getActuacionesDisponibles()         { return actuacionesDisponibles; }
    public void setInstrumentoPrincipal(String i)  { instrumentoPrincipal = i; }
    public void setNivelTecnico(int n)             { nivelTecnico = n; }
    public void setEnsayosAsistidos(int e)         { ensayosAsistidos = e; }
    public void setActuacionesDisponibles(int a)   { actuacionesDisponibles = a; }
}