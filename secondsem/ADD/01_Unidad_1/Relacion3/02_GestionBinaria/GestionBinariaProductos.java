import java.nio.file.Path;

public class GestionBinariaProductos {

    public static void main(String[] args) {

        Path archivo = Path.of("datos", "productos.dat");

        // TODO 1: comprobar que el archivo existe.
        if (!java.nio.file.Files.exists(archivo)) {
            System.err.println("El archivo " + archivo.toString() + " no existe.");
            return;
        }

        // TODO 2: recuperar el objeto y comprobar que es una lista de Producto.
        java.util.List<Producto> productos = null;
        try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(
                java.nio.file.Files.newInputStream(archivo))) {
            Object obj = ois.readObject();
            if (obj instanceof java.util.List) {
                @SuppressWarnings("unchecked")
                java.util.List<Producto> lista = (java.util.List<Producto>) obj;
                productos = lista;
            } else {
                System.err.println("El archivo " + archivo.toString() + " no contiene una lista de productos.");
                return;
            }
        } catch (java.io.IOException | ClassNotFoundException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        // TODO 3: mostrar los productos.
        for (Producto p : productos) {
            System.out.println(p);
        }

        // TODO 4: pedir un ID y un nuevo stock.
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Introduce el ID del producto a modificar: ");
        int id = scanner.nextInt();
        System.out.print("Introduce el nuevo stock: ");
        int nuevoStock = scanner.nextInt();
        scanner.close(); // Good practice to close the scanner

        // TODO 5: si existe, modificarlo y guardar de nuevo la lista completa.
        boolean encontrado = false;
        for (Producto p : productos) {
            if (p.getId() == id) {
                p.setStock(nuevoStock);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.err.println("El producto con ID " + id + " no existe.");
            return;
        }

        // TODO 6: volver a guardar para comprobar la persistencia.
        try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(
                java.nio.file.Files.newOutputStream(archivo))) {
            oos.writeObject(productos);
            System.out.println("¡Stock actualizado y guardado correctamente en " + archivo.toString() + "!");
        } catch (java.io.IOException e) {
            System.err.println("Error al guardar los cambios: " + e.getMessage());
        }
    }
}