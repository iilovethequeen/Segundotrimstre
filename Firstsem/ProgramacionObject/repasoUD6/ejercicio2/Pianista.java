public class Pianista extends Instrumentista implements Tocable {
    private int numeroInstrumentos;
    private double valorInstrumentos;
    private int acompanamientosPreparados;
    private int precision;

    public Pianista(String nombreArtistico, String localidad,
                    int anosEnLaAgrupacion, double puntosBase,
                    String instrumentoPrincipal, int nivelTecnico,
                    int ensayosAsistidos, int actuacionesDisponibles,
                    int numeroInstrumentos, double valorInstrumentos,
                    int acompanamientosPreparados, int precision) {
        super(nombreArtistico, localidad, anosEnLaAgrupacion, puntosBase,
              instrumentoPrincipal, nivelTecnico, ensayosAsistidos, actuacionesDisponibles);
        this.numeroInstrumentos = numeroInstrumentos;
        this.valorInstrumentos = valorInstrumentos;
        this.acompanamientosPreparados = acompanamientosPreparados;
        this.precision = precision;
    }

    @Override
    public double calcularRendimiento() {
        return super.calcularRendimiento()
                + (acompanamientosPreparados * 3)
                + precision
                + (valorInstrumentos * 0.015);
    }

    @Override
    public boolean estaDisponible() {
        return super.estaDisponible() && precision >= 5;
    }

    @Override
    public boolean asignarInstrumento(String nombre, double valor) {
        if (nombre == null || nombre.isEmpty()) return false;
        if (valor <= 0) return false;
        numeroInstrumentos++;
        valorInstrumentos += valor;
        return true;
    }

    @Override
    public boolean retirarInstrumento(String nombre, double valor) {
        if (numeroInstrumentos == 0) return false;
        if (valor <= 0) return false;
        numeroInstrumentos--;
        valorInstrumentos -= valor;
        if (valorInstrumentos < 0) valorInstrumentos = 0;
        return true;
    }

    public void prepararAcompanamiento() {
        acompanamientosPreparados++;
    }

    public void aumentarPrecision(int cantidad) {
        if (cantidad > 0) precision += cantidad;
    }

    // Getters y setters
    public int getNumeroInstrumentos()             { return numeroInstrumentos; }
    public double getValorInstrumentos()           { return valorInstrumentos; }
    public int getAcompanamientosPreparados()      { return acompanamientosPreparados; }
    public int getPrecision()                      { return precision; }
    public void setNumeroInstrumentos(int n)       { numeroInstrumentos = n; }
    public void setValorInstrumentos(double v)     { valorInstrumentos = v; }
    public void setAcompanamientosPreparados(int a){ acompanamientosPreparados = a; }
    public void setPrecision(int p)                { precision = p; }
}