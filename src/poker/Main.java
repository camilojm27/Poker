/*
 * INTEGRANTES:
 * CAMILO JOSE MEZU MINA = 1824313
 * SANTIAGO MARTINEZ MESA = 1823107
 */
package poker;

import javax.swing.*;
import java.awt.*;

// TODO: Auto-generated Javadoc
//Compilar con la ultima version de JDK para que funcione la musica :D
//Compilar con oracle 8 JDK si no compila, para jugarlo con menos clientes utilice la variable cantidad jugadores en la clase servidor
//Para hacer pruebas fuera de local Host EN la clase menu inicio linea 163 escribe la ip deseada
//El juego no inicia hasta que esten los 6 jugadores (interfaz bloqueada)

/**
 * The Class Main.
 */
public class Main {
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        try {
            String className = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(className);
        }
        catch (Exception e) {}

        EventQueue.invokeLater(new Runnable() {public void run() {
        	
            MenuInicio myWindow =
                    new MenuInicio();



        }});



    }

}
