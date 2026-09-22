package ProgramacionObject.Ejercicio5;

import java.util.Random;

public class FakerCine {

    private Random random = new Random();

    private String[] inicios = { "El secreto de", "La leyenda de", "Regreso a", "Sombras en", "El misterio de",
            "Crónicas de" };
    private String[] temas = { "la Selva", "los Anillos", "Marte", "la Eternidad", "un Destino", "el Tiempo" };
    private String[] directores = { "Christopher Nolan", "Steven Spielberg", "Greta Gerwig", "Martin Scorsese",
            "Quentin Tarantino", "James Cameron" };
    private String[] sinopsisBase = {
            "Un viaje épico a través de lo desconocido.",
            "Una historia de traición y redención en tiempos difíciles.",
            "El descubrimiento que cambió el curso de la humanidad.",
            "Una carrera contra el tiempo para salvar el mundo."
    };

    public String generarTitulo() {
        return inicios[random.nextInt(inicios.length)] + " " + temas[random.nextInt(temas.length)];
    }

    public String generarDirector() {
        return directores[random.nextInt(directores.length)];
    }

    public String generarSinopsis() {
        return sinopsisBase[random.nextInt(sinopsisBase.length)];
    }

    public int generarDuracion() {
        
        return random.nextInt(101) + 80;
    }
}
