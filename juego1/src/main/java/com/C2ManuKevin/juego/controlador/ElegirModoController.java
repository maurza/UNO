package com.C2ManuKevin.juego.controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.C2ManuKevin.juego.controlador.cliente.Cliente;
import com.C2ManuKevin.juego.controlador.servidor.Servidor;
import com.C2ManuKevin.juego.modelo.Jugador;
import com.C2ManuKevin.juego.modelo.Partida;
import com.C2ManuKevin.juego.vista.ElegirModo;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;


public class ElegirModoController {

    private final ElegirModo vista;
   
    public void show() {
        vista.setVisible(true);
    }

    public ElegirModoController() {
        vista = new ElegirModo(this);
    }

    public void esperarJugadores() {
        EsperarJugadoresController esperarJugadoresController = new EsperarJugadoresController();
        Juego.getInstance().esperarJugadoresController = esperarJugadoresController;
        esperarJugadoresController.show();
    }

    public void crearPartida(String name) {
        if (!name.isEmpty()) {
            Partida partida = new Partida();
            Juego.getInstance().setPartida(partida);
            Jugador jugador = new Jugador(name);
            
            jugador.setIP(getIP());
            
            vista.dispose();
            partida.agregarJugador(jugador);
            Juego.getInstance().setJugador(jugador);
            iniciarServidor();
            esperarJugadores();
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese el nombre del jugador");
        }
    }

    //cliente
    public void unirsePartida(String nombre) {
        if (!nombre.isEmpty()){
            String s = JOptionPane.showInputDialog("Ingrese IP de la partida");
            if (!s.isEmpty()) {
                Partida partida = new Partida();
                Juego.getInstance().setPartida(partida);
                Jugador jugador = new Jugador(nombre);
                try {
                    jugador.setIP(InetAddress.getLocalHost().getHostAddress());
                } catch (UnknownHostException ex) {
                    Logger.getLogger(ElegirModoController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Juego.getInstance().setJugador(jugador);
                System.out.println(jugador);
                Juego.getInstance().cliente = new Cliente(s);
                conectarAlServidor(Juego.getInstance().cliente);
                vista.dispose();
                esperarJugadores();
                agregarJugadorAlServidor(jugador);
            }else{
                JOptionPane.showMessageDialog(null, "Ingrese la IP");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Ingrese el nombre del jugador");
        }
        

    }

    private void conectarAlServidor(Cliente cliente) {
        final SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                cliente.conexion();
                return null;
            }

        };
        worker.execute();
    }

    private void iniciarServidor() {
        final SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                Servidor s = new Servidor();
                s.levantarServidor();
                return null;
            }
        };
        worker.execute();
    }

    //cliente
    private void agregarJugadorAlServidor(Jugador jugador) {
        Cliente cliente = Juego.getInstance().cliente;
        cliente.enviarMensajeAlServidor(ComandosController.ENVIAR_PERSONA);
        ObjectMapper mapper = new ObjectMapper();
        try {
            cliente.enviarMensajeAlServidor(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jugador));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public String getIP() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(EsperarJugadoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
