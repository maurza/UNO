package com.C2ManuKevin.juego.controlador.servidor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.C2ManuKevin.juego.controlador.ComandosController;
import com.C2ManuKevin.juego.controlador.Juego;
import com.C2ManuKevin.juego.controlador.cliente.Cliente;
import com.C2ManuKevin.juego.modelo.Jugador;
import com.C2ManuKevin.juego.modelo.Partida;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorHilo extends Thread {

    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private int idSessio;

    public ServidorHilo(Socket socket, int id) {
        this.socket = socket;
        this.idSessio = id;
        try {
            dis = new DataInputStream(socket.getInputStream());

        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void desconnectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        String accion = "";
        try {
            do {
                accion = dis.readUTF();
                accionar(accion);
            } while (!accion.equals("end!"));
            System.out.println("Finalizado");
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
        desconnectar();
    }

    public void enviarLogAClientes(String str) {
        enviarMensajeAClientes(ComandosController.RECIBIR_LOG);
        enviarMensajeAClientes(str);
    }

    public void reiniciarPartida() {
        Juego.getInstance().getPartida().descartarManos();
        Juego.getInstance().getPartida().repartirCartasInicial();
        enviarPartidaClientes();
    }

    private void accionar(String accion) {
        ObjectMapper mapper = new ObjectMapper();
        if (accion.equals(ComandosController.ENVIAR_PERSONA)) {
            try {
                if (!Juego.getInstance().getPartida().isIniciada()) {
                    String string = dis.readUTF();
                    Jugador jugador = mapper.readValue(string, Jugador.class);
                    Juego.getInstance().getPartida().agregarJugador(jugador);
                    Juego.getInstance().esperarJugadoresController.reloadUI();
                    enviarPartidaClientes();
                }
            } catch (IOException ex) {
                Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (accion.equals(ComandosController.ENVIAR_PARTIDA)) {
            try {
                String string = dis.readUTF();
                Partida partida = mapper.readValue(string, Partida.class);
                System.out.println("Se recibe la partida " + partida.hashCode() + " del cliente");
                Juego.getInstance().setPartida(partida);
                Juego.getInstance().mesaDeJuegoController.reloadUI();
                enviarPartidaClientes();
            } catch (IOException ex) {
                Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (accion.equals(ComandosController.ENVIAR_LOG)) {
            String string;
            try {
                string = dis.readUTF();
                Juego.getInstance().mesaDeJuegoController.setLog(string);
                enviarLogAClientes(string);
            } catch (IOException ex) {
                Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (accion.equals(ComandosController.REINICIAR_PARTIDA)) {
            reiniciarPartida();
        }
    }

    public void enviarPartidaClientes() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        validarJugadores();
        try {
            for (Socket soc : Juego.getInstance().clientesSockets) {
                enviarMensajeAlCliente(ComandosController.RECIBIR_PARTIDA, soc);
                String sr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(Juego.getInstance().getPartida());
                enviarMensajeAlCliente(sr, soc);
                System.out.println("Se envió a los clientes la partida " + Juego.getInstance().getPartida().hashCode());
            }

        } catch (JsonProcessingException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void enviarMensajeAlCliente(String s, Socket so) // M�todo para iniciar el cliente
    {
        try {
            dos = new DataOutputStream(so.getOutputStream());
            dos.writeUTF(s);
            dos.flush();

        } catch (IOException e) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void enviarMensajeAClientes(String s) {
        Juego.getInstance().clientesSockets.forEach((soc) -> {
            enviarMensajeAlCliente(s, soc);
        });
    }

    private void validarJugadores() {
        int i = 0;
        for (Socket clientesSocket : Juego.getInstance().clientesSockets) {
            if (clientesSocket.isClosed()) {
                Juego.getInstance().getPartida().getJugadores().remove(i);
            }
            i++;
        }
    }

}
