package poker;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

//import com.sun.glass.ui.Window;


public class GUIPrincipal extends JFrame {
	
	private static ArrayList<Carta> barajaPc;
    private static ArrayList<Carta> barajaJugador;
    private static ArrayList<Carta> cartasComunitarias;
    
    public static Dimension sizeGame;
	public static ControlUnit controlUnit;
    private static PanelCentral panelCentral;
    private PanelLateral panelLateral;
    private JButton boton;
    public static Window vprincipal;

    
    
    GUIPrincipal(){

        vprincipal = this;
    	sizeGame = new Dimension(1200,720);
    	controlUnit = new ControlUnit();
        setPanelCentral(new PanelCentral());
        panelLateral = new PanelLateral();
        
        barajaPc = controlUnit.getBarajaPc();
        barajaJugador = controlUnit.getBarajaJugador();
        
        initGUI();


        setTitle("Poker");
        setResizable(false);
        //pack();
        setSize(sizeGame);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

   
	public void startGame() {

		/////////////////////////////////////////
        //ADD PANELES
		add(getPanelCentral(),BorderLayout.CENTER);
	    add(panelLateral,BorderLayout.WEST);

	    ////////////////////////////////////////
        //ADD GRAFICAL INTERFASE
	    getPanelCentral().addFichas();
        getPanelCentral().addButtons();
        panelLateral.addButtons();

        getPanelCentral().updateUI();
        panelLateral.updateUI();

        ////////////////////////////////////////
        //REPARTICION DE CARTAS
        
        PanelCentral.getJugador();
		Jugador.realizarApuesta();
        
	    getPanelCentral().addCartasComunitarias();
	    getPanelCentral().addCartasJugador();
	    getPanelCentral().addCartasPC();
	    
	    JOptionPane.showMessageDialog(null, "GAME START");
	    getPanelCentral().turnCards("player");
	    getPanelCentral().turnCards("pc");

			
    }

	private void initGUI() {

		startGame();

    }
	
	

	
	public ArrayList<Carta> getBarajaPc() {
		return barajaPc;
	}

	public static ArrayList<Carta> getBarajaJugador() {
		return barajaJugador;
	}


	public static PanelCentral getPanelCentral() {
		return panelCentral;
	}


	public void setPanelCentral(PanelCentral panelCentral) {
		this.panelCentral = panelCentral;
	}
    
    
}
