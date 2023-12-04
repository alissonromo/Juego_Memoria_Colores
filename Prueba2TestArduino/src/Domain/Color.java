/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.awt.Graphics;

/**
 *
 * @author User
 */
public abstract class Color {

    protected int posX;
    protected int posY;
    protected int largo;
    protected int ancho;

    public Color() {
        this.posX = 0;
        this.posY = 0;
        this.ancho = 0;
        this.largo = 0;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public abstract void dibujar(Graphics g);

    public abstract void coordenadas(int posX, int posY, int ancho, int largo);
}
