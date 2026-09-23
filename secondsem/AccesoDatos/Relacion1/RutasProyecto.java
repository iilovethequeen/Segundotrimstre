import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
//Crea una clase RutasProyecto que represente las rutas de la carpeta datos , el fichero clubes.txt y una
//subcarpeta llamada copias . No debes crear todavía ningún archivo ni carpeta.
//Construye la ruta de datos con Path.of(...) .
//Obtén las otras dos rutas utilizando resolve(...) .
//Muestra en consola las rutas.
public class RutasProyecto {
public static void main(String[] args) {
Path datos = Path.of("datos");
Path copias = datos.resolve("copias");
Path clubes = datos.resolve("clubes.txt");

try {
Files.createDirectories(datos);
Files.createDirectories(copias);

if (files.notExists(clubes)) {
Files.createFile(clubes);
}


    system.out.println("Ruta de la carpeta datos: " + datos);
    system.out.println("Ruta de la subcarpeta copias: " + copias);
    system.out.println("Ruta del fichero clubes.txt: " + clubes);



}



}