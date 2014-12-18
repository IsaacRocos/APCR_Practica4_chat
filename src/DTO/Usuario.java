/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DTO;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.Objects;

/**
 *Esta clase contiene los datos necesarios de un usuario, para que el sistema pueda identificarlo
 * @author ivan
 */
public class Usuario implements Serializable {
    
    private String nombre;
    private String host;
    
    private ArrayList<Mensaje> conversacion;
    
    
    /**
     * Constructor usuario que inicializa todas las variables
     * @param nombre nombre describe un identificador unico de un usuario en sesion
     * @param host  host describe la ip de la maquina del usuario
     */
    public Usuario (String nombre, String host){
        this.nombre = nombre;
        this.host = host;
        
        this.conversacion = new ArrayList<>();
    }
    
    @Override
    public String toString(){
        return nombre;
    }
    
    @Override
    public boolean equals(Object objeto){
        if(objeto instanceof Usuario){
            Usuario temporal = (Usuario)objeto;
            if(this.nombre.equals(temporal.nombre) && this.host.equals(temporal.host)){
                return true;
            }else{
                return false;
            }
            
        }else return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.nombre);
        hash = 47 * hash + Objects.hashCode(this.host);
        return hash;
    }
    
    //<editor-fold desc="Geters y Seters de la clase">
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public ArrayList<Mensaje> getConversacion() {
        return conversacion;
    }

    public void setConversacion(ArrayList<Mensaje> conversacion) {
        this.conversacion = conversacion;
    }
    //</editor-fold>
    
    
    
    
}
