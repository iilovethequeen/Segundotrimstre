public class Musico extends PersonaAgrupacion {
    private String instrumentoPrincipal;
    private int nivel;
    private int asistenciaEnsayos;
    private double valorBase;

    public Musico(String nombre, int edad, int antiguedad,
                  String instrumentoPrincipal, int nivel,
                  int asistenciaEnsayos, double valorBase) {
        super(nombre, edad, antiguedad);
        this.instrumentoPrincipal = instrumentoPrincipal;
        this.nivel = nivel;
        this.asistenciaEnsayos = asistenciaEnsayos;
        this.valorBase = valorBase;
    }

    @Override
    public double calcularAportacion() {
        return valorBase + (nivel * 2) + (asistenciaEnsayos * 1.5);
    }

    @Override
    public boolean puedeParticipar() {
        return asistenciaEnsayos >= 3;
    }

    public void registrarEnsayo() {
        asistenciaEnsayos++;
    }

    public void subirNivel() {
        nivel++;
    }

    // Getters y setters
    public String getInstrumentoPrincipal()       { return instrumentoPrincipal; }
    public int getNivel()                          { return nivel; }
    public int getAsistenciaEnsayos()              { return asistenciaEnsayos; }
    public double getValorBase()                   { return valorBase; }
    public void setInstrumentoPrincipal(String i)  { instrumentoPrincipal = i; }
    public void setNivel(int n)                    { nivel = n; }
    public void setAsistenciaEnsayos(int a)        { asistenciaEnsayos = a; }
    public void setValorBase(double v)             { valorBase = v; }
}