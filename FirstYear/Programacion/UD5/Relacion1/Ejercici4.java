package UD5.Relacion1;

public class Ejercici4 {
    // Crea una clase Auto con atributos marca, modelo y velocidadActual. Agrega
    // métodos acelerar(cantidad) y frenar(cantidad) que aumenten o reduzcan la
    // velocidad actual.
    public static void main(String[] args) {
        Auto auto1 = new Auto("Toyota", "Corolla");
        Auto auto2 = new Auto("Ford", "Mustang");

        auto1.acelerar(50);

        System.out.println("Auto 1 - Marca: " + auto1.marca + ", Modelo: " + auto1.modelo + ", Velocidad Actual: "
                + auto1.velocidadActual + " km/h");

        auto1.frenar(20);
        System.out.println("Auto 1 - Marca: " + auto1.marca + ", Modelo: " + auto1.modelo + ", Velocidad Actual: "
                + auto1.velocidadActual + " km/h");

        auto2.acelerar(100);
        System.out.println("Auto 2 - Marca: " + auto2.marca + ", Modelo: " + auto2.modelo + ", Velocidad Actual: "
                + auto2.velocidadActual + " km/h");

        auto2.frenar(150);
        System.out.println("Auto 2 - Marca: " + auto2.marca + ", Modelo: " + auto2.modelo + ", Velocidad Actual: "
                + auto2.velocidadActual + " km/h");
    }
}

///==============================================CLASSS=============================================================///

class Auto {
    String marca;
    String modelo;
    double velocidadActual;

    Auto(String marcas, String modelos) {
        this.marca = marcas ;
        this.modelo = modelos;
        this.velocidadActual = 0;
    }

    void acelerar(double cantidad) {
        if (cantidad > 0) {
            this.velocidadActual += cantidad;
        }
    }

    void frenar(double cantidad) {
        if (cantidad > 0) {
            this.velocidadActual -= cantidad;
            if (this.velocidadActual < 0) {
                this.velocidadActual = 0;
            }
        }
    }
}
