import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.iesalandalus.programacion.utilidades.Entrada;

// Ejercicio 2
public class Lector {

    public static void main(String[] args) {
        System.out.print("Introduce un número entero: ");
        int numero = Entrada.entero();

        String ejecutableJava = System.getProperty("java.home") + "\\bin\\java.exe";
        String classpath = System.getProperty("java.class.path");

        ProcessBuilder constructorEscritor = new ProcessBuilder(
                ejecutableJava, "-cp", classpath, "Escritor");
        constructorEscritor.redirectError(ProcessBuilder.Redirect.INHERIT);

        try {
            Process escritor = constructorEscritor.start();

            OutputStream entradaEscritor = escritor.getOutputStream();
            entradaEscritor.write((numero + "\n").getBytes());
            entradaEscritor.flush();
            entradaEscritor.close();

            BufferedReader salidaEscritor = new BufferedReader(
                    new InputStreamReader(escritor.getInputStream()));
            String linea;
            while ((linea = salidaEscritor.readLine()) != null) {
                System.out.println(linea);
            }
            salidaEscritor.close();

        } catch (IOException e) {
            System.err.println("No se pudo iniciar o comunicar con Escritor.");
        }
    }
}