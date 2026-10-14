import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

// Ejercicio 1
public class Tuberia {

    public static void main(String[] args) {
        try {
            ProcessBuilder comandoDirectorio = new ProcessBuilder("cmd.exe", "/c", "dir");
            ProcessBuilder comandoOrdenar = new ProcessBuilder("cmd.exe", "/c", "sort");

            comandoDirectorio.redirectError(ProcessBuilder.Redirect.INHERIT);
            comandoOrdenar.redirectError(ProcessBuilder.Redirect.INHERIT);

            Process procesoDirectorio = comandoDirectorio.start();
            Process procesoOrdenar = comandoOrdenar.start();

            BufferedReader salidaDirectorio = new BufferedReader(
                    new InputStreamReader(procesoDirectorio.getInputStream()));
            OutputStream entradaOrdenar = procesoOrdenar.getOutputStream();

            String linea;
            while ((linea = salidaDirectorio.readLine()) != null) {
                entradaOrdenar.write((linea + "\r\n").getBytes());
            }

            salidaDirectorio.close();
            entradaOrdenar.close();

            BufferedReader salidaOrdenada = new BufferedReader(
                    new InputStreamReader(procesoOrdenar.getInputStream()));
            String lineaOrdenada;
            while ((lineaOrdenada = salidaOrdenada.readLine()) != null) {
                System.out.println(lineaOrdenada);
            }
            salidaOrdenada.close();

            System.out.println("Finalizada la ejecución");
        } catch (IOException e) {
            System.err.println("No se pudo ejecutar la tubería.");
        }
    }
}