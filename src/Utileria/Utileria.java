package Utileria;

import DTO.Mensaje;
import DTO.Usuario;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.PatternSyntaxException;

/**
 * Esta clase nos ayuda a traducir los tipos de banderas y codigos que se
 * utilizan en el sistema
 *
 * @author ivan
 */
public class Utileria {

    public static String etiqueta = "<img width=30 height=30 src = \"file:\\";
    public static String d = System.getProperty("file.separator");
    public static String pathProyecto = System.getProperty("user.dir");

    /**
     * Metodo para poder traducir un tipo de mensaje.
     *
     * @param id id es el tipo de mensaje a traducir
     * @return Regresa la descripcion del tipo de mensaje recibido
     */
    public static String traducirMensaje(int id) {
        if (id == 0) {
            return "Mensaje de login";
        }
        if (id == 1) {
            return "Mensaje para todos";
        }
        if (id == 2) {
            return "Mensaje privado";
        }
        if (id == 3) {
            return "Mensaje de logout";
        }
        if (id == 4) {
            return "Mensaje de error al leer un mensaje";
        }

        return "Error: Mensaje no reconocido";
    }

    /**
     * Metodo para dar formato a un mensaje.
     *
     * @param mensajeUsuario es el mensaje que se va a formatear
     * @param usuario es el usuario que recibe el mensaje
     * @return Regresa el mensaje formateado
     */
    public static String formatearMensaje(Mensaje mensajeUsuario, Usuario usuario) {
        int tipoMsj = mensajeUsuario.getTipoMensaje();
        String datosMensaje = mensajeUsuario.getDatos();
        String mensajeFormateado;
        int alineación;
        try {
            if (usuario.equals(mensajeUsuario.getRemitente())) {
                alineación = 0;
            } else {
                alineación = 1;
            }
        } catch (Exception e) {
            alineación = 1;
        }

        datosMensaje = traducirEmotico(datosMensaje);

        switch (tipoMsj) {
            case 0:
                mensajeFormateado = "<p align=\"center\"><font size=\"3\" color=\"#12FF95\"><b>" + mensajeUsuario.getRemitente() + "</b> se ha conectado al chat</font></p>";
                break;
            case 1:
                if (alineación == 0) {
                    mensajeFormateado = "<p align=\"left\"><font color=\"#1B4B8B\"><b>" + mensajeUsuario.getRemitente() + "</b><br>" + datosMensaje + "</font></p>";
                } else {
                    mensajeFormateado = "<p align=\"right\"><font color=\"#000000\"><b>" + mensajeUsuario.getRemitente() + "</b><br>" + datosMensaje + "</font></p>";
                }
                break;
            case 2:
                if (alineación == 0) {
                    mensajeFormateado = "<p align=\"left\"><font color=\"#1B4B8B\"><b>" + mensajeUsuario.getRemitente() + "</b><br>" + datosMensaje + "</font></p>";
                } else {
                    mensajeFormateado = "<p align=\"right\"><font color=\"#4A1B8B\"><b>" + mensajeUsuario.getRemitente() + "</b><br>" + datosMensaje + "</font></p>";
                }
                break;
            case 3:
                mensajeFormateado = "<p align=\"center\"><font size=\"3\" color=\"#FFA410\"><b>" + mensajeUsuario.getRemitente() + "</b> se ha desconectado del chat</font></p>";
                break;
            case 4:
                mensajeFormateado = "<p align=\"center\"><font size=\"3\" color=\"#FF2155\"> Error al recibir un mensaje </font></p>";
                break;
            default:
                mensajeFormateado = "";
                break;
        }

        return mensajeFormateado;

    }

    /**
     * Metodo que busca un codigo de emotico y llama a traducirTodos() encaso de que encuentre una incidencia.
     *
     * @param datosMensaje mesaje que puede contener secuencias de caracteres
     * que representan emoticonos.
     * @return Regresa una etiqueta imagen de HTML del emotico
     */
   public static String traducirEmotico(String datosMensaje) {
        if(datosMensaje.contains(":)")){
            datosMensaje = traducirTodos(datosMensaje,":)","8).png");
        }
        if(datosMensaje.contains("(._.)")){
            datosMensaje = traducirTodos(datosMensaje,"(._.)","perp.png");
        }
        if(datosMensaje.contains(":D")){
            datosMensaje = traducirTodos(datosMensaje,":D","8D.png");
        }
        if(datosMensaje.contains(":O")){
            datosMensaje = traducirTodos(datosMensaje,":O","8O.png");
        }
        if(datosMensaje.contains("<3")){
            datosMensaje = traducirTodos(datosMensaje,"<3","c3.png");
        }
        if(datosMensaje.contains("¬¬")){
            datosMensaje = traducirTodos(datosMensaje,"¬¬","hum.png");
        }
        if(datosMensaje.contains(">.<")){
            datosMensaje = traducirTodos(datosMensaje,">.<","hate.png");
        }
        if(datosMensaje.contains("(y)")){
            datosMensaje = traducirTodos(datosMensaje,"(y)","like.png");
        }
        if(datosMensaje.contains("omg")){
            datosMensaje = traducirTodos(datosMensaje,"omg","omyg.png");
        }
        if(datosMensaje.contains("lool")){
            datosMensaje = traducirTodos(datosMensaje,"lool","laugh.png");
        }
        if(datosMensaje.contains("lml")){
            datosMensaje = traducirTodos(datosMensaje,"lml","rock.png");
        }
        if(datosMensaje.contains("B|")){
            datosMensaje = traducirTodos(datosMensaje,"B|","style.png");
        }
        if(datosMensaje.contains("zzz")){
            datosMensaje = traducirTodos(datosMensaje,"zzz","suenio.png");
        }
        if(datosMensaje.contains("mmmm")){
            datosMensaje = traducirTodos(datosMensaje,"mmmm","tnk.png");
        }
        if(datosMensaje.contains("wtf")){
            datosMensaje = traducirTodos(datosMensaje,"wtf","whatf.png");
        }
        
        return datosMensaje;
    }

   /**
     * Metodo que traduce a codigo en html, todos los codigos de un emotico especifico en un mensaje.
     *
     * @param datosMensaje mesaje que puede contener secuencias de caracteres
     * que representan el emoticono.
     * @param secuencia es la secuencia de caracteres que representa al emoticono que se va a buscar en @param datosMensaje.
     * @param nombreImagen el el nombre de la imagen que corresponde al @param secuencia del emoticono.
     * @return Regresa una etiqueta imagen de HTML del emotico
     */
    private static String traducirTodos(String datosMensaje,String secuencia,String nombreImagen) {
        StringBuilder sb =  new StringBuilder(datosMensaje);
        while(datosMensaje.contains(secuencia)){
            //System.out.println("Treduciendo emoticono");
            int indiceInicio = sb.indexOf(secuencia);
            sb.replace(indiceInicio, (indiceInicio+secuencia.length()), etiqueta+pathProyecto+d+"src"+d+"Img"+d+nombreImagen+"\"/>");
            datosMensaje = sb.toString();
        }
        return datosMensaje;
    }

    /**
     * Convierte una variable tipo Object a un flujo de bytes.
     *
     * @param obj obj es el objeto que se tratara de convertir a bytes
     * @return Regrea un flujo de bytes con los datos del objeto
     * @throws IOException
     */
    public static byte[] serailizarObjeto(Object obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        return baos.toByteArray();
    }

    /**
     * Convierte un flujo de Bytes a una varible tipo Object.
     *
     * @param objBytes objBytes Es el flujo de bytes que se convertiran a objeto
     * @return Un variable tipo Object con los datos del arreglo de bytes
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object deseralizarObjeto(byte[] objBytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(objBytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return ois.readObject();
    }
}