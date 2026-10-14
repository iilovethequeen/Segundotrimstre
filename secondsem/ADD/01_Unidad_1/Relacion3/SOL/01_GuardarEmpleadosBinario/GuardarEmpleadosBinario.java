import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GuardarEmpleadosBinario {

    public static void main(String[] args) {

        Path archivo = Path.of("datos", "empleados.dat");

        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado(1, "Ana Torres", "Ventas"));
        empleados.add(new Empleado(2, "Luis Martín", "Sistemas"));
        empleados.add(new Empleado(3, "Marta Ruiz", "Administración"));

        try {
            Files.createDirectories(archivo.getParent());

            try (ObjectOutputStream salida = new ObjectOutputStream(
                    Files.newOutputStream(archivo))) {
                salida.writeObject(empleados);
            }

            System.out.println("Empleados guardados correctamente.");

        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }
}
