package com.C2ManuKevin.juego.modelo;

import java.io.Serializable;

public class Carta implements Serializable{
	private ColorCarta color;
	private CaraCarta cara;

	public ColorCarta getColor() {
		return color;
	}

	public void setColor(ColorCarta color) {
		this.color = color;
	}

	public CaraCarta getCara() {
		return cara;
	}

	public void setCara(CaraCarta cara) {
		this.cara = cara;
	}

	@Override
	public String toString() {
		return cara.getValor() + "[" + color.getColor() + "]";
	}
}
