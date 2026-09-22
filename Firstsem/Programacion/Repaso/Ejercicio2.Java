import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Ejercicio2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> marcas = new ArrayList<>();
        ArrayList<Integer> potencias = new ArrayList<>();
        ArrayList<String> trabajos = new ArrayList<>();

        String menu =
                "\n--- TRACTOMATCH ---\n" +
                "1. Añadir tractor\n" +
                "2. Listar tractores\n" +
                "3. Evaluar tractor (por índice)\n" +
                "4. Eliminar tractor (por índice)\n" +
                "0. Salir";

        int opcion = -1;

        while (opcion != 0) {
            opcion = mostrarMenuYLeerOpcion(sc, menu);

            if (opcion == 1) {
                anadirTractor(sc, marcas, potencias, trabajos);
            } else if (opcion == 2) {
                listarTractores(marcas, potencias, trabajos);
            } else if (opcion == 3) {
                evaluarTractor(sc, marcas, potencias, trabajos);
            } else if (opcion == 4) {
                eliminarTractor(sc, marcas, potencias, trabajos);
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

    static void anadirTractor(Scanner sc,
                              ArrayList<String> marcas,
                              ArrayList<Integer> potencias,
                              ArrayList<String> trabajos) {

        imprimirPrompt("Marca del tractor: ");
        String marca = sc.nextLine().trim();

        int potencia = leerEnteroMayorQueCero(sc, "Potencia (CV): ");
        String trabajo = leerTrabajoValido(sc);

        marcas.add(marca);
        potencias.add(potencia);
        trabajos.add(trabajo);

        imprimirMensaje("Tractor añadido correctamente.");
    }

    static void listarTractores(ArrayList<String> marcas,
                                ArrayList<Integer> potencias,
                                ArrayList<String> trabajos) {

        if (marcas.isEmpty()) {
            imprimirMensaje("No hay tractores registrados.");
        } else {
            int indice = 0;
            while (indice < marcas.size()) {
                imprimirMensaje(indice + " -> " + marcas.get(indice) +
                        " | " + potencias.get(indice) +
                        " CV | trabajo: " + trabajos.get(indice));
                indice++;
            }
        }
    }

    static void evaluarTractor(Scanner sc,
                               ArrayList<String> marcas,
                               ArrayList<Integer> potencias,
                               ArrayList<String> trabajos) {

        if (marcas.isEmpty()) {
            imprimirMensaje("No hay tractores para evaluar.");
        } else {
            listarTractores(marcas, potencias, trabajos);
            int indice = pedirIndiceValido(sc, marcas.size(), "Índice del tractor: ");
            String resultado = calcularResultado(trabajos.get(indice), potencias.get(indice));
            imprimirMensaje("Resultado: " + resultado);
            imprimirMensaje(mensajeResultado(resultado));
        }
    }

    static void eliminarTractor(Scanner sc,
                                ArrayList<String> marcas,
                                ArrayList<Integer> potencias,
                                ArrayList<String> trabajos) {

        if (marcas.isEmpty()) {
            imprimirMensaje("No hay tractores para eliminar.");
        } else {
            listarTractores(marcas, potencias, trabajos);
            int indice = pedirIndiceValido(sc, marcas.size(), "Índice a eliminar: ");
            marcas.remove(indice);
            potencias.remove(indice);
            trabajos.remove(indice);
            imprimirMensaje("Tractor eliminado correctamente.");
        }
    }

    static String leerTrabajoValido(Scanner sc) {
        String trabajo = "";
        boolean valido = false;

        while (!valido) {
            imprimirPrompt("Trabajo (arado / transporte / siembra): ");
            trabajo = sc.nextLine().trim().toLowerCase();
            valido = esTrabajoValido(trabajo);
            if (!valido) {
                imprimirMensaje("Trabajo no válido.");
            }
        }
        return trabajo;
    }

    static boolean esTrabajoValido(String trabajo) {
        boolean resultado =
                trabajo.equals("arado") ||
                trabajo.equals("transporte") ||
                trabajo.equals("siembra");
        return resultado;
    }

    static String calcularResultado(String trabajo, int potencia) {
        String resultado = "NO APTO";

        if (trabajo.equals("arado") && potencia >= 120) {
            resultado = "APTO";
        } else if (trabajo.equals("transporte") && potencia >= 90) {
            resultado = "APTO";
        } else if (trabajo.equals("siembra") && potencia >= 70) {
            resultado = "APTO";
        }

        return resultado;
    }

    static String mensajeResultado(String resultado) {
        ArrayList<String> mensajesApto = new ArrayList<>();
        mensajesApto.add("Este tractor es adecuado para el trabajo.");
        mensajesApto.add("Buena elección para este tipo de tarea.");

        ArrayList<String> mensajesNoApto = new ArrayList<>();
        mensajesNoApto.add("La potencia es insuficiente.");
        mensajesNoApto.add("Sería mejor usar un tractor más potente.");

        // Se puede usar también Math.random() en lugar de Random
        Random generadorAleatorio = new Random();
        String mensaje = "";

        if (resultado.equals("APTO")) {
            mensaje = mensajesApto.get(generadorAleatorio.nextInt(mensajesApto.size()));
        } else {
            mensaje = mensajesNoApto.get(generadorAleatorio.nextInt(mensajesNoApto.size()));
        }

        return mensaje;
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