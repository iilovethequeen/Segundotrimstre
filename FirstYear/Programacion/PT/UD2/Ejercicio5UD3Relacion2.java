package Programacion.PT.UD2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio5UD3Relacion2 {

    public static int detectarPalabrasClave(String texto, ArrayList<String> palabrasClave) {
        int contador = 0;
        String textoMinusculas = texto.toLowerCase();

        for (String palabra : palabrasClave) {
            String palabraMinusculas = palabra.toLowerCase();
            int indice = textoMinusculas.indexOf(palabraMinusculas);
            while (indice != -1) {
                contador++;
                indice = textoMinusculas.indexOf(palabraMinusculas, indice + palabraMinusculas.length());
            }
        }

        return contador;
    }

     // ============================= MAIN ============================= //
    public static void main(String[] args) {

        String texto = "Me gusta programar en Python y Java";

        ArrayList<String> plabarsClave = new ArrayList<>(
                Arrays.asList("python", "java", "c++"));

        int recuentoPalabrasClave = detectarPalabrasClave(texto, plabarsClave);

        System.out.println("Las palabras clave aparecen " + recuentoPalabrasClave + " veces.");

    }
}