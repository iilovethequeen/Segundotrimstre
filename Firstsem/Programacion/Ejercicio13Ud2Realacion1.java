package Programacion;

import java.util.ArrayList;
import java.util.Arrays;

public class Ejercicio13Ud2Realacion1 {
    public static void main(String[] args) {
       ArrayList<Integer> numeros = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
         for (int i = numeros.size(); i > 0; i--) {
          numeros.remove(i - 1);
          System.out.println(numeros);
          
         }

    }
}
