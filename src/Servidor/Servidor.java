package Servidor;

import poker.ControlUnit;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Servidor extends JFrame {
    public static void main(String[] args) {

            EventQueue.invokeLater(new Runnable() {public void run() {

                Servidor servidor = new Servidor();
                //servidor.execute();

            }});

    }

    private JTextArea areaSalida;

    public static final int cantidadJugadores = 2;
    private ExecutorService ejecutarJuego;
    private Lock bloqueoJuego;
    private Condition[] turnos = new Condition[cantidadJugadores];
    private Jugador[] jugadores;
    private int jugadorActual;

    private ServerSocket servidor;

    public Servidor() {
        super("Servidor Juego");


        ejecutarJuego = Executors.newFixedThreadPool(cantidadJugadores);
        bloqueoJuego = new ReentrantLock();
        for (int i = 0; i < cantidadJugadores; i++) {
            turnos[i] = bloqueoJuego.newCondition();
        }


        jugadores = new Jugador[cantidadJugadores];


        //establece el servidor



        areaSalida = new JTextArea();
        areaSalida.setEditable(false);
        add(areaSalida, BorderLayout.CENTER);
        areaSalida.setText("Esperando " + cantidadJugadores + " jugadores");

        setSize(300, 300);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            servidor = new ServerSocket(12345, cantidadJugadores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void execute(){
        for (int i = 0; i < cantidadJugadores; i++) {
            try {
                jugadores[i] = new Jugador(servidor.accept(), i);
                ejecutarJuego.execute(jugadores[i]);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

try {
        jugadores[0].establecerSuspendido(false);
        turnos[0].signal();

}
finally {
    bloqueoJuego.unlock();
}

    }

    private void mostrarMensaje(final String mensajeAMostrar) {
        SwingUtilities.invokeLater(new Runnable() {
                                       public void run() {
                                           areaSalida.append(mensajeAMostrar); // agrega el mensaje
                                       } // fin del metodo run
                                   } // fin de la clase interna
        ); // fin de la llamada a SwingUtilities.invokeLater
    } // fin del metodo mostrarMensaje

    private class Jugador implements Runnable {
        private Socket conexion; // conexion con el cliente
        private Scanner entrada; // entrada del cliente
        private Formatter salida; // salida al cliente
        private int numeroJugador; // identifica al Jugador
        private boolean suspendido = true; // indica si el subproceso esta suspendido

        public Jugador(Socket socket, int numero) {
            numeroJugador = numero;
            conexion = socket;

            try {
                entrada = new Scanner(conexion.getInputStream());
                salida = new Formatter(conexion.getOutputStream());
                salida.flush();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }


        @Override
        public void run() {
            try{
                mostrarMensaje( "Jugador " + "marca" + " conectado\n" );
                salida.format( "%s\n", "marca" ); // envia la marca del jugador
                salida.flush(); // vacia la salida

            } // fin de try
            finally {
                try {
                    conexion.close(); // cierra la conexion con el cliente
                } catch ( IOException excepcionES ){
                    excepcionES.printStackTrace();
                    System.exit( 1 );
                }
            }
        }
        public void establecerSuspendido( boolean estado ){
            suspendido = estado;
        }
    }
}
