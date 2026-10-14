import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Ej02 {
    public static void main(String[] args) {

        // 1. Using Path instead of File
        Path rutaArchivo = Path.of("C:", "Users", "Otaku", "OneDrive", "Desktop",
                "Segundotrimstre", "secondsem", "ADD",
                "01_Unidad_1", "04_Datos_y_recursos", "alumnos.csv");

        // 2. Scanner for reading keyboard input from the user
        Scanner teclado = new Scanner(System.in);
        int idbuscado = 0;
        boolean encontrado = false;
        
        System.out.print("Introduce el ID del alumno a buscar: ");
        try {
            idbuscado = Integer.parseInt(teclado.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID introducido no es un número válido.");
            teclado.close();
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(rutaArchivo, StandardCharsets.UTF_8)) {
            String linea;

    
            while ((linea = reader.readLine()) != null) {
                String[] campos = linea.split(";", -1);

                if (campos.length == 3) {
                    try {
                        int id = Integer.parseInt(campos[0].trim());
                        String nombre = campos[1].trim();
                        String grupo = campos[2].trim();

                        if (id == idbuscado) {
                            System.out.printf("Alumno encontrado: %s - %s%n", nombre, grupo);
                            encontrado = true;
                            return;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: El ID en la línea no es un número válido  " + linea);
                    }
                } else {
                    System.out.println("Error: La línea no contiene exactamente tres campos  " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        if (!encontrado) {
            System.out.println("El alumno con ID " + idbuscado + " no existe.");
        }

        teclado.close();
    }
}