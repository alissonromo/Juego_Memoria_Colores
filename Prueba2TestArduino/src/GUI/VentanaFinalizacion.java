/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author User
 */
public class VentanaFinalizacion extends JFrame{
    
    private PanelFinalizacion panelFinalizacion;
    public VentanaFinalizacion() {
        this.setSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.panelFinalizacion = new PanelFinalizacion(this);
        this.add(this.panelFinalizacion);
    }
    
}
