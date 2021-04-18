/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.C2ManuKevin.juego.controlador;

import com.C2ManuKevin.juego.modelo.Carta;
import com.C2ManuKevin.juego.modelo.Jugador;
import com.C2ManuKevin.juego.vista.MesaDeJuego;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

/**
 *
 * @author JUAN-PC
 */
public class MesaDeJuegoController {

    private MesaDeJuego vista;
    private boolean UNO = false;

    public void show() {
        vista = new MesaDeJuego(this);
        vista.setLocationRelativeTo(null);
        vista.setJugadorNombre(Juego.getInstance().getJugador());
        reloadUI();
        vista.setVisible(true);
    }

    public void reloadUI() {
        validarPartida();
        vista.showMano(createCartas());
        vista.showJugadoresLista(createJugadores());
        vista.showCarta(new TextAreaCarta(Juego.getInstance().getPartida().getCartaMesa()));
        vista.reload();

    }

    public void decirUNO() {
        UNO = true;
    }

    public void setLog(String string) {
        vista.setLog(string);
    }

    private List<JLabel> createCartas() {
        ArrayList<JLabel> cartasText = new ArrayList<>();
        Jugador jugador = Juego.getInstance().getJugador();
        Boolean b = Juego.getInstance().getJugador().equals(Juego.getInstance().getPartida().getJugadorConTurno());
        vista.setButtonEnable(b);
        for (Carta carta : jugador.getMano().getCartas()) {
            TextAreaCarta areaCarta = new TextAreaCarta(carta);
            if (b) {
                areaCarta.addMouseListener(new TextAreaCarta(carta));
            }
            cartasText.add(areaCarta);
        }
        return cartasText;
    }

    public void getNuevaCarta() {
        for (int i = 1; i <= Juego.getInstance().getPartida().getCantidadCartasArrastre(); i++) {
            Juego.getInstance().getPartida().getJugadorConTurno().getMano().agregarCarta(Juego.getInstance().getPartida().getMazoArrastre().entregarCarta());
        }
        Juego.getInstance().getPartida().setCantidadCartasArrastre(1);
        //finalizarTurno();
        reloadUI();
    }

    public void finalizarTurno() {
        if (UNO) {
            if (Juego.getInstance().getJugador().getMano().getCartas().size() > 1) {
                penalizar();
                enviarLog("dijo UNO! erroneamente y fue penalizado");
            } else {
                enviarLog("dijo UNO!");
            }
        } else {
            if (Juego.getInstance().getJugador().getMano().getCartas().size() == 1) {
                penalizar();
                enviarLog("no dijo UNO! y fue penalizado");
            }
        }

        Juego.getInstance().getPartida().siguienteTurno();
        System.out.println(Juego.getInstance().cliente);
        enviarPartida();
        UNO = false;
    }

    private List<JLabel> createJugadores() {
        ArrayList<JLabel> jlabels = new ArrayList<>();
        for (Jugador js : Juego.getInstance().getPartida().getJugadores()) {
            String str = js.getNombre() + " => ("
                    + js.getMano().getCartas().size() + " cartas)";
            if (js.equals(Juego.getInstance().getPartida().getJugadorConTurno())) {
                str += " Jugando";
            } else {
                str += " En espera";
            }

            JLabel label = new JLabel(str);
            jlabels.add(label);
        }
        return jlabels;
    }

    private void penalizar() {
        Juego.getInstance().getPartida().getJugadorConTurno().getMano().agregarCarta(Juego.getInstance().getPartida().getMazoArrastre().entregarCarta());
        Juego.getInstance().getPartida().getJugadorConTurno().getMano().agregarCarta(Juego.getInstance().getPartida().getMazoArrastre().entregarCarta());
    }

    private void enviarLog(String s) {
        String str = Juego.getInstance().getJugador().getNombre() + ": " + s;
        if (Juego.getInstance().cliente != null) {
            Juego.getInstance().cliente.enviarLogAlServidor(str);
        } else {
            setLog(str);
            Juego.getInstance().servidor.enviarLogAClientes(str);
        }
    }

    private void validarPartida() {
        for (Jugador jugadore : Juego.getInstance().getPartida().getJugadores()) {
            if (jugadore.getMano().getCartas().size() == 0) {
                JOptionPane.showMessageDialog(vista, jugadore + " Ha ganado la partida, en unos segundos iniciará otra");
                reiniciarPartida();
            }
        }
    }

    private void enviarPartida() {
        if (Juego.getInstance().cliente != null) {
            Juego.getInstance().cliente.enviarPartidaAlServidor();
        } else {
            Juego.getInstance().servidor.enviarPartidaClientes();
        }
    }

    private void reiniciarPartida() {
        if (Juego.getInstance().cliente != null) {
            Juego.getInstance().cliente.reiniciarPartida();
        } else {
            Juego.getInstance().servidor.reiniciarPartida();
        }
    }

    private class TextAreaCarta extends JLabel implements MouseListener {

        private Carta carta;

        public TextAreaCarta(Carta carta) {
            super(carta.getCara().getValor());
            this.carta = carta;
            this.setOpaque(true);
            this.setBackground(carta.getColor().getAwtColor());
            this.setForeground(Color.white);
            this.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
            this.setHorizontalAlignment(JLabel.CENTER);
            this.setBorder(new LineBorder(Color.BLACK, 5));
            this.setPreferredSize(new Dimension(100, 150));
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (Juego.getInstance().getPartida().tirarCarta(getCarta())) {
                finalizarTurno();
                reloadUI();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        /**
         * @return the carta
         */
        public Carta getCarta() {
            return carta;
        }

        /**
         * @param carta the carta to set
         */
        public void setCarta(Carta carta) {
            this.carta = carta;
        }

    }

}
