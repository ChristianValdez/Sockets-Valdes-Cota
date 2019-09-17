package clientearchivosbarra;

public class ClienteArchivosBarra {

    public static void main(String[] args) {
        Cliente CT=null;
        try{
            CT = new Cliente(args);
            CT.Iniciar();
        }catch(Exception e){
            System.out.println("Error en la definición de argumentos: "+e.toString());
        }
    }
    
}
