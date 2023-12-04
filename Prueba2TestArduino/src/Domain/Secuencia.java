/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author User
 */
public class Secuencia {

    private Random random;
    private ArrayList<Integer> secuencia;
    private int tamanioSecuencia;
    private ArrayList<Color> colores;
    private int index;
    private boolean condicionDibujar;
    private String serie;
    private String dificultad;
    private ArrayList<String> serieArray;
    private Cronometro cronometro;

    public Secuencia() {
        this.random = new Random();
        this.secuencia = new ArrayList<>();
        this.tamanioSecuencia = 0;
        this.colores = new ArrayList<>();
        this.index = 0;
        this.condicionDibujar = true;
        this.serie = "";
        this.dificultad = "";
        this.serieArray = new ArrayList<>();
        this.cronometro = new Cronometro();
    }

    public void crearSecuencia(String dificultad) {
        this.dificultad = dificultad;
        if (this.dificultad.equalsIgnoreCase("Dificil")) {
            this.tamanioSecuencia = 9;
        } else if (this.dificultad.equalsIgnoreCase("Facil")) {
            this.tamanioSecuencia = 3;
        } else if (this.dificultad.equalsIgnoreCase("Intermedio")) {
            this.tamanioSecuencia = 6;
        }
        this.cronometro.recibirDificultad(this.dificultad);
        int numeroRandom = 0;
        for (int i = 0; i < this.tamanioSecuencia; i++) {
            numeroRandom = this.random.nextInt(0, 4);
            if (numeroRandom == 0) {
                this.serie += 0;
                this.serieArray.add("" + 0);
                this.colores.add(new ColorRojo());
            } else if (numeroRandom == 1) {
                this.serie += 1;
                this.serieArray.add("" + 1);
                this.colores.add(new ColorAzul());
            } else if (numeroRandom == 2) {
                this.serie += 2;
                this.serieArray.add("" + 2);
                this.colores.add(new ColorVerde());
            } else if (numeroRandom == 3) {
                this.serie += 3;
                this.serieArray.add("" + 3);
                this.colores.add(new ColorNaranja());
            }

        }
    }

    public void dibujar(Graphics g) {
        if (this.index < this.colores.size() && this.condicionDibujar) {
            this.colores.get(this.index).dibujar(g);
        }
        this.cronometro.dibujar(g);
    }

    public void aumentarContador() {
        if (this.index < this.colores.size()) {
            this.index++;
            this.condicionDibujar = true;
        }
    }

    public boolean isCondicionDibujar() {
        return condicionDibujar;
    }

    public void setCondicionDibujar(boolean condicionDibujar) {
        this.condicionDibujar = condicionDibujar;
    }

    public String serieConseguida() {
        return this.serie;
    }

    public void reiniciarSerie() {
        this.serie = "";
    }

    public boolean secuenciaMostrada() {
        return this.index == this.colores.size();
    }

    public int getTamanioSecuencia() {
        return tamanioSecuencia;
    }

    public void setTamanioSecuencia(int tamanioSecuencia) {
        this.tamanioSecuencia = tamanioSecuencia;
    }

    public ArrayList<Color> getColores() {
        return colores;
    }

    public void setColores(ArrayList<Color> colores) {
        this.colores = colores;
    }

    public void reiniciar() {
        this.colores.clear();
        this.index = 0;
        this.reiniciarSerie();
        this.secuencia.clear();
        this.serieArray.clear();
        this.crearSecuencia(this.dificultad);
        this.condicionDibujar = true;
    }

    public boolean compararValores(String dato, int index) {
        if (this.serieArray.size() > index) {
            return this.serieArray.get(index).equals(dato);
        }
        return false;
    }

    public Cronometro getCronometro() {
        return cronometro;
    }

    public void setCronometro(Cronometro cronometro) {
        this.cronometro = cronometro;
    }

}
