/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prueba2testarduino;

/**
 *
 * @author JesnerEliecerMelgara
 */
import Domain.ConexionArduino;
import GUI.VentanaConfiguracion;

public class Prueba2TestArduino {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
        // Busca el puerto serial COM3
        ConexionArduino conexionArduino = ConexionArduino.conexionSinglenton();
        VentanaConfiguracion ventana = new VentanaConfiguracion();
        ventana.setVisible(true);
    }
}
