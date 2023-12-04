/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

/**
 *
 * @author User
 */
public class ColorFactory {

    public ColorFactory() {
    }
    //2 Naranja, 4 verde, 3 azul, rojo 5

    //Aquí uso estos valores para probar con el teclado.
    public Color obtenerColor(String dato) {
        if (dato.contains("0")) {
            return new ColorRojo();
        } else if (dato.contains("1")) {
            return new ColorAzul();
        } else if (dato.contains("2")) {
            return new ColorVerde();
        } else if (dato.contains("3")) {
            return new ColorNaranja();
        }
        return null;

    }
}
