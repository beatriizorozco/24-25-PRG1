import java.util.Scanner;

public class Halloween {
    public static void main(String args[]) {
      Scanner scanner = new Scanner(System.in);
      String inputUsuario;

      final int MAX_CARAMELOS = 20;
      final int MAX_PISOS = 4;
      final int MAX_CASAS = 4;
      final double P_ABIERTA = 0.7;
      final double P_CARAMELOS = 0.8;
      final int MAX_CARAMELOS_POR_CASA= 3;
      final int MIN_CARAMELOS_POR_CASA = 1;
        
    int[] caramelos = {0, 0, 0};
    boolean[] bolsaLlena = {false, false, false};
    
      int pisoActual = 0; 
      int casasVisitadas = 0; 
      
      do { 
        pisoActual++;
        System.out.println("\n=== Piso " + pisoActual + " ==="); 
          
        for (int i = 1; i <= MAX_CASAS; i++) {
            
          casasVisitadas++;
          System.out.println("\nVisitando casa " + i + " del piso " + pisoActual);
         
          double casaAbierta = Math.random();
            
          if(casaAbierta <= P_ABIERTA) {
            System.out.println("¡La casa está abierta!");
            danCaramelos = Math.random();
            for (int nino = 0; nino < 3; nino++) {
                        caramelos[nino] = repartirCaramelos(
                                caramelos[nino],
                                bolsaLlena,
                                nino,
                                danCaramelos,
                                P_CARAMELOS,
                                MAX_CARAMELOS_POR_CASA,
                                MIN_CARAMELOS_POR_CASA,
                                MAX_CARAMELOS
                        );
            }
          }
          else { 
            System.out.println("Casa cerrada, seguimos adelante..."); 
          }
          inputUsuario = scanner.nextLine();
        }             
      } while ((pisoActual <= MAX_PISOS) && !(bolsaLlena[0] && bolsaLlena[1] && bolsaLlena[2]));

      if (bolsaLlena[0] && bolsaLlena[1] && bolsaLlena[2]) { 
        System.out.println("\n¡Todas las bolsas están llenas! Terminamos la búsqueda.");
      }

      System.out.println("\n=== Resultados Finales ===");
      System.out.println("Casas visitadas: " + casasVisitadas );
        for (int i = 0; i < 3; i++) {
                System.out.println("Niño " + (i + 1) + ": " + caramelos[i] + " caramelos" + (bolsaLlena[i] ? " (Bolsa llena)" : ""));
        }
    }

     static int repartirCaramelos(int caramelosActuales, boolean[] bolsaLlena, int indiceNino,
                                 double danCaramelos, double P_CARAMELOS,
                                 int MAX_CARAMELOS_POR_CASA, int MIN_CARAMELOS_POR_CASA,
                                 int MAX_CARAMELOS) {

        if (!bolsaLlena[indiceNino]) {
            if (danCaramelos <= P_CARAMELOS) {
                int numeroCaramelos = (int) (Math.random() * (MAX_CARAMELOS_POR_CASA - MIN_CARAMELOS_POR_CASA + 1)) + MIN_CARAMELOS_POR_CASA;

                caramelosActuales += numeroCaramelos;
                System.out.println("Niño " + (indiceNino + 1) + " recibió " + numeroCaramelos + " caramelos");

                if (caramelosActuales >= MAX_CARAMELOS) {
                    bolsaLlena[indiceNino] = true;
                    caramelosActuales = MAX_CARAMELOS;
                    System.out.println("¡La bolsa del niño " + (indiceNino + 1) + " está llena!");
                }
            }
        }

        return caramelosActuales;
    }

}
