package ProgramacionObject.Ejercicio5;

public class Películas {
    // Crea una clase Pelicula con atributos titulo, sinopsis, director, duracion.
    // Implementa los métodos get y set para cada atributo, así como el método
    // toString(). Luego, crea una clase Cine con un array de películas y métodos
    // para:
   
    //Atributos
    private String titulo;
    private String sinopsis;
    private String director;
    private int duracion;

    //Constructor
    public Películas(String titulo, String sinopsis, String director, int duracion) {
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.director = director;
        this.duracion = duracion;
    }



    @Override
    public String toString() {
        return String.format("Título: %-25s | Director: %-20s | Duración: %d min\nSinopsis: %s",
                titulo, director, duracion, sinopsis);
    }
    // Getters and Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    
}