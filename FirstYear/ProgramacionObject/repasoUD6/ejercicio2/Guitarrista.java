public class Guitarrista extends Instrumentista implements Tocable, Revisable {
    private int numeroInstrumentos;
    private double valorInstrumentos;
    private int solosPreparados;
    private int partiturasRevisadas;
    private int erroresDetectados;

    public Guitarrista(String nombreArtistico, String localidad,
                       int anosEnLaAgrupacion, double puntosBase,
                       String instrumentoPrincipal, int nivelTecnico,
                       int ensayosAsistidos, int actuacionesDisponibles,
                       int numeroInstrumentos, double valorInstrumentos,
                       int solosPreparados) {
        super(nombreArtistico, localidad, anosEnLaAgrupacion, puntosBase,
              instrumentoPrincipal, nivelTecnico, ensayosAsistidos, actuacionesDisponibles);
        this.numeroInstrumentos = numeroInstrumentos;
        this.valorInstrumentos = valorInstrumentos;
        this.solosPreparados = solosPreparados;
        this.partiturasRevisadas = 0;
        this.erroresDetectados = 0;
    }

    @Override
    public double calcularRendimiento() {
        return super.calcularRendimiento()
                + (solosPreparados * 4)
                + (partiturasRevisadas * 1.5)
                - erroresDetectados
                + (valorInstrumentos * 0.02);
    }

    @Override
    public boolean estaDisponible() {
        return super.estaDisponible() && solosPreparados >= 1;
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

    @Override
    public void sumarPartiturasRevisadas() {
        partiturasRevisadas++;
    }

    @Override
    public void sumarErroresDetectados(int cantidad) {
        if (cantidad > 0) erroresDetectados += cantidad;
    }

    public void prepararSolo() {
        solosPreparados++;
    }

    public void corregirError() {
        if (erroresDetectados > 0) erroresDetectados--;
    }

    // Getters y setters
    public int getNumeroInstrumentos()          { return numeroInstrumentos; }
    public double getValorInstrumentos()        { return valorInstrumentos; }
    public int getSolosPreparados()             { return solosPreparados; }
    public int getPartiturasRevisadas()         { return partiturasRevisadas; }
    public int getErroresDetectados()           { return erroresDetectados; }
    public void setNumeroInstrumentos(int n)    { numeroInstrumentos = n; }
    public void setValorInstrumentos(double v)  { valorInstrumentos = v; }
    public void setSolosPreparados(int s)       { solosPreparados = s; }
    public void setPartiturasRevisadas(int p)   { partiturasRevisadas = p; }
    public void setErroresDetectados(int e)     { erroresDetectados = e; }
}