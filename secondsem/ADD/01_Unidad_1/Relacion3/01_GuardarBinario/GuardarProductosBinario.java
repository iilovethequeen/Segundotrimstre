import java.nio.file.Path;

public class GuardarProductosBinario {

    public static void main(String[] args) {

        Path archivo = Path.of("datos", "productos.dat");

        // TODO 1: crear una lista con al menos cuatro Producto.
        java.util.List<Producto> productos = new java.util.ArrayList<>();
        productos.add(new Producto(1, "Producto 1", 5.0, 100));
        productos.add(new Producto(2, "Producto 2", 10.0, 200));
        productos.add(new Producto(3, "Producto 3", 15.0, 300));
        productos.add(new Producto(4, "Producto 4", 20.0, 400));

        // TODO 2: crear la carpeta datos si hace falta.
        if (!java.nio.file.Files.exists(archivo.getParent())) {
            try {
                java.nio.file.Files.createDirectories(archivo.getParent());
            } catch (java.io.IOException e) {
                System.err.println("Error al crear la carpeta: " + e.getMessage());
                return;
            }
        }

        // TODO 3: guardar la lista completa con ObjectOutputStream.
        try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(
                java.nio.file.Files.newOutputStream(archivo))) {
            oos.writeObject(productos);
            System.out.println("Productos guardados correctamente en " + archivo.toString());
        } catch (java.io.IOException e) {
            System.err.println("Error al guardar los productos: " + e.getMessage());
        }

        // TODO 4: controlar IOException.

    }
}
