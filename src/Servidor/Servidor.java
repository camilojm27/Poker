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
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Servidor extends JFrame {
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {public void run() {

            Servidor servidor = new Servidor();

        }});

    }

    private JTextArea areaSalida;

    public static final int cantidadJugadores = 6;
    public  int jugadoresConectados = 0;
    private ExecutorService ejecutarJuego;
    private Lock bloqueoJuego;
    private Condition[] turnos = new Condition[cantidadJugadores];
    private Jugador[] jugadores;
    private ServerSocket servidor;
    private ArrayList<Carta> cartasComunitarias;
    private Baraja baraja;
    private  int apuestaActual = 0;
    private String[] nombres;
    private ArrayList<Boolean> ganador;
    private ArrayList<Integer> PUNTAJES_GLOBALES;
    private ControlUnit controlUnit;

    public Servidor() {
        super("Servidor Juego");
        System.out.println("SERVIDOR INICIADO... esperando " + cantidadJugadores + " Jugadores ");
        controlUnit = new ControlUnit();
        baraja = new Baraja();
        cartasComunitarias = baraja.repartirCartasComunitarias();
        PUNTAJES_GLOBALES = new ArrayList<>();

        ejecutarJuego = Executors.newFixedThreadPool(cantidadJugadores);
        bloqueoJuego = new ReentrantLock();
        for (int i = 0; i < cantidadJugadores; i++) {
            turnos[i] = bloqueoJuego.newCondition();
        }
        nombres = new String[6];


        jugadores = new Jugador[cantidadJugadores];


        //establece el servidor



        try {
            servidor = new ServerSocket(5000, cantidadJugadores);
        } catch (IOException e) {
            e.printStackTrace();
        }
        areaSalida = new JTextArea();
        areaSalida.setEditable(false);
        add(areaSalida, BorderLayout.CENTER);
        areaSalida.setText("Esperando " + cantidadJugadores + " jugadores \n");



        setSize(300, 300);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



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
    private int mayorPuntaje(){
        for (Servidor.Jugador jugador : jugadores) {

            for (int puntaje = 0; puntaje < jugador.puntajeJugador.size(); puntaje++) {
                jugador.playerScore += jugador.puntajeJugador.get(puntaje);
            }
            PUNTAJES_GLOBALES.add(jugador.playerScore);

            System.out.println("Puntaje JUgador = " + jugador.playerScore);
        }
        Collections.sort(PUNTAJES_GLOBALES);
        PUNTAJES_GLOBALES.forEach(puntaje -> System.out.println(puntaje));


        for (Servidor.Jugador jugador : jugadores) {
            System.out.println("PUntajes globales : " + PUNTAJES_GLOBALES);
            if (jugador.playerScore == PUNTAJES_GLOBALES.get(PUNTAJES_GLOBALES.size() - 1)) {
                System.out.println("Player Score = " + jugador.playerScore);
                return jugador.numeroJugador;
            }
        }


        return -1;
    }



    public ArrayList<Boolean> winner(){


        ArrayList<Boolean> salida = new ArrayList<>();

        for (Servidor.Jugador jugador : jugadores) {
            jugador.puntajeJugador = makePuntaje(jugador.cartas);

        }
        int ganador = mayorPuntaje();
        System.out.println("EL ganodor es el #  " + ganador);
        for (int i = 0; i < cantidadJugadores; i++) {
            if (i == ganador){
                salida.add(true);
            }
            else {
                salida.add(false);
            }
        }

        return salida;

    }
    public ArrayList<Integer> makePuntaje(ArrayList<Carta> barajaJugador){
        Carta aux;
        ArrayList<Integer>puntajeJugador = new ArrayList<>();


        for (int cartaComunitaria = 0; cartaComunitaria < 5; cartaComunitaria++) {
            aux = cartasComunitarias.get(cartaComunitaria);


            for (int cartaJugador = 0; cartaJugador < 2; cartaJugador++) {
                cartasComunitarias.add(cartaComunitaria, barajaJugador.get(cartaJugador));
                cartasComunitarias.remove(cartaComunitaria + 1);
                puntajeJugador.add(controlUnit.ranking(cartasComunitarias));
                cartasComunitarias.remove(cartaComunitaria);
                cartasComunitarias.add(cartaComunitaria, aux);
            }
        }
        return puntajeJugador;
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
        private ArrayList<Carta> cartas;
        private ArrayList<Integer> puntajeJugador;
        private int playerScore;

        public Jugador(Socket socket, int numero) {
            numeroJugador = numero;
            conexion = socket;
            cartas = baraja.repartirBarajaJugadores();

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
                        salidaTEMP.writeObject(nombres);
                        salidaTEMP.flush();
                        cartas.forEach(carta -> System.out.println(carta.getId() + carta.getTipo())  );
                        salidaTEMP.writeObject(cartas);
                        salidaTEMP.flush();
                        salidaTEMP.writeObject(cartasComunitarias);
                        salidaTEMP.flush();
                        dinero = entradaTEMP.readInt();
                        apuestaIndividual = entradaTEMP.readInt();
                        apuestaActual += apuestaIndividual;

                        salidaTEMP.writeInt(apuestaActual);
                        salidaTEMP.flush();

                        printCambios(dinero, apuestaIndividual, i);

                    }
                    //Stage 2
                    for (int i = 0; i < cantidadJugadores; i++) {
                        salidaTEMP = jugadores[i].salida;
                        entradaTEMP = jugadores[i].entrada;
                        //Se envia la etapa #2
                        System.out.println("Empieza la etapa #2");
                        mostrarMensaje("Etapa 2");

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

                        System.out.println("Empieza la etapa #3");
                        mostrarMensaje("Etapa 3");

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
                        mostrarMensaje("Etapa 4");

                        salidaTEMP.writeInt(4);
                        salidaTEMP.flush();

                        System.out.println("Salida dinero");
                      //  dinero = entradaTEMP.readInt();
                       // apuestaIndividual = entradaTEMP.readInt();
                        //apuestaActual += apuestaIndividual;

                        printCambios(dinero, apuestaIndividual, i);
                    }

                    //Se elige ganador
                    for (int i = 0; i < cantidadJugadores; i++) {

                        System.out.println("Etapa 5 ");
                        mostrarMensaje("Etapa 5");
                        salidaTEMP = jugadores[i].salida;
                        entradaTEMP = jugadores[i].entrada;
                        salidaTEMP.writeInt(5);
                        salidaTEMP.flush();
                        ganador = winner();
                        ganador.forEach(ganador -> System.out.println(ganador));


                        salidaTEMP.writeBoolean(ganador.get(i));
                        salidaTEMP.flush();
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