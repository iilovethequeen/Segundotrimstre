package ProgramacionObject.Ejercicio2;

public class Main {
    public static void main(String[] args) {
        bibilotica biblioteca = new bibilotica();

        libros libro1 = new libros("001", "El Quijote", "Miguel de Cervantes", null, false);
        biblioteca.agrergarLibro(libro1);

        System.out.println("Libros disponibles en la biblioteca:");
        System.out.println(biblioteca.mostrarLibrosCompletos());

        final int NUMERO_DE_LIBROS = 100;
        for (int i = 0; i < NUMERO_DE_LIBROS; i++) {
            libros libroAleatorio = new libros(FakerConciones.id(), FakerConciones.tituloLibro(), FakerConciones.autor(), null, false);
            biblioteca.agrergarLibro(libroAleatorio);
        }
        System.out.println("Libros disponibles en la biblioteca después de agregar 100 libros:"

        );
        System.out.println(biblioteca.mostrarLibrosCompletos());

        

    }
}
