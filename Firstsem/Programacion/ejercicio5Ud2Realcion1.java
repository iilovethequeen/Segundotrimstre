package Programacion;

import java.util.ArrayList;

public class ejercicio5Ud2Realcion1 {
    public static void main(String[] args) {
      ArrayList<String> frutas = new ArrayList<String>();
        frutas.add("Manzana");
        frutas.add("Banana");
        frutas.add( "Naranja");
        frutas.remove(0);
        frutas.add( "fresa");
       
        for (String fruta : frutas) {
            System.out.println(fruta);
        }
    }

}