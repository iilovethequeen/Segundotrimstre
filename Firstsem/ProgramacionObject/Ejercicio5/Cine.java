package ProgramacionObject.Ejercicio5;

import java.util.ArrayList;

public class Cine {

    ArrayList<Películas> peliculas;

    public Cine(){
        this.peliculas = new ArrayList<>();

    }
    //Agregar películas
    void agregarPelicula(Películas pelicula) {
        // Implementar lógica para agregar película a la colección
        this.peliculas.add(pelicula);

        
    }


//Mostrar todas las películas

    void mostrarPeliculas() {
        // Implementar lógica para mostrar todas las películas
        if (peliculas.isEmpty()) {
            System.out.println("No hay películas en el cine.");
        } else {
            System.out.println("\n--- PELÍCULAS EN EL CINE ---");
            for (Películas p : peliculas) {
                System.out.println(p);
            }
        }
    }

//Buscar una película por título
    Películas buscarPeliculaPorTitulo(String titulo) {
        // Implementar lógica para buscar película por título
        for (Películas p : peliculas) {
            if (p.getTitulo().equalsIgnoreCase(titulo)) {
                return p;
            }
        }
        return null; // Retorna null si no se encuentra la película
    }

//Calcular la duración media de las películas
    double duracionMedia() {
        // Implementar lógica para calcular la duración media de las películas
        if (peliculas.isEmpty()) {
            return 0.0; // Evitar división por cero
        }
        int totalDuracion = 0;
        for (Películas p : peliculas) {
            totalDuracion += p.getDuracion();
        }
        return (double) totalDuracion / peliculas.size();
    }
    public void duracionTotal() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'duracionTotal'");
    }

    
}
