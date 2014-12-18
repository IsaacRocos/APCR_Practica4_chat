
package Utileria;

import DTO.Mensaje;
import DTO.Usuario;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *Esta clase nos ayuda a traducir los tipos de banderas y codigos que se utilizan en el sistema
 * @author ivan
 */
public class Utileria {
    
    public static String etiqueta = "<img WIDTH = 10 HEIGHT = 10 SRC = \"";
    public static String d = System.getProperty("line.separator");
    public static String pathProyecto = System.getProperty("user.dir");
    
    /**
     *Metodo para poder traducir un tipo de mensaje.
     * @param id id es el tipo de mensaje a traducir
     * @return Regresa la descripcion del tipo de mensaje recibido
     */
    public static String traducirMensaje(int id){
        if(id == 0)
            return "Mensaje de login";
        if(id == 1)
            return "Mensaje para todos";
        if(id == 2)
            return "Mensaje privado";
        if(id == 3)
            return "Mensaje de logout";
        if(id == 4)
            return "Mensaje de error al leer un mensaje";
        
        return "Error: Mensaje no reconocido";
    }
    
    /**
     *Metodo para dar formato a un mensaje.
     * @param id id es el tipo de mensaje a traducir
     * @return Regresa la descripcion del tipo de mensaje recibido
     */
    public static String formatearMensaje(Mensaje mensajeUsuario,Usuario usuario){
        
        int tipoMsj = mensajeUsuario.getTipoMensaje();
        String mensajeForm ="";
        int alineación;
        try {
            if(usuario.equals(mensajeUsuario.getRemitente())){
                alineación=0;
            }else{alineación=1;}
        } catch (Exception e) {
            alineación=1;
        }
        
        
        switch(tipoMsj){
            case 0:
                mensajeForm = "<p align=\"center\"><font size=\"3\" color=\"#12FF95\"><b>"+mensajeUsuario.getRemitente()+"</b> se ha conectado al chat</font></p>";
                break;
            case 1:
                if(alineación==0){
                    mensajeForm = "<p align=\"left\"><font color=\"#1B4B8B\"><b>"+mensajeUsuario.getRemitente()+"</b><br>"+mensajeUsuario.getDatos()+"</font></p>";
                }else{
                    mensajeForm = "<p align=\"right\"><font color=\"#000000\"><b>"+mensajeUsuario.getRemitente()+"</b><br>"+mensajeUsuario.getDatos()+"</font></p>";
                }
                break;
            case 2:
                if(alineación==0){
                    mensajeForm = "<p align=\"left\"><font color=\"#1B4B8B\"><b>"+mensajeUsuario.getRemitente()+"</b><br>"+mensajeUsuario.getDatos()+"</font></p>";
                }else{
                    mensajeForm = "<p align=\"right\"><font color=\"#4A1B8B\"><b>"+mensajeUsuario.getRemitente()+"</b><br>"+mensajeUsuario.getDatos()+"</font></p>";
                }
                break;
            case 3:
                mensajeForm = "<p align=\"center\"><font size=\"3\" color=\"#FFA410\"><b>"+mensajeUsuario.getRemitente()+"</b> se ha desconectado del chat</font></p>";
                break;
            case 4:
                mensajeForm = "<p align=\"center\"><font size=\"3\" color=\"#FF2155\"> Error al recibir un mensaje </font></p>";
                break;
        }
        
        
        
        return mensajeForm;
    
    }
    
    /**
     * Metodo que traduce un codigo de emotico a codigo en html.
     * @param emotico emotico describe una imagen emotico por medio de una secuencia de caracteres
     * @return Regresa una etiqueta imagen de HTML del emotico
     */
    public static String traducirEmotico(String emotico){
        if(emotico.equals(":)"))
            return etiqueta+pathProyecto+d+"img"+d+"sonriente.jpg\" />";
        
        //Se agregan todos los emoticones necesarios
        return "";
    }
    
    /**
     * Convierte una variable tipo Object a un flujo de bytes.
     * @param obj obj es el objeto que se tratara de convertir a bytes
     * @return Regrea un flujo de bytes con los datos del objeto
     * @throws IOException 
     */
    public static byte[] serailizarObjeto(Object obj) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        return baos.toByteArray();
    }
    
    /**
     * Convierte un flujo de Bytes a una varible tipo Object.
     * @param objBytes objBytes Es el flujo de bytes que se convertiran a objeto
     * @return Un variable tipo Object con los datos del arreglo de bytes
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static Object deseralizarObjeto(byte[] objBytes) throws IOException, ClassNotFoundException{
        ByteArrayInputStream bais = new ByteArrayInputStream(objBytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return ois.readObject();
    }
    
}