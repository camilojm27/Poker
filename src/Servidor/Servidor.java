package Servidor;

import poker.ControlUnit;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Servidor extends JFrame {

    private JTextArea areaSalida;

    public static final int cantidadJugadores = ControlUnit.getCantidadJugadores();
    private ExecutorService ejecutarJuego;
    private Lock bloqueoJuego;
    private Condition jugador;


    private ServerSocket servidor;

    public Servidor(){
        super("Servidor Juego");


        ejecutarJuego = Executors.newFixedThreadPool(ControlUnit.getCantidadJugadores());
        bloqueoJuego = new ReentrantLock();

        //establece el servidor
        try {
            servidor = new ServerSocket(12345, cantidadJugadores);
        } catch (IOException e) {
            e.printStackTrace();
        }


        areaSalida = new JTextArea();
        areaSalida.setEditable(false);
        add(areaSalida, BorderLayout.CENTER);
        areaSalida.setText("Estarando " + cantidadJugadores + " jugadores");

        setSize(300,300);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
}
