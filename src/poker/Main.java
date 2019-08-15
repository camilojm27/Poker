package poker;

import javax.swing.UIManager;
import java.awt.EventQueue;

//Compilar con la ultima version de JDK para que funcione la musica :D

public class Main {
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
