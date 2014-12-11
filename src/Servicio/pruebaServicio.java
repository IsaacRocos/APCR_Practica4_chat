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

/**
 *
 * @author ivan
 */
public class pruebaServicio implements Chat {
    
    public static void main(String args[]){
        pruebaServicio pS = new pruebaServicio();
        Servicio s = new Servicio("230.1.1.1",8082,pS);
        try {
            s.unirAlGrupo();
            s.start();
            try {
                s.enviarMensaje(new Mensaje(1,"Hola mundo"));
            } catch (IOException ex) {
                System.err.println("Error al enviar un mensaje: "+ex);
            }
        } catch (IOException ex) {
            System.err.println("Error al unirse al grupo:"+ex);
        }
    }

    @Override
    public void procesarMensaje(Mensaje mensaje) {
        //Se implementan los casos para cada posible mensaje recibido, login, logout, etc.
        System.out.println("Mensaje Recibido: "+mensaje.getDatos()+" tipo: "+Utileria.traducirMensaje(mensaje.getTipoMensaje()));
    }
    
}
