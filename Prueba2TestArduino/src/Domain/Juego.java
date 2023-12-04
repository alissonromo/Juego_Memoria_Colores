package Domain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class Juego {

    private Secuencia secuencia;
    private String secuenciaDigitada;
    private boolean condicionIngresarDatos;
    private int contadorRespuestasIngresadas;
    private boolean condicionRespuestaAcertada;
    private boolean condicionPerder;
    private ArrayList<Domain.Color> coloresSeleccionados;
    private ColorFactory colorFactory;
    private int pinVidas;
    private int vidas;
    private boolean condicionReinicio;
    private ConexionArduino conexionArduino;
    private boolean respuestaCorrecto;
    private boolean condicionCorrectoIncorrecto;
    private boolean condicionIncorrecto;

    public Juego() {
        this.secuencia = new Secuencia();
        this.secuenciaDigitada = "";
        this.condicionIngresarDatos = false;
        this.contadorRespuestasIngresadas = 0;
        this.condicionRespuestaAcertada = false;
        this.coloresSeleccionados = new ArrayList<>();
        this.condicionPerder = false;
        this.colorFactory = new ColorFactory();
        this.pinVidas = 7;
        this.vidas = 3;
        this.condicionReinicio = false;
        this.conexionArduino = ConexionArduino.conexionSinglenton();
        this.respuestaCorrecto = false;
        this.condicionCorrectoIncorrecto = false;
        this.condicionIncorrecto = false;
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);

        if (!this.condicionPerder) {
            this.secuencia.dibujar(g);
            g.setColor(Color.green);
            g.setFont(new Font("Arial Black", 1, 25));
            g.drawString("Vidas: " + this.vidas, 280, 50);
            if (this.condicionIngresarDatos) {
                g.setColor(Color.GREEN);
                g.setFont(new Font("Arial Black", Font.BOLD, 20));
                g.drawString("INGRESE LA SECUENCIA MOSTRADA", 180, 200);
                this.secuencia.getCronometro().empezar();
                if (this.respuestaCorrecto && this.condicionCorrectoIncorrecto && this.condicionPerder == false) {
                    g.drawString("Correcto", 270, 270);
                } else if (this.respuestaCorrecto == false && this.condicionCorrectoIncorrecto && this.condicionIncorrecto && this.condicionPerder == false) {
                    g.setColor(Color.RED);
                    g.drawString("Incorrecto", 270, 270);
                }
            }

            if (this.secuencia.getCronometro().isTiempoTerminado()) {
                this.pinVidas++;
                this.vidas -= 1;
                this.conexionArduino.enviarDatos(String.valueOf(this.pinVidas));
                this.condicionReinicio = true;
                this.secuencia.getCronometro().setTiempoTerminado(false);
                if (this.vidas == 0) {
                    this.condicionPerder = true;
                }
            }

            if (this.condicionRespuestaAcertada) {
                this.condicionIngresarDatos = false;
                g.setColor(Color.GREEN);
                g.setFont(new Font("Arial Black", Font.BOLD, 20));
                g.drawString("Secuencia Acertada", 200, 200);
            }

            for (Domain.Color color : this.coloresSeleccionados) {
                color.dibujar(g);
            }
        } else {
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial Black", Font.BOLD, 35));
            g.drawString("Perdiste", 275, 200);
        }
    }

    public Secuencia getSecuencia() {
        return secuencia;
    }

    public boolean datoIngresado(String dato) {
        this.respuestaCorrecto = this.secuencia.compararValores(dato, this.contadorRespuestasIngresadas++);
        if (this.respuestaCorrecto) {
            this.secuenciaDigitada += dato;
            Domain.Color colorObtenido = this.colorFactory.obtenerColor(dato);

            if (colorObtenido != null) {
                if (!this.coloresSeleccionados.isEmpty()) {
                    Domain.Color ultimoColor = this.coloresSeleccionados.get(this.coloresSeleccionados.size() - 1);
                    colorObtenido.coordenadas(ultimoColor.getLargo() + ultimoColor.getPosX() + 50, 400, 50, 50);
                } else {
                    colorObtenido.coordenadas(0, 400, 50, 50);
                }
                this.coloresSeleccionados.add(colorObtenido);
            }
            this.respuestaCorrecto = true;
            return true;
        }

        if (this.contadorRespuestasIngresadas > 0) {
            this.contadorRespuestasIngresadas--;
        }
        this.respuestaCorrecto = false;
        return false;
    }

    public boolean compararSecuencias() {
        this.respuestaCorrecto = this.secuenciaDigitada.equals(this.secuencia.serieConseguida());
        return this.respuestaCorrecto;
    }

    public boolean isCondicionIngresarDatos() {
        return condicionIngresarDatos;
    }

    public void setCondicionIngresarDatos(boolean condicionIngresarDatos) {
        this.condicionIngresarDatos = condicionIngresarDatos;
    }

    public int getContadorRespuestasIngresadas() {
        return contadorRespuestasIngresadas;
    }

    public void setContadorRespuestasIngresadas(int contadorRespuestasIngresadas) {
        this.contadorRespuestasIngresadas = contadorRespuestasIngresadas;
    }

    public void reiniciarDatos() {
        this.secuenciaDigitada = "";
        this.condicionIngresarDatos = false;
        this.contadorRespuestasIngresadas = 0;
        this.condicionRespuestaAcertada = false;
        this.condicionReinicio = false;
        this.respuestaCorrecto = false;
        this.condicionCorrectoIncorrecto = false;
        this.condicionIncorrecto = false;
        this.coloresSeleccionados.clear();
        this.secuencia.getCronometro().reiniciar();
        this.secuencia.reiniciar();
    }

    public boolean isCondicionRespuestaAcertada() {
        return condicionRespuestaAcertada;
    }

    public void setCondicionRespuestaAcertada(boolean condicionRespuestaAcertada) {
        this.condicionRespuestaAcertada = condicionRespuestaAcertada;
    }

    public boolean isCondicionPerder() {
        return condicionPerder;
    }

    public void setCondicionPerder(boolean condicionPerder) {
        this.condicionPerder = condicionPerder;
    }

    public int getPinVidas() {
        return pinVidas;
    }

    public void setPinVidas(int pinVidas) {
        this.pinVidas = pinVidas;
    }

    public boolean isCondicionReinicio() {
        return condicionReinicio;
    }

    public void setCondicionReinicio(boolean condicionReinicio) {
        this.condicionReinicio = condicionReinicio;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public boolean isRespuestaCorrecto() {
        return respuestaCorrecto;
    }

    public void setRespuestaCorrecto(boolean respuestaCorrecto) {
        this.respuestaCorrecto = respuestaCorrecto;
    }

    public boolean isCondicionCorrectoIncorrecto() {
        return condicionCorrectoIncorrecto;
    }

    public void setCondicionCorrectoIncorrecto(boolean condicionCorrectoIncorrecto) {
        this.condicionCorrectoIncorrecto = condicionCorrectoIncorrecto;
    }

    public boolean isCondicionIncorrecto() {
        return condicionIncorrecto;
    }

    public void setCondicionIncorrecto(boolean condicionIncorrecto) {
        this.condicionIncorrecto = condicionIncorrecto;
    }

}
