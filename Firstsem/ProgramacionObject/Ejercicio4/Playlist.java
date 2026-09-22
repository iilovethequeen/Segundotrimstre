package ProgramacionObject.Ejercicio4;
import java.util.ArrayList;

public class Playlist {
  
    private ArrayList<Conciones> listaConciones;

    public Playlist() {
        this.listaConciones = new ArrayList<>();
    }

    public void agregarConciones(Conciones concione) {
        this.listaConciones.add(concione);
    }

    public void mostrarConciones() {
        if (listaConciones.isEmpty()) {
            System.out.println("La playlist está vacía.");
        } else {
            System.out.println("\n--- MI PLAYLIST ---");
            for (Conciones canciones : listaConciones) {
                System.out.println(canciones);
            }
        }
    }

    public void duracionTotal() {
        double total = 0;
        for (Conciones canciones : listaConciones) {
            total += canciones.getDuracion();
        }
    
        int minutos = (int) total / 60;
        int segundos = (int) total % 60;
        System.out.println("\n> Duración total: " + total + " segundos (" + minutos + " min " + segundos + " seg)");
    }
}