package Repaso;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Ejercicio4 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> tractores = new ArrayList<>();
        ArrayList<Double> kilometros = new ArrayList<>();
        ArrayList<Double> litrosDisponibles = new ArrayList<>();
        ArrayList<String> terrenos = new ArrayList<>();

        String menu = "\n--- RUTAS DE REPARTO CON TRACTORES ---\n" +
                "1. Añadir ruta\n" +
                "2. Listar rutas\n" +
                "3. Calcular consumo (por índice)\n" +
                "4. Eliminar ruta (por índice)\n" +
                "0. Salir";

        int opcion = -1;

        while (opcion != 0) {
            opcion = mostrarMenuYLeerOpcion(sc, menu);

            if (opcion == 1) {
                anadirRuta(sc, tractores, kilometros, litrosDisponibles, terrenos);
            } else if (opcion == 2) {
                listarRutas(tractores, kilometros, litrosDisponibles, terrenos);
            } else if (opcion == 3) {
                calcularConsumoRuta(sc, tractores, kilometros, litrosDisponibles, terrenos);
            } else if (opcion == 4) {
                eliminarRuta(sc, tractores, kilometros, litrosDisponibles, terrenos);
            } else if (opcion == 0) {
                imprimirMensaje("Saliendo del programa...");
            } else {
                imprimirMensaje("Opción no válida.");
            }
        }

        sc.close();
    }

    static void imprimirMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    static void imprimirPrompt(String mensaje) {
        System.out.print(mensaje);
    }

    static int mostrarMenuYLeerOpcion(Scanner sc, String menu) {
        imprimirMensaje(menu);
        imprimirPrompt("Opción: ");
        int opcion = leerEntero(sc);
        return opcion;
    }

    static void anadirRuta(Scanner sc,
            ArrayList<String> tractores,
            ArrayList<Double> kilometros,
            ArrayList<Double> litrosDisponibles,
            ArrayList<String> terrenos) {

        imprimirPrompt("Tractor: ");
        String tractor = sc.nextLine().trim();

        double km = leerDoubleMayorQueCero(sc, "Kilómetros: ");
        double litros = leerDoubleMayorQueCero(sc, "Litros disponibles: ");
        String terreno = leerTerrenoValido(sc);

        tractores.add(tractor);
        kilometros.add(km);
        litrosDisponibles.add(litros);
        terrenos.add(terreno);

        imprimirMensaje("Ruta añadida correctamente.");
    }

    static void listarRutas(ArrayList<String> tractores,
            ArrayList<Double> kilometros,
            ArrayList<Double> litrosDisponibles,
            ArrayList<String> terrenos) {

        if (tractores.isEmpty()) {
            imprimirMensaje("No hay rutas registradas.");
        } else {
            int indice = 0;
            while (indice < tractores.size()) {
                imprimirMensaje(indice + " -> " + tractores.get(indice) +
                        " | km: " + kilometros.get(indice) +
                        " | litros: " + litrosDisponibles.get(indice) +
                        " | terreno: " + terrenos.get(indice));
                indice++;
            }
        }
    }

    static void calcularConsumoRuta(Scanner sc,
            ArrayList<String> tractores,
            ArrayList<Double> kilometros,
            ArrayList<Double> litrosDisponibles,
            ArrayList<String> terrenos) {

        if (tractores.isEmpty()) {
            imprimirMensaje("No hay rutas para calcular.");
        } else {
            listarRutas(tractores, kilometros, litrosDisponibles, terrenos);
            int indice = pedirIndiceValido(sc, tractores.size(), "Índice de la ruta: ");

            double km = kilometros.get(indice);
            double litros = litrosDisponibles.get(indice);
            String terreno = terrenos.get(indice);

            double consumo = consumoPorTerreno(terreno);
            double litrosNecesarios = km * consumo;

            String resultado = "NO VIABLE";
            if (litros >= litrosNecesarios) {
                resultado = "VIABLE";
            }

            imprimirMensaje("Resultado: " + resultado);

            if (resultado.equals("VIABLE")) {
                imprimirMensaje(mensajeViable());
            } else {
                imprimirMensaje(mensajeNoViable());
            }
        }
    }

    static void eliminarRuta(Scanner sc,
            ArrayList<String> tractores,
            ArrayList<Double> kilometros,
            ArrayList<Double> litrosDisponibles,
            ArrayList<String> terrenos) {

        if (tractores.isEmpty()) {
            imprimirMensaje("No hay rutas para eliminar.");
        } else {
            listarRutas(tractores, kilometros, litrosDisponibles, terrenos);
            int indice = pedirIndiceValido(sc, tractores.size(), "Índice a eliminar: ");

            tractores.remove(indice);
            kilometros.remove(indice);
            litrosDisponibles.remove(indice);
            terrenos.remove(indice);

            imprimirMensaje("Ruta eliminada correctamente.");
        }
    }

    static String leerTerrenoValido(Scanner sc) {
        String terreno = "";
        boolean valido = false;

        while (!valido) {
            imprimirPrompt("Terreno (llano / mixto / montaña): ");
            terreno = sc.nextLine().trim().toLowerCase();
            valido = terreno.equals("llano") || terreno.equals("mixto") || terreno.equals("montaña");
            if (!valido) {
                imprimirMensaje("Terreno no válido.");
            }
        }
        return terreno;
    }

    static double consumoPorTerreno(String terreno) {
        double consumo = 0.0;

        if (terreno.equals("llano")) {
            consumo = 0.12;
        } else if (terreno.equals("mixto")) {
            consumo = 0.18;
        } else if (terreno.equals("montaña")) {
            consumo = 0.25;
        }
        return consumo;
    }

    static String mensajeViable() {
        ArrayList<String> mensajes = new ArrayList<>();
        mensajes.add("Ruta viable: combustible suficiente.");
        mensajes.add("Puedes realizar la ruta sin repostar.");

        // Se puede usar también Math.random() en lugar de Random
        Random r = new Random();
        String mensaje = mensajes.get(r.nextInt(mensajes.size()));
        return mensaje;
    }

    static String mensajeNoViable() {
        ArrayList<String> mensajes = new ArrayList<>();
        mensajes.add("Ruta no viable: falta combustible.");
        mensajes.add("Necesitas repostar antes de salir.");

        // Se puede usar también Math.random() en lugar de Random
        Random r = new Random();
        String mensaje = mensajes.get(r.nextInt(mensajes.size()));
        return mensaje;
    }

    static int leerEntero(Scanner sc) {
        int numero = 0;
        boolean valido = false;

        while (!valido) {
            String texto = sc.nextLine().trim();
            try {
                numero = Integer.parseInt(texto);
                valido = true;
            } catch (NumberFormatException e) {
                imprimirPrompt("Introduce un número entero: ");
            }
        }
        return numero;
    }

    static double leerDoubleMayorQueCero(Scanner sc, String prompt) {
        double numero = 0.0;
        boolean valido = false;

        while (!valido) {
            imprimirPrompt(prompt);
            try {
                numero = Double.parseDouble(sc.nextLine().trim());
                valido = numero > 0;
                if (!valido) {
                    imprimirMensaje("Debe ser mayor que 0.");
                }
            } catch (NumberFormatException e) {
                imprimirMensaje("Número no válido.");
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
