/*
 * INTEGRANTES:
 * CAMILO JOSE MEZU MINA = 1824313
 * SANTIAGO MARTINEZ MESA = 1823107
 */
package poker;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Jugador extends JOptionPane {


	private boolean apuestaValida = true;
	private static String username;
	//public static final int ID = Integer.parseInt(GUIPrincipal.getEntrada());
	private int dinero = 50000;
	private static int bote = 0;
	private int miApuesta;
	private int id,cordx, cordy;
	private boolean plantarse,winner;

	PanelCentral p = GUIPrincipal.getPanelCentral();
	static ControlUnit control = GUIPrincipal.controlUnit;
	static int apuestaActual = control.getApuestaActual();

	Jugador(){
		username = " ";
		this.apuestaValida = true;
		this.dinero = 50000;
		this.miApuesta = 0;
		this.cordx = 0;
		this.cordy = 0;
		this.plantarse = false;
		this.winner = false;

	}
	
	public static void resetStats(Jugador jugador) {
		jugador.apuestaValida = true;
		jugador.miApuesta = 0;
		jugador.plantarse = false;
		jugador.winner = false;
	}

	boolean plantarse(Jugador jugador) {

		jugador.plantarse = true;
		return plantarse;
	}

	public static void realizarApuestaFija(Jugador jugador) {
		bote = ControlUnit.getApuestaActual();
		apuestaActual= bote + getBote();
		Jugador.setDinero(jugador.dinero - bote, jugador);
	}

	public int realizarApuesta(int stage,Jugador jugador) {

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
					realizarApuesta(1,jugador);
					//System.exit(1);
				}
			   
			   else {

					while(apuestaValida) {
						try {

							bote = Integer.parseInt(inApuesta);
							apuestaValida = false;
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Ingresa un valor valido");
							realizarApuesta(1,jugador);
						}
					}


				 
					 if(getBote() > dinero) {
						 JOptionPane.showMessageDialog(null, "no tienes el dinero suficiente");
						 realizarApuesta(1,jugador);
						
					 }
					 
					 if(getBote() <= dinero) {
						 dinero = dinero - getBote();
						 JOptionPane.showMessageDialog(null, "Apuestas " + getBote());
						 apuestaActual = getBote();
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
						realizarApuesta(2,jugador);
						//System.exit(1);
					}
					
					if(Integer.parseInt(inApuesta) < apuestaActual) {
						
						JOptionPane.showMessageDialog(null, "Debes igualar o aumentar la apuesta actual");
						realizarApuesta(2,jugador);
						
					}
				   
				   else {
					   
					   bote = Integer.parseInt(inApuesta);
					   
					 
						 if(getBote() > dinero) {
							 JOptionPane.showMessageDialog(null, "no tienes el dinero suficiente, tu pierdes");
							 System.exit(1);
							
						 }
						 
						 if(getBote() <= dinero) {
							 apuestaActual = apuestaActual + getBote();
							 dinero = dinero - getBote();
							 JOptionPane.showMessageDialog(null, "Apuestas " + getBote());
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
						realizarApuesta(3,jugador);
						//System.exit(1);
					}
					
					if(Integer.parseInt(inApuesta) <= apuestaActual) {
						
						JOptionPane.showMessageDialog(null, "Debes igualar o aumentar la apuesta actual");
						realizarApuesta(3,jugador);
						
					}
				   
				   else {
					   
					   bote = Integer.parseInt(inApuesta);
					   
					 
						 if(getBote() > dinero) {
							 JOptionPane.showMessageDialog(null, "no tienes el dinero suficiente, tu pierdes");
							 System.exit(1);
							
						 }
						 
						 if(getBote() <= dinero) {
							 apuestaActual = apuestaActual + getBote();
							 dinero = dinero - getBote();
							 JOptionPane.showMessageDialog(null, "Apuestas " + getBote());
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

		
	public static int getDinero(Jugador jugador) {
		return jugador.dinero;
	}

	public static void setDinero(int dinero,Jugador jugador) {
		jugador.dinero = dinero;
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

	public static int getBote() {
		return bote;
	}

	public static void setUsername(String nombre) {
		username = nombre;
	}

	public static String getUsername() {
		return username;
	}

	public static void setApuesta(int apuesta) {
		bote = apuesta;
	}

}
