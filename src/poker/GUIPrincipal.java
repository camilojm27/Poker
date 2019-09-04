/*
 * INTEGRANTES:
 * CAMILO JOSE MEZU MINA = 1824313
 * SANTIAGO MARTINEZ MESA = 1823107
 */
package poker;

import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.*;

// TODO: Auto-generated Javadoc
//import com.sun.glass.ui.Window;
public class GUIPrincipal extends JFrame {
	private String hostPoker;
	private Socket conexion; // conexion con el servidor
	private Scanner entrada; // entrada del servidor
	private Formatter salida; // salida al servidor


	private static ArrayList<Carta> barajaPc;
    private static ArrayList<Carta> barajaJugador1;
    private static Jugador jugador1,jugador2,jugador3,jugador4,jugador5,jugadorActual;
    private static Pc pc;
    public static Dimension sizeGame;
	public static ControlUnit controlUnit;
    private static PanelCentral panelCentral;
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
        
        barajaPc = controlUnit.getBarajaPc();
        barajaJugador1 = controlUnit.getBarajaJugador();
        jugador1 = new Jugador();
        jugador2 = new Jugador();
        jugador3 = new Jugador();
        jugador4 = new Jugador();
        jugador5 = new Jugador();
        
        pc = new Pc();
        
        jugadorActual = jugador1;
        
        initGUI();


        setTitle("Poker");
        setResizable(false);
        //pack();
        setSize(sizeGame);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        iniciarCliente();
        startGame();
        
    }

	private void iniciarCliente() {
		// se conecta al servidor, obtiene los flujos e inicia subproceso de salida

		try {
			conexion = new Socket(InetAddress.getByName(hostPoker), 12345);

			entrada = new Scanner(conexion.getInputStream());
			salida = new Formatter(conexion.getOutputStream());

		} catch (IOException e) {
			e.printStackTrace();
		}
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
			
			gameStage(1);
			JOptionPane.showMessageDialog(null, "Primera Ronda de Apuestas");
    	}

	
	 /**
 	 * Game stage.
 	 *
 	 * @param ronda the ronda
 	 */
 	public static void gameStage(int ronda) {
 		
 			Jugador jugadorActual = PanelCentral.getJugador();
	    	panelCentral.infoPanelCentral();
	    	GUIPrincipal.ronda = 2;
	    	switch(ronda) {
				case 1:

					////////////////////////////////////////
					//REPARTICION DE CARTAS
				
					jugadorActual.realizarApuesta(1,jugadorActual);
					pc.apuestaPc(Jugador.getBote());

					getPanelCentral().repartirCartas();
					getPanelCentral().addCartasPC();
					getPanelCentral().addCartasComunitarias();

					getPanelCentral().turnCards("player");
					getPanelCentral().turnCards("pc");
					getPanelCentral().updateUI();
					break;
	    		


				case 2:
					getPanelCentral().showNextCard(1);
					getPanelCentral().showNextCard(2);
					getPanelCentral().showNextCard(3);
					pc.apuestaPc(Jugador.getBote());
					getPanelCentral().updateUI();
					break;
					
				case 3:
					getPanelCentral().showNextCard(4);
					pc.apuestaPc(Jugador.getBote());
					getPanelCentral().updateUI();
					break;
				case 4:
					getPanelCentral().showNextCard(5);
					pc.apuestaPc(Jugador.getBote());
					getPanelCentral().updateUI();
					ronda++;
					break;
                case 5:
                	if (controlUnit.winner() == Jugador.getUsername(jugadorActual)){
                		JOptionPane.showMessageDialog(null, "Ganaste!");
					}
                	else {
                		JOptionPane.showMessageDialog(null, "Perdiste :(");
					}

                    break;
	    	}
	  
	 }
	
	public static ArrayList<Carta> getBarajaPc() {
		return barajaPc;
	}

	public static ArrayList<Carta> getBarajaJugador() {
		return barajaJugador1;
	}

	public static PanelCentral getPanelCentral() {
		return panelCentral;
	}

	public void setPanelCentral(PanelCentral panelCentral) {
		this.panelCentral = panelCentral;
	}
	
	public static Jugador getJugador() {
		return jugadorActual;
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

	public static void setRonda(int ronda) {
		GUIPrincipal.ronda = ronda;
	}
	
 	public static Sonidos getSonidos() {
			return sonidos;
		}

	
    
}
