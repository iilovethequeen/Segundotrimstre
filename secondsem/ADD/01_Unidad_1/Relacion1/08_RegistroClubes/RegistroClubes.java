import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class RegistroClubes {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        Path carpetaDatos = Path.of("datos");
        Path ficheroClubes = carpetaDatos.resolve("clubes.csv");

        try {
            // Crear la carpeta datos si no existe
            Files.createDirectories(carpetaDatos);

            // Abrimos el fichero en modo añadir
            try (BufferedWriter escritor = Files.newBufferedWriter(
                    ficheroClubes,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND)) {

                String id = "";

                // Seguimos pidiendo clubes mientras el ID no sea -1
                while (!id.equals("-1")) {

                    System.out.print("Introduce el identificador del club (-1 para terminar): ");
                    id = teclado.nextLine();

                    if (!id.equals("-1")) {

                        System.out.print("Introduce el nombre del club: ");
                        String nombre = teclado.nextLine();

                        System.out.print("Introduce la ciudad del club: ");
                        String ciudad = teclado.nextLine();

                        escritor.write(id + ";" + nombre + ";" + ciudad);
                        escritor.newLine();

                        System.out.println("Club guardado correctamente.\n");
                    }
                }
            }

            // Mostrar todo el contenido del fichero
            System.out.println("\n--- CLUBES GUARDADOS ---");

            try (BufferedReader lector = Files.newBufferedReader(
                    ficheroClubes,
                    StandardCharsets.UTF_8)) {

                String linea;

                while ((linea = lector.readLine()) != null) {
                    System.out.println(linea);
                }
            }

        } catch (IOException e) {
            System.out.println("Se ha producido un error de entrada/salida:");
            System.out.println(e.getMessage());
        }

        teclado.close();
    }
}