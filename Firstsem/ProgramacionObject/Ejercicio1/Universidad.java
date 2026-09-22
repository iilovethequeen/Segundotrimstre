package ProgramacionObject.Ejercicio1;

import java.util.ArrayList;

public class Universidad {
    ArrayList<Estudiante> Almonado;

    Universidad() {
        this.Almonado = new ArrayList<>();
    }

    void agregarEstudiante(Estudiante estudiante) {
        this.Almonado.add(estudiante);
    }
    public String mostrarEstudiantes(){
        String lista = "";
        for (Estudiante estudiante : Almonado) {
            lista += estudiante.getNombre() + "\n";
            
        }
        return lista;   
    }

    public Estudiante buscarEstudiante(String numeroDeMatricula) {
        Estudiante estudianteEncontrado = null;
        for (Estudiante estudiante : Almonado) {
            if (estudiante.getNumeroDeMareicula().equals(numeroDeMatricula)) {
                return estudiante;
            }
        }
        return estudianteEncontrado;
    }

}
