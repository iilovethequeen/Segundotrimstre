package ProgramacionObject.Ejercicio3;

import java.util.Scanner;
// Importamos el Faker que está en la carpeta superior
import ProgramacionObject.Ejercicio3.Faker; 

public class Main {
    public static void main(String[] args) {
        // Inicialización de herramientas
        Scanner sc = new Scanner(System.in);
        Supermercado supermercado = new Supermercado();
        Faker faker = new Faker(); 


        // Constantes del menú
        final int OPCION1 = 1;
        final int OPCION2 = 2;
        final int OPCION3 = 3;
        final int OPCION4 = 4;

        String menu = "\n--- GESTIÓN DE SUPERMERCADO ---\n" +
                "1. Generar 100 productos aleatorios (Faker)\n" +
                "2. Vender un producto (reducir stock)\n" +
                "3. Mostrar el inventario\n" +
                "4. Salir";

        int opcion = 0;

        while (opcion != OPCION4) {
            opcion = mostrarMenu(sc, menu);

            if (opcion == OPCION1) {
                // --- Lógica para agregar 100 productos usando el Faker ---
                for (int i = 0; i < 100; i++) {
                    Prodoctos p = new Prodoctos(
                        faker.id(), 
                        faker.productName(), 
                        "Producto generado automáticamente", 
                        faker.price(), 
                        faker.stock()
                    );
                    supermercado.agregarProducto(p);
                }
                imprimirMensaje("¡Se han añadido 100 productos aleatorios correctamente!");

            } else if (opcion == OPCION2) {
                // --- Lógica para vender productos ---
                imprimirPrompt("Introduce el ID del producto a vender: ");
                String idVenta = sc.nextLine();
                imprimirPrompt("Introduce la cantidad: ");
                int cantidadVenta = leerEntero(sc);
                
                supermercado.venderProducto(idVenta, cantidadVenta);

            } else if (opcion == OPCION3) {
                // --- Lógica para mostrar inventario ---
                supermercado.mostrarInventario();

            } else if (opcion == OPCION4) {
                imprimirMensaje("Saliendo del programa. ¡Hasta luego!");
            } else {
                imprimirMensaje("Opción no válida. Inténtalo de nuevo.");
            }
        }
        sc.close();
    }

    // ======================= Funciones Auxiliares ======================================== //

    static void imprimirMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    static void imprimirPrompt(String mensaje) {
        System.out.print(mensaje);
    }

    static int mostrarMenu(Scanner sc, String menu) {
        imprimirMensaje(menu);
        imprimirPrompt("Opción: ");
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
                imprimirPrompt("Debes introducir un número entero: ");
            }
        }
        return numero;
    }
}