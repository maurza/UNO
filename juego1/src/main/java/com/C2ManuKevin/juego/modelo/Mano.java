package com.C2ManuKevin.juego.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KevinRg & Manu
 */

public class Mano implements Serializable {

    private List<Carta> cartas;

    public Mano() {
        cartas = new ArrayList<>();
    }

    public void agregarCarta(Carta carta) {
        if (carta == null) {
            System.err.println("No hay mas cartas en el mazo de arrastre");
        }
        getCartas().add(carta);
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }

}
