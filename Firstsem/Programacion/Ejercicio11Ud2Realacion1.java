package Programacion;

import java.util.ArrayList;

public class Ejercicio11Ud2Realacion1 {
    
    public static void main(String[] args) {
        ArrayList<String> nombres = new ArrayList<String>();
        nombres.add("Ana");
        nombres.add("Pedro");

        nombres.add("Lura");
        nombres.add(0,"Carlos");

        String eliminadoPrimero = nombres.remove(0);
        String  eliminadoUltimo = nombres.remove(nombres.size() - 1);
       

        System.out.println("Nombres en la lista:");
        for (String nombre : nombres) {
            System.out.println(nombre);
        }
        System.out.println("Nombre eliminadoUltimo:" + eliminadoUltimo);
        System.out.println("Nombre eliminadoPrimero:" + eliminadoPrimero);
        
    }
}
