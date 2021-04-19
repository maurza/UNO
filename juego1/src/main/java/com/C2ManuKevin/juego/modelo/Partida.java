package com.C2ManuKevin.juego.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.C2ManuKevin.juego.controlador.Juego;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author KevinRg & Manu
 */

public class Partida implements Serializable {

    private final int CARTAS_INICIALES = 7;

    private int turno;
    private List<Jugador> jugadores;
    private Mazo mazoArrastre;
    private Carta cartaMesa;
    private int sentidoJuego;
    private int cantidadCartasArrastre;
    private boolean iniciada = false;

    public Partida() {
        jugadores = new ArrayList<>();
        mazoArrastre = new Mazo();
        sentidoJuego = 1;
        cantidadCartasArrastre = 1;
    }

    public void cambiarSentido() {
        setSentidoJuego(getSentidoJuego() * -1);
    }

    public void agregarJugador(Jugador jugador) {
        getJugadores().add(jugador);

    }

    public void eliminarJugador(Jugador jugador) {
        getJugadores().remove(jugador);
    }

    public void repartirCartasInicial() {
        for (int i = 1; i <= CARTAS_INICIALES; i++) {
            jugadores.forEach((jugador) -> {
                jugador.getMano().agregarCarta(mazoArrastre.entregarCarta());
            });
        }
    }

    public Mazo getMazoArrastre() {
        return mazoArrastre;
    }

    public void setMazoArrastre(Mazo mazoArrastre) {
        this.mazoArrastre = mazoArrastre;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    /**
     * @return the turno
     */
    public int getTurno() {
        return turno;
    }

    /**
     * @param turno the turno to set
     */
    public void setTurno(int turno) {
        this.turno = turno;
    }

    public void sortearTurnos() {
        Random random = new Random();
        Collections.shuffle(jugadores, random);
    }

    public void siguienteTurno() {
        turno += getSentidoJuego();
        if (turno == jugadores.size()) {
            turno = 0;
        } else if (turno == -1) {
            turno = jugadores.size() - 1;
        }
    }

    public boolean tirarCarta(Carta carta) {

        if (cantidadCartasArrastre > 1) {
            if (carta.getColor().equals(getCartaMesa().getColor()) || carta.getCara().equals(CaraCarta.MASDOS)) {
                switch (carta.getCara()) {
                    case DEVUELVE:
                        this.setCartaMesa(carta);
                        Juego.getInstance().getJugador().getMano().getCartas().remove(carta);
                        validarDevolver(carta);
                        return true;
                    case MASDOS:
                        this.setCartaMesa(carta);
                        Juego.getInstance().getJugador().getMano().getCartas().remove(carta);
                        validarMasDos(carta);
                        return true;
                    case BLOQUEO:
                        this.setCartaMesa(carta);
                        Juego.getInstance().getJugador().getMano().getCartas().remove(carta);
                        return true;
                    default:
                        break;
                }
            }

        } else if (carta.getColor().equals(getCartaMesa().getColor()) || carta.getCara().equals(getCartaMesa().getCara())) {
            this.setCartaMesa(carta);
            validarDevolver(carta);
            validarMasDos(carta);
            validarBloqueo(carta);
            Juego.getInstance().getJugador().getMano().getCartas().remove(carta);
            return true;
        }
        return false;
    }

    @JsonIgnore
    public Jugador getJugadorConTurno() {
        return jugadores.get(turno);
    }

    @JsonIgnore
    public void setJugadorConTurno(Jugador jugador) {
        jugadores.set(turno, jugador);
    }

    /**
     * @return the cartaMesa
     */
    public Carta getCartaMesa() {
        return cartaMesa;
    }

    /**
     * @param cartaMesa the cartaMesa to set
     */
    public void setCartaMesa(Carta cartaMesa) {
        this.cartaMesa = cartaMesa;
    }

    /**
     * @return the sentidoJuego
     */
    public int getSentidoJuego() {
        return sentidoJuego;
    }

    /**
     * @param sentidoJuego the sentidoJuego to set
     */
    public void setSentidoJuego(int sentidoJuego) {
        this.sentidoJuego = sentidoJuego;
    }

    public void descartarManos() {
        for (Jugador jugadore : jugadores) {
            jugadore.getMano().setCartas(new ArrayList<>());
        }

    }

    private void validarDevolver(Carta carta) {
        if (carta.getCara().equals(CaraCarta.DEVUELVE)) {
            Juego.getInstance().getPartida().cambiarSentido();
        }
    }

    /**
     * @return the cantidadCartasArrastre
     */
    public int getCantidadCartasArrastre() {
        return cantidadCartasArrastre;
    }

    /**
     * @param cantidadCartasArrastre the cantidadCartasArrastre to set
     */
    public void setCantidadCartasArrastre(int cantidadCartasArrastre) {
        this.cantidadCartasArrastre = cantidadCartasArrastre;
    }

    private void validarMasDos(Carta carta) {
        if (carta.getCara().equals(CaraCarta.MASDOS)) {
            if (cantidadCartasArrastre % 2 == 0) {
                cantidadCartasArrastre += 2;
            } else {
                cantidadCartasArrastre++;
            }
        }
    }

    private void validarBloqueo(Carta carta) {
        if (carta.getCara().equals(CaraCarta.BLOQUEO)) {
            siguienteTurno();
        }
    }

    /**
     * @return the iniciada
     */
    public boolean isIniciada() {
        return iniciada;
    }

    /**
     * @param iniciada the iniciada to set
     */
    public void setIniciada(boolean iniciada) {
        this.iniciada = iniciada;
    }

}
