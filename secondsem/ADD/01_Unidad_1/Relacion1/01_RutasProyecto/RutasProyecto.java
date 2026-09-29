import java.nio.file.Path;

public class RutasProyecto {
    public static void main(String[] args) {
        Path datos = Path.of("datos");
        Path clubes = datos.resolve("clubes.txt");
        Path copias = datos.resolve("copias");

        System.out.println("RUTAS RELATIVAS");
        System.out.println("Datos: " + datos);
        System.out.println("Clubes: " + clubes);
        System.out.println("Copias: " + copias);

        System.out.println();

        System.out.println("RUTAS ABSOLUTAS");
        System.out.println("Datos: " + datos.toAbsolutePath());
        System.out.println("Clubes: " + clubes.toAbsolutePath());
        System.out.println("Copias: " + copias.toAbsolutePath());
    }
}
