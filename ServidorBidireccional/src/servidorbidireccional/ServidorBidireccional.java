package servidorbidireccional;
import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author christianvaldez
 */

public class ServidorBidireccional {

    public static void main(String[] args){
        ServerSocket socketServidor = null;
        Socket socket = null;
        BufferedReader lector = null;
        PrintWriter escritor = null;
        String entrada="";
        Scanner scan=new Scanner(System.in);
        String salida="";
        
        // los try catch nos va notificar los errores que pueden surgir al ingresar mal un dato.
        try {
            socketServidor = new ServerSocket(2500);
        } catch (IOException ex) {
            System.out.println("Error al crear el socket de servidor");
        }
        try {
            socket = socketServidor.accept();
        } catch (IOException ex) {
            System.out.println("Error al crear el socket de conexión");
        }

        try {
            lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            System.out.println("Error al crear lector de datos");
        }
        try {
            escritor = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException ex) {
            System.out.println("Error al crear escritor de datos");
        }
        do{
            try {
                entrada=lector.readLine();
            } catch (IOException ex) {
                System.out.println("Error al leer los datos");
            }
            System.out.println(entrada);
            if(entrada.equalsIgnoreCase("End")){
                System.out.println("Chao");
                try {
                    socket.close();
                } catch (IOException ex) {
                    System.out.println("Error al cerrar el socket del conexión");
                }
                try {
                    socketServidor.close();
                } catch (IOException ex) {
                    System.out.println("Error al cerrar el socket del servidor");
                }
                System.exit(0);
            }
            salida=scan.nextLine();
            escritor.println(salida);
        }while(!entrada.equalsIgnoreCase("End"));
    }
    
}
