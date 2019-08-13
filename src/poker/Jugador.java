package poker;

import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Jugador extends JOptionPane {
	
	/** The lector. */
	Scanner lector = new Scanner(System.in);
	
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
	public int realizarApuesta(int stage) {
		
		if(stage == 1) {
			String inApuesta = JOptionPane.showInputDialog(
					GUIPrincipal.vprincipal, 
			        "¿Cuanto desea apostar?", 
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
				   
				   apuesta = Integer.parseInt(inApuesta);
				   
				 
					 if(getApuesta() > dinero) {
						 JOptionPane.showMessageDialog(null, "no tienes el dinero suficiente");
						 realizarApuesta(1);
						
					 }
					 
					 if(getApuesta() < dinero) {
						 dinero = dinero - getApuesta();
						 JOptionPane.showMessageDialog(null, "tu dinero es " + dinero);
						 apuestaActual = getApuesta();
					 }	
			   }
     
		}
		
		if(stage == 2) {
			
			if(dinero >= apuestaActual) {
				
				String inApuesta = JOptionPane.showInputDialog(
						GUIPrincipal.vprincipal, 
				        "¿Cuanto desea apostar?", 
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
						 
						 if(getApuesta() < dinero) {
							 apuestaActual = apuestaActual + getApuesta();
							 dinero = dinero - getApuesta();
							 JOptionPane.showMessageDialog(null, "tu dinero es " + dinero);
						 }	
				   }
				
			}
			
			else JOptionPane.showMessageDialog(null, "no tienes dinero para seguir apostando, tu pierdes");
				// System.exit(1);
			
			
     
		
			
			}
		
		
		return dinero;	
	}
	
	public int apuestaPc(int apuestaPrevia) {
		
		if(apuestaPrevia < dineroPC) {
			
			apuestaPC = (2*apuestaPrevia);
			dineroPC = dineroPC - (2*apuestaPrevia);
			apuestaActual = apuestaActual + apuestaPC;
			
			
		}
		
		if(apuestaPrevia > dineroPC) {
			
			JOptionPane.showMessageDialog(null, "El contrincante se ha quedado sin dinero, tu ganas");
			System.exit(1);
		
			
		}
		
		return dineroPC;
		
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

	public int getApuesta() {
		return apuesta;
	}
	
	public int getApuestaPC() {
		return apuestaPC;
	}

	public static int getApuestaActual() {
		return apuestaActual;
	}
	
	

}
