package Programacion;

import java.util.ArrayList;

public class Ejercicio10Ud2Realacion1 {
    public static void main(String[] args) {
        ArrayList<String> nombres = new ArrayList<String>();
        nombres.add("Ana");
        nombres.add("Pedro");

        nombres.add("Lura");
        nombres.add(0,"Carlos");

        System.out.println("Nombres en la lista:");
        for (String nombre : nombres) {
            System.out.println(nombre);
        }
    }
}
