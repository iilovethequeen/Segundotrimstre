package ProgramacionObject.Ejercicio1;

public class Estudiante {

    private String nombre;
    private int edad;
    private String NumeroDeMareicula;

    public Estudiante(String nombre, int edad, String NumeroDeMareicula) {
        this.nombre = nombre;
        this.edad = edad;
        this.NumeroDeMareicula = NumeroDeMareicula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        int EdadMinima = 17;
        if (edad < 17) {

            EdadMinima = -1;
            
        }else {
            EdadMinima = edad;
        }
        return EdadMinima;
    }

    public void setEdad(int edad) {
        if (edad >= 17) {
            this.edad = edad;
        }

    }

    public String getNumeroDeMareicula() {
        return NumeroDeMareicula;
    }

    public void setNumeroDeMareicula(String NumeroDeMareicula) {
        this.NumeroDeMareicula = NumeroDeMareicula;
    }

    @Override // use this alw for to.String() so it chanche alw //
    public String toString() {
        return "Estudiante{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", NumeroDeMareicula='" + NumeroDeMareicula + '\'' +
                '}';
    }
}
