public class Trompetista extends Musico implements Asignable {
    private int numeroInstrumentos;
    private double valorInstrumentos;
    private int resistencia;
    private int numeroSolos;

    public Trompetista(String nombre, int edad, int antiguedad,
                       String instrumentoPrincipal, int nivel,
                       int asistenciaEnsayos, double valorBase,
                       int numeroInstrumentos, double valorInstrumentos,
                       int resistencia, int numeroSolos) {
        super(nombre, edad, antiguedad, instrumentoPrincipal, nivel, asistenciaEnsayos, valorBase);
        this.numeroInstrumentos = numeroInstrumentos;
        this.valorInstrumentos = valorInstrumentos;
        this.resistencia = resistencia;
        this.numeroSolos = numeroSolos;
    }

    @Override
    public double calcularAportacion() {
        return super.calcularAportacion() + resistencia + (numeroSolos * 3) + (valorInstrumentos * 0.05);
    }

    @Override
    public boolean puedeParticipar() {
        return super.puedeParticipar() && resistencia >= 5;
    }

    @Override
    public boolean asignarInstrumento(String nombreInstrumento, double valorInstrumento) {
        if (nombreInstrumento == null || nombreInstrumento.isEmpty()) return false;
        if (valorInstrumento <= 0) return false;
        numeroInstrumentos++;
        valorInstrumentos += valorInstrumento;
        return true;
    }

    @Override
    public boolean quitarInstrumento(String nombreInstrumento, double valorInstrumento) {
        if (numeroInstrumentos == 0) return false;
        if (valorInstrumento <= 0) return false;
        numeroInstrumentos--;
        valorInstrumentos -= valorInstrumento;
        if (valorInstrumentos < 0) valorInstrumentos = 0;
        return true;
    }

    public void registrarSolo() {
        numeroSolos++;
    }

    public void aumentarResistencia(int cantidad) {
        if (cantidad > 0) resistencia += cantidad;
    }

    // Getters y setters
    public int getNumeroInstrumentos()          { return numeroInstrumentos; }
    public double getValorInstrumentos()        { return valorInstrumentos; }
    public int getResistencia()                 { return resistencia; }
    public int getNumeroSolos()                 { return numeroSolos; }
    public void setNumeroInstrumentos(int n)    { numeroInstrumentos = n; }
    public void setValorInstrumentos(double v)  { valorInstrumentos = v; }
    public void setResistencia(int r)           { resistencia = r; }
    public void setNumeroSolos(int n)           { numeroSolos = n; }
}