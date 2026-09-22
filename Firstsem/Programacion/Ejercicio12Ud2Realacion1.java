package Programacion;

import java.util.ArrayList;

public class Ejercicio12Ud2Realacion1 {
    public static void main(String[] args) {
        ArrayList<String>  letras = new ArrayList<String>();
        letras.add("A");
        letras.add("B");
        letras.add("C");
        letras.add("D");
        ArrayList<String> invertido = new ArrayList<String>();
        int tamanioLetras = letras.size();


        for (int i = 0; i < tamanioLetras; i++) {
            invertido.add(letras.remove(letras.size() - 1));
        }
          System.out.println(invertido);

    }

}
