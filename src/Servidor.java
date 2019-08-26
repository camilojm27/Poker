import poker.ControlUnit;

import javax.swing.JFrame;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Servidor extends JFrame {

    private ExecutorService ejecutarJuego;
    private Lock bloqueoJuego;
    private Condition jugador;
    Servidor(){

        super("Servidor Juego");

        ejecutarJuego = Executors.newFixedThreadPool(3);
        bloqueoJuego = new ReentrantLock();


        setSize(200,300);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
}
