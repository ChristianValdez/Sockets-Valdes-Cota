package clientebasico;
import java.io.*;
import java.net.*;

/**
 *
 * @author christianvaldez
 */

public class ClienteBasico {

    public static void main(String[] args) {
        
        //Simplemente nos va notificar si hubo un fallo al ingresar los datos
        try {
            CrearCliente(args);
        } catch (Exception e) {
            System.out.println("Error: Datos de conexión incompletos");
            System.out.println("Ingrese el IP, puerto y mensaje a enviar");
        }
    }
    // Creamos cada uno de los argumentos en este caso son 3 ip, puerto y el mensaje
    public static void CrearCliente(String[] args) {
        String ip = args[0];
        int puerto = Integer.valueOf(args[1]);
        String mensaje = args[2];
        Socket socket = null;
        PrintWriter escritor = null;
        
        // Nos enviara un mensaje si ingresamos mal la ip y el socket
        try {
            socket = new Socket(ip, puerto);
        } catch (IOException ex) {
            System.out.println("Error al crear el socket de conexión");
            System.exit(1);
        }
        // mostrara si hubo algún error al enviar  el mensaje
        try {
            escritor = new PrintWriter(socket.getOutputStream(), true);
            escritor.println(mensaje);
        } catch (IOException ex) {
            System.out.println("Error al enviar datos");
            System.exit(2);
        }
        //Mostrara si hubo un error al cerrar el socket
        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println("Error al cerrar socket de conexión");
            System.exit(3);
        }
    }
}
