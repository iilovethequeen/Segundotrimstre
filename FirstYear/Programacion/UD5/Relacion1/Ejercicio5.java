package Programacion.UD5.Relacion1;

public class Ejercicio5 {

    // Crea una clase Termómetro con un atributo temperatura. Agrega el método
    // convertirAFahrenheit().
    public static void main(String[] args) {
        Termometro termómetro1 = new Termometro(0);
        Termometro termómetro2 = new Termometro(37);

        System.out.println("Temperatura en Celsius: " + termómetro1.temperatura + "°C, en Fahrenheit: "
                + termómetro1.convertirAFahrenheit() + "°F");
        System.out.println("Temperatura en Celsius: " + termómetro2.temperatura + "°C, en Fahrenheit: "
                + termómetro2.convertirAFahrenheit() + "°F");
    }
}

// ====================================CLASSES==========================================//

class Termometro {

    double temperatura;

    public Termometro(double temperatura) {
        this.temperatura = temperatura;
    }

   double convertirAFahrenheit() {
        return (this.temperatura * 9 / 5) + 32;
    }

}
