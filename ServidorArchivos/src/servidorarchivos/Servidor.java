package servidorarchivos;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author christianvaldez
 */

public class Servidor {

    private int puerto;
    private Socket Socket;
    private ServerSocket Servidor;

    public Servidor(String[] args) {
        puerto = Integer.valueOf(args[0]);
        Socket = null;
        Servidor = null;
    }

    public void Iniciar() {
        while (true) {
            AbrirSockets();
            ContestarPeticion(LeerPeticion());
            CerrarSockets();
        }
    }

    public void AbrirSockets() {
        try {
            Servidor = new ServerSocket(puerto);
        } catch (IOException ex) {
            System.out.println("Error al crear socket de servidor: " + ex.toString());
        }
        try {
            Socket = Servidor.accept();
        } catch (IOException ex) {
            System.out.println("Error al crear socket de conexión: " + ex.toString());
        }
    }

    public void CerrarSockets() {
        try {
            Socket.close();
            Servidor.close();
        } catch (IOException ex) {
            System.out.println("Error al cerrar sockets de conexión");
        }
    }

    public String LeerPeticion() {
        BufferedReader Lector = null;
        String Direccion = "";
        try {
            Lector = new BufferedReader(new InputStreamReader(Socket.getInputStream()));
        } catch (IOException ex) {
            System.out.println("Error al crear lector de datos");
        }
        try {
            Direccion = Lector.readLine();
            System.out.println("Se pidió el archivo: " + Direccion);
        } catch (IOException ex) {
            System.out.println("Error al leer datos: " + ex.toString());
        }
        return Direccion;
    }

    public void ContestarPeticion(String ruta) {
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(Socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Error al crear salida de datos: " + ex.toString());
        }
        File Archivo = new File(ruta);
        if (Archivo.exists()) {
            try {
                dos.writeBoolean(true);
            } catch (IOException ex) {
                System.out.println("Error al enviar respuesta: " + ex.toString());
            }
            EnviarArchivo(Archivo, dos);
        }
    }

    public void EnviarArchivo(File Archivo, DataOutputStream dos) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(Archivo));
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado: " + ex.toString());
        }
        try {
            bos = new BufferedOutputStream(Socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Error al crear buffer de salida: " + ex.toString());
        }
        try {
            dos.writeUTF(Archivo.getName());
        } catch (IOException ex) {
            System.out.println("No se encontró nombre de archivo: " + ex.toString());
        }
        byte[] byteArray = new byte[8192];
        int in;
        try {
            while ((in = bis.read(byteArray)) != -1) {
                bos.write(byteArray, 0, in);
            }
        } catch (IOException ex) {
            System.out.println("Error al leer bits: " + ex.toString());
        }
        try {
            bis.close();
            bos.close();
        } catch (IOException ex) {
            System.out.println("Error al cerrar buffers de datos: " + ex.toString());
        }
    }
}
