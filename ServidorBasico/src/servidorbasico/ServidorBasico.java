package servidorbasico;
import java.net.*;
import java.io.*;
/**
 *
 * @author christianvaldez
 */

public class ServidorBasico {

    public static void main(String[] args) {
        // Creamos el servidor y si no ingresaste nada mostrara error
        try {
            CrearServidor(args);
        } catch (Exception e) {
            System.out.println("Error: Ingrese el puerto");
        }
    }
        // creamos los argumentos
    public static void CrearServidor(String[] args) {
        int puerto = Integer.valueOf(args[0]);
        ServerSocket socketServidor = null;
        Socket socket = null;
        String Entrada = "";
        BufferedReader lector = null;
        
        // El try catch nos va mostrar si hubo un error en cada uno de los que creeamos
        try {
            socketServidor = new ServerSocket(puerto);
        } catch (IOException ex) {
            System.out.println("Error al crear socket de servidor");
        }
        while (true) {
            try {
                socket = socketServidor.accept();
            } catch (IOException ex) {
                System.out.println("Error al crear socket de conexión");
            }
            try {
                lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException ex) {
                System.out.println("Error al crear lector de datos");
            }
            
            try {
                while ((Entrada = lector.readLine()) != null) {
                    System.out.println("El cliente dijo: " + Entrada);
                }
            } catch (IOException ex) {
                System.out.println("Error al leer datos");
            }
        }
    }

}
