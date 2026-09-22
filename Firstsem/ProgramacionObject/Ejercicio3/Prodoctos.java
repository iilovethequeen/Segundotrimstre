package ProgramacionObject.Ejercicio3;

public class Prodoctos{
    // Crea una clase Producto con atributos id, nombre, descripción, precio y
    private String id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidadEnStock;
    
    /**
    
     * @param id Identificador único del producto
     * @param nombre Nombre del producto
     * @param descripcion Descripción del producto
     * @param precio Precio del producto
     * @param cantidadEnStock Cantidad disponible en stock
     * 
     */

    Prodoctos(String id, String nombre, String descripcion, double precio, int cantidadEnStock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidadEnStock = cantidadEnStock;
    }


   @Override
   public String toString() {
    return "=======================Productos===================\n" +
            "id ='" + id + '\'' +
            ", nombre ='" + nombre + '\'' +
            ", descripcion ='" + descripcion + '\'' +
            ", precio =" + precio +
            ", cantidadEnStock " + cantidadEnStock +
            "\n==================================================";
   }
    
   public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
     public String getNombre() {
        return nombre;  
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public int getCantidadEnStock() {
        return cantidadEnStock;
    }
    public void setCantidadEnStock(int cantidadEnStock) {
        this.cantidadEnStock = cantidadEnStock;
    }
}



