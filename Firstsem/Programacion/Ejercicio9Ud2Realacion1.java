package Programacion;

import java.util.ArrayList;

public class Ejercicio9Ud2Realacion1 {
     public static void main(String[] args) {
        ArrayList<Integer> pila = new ArrayList<Integer>();
        pila.add(10);
        pila.add(20);
        pila.add(30);
        System.out.println(pila);
        Integer clienteAtendido = pila.remove(pila.size() - 1);
        System.out.println("Atendiendo a: " + clienteAtendido);
        System.out.println("Clientes restantes: " + pila);
        clienteAtendido = pila.remove(pila.size() - 1);
        System.out.println("He atendido a " + clienteAtendido);
        System.out.println("Clientes restantes: " + pila);
        clienteAtendido = pila.remove(pila.size() - 1);
        System.out.println("He atendido a " + clienteAtendido);
        System.out.println("Clientes restantes: " + pila);
       
        for (Integer cliente : pila) {
            System.out.println(cliente);
        }
       
       // System.out.println(String.join(", ", cola));
    }
}
