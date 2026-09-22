package Repaso;


import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> matriculas = new ArrayList<>();
        ArrayList<String> tiposRevision = new ArrayList<>();
        ArrayList<Integer> serviciosExtra = new ArrayList<>();
        ArrayList<Double> costesFinales = new ArrayList<>();

        String menu =
                "\n--- TALLER DE TRACTORES: REVISIONES ---\n" +
                "1. Añadir revisión\n" +
                "2. Listar revisiones\n" +
                "3. Mostrar coste de una revisión (por índice)\n" +
                "4. Eliminar revisión (por índice)\n" +
                "0. Salir";

        int opcion = -1;

        while (opcion != 0) {
            opcion = mostrarMenuYLeerOpcion(sc, menu);

            if (opcion == 1) {
                anadirRevision(sc, matriculas, tiposRevision, serviciosExtra, costesFinales);
            } else if (opcion == 2) {
                listarRevisiones(matriculas, tiposRevision, serviciosExtra, costesFinales);
            } else if (opcion == 3) {
                mostrarCoste(sc, matriculas, tiposRevision, serviciosExtra, costesFinales);
            } else if (opcion == 4) {
                eliminarRevision(sc, matriculas, tiposRevision, serviciosExtra, costesFinales);
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

    // ------------------ AÑADIR ------------------
    static void anadirRevision(Scanner sc,
                              ArrayList<String> matriculas,
                              ArrayList<String> tiposRevision,
                              ArrayList<Integer> serviciosExtra,
                              ArrayList<Double> costesFinales) {

        imprimirPrompt("Matrícula: ");
        String matricula = sc.nextLine().trim().toUpperCase();

        String tipo = leerTipoRevisionValido(sc);

        int extras = leerEnteroMayorOIgualQueCero(sc, "Número de servicios extra: ");

        boolean aplicaDescuento = terminaEnPar(matricula);
        double coste = calcularCoste(tipo, extras, aplicaDescuento);

        matriculas.add(matricula);
        tiposRevision.add(tipo);
        serviciosExtra.add(extras);
        costesFinales.add(coste);

        imprimirMensaje("Revisión añadida. Coste guardado: " + formatoEuros(coste));
        if (!terminaEnDigito(matricula)) {
            imprimirMensaje("Aviso: la matrícula no termina en número; no se aplica descuento.");
        } else if (aplicaDescuento) {
            imprimirMensaje("Descuento aplicado: 10% (matrícula termina en número par).");
        } else {
            imprimirMensaje("Sin descuento (matrícula termina en número impar).");
        }
    }

    // ------------------ LISTAR ------------------
    static void listarRevisiones(ArrayList<String> matriculas,
                                 ArrayList<String> tiposRevision,
                                 ArrayList<Integer> serviciosExtra,
                                 ArrayList<Double> costesFinales) {

        if (matriculas.isEmpty()) {
            imprimirMensaje("No hay revisiones registradas.");
        } else {
            imprimirMensaje("\nLISTA DE REVISIONES:");
            int indice = 0;
            while (indice < matriculas.size()) {
                imprimirMensaje(indice + " -> " +
                        matriculas.get(indice) +
                        " | " + tiposRevision.get(indice) +
                        " | extras: " + serviciosExtra.get(indice) +
                        " | coste: " + formatoEuros(costesFinales.get(indice)));
                indice++;
            }
        }
    }

    // ------------------ MOSTRAR COSTE ------------------
    static void mostrarCoste(Scanner sc,
                             ArrayList<String> matriculas,
                             ArrayList<String> tiposRevision,
                             ArrayList<Integer> serviciosExtra,
                             ArrayList<Double> costesFinales) {

        if (matriculas.isEmpty()) {
            imprimirMensaje("No hay revisiones para consultar.");
        } else {
            listarRevisiones(matriculas, tiposRevision, serviciosExtra, costesFinales);
            int indice = pedirIndiceValido(sc, matriculas.size(), "Índice de la revisión: ");

            String matricula = matriculas.get(indice);
            String tipo = tiposRevision.get(indice);
            int extras = serviciosExtra.get(indice);

            boolean aplicaDescuento = terminaEnPar(matricula);
            double costeRecalculado = calcularCoste(tipo, extras, aplicaDescuento);

            imprimirMensaje("Revisión: " + matricula + " | " + tipo + " | extras: " + extras);
            imprimirMensaje("Coste: " + formatoEuros(costeRecalculado));

            if (!terminaEnDigito(matricula)) {
                imprimirMensaje("Aviso: la matrícula no termina en número; no se aplica descuento.");
            } else if (aplicaDescuento) {
                imprimirMensaje("Descuento aplicado: 10% (matrícula termina en número par).");
            } else {
                imprimirMensaje("Sin descuento (matrícula termina en número impar).");
            }

            // Sincronizamos el coste guardado (por si alguien lo editó manualmente o si se quiere recalcular siempre)
            costesFinales.set(indice, costeRecalculado);
        }
    }

    // ------------------ ELIMINAR ------------------
    static void eliminarRevision(Scanner sc,
                                ArrayList<String> matriculas,
                                ArrayList<String> tiposRevision,
                                ArrayList<Integer> serviciosExtra,
                                ArrayList<Double> costesFinales) {

        if (matriculas.isEmpty()) {
            imprimirMensaje("No hay revisiones para eliminar.");
        } else {
            listarRevisiones(matriculas, tiposRevision, serviciosExtra, costesFinales);
            int indice = pedirIndiceValido(sc, matriculas.size(), "Índice de la revisión a eliminar: ");

            matriculas.remove(indice);
            tiposRevision.remove(indice);
            serviciosExtra.remove(indice);
            costesFinales.remove(indice);

            imprimirMensaje("Revisión eliminada correctamente.");
        }
    }

    // ------------------ LÓGICA ------------------
    static String leerTipoRevisionValido(Scanner sc) {
        String tipo = "";
        boolean tipoCorrecto = false;

        while (!tipoCorrecto) {
            imprimirPrompt("Tipo de revisión (basica / completa): ");
            tipo = sc.nextLine().trim().toLowerCase();

            tipoCorrecto = tipo.equals("basica") || tipo.equals("completa");

            if (!tipoCorrecto) {
                imprimirMensaje("Tipo no válido. Debe ser basica o completa.");
            }
        }

        return tipo;
    }

    static double calcularCoste(String tipo, int extras, boolean aplicaDescuento) {
        double base = 0.0;

        if (tipo.equals("basica")) {
            base = 60.0;
        } else if (tipo.equals("completa")) {
            base = 120.0;
        }

        double subtotal = base + (extras * 15.0);
        double total = subtotal;

        if (aplicaDescuento) {
            total = subtotal * 0.9;
        }

        return total;
    }

    static boolean terminaEnDigito(String texto) {
        boolean terminaEnNumero = false;

        if (texto.length() > 0) {
            char ultimo = texto.charAt(texto.length() - 1);
            terminaEnNumero = (ultimo >= '0' && ultimo <= '9');
        }

        return terminaEnNumero;
    }

    static boolean terminaEnPar(String texto) {
        boolean par = false;

        if (terminaEnDigito(texto)) {
            char ultimo = texto.charAt(texto.length() - 1);
            int numero = ultimo - '0';
            par = (numero % 2 == 0);
        }

        return par;
    }

    static String formatoEuros(double cantidad) {
        String texto = String.format("%.2f €", cantidad);
        return texto;
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

    static int leerEnteroMayorOIgualQueCero(Scanner sc, String prompt) {
        int numero = -1;

        while (numero < 0) {
            imprimirPrompt(prompt);
            numero = leerEntero(sc);

            if (numero < 0) {
                imprimirMensaje("Debe ser 0 o mayor.");
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
