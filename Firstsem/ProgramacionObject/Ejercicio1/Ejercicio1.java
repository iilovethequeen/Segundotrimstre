package ProgramacionObject.Ejercicio1;

public class Ejercicio1 {
    public static void main(String[] args) {

        Estudiante Ahmed = new Estudiante("Ahmed", 20, "Stwm001");
       Ahmed.setNombre("Ahmed omar");
      // System.out.println(Ahmed.getNombre());
         Ahmed.setEdad(16);
        // System.out.println(Ahmed.getEdad());
            Ahmed.setEdad(18);
           // System.out.println(Ahmed.getEdad());
            Ahmed.setNumeroDeMareicula("Stwm002");
           // System.out.println(Ahmed.getNumeroDeMareicula());

            Universidad urg = new Universidad();
            urg.agregarEstudiante(Ahmed);
            System.out.println(urg);
            System.out.println(urg.mostrarEstudiantes());
            Estudiante estudianteEncontrado = urg.buscarEstudiante("Stwm002");
            if (estudianteEncontrado != null) {
                System.out.println("Estudiante encontrado: " + estudianteEncontrado);
            } else {
                System.out.println("Estudiante no encontrado.");
            }

         

    }

}
