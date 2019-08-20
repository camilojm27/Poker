/*
 * INTEGRANTES:
 * CAMILO JOSE MEZU MINA = 1824313
 * SANTIAGO MARTINEZ MESA = 1823107
 */
package poker;

import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

// TODO: Auto-generated Javadoc
/**
 * The Class Jugador.
 */
public class Jugador extends JOptionPane {

	/**
	 * The apuesta valida.
	 */
	static boolean apuestaValida = true;
	
	/** The username. */
	private static String username;



	/** The dinero. */
	private static int dinero = 50000;
	
	/** The dinero PC. */
	private static int dineroPC = 50000;
	
	/** The apuesta. */
	private static int apuesta = 0;
	
	/** The apuesta actual. */
	private static int apuestaActual= 0;
	
	/** The apuesta PC. */
	private static int apuestaPC = 0;
	
	/** The plantarse. */

	private static boolean plantarseJugador, plantarsePC;
	
	/** The apuesta valida. */
	

	private static boolean plantarse;
	/**
	 * The lector.
	 */
	private Scanner lector = new Scanner(System.in);
	
	/** The p. */
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
	
	public static void realizarApuestaFija() {
		apuesta = getApuestaActual();
		apuestaActual= apuesta + getApuesta();
		dinero = dinero - apuesta;
	}

	/**
	 * Realizar apuesta.
	 *
	 * @param stage the stage
	 * @return the int
	 */
	public int realizarApuesta(int stage) {

		if(dinero == 0){
			return 0;
		}
		
		if(stage == 1) {
			String inApuesta = JOptionPane.showInputDialog(
					GUIPrincipal.vprincipal, 
			        "Cuanto desea apostar?",
			        "Ciega", 
			        JOptionPane.WARNING_MESSAGE
			    );

				if(inApuesta == null || (inApuesta != null && ("".equals(inApuesta))))   
				{
					JOptionPane.showMessageDialog(null, "Sin ciega no hay juego");
					realizarApuesta(1);
					//System.exit(1);
				}
			   
			   else {

					while(apuestaValida) {
						try {

							apuesta = Integer.parseInt(inApuesta);
							apuestaValida = false;
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Ingresa un valor valido");
							realizarApuesta(1);
						}
					}


				 
					 if(getApuesta() > dinero) {
						 JOptionPane.showMessageDialog(null, "no tienes el dinero suficiente");
						 realizarApuesta(1);
						
					 }
					 
					 if(getApuesta() <= dinero) {
						 dinero = dinero - getApuesta();
						 JOptionPane.showMessageDialog(null, "Apuestas " + getApuesta());
						 apuestaActual = getApuesta();
					 }	
			   }
     
		}
		
		if(stage == 2) {
			
			if(dinero >= apuestaActual) {
				
				String inApuesta = JOptionPane.showInputDialog(
						GUIPrincipal.vprincipal, 
				        "�Cuanto desea apostar?", 
				        "Ciega", 
				        JOptionPane.WARNING_MESSAGE
				    );
				
				
					if(inApuesta == null || (inApuesta != null && ("".equals(inApuesta))))   
					{
						
						JOptionPane.showMessageDialog(null, "Debes igualar o aumentar la apuesta actual");
						realizarApuesta(2);
						//System.exit(1);
					}
					
					if(Integer.parseInt(inApuesta) < apuestaActual) {
						
						JOptionPane.showMessageDialog(null, "Debes igualar o aumentar la apuesta actual");
						realizarApuesta(2);
						
					}
				   
				   else {
					   
					   apuesta = Integer.parseInt(inApuesta);
					   
					 
						 if(getApuesta() > dinero) {
							 JOptionPane.showMessageDialog(null, "no tienes el dinero suficiente, tu pierdes");
							 System.exit(1);
							
						 }
						 
						 if(getApuesta() <= dinero) {
							 apuestaActual = apuestaActual + getApuesta();
							 dinero = dinero - getApuesta();
							 JOptionPane.showMessageDialog(null, "Apuestas " + getApuesta());
						 }	
				   }
				
			}
			
			else JOptionPane.showMessageDialog(null, "no tienes dinero para seguir apostando, tu pierdes");
				// System.exit(1);
			
			
     
		
			
			}
		
		if(stage == 3) {
			
			if(dinero > apuestaActual) {
				
				String inApuesta = JOptionPane.showInputDialog(
						GUIPrincipal.vprincipal, 
				        "Ingrese su apuesta",
				        "Ciega", 
				        JOptionPane.WARNING_MESSAGE
				    );
				
				
					if(inApuesta == null || (inApuesta != null && ("".equals(inApuesta))))   
					{
						
						JOptionPane.showMessageDialog(null, "Debes aumentar la apuesta actual");
						realizarApuesta(3);
						//System.exit(1);
					}
					
					if(Integer.parseInt(inApuesta) <= apuestaActual) {
						
						JOptionPane.showMessageDialog(null, "Debes igualar o aumentar la apuesta actual");
						realizarApuesta(3);
						
					}
				   
				   else {
					   
					   apuesta = Integer.parseInt(inApuesta);
					   
					 
						 if(getApuesta() > dinero) {
							 JOptionPane.showMessageDialog(null, "no tienes el dinero suficiente, tu pierdes");
							 System.exit(1);
							
						 }
						 
						 if(getApuesta() <= dinero) {
							 apuestaActual = apuestaActual + getApuesta();
							 dinero = dinero - getApuesta();
							 JOptionPane.showMessageDialog(null, "Apuestas " + getApuesta());
						 }	
				   }
				
			}
			
			else {
				JOptionPane.showMessageDialog(null, "no tienes dinero para seguir apostando, tu pierdes");
				System.exit(1);
				}

			}

		return dinero;	
	}
	
	/**
	 * Apuesta pc.
	 *
	 * @param apuesta the apuesta
	 * @return the int
	 */
	public int apuestaPc(int apuesta) {
		
		if(apuesta > dineroPC) {
			
			JOptionPane.showMessageDialog(null, "El contrincante se ha quedado sin dinero, tu ganas");
			System.exit(1);

		}
		
		if(apuesta < dineroPC && apuesta <= 25000) {
			
			apuestaPC = (apuesta + apuesta);
			dineroPC = dineroPC - getApuestaPC();
			apuestaActual = apuesta + apuestaPC;
			JOptionPane.showMessageDialog(null, "Cortana apuesta " + getApuestaPC());
		}
		
		else {
			apuestaPC = apuesta+apuesta;
			dineroPC = dineroPC - apuestaPC;
			apuestaActual = apuesta + apuestaPC;
			JOptionPane.showMessageDialog(null, "Cortana apuesta " + getApuestaPC());
		}

		return dineroPC;
	}
		

	/**
	 * Gets the dinero.
	 *
	 * @return the dinero
	 */
	public static int getDinero() {
		return dinero;
	}

	/**
	 * Sets the dinero.
	 *
	 * @param dinero the new dinero
	 */
	public static void setDinero(int dinero) {
		Jugador.dinero = dinero;
	}


	/**
	 * Gets the dinero PC.
	 *
	 * @return the dinero PC
	 */
	public static int getDineroPC() {
		return dineroPC;
	}

	/**
	 * Sets the dinero PC.
	 *
	 * @param dineroPC the new dinero PC
	 */
	public static void setDineroPC(int dineroPC) {
		Jugador.dineroPC = dineroPC;
	}

	/**
	 * Turn cards.
	 *
	 * @param who the who
	 */
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

	/**
	 * Gets the apuesta.
	 *
	 * @return the apuesta
	 */
	public static int getApuesta() {
		return apuesta;
	}
	
	/**
	 * Gets the apuesta PC.
	 *
	 * @return the apuesta PC
	 */
	public int getApuestaPC() {
		return apuestaPC;
	}

	/**
	 * Gets the apuesta actual.
	 *
	 * @return the apuesta actual
	 */
	public static int getApuestaActual() {
		return apuestaActual;
	}

	

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public static void setUsername(String username) {
		Jugador.username = username;
	}
	
	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public static String getUsername() {
		return Jugador.username;
	}

	/**
	 * Sets the apuesta.
	 *
	 * @param apuesta the new apuesta
	 */
	public static void setApuesta(int apuesta) {
		Jugador.apuesta = apuesta;
	}

	/**
	 * Sets the apuesta actual.
	 *
	 * @param apuestaActual the new apuesta actual
	 */
	public static void setApuestaActual(int apuestaActual) {
		Jugador.apuestaActual = apuestaActual;
	}

	/**
	 * Sets the apuesta PC.
	 *
	 * @param apuestaPC the new apuesta PC
	 */
	public static void setApuestaPC(int apuestaPC) {
		Jugador.apuestaPC = apuestaPC;
	}
	
	

}
