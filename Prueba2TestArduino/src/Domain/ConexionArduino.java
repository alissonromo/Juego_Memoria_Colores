package Domain;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class ConexionArduino {

    private static SerialPort comPort;
    private static ConexionArduino conexionArduino;
    public static String mensaje;

    private ConexionArduino() {
        this.comPort = SerialPort.getCommPort("COM3");
        this.conexion();
        this.mensaje = "";
        this.enviarDatos("1");
    }

    public static ConexionArduino conexionSinglenton() {
        if (conexionArduino == null) {
            conexionArduino = new ConexionArduino();
        }
        return conexionArduino;
    }

    public void conexion() {
        if (comPort != null) {
            // Abre el puerto serial
            boolean openPort = comPort.openPort();
            // Configura los parámetros de la conexión (baudrate, bits de datos, bits de parada, paridad)
            comPort.setComPortParameters(9600, 8, 1, 0);

            // Escucha eventos de entrada serial
            comPort.addDataListener(new SerialPortDataListener() {
                @Override
                public int getListeningEvents() {
                    return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
                }

                //METODO PARA LEER LOS DATOS.
                @Override
                public void serialEvent(SerialPortEvent event) {
                    if (event.getEventType() == SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
                        // Datos disponibles para leer

                        byte[] newData = new byte[comPort.bytesAvailable()];
                        comPort.readBytes(newData, newData.length);

                        // Convierte los datos a una cadena y muestra
                        String receivedData = new String(newData);

                        //LO DE LOS ARREGLOS SE PUEDE MEJORAR Y COLOCAR OTRA FORMA MÁS OPTIMA JAJA.
                        if (!receivedData.isBlank()) {
                            mensaje = receivedData;
                        } else {
                            receivedData = "0";
                        }
                    }

                }
            });
        } else {
            System.out.println("Puerto COM3 no encontrado.");
        }

    }

    //METODO PARA ENVIAR LOS DATOS.
    public void enviarDatos(String datos) {
        if (comPort.isOpen()) {
            comPort.writeBytes(datos.getBytes(), datos.length());
        } else {
            System.out.println("El puerto COM3 no está abierto.");
        }
    }

    public static void limpiarMensaje() {
        mensaje = "";
    }

}
