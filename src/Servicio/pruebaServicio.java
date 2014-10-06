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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ivan
 */
public class pruebaServicio implements Chat {
    
    public static void main(String args[]){
        pruebaServicio pS = new pruebaServicio();
        Servicio s = new Servicio(pS);
        try {
            s.unirAlGrupo();
        } catch (IOException ex) {
            System.err.println("Error al unirse al grupo");
        }
        s.start();
        
        System.out.println("Enviando Mensaje");
        try {
            s.enviarMensaje(new Mensaje(1,"Hola mundo"));
        } catch (IOException ex) {
            System.err.println("Error al enviar un mensaje");
        }
        System.out.println("Mensaje enviado");
    }

    @Override
    public void procesarMesnaje(Mensaje mensaje) {
        //Se implementan los casos para cada posible mensaje recibido, login, logout, etc.
        System.out.println("Mensaje Recibido: "+mensaje.getDatos()+" tipo: "+Utileria.traducirMensaje(mensaje.getTipoMensaje()));
    }
    
}
