import org.iesalandalus.programacion.utilidades.Entrada;

// Ejercicio 2
public class Escritor {

    public static void main(String[] args) {
        int numero = Entrada.entero();

        System.out.println("Los diez números siguientes son:");
        for (int siguiente = 1; siguiente <= 10; siguiente++) {
            System.out.println(numero + siguiente);
        }
    }
}