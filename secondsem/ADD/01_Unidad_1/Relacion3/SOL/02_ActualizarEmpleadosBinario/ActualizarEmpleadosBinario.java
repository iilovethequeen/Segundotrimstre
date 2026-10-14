import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ActualizarEmpleadosBinario {

    public static void main(String[] args) {

        Path archivo = Path.of("datos", "empleados.dat");
        Scanner teclado = new Scanner(System.in);

        if (!Files.exists(archivo)) {
            System.out.println("No existe el archivo: " + archivo);
        } else {
            try (ObjectInputStream entrada = new ObjectInputStream(
                    Files.newInputStream(archivo))) {

                Object dato = entrada.readObject();
                List<Empleado> empleados = new ArrayList<>();
                boolean contenidoValido = dato instanceof List<?>;

                if (contenidoValido) {
                    List<?> lista = (List<?>) dato;

                    for (Object elemento : lista) {
                        if (elemento instanceof Empleado) {
                            empleados.add((Empleado) elemento);
                        } else {
                            contenidoValido = false;
                        }
                    }
                }

                if (!contenidoValido) {
                    System.out.println("El archivo no contiene una lista válida de empleados.");
                } else {
                    System.out.print("ID del empleado: ");
                    int idBuscado = Integer.parseInt(teclado.nextLine().trim());

                    System.out.print("Nuevo departamento: ");
                    String nuevoDepartamento = teclado.nextLine().trim();

                    boolean encontrado = false;

                    for (Empleado empleado : empleados) {
                        if (empleado.getId() == idBuscado) {
                            empleado.setDepartamento(nuevoDepartamento);
                            encontrado = true;
                        }
                    }

                    if (encontrado) {
                        try (ObjectOutputStream salida = new ObjectOutputStream(
                                Files.newOutputStream(archivo))) {
                            salida.writeObject(empleados);
                        }

                        for (Empleado empleado : empleados) {
                            System.out.println(empleado);
                        }
                    } else {
                        System.out.println("No existe un empleado con ese ID.");
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("El ID debe ser numérico.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("No se pudo recuperar el archivo: " + e.getMessage());
            }
        }

        teclado.close();
    }
}
