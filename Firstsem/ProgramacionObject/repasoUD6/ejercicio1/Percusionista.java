public class Percusionista extends Musico implements Asignable {
    private int numeroInstrumentos;
    private double valorInstrumentos;
    private int potenciaGolpe;
    private int cambiosRitmoCorrectos;

    public Percusionista(String nombre, int edad, int antiguedad,
                         String instrumentoPrincipal, int nivel,
                         int asistenciaEnsayos, double valorBase,
                         int numeroInstrumentos, double valorInstrumentos,
                         int potenciaGolpe, int cambiosRitmoCorrectos) {
        super(nombre, edad, antiguedad, instrumentoPrincipal, nivel, asistenciaEnsayos, valorBase);
        this.numeroInstrumentos = numeroInstrumentos;
        this.valorInstrumentos = valorInstrumentos;
        this.potenciaGolpe = potenciaGolpe;
        this.cambiosRitmoCorrectos = cambiosRitmoCorrectos;
    }

    @Override
    public double calcularAportacion() {
        return super.calcularAportacion() + (potenciaGolpe * 2)
                + (cambiosRitmoCorrectos * 4) + (valorInstrumentos * 0.03);
    }

    @Override
    public boolean puedeParticipar() {
        return super.puedeParticipar() && potenciaGolpe >= 4;
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

    public void registrarCambioRitmoCorrecto() {
        cambiosRitmoCorrectos++;
    }

    public void aumentarPotencia(int cantidad) {
        if (cantidad > 0) potenciaGolpe += cantidad;
    }

    // Getters y setters
    public int getNumeroInstrumentos()          { return numeroInstrumentos; }
    public double getValorInstrumentos()        { return valorInstrumentos; }
    public int getPotenciaGolpe()               { return potenciaGolpe; }
    public int getCambiosRitmoCorrectos()       { return cambiosRitmoCorrectos; }
    public void setNumeroInstrumentos(int n)    { numeroInstrumentos = n; }
    public void setValorInstrumentos(double v)  { valorInstrumentos = v; }
    public void setPotenciaGolpe(int p)         { potenciaGolpe = p; }
    public void setCambiosRitmoCorrectos(int c) { cambiosRitmoCorrectos = c; }
}