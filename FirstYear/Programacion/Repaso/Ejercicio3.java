package Repaso;

import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> modelos = new ArrayList<>();
        ArrayList<Integer> preciosIniciales = new ArrayList<>();
        ArrayList<Integer> mejoresPujas = new ArrayList<>();
        ArrayList<String> mejoresPostores = new ArrayList<>();

        String menu = "\n--- SUBASTA DE TRACTORES ---\n" +
                "1. Añadir lote\n" +
                "2. Listar lotes\n" +
                "3. Hacer puja (por índice)\n" +
                "4. Ver ganador actual (por índice)\n" +
                "5. Eliminar lote (por índice)\n" +
                "0. Salir";

        int opcion = -1;

        while (opcion != 0) {
            opcion = mostrarMenuYLeerOpcion(sc, menu);

            if (opcion == 1) {
                anadirLote(sc, modelos, preciosIniciales, mejoresPujas, mejoresPostores);
            } else if (opcion == 2) {
                listarLotes(modelos, preciosIniciales, mejoresPujas, mejoresPostores);
            } else if (opcion == 3) {
                hacerPuja(sc, modelos, preciosIniciales, mejoresPujas, mejoresPostores);
            } else if (opcion == 4) {
                verGanador(sc, modelos, preciosIniciales, mejoresPujas, mejoresPostores);
            } else if (opcion == 5) {
                eliminarLote(sc, modelos, preciosIniciales, mejoresPujas, mejoresPostores);
            } else if (opcion == 0) {
                imprimirMensaje("Saliendo del programa...");
            } else {
                imprimirMensaje("Opción no válida.");
            }
        }

        sc.close();
    }

    // ------------------ IMPRESIÓN ------------------
    static void imprimirMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    static void imprimirPrompt(String mensaje) {
        System.out.print(mensaje);
    }

    // ------------------ MENÚ ------------------
    static int mostrarMenuYLeerOpcion(Scanner sc, String menu) {
        imprimirMensaje(menu);
        imprimirPrompt("Opción: ");
        int opcion = leerEntero(sc);
        return opcion;
    }

    // ------------------ AÑADIR LOTE ------------------
    static void anadirLote(Scanner sc,
            ArrayList<String> modelos,
            ArrayList<Integer> preciosIniciales,
            ArrayList<Integer> mejoresPujas,
            ArrayList<String> mejoresPostores) {

        imprimirPrompt("Modelo del tractor: ");
        String modelo = sc.nextLine().trim();

        int precioInicial = leerEnteroMayorQueCero(sc, "Precio inicial (€): ");

        modelos.add(modelo);
        preciosIniciales.add(precioInicial);
        mejoresPujas.add(0);
        mejoresPostores.add("NADIE");

        imprimirMensaje("Lote añadido correctamente.");
    }

    // ------------------ LISTAR LOTES ------------------
    static void listarLotes(ArrayList<String> modelos,
            ArrayList<Integer> preciosIniciales,
            ArrayList<Integer> mejoresPujas,
            ArrayList<String> mejoresPostores) {

        if (modelos.isEmpty()) {
            imprimirMensaje("No hay lotes registrados.");
        } else {
            imprimirMensaje("\nLISTA DE LOTES:");
            int indice = 0;
            while (indice < modelos.size()) {
                imprimirMensaje(indice + " -> " +
                        modelos.get(indice) +
                        " | inicial: " + preciosIniciales.get(indice) +
                        " | mejor puja: " + mejoresPujas.get(indice) +
                        " | postor: " + mejoresPostores.get(indice));
                indice++;
            }
        }
    }

    // ------------------ PUJAR ------------------
    static void hacerPuja(Scanner sc,
            ArrayList<String> modelos,
            ArrayList<Integer> preciosIniciales,
            ArrayList<Integer> mejoresPujas,
            ArrayList<String> mejoresPostores) {

        if (modelos.isEmpty()) {
            imprimirMensaje("No hay lotes para pujar.");
        } else {
            listarLotes(modelos, preciosIniciales, mejoresPujas, mejoresPostores);

            int indice = pedirIndiceValido(sc, modelos.size(), "Índice del lote: ");

            imprimirPrompt("Nombre del postor: ");
            String postor = sc.nextLine().trim();

            int cantidadPuja = leerEnteroMayorQueCero(sc, "Cantidad de la puja (€): ");

            int precioInicial = preciosIniciales.get(indice);
            int mejorPujaActual = mejoresPujas.get(indice);

            boolean superaPrecioInicial = (cantidadPuja >= precioInicial);
            boolean superaMejorPuja = (cantidadPuja > mejorPujaActual);

            if (!superaPrecioInicial) {
                imprimirMensaje("Puja rechazada: no llega al precio inicial (" + precioInicial + ").");
            } else if (!superaMejorPuja) {
                imprimirMensaje("Puja rechazada: debe superar la mejor puja actual (" + mejorPujaActual + ").");
            } else {
                mejoresPujas.set(indice, cantidadPuja);
                mejoresPostores.set(indice, postor);
                imprimirMensaje("Puja aceptada. Nuevo ganador provisional: " + postor + " con " + cantidadPuja + "€.");
            }
        }
    }

    // ------------------ VER GANADOR ------------------
    static void verGanador(Scanner sc,
            ArrayList<String> modelos,
            ArrayList<Integer> preciosIniciales,
            ArrayList<Integer> mejoresPujas,
            ArrayList<String> mejoresPostores) {

        if (modelos.isEmpty()) {
            imprimirMensaje("No hay lotes para consultar.");
        } else {
            listarLotes(modelos, preciosIniciales, mejoresPujas, mejoresPostores);

            int indice = pedirIndiceValido(sc, modelos.size(), "Índice del lote: ");

            String modelo = modelos.get(indice);
            int mejorPuja = mejoresPujas.get(indice);
            String postor = mejoresPostores.get(indice);

            imprimirMensaje("Lote: " + modelo);

            if (mejorPuja == 0) {
                imprimirMensaje("Ganador actual: SIN PUJAS");
            } else {
                imprimirMensaje("Ganador actual: " + postor + " con " + mejorPuja + "€.");
            }
        }
    }

    // ------------------ ELIMINAR LOTE ------------------
    static void eliminarLote(Scanner sc,
            ArrayList<String> modelos,
            ArrayList<Integer> preciosIniciales,
            ArrayList<Integer> mejoresPujas,
            ArrayList<String> mejoresPostores) {

        if (modelos.isEmpty()) {
            imprimirMensaje("No hay lotes para eliminar.");
        } else {
            listarLotes(modelos, preciosIniciales, mejoresPujas, mejoresPostores);

            int indice = pedirIndiceValido(sc, modelos.size(), "Índice del lote a eliminar: ");

            modelos.remove(indice);
            preciosIniciales.remove(indice);
            mejoresPujas.remove(indice);
            mejoresPostores.remove(indice);

            imprimirMensaje("Lote eliminado correctamente.");
        }
    }

    // ------------------ LECTURA Y VALIDACIÓN ------------------
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

    static int leerEnteroMayorQueCero(Scanner sc, String prompt) {
        int numero = 0;

        while (numero <= 0) {
            imprimirPrompt(prompt);
            numero = leerEntero(sc);

            if (numero <= 0) {
                imprimirMensaje("Debe ser mayor que 0.");
            }
        }

        return numero;
    }

    static int pedirIndiceValido(Scanner sc, int tam, String prompt) {
        int indice = -1;

        while (indice < 0 || indice >= tam) {
            imprimirPrompt(prompt);
            indice = leerEntero(sc);

            if (indice < 0 || indice >= tam) {
                imprimirMensaje("Índice fuera de rango.");
            }
        }

        return indice;
    }
}
