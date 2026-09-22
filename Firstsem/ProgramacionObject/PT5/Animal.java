package ProgramacionObject.PT5;

/**
 * Represents an Animal entity with its basic attributes and properties.
 * 
 * This class encapsulates information about an animal including its name, 
 * identification code, quantity, and price. It provides getters and setters 
 * for all attributes and a formatted string representation.
 * 
 * @author [AHMED IKDEEWISH]
 * @version 1.0
 */
public class Animal {

    /**
     * The name of the animal.
     */
    private String nombre;

    /**
     * The unique code identifier for the animal.
     */
    private String codigo;

    /**
     * The quantity of this animal in stock.
     */
    private int cantidad;
    private double precio;

    /**
     * Constructs an Animal object with the specified attributes.
     * 
     * @param nombre the name of the animal
     * @param codigo the unique code identifier for the animal
     * @param cantidad the quantity of this animal in stock
     * @param precio the price of the animal in euros
     */
    public Animal(String nombre, String codigo, int cantidad, double precio) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    /**
     * Returns a formatted string representation of the Animal object.
     * 
     * @return a formatted string containing the code, name, quantity, and price
     */
    @Override
    public String toString() {
        return String.format("Código: %s | Nombre: %s | Cantidad: %d | Precio: %.2f " + "€",
                codigo, nombre, cantidad, precio);
    }

    /**
     * Gets the name of the animal.
     * 
     * @return the name of the animal
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the name of the animal.
     * 
     * @param nombre the name to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the code identifier of the animal.
     * 
     * @return the code identifier
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Sets the code identifier of the animal.
     * 
     * @param codigo the code identifier to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Gets the quantity of the animal in stock.
     * 
     * @return the quantity of the animal
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Sets the quantity of the animal in stock.
     * 
     * @param cantidad the quantity to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Gets the price of the animal in euros.
     * 
     * @return the price of the animal
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Sets the price of the animal in euros.
     * 
     * @param precio the price to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
}