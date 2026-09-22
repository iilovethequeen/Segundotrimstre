package Programacion;

import java.util.ArrayList;

public class Ejercicio8Ud2Realacion1 {
    public static void main(String[] args) {
        ArrayList<String> cola = new ArrayList<String>();
        cola.add("cliente1");
        cola.add("cliente2");
        cola.add("cliente3");
        System.out.println(cola);
        String clienteAtendido = cola.remove(0);
        System.out.println("Atendiendo a: " + clienteAtendido);
        System.out.println("Clientes restantes: " + cola);
        clienteAtendido = cola.remove(0);
        System.out.println("He atendido a " + clienteAtendido);
        System.out.println("Clientes restantes: " + cola);
        clienteAtendido = cola.remove(0);
        System.out.println("He atendido a " + clienteAtendido);
        System.out.println("Clientes restantes: " + cola);
       
        for (String cliente : cola) {
            System.out.println(cliente);
        }
       
       // System.out.println(String.join(", ", cola));
    }

}
