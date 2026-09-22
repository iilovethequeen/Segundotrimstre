package ProgramacionObject.PT5;

import java.util.ArrayList;

public class Zoologico {

    private ArrayList<Animal> animales;

    public Zoologico() {
        animales = new ArrayList<>();
    }

    public void agregarAnimal(Animal animal) {
        animales.add(animal);
    }

    public ArrayList<Animal> obtenerTodos() {
        return animales;
    }

    /**
     * Searches for an animal in the collection by its code.
     *
     * @param codigo the code of the animal to search for (case-insensitive)
     * @return the Animal object if found, or null if no animal with the specified code exists
     */
    public Animal buscarAnimalPorCodigo(String codigo) {
        Animal encontrado = null;

        for (Animal animal : animales) {
            if (animal.getCodigo().equalsIgnoreCase(codigo)) {
                encontrado = animal;
            }
        }

        return encontrado;
    }

    public int agregarEjemplares(String codigo, int cantidad) {

        int resultado = 0; // 0 = no encontrado

        Animal animal = buscarAnimalPorCodigo(codigo);

        if (animal != null) {
            if (cantidad <= 0) {
                resultado = -1; // cantidad inválida
            } else {
                animal.setCantidad(animal.getCantidad() + cantidad);
                resultado = 1; // éxito
            }
        }

        return resultado;
    }

    public int retirarEjemplares(String codigo, int cantidad) {

        int resultado = 0; // 0 = no encontrado

        Animal animal = buscarAnimalPorCodigo(codigo);

        if (animal != null) {
            if (cantidad <= 0) {
                resultado = -2; // cantidad inválida
            } else if (cantidad > animal.getCantidad()) {
                resultado = -1; // más de lo disponible
            } else {
                animal.setCantidad(animal.getCantidad() - cantidad);
                resultado = 1; // éxito
            }
        }

        return resultado;
    }

    public int eliminarAnimal(String codigo) {

        int resultado = 0; // no encontrado

        Animal animal = buscarAnimalPorCodigo(codigo);

        if (animal != null) {
            if (animal.getCantidad() > 0) {
                resultado = -1; // tiene ejemplares
            } else {
                animales.remove(animal);
                resultado = 1; // eliminado
            }
        }

        return resultado;
    }
}