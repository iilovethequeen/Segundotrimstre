package Programacion;

import java.util.ArrayList;

public class ejercicio3Ud2Realcion1 {
    public static void main(String[] args) {
      ArrayList<String> animales = new ArrayList<String>();
        animales.add("Perro");
        animales.add("Gato");
        animales.add( 0, "loro");
        animales.add( 0, "tortuga");
       
        for (String animal : animales) {
            System.out.println(animal);
        }
        System.out.println(animales.remove(0));
    }

}