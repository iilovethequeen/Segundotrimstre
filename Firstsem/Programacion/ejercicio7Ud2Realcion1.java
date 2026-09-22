package Programacion;

import java.util.ArrayList;

public class ejercicio7Ud2Realcion1 {
    public static void main(String[] args) {
        ArrayList<Integer> numeros = new ArrayList<Integer>();
        numeros.add(100);
        numeros.add(200);
        numeros.add(300);

        int numeroTemporal = numeros.get(0);
        int numeroTemporal2 = numeros.get(2);
        numeroTemporal = numeros.set(2, numeroTemporal);
        numeroTemporal2 = numeros.set(0, numeroTemporal2);

        


       for (Integer integer : numeros) {
        System.err.println(integer);
       }
    
    }

}
