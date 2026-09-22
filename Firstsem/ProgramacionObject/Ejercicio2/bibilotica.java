package ProgramacionObject.Ejercicio2;

import java.util.ArrayList;


public class bibilotica {
    ArrayList<libros> libros;

    bibilotica(){
        this.libros = new ArrayList<>();

    }
    void agrergarLibro(libros libro){
        this.libros.add(libro);
    }



    //Prestar un libro (cambiar disponible a false)
    
    void prestarLibro(String id){
        for (libros libro : libros) {
            if (libro.getId().equals(id)) {
                libro.setDisponible(false);
                System.out.println("Libro prestado: " + libro.getTituloLibro());
                return;
            }
        }
        System.out.println("Libro no encontrado con ID: " + id);
    }

    void devolverLibro(String id){
        for (libros libro : libros) {
            if (libro.getId().equals(id)) {
                libro.setDisponible(true);
                System.out.println("Libro devuelto: " + libro.getTituloLibro());
                return;
            }
        }
        System.out.println("Libro no encontrado con ID: " + id);
    }

  
     public String mostrarLibrosCompletos() {
        String lista = "";
        for (libros libro : libros) {
            lista += libro + "||\n";
        }
        return lista;
    }


    


}

