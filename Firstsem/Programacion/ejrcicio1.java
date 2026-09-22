package Programacion;

import java.util.Scanner;

public class ejrcicio1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // datos de entrada
        // === Número de encargos ===
        int numerentragos = 0;
        do {
            System.out.print("\"Número de encargos: ");
            numerentragos = scanner.nextInt();
        } while (numerentragos <= 0);

        // === Stock De Madera disponible ===  
        int stockMadera = 0;
        do {
            System.out.print("Stock de madera disponible (en kg): ");
            stockMadera = scanner.nextInt();
        } while (stockMadera <= 0);

       // === Stock De Acero disponible ===
        double stockAcero = 0;
        do {
            System.out.print("Stock de acero disponible (en kg): ");
            stockAcero = scanner.nextDouble();
        } while (stockAcero <= 0);

        // === Variables acumuladas ===
        int totalMader = 0;
        double totalAcero = 0;
        double totalDinero = 0.0;
        int unidadesFlecha = 0;
        int unidadesEscudo = 0;
        int unidadesEspada = 0;
        boolean superaStock = false;
        int i = 1;

        // === 3) Proceso de registro de encargos ===
        while (i <= numerentragos && superaStock == false) {
            System.out.println("Encargo número " + i);
        String tipo; 
        // Validar tipo de encargo
        do {
            System.out.print("Tipo de encargo (Flecha/Escudo/Espada): ");
            tipo = scanner.next();
        } while (!tipo.equals("Flecha") && !tipo.equals("Escudo") && !tipo.equals("Espada"));
            // Validar número de unidades
        int unidades = 0;
        do {
            System.out.print("Número de unidades: ");
            unidades = scanner.nextInt();
        } while (unidades < 0);

        
        // Calcular consumo e importe
        int  madera = 0; 
        double acero = 0.0;
        double precio = 0.0;

        if (tipo.equals("Flecha")) {
            madera = 1 * unidades;
            acero =  0.05 * unidades;
            precio = 2 * unidades;
        } else if (tipo.equals("Escudo")) {
            madera = 3 * unidades;
            acero = 1.5 * unidades ;
            precio = 30 * unidades;
        } else if (tipo.equals("Espada")) {
            madera = 3 * unidades;
            acero = 1.5 * unidades ;
            precio = 30.0 * unidades;
        }
        if (totalMader + madera > stockMadera) {
            System.out.println("Alerta! Has superado el stock de madera.");
            superaStock = true;
        } else if (totalAcero + acero > stockAcero) {
            System.out.println("Alerta! Has superado el stock de acero.");
            superaStock = true;
        }else {


     // Acumular totales
        totalMader += madera;
        totalAcero += acero;
        totalDinero += precio;
        }

      

        // Actualizar contadores de unidades
        if (tipo.equals("Flecha")) {
            unidadesFlecha += unidades;
        } else if (tipo.equals("Escudo")) {
            unidadesEscudo += unidades;
        } else if (tipo.equals("Espada")) {
            unidadesEspada += unidades;
        }

        // Verificar si se supera el stock
        if (totalMader > stockMadera) {
            superaStock = true; // Se supera el stock de madera
                // Mostrar resumen del encargo
          System.out.println("Artículo: " + tipo + " | Unidades: " + unidades + " | Importe cobrado: " + precio);
          System.out.println("Madera gastada: " + madera + " | Acumulada: " + totalMader);
          System.out.println("Acero gastado: " + acero + " | Acumulada: " + totalAcero);
        }
        i++;
        
     }
        // === 4) Resultados finales ===
      System.out.println("=== RESUMEN FINAL ===");
      System.out.println("Encargos registrados: " + (superaStock ? i - 1 : numerentragos) + " de " + numerentragos);
      System.out.println("Unidades -> Flecha: " + unidadesFlecha + " | Escudo: " + unidadesEscudo + " | Espada: " + unidadesEspada);
      System.out.println("Consumo total -> Madera: " + totalMader + " varas | Acero: %.2f " + totalAcero + " kg");
      System.out.println("Stock restante -> Madera: " + (stockMadera - totalMader) + " | Acero: " + (stockAcero - totalAcero));
      System.out.println("Importe total: " + totalDinero + " monedas");

      if (superaStock == true) {
        System.out.println("Registro interrumpido por superación de stock.");
      } else {
        System.out.println("Registro completado con éxito.");
      }
        scanner.close();
}}