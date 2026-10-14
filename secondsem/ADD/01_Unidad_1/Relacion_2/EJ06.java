import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class EJ06 {
    
    public static void main(String[] args) {

        // 1. Using Path instead of File
        Path rutaArchivo = Path.of("C:\\Users\\Otaku\\OneDrive\\Desktop\\Segundotrimstre\\secondsem\\ADD\\01_Unidad_1\\04_Datos_y_recursos\\productos_errores.csv");

        try (BufferedReader reader = Files.newBufferedReader(rutaArchivo, StandardCharsets.UTF_8)) {
            String linea;
            int numeroLinea = 0;
            Set<Integer> idsValidos = new HashSet<>();
            List<String> registrosValidos = new ArrayList<>();

            while ((linea = reader.readLine()) != null) {
                numeroLinea++;
                String[] campos = linea.split(";", -1);

                if (campos.length != 3) {
                    System.out.printf("Error en línea %d: La línea no contiene exactamente tres campos.%n", numeroLinea);
                    continue;
                }

                try {
                    int id = Integer.parseInt(campos[0].trim());
                    String nombre = campos[1].trim();
                    int stock = Integer.parseInt(campos[2].trim());

                    if (id <= 0) {
                        System.out.printf("Error en línea %d: ID debe ser un entero positivo.%n", numeroLinea);
                        continue;
                    }
                    if (nombre.isEmpty()) {
                        System.out.printf("Error en línea %d: Nombre no puede estar vacío.%n", numeroLinea);
                        continue;
                    }
                    if (stock < 0) {
                        System.out.printf("Error en línea %d: Stock debe ser un entero mayor o igual a cero.%n", numeroLinea);
                        continue;
                    }
                    if (idsValidos.contains(id)) {
                        System.out.printf("Error en línea %d: ID duplicado encontrado.%n", numeroLinea);
                        continue;
                    }

                    // Registro válido
                    idsValidos.add(id);
                    registrosValidos.add(linea);

                } catch (NumberFormatException e) {
                    System.out.printf("Error en línea %d: ID o Stock no son números válidos.%n", numeroLinea);
                }
            }

            // Guardar registros válidos en productos_validos.csv
            Path rutaSalida = Path.of("C:", "Users", "Otaku", "OneDrive", "Desktop",
                    "Segundotrimstre", "secondsem", "ADD",
                    "01_Unidad_1", "04_Datos_y_recursos", "productos_validos.csv");
            Files.write(rutaSalida, registrosValidos, StandardCharsets.UTF_8);
            System.out.println("Registros válidos guardados en productos_validos.csv");

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }


    }
}
