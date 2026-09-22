package UD5.Relacion1;

public class Eercicio3 {
    // Crea una clase Alumno con atributos nombre, materia y calificacion. Agrega un
    // método aprobo() que devuelva True si la calificación es mayor o igual a 6, y
    // False en caso contrario.
    public static void main(String[] args) {
        Alumno alumno1 = new Alumno("Carlos", "Matemáticas", 7.5);
        Alumno alumno2 = new Alumno("Ana", "Historia", 5.0);

        if (alumno1.aprobo()) {

            System.out.println("El alumno " + alumno1.nombre + " en la materia " + alumno1.materia + " aprobó");
        } else {
            System.out.println("El alumno " + alumno1.nombre + " en la materia " + alumno1.materia + " suspende");

        }

        if (alumno2.aprobo()) {
            System.out.println("El alumno " + alumno2.nombre + " en la materia " + alumno2.materia + " aprobó: ");

        }else {
            System.out.println("El alumno " + alumno2.nombre + " en la materia " + alumno2.materia + " suspende");

        }


    }
}

////////==============================================CLASSS=============================================================///
class Alumno {
    final String nombre;
    final String materia;
    final double calificacion;
    boolean Siaproboado;

    public Alumno(String nombre, String materia, double calificacion) {
        this.nombre = nombre;
        this.materia = materia;
        this.calificacion = calificacion;
    }

    public boolean aprobo() {

        return calificacion >= 6;

    }
}