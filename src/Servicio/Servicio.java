/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    private String HOST_LOCAL_ESCUCHA;
    private int PUERTO_ENVIO;
    private int PUERTO_ESCUCHA;
    
    private Chat chat;
    
    
    private final int TAM_MNSJ;
    
    /**
     * Constructor que inicializa la ip del grupo a unirse y el puerto de comunicación
     * @param grupo grupo indica la dirección ip del grupo al que se unirá el usuario el valor por default es 230.1.1.1
     * @param puertoEscucha puertoEscucha indica el puerto por el cual se reciviran los mensajes de cualquier usuario, el valor por default es 4001
     * @param puertoEnvio puertoEnvio indica el puerto por el cual se enviaran los mensajes, el valor por default es 4000
     * @param chatUsuario  chat representa la vista del cliente, la cual se utilizará para poder imprimir los mensajes recibidos
     */
    public Servicio(String grupo, int puertoEnvio, int puertoEscucha, Chat chatUsuario){
        this.HOST_GRUPO = grupo;
        this.PUERTO_ENVIO = puertoEnvio;
        this.HOST_LOCAL_ENVIO = null;
        this.servicioEnvio = null;
        this.servicioEscucha = null;
        this.grupo = null;
        this.TAM_MNSJ = 900;
        this.chat = chatUsuario;
    }
    
    /**
     * Constructor que inicializa la ip del grupo a unirse y el puerto de comunicación al valor por default
     * El valor por default de la ip del grupo es 230.1.1.1
     * El valor por default del puerto de envio de mensajes es 4000
     * El valor por default del puerto de escucha de mensajes es 4001
     * @param chatUsuario  chat representa la vista del cliente, la cual se utilizará para poder imprimir los mensajes recibidos
     */
    public Servicio(Chat chatUsuario){
        this.HOST_GRUPO = "230.1.1.1";
        this.PUERTO_ENVIO = 4000;
        this.PUERTO_ESCUCHA = 4001;
        this.HOST_LOCAL_ESCUCHA = null;
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
     */
    public void unirAlGrupo() throws IOException, UnknownHostException{
        this.setServicioEnvio(new MulticastSocket(this.getPUERTO_ENVIO()));
        this.setServicioEscucha(new MulticastSocket(this.getPUERTO_ESCUCHA()));
        this.setGrupo(this.validarDireccionDelGrupo());
        this.getServicioEnvio().joinGroup(this.getGrupo());
        this.getServicioEscucha().joinGroup(this.getGrupo());
        System.out.println("Se ha logrado unir al grupo con la ip : "+this.getHOST_GRUPO()+" con el puerto: "+this.getPUERTO_ENVIO()+", desde: "+this.getHOST_LOCAL_ENVIO());
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
        byte[] mensajeBytes = Utileria.serailizarObjeto(mensaje);
        this.enviarTamanioMensaje(mensajeBytes.length, this.getGrupo());
        DatagramPacket paquete = new DatagramPacket(mensajeBytes,mensajeBytes.length,this.getGrupo(),this.getPUERTO_ENVIO());
        this.getServicioEnvio().send(paquete);
    }
    
    /**
     * Función para enviar el tamaño de un mensaje hacia una ip cualquiera
     * @param tamanio tamanio representa el tamaño del mensaje a enviar
     * @param ip ip es la dirección a quien se va a enviar
     * @throws IOException 
     */
    private void enviarTamanioMensaje(Integer tamanio, InetAddress ip) throws IOException{
        byte[] tamanioBytes = Utileria.serailizarObjeto(tamanio);
        DatagramPacket paquete = new DatagramPacket(tamanioBytes,tamanioBytes.length,ip,this.getPUERTO_ENVIO());
        this.getServicioEnvio().send(paquete);
    }
    
    /**
     * Funcion que recibir un mensaje
     * @return Regresa el mensjae que se le fue enviado a este cliente
     * @throws IOException
     * @throws java.lang.ClassNotFoundException
     */
    public Mensaje recibirMensaje() throws IOException, ClassNotFoundException{
        int tamanio = this.recibirTamanioMensaje();
        byte[] mensajeBytes = new byte[tamanio];
        DatagramPacket paquete = new DatagramPacket(mensajeBytes,tamanio);
        this.getServicioEscucha().receive(paquete);
        return (Mensaje)Utileria.deseralizarObjeto(paquete.getData());
    }
    
    /**
     * Función para recibir el tamaño de un mensaje
     * @return Regresa el tamaño del mensaje que esta por recibirse
     * @throws IOException 
     * @throws java.lang.ClassNotFoundException 
     */
    private int recibirTamanioMensaje() throws IOException, ClassNotFoundException{
        byte[] tamanioBytes = new byte[this.TAM_MNSJ];
        DatagramPacket paquete = new DatagramPacket(tamanioBytes,tamanioBytes.length);
        this.getServicioEscucha().receive(paquete);
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
                mnsRecibido = new Mensaje(5,err);
            } catch (ClassNotFoundException cnfe) {
                err = "Error al recibir un mensaje, El objeto recibido no es de tipo Mensaje:\n"+cnfe.getMessage();
                mnsRecibido = new Mensaje(5,err);
            }
            this.getChat().procesarMesnaje(mnsRecibido);
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
     * @return the PUERTO_ENVIO
     */
    public int getPUERTO_ENVIO() {
        return PUERTO_ENVIO;
    }

    /**
     * @param PUERTO_ENVIO the PUERTO_ENVIO to set
     */
    public void setPUERTO_ENVIO(int PUERTO_ENVIO) {
        this.PUERTO_ENVIO = PUERTO_ENVIO;
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
     * @return the HOST_LOCAL_ESCUCHA
     */
    public String getHOST_LOCAL_ESCUCHA() {
        return HOST_LOCAL_ESCUCHA;
    }

    /**
     * @param HOST_LOCAL_ESCUCHA the HOST_LOCAL_ESCUCHA to set
     */
    public void setHOST_LOCAL_ESCUCHA(String HOST_LOCAL_ESCUCHA) {
        this.HOST_LOCAL_ESCUCHA = HOST_LOCAL_ESCUCHA;
    }

    /**
     * @return the PUERTO_ESCUCHA
     */
    public int getPUERTO_ESCUCHA() {
        return PUERTO_ESCUCHA;
    }

    /**
     * @param PUERTO_ESCUCHA the PUERTO_ESCUCHA to set
     */
    public void setPUERTO_ESCUCHA(int PUERTO_ESCUCHA) {
        this.PUERTO_ESCUCHA = PUERTO_ESCUCHA;
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
