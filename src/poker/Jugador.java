package poker;

import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Jugador extends JOptionPane {

	/**
	 * The apuesta valida.
	 */
	static boolean apuestaValida = true;
	private static String username;



	/** The dinero. */
	private static int dinero = 50000;
	
	private static int dineroPC = 50000;
	
	/** The apuesta. */
	private static int apuesta = 0;
	private static int apuestaActual= 0;
	private static int apuestaPC = 0;
	
	/** The plantarse. */

	private static boolean plantarseJugador, plantarsePC;
	
	/** The apuesta valida. */
	

	private static boolean plantarse;
	/**
	 * The lector.
	 */
	private Scanner lector = new Scanner(System.in);
	
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
			apuestaPC = apuesta+1000;
			dineroPC = dineroPC - apuestaPC;
			apuestaActual = apuesta + apuestaPC;
			JOptionPane.showMessageDialog(null, "Cortana apuesta " + getApuestaPC());
		}

		return dineroPC;
	}
		

	public int getDinero() {
		return dinero;
	}

	public static void setDinero(int dinero) {
		Jugador.dinero = dinero;
	}


	public static int getDineroPC() {
		return dineroPC;
	}

	public static void setDineroPC(int dineroPC) {
		Jugador.dineroPC = dineroPC;
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

	public static int getApuesta() {
		return apuesta;
	}
	
	public int getApuestaPC() {
		return apuestaPC;
	}

	public static int getApuestaActual() {
		return apuestaActual;
	}

	

	public static void setUsername(String username) {
		Jugador.username = username;
	}
	public static String getUsername() {
		return Jugador.username;
	}

	public static void setApuesta(int apuesta) {
		Jugador.apuesta = apuesta;
	}

	public static void setApuestaActual(int apuestaActual) {
		Jugador.apuestaActual = apuestaActual;
	}

	public static void setApuestaPC(int apuestaPC) {
		Jugador.apuestaPC = apuestaPC;
	}
	
	

}
