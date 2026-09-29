import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ListarDatos {
    public static void main(String[] args) {
        Path datos = Path.of("datos");

        if (!Files.exists(datos)) {
            System.out.println("La carpeta datos no existe.");
            return;
        }

        try (DirectoryStream<Path> elementos = Files.newDirectoryStream(datos)) {
            for (Path elemento : elementos) {
                if (Files.isRegularFile(elemento)) {
                    System.out.println(elemento.getFileName());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al acceder a la carpeta:");
            System.out.println(e.getMessage());
        }
    }
}
