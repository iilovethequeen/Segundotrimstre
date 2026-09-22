import java.util.ArrayList;

public class Repaso1 {
    public static void main(String[] args) {

        // a) Crear objetos
        Director director = new Director("Ana Torres", 45, 20, "Dirección orquestal", 5, 300.0);

        Trompetista t1 = new Trompetista("Luis Vega", 30, 8, "Trompeta", 7, 2, 100.0,
                                          1, 500.0, 4, 3);
        Trompetista t2 = new Trompetista("Marta Gil",  25, 3, "Trompeta", 5, 1, 80.0,
                                          1, 300.0, 6, 1);

        Percusionista p1 = new Percusionista("Carlos Ruiz", 35, 12, "Caja", 6, 4, 90.0,
                                              2, 800.0, 3, 5);
        Percusionista p2 = new Percusionista("Elena Mora", 28, 5, "Bombo", 4, 2, 70.0,
                                              1, 400.0, 5, 2);

        // b) Llamadas a métodos modificadores
        t1.registrarEnsayo();   // asistencia: 2 -> 3
        t1.registrarEnsayo();   // asistencia: 3 -> 4
        t1.subirNivel();        // nivel: 7 -> 8

        t2.registrarEnsayo();   // asistencia: 1 -> 2
        t2.registrarEnsayo();   // asistencia: 2 -> 3
        t2.aumentarResistencia(2); // resistencia: 6 -> 8

        p1.registrarEnsayo();   // asistencia: 4 -> 5
        p1.aumentarPotencia(2); // potencia: 3 -> 5
        p1.registrarCambioRitmoCorrecto(); // cambios: 5 -> 6

        p2.registrarEnsayo();   // asistencia: 2 -> 3
        p2.registrarEnsayo();   // asistencia: 3 -> 4
        p2.aumentarPotencia(1); // potencia: 5 -> 6
        p2.registrarCambioRitmoCorrecto(); // cambios: 2 -> 3

        t1.asignarInstrumento("Trompeta Bb", 600.0);   // true
        t1.asignarInstrumento("Fliscorno",   400.0);   // true
        t1.quitarInstrumento("Fliscorno",    400.0);   // true
        t1.asignarInstrumento("",            200.0);   // false (nombre vacío)

        p1.asignarInstrumento("Caja", 350.0);          // true
        p1.quitarInstrumento("Caja",  350.0);          // true
        p1.quitarInstrumento("Bombo",  -50.0);         // false (valor negativo)

        t1.registrarSolo();
        t1.registrarSolo();
        p1.registrarCambioRitmoCorrecto();

        // Asignar músicos al director
        director.asignarMusico(t1);    // true
        director.asignarMusico(p1);    // true
        director.asignarMusico(t2);    // true
        director.asignarMusico(null);  // false
        director.asignarMusico(t1);    // false (ya existe)
        director.expulsarMusico("Marta Gil"); // true  -> quedan t1 y p1
        director.asignarMusico(p2);    // true

        // c) ArrayList polimórfico
        ArrayList<PersonaAgrupacion> coleccion = new ArrayList<>();
        coleccion.add(director);
        coleccion.add(t1);
        coleccion.add(t2);
        coleccion.add(p1);
        coleccion.add(p2);

        // d) Calcular suma de aportaciones y cuántos pueden participar
        double sumaTotal = 0;
        int puedenParticipar = 0;
        for (PersonaAgrupacion persona : coleccion) {
            sumaTotal += persona.calcularAportacion();
            if (persona.puedeParticipar()) puedenParticipar++;
        }

        // e) Mostrar resultados finales
        System.out.println("=== RESULTADOS FINALES ===");
        System.out.printf("Aportación del director: %.2f%n", director.calcularAportacion());

        for (PersonaAgrupacion persona : coleccion) {
            if (persona instanceof Musico) {
                System.out.printf("Aportación de %s: %.2f%n",
                        persona.getNombre(), persona.calcularAportacion());
            }
        }

        System.out.println("Músicos asignados al director: " + director.contarMusicosAsignados());
        System.out.printf("Valor total aportación músicos asignados: %.2f%n",
                director.calcularValorTotalAportacionMusicos());
        System.out.printf("Suma total aportación colección: %.2f%n", sumaTotal);
        System.out.println("Personas que pueden participar: " + puedenParticipar);
    }
}