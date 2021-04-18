/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.C2ManuKevin.juego.controlador;

import com.C2ManuKevin.juego.controlador.cliente.Cliente;
import com.C2ManuKevin.juego.controlador.servidor.ServidorHilo;
import com.C2ManuKevin.juego.modelo.Jugador;
import com.C2ManuKevin.juego.modelo.Partida;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JUAN-PC
 */
public class Juego {

    /**
     * @param args the command line arguments
     */
    /**
     * static Singleton instance.
     */
    private static volatile Juego instance;
    public ServidorHilo servidor;
    private Partida partida;
    public Cliente cliente;
    public List<Socket> clientesSockets = new ArrayList<>();
    public ElegirModoController elegirModoController;
    public EsperarJugadoresController esperarJugadoresController;
    public MesaDeJuegoController mesaDeJuegoController;

    private Jugador jugador;

    /**
     * i
     * Private constructor for singleton.
     */
    private Juego() {
    }

    /**
     * Return a singleton instance of Juego.
     *
     * @return
     */
    public static Juego getInstance() {
        if (instance == null) {
            synchronized (Juego.class) {
                if (instance == null) {
                    instance = new Juego();
                }
            }
        }
        return instance;
    }

    /**
     * @return the jugador
     */
    public Jugador getJugador() {
        return jugador;
    }

    /**
     * @param jugador the jugador to set
     */
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    /**
     * @return the partida
     */
    public Partida getPartida() {
        return partida;
    }

    /**
     * @param partida the partida to set
     */
    public void setPartida(Partida partida) {
        this.partida = partida;
        for (Jugador jug : partida.getJugadores()) {
            if (jug.toString().equals(this.jugador.toString())) {
                this.jugador = jug;
            }
        }

    }

}
