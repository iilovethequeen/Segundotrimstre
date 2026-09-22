
package ProgramacionObject.Ejercicio3;

import java.util.Random;

public class Faker {
    private Random random = new Random();

    // Arrays to generate realistic supermarket data
    private String[] nombres = {"Leche", "Pan", "Arroz", "Manzana", "Pasta", "Cereal", "Queso", "Pollo", "Huevos", "Galletas"};
    private String[] marcas = {"Hacendado", "Pascual", "Gallo", "Nestlé", "Danone", "Kelloggs", "Craft"};
    private String[] adjetivos = {"Fresco", "Integral", "Premium", "Orgánico", "Sin Lactosa", "Artesanal"};

    // Generates a random 3-digit ID
    public String id() {
        return String.format("%03d", random.nextInt(999) + 1);
    }

    // Generates a random product name (e.g., "Leche Pascual Integral")
    public String productName() {
        return nombres[random.nextInt(nombres.length)] + " " + 
               marcas[random.nextInt(marcas.length)] + " " + 
               adjetivos[random.nextInt(adjetivos.length)];
    }

    // Generates a random price between 0.50 and 25.00
    public double price() {
        double p = 0.5 + (25.0 - 0.5) * random.nextDouble();
        return Math.round(p * 100.0) / 100.0; // Rounds to 2 decimals
    }

    // Generates a random stock quantity between 1 and 200
    public int stock() {
        return random.nextInt(200) + 1;
    }
}