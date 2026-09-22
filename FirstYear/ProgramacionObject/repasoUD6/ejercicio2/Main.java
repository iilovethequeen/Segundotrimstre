import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // a) Crear objetos
        ResponsableMusical responsable = new ResponsableMusical(
                "Sofia Reyes", "Granada", 15, 200.0, "Semana Cofrade");

        Guitarrista g1 = new Guitarrista(
                "Javi Rock", "Sevilla", 8, 100.0,
                "Guitarra eléctrica", 7, 3, 2,
                1, 800.0, 2);
        Guitarrista g2 = new Guitarrista(
                "Laura Strings", "Málaga", 5, 80.0,
                "Guitarra acústica", 6, 2, 1,
                1, 500.0, 0);

        Pianista p1 = new Pianista(
                "Marco Keys", "Córdoba", 10, 120.0,
                "Piano de cola", 8, 4, 3,
                1, 12000.0, 3, 6);
        Pianista p2 = new Pianista(
                "Ana Notes", "Jaén", 3, 60.0,
                "Piano digital", 5, 1, 1,
                1, 2000.0, 1, 4);

        // b) Llamadas a métodos modificadores

        // registrarEnsayo / consumirActuacion
        g1.registrarEnsayo();
        g1.registrarEnsayo();
        g1.consumirActuacion();   // actuaciones: 2 -> 1

        g2.registrarEnsayo();
        g2.consumirActuacion();   // actuaciones: 1 -> 0  → no disponible
        g2.consumirActuacion();   // ya es 0, no hace nada

        p1.registrarEnsayo();
        p1.consumirActuacion();   // actuaciones: 3 -> 2

        p2.registrarEnsayo();
        p2.registrarEnsayo();
        p2.consumirActuacion();   // actuaciones: 1 -> 0  → no disponible

        // asignarInstrumento / retirarInstrumento
        g1.asignarInstrumento("Guitarra slide", 650.0);   // true
        g1.retirarInstrumento("Guitarra slide", 650.0);   // true
        g1.asignarInstrumento("", 200.0);                  // false: nombre vacío
        g1.retirarInstrumento("Guitarra eléctrica", -10); // false: valor negativo

        p1.asignarInstrumento("Teclado MIDI", 900.0);     // true
        p1.retirarInstrumento("Teclado MIDI", 900.0);     // true

        // sumarPartiturasRevisadas / sumarErroresDetectados
        g1.sumarPartiturasRevisadas();
        g1.sumarPartiturasRevisadas();
        g1.sumarErroresDetectados(1);
        g1.sumarErroresDetectados(-3); // no hace nada
        g1.corregirError();            // errores: 1 -> 0

        g2.sumarPartiturasRevisadas();
        g2.sumarErroresDetectados(2);

        responsable.sumarPartiturasRevisadas();
        responsable.sumarPartiturasRevisadas();
        responsable.sumarErroresDetectados(3);

        // prepararSolo / prepararAcompanamiento / aumentarPrecision
        g2.prepararSolo();       // solosPreparados: 0 -> 1 (ahora puede participar)
        g1.prepararSolo();

        p1.prepararAcompanamiento();
        p2.prepararAcompanamiento();
        p2.aumentarPrecision(2); // precision: 4 -> 6  (ahora disponible si actuaciones > 0)

        // sumarPuntosBase
        g1.sumarPuntosBase(15.0);
        p1.sumarPuntosBase(20.0);
        responsable.sumarPuntosBase(-5.0); // no hace nada

        // asignarMiembro / quitarMiembro
        responsable.asignarMiembro(g1);    // true
        responsable.asignarMiembro(p1);    // true
        responsable.asignarMiembro(g2);    // true
        responsable.asignarMiembro(null);  // false
        responsable.asignarMiembro(g1);    // false: ya existe
        responsable.quitarMiembro("Laura Strings"); // true  → quedan g1 y p1
        responsable.asignarMiembro(p2);    // true  → g1, p1, p2

        // c) ArrayList polimórfico
        ArrayList<MiembroBanda> coleccion = new ArrayList<>();
        coleccion.add(responsable);
        coleccion.add(g1);
        coleccion.add(g2);
        coleccion.add(p1);
        coleccion.add(p2);

        // e) Referencias obligatorias
        MiembroBanda referencia1 = g1;           // referencia de tipo padre
        Tocable referencia2 = p1;                // referencia de tipo interfaz
        referencia1.sumarAnos();                 // llama al método heredado
        referencia2.asignarInstrumento("Steinway", 15000.0); // llama al método de interfaz

        // d) Calcular rendimiento total y disponibles
        double rendimientoTotal = 0;
        int disponibles = 0;
        for (MiembroBanda m : coleccion) {
            rendimientoTotal += m.calcularRendimiento();
            if (m.estaDisponible()) disponibles++;
        }

        // f) Mostrar resultados
        System.out.println("=== RESULTADOS FINALES ===");
        System.out.printf("Rendimiento del responsable: %.2f%n",
                responsable.calcularRendimiento());

        for (MiembroBanda m : coleccion) {
            if (m instanceof Guitarrista) {
                System.out.printf("Rendimiento guitarrista %s: %.2f%n",
                        m.getNombreArtistico(), m.calcularRendimiento());
            }
        }
        for (MiembroBanda m : coleccion) {
            if (m instanceof Pianista) {
                System.out.printf("Rendimiento pianista %s: %.2f%n",
                        m.getNombreArtistico(), m.calcularRendimiento());
            }
        }

        System.out.println("Integrantes a cargo del responsable: "
                + responsable.getNumeroIntegrantesACargo());
        System.out.printf("Rendimiento del equipo del responsable: %.2f%n",
                responsable.calcularRendimientoEquipo());
        System.out.printf("Rendimiento total de la colección: %.2f%n", rendimientoTotal);
        System.out.println("Miembros disponibles: " + disponibles);
    }
}