package Programacion;

import java.util.ArrayList;

public class ejercicio2Ud2Realcion1 {
    public static void main(String[] args) {
      ArrayList<String> colores = new ArrayList<String>();
        colores.add("Rojo");
        colores.add("Verde");
        colores.add("Azul");

       String eliminado = colores.remove(colores.size() - 1);
        for (String color : colores) {
            System.out.println(color);
        }
        System.out.print(String.join("-" + eliminado));
    }

}
