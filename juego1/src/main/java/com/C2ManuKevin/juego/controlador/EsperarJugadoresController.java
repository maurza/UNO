package com.C2ManuKevin.juego.controlador;

import com.C2ManuKevin.juego.modelo.Jugador;
import com.C2ManuKevin.juego.vista.EsperarJugadores;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KevinRg & Manu
 */
public class EsperarJugadoresController {
    
    private EsperarJugadores vista;
    
    public EsperarJugadoresController() {
        this.vista = new EsperarJugadores(this);
        vista.reload();
    }
    
    public String getIP() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(EsperarJugadoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public void show() {
        vista.setVisible(true);
    }
    
    public Jugador getJugador(int i) {
        return Juego.getInstance().getPartida().getJugadores().get(i);
    }
    
    public int getCantidadJugadores() {
        return Juego.getInstance().getPartida().getJugadores().size();
    }
    
    public void copiarAlPortapapeles(String text) {
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection ss = new StringSelection(text);
        cb.setContents(ss, ss);
    }
    
    public void reloadUI() {
        vista.reload();
    }
    
    public void iniciarJuego() {
        Juego.getInstance().getPartida().getMazoArrastre().barajar();
        Juego.getInstance().getPartida().repartirCartasInicial();
        Juego.getInstance().getPartida().sortearTurnos();
        Juego.getInstance().getPartida().setCartaMesa(Juego.getInstance().getPartida().getMazoArrastre().entregarCarta());
        Juego.getInstance().servidor.enviarPartidaClientes();
        crearMesa();
        Juego.getInstance().servidor.enviarMensajeAClientes(ComandosController.EMPEZAR_JUEGO);
        Juego.getInstance().getPartida().setIniciada(true);
    }
    
    public void crearMesa() {
        MesaDeJuegoController mesaDeJuegoController = new MesaDeJuegoController();
        Juego.getInstance().mesaDeJuegoController = mesaDeJuegoController;
        mesaDeJuegoController.show();
        vista.dispose();
    }
}
