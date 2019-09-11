package Servidor;

import poker.Baraja;
import poker.Carta;
import poker.ControlUnit;

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

    public static final int cantidadJugadores = 1;
    public  int jugadoresConectados = 0;
    private ExecutorService ejecutarJuego;
    private Lock bloqueoJuego;
    private Condition[] turnos = new Condition[cantidadJugadores];
    private Jugador[] jugadores;
    private ServerSocket servidor;
    private ArrayList<Carta> cartas, cartasComunitarias;
    private Baraja baraja;
    private  int apuestaActual = 0;
    private String[] nombres;

    public Servidor() {
        //super("Servidor Juego");
        System.out.println("SERVIDOR INICIADO...");
        baraja = new Baraja();
        cartasComunitarias = baraja.repartirCartasComunitarias();

        ejecutarJuego = Executors.newFixedThreadPool(cantidadJugadores);
        bloqueoJuego = new ReentrantLock();
        for (int i = 0; i < cantidadJugadores; i++) {
            turnos[i] = bloqueoJuego.newCondition();
        }
        nombres = new String[cantidadJugadores];


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
        /*bloqueoJuego.lock();
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

         */

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
        private  int dinero = 50000;
        private  int miApuesta;
        private  int bote = 0;
        int apuestaIndividual = 0;
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
            //mostrarMensaje( "Jugador " + jugadoresConectados + " conectado\n" );

            try {

                salida.writeInt(jugadoresConectados); // envia la marca del jugador
                salida.flush(); // vacia la salida

                //Envia la etapa 0 del juego y recibe el nombre del jugador
                salida.writeInt(0);
                salida.flush();
                String nombre = (String) entrada.readObject();
                nombres[jugadoresConectados - 1] = nombre;

                if (cantidadJugadores == jugadoresConectados){
                    //  Inicia la etapa 1 de reparticion de cartas

                    System.out.println("Repartiendo cartas al jugador  " + jugadoresConectados);
                    ObjectInputStream entradaTEMP;
                    ObjectOutputStream salidaTEMP;

                    for (int i = 0; i < cantidadJugadores; i++) {

                        salidaTEMP = jugadores[i].salida;
                        entradaTEMP = jugadores[i].entrada;
                        salidaTEMP.writeInt(1);
                        salidaTEMP.flush();
                        cartas = baraja.repartirBarajaJugadores();
                        cartas.forEach(carta -> System.out.println(carta.getId() + carta.getTipo())  );
                        salidaTEMP.writeObject(cartas);
                        salidaTEMP.flush();
                        salidaTEMP.writeObject(cartasComunitarias);
                        salidaTEMP.flush();
                        dinero = entradaTEMP.readInt();
                        apuestaIndividual = entradaTEMP.readInt();
                        apuestaActual += apuestaIndividual;


                        printCambios(dinero, apuestaIndividual, i);

                    }
                    //Stage 2
                    for (int i = 0; i < cantidadJugadores; i++) {
                        salidaTEMP = jugadores[i].salida;
                        entradaTEMP = jugadores[i].entrada;
                        //Se envia la etapa #2
                        System.out.println("Empieza la etapa #2");
                        salidaTEMP.writeInt(2);
                        salidaTEMP.flush();

                        dinero = entradaTEMP.readInt();
                        apuestaIndividual = entradaTEMP.readInt();
                        apuestaActual += apuestaIndividual;

                        printCambios(dinero, apuestaIndividual, i);

                    }

                    for (int i = 0; i < cantidadJugadores; i++) {

                        salidaTEMP = jugadores[i].salida;
                        entradaTEMP = jugadores[i].entrada;
                        //Se envia la etapa #2
                        System.out.println("Empieza la etapa #3");
                        salidaTEMP.writeInt(3);
                        salidaTEMP.flush();

                        dinero = entradaTEMP.readInt();
                        apuestaIndividual = entradaTEMP.readInt();
                        apuestaActual += apuestaIndividual;

                        printCambios(dinero, apuestaIndividual, i);
                    }

                    System.out.println("ETAPA 4 HERE");

                    for (int i = 0; i < cantidadJugadores; i++) {

                        salidaTEMP = jugadores[i].salida;
                        entradaTEMP = jugadores[i].entrada;
                        //Se envia la etapa #2
                        System.out.println("Empieza la etapa #4");
                        salidaTEMP.writeInt(4);
                        salidaTEMP.flush();

                        dinero = entradaTEMP.readInt();
                        apuestaIndividual = entradaTEMP.readInt();
                        apuestaActual += apuestaIndividual;

                        printCambios(dinero, apuestaIndividual, i);
                    }



                   //


                }



                bloqueoJuego.lock();
                /*
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


                }*/



            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            // fin de try


        }

        private void printCambios(int dinero, int apuestaIndividual, int numeroJugador) {
            System.out.println("Dinero de " + nombres[numeroJugador] + " = " + dinero);
            System.out.println(nombres[numeroJugador] + "apuesta " + apuestaIndividual);
            System.out.println("Tamaño del bote = " + apuestaActual);
        }

        public void establecerSuspendido( boolean estado ){
            suspendido = estado;
        }
    }
}