package Programacion;

import java.util.ArrayList;

public class ejercicio6Ud2Realcion1 {
    public static void main(String[] args) {
        ArrayList<String> dias = new ArrayList<String>();
        dias.add("Lunes");
        dias.add("Martes");
        dias.add("Miércoles");
        dias.add("Jueves");
        dias.add("Viernes");
        dias.add("Sábado");
        dias.add("Domingo");
        dias.set(dias.size() - 1, "domingo (fin de semana)");

        for (String dia : dias) {
            System.out.println(dia);
        }
    }

}