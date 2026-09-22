public abstract class PersonaAgrupacion {
    private String nombre;
    private int edad;
    private int antiguedad;

    public PersonaAgrupacion(String nombre, int edad, int antiguedad) {
        this.nombre = nombre;
        this.edad = edad;
        this.antiguedad = antiguedad;
    }

    public abstract double calcularAportacion();
    public abstract boolean puedeParticipar();

    public void cumplirAnio() {
        edad++;
    }

    public void aumentarAntiguedad() {
        antiguedad++;
    }

    // Getters y setters
    public String getNombre()          { return nombre; }
    public int getEdad()               { return edad; }
    public int getAntiguedad()         { return antiguedad; }
    public void setNombre(String n)    { nombre = n; }
    public void setEdad(int e)         { edad = e; }
    public void setAntiguedad(int a)   { antiguedad = a; }
}