/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import Domain.ConexionArduino;
import Domain.Juego;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

/**
 *
 * @author User
 */
public class PanelActividad extends javax.swing.JPanel implements ActionListener {

    /**
     * Creates new form PanelActividad
     */
    private Juego juego;
    private Ventana ventana;
    private Timer timer;
    private ConexionArduino conexionArduino;
    private int contador;
    private int contadorRespuestaAcertada;
    private boolean verificarValor;
    private int contadorPerdida;
    private int contadorTiempoCorrectoIncorr;
    private boolean condicionDesactivarAnimacion;
    private int contadorAnimacion;

    public PanelActividad(Ventana ventana) {
        initComponents();
        this.juego = new Juego();
        this.juego.reiniciarDatos();
        this.ventana = ventana;
        this.timer = new Timer(1000, this);
        this.timer.start();
        this.conexionArduino = ConexionArduino.conexionSinglenton();
        this.contador = 0;
        this.contadorRespuestaAcertada = 0;
        this.verificarValor = true;
        this.contadorPerdida = 0;
        this.reiniciarDatos();
        this.contadorTiempoCorrectoIncorr = 0;
        this.condicionDesactivarAnimacion = true;
        this.contadorAnimacion = 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(800, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g) {
        this.juego.dibujar(g);
        if (this.juego.getContadorRespuestasIngresadas() == this.juego.getSecuencia().getTamanioSecuencia()) {
            if (this.juego.compararSecuencias()) {
                this.juego.setCondicionRespuestaAcertada(true);
            }
        }
        this.datosDigitados();

        this.repaint();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    public void abrirVentana() {
        if (true) {
            VentanaFinalizacion ventanaFinalizacion = new VentanaFinalizacion();
            ventanaFinalizacion.setVisible(true);
            this.ventana.dispose();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.timer) {
            if (!this.juego.isCondicionPerder()) {
                if (this.juego.getSecuencia().secuenciaMostrada()) {
                    this.juego.setCondicionIngresarDatos(true);
                }
                if (this.juego.compararSecuencias()) {
                    this.contadorRespuestaAcertada++;

                }
                if ((this.contador % 2) == 0 && this.contador > 0) {
                    this.juego.getSecuencia().aumentarContador();
                } else {
                    this.juego.getSecuencia().setCondicionDibujar(false);
                }

                if (this.contadorRespuestaAcertada == 2 || this.juego.isCondicionReinicio()) {
                    this.juego.reiniciarDatos();
                    this.reiniciarDatos();
                }
                if (this.contadorTiempoCorrectoIncorr > 0) {
                    this.contadorAnimacion++;
                }
                if (this.contadorTiempoCorrectoIncorr > 0 && this.contadorAnimacion >= 2) {
                    this.juego.setCondicionIncorrecto(false);
                    this.contadorTiempoCorrectoIncorr = 0;
                    this.contadorAnimacion = 0;
                }
                this.contador++;
            } else {
                if (this.contadorPerdida == 3) {
                    this.abrirVentana();
                }
                this.contadorPerdida++;
            }
        }

    }

    public void recibirDatos(String dificultad) {
        this.juego.getSecuencia().crearSecuencia(dificultad);

    }

    public void reiniciarDatos() {
        this.contador = 0;
        this.contadorRespuestaAcertada = 0;
        this.contadorTiempoCorrectoIncorr = 0;
        this.condicionDesactivarAnimacion = true;
    }

    public void datosDigitados() {

        if (this.juego.getSecuencia().secuenciaMostrada() && this.juego.isCondicionIngresarDatos()) {
            if (this.conexionArduino.mensaje.contains("8")) {
                this.verificarValor = this.juego.datoIngresado("0");
                this.condicionDesactivarAnimacion = true;
            } else if (this.conexionArduino.mensaje.contains("9")) {
                this.verificarValor = this.juego.datoIngresado("1");
                this.condicionDesactivarAnimacion = true;
            } else if (this.conexionArduino.mensaje.contains("6")) {
                this.verificarValor = this.juego.datoIngresado("2");
                this.condicionDesactivarAnimacion = true;
            } else if (this.conexionArduino.mensaje.contains("2")) {
                this.verificarValor = this.juego.datoIngresado("3");
                this.condicionDesactivarAnimacion = true;
            }

            if (this.verificarValor == false && !this.juego.isCondicionPerder()) {
                this.juego.setCondicionCorrectoIncorrecto(true);
                this.juego.setCondicionIncorrecto(true);
                this.conexionArduino.enviarDatos("" + this.juego.getPinVidas());
                if (this.juego.getPinVidas() == 9) {
                    this.juego.setCondicionPerder(true);
                } else {
                    this.juego.setPinVidas(this.juego.getPinVidas() + 1);
                    this.juego.setVidas(this.juego.getVidas() - 1);
                    this.verificarValor = true;
                }
                this.contadorTiempoCorrectoIncorr++;
            } else {
                if (this.juego.getContadorRespuestasIngresadas() > 0 && this.condicionDesactivarAnimacion) {
                    this.juego.setCondicionCorrectoIncorrecto(true);
                    this.contadorTiempoCorrectoIncorr++;
                    this.condicionDesactivarAnimacion = false;
                }
            }
            this.conexionArduino.limpiarMensaje();
        }
    }
}
