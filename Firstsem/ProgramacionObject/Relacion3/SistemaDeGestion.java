package ProgramacionObject.Relacion3;

import java.util.Scanner;

/*
Genera 50 amarres con Faker y añádelos al puerto.
Genera 100 embarcaciones con Faker y regístralas en el puerto.
(Opcional pero recomendable) Asigna automáticamente algunas embarcaciones a amarres para que el sistema arranque con amarres ocupados y libres. */
public class SistemaDeGestion {
    public static void main(String[] args) {

        final int GENERAR_AMARRES = 1;
        final int GENERAR_EMBARCACIONES = 2;
        final int ASIGNAR_AMARRES = 3;

        final int SALIR = 4;

        String menu = "\n--- Gen ---\n" +
                "1. Generar 50 amarres\n" +
                "2. Generar 100 embarcaciones\n" +
                "3. Asignar embarcaciones a amarres\n" +
                "4. Salir";

        Scanner sc = new Scanner(System.in);

     Puerto puerto = new Puerto("Puerto Principal", "Ciudad Costera", 150, 100.0);
        Faker faker = new Faker();


        int opcion = 0;
        while (opcion != SALIR) {
            opcion = mostrarMenu(sc, menu);

            if (opcion == GENERAR_AMARRES) {
                for (int i = 0; i < 50; i++) {
                    Amarre nuevoAmarre = new Amarre(
                            faker.numeroAmarre(),
                            faker.longitudMaximaAmarre(),
                            faker.ocupado(),
                            faker.precioDia(),
                            faker.tipoAmarre()
                    );
                    // Aquí deberías agregar el nuevo amarre al puerto
                    // puerto.agregarAmarre(nuevoAmarre);

                }
                System.out.println("¡50 amarres generados con éxito!");

            } else if (opcion == GENERAR_EMBARCACIONES) {
                for (int i = 0; i < 100; i++) {
                    Embarcacion nuevaEmbarcacion = new Embarcacion(
                            faker.nombreEmbarcacion(),
                            faker.tipoEmbarcacion(),
                            faker.longitudEmbarcacion(),
                            faker.pesoEmbarcacion()
                    );
                    // Aquí deberías registrar la nueva embarcación en el puerto
                    // puerto.registrarEmbarcacion(nuevaEmbarcacion);

                }
                System.out.println("¡100 embarcaciones generadas con éxito!");

            } else if (opcion == ASIGNAR_AMARRES) {
                // Aquí se asignarían embarcaciones a amarres
                System.out.println("Asignando embarcaciones a amarres...");

            } else if (opcion == SALIR) {
                System.out.println("Cerrando reproductor...");
            } else {
                System.out.println("Opción incorrecta.");
            }
        }

    }

    static int mostrarMenu(Scanner sc, String menu) {
        System.out.println(menu);
        System.out.print("Opción: ");
        return leerEntero(sc);
    }

    static int leerEntero(Scanner sc) {
        int numero = 0;
        boolean numeroValido = false;

        while (!numeroValido) {
            String texto = sc.nextLine().trim();
            try {
                numero = Integer.parseInt(texto);
                numeroValido = true;
            } catch (NumberFormatException e) {
                System.out.print("Debes introducir un número entero: ");
            }
        }

        return numero;
    }
}