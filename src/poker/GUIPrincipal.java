/*
 * INTEGRANTES:
 * CAMILO JOSE MEZU MINA = 1824313
 * SANTIAGO MARTINEZ MESA = 1823107
 */
package poker;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

// TODO: Auto-generated Javadoc
//import com.sun.glass.ui.Window;
public class GUIPrincipal extends JFrame {

	private static ArrayList<Carta> barajaPc;
    private static ArrayList<Carta> barajaJugador1;
    private static Jugador jugador;
    
    private static Pc pc;
    public static Dimension sizeGame;
	public static ControlUnit controlUnit;
    private static PanelCentral panelCentral;
    private static PanelLateral panelLateral;
    private static int ronda;
    private JButton boton;
    public static Window vprincipal;
	private static Sonidos sonidos;

	GUIPrincipal(){

        vprincipal = this;
    	sizeGame = new Dimension(1200,720);
    	controlUnit = new ControlUnit();
        setPanelCentral(new PanelCentral());
        panelLateral = new PanelLateral();
        
        barajaPc = controlUnit.getBarajaPc();
        barajaJugador1 = controlUnit.getBarajaJugador();
        jugador = new Jugador();
        pc = new Pc();
        
        initGUI();


        setTitle("Poker");
        setResizable(false);
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
			gameStage(1);
			JOptionPane.showMessageDialog(null, "Primera Ronda de Apuestas");
    	}

	
	 /**
 	 * Game stage.
 	 *
 	 * @param ronda the ronda
 	 */
 	public static void gameStage(int ronda) {
	    	panelCentral.infoPanelCentral();
	    	GUIPrincipal.ronda = 2;
	    	switch(ronda) {
				case 1:

					////////////////////////////////////////
					//REPARTICION DE CARTAS
					

					PanelCentral.getJugador();

					jugador.realizarApuesta(1);
					pc.apuestaPc(jugador.getApuesta());

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
					pc.apuestaPc(jugador.getApuesta());
					getPanelCentral().updateUI();
					break;
					
				case 3:
					getPanelCentral().showNextCard(4);
					pc.apuestaPc(jugador.getApuesta());
					getPanelCentral().updateUI();
					break;
				case 4:
					getPanelCentral().showNextCard(5);
					pc.apuestaPc(jugador.getApuesta());
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
	
	

	
	/**
	 * Gets the baraja pc.
	 *
	 * @return the baraja pc
	 */
	public static ArrayList<Carta> getBarajaPc() {
		return barajaPc;
	}

	/**
	 * Gets the baraja jugador.
	 *
	 * @return the baraja jugador
	 */
	public static ArrayList<Carta> getBarajaJugador() {
		return barajaJugador1;
	}


	/**
	 * Gets the panel central.
	 *
	 * @return the panel central
	 */
	public static PanelCentral getPanelCentral() {
		return panelCentral;
	}


	/**
	 * Sets the panel central.
	 *
	 * @param panelCentral the new panel central
	 */
	public void setPanelCentral(PanelCentral panelCentral) {
		this.panelCentral = panelCentral;
	}
	
	/**
	 * Gets the jugador.
	 *
	 * @return the jugador
	 */
	public static Jugador getJugador() {
		return jugador;
	}


	/**
	 * Gets the pc.
	 *
	 * @return the pc
	 */
	public static Pc getPc() {
		return pc;
	}
	
	/**
	 * Gets the panel lateral.
	 *
	 * @return the panel lateral
	 */
	public static PanelLateral getPanelLateral() {
		return panelLateral;
	}
	
	/**
	 * Gets the ronda.
	 *
	 * @return the ronda
	 */
	public static int getRonda() {
		return ronda;
	}

	/**
	 * Sets the ronda.
	 *
	 * @param ronda the new ronda
	 */
	public static void setRonda(int ronda) {
		GUIPrincipal.ronda = ronda;
	}
	
	 /**
 	 * Gets the sonidos.
 	 *
 	 * @return the sonidos
 	 */
 	public static Sonidos getSonidos() {
			return sonidos;
		}

	
    
}
