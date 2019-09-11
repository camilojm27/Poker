/*
 * INTEGRANTES:
 * CAMILO JOSE MEZU MINA = 1824313
 * SANTIAGO MARTINEZ MESA = 1823107
 */
package poker;
//https://stackoverflow.com/questions/11570356/jframe-in-full-screen-java
import sun.nio.ch.SocketAdaptor;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketImpl;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.*;

// TODO: Auto-generated Javadoc

public class GUIPrincipal extends JFrame implements Runnable{
	private String hostPoker;
	private Socket conexion; // conexion con el servidor
	public static ObjectInputStream entrada; // entrada del servidor
	public static ObjectOutputStream salida; // salida al servidor



    public static Jugador jugador;
    private static Pc pc;
    public static Dimension sizeGame;
	public static ControlUnit controlUnit;
    public static PanelCentral panelCentral;
    private static PanelLateral panelLateral;
    private static int ronda;
    private JButton boton;
    public static Window vprincipal;
	private static Sonidos sonidos;

	GUIPrincipal(String host){

		hostPoker = host;
		vprincipal = this;
		sizeGame = new Dimension(1200,720);
		controlUnit = new ControlUnit();
		setPanelCentral(new PanelCentral());
		panelLateral = new PanelLateral();

		//barajaJugador = controlUnit.getBarajaJugador();

		jugador = new Jugador();
		pc = new Pc();
        initGUI();

        setTitle("Poker");
        setResizable(true);
        //pack();
        setSize(sizeGame);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        startGame();
        
    }


	/**
	 * Inits the GUI.
	 */
	public void initGUI() {
		
		/////////////////////////////////////////
		//ADD PANELES
		add(getPanelCentral(),BorderLayout.CENTER);
//		add(getPanelLateral(),BorderLayout.WEST);

		////////////////////////////////////////
		//ADD GRAFICAL INTERFASE
		getPanelCentral().addButtons();
//		panelLateral.addButtons();

		getPanelCentral().updateUI();
//		panelLateral.updateUI();
		vprincipal.setVisible(true);

    }
	
	/**
	 * Start game.
	 */
	public void startGame() {
			//sonidos = new Sonidos(Sonidos.secondSong);
		this.run();

					//gameStage(1);
					JOptionPane.showMessageDialog(null, "Primera Ronda de Apuestas");

    	}

	 /**
 	 * Game stage.
 	 *
 	 * @param ronda the ronda
 	 */
 	public static void gameStage(int ronda) throws IOException {

	    	panelCentral.infoPanelCentral();
	    	panelCentral.fixName();
	    	GUIPrincipal.ronda = 2;
	    	switch(ronda) {
				case 0:
					salida.writeObject(Jugador.getUsername());
					salida.flush();
					gameStage(entrada.readInt());


					break;
				case 1:

					////////////////////////////////////////
					//REPARTICION DE CARTAS

					//cartas.forEach(carta -> System.out.println(carta.getId()));
					try {
						ArrayList<Carta> cartas = (ArrayList<Carta>) entrada.readObject();
						GUIPrincipal.controlUnit.setBarajaJugador(cartas);
						cartas =  (ArrayList<Carta>) entrada.readObject();
						GUIPrincipal.controlUnit.setCartasComunitarias(cartas);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					//cartas.forEach(carta -> System.out.println(carta.getId()));
					jugador.realizarApuesta(1);
					pc.apuestaPc(Jugador.getBote());
					getPanelCentral().repartirCartas();
					getPanelCentral().repartirOthers();
					getPanelCentral().addCartasPC();
					getPanelCentral().addCartasComunitarias();

					getPanelCentral().turnCards("player");
					getPanelCentral().turnCards("pc");
					getPanelCentral().updateUI();
					gameStage(entrada.readInt());
					break;
	    		


				case 2:
					panelCentral.informacion.append("Etapa #2");
					getPanelCentral().showNextCard(1);
					getPanelCentral().showNextCard(2);
					getPanelCentral().showNextCard(3);
					pc.apuestaPc(Jugador.getBote());
					panelCentral.updateUI();
					panelCentral.addAL();
					break;
					
				case 3:
					panelCentral.informacion.append("Etapa #3");
					panelCentral.addAL();
					getPanelCentral().showNextCard(4);
					pc.apuestaPc(Jugador.getBote());
					panelCentral.updateUI();

					break;
				case 4:
					panelCentral.informacion.append("Etapa #4");
					getPanelCentral().showNextCard(5);
					pc.apuestaPc(Jugador.getBote());
					getPanelCentral().updateUI();
					ronda++;
					break;
                case 5:
                	if (controlUnit.winner() == Jugador.getUsername()){
                		JOptionPane.showMessageDialog(null, "Ganaste!");
					}
                	else {
                		JOptionPane.showMessageDialog(null, "Perdiste :(");
					}

                    break;
	    	}
	  
	 }
	




	public static PanelCentral getPanelCentral() {
		return panelCentral;
	}

	public void setPanelCentral(PanelCentral panelCentral) {
		this.panelCentral = panelCentral;
	}
	
	public static Jugador getJugador() {
		return jugador;
	}

	public static Pc getPc() {
		return pc;
	}
	
	public static PanelLateral getPanelLateral() {
		return panelLateral;
	}
	
	public static int getRonda() {
		return ronda;
	}

	public static void setRonda(int ronda) throws IOException {
		GUIPrincipal.ronda = ronda;
	}
	
 	public static Sonidos getSonidos() {
			return sonidos;
		}


	@Override
	public void run() {
		try {
			conexion = new Socket(hostPoker, 12345);

			entrada = new ObjectInputStream(conexion.getInputStream());
			salida = new ObjectOutputStream(conexion.getOutputStream());
			getEntrada();
			gameStage(entrada.readInt());




		} catch (IOException e) {
			e.printStackTrace();
			//System.exit(1);
		}
    }

	public  void  getEntrada() {
		try {
			int id = entrada.readInt();
			Jugador.setID(id);
			if(id == 1){
				Jugador.setCordx(291);
				Jugador.setCordy(523);
			}

			if(id == 2){
				Jugador.setCordx(640);
				Jugador.setCordy(523);
			}

			if(id == 3){
				Jugador.setCordx(940);
				Jugador.setCordy(260);
			}

			if(id == 4){
				Jugador.setCordx(640);
				Jugador.setCordy(12);
			}

			if(id == 5){
				Jugador.setCordx(291);
				Jugador.setCordy(12);

			}


		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}
