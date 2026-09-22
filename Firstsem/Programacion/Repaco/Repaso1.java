/*
 * REPASO 1 - Agrupación Musical "Al cielo con Visual Studio"
 *
 * En este ejercicio vas a desarrollar un sistema completo para gestionar
 * el personal y los instrumentos de una agrupación musical.
 *
 *
 * REGLAS GENERALES OBLIGATORIAS
 * 1. Debes crear todas las clases e interfaces necesarias.
 * 2. Cada clase que hagas debe tener como mínimo 3 atributos y 4 métodos.
 * 3. No vale hacer métodos que solo muestren texto por pantalla.
 * 4. Los métodos deben modificar datos, devolver cálculos o actualizar el estado de los objetos.
 * 5. En el main debes probar todos los métodos importantes.
 * 6. Usa ArrayList en lugar de arrays.
 *
 * ============================================================
 * PARTE 1. CLASE ABSTRACTA
 * ============================================================
 *
 * 1) Crea una clase abstracta llamada PersonaAgrupacion.
 *
 * Debe tener exactamente estos 3 atributos:
 * - private String nombre;
 * - private int edad;
 * - private int antiguedad;
 *
 * Debe tener:
 * - constructor con todos los atributos
 * - getters y setters
 *
 * Debe incluir exactamente estos 4 métodos:
 *
 * a) public abstract double calcularAportacion();
 *    Este método devolverá un valor numérico que represente la aportación
 *    total de esa persona a la agrupación.
 *
 * b) public abstract boolean puedeParticipar();
 *    Este método devolverá true o false según las reglas de cada clase hija.
 *
 * c) public void cumplirAnio()
 *    Debe aumentar en 1 la edad.
 *
 * d) public void aumentarAntiguedad()
 *    Debe aumentar en 1 la antigüedad.
 *
 * ============================================================
 * PARTE 2. INTERFACES
 * ============================================================
 *
 * 2) Crea estas 2 interfaces:
 *
 * INTERFAZ 1: Asignable
 * Debe declarar exactamente estos 2 métodos:
 *
 * - boolean asignarInstrumento(String nombreInstrumento, double valorInstrumento);
 * - boolean quitarInstrumento(String nombreInstrumento, double valorInstrumento);
 *
 * REGLAS:
 * - asignarInstrumento devolverá true si la asignación se realiza correctamente.
 * - quitarInstrumento devolverá true si el instrumento existía y se ha quitado.
 *
 * INTERFAZ 2: GestionHumana
 * Debe declarar exactamente estos 2 métodos:
 *
 * - boolean asignarMusico(PersonaAgrupacion persona);
 * - boolean expulsarMusico(String nombre);
 *
 * REGLAS:
 * - asignarMusico devolverá true si la persona se añade correctamente.
 * - expulsarMusico devolverá true si encuentra a la persona por nombre y la elimina.
 *
 * ============================================================
 * PARTE 3. CLASE DIRECTOR
 * ============================================================
 *
 * 3) Crea una clase Director que herede de PersonaAgrupacion e implemente GestionHumana.
 *
 * Debe tener exactamente estos 4 atributos propios:
 * - private String especialidad;
 * - private int maximoMusicos;
 * - private ArrayList<PersonaAgrupacion> musicosAsignados;
 * - private double plusDireccion;
 *
 * Debe tener constructor, getters y setters.
 *
 * Debe implementar como mínimo estos 4 métodos:
 *
 * a) @Override
 *    public double calcularAportacion()
 *    Debe devolver:
 *    antiguedad * 10 + plusDireccion + (musicosAsignados.size() * 5)
 *
 * b) @Override
 *    public boolean puedeParticipar()
 *    Debe devolver true si la edad es mayor o igual que 18.
 *
 * c) @Override
 *    public boolean asignarMusico(PersonaAgrupacion persona)
 *    REGLAS:
 *    - Si persona es null, devuelve false.
 *    - Si ya existe una persona con el mismo nombre, devuelve false.
 *    - Si ya has alcanzado maximoMusicos, devuelve false.
 *    - Si no, la añades a musicosAsignados y devuelves true.
 *
 * d) @Override
 *    public boolean expulsarMusico(String nombre)
 *    REGLAS:
 *    - Busca en la lista por nombre exacto.
 *    - Si lo encuentra, lo elimina y devuelve true.
 *    - Si no lo encuentra, devuelve false.
 *
 * Además, añade estos 2 métodos:
 *
 * e) public int contarMusicosAsignados()
 *    Debe devolver el número actual de músicos asignados.
 *
 * f) public double calcularValorTotalAportacionMusicos()
 *    Debe devolver la suma de calcularAportacion() de todos los músicos asignados.
 *
 * ============================================================
 * PARTE 4. CLASE MUSICO
 * ============================================================
 *
 * 4) Crea una clase Musico que herede de PersonaAgrupacion.
 *
 * Debe tener exactamente estos 4 atributos propios:
 * - private String instrumentoPrincipal;
 * - private int nivel;
 * - private int asistenciaEnsayos;
 * - private double valorBase;
 *
 * Debe tener constructor, getters y setters.
 *
 * Debe implementar exactamente estos 4 métodos:
 *
 * a) @Override
 *    public double calcularAportacion()
 *    Debe devolver:
 *    valorBase + (nivel * 2) + (asistenciaEnsayos * 1.5)
 *
 * b) @Override
 *    public boolean puedeParticipar()
 *    Debe devolver true si asistenciaEnsayos es mayor o igual que 3.
 *
 * c) public void registrarEnsayo()
 *    Debe aumentar asistenciaEnsayos en 1.
 *
 * d) public void subirNivel()
 *    Debe aumentar nivel en 1.
 *
 * ============================================================
 * PARTE 5. CLASE TROMPETISTA
 * ============================================================
 *
 * 5) Crea una clase Trompetista que herede de Musico e implemente Asignable.
 *
 * Debe tener exactamente estos 4 atributos propios:
 * - private int numeroInstrumentos;
 * - private double valorInstrumentos;
 * - private int resistencia;
 * - private int numeroSolos;
 *
 * Debe tener constructor, getters y setters.
 *
 * Debe implementar exactamente estos 6 métodos:
 *
 * a) @Override
 *    public double calcularAportacion()
 *    Debe devolver:
 *    super.calcularAportacion() + resistencia + (numeroSolos * 3) + (valorInstrumentos * 0.05)
 *
 * b) @Override
 *    public boolean puedeParticipar()
 *    Debe devolver true si:
 *    - super.puedeParticipar() es true
 *    - y resistencia es mayor o igual que 5
 *
 * c) @Override
 *    public boolean asignarInstrumento(String nombreInstrumento, double valorInstrumento)
 *    REGLAS:
 *    - Si nombreInstrumento es null o vacío, devuelve false.
 *    - Si valorInstrumento es menor o igual que 0, devuelve false.
 *    - Si no, aumenta numeroInstrumentos en 1, suma valorInstrumento a valorInstrumentos y devuelve true.
 *
 * d) @Override
 *    public boolean quitarInstrumento(String nombreInstrumento, double valorInstrumento)
 *    REGLAS:
 *    - Si numeroInstrumentos es 0, devuelve false.
 *    - Si valorInstrumento es menor o igual que 0, devuelve false.
 *    - Si no, resta 1 a numeroInstrumentos.
 *    - Resta valorInstrumento a valorInstrumentos.
 *    - Si valorInstrumentos queda negativo, déjalo en 0.
 *    - Devuelve true.
 *
 * e) public void registrarSolo()
 *    Debe aumentar numeroSolos en 1.
 *
 * f) public void aumentarResistencia(int cantidad)
 *    REGLAS:
 *    - Si cantidad es positiva, se suma a resistencia.
 *    - Si cantidad es 0 o negativa, no se modifica resistencia.
 *
 * ============================================================
 * PARTE 6. CLASE PERCUSIONISTA
 * ============================================================
 *
 * 6) Crea una clase Percusionista que herede de Musico e implemente Asignable.
 *
 * Debe tener exactamente estos 4 atributos propios:
 * - private int numeroInstrumentos;
 * - private double valorInstrumentos;
 * - private int potenciaGolpe;
 * - private int cambiosRitmoCorrectos;
 *
 * Debe tener constructor, getters y setters.
 *
 * Debe implementar exactamente estos 6 métodos:
 *
 * a) @Override
 *    public double calcularAportacion()
 *    Debe devolver:
 *    super.calcularAportacion() + (potenciaGolpe * 2) + (cambiosRitmoCorrectos * 4) + (valorInstrumentos * 0.03)
 *
 * b) @Override
 *    public boolean puedeParticipar()
 *    Debe devolver true si:
 *    - super.puedeParticipar() es true
 *    - y potenciaGolpe es mayor o igual que 4
 *
 * c) @Override
 *    public boolean asignarInstrumento(String nombreInstrumento, double valorInstrumento)
 *    Debe seguir exactamente las mismas reglas que en Trompetista.
 *
 * d) @Override
 *    public boolean quitarInstrumento(String nombreInstrumento, double valorInstrumento)
 *    Debe seguir exactamente las mismas reglas que en Trompetista.
 *
 * e) public void registrarCambioRitmoCorrecto()
 *    Debe aumentar cambiosRitmoCorrectos en 1.
 *
 * f) public void aumentarPotencia(int cantidad)
 *    REGLAS:
 *    - Si cantidad es positiva, se suma a potenciaGolpe.
 *    - Si cantidad es 0 o negativa, no se modifica potenciaGolpe.
 *
 * ============================================================
 * PARTE 7. MAIN OBLIGATORIO
 * ============================================================
 *
 * 7) En el main de Repaso1 debes hacer exactamente estas pruebas:
 *
 * a) Crear:
 *    - 1 Director
 *    - 2 Trompetistas
 *    - 2 Percusionistas
 *
 * b) Llamar varias veces a métodos para modificar datos:
 *    - registrarEnsayo()
 *    - subirNivel()
 *    - asignarInstrumento(...)
 *    - quitarInstrumento(...)
 *    - registrarSolo()
 *    - registrarCambioRitmoCorrecto()
 *    - aumentarResistencia(...)
 *    - aumentarPotencia(...)
 *    - asignarMusico(...)
 *    - expulsarMusico(...)
 *
 * c) Crear un ArrayList<PersonaAgrupacion> con varios objetos de tipos distintos.
 *
 * d) Recorrer la colección y calcular:
 *    - la suma total de calcularAportacion() de todos
 *    - cuántos pueden participar
 *
 * e) Mostrar por pantalla únicamente estos resultados finales:
 *    - aportación del director
 *    - aportación de cada músico
 *    - número de músicos asignados al director
 *    - valor total de aportación de los músicos asignados
 *    - suma total de aportación de toda la colección
 *    - número total de personas que pueden participar
 *
 */

public class Repaso1 {
    public static void main(String[] args) {
        // Desarrolla aquí tu solución.
    }



}