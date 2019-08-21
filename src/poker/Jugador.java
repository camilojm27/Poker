/*
 * INTEGRANTES:
 * CAMILO JOSE MEZU MINA = 1824313
 * SANTIAGO MARTINEZ MESA = 1823107
 */
package poker;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Jugador extends JOptionPane {


	static boolean apuestaValida = true;
	private static String username;
	private static int dinero = 50000;
	private static int apuesta = 0;
	private static int id,cordx, cordy;
	private static boolean plantarse;

	PanelCentral p = GUIPrincipal.getPanelCentral();
	static ControlUnit control = GUIPrincipal.controlUnit;
	static int apuestaActual = control.getApuestaActual();

	Jugador(){

		this.apuestaValida = true;
		this.dinero = 50000;
		this.apuesta = 0;
		this.cordx = 0;
		this.cordy = 0;
		this.plantarse = false;

	}

	boolean plantarse() {

		boolean estoyPlantado = true;
		return estoyPlantado;
	}
	
	public static void realizarApuestaFija() {
		apuesta = control.getApuestaActual();
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

		
	public static int getDinero() {
		return dinero;
	}

	public static void setDinero(int dinero) {
		Jugador.dinero = dinero;
	}

	public void turnCards(String who){
	    if(who.equals("player")){
	        p.p1c1.setIcon(new ImageIcon(getClass().getResource("/imagenes/RR.png")));
	        p.p1c2.setIcon(new ImageIcon(getClass().getResource("/imagenes/RR.png")));
		}
	    
	    if(who.equals("pNormal")) {
	    	
	    	p.p1c1.setIcon(GUIPrincipal.controlUnit.getBarajaJugador().get(0).getImagenRedi());
	    	p.p1c2.setIcon(GUIPrincipal.controlUnit.getBarajaJugador().get(1).getImagenRedi());
	    }
	}

	public static int getApuesta() {
		return apuesta;
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

}
