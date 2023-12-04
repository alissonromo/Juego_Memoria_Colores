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
public class VentanaConfiguracion extends JFrame{
    private PanelConfiguracion panelConfiguracion;
    public VentanaConfiguracion(){
        this.setSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.panelConfiguracion = new PanelConfiguracion(this);
        this.add(this.panelConfiguracion);
    }
    
    
    
    
}
