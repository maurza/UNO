package com.C2ManuKevin.juego.modelo;

import java.awt.Color;

/**
 *
 * @author KevinRg & Manu
 */

public enum ColorCarta {
    ROJO("ROJO", Color.RED), AMARILLO("AMARILLO", Color.YELLOW), VERDE("VERDE", Color.GREEN), AZUL("AZUL", Color.BLUE);

    private String color;
    private Color awtColor;

    private ColorCarta(String color, Color awtColor) {
        this.setColor(color);
        this.setAwtColor(awtColor);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the awtColor
     */
    public Color getAwtColor() {
        return awtColor;
    }

    /**
     * @param awtColor the awtColor to set
     */
    public void setAwtColor(Color awtColor) {
        this.awtColor = awtColor;
    }
}
