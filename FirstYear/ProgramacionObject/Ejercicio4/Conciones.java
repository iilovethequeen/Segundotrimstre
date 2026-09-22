package ProgramacionObject.Ejercicio4;

public class Conciones {
    private String titulo;
    private String artista;
    private Double duracion;

    /**
     * 
     * @param titulo   // we use this for the constructor of the class
     * @param artista  // we use this for the constructor of the class
     * 
     * @param duracion // we use this for the constructor of the class
     */

    public Conciones(String titulo, String artista, Double duracion) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracion = duracion;
    }

    @Override // we alw use this for thr method of to string() so can repet itself auto

    // %-25s: Muestra el título con un máximo de 25 caracteres, alineado a la izquierda. Si el título es más corto, se rellena con espacios.
    // %-15s: Muestra el nombre del artista con un máximo de 15 caracteres, alineado a la izquierda.
    // %.2f seg: Muestra la duración en segundos con dos decimales, seguido de la etiqueta "seg
    public String toString() {
        return String.format("Título: %-25s | Artista: %-15s | Duración: %.2f seg",
                titulo, artista, duracion);
    }

    // Getters and Setters

    public String getTitulo() {

        return titulo;

    }

    /**
     * 
     * @param titulo
     */

    public void setTitulo(String titulo) {

        this.titulo = titulo;

    }

    public String getArtista() {
        return artista;

    }

    public void setArtista(String artista) {
        this.artista = artista;

    }

    public Double getDuracion() {
        return duracion;
    }

    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }

}