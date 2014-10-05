/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package DTO;

import java.io.Serializable;

/**
 * Esta clase es una abstacción de un mensaje real, creado para poder ser enviado por sockets
 * @author ivan 
 */
public class Mensaje implements Serializable {
    
    private int tipoMensaje;
    
    private String datos;
    
    private Usuario remitente;
    private Usuario destinatario;
    
    /**
     *Constructor de la clase mensaje que inicializa todas las variables de la clase
     * @param tipo tipo describe el modo en que se tratara al mensaje
     * @param datos datos describe el cuerpo del mensaje
     * @param remitente remitente describe el usuario que envia el mensaje
     * @param destinatario destinatario describe el ururaio al que va dirigido el mensaje
     */
    public Mensaje(int tipo, 
            String datos, 
            Usuario remitente, 
            Usuario destinatario){
        
        this.tipoMensaje = tipo;
        this.datos = datos;
        this.remitente = remitente;
        this.destinatario = destinatario;
        
    }

    //<editor-fold desc="Geters y Seters de la clase">
    public int getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(int tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public Usuario getRemitente() {
        return remitente;
    }

    public void setRemitente(Usuario remitente) {
        this.remitente = remitente;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }    
    //</editor-fold>
}
