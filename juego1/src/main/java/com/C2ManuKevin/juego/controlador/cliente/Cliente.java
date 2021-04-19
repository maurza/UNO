package com.C2ManuKevin.juego.controlador.cliente;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.C2ManuKevin.juego.controlador.ComandosController;
import com.C2ManuKevin.juego.controlador.Juego;
import com.C2ManuKevin.juego.controlador.servidor.ServidorHilo;
import com.C2ManuKevin.juego.modelo.Partida;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KevinRg & Manu
 */

public class Cliente {
    
    private final int PUERTO = 1234; // Puerto para la conexion
    private final String HOST; // Host para la conexion
    private Socket cs;
    private DataOutputStream dos;
    private DataInputStream dis;
    
    public Cliente(String host) {
        HOST = host;
        
    }
    
    public void conexion() {
        try {
            cs = new Socket(HOST, PUERTO);
            dis = new DataInputStream(cs.getInputStream());
            Thread hilo = new Thread(() -> {
                escucharRespuesta();
            });
            hilo.start();
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviarMensajeAlServidor(String s) {
        try {
//            System.out.println("Se envía al servidor: " + s);
            dos = new DataOutputStream(cs.getOutputStream());
            dos.writeUTF(s);
            dos.flush();
            
        } catch (IOException e) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void enviarLogAlServidor(String str) {
        enviarMensajeAlServidor(ComandosController.ENVIAR_LOG);
        enviarMensajeAlServidor(str);
    }
    
    public void reiniciarPartida() {
        enviarMensajeAlServidor(ComandosController.REINICIAR_PARTIDA);
    }
    
    private void escucharRespuesta() {
        String accion = "";
        try {
            do {
                accion = dis.readUTF();
//                System.out.println("Se recibe del servidor: " + accion);
                accionar(accion);
            } while (!accion.equals("end!"));
            System.out.println("Finalizado");
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void accionar(String accion) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        if (accion.equals(ComandosController.RECIBIR_PARTIDA)) {
            try {
                String string = dis.readUTF();
                Partida partida = mapper.readValue(string, Partida.class);
                System.out.println("Se recibe del servidor la partida " + partida.hashCode());
                Juego.getInstance().setPartida(partida);
                if (Juego.getInstance().mesaDeJuegoController != null) {
                    Juego.getInstance().mesaDeJuegoController.reloadUI();
                } else {
                    Juego.getInstance().esperarJugadoresController.reloadUI();
                }
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (accion.equals(ComandosController.EMPEZAR_JUEGO)) {
            Juego.getInstance().esperarJugadoresController.crearMesa();
            
        } else if (accion.equals(ComandosController.RECIBIR_LOG)) {
            String string;
            try {
                string = dis.readUTF();
                Juego.getInstance().mesaDeJuegoController.setLog(string);
            } catch (IOException ex) {
                Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void enviarPartidaAlServidor() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            enviarMensajeAlServidor(ComandosController.ENVIAR_PARTIDA);
            String sr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(Juego.getInstance().getPartida());
            enviarMensajeAlServidor(sr);
            System.out.println("Se envia la partida al servidor");
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
