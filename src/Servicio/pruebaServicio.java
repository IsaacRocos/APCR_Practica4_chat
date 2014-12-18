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

    public static void main(String args[]) {
        pruebaServicio pS = new pruebaServicio();
        Servicio s = new Servicio("230.1.1.1", 8082, pS);
        try {
            s.unirAlGrupo();
            s.start();
            try {
                s.enviarMensaje(new Mensaje(1, "Hola mundo"));
            } catch (IOException ex) {
                System.err.println("Error al enviar un mensaje: " + ex);
            }
        } catch (IOException ex) {
            System.err.println("Error al unirse al grupo:" + ex);
        }
    }

    @Override
    public void procesarMensaje(Mensaje mensaje) {
        System.out.println("Procesando mensaje recibido: " + mensaje.getDatos() + " tipo: " + Utileria.traducirMensaje(mensaje.getTipoMensaje()));
    }
}
