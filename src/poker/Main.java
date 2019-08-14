package poker;

import javax.swing.UIManager;
import java.awt.EventQueue;

//Compilar con Java 8 JDK  de oracle y no con openJDK para que funcione la musica

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
