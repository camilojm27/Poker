package poker;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

//import com.sun.glass.ui.Window;


public class GUIPrincipal extends JFrame {
	
	private static ArrayList<Carta> barajaPc;
    private static ArrayList<Carta> barajaJugador;
    
    public static Dimension sizeGame;
	public static ControlUnit controlUnit;
    private PanelCentral panelCentral;
    private PanelLateral panelLateral;
    private JButton boton;
    
    
    GUIPrincipal(){
    	
    	sizeGame = new Dimension(1200,720);
    	controlUnit = new ControlUnit();
        panelCentral = new PanelCentral();
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
		
		add(panelCentral,BorderLayout.CENTER);
	    add(panelLateral,BorderLayout.WEST);
	    	
	    panelCentral.addCartas();
	    panelCentral.addFichas();
	    panelCentral.updateUI();
	    panelLateral.addButtons();
	    panelLateral.updateUI();


			
    }

	private void initGUI() {

		//startMenu();
		startGame();
		

    }

	
	public ArrayList<Carta> getBarajaPc() {
		return barajaPc;
	}

	public static ArrayList<Carta> getBarajaJugador() {
		return barajaJugador;
	}
    
    
}
