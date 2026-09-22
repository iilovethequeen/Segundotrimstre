package ProgramacionObject.Ejercicio3;

import java.util.ArrayList;

public class Supermercado {
    // Luego, crea una clase Supermercado con un array de productos y métodos para:

    ArrayList<Prodoctos> productos;
    Supermercado() {
        this.productos = new ArrayList<>();

    }

    void agregarProducto(Prodoctos producto) {
        this.productos.add(producto);
    }

    // Vender un producto (reducir cantidadEnStock)
    void venderProducto(String id, int cantidad) {
        for (Prodoctos producto : productos) {
            if (producto.getId().equals(id)) {
                if (producto.getCantidadEnStock() >= cantidad) {
                    producto.setCantidadEnStock(producto.getCantidadEnStock() - cantidad);
                    System.out.println("Producto vendido: " + producto.getNombre() + ", Cantidad: " + cantidad);
                } else {
                    System.out.println("No hay suficiente stock para el producto: " + producto.getNombre());
                }
                return;
            }
        }
        System.out.println("Producto no encontrado con ID: " + id);
    }

    void mostrarInventario() {
        System.out.println("\n--- Inventario del Supermercado ---");
        for (Prodoctos producto : productos) {
            System.out.println(producto);
        }
     }
     


}
