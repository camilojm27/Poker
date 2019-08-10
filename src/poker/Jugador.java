package poker;

import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Jugador extends JOptionPane {
	
	/** The lector. */
	Scanner lector = new Scanner(System.in);
	
	/** The dinero. */
	private static int dinero = 50000;
	
	/** The apuesta. */
	private static int apuesta = 0;
	
	/** The plantarse. */
	private static boolean plantarse;
	
	/** The apuesta valida. */
	static boolean  apuestaValida = false;
	
	PanelCentral p = GUIPrincipal.getPanelCentral();

	
	/**
	 * Plantarse.
	 *
	 * @return true, if successful
	 */
	boolean plantarse() {

		boolean estoyPlantado = true;
		return estoyPlantado;
	}
	
	/**
	 * Realizar apuesta.
	 */
	public static void realizarApuesta() {
		 String inApuesta = JOptionPane.showInputDialog(null, "¿Cuanto desea apostar?");
		 apuesta = Integer.parseInt(inApuesta);
		 
		 if(apuesta > dinero) {
			 JOptionPane.showMessageDialog(null, "no tienes el dinero suficiente");
			 realizarApuesta();
		 }
		 
		 if(apuesta < dinero) {
			 dinero = dinero - apuesta;
			 JOptionPane.showMessageDialog(null, "tu dinero es " + dinero);
			 
		 }
	}
	
	public void turnCards(String who){
	    if(who.equals("player")){
	        p.c1Ju.setIcon(new ImageIcon(getClass().getResource("/imagenes/RR.png")));
	        p.c2Ju.setIcon(new ImageIcon(getClass().getResource("/imagenes/RR.png")));
        }
	    
	    if(who.equals("pNormal")) {
	    	
	    	p.c1Ju.setIcon(GUIPrincipal.controlUnit.getBarajaJugador().get(0).getImagen());
	    	p.c2Ju.setIcon(GUIPrincipal.controlUnit.getBarajaJugador().get(1).getImagen());
	    }
    
	
	
	}

}
