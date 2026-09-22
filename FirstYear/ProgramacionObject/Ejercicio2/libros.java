package ProgramacionObject.Ejercicio2;

public class libros {
    //Ejercicio 2: Biblioteca y Libros
//Crea una clase Libro con atributos id, titulo, autor, sinopsis y disponible (booleano). Implementa los métodos get y set para cada atributo, así como el método toString(). Luego, crea una clase Biblioteca con un array de libros y métodos para:

private String id;
private String tituloLibro;
private String autorlibro;
private String sinopsis;
private boolean disponible;

libros(String id, String tituloLibro, String autorlibro, String sinopsis, boolean disponible) {
    this.id = id;
    this.tituloLibro = tituloLibro ;
    this.autorlibro = autorlibro;
    this.sinopsis = sinopsis;
    this.disponible = disponible;
}

// tostring() para mostrar la información del libro
@Override
public String toString() {
    return "libros{" +
            "id='" + id + '\'' +
            ", tituloLibro='" + tituloLibro + '\'' +
            ", autorlibro='" + autorlibro + '\'' +
            ", sinopsis='" + sinopsis + '\'' +
            ", disponible=" + disponible +
            '}';
}


public String getId() {
    return id;
}

public void setId(String id) {
    this.id = id;
}

public String getTituloLibro() {
    return tituloLibro;
}

public void setTituloLibro(String tituloLibro) {
    this.tituloLibro = tituloLibro;
}

public String getAutorLibro() {
    return autorlibro;
}

public void setAutorLibro(String autorlibro) {
    this.autorlibro = autorlibro;
}

public String getSinopsis() {
    return sinopsis;
}

public void setSinopsis(String sinopsis) {
    this.sinopsis = sinopsis;
}

public boolean isDisponible() {
    return disponible;
}

public void setDisponible(boolean disponible) {
    this.disponible = disponible;


}
}