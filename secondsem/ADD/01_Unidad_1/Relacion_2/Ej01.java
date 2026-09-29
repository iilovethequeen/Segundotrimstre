import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//01 Catálogo de videojuegos: interpretar registros
//Apartado 4.1 · split, arrays y conversión de tipos
//Archivo: videojuegos.csv
//Lee videojuegos.csv . La primera línea es la cabecera y las siguientes contienen id;titulo;plataforma .
//Convierte cada línea de datos en sus tres campos y muéstralos con un formato distinto al original.
//Ignora la cabecera al procesar los registros.
//Separa cada línea con split(";", -1) y comprueba que contiene exactamente tres campos.
//Convierte el ID con Integer.parseInt(...) y controla un posible NumberFormatException .
//Muestra cada videojuego como [101] Hollow Knight - PC .
//Comprueba: cambia temporalmente un ID por una letra y verifica que el programa informa del error sin
//intentar usar ese registro.//

public class Ej01 {

    public static void main(String[] args) {
        File archivo = new File("videojuegos.csv");

        try (Scanner scanner = new Scanner(archivo)) {
            // 1. Ignorar la cabecera si el archivo no está vacío
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            // 2. Leer y procesar el resto de líneas del archivo
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] campos = linea.split(";", -1);

                if (campos.length == 3) {
                    try {
                        int id = Integer.parseInt(campos[0]);
                        String titulo = campos[1];
                        String plataforma = campos[2];

                        System.out.printf("[%d] %s - %s%n", id, titulo, plataforma);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: ID no es un número válido en la línea -> " + linea);
                    }
                } else {
                    System.out.println("Error: La línea no contiene exactamente tres campos -> " + linea);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: No se encontró el archivo videojuegos.csv");
        }
    }
}

