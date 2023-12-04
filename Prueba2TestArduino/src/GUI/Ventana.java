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
public class Ventana extends JFrame{
    private PanelActividad panelActividad;
    public Ventana() {
        this.setSize(new Dimension(800, 600));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.panelActividad = new PanelActividad(this);
        this.add(this.panelActividad);
    }

    public PanelActividad getPanelActividad() {
        return panelActividad;
    }

    public void setPanelActividad(PanelActividad panelActividad) {
        this.panelActividad = panelActividad;
    }
    
    
    
}
