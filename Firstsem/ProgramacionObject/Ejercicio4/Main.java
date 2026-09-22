package ProgramacionObject.Ejercicio4;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        // Constants for menu options
        final int GENERAR = 1;
        final int MOSTRAR = 2;
        final int DURACION = 3;
        final int SALIR = 4;

        // Menu string
        String menu = "\n--- GESTIÓN DE PLAYLIST ---\n" +
                "1. Generar 100 canciones\n" +
                "2. Mostrar todas las canciones\n" +
                "3. Calcular la duración total\n" +
                "4. Salir";

        // Initialize Scanner, Playlist, and Faker
        Scanner sc = new Scanner(System.in);
        Playlist playlist = new Playlist();
        FakerConciones faker = new FakerConciones();

        // Variable to store user option
        int opcion = 0;

        // Main loop for menu
        while (opcion != SALIR) {
            opcion = mostrarMenu(sc, menu);
            // Handle user options
            if (opcion == GENERAR) {
                for (int i = 0; i < 100; i++) {
                    Conciones nueva = new Conciones(
                            faker.songTitle(),
                            faker.artistName(),
                            faker.duration());
                    playlist.agregarConciones(nueva);
                }
                System.out.println("¡100 canciones añadidas con éxito!");

            } else if (opcion == MOSTRAR) {
                playlist.mostrarConciones();

            } else if (opcion == DURACION) {
                playlist.duracionTotal();

            } else if (opcion == SALIR) {
                System.out.println("Cerrando reproductor...");
            } else {
                System.out.println("Opción incorrecta.");
            }
        }
        sc.close();
    }

    ///=======================================Funciones======================================================//



    static int mostrarMenu(Scanner sc, String menu) {
        System.out.println(menu);
        System.out.print("Opción: ");
        return leerEntero(sc);
    }
    /**
     * 
     * @param sc // we use this for the scanner of the menu
     * 
     * @return
     */

    static int leerEntero(Scanner sc) {
        int numero = 0;
        boolean numeroValido = false;

        while (!numeroValido) {
            String texto = sc.nextLine().trim();
            try {
                numero = Integer.parseInt(texto);
                numeroValido = true;
            } catch (NumberFormatException e) {
                System.out.print("Debes introducir un número entero: ");
            }
        }

        return numero;
    }
}