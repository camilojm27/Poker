/*
 * INTEGRANTES:
 * CAMILO JOSE MEZU MINA = 1824313
 * SANTIAGO MARTINEZ MESA = 1823107
 */
package poker;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Pc extends JOptionPane {

	static boolean apuestaValida = true;
	private static String username;
	private static int dineroPC = 50000;
	private static int apuestaPC = 0;
	private static boolean plantarsePC;

	PanelCentral p = GUIPrincipal.getPanelCentral();

	boolean plantarse() {

		boolean estoyPlantado = true;
		return estoyPlantado;
	}


	public int apuestaPc(int apuesta) {
		
		if(apuesta > dineroPC) {
			
			JOptionPane.showMessageDialog(null, "El contrincante se ha quedado sin dinero, tu ganas");
			System.exit(1);

		}
		
		if(apuesta < dineroPC && apuesta <= 25000) {
			
			apuestaPC = (apuesta + apuesta);
			dineroPC = dineroPC - getApuestaPC();
			GUIPrincipal.controlUnit.setApuestaActual(apuesta + apuestaPC);
			JOptionPane.showMessageDialog(null, "Cortana apuesta " + getApuestaPC());
		}
		
		else {
			apuestaPC = apuesta+apuesta;
			dineroPC = dineroPC - apuestaPC;
			GUIPrincipal.controlUnit.setApuestaActual(apuesta + apuestaPC);
			JOptionPane.showMessageDialog(null, "Cortana apuesta " + getApuestaPC());
		}

		return dineroPC;
	}
		

	public static int getDineroPC() {
		return dineroPC;
	}

	public static void setDineroPC(int dineroPC) {
		Pc.dineroPC = dineroPC;
	}
	
	public int getApuestaPC() {
		return apuestaPC;
	}

	public static void setUsername(String username) {
		Pc.username = username;
	}

	public static String getUsername() {
		return Pc.username;
	}
	
	public static void setApuestaPC(int apuestaPC) {
		Pc.apuestaPC = apuestaPC;
	}
	
	

}
