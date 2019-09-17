package servidorarchivos;

/**
 *
 * @author christianvaldez
 */

public class ServidorArchivos {

    public static void main(String[] args) {
        Servidor SD=null;
        try{
            SD = new Servidor(args);
            SD.Iniciar(); 
        }catch(Exception e){
            System.out.println("Error en la definición de argumentos");
        }
    }
}
