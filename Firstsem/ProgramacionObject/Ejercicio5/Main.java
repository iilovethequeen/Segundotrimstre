package ProgramacionObject.Ejercicio5;

import java.util.ArrayList;
import java.util.Scanner;

import ProgramacionObject.Ejercicio4.Conciones;

public class Main {
    /**
     * @param args
     */
    public static void main(String[] args) {

        final int GENERAR = 1;
        final int MOSTRAR = 2;
        final int DURACION = 3;
        final int SALIR = 4;

        String menu = "\n--- GESTIÓN DE CINE ---\n" +
                "1. Generar 100 películas (Faker)\n" +
                "2. Mostrar todas las películas\n" +
                "3. Calcular la duración media\n" +
                "4. Salir";

        Cine cine = new Cine();
        FakerCine faker = new FakerCine();

        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        while (opcion != SALIR) {
            opcion = mostrarMenu(sc, menu);

            if (opcion == GENERAR) {
                for (int i = 0; i < 100; i++) {
                    Películas pelicula = new Películas(
                            faker.generarTitulo(),
                            faker.generarDirector(),
                            faker.generarSinopsis(),
                            faker.generarDuracion());
                    cine.agregarPelicula(pelicula);
                }
                System.out.println("¡100 canciones añadidas con éxito!");

            } else if (opcion == MOSTRAR) {
                cine.mostrarPeliculas();

            } else if (opcion == DURACION) {
                cine.duracionTotal();

            } else if (opcion == SALIR) {
                System.out.println("Cerrando reproductor...");
            } else {
                System.out.println("Opción incorrecta.");
            }
        }

    }

    static int mostrarMenu(Scanner sc, String menu) {
        System.out.println(menu);
        System.out.print("Opción: ");
        return leerEntero(sc);
    }

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
