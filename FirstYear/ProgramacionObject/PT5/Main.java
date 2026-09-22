package ProgramacionObject.PT5;

import java.util.Scanner;

/**
 * Represents an Animal entity with its basic attributes and properties.
 * 
 * This class encapsulates information about an animal including its name, 
 * identification code, quantity, and price. It provides getters and setters 
 * for all attributes and a formatted string representation.
 * 
 * @author [AHMED IKDEEWISH]
 * @version 1.0
 */
public class Main {

    /**
     * Main method that manages the Zoo management system.
     * 
     * This method initializes a Zoo with 1000 randomly generated animals and provides
     * an interactive menu-driven interface for the user to perform various operations.
     * 
     * <h2>Menu Options:</h2>
     * <ul>
     *   <li>1 - Display all animals in the zoo</li>
     *   <li>2 - Search for an animal by its code</li>
     *   <li>3 - Add specimens to an existing animal</li>
     *   <li>4 - Remove specimens from an animal</li>
     *   <li>5 - Delete an animal from the zoo</li>
     *   <li>6 - Exit the program</li>
     * </ul>
     * 
     * <h2>Operations:</h2>
     * <p>The method supports the following operations:</p>
     * <ul>
     *   <li><b>Show All:</b> Lists all animals currently in the zoo with their details</li>
     *   <li><b>Search:</b> Finds and displays a specific animal by its code</li>
     *   <li><b>Add Specimens:</b> Increases the count of specimens for an animal by a given quantity</li>
     *   <li><b>Remove Specimens:</b> Decreases the count of specimens for an animal by a given quantity</li>
     *   <li><b>Delete Animal:</b> Removes an animal from the zoo if it has no remaining specimens</li>
     * </ul>
     * 
     * @param args Command line arguments (not used in this implementation)
     * @see Zoologico
     * @see Animal
     * @see Faker
     */
    public static void main(String[] args) {

        // Constantes para las opciones del menú
        final int MOSTRAR = 1;
        final int BUSCAR = 2;
        final int AGREGAR = 3;
        final int RETIRAR = 4;
        final int ELIMINAR = 5;
        final int SALIR = 6;
        // Crear el zoológico y llenarlo con animales generados aleatoriamente
        Zoologico zoo = new Zoologico();
        // Generar 1000 animales aleatorios
        for (int i = 0; i < 1000; i++) {
            Animal animal = new Animal(
                    Faker. nombreAnimal(),
                    Faker.codigoAnimal(),
                    Faker.cantidadAnimal(),
                    Faker.precioAnimal());

            zoo.agregarAnimal(animal);
        }
        // Menú de opciones para el usuario
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (opcion != SALIR) {

            System.out.println("\n===== GESTIÓN DE ZOOLÓGICO =====");
            System.out.println("1. Mostrar todos los animales");
            System.out.println("2. Buscar un animal por código");
            System.out.println("3. Agregar ejemplares a un animal");
            System.out.println("4. Retirar ejemplares de un animal");
            System.out.println("5. Eliminar un animal");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero(sc);
            if (opcion == MOSTRAR) {
                // Mostrar todos los animales

                for (Animal a : zoo.obtenerTodos()) {
                    System.out.println(a);
                }

            } else if (opcion == BUSCAR) {
                // Buscar un animal por código
                System.out.print("Ingrese el código: ");
                String codigo = sc.nextLine().trim();

                if (codigo.isEmpty()) {
                    System.out.println("El código no puede estar vacío.");
                  
                }else {
                    Animal animal = zoo.buscarAnimalPorCodigo(codigo);

                    if (animal != null) {
                        System.out.println("Animal encontrado:");
                        System.out.println(animal);
                    } else {
                        System.out.println("No se encontró ningún animal con el código proporcionado.");
                    }
                }

               

            } else if (opcion == AGREGAR) {
                // Agregar ejemplares a un animal
                System.out.print("Código: ");
                String codigo = sc.nextLine().trim();

                System.out.print("Cantidad a agregar: ");
                int cantidad = leerEntero(sc);

                int resultado = zoo.agregarEjemplares(codigo, cantidad);

                if (resultado == 0)
                    System.out.println("Animal no encontrado.");
                else if (resultado == -1)
                    System.out.println("Cantidad inválida.");
                else
                    System.out.println("Ejemplares agregados correctamente.");

            } else if (opcion == RETIRAR) {
                // Retirar ejemplares de un animal
                System.out.print("Código: ");
                String codigo = sc.nextLine().trim();

                System.out.print("Cantidad a retirar: ");
                int cantidad = leerEntero(sc);

                int resultado = zoo.retirarEjemplares(codigo, cantidad);

                if (resultado == 0)
                    System.out.println("Animal no encontrado.");
                else if (resultado == -1)
                    System.out.println("No se puede retirar más ejemplares de los disponibles.");
                else if (resultado == -2)
                    System.out.println("Cantidad inválida.");
                else
                    System.out.println("Ejemplares retirados correctamente.");

            } else if (opcion == ELIMINAR) {
                // Eliminar un animal
                System.out.print("Código: ");
                String codigo = sc.nextLine().trim();

                int resultado = zoo.eliminarAnimal(codigo);

                if (resultado == 0)
                    System.out.println("Animal no encontrado.");
                else if (resultado == -1)
                    System.out.println("No se puede eliminar un animal con ejemplares disponibles.");
                else
                    System.out.println("Animal eliminado correctamente.");

            } else if (opcion == SALIR) {

                System.out.println("Saliendo del programa...");

            } else {

                System.out.println("Opción no válida. Por favor, intente de nuevo.");

            }
        }

        sc.close();
    }

    public static int leerEntero(Scanner sc) {

        int numero = 0;
        boolean valido = false;

        while (!valido) {
            String texto = sc.nextLine().trim();
            try {
                numero = Integer.parseInt(texto);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.print("Debe introducir un número válido: ");
            }
        }

        return numero;
    }
}