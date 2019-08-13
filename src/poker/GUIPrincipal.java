package poker;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

//import com.sun.glass.ui.Window;


public class GUIPrincipal extends JFrame {
	
	private static ArrayList<Carta> barajaPc;
    private static ArrayList<Carta> barajaJugador;
    private static Jugador jugador,pc;
    
    public static Dimension sizeGame;
	public static ControlUnit controlUnit;
    private static PanelCentral panelCentral;
    private static PanelLateral panelLateral;
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
        jugador = new Jugador();
        pc = new Jugador();
        
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

   
	public void startGame() {

        ////////////////////////////////////////
        //REPARTICION DE CARTAS
        
        PanelCentral.getJugador();
		jugador.realizarApuesta(1);
		pc.apuestaPc(jugador.getApuesta());
            
	    getPanelCentral().addCartasJugador();
	    getPanelCentral().addCartasPC();
	    
	    
	    
	   
	    getPanelCentral().turnCards("player");
	    getPanelCentral().turnCards("pc");
	    JOptionPane.showMessageDialog(null, "GAME START");
	    
	    getPanelCentral().addCartasComunitarias();
	    getPanelCentral().updateUI();
			
    }


	public void initGUI() {

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
		vprincipal.setVisible(true);

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
	
	public static Jugador getJugador() {
		return jugador;
	}


	public static Jugador getPc() {
		return pc;
	}
	
	public static PanelLateral getPanelLateral() {
		return panelLateral;
	}
    
    
}
