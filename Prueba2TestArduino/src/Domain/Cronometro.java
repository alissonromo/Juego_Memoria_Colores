package Domain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Cronometro implements ActionListener {

    private int cronometro;
    private boolean empezar;
    private int contador;
    private boolean tiempoTerminado;
    private Timer timer;
    private int[] formatos = {60, 40, 30};
    private int formatoIndex;

    public Cronometro() {
        this.cronometro = 0;
        this.timer = new Timer(1000, this);
        this.empezar = true;
        this.contador = 0;
        this.tiempoTerminado = false;
        this.formatoIndex = 0;
    }

    public void recibirDificultad(String dificultad) {
        if (dificultad.equals("Dificil")) {
            this.formatoIndex = 2;
            this.cronometro = formatos[2];
        } else if (dificultad.equals("Facil")) {
            this.formatoIndex = 0;
            this.cronometro = formatos[0];
        } else if (dificultad.equals("Intermedio")) {
            this.formatoIndex = 1;
            this.cronometro = formatos[1];
        }
    }

    public void empezar() {
        this.timer.start();
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.green);
        g.setFont(new Font("Arial Black", 1, 25));
        g.drawString("Tiempo: " + formatoCronometro(this.cronometro), 500, 50);
    }

    public void reiniciar() {
        this.tiempoTerminado = false;
        this.empezar = true;
        this.timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.timer && this.empezar) {
            if (this.cronometro == 0) {
                this.tiempoTerminado = true;
                this.empezar = !this.empezar;
                this.timer.stop();
                if (formatoIndex < formatos.length - 1) {
                    formatoIndex++;
                    this.cronometro = formatos[formatoIndex];
                }
            }else{
                this.cronometro -= 1;
            }
        }
    }

    private String formatoCronometro(int segundos) {
        int minutos = segundos / 60;
        int segundosRestantes = segundos % 60;
        return String.format("%02d:%02d", minutos, segundosRestantes);
    }

    public int getCronometro() {
        return cronometro;
    }

    public void setCronometro(int cronometro) {
        this.cronometro = cronometro;
    }

    public boolean isTiempoTerminado() {
        return tiempoTerminado;
    }

    public void setTiempoTerminado(boolean tiempoTerminado) {
        this.tiempoTerminado = tiempoTerminado;
    }
    
    
}
