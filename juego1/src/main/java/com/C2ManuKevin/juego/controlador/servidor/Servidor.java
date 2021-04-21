package com.C2ManuKevin.juego.controlador.servidor;
import com.C2ManuKevin.juego.controlador.Juego;
import java.io.IOException;

import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author KevinRG & Manuela
 *
 */
public class Servidor extends Thread {
    public void levantarServidor() {
        ServerSocket ss;
        try {
            ss = new ServerSocket(1234);
            int idSession = 0;
            while (true) {
                Socket socket;
                socket = ss.accept();
                Juego.getInstance().clientesSockets.add(socket);
                ServidorHilo servidorHilo = new ServidorHilo(socket, idSession);
                Juego.getInstance().servidor = servidorHilo;
                servidorHilo.start();
                idSession++;
            }
        } catch (SocketException ex) {
            ex.getMessage();
            ex.getMessage().getClass().getName();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
