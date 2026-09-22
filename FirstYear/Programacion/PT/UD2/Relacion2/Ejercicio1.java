package Programacion.PT.UD2.Relacion2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio1 {

    public static void main(String[] args) {

        ArrayList<String> libros = new ArrayList<>(
                Arrays.asList("El quijote", "La Biblia", "Manual de resistencia", "La criada"));
        ArrayList<Integer> cantidades = new ArrayList<>(Arrays.asList(5, 3, 1, 7));

        final String errormasige = "Entrada no válida. Inténtelo de nuevo.";

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 4) {

            do {
                try {
                    System.out.println("\n1. Añadir un nuevo libro.");
                    System.out.println("2. Actualizar cantidad.");
                    System.out.println("3. Ver inventario.");
                    System.out.println("4. Salir.");
                    System.out.print("Seleccione una opción: ");
                    opcion = Integer.parseInt(scanner.nextLine());

        
                } catch (Exception e) {
                   
                    System.out.println( errormasige );
                    opcion = -1;
                }
            } while (opcion < 0 && opcion > 4);

            if (opcion == 1) {

                
                System.out.print("Introduzca el nombre del libro: ");
                String libroNuevo = scanner.nextLine();
                libros.add(libroNuevo);
                
                
                int cantidadLibroNuevo = -1;
                do {
                    try {

                    System.out.print("Cantidad disponible: ");
                    cantidadLibroNuevo = Integer.parseInt(scanner.nextLine());

                    cantidades.add(cantidadLibroNuevo);

                } catch (Exception e) {
                    System.out.println(errormasige);
                    cantidadLibroNuevo = -1;
                }
                } while (cantidadLibroNuevo < 0);



            } else if (opcion == 2) {

                System.out.print("Nombre del libro a actualizar: ");
                String libroActualizado = scanner.nextLine();

                System.out.print("Nueva cantidad: ");
                int cantidadLibroActualizado = Integer.parseInt(scanner.nextLine());

                boolean encontrado = false;

                for (int i = 0; i < libros.size(); i++) {
                    if (libros.get(i).equals(libroActualizado)) {
                        cantidades.set(i, cantidadLibroActualizado);
                        encontrado = true;
                    }
                }

                if (!encontrado) {
                    System.out.println("Libro no encontrado");
                }

            } else if (opcion == 3) {

                System.out.println("\nInventario:");
                for (int i = 0; i < libros.size(); i++) {
                    System.out.println(libros.get(i) + " - " + cantidades.get(i) + " unidades");
                }
            }
        }

        scanner.close();
        System.out.println("Programa finalizado.");
    }
}
