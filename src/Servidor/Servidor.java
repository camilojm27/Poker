package Servidor;

import poker.Baraja;
import poker.Carta;
import poker.ControlUnit;
import poker.GUIPrincipal;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Servidor extends ControlUnit {
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {public void run() {

            Servidor servidor = new Servidor();


        }});

    }

    private JTextArea areaSalida;

    public static final int cantidadJugadores = 2;
    public  int jugadoresConectados = 0;
    private ExecutorService ejecutarJuego;
    private Lock bloqueoJuego;
    private Condition[] turnos = new Condition[cantidadJugadores];
    private Jugador[] jugadores;
    private int jugadorActual;
    private Window ventana;
    private ServerSocket servidor;
    private ArrayList<Carta> cartas, cartasComunitarias;
    private ControlUnit controlUnit;
    private Baraja baraja;

    public Servidor() {
        //super("Servidor Juego");
        baraja = new Baraja();
        cartasComunitarias = baraja.repartirCartasComunitarias();

        ejecutarJuego = Executors.newFixedThreadPool(cantidadJugadores);
        bloqueoJuego = new ReentrantLock();
        for (int i = 0; i < cantidadJugadores; i++) {
            turnos[i] = bloqueoJuego.newCondition();
        }


        jugadores = new Jugador[cantidadJugadores];


        //establece el servidor



        try {
            servidor = new ServerSocket(12345, cantidadJugadores);
        } catch (IOException e) {
            e.printStackTrace();
        }
        areaSalida = new JTextArea();
        areaSalida.setEditable(false);
        //add(areaSalida, BorderLayout.CENTER);
        areaSalida.setText("Esperando " + cantidadJugadores + " jugadores \n");

        //ventana = this;

        //setSize(300, 300);
        //setResizable(true);
        //setLocationRelativeTo(null);
        //setVisible(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        execute();
    }

    public void execute(){

        System.out.println("Servidor Iniciado");

        for (int i = 0; i < cantidadJugadores; i++) {
            try {
                jugadores[i] = new Jugador(servidor.accept(), i);
                ejecutarJuego.execute(jugadores[i]);
                System.out.println(i);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        bloqueoJuego.lock();
        try {
            jugadores[0].wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
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
                                           areaSalida.append(mensajeAMostrar + "\n"); // agrega el mensaje
                                           //System.out.println(mensajeAMostrar);
                                       } // fin del metodo run
                                   } // fin de la clase interna
        ); // fin de la llamada a SwingUtilities.invokeLater
    } // fin del metodo mostrarMensaje

    private class Jugador implements Runnable {

        private Socket conexion; // conexion con el cliente
        private ObjectInputStream entrada; // entrada del cliente
        private ObjectOutputStream salida; // salida al cliente
        private int numeroJugador; // identifica al Jugador
        private boolean suspendido = true; // indica si el subproceso esta suspendido
        private int dinero = 50000;
        private int miApuesta;
        private int bote = 0;
        private int apuestaActual;

        public Jugador(Socket socket, int numero) {
            numeroJugador = numero;
            conexion = socket;

            try {
                salida = new ObjectOutputStream(conexion.getOutputStream());
                entrada = new ObjectInputStream(conexion.getInputStream());
                salida.flush();
                jugadoresConectados++;
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }



        public void run() {

            System.out.println("Jugador # " + jugadoresConectados + " conectado");
            mostrarMensaje( "Jugador " + jugadoresConectados + " conectado\n" );

            try {
                salida.writeInt(jugadoresConectados); // envia la marca del jugador
                salida.flush(); // vacia la salida
                System.out.println("Repartiendo cartas al jugador  " + jugadoresConectados);
                cartas = baraja.repartirBarajaJugadores();
                cartas.forEach(carta -> System.out.println(carta.getId() + carta.getTipo())  );
                salida.writeObject(cartas);
                salida.flush();
                salida.writeObject(cartasComunitarias);
                salida.flush();


                System.out.println("Dinero = " + dinero);
                System.out.println("apuesta = " + apuestaActual);
                bloqueoJuego.lock();

                while (suspendido){
                    try {
                        turnos[0].await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finally {
                        bloqueoJuego.unlock();
                    }
                    System.out.println("El otro jugador se conecto. Ahora es su turno.\n" );


                }



            } catch (IOException e) {
                e.printStackTrace();
            }

            // fin de try


        }
        public void establecerSuspendido( boolean estado ){
            suspendido = estado;
        }
    }
}