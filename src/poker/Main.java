/*
 * INTEGRANTES:
 * CAMILO JOSE MEZU MINA = 1824313
 * SANTIAGO MARTINEZ MESA = 1823107
 */
package poker;

import javax.swing.UIManager;
import java.awt.EventQueue;

// TODO: Auto-generated Javadoc
//Compilar con la ultima version de JDK para que funcione la musica :D
//Compilar con oracle 8 JDK si no compila

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
