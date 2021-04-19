package com.C2ManuKevin.juego.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author KevinRg & Manu
 */


public class Mazo implements Serializable {

    private List<Carta> cartas;

    public Mazo() {
        cartas = crearMazo();
    }

    public void barajar() {
        Random random = new Random();
        Collections.shuffle(getCartas(), random);
    }

    public void verMazo() {
        for (Carta carta : getCartas()) {
            System.out.println(carta);
        }
    }

    public Carta entregarCarta() {
        if (getCartas().size() > 0) {
            Carta carta = getCartas().get(0);
            getCartas().remove(0);
            return carta;
        } else {
            cartas = crearMazo();
            barajar();
            return entregarCarta();
        }
    }

    /**
     * @return the cartas
     */
    public List<Carta> getCartas() {
        return cartas;
    }

    /**
     * @param cartas the cartas to set
     */
    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }

    private List<Carta> crearMazo() {
        ArrayList<Carta> carts = new ArrayList<>();
        for (ColorCarta color : ColorCarta.values()) {
            for (CaraCarta cara : CaraCarta.values()) {
                Carta carta = new Carta();
                carta.setColor(color);
                carta.setCara(cara);
                carts.add(carta);
            }
        }
        return carts;
    }

}
