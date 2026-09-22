package ProgramacionObject.Ejercicio4;
import java.util.Random;

public class FakerConciones {
    private Random random = new Random();

    private String[] titulos = {"Bohemian Rhapsody", "Blinding Lights", "Shape of You", "Hotel California", "Billie Jean", "Rolling in the Deep", "Starboy", "Yellow", "Believer", "Humble"};
    private String[] artistas = {"Queen", "The Weeknd", "Ed Sheeran", "Eagles", "Michael Jackson", "Adele", "Coldplay", "Imagine Dragons", "Kendrick Lamar", "Dua Lipa"};
    private String[] remix = {"Remix", "Live Version", "Acoustic", "Radio Edit"};

    public String songTitle() {
        String base = titulos[random.nextInt(titulos.length)];
        if (random.nextInt(10) > 7) { 
            base += " (" + remix[random.nextInt(remix.length)] + ")";
        }
        return base;
    }

    public String artistName() {
        return artistas[random.nextInt(artistas.length)];
    }

    public Double duration() {
       
        return 120.0 + (180.0 * random.nextDouble());
    }
}