package com.C2ManuKevin.juego.modelo;

public enum CaraCarta {
    CERO("0"), UNO("1"), DOS("2"), TRES("3"), CUATRO("4"), CINCO("5"), SEIS("6"), SIETE("7"), OCHO("8"), NUEVE("9"), BLOQUEO("BLQ"), DEVUELVE("DEV"), MASDOS("+2");

    private String valor;

    private CaraCarta(String valor) {
        this.setValor(valor);
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
