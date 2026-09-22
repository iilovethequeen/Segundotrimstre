package Programacion.PT.UD2.Relacion2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio6 {
    public static void main(String[] args) {
        ArrayList<String> tareas = new ArrayList<>(
                Arrays.asList("tarea1", "tarea2", "tarea3"));

        ArrayList<Boolean> completadas = new ArrayList<>(
                Arrays.asList(false, true, false));

        int opcion = 0;
        // Variable para la opción del menú
        while(opcion != 5 ){
            System.out.println("\n1. Añadir una nueva tarea.");
            System.out.println("2. Marcar tarea como completada.");
            System.out.println("3. Ver todas las tareas.");
            System.out.println("4. Ver tareas pendientes.");
            System.out.println("5. Salir.");
            System.out.print("Seleccione una opción: ");
            Scanner scanner = new Scanner(System.in);
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Entrada no válida. Inténtelo de nuevo.");
                opcion = -1;
            }

            if(opcion == 1) {
                String nuevaTarea = "";
                System.out.print("Introduzca la descripción de la nueva tarea: ");
                nuevaTarea = scanner.nextLine();
                tareas.add(nuevaTarea);
                completadas.add(false);
            } else if(opcion == 2) {
                String buscarTarea = "";
                System.out.print("Introduzca la descripción de la tarea a marcar como completada: ");   
                buscarTarea = scanner.nextLine();
                boolean encontrada = false;
                for(int i = 0; i < tareas.size(); i++) {
                    if(tareas.get(i).equalsIgnoreCase(buscarTarea)) {
                        completadas.set(i, true);
                        encontrada = true;
                        System.out.println("Tarea marcada como completada.");
                        
                    }else{
                        System.out.println("Tarea no encontrada.");
                    }

                }
            } else if(opcion == 3) {
                ArrayList<String> pendientes = new ArrayList<>();

                for(int i = 0; i < tareas.size(); i++) {
                 if(!completadas.get(i)) {
                     pendientes.add(tareas.get(i));
                 }
                }
                System.out.println("Tareas pendientes:");



            } else if (opcion == 4) {
                System.out.println("Todas las tareas:");
                for(int i = 0; i < tareas.size(); i++) {
                    String estado = completadas.get(i) ? "Completada" : "Pendiente";
                    System.out.println("- " + tareas.get(i) + " [" + estado + "]");
                }
            } else if (opcion != 5) {
                System.out.println("Entrada no válida. Inténtelo de nuevo.");
            }
        }
    }
}
