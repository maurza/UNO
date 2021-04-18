package com.C2ManuKevin.juego.modelo;

import java.io.Serializable;

public class Jugador implements Serializable {

    private Mano mano;
    private String nombre;
    private String IP;

    public Jugador() {
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public Jugador(String nombre) {
        this.setNombre(nombre);
        this.mano = new Mano();
    }

    public Mano getMano() {
        return mano;
    }

    public void setMano(Mano mano) {
        this.mano = mano;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void verMano() {
        for (Carta carta : mano.getCartas()) {
            System.out.println(carta.toString());
        }
    }

    @Override
    public String toString() {
        return nombre + "//" + IP;
    }

}
