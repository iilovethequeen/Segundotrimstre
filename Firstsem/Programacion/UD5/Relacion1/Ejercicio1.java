package UD5.Relacion1;

public class Ejercicio1 {

    // Ejercicio 1: Clase Círculo

    // /Crea una clase Círculo con el atributo radio. Agrega un método
    // calcularArea() que retorne el área del círculo (π * radio²) y otro método
    // calcularPerimetro() que retorne su perímetro (2 * π * radio).
    public static void main(String[] args) {
        Circulo circulo1 = new Circulo(5);
        Circulo circulo2 = new Circulo(1);

        System.out.println("Círculo de radio " + circulo1.radio + " Area: " + circulo1.calcularArea() + " Perímetro: "
                + circulo1.calcularPerimetro());
        System.out.println("Círculo de radio " + circulo2.radio + " Area: " + circulo2.calcularArea() + " Perímetro: "
                + circulo2.calcularPerimetro());
    }
}

class Circulo {

    final double PI = 3.1415;
    double radio;

    Circulo(double radio) {
        this.radio = radio;
    }

    double calcularArea() {
        return PI * this.radio * this.radio;
    }

    double calcularPerimetro() {
        return 2 * PI * this.radio;
    }

}