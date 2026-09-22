package ProgramacionObject.Relacion3;

import java.util.ArrayList;

import ProgramacionObject.Ejercicio4.Conciones;

public class Puerto {

    private String nombre;
    public String ubicacion;
    private int capacidadMaxima;
    private double tarifaBase;

    public Puerto(String nombre, String ubicacion, int capacidadMaxima, double tarifaBase) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.capacidadMaxima = capacidadMaxima;
        this.tarifaBase = tarifaBase;
    }

    // =====================================AryyeList================================================//
    private ArrayList<Amarre> amarres;

    private ArrayList<Embarcacion> embarcaciones;

    private ArrayList<String> matriculasAmarradas;
    private ArrayList<Integer> amarreDeEmbarcacion;

    // Getters y setters para los campos privados
    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public int getcapacidadMaxima() {
        return capacidadMaxima;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setcapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public void setTarifaBase(double tarifaBase) {
        this.tarifaBase = tarifaBase;
    }

    public String toString() {
        return "Puerto{" +
                "nombre='" + nombre + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", capacidadMaxima=" + capacidadMaxima +
                ", tarifaBase=" + tarifaBase +
                '}';
    }

}
