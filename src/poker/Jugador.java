/*
 * INTEGRANTES:
 * CAMILO JOSE MEZU MINA = 1824313
 * SANTIAGO MARTINEZ MESA = 1823107
 */
package poker;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.io.IOException;


public class Jugador extends JOptionPane {


	private static boolean apuestaValida = true;
	private static String username;
	private static int ID;

	private static int cordx, cordy;

	private static int dinero = 50000;

	private static int bote = 0;
	private static int miApuesta;
	private static boolean plantarse;
	PanelCentral p = GUIPrincipal.getPanelCentral();

	static ControlUnit control = GUIPrincipal.controlUnit;
	static int apuestaActual = control.getApuestaActual();
	Jugador(){
		this.apuestaValida = true;
		this.dinero = 50000;
		this.miApuesta = 0;
		this.plantarse = false;

	}

	public static void resetStats() {
		apuestaValida = true;
		miApuesta = 0;
		plantarse = false;
	}

	boolean plantarse() {

		plantarse = true;
		return plantarse;
	}

	public static void realizarApuestaFija() {
		bote = ControlUnit.getApuestaActual();
		apuestaActual= bote + getBote();
		setDinero(dinero - bote);
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

							bote = Integer.parseInt(inApuesta);
							apuestaValida = false;
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Ingresa un valor valido");
							realizarApuesta(1);
						}
					}



					 if(getBote() > dinero) {
						 JOptionPane.showMessageDialog(null, "no tienes el dinero suficiente");
						 realizarApuesta(1);

					 }

					 if(getBote() <= dinero) {
						 dinero = dinero - getBote();
						 JOptionPane.showMessageDialog(null, "Apuestas " + getBote());
						 apuestaActual = getBote();
						 try {
							 GUIPrincipal.salida.writeInt(dinero);
							 GUIPrincipal.salida.flush();
							 GUIPrincipal.salida.writeInt(apuestaActual);
							 GUIPrincipal.salida.flush();
							 //Se le da el turno al otro jugadorM;
							 GUIPrincipal.salida.writeObject(Jugador.getUsername());
							 GUIPrincipal.salida.flush();


						 } catch (IOException e) {
							 e.printStackTrace();
						 }

					 }
			   }
			GUIPrincipal.panelCentral.removeAL();
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
						realizarApuesta(3);
						//System.exit(1);
					}

					if(Integer.parseInt(inApuesta) <= apuestaActual) {

						JOptionPane.showMessageDialog(null, "Debes igualar o aumentar la apuesta actual");
						realizarApuesta(3);

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


	public static int getDinero() {
		return dinero;
	}

	public static void setDinero(int amount) {

		dinero = amount;
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

	public static int getID() {
		return ID;
	}

	public static void setID(int ID) {
		Jugador.ID = ID;
	}

	public static int getCordx() {
		return cordx;
	}

	public static void setCordx(int cordx) {
		Jugador.cordx = cordx;
	}

	public static int getCordy() {
		return cordy;
	}

	public static void setCordy(int cordy) {
		Jugador.cordy = cordy;
	}

}
