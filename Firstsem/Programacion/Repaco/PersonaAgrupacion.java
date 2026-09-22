
    /*
 * 1) Crea una clase abstracta llamada PersonaAgrupacion.
 *
 * Debe tener exactamente estos 3 atributos:
 * - private String nombre;
 * - private int edad;
 * - private int antiguedad;
 *
 * Debe tener:
 * - constructor con todos los atributos
 * - getters y setters*/

abstract class PersonaAgrupacion {
    private String nombre;
    private int edad;
    private int antiguedad;

    public PersonaAgrupacion(String nombre, int edad, int antiguedad) {
        this.nombre = nombre;
        this.edad = edad;
        this.antiguedad = antiguedad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

     public abstract double calcularAportacion(){
    
 }

  
  public abstract boolean puedeParticipar(){

  }
 
  public void cumplirAnio(){

  }
  

  public void aumentarAntiguedad(){
    
  }
 
 

}

