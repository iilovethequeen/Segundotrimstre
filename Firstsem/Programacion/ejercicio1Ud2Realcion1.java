package Programacion;

import java.util.ArrayList;

public class ejercicio1Ud2Realcion1 {
    public static void main(String[] args) {
        ArrayList<Integer> numeros = new ArrayList<Integer>();
        numeros.add(5);
        numeros.add(10);
        numeros.add(15);
        numeros.add(20);
       for (Integer integer : numeros) {
        System.err.println(integer);
       }
       System.out.println(numeros.get(0));
        System.out.println(numeros.get(numeros.size() - 1));
    }

}


   =
 
