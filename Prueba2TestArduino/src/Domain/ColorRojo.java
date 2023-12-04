/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author User
 */
public class ColorRojo extends Color {

    public ColorRojo() {
        this.posX = 260;
        this.posY = 150;
        this.ancho = 200;
        this.largo = 200;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(java.awt.Color.RED);
        g.fillOval(this.posX, this.posY, this.ancho, this.largo);
        g.setFont(new Font("Arial Black", 1, 16));
        g.drawString("Rojo", this.posX, this.posY + this.ancho + 40);
    }

    @Override
    public void coordenadas(int posX, int posY, int ancho, int largo) {
        this.posX = posX;
        this.posY = posY;
        this.largo = largo;
        this.ancho = ancho;
    }

}
