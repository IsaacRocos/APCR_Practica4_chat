
package Servicio;

import DTO.Mensaje;
import Utileria.Chat;
import Utileria.Utileria;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 *Clase que contiene los metodos necesarios para manejar el comportamiento de un cliente de un chat
 * @author ivan
 */
public class Servicio extends Thread {
    
    private MulticastSocket servicioEnvio;
    private MulticastSocket servicioEscucha;
    private InetAddress grupo;
    
    private String HOST_GRUPO;
    private String HOST_LOCAL_ENVIO;
    private int PUERTO;
    
    private Chat chat;
    
    
    private final int TAM_MNSJ;
    
    /**
     * Constructor que inicializa la ip del grupo a unirse y el puerto de comunicación
     * @param grupo grupo indica la dirección ip del grupo al que se unirá el usuario el valor por default es 230.1.1.1
     * @param puerto puerto indica el puerto por el cual se reciviran y enviaran los mensajes de cualquier usuario, el valor por default es 4000
     * @param chatUsuario  chat representa la vista del cliente, la cual se utilizará para poder imprimir los mensajes recibidos
     */
    public Servicio(String grupo, int puerto, Chat chatUsuario){
        this.HOST_GRUPO = grupo;
        this.PUERTO = puerto;
        this.HOST_LOCAL_ENVIO = null;
        this.servicioEnvio = null;
        this.servicioEscucha = null;
        this.grupo = null;
        this.TAM_MNSJ = 9000;
        this.chat = chatUsuario;
    }
    
    /**
     * Constructor que inicializa la ip del grupo a unirse y el puerto de comunicación al valor por default
     * El valor por default de la ip del grupo es 230.1.1.1
     * El valor por default del puerto de envio y recibo de mensajes es 4000
     * @param chatUsuario  chat representa la vista del cliente, la cual se utilizará para poder imprimir los mensajes recibidos
     */
    public Servicio(Chat chatUsuario){
        this.HOST_GRUPO = "230.1.1.1";
        this.PUERTO = 4000;
        this.servicioEnvio = null;
        this.servicioEscucha = null;
        this.HOST_LOCAL_ENVIO = null;
        this.grupo = null;
        this.TAM_MNSJ = 10;
        this.chat = chatUsuario;
    }    
    
    /**
     * Funcion para poder levantar el servicio de chat con el puerto y direccion IP del grupo establecidos
     * @throws java.io.IOException Lanzara la excepcion IOException cuando no se pueda levantar el servicio en el puerto indicado
     * @throws java.net.UnknownHostException Lanzará ña excepción UnknownHostException cuando no pueda obtener la ip local, o cuando no pueda resolver la ip del grupo
     * @throws java.lang.IllegalArgumentException Cuando el puesrto especificado está fuera de rango.
     */
    public void unirAlGrupo() throws IOException, UnknownHostException,IllegalArgumentException{
        this.setServicioEnvio(new MulticastSocket(this.getPUERTO()));
        this.setServicioEscucha(new MulticastSocket(this.getPUERTO()));
        this.setGrupo(this.validarDireccionDelGrupo());
        this.getServicioEnvio().joinGroup(this.getGrupo());
        this.getServicioEscucha().joinGroup(this.getGrupo());
        System.out.println("Se ha logrado unir al grupo con la ip : "+this.getHOST_GRUPO()+" con el puerto: "+this.getPUERTO()+", desde: "+this.getHOST_LOCAL_ENVIO());
    }
    
    /**
     * Función para poder resolver la dirección ip del grupo que se establecio y obtener la ip local
     * @return Un objeto InetAddres que contiene la dirección ip del grupo
     * @throws UnknownHostException Lanza esta excepción cuando no pueda encontrar la ip del grupo
     */
    private InetAddress validarDireccionDelGrupo() throws UnknownHostException{
        this.setHOST_LOCAL_ENVIO(InetAddress.getLocalHost().getHostAddress());
        return InetAddress.getByName(this.getHOST_GRUPO());
    }
    
    /**
     * Funcion que envia un mensaje a todos los usuarios que esten unidos al grupo
     * @param mensaje
     * @throws IOException
     */
    public void enviarMensaje(Mensaje mensaje) throws IOException{
        System.out.println("<"+mensaje.getRemitente()+">" + " Enviando Nuevo Mensaje: "+mensaje.getDatos());
        byte[] mensajeBytes = Utileria.serailizarObjeto(mensaje);
        this.enviarTamanioMensaje(mensajeBytes.length, this.getGrupo());
        DatagramPacket paquete = new DatagramPacket(mensajeBytes,mensajeBytes.length,this.getGrupo(),this.getPUERTO());
        this.getServicioEnvio().send(paquete);
        System.out.println("Mensaje Enviado.");
    }
    
    /**
     * Función para enviar el tamaño de un mensaje hacia una ip cualquiera
     * @param tamanio tamanio representa el tamaño del mensaje a enviar
     * @param ip ip es la dirección a quien se va a enviar
     * @throws IOException 
     */
    private void enviarTamanioMensaje(Integer tamanio, InetAddress ip) throws IOException{
        System.out.println("Enviando Tamanio mensaje: "+tamanio);
        byte[] tamanioBytes = Utileria.serailizarObjeto(tamanio);
        DatagramPacket paquete = new DatagramPacket(tamanioBytes,tamanioBytes.length,ip,this.getPUERTO());
        this.getServicioEnvio().send(paquete);
        System.out.println("Tamanio mensaje enviado.");
    }
    
    /**
     * Funcion que recibir un mensaje
     * @return Regresa el mensjae que se le fue enviado a este cliente
     * @throws IOException
     * @throws java.lang.ClassNotFoundException
     */
    public Mensaje recibirMensaje() throws IOException, ClassNotFoundException{
        System.out.println("Esperando nuevo mensaje...");
        int tamanio = this.recibirTamanioMensaje();
        byte[] mensajeBytes = new byte[tamanio];
        DatagramPacket paquete = new DatagramPacket(mensajeBytes,tamanio);
        this.getServicioEscucha().receive(paquete);
        Mensaje msjRecibido = ((Mensaje)Utileria.deseralizarObjeto(paquete.getData()));
        System.out.println("Mensaje de <"+ msjRecibido.getRemitente() +"> recibido: "+ msjRecibido.getDatos());
        return msjRecibido;
    }
    
    /**
     * Función para recibir el tamaño de un mensaje
     * @return Regresa el tamaño del mensaje que esta por recibirse
     * @throws IOException 
     * @throws java.lang.ClassNotFoundException 
     */
    private int recibirTamanioMensaje() throws IOException, ClassNotFoundException{
        System.out.println("Esperando tamanio mensaje...");
        byte[] tamanioBytes = new byte[this.TAM_MNSJ];
        DatagramPacket paquete = new DatagramPacket(tamanioBytes,tamanioBytes.length);
        this.getServicioEscucha().receive(paquete);
        System.out.println("Tamanio de mensaje recibido: "+ (int)Utileria.deseralizarObjeto(paquete.getData()));
        return (int)Utileria.deseralizarObjeto(paquete.getData());
    }
    
    /**
     * Funcion que crea un subproceso para poder escuchar los mensajes de cualquier usuario
     */
    @Override
    public void run() {
        for(;;){
            Mensaje mnsRecibido;
            String err;
            try {
                mnsRecibido = this.recibirMensaje();
            } catch (IOException ioe) {
                err = "Error al recibir un mensaje, Error de comunicación:\n"+ioe.getMessage();
                System.err.println(err);
                mnsRecibido = new Mensaje(4,err);
            } catch (ClassNotFoundException cnfe) {
                err = "Error al recibir un mensaje, El objeto recibido no es de tipo Mensaje:\n"+cnfe.getMessage();
                System.err.println(err);
                mnsRecibido = new Mensaje(4,err);
            } catch(Exception ex){
                err = "Error al recibir un mensaje, El objeto recibido no es de tipo Mensaje:\n excepción de tipo: "+ ex.getClass().getSimpleName() +"\n" + ex.getMessage();
                System.err.println(err);
                mnsRecibido = new Mensaje(4,err);
            }
            this.getChat().procesarMensaje(mnsRecibido);
        }
    }

    //<editor-fold desc="Geters y Seters">
    /**
     * @return the servicioEnvio
     */
    public MulticastSocket getServicioEnvio() {
        return servicioEnvio;
    }

    /**
     * @param servicioEnvio the servicioEnvio to set
     */
    public void setServicioEnvio(MulticastSocket servicioEnvio) {
        this.servicioEnvio = servicioEnvio;
    }

    /**
     * @return the HOST_GRUPO
     */
    public String getHOST_GRUPO() {
        return HOST_GRUPO;
    }

    /**
     * @param HOST_GRUPO the HOST_GRUPO to set
     */
    public void setHOST_GRUPO(String HOST_GRUPO) {
        this.HOST_GRUPO = HOST_GRUPO;
    }

    /**
     * @return the PUERTO
     */
    public int getPUERTO() {
        return PUERTO;
    }

    /**
     * @param PUERTO the PUERTO to set
     */
    public void setPUERTO(int PUERTO) {
        this.PUERTO = PUERTO;
    }

    /**
     * @return the HOST_LOCAL_ENVIO
     */
    public String getHOST_LOCAL_ENVIO() {
        return HOST_LOCAL_ENVIO;
    }

    /**
     * @param HOST_LOCAL_ENVIO the HOST_LOCAL_ENVIO to set
     */
    public void setHOST_LOCAL_ENVIO(String HOST_LOCAL_ENVIO) {
        this.HOST_LOCAL_ENVIO = HOST_LOCAL_ENVIO;
    }

    /**
     * @return the grupo
     */
    public InetAddress getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(InetAddress grupo) {
        this.grupo = grupo;
    }
    
    /**
     * @return the servicioEscucha
     */
    public MulticastSocket getServicioEscucha() {
        return servicioEscucha;
    }

    /**
     * @param servicioEscucha the servicioEscucha to set
     */
    public void setServicioEscucha(MulticastSocket servicioEscucha) {
        this.servicioEscucha = servicioEscucha;
    }
    
     /**
     * @return the chat
     */
    public Chat getChat() {
        return chat;
    }

    /**
     * @param chat the chat to set
     */
    public void setChat(Chat chat) {
        this.chat = chat;
    }
    
    //</editor-fold>
}
