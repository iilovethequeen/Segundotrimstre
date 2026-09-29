import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PrepararCarpetas {
    public static void main(String[] args) {
        Path datos = Path.of("datos");
        Path copias = datos.resolve("copias");
        Path clubes = datos.resolve("clubes.txt");
        Path respaldo = copias.resolve("respaldo.txt");

        try {
            Files.createDirectories(copias);

            if (!Files.exists(clubes)) {
                Files.createFile(clubes);
            }

            if (!Files.exists(respaldo)) {
                Files.createFile(respaldo);
            }

            System.out.println("clubes.txt");
            System.out.println("Existe: " + Files.exists(clubes));
            System.out.println("Tamaño: " + Files.size(clubes) + " bytes");

            System.out.println();

            System.out.println("respaldo.txt");
            System.out.println("Existe: " + Files.exists(respaldo));
            System.out.println("Tamaño: " + Files.size(respaldo) + " bytes");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
