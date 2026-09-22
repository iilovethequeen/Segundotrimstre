package Programacion.UD5.Relacion1;

public class Ejercicio6 {
    // Crea una clase Reloj con atributos hora, minuto y segundo. Agrega un método
    // toString() que devuelva la hora en formato hh:mm:ss. Agrega el método
    // sumarMinutos(minutos) , restarMinutos(minutos) , sumarSegundos(segundos) y
    // restarSegundos(segundos)
    public static void main(String[] args) {
     Relojo reloj1 = new Relojo(14, 30, 45);
     System.out.println("hora incial" + reloj1);
     reloj1.sumarMinutos(90);
     System.out.println("Despues sumar minutos" + reloj1);
     reloj1.restarMinutos(45);
     System.out.println("Despuse restar Minutos" + reloj1);
     
    }
}

// =================================CLASSES=================================//

class Relojo {

    int hora; 
    int minuto;
    int segundo;

    public Relojo(int hora, int minuto, int segundo){
        this.hora = hora;
        this.minuto = minuto; 
        this.segundo = segundo;

    }

    public String toString() {
      return String.format("%02d:%02d:%02d", hora, minuto, segundo);
    }

    void sumarMinutos(int minutos){
     int totalMinutos = this.minuto + minutos;
     this.hora = (this.hora + totalMinutos / 60) % 24;
     this.minuto = totalMinutos % 60;
    }
    
    void restarMinutos(int minutos){
        int totalMintutos = this.minuto - minutos;
        while (totalMintutos < 0) {
            totalMintutos += 60;
            this.hora = (this.hora - 1 + 24) % 24;
        }
        this.minuto = totalMintutos;
    }

    void sumarSegundos(int segundos){
        int totalSegundo = this.segundo + segundos;
        this.minuto = (this.minuto + totalSegundo / 60) % 60;
        this.segundo = totalSegundo % 60;
        this.hora = (this.hora + (this.minuto + totalSegundo / 60) / 60) % 24;

    }
}
