package UD5.Relacion1;

public class Ejercicio2 {
    // Crea una clase CuentaBancaria con atributos titular y saldo. Agrega métodos

    // depositar(cantidad) y retirar(cantidad), asegurando que el saldo no sea
    // negativo después de un retiro.
    public static void main(String[] args) {
        CuentaBancaria cuenta1 = new CuentaBancaria("Juan Pérez", 1000);
        CuentaBancaria cuenta2 = new CuentaBancaria("María López", 500);

        System.out.println("Saldo inicial de " + cuenta1.titular + ": " + cuenta1.saldo);
        cuenta1.depositar(200);
        System.out.println("Saldo después de depositar 200: " + cuenta1.saldo);
        boolean retiroExitoso = cuenta1.retirar(1500);
        System.out.println("Intento de retirar 1500: " + retiroExitoso + ". Saldo actual: " + cuenta1.saldo);
        System.out.println("Saldo inicial de " + cuenta2.titular + ": " + cuenta2.saldo);
        cuenta2.depositar(300);
        System.out.println("Saldo después de depositar 300: " + cuenta2.saldo);
        retiroExitoso = cuenta2.retirar(600);
        System.out.println("Intento de retirar 600: " + retiroExitoso + ". Saldo actual: " + cuenta2.saldo);

        
    }
}

class CuentaBancaria {

    String titular;
    double saldo;

    CuentaBancaria(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    void depositar(double cantidad) {
        if (cantidad > 0) {
            this.saldo += cantidad;
        }
    }

   boolean retirar(double cantidad) {
       if(cantidad > 0 && cantidad <= saldo){
           saldo -= cantidad;
           return true;
       }
       return false;
    }
}