import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ej07 {
    public static void main(String[] args) {

        // Convierte la modificación de stock del ejercicio 3 en una operación segura. Antes de cambiar inventario.csv, el programa debe conservar una copia recuperable.
        // Crea datos/copias si hace falta y copia el archivo actual a inventario.bak con Files.copy(...,REPLACE_EXISTING)
        // Realiza después la modificación solicitada.
        // Añade una opción independiente para restaurar inventario.bak; antes de sustituir los datos actuales, pide confirmación al usuario.
        // Si no existe la copia, informa y conserva el archivo actual.
        // Comprueba: la ejecución debe terminar aunque haya varios errores. Abre productos_validos.csv y confirma que no contiene filas inválidas ni IDs duplicados.

        Path rutaArchivo = Path.of("C:\\Users\\Otaku\\OneDrive\\Desktop\\Segundotrimstre\\secondsem\\ADD\\01_Unidad_1\\04_Datos_y_recursos\\inventario.csv");
        
        // Definición de las rutas del directorio de copias y archivo de backup
        Path dirCopias = Path.of("datos/copias");
        Path rutaBackup = dirCopias.resolve("inventario.bak");

        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== GESTIÓN SEGURA DE INVENTARIO ===");
        System.out.println("1. Modificar stock de un producto (con copia de seguridad)");
        System.out.println("2. Restaurar copia de seguridad (inventario.bak)");
        System.out.print("Selecciona una opción (1 o 2): ");
        
        String opcion = scanner.nextLine().trim();

        if (opcion.equals("1")) {
            modificarStockSeguro(rutaArchivo, dirCopias, rutaBackup, scanner);
        } else if (opcion.equals("2")) {
            restaurarBackup(rutaArchivo, rutaBackup, scanner);
        } else {
            System.out.println("Opción no válida. Saliendo del programa.");
        }

        scanner.close();
    }

    /**
     * Realiza la copia de seguridad e interactúa con el usuario para actualizar el stock.
     */
    private static void modificarStockSeguro(Path archivoOriginal, Path dirCopias, Path archivoBackup, Scanner scanner) {
        if (!Files.exists(archivoOriginal)) {
            System.err.println("Error: El archivo original no existe en la ruta: " + archivoOriginal);
            return;
        }

        try {
            // 1. Crear directorio datos/copias si no existe
            if (!Files.exists(dirCopias)) {
                Files.createDirectories(dirCopias);
                System.out.println("Directorio de copias creado: " + dirCopias.toAbsolutePath());
            }

            // 2. Crear la copia de seguridad usando StandardCopyOption.REPLACE_EXISTING
            Files.copy(archivoOriginal, archivoBackup, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Copia de seguridad guardada correctamente en: " + archivoBackup.getFileName());

            // 3. Solicitar datos de modificación
            System.out.print("Introduce el ID del producto a modificar: ");
            String idBuscado = scanner.nextLine().trim();

            System.out.print("Introduce el nuevo stock: ");
            String nuevoStockStr = scanner.nextLine().trim();

            // 4. Leer, modificar el contenido y escribir en el archivo original
            List<String> lineas = Files.readAllLines(archivoOriginal);
            List<String> lineasModificadas = new ArrayList<>();
            boolean encontrado = false;

            for (String linea : lineas) {
                String[] campos = linea.split(",");
                // Si la fila tiene formato id,nombre,stock,... comprobamos el id
                if (campos.length > 0 && campos[0].trim().equals(idBuscado)) {
                    if (campos.length >= 3) {
                        campos[2] = nuevoStockStr; // Asumiendo que el stock es la tercera columna (índice 2)
                        linea = String.join(",", campos);
                        encontrado = true;
                    }
                }
                lineasModificadas.add(linea);
            }

            if (encontrado) {
                Files.write(archivoOriginal, lineasModificadas);
                System.out.println("Modificación completada exitosamente en " + archivoOriginal.getFileName());
            } else {
                System.out.println("No se encontró ningún producto con ID: " + idBuscado + ". No se realizaron cambios.");
            }

        } catch (IOException e) {
            System.err.println("Error procesando los archivos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }

    /**
     * Restaura la copia de seguridad previa confirmación del usuario.
     */
    private static void restaurarBackup(Path archivoOriginal, Path archivoBackup, Scanner scanner) {
        // Verificar si existe la copia de seguridad
        if (!Files.exists(archivoBackup)) {
            System.out.println("No existe ninguna copia de seguridad (inventario.bak). Se conserva el archivo actual.");
            return;
        }

        // Pedir confirmación al usuario
        System.out.print("¿Está seguro de que desea restaurar inventario.bak? Se sobrescribirá el archivo actual (S/N): ");
        String confirmacion = scanner.nextLine().trim();

        if (confirmacion.equalsIgnoreCase("S")) {
            try {
                Files.copy(archivoBackup, archivoOriginal, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("El archivo inventario.csv ha sido restaurado con éxito desde la copia.");
            } catch (IOException e) {
                System.err.println("Error durante la restauración del archivo: " + e.getMessage());
            }
        } else {
            System.out.println("Operación de restauración cancelada. Se conserva el archivo actual.");
        }
    }
}