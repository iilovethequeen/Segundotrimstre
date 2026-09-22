package Programacion.PT;

import java.util.ArrayList;

public class PT2 {
    public static void main(String[] args) {
        final String BOMBA = "💣";
         final String CERRADAS = "🟩";
         ArrayList<String> tablaInterna = new ArrayList<String>();

            int cantidadDeBombas = (int)(Math.random() * 5) + 1;
            // Inicializar tablero interno
            for (int i = 0; i < 10; i++) {
                tablaInterna.add("0");
            }
            // Colocar bombas
            int bombasColocadas = 0;
            while (bombasColocadas < cantidadDeBombas) {
                int pos = (int)(Math.random() * 10);
                if (tablaInterna.get(pos).equals("0")) {
                    tablaInterna.set(pos, "1");
                    bombasColocadas++;
                }
            }

            // Tablero visible
            ArrayList<String> visible = new ArrayList<String>();
            for( int i = 0 ; i < 10 ; i ++){
                visible.add(CERRADAS);
            }
            int turno = 1;
            int descubiertasSeguras = 0;
            int totalSeguras = 10 - cantidadDeBombas;
            boolean jugando = true;
            // Bucle principal
            while (jugando) {
                System.out.println("Tablero interno:" + tablaInterna);
                System.out.println("Tablero visible:" + visible);
                if (turno == 1) {
                    System.out.println("Turno de Mario (Jugador 1)");
                } else {
                    System.out.println("Turno de Luigi (Jugador 2)");
                }
                int eleccion = (int)(Math.random() * 10);
                // ❌ Índice fuera de rango → volver a pedir
                if (eleccion < 0 || eleccion > 9) {
                    System.out.println("Índice fuera de rango. Intenta otra vez.");
                    continue;
                }
                // ❌ Ya descubierta → volver a pedir
                if (!visible.get(eleccion).equals(CERRADAS)) {
                    System.out.println("Esa tubería ya fue descubierta. Intenta otra vez.");
                    continue;
                }
                // 💣 Bomba
                if (tablaInterna.get(eleccion).equals("1")) {
                    visible.set(eleccion, BOMBA);
                    System.out.println("💥 ¡Topo-bomba!");
                    if (turno == 1) {
                        System.out.println("Mario pierde.");
                    } else {
                        System.out.println("Luigi pierde.");
                    }
                    jugando = false;
                   
                }else{
                    System.out.println("Elección segura.");
                    jugando = true;
                }
                // ⚪ Seguro
                visible.set(eleccion, "⚪");
                descubiertasSeguras++;
                if (descubiertasSeguras == totalSeguras) {
                    System.out.println("🎉 Todas las tuberías seguras fueron descubiertas.");
                    System.out.println("Mario y Luigi ganan juntos.");
                    jugando = false;
                   
                }else{
                    System.out.println("Elección segura. Siguiente turno.");
                    jugando = true;
                }
                // Cambiar turno
                turno = turno == 1 ? 2 : 1;
            }
            
       
        
    }
    
}


/*<script>
    const BOMBA = "💣";
    const CERRADAS = "🟩";

    let tablaInterna = [];

    let cantidadDeBombas = Math.floor(Math.random() * 5) + 1;

    // Inicializar tablero interno
    for (let i = 0; i < 10; i++) {
        tablaInterna.push(0);
    }

    // Colocar bombas
    let bombasColocadas = 0;
    while (bombasColocadas < cantidadDeBombas) {
        let pos = Math.floor(Math.random() * 10);
        if (tablaInterna[pos] === 0) {
            tablaInterna[pos] = 1;
            bombasColocadas++;
        }
    }

    // Tablero visible
    let visible = [];
    for (let i = 0; i < 10; i++) {
        visible.push(CERRADAS);
    }

    let turno = 1;
    let descubiertasSeguras = 0;
    let totalSeguras = 10 - cantidadDeBombas;
    let jugando = true;

    // Bucle principal
    while (jugando) {

        console.log("Tablero interno:", tablaInterna.join(" "));
        console.log("Tablero visible:", visible.join(" "));
        

        console.log(
            turno === 1
                ? "Turno de Mario (Jugador 1)"
                : "Turno de Luigi (Jugador 2)"
        );

        let eleccion = parseInt(prompt("Elige un índice entre 0 y 9:"));

        // ❌ Índice fuera de rango → volver a pedir
        if (isNaN(eleccion) || eleccion < 0 || eleccion > 9) {
            console.log("Índice fuera de rango. Intenta otra vez.");
            continue;
        }

        // ❌ Ya descubierta → volver a pedir
        if (visible[eleccion] !== CERRADAS) {
            console.log("Esa tubería ya fue descubierta. Intenta otra vez.");
            continue;
        }

        // 💣 Bomba
        if (tablaInterna[eleccion] === 1) {
            visible[eleccion] = BOMBA;
            console.log("💥 ¡Topo-bomba!");

            if (turno === 1) {
                console.log("Mario pierde.");
            } else {
                console.log("Luigi pierde.");
            }

            jugando = false;
            break;
        }

        // ⚪ Seguro
        visible[eleccion] = "⚪";
        descubiertasSeguras++;

        if (descubiertasSeguras === totalSeguras) {
            console.log("🎉 Todas las tuberías seguras fueron descubiertas.");
            console.log("Mario y Luigi ganan juntos.");
            jugando = false;
            break;
        }

        // Cambiar turno
        turno = turno === 1 ? 2 : 1;
    }
</script>
/* */

