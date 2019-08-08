package poker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class PanelCentral extends JPanel {
	
	private ArrayList<Carta> barajaPc;
    private ArrayList<Carta> barajaJugador = GUIPrincipal.getBarajaJugador();
	
	PanelCentral(){
		
		this.setLayout(null);
		this.setBackground(Color.lightGray);
		this.setPreferredSize(GUIPrincipal.tamañoJuego);
	
	}
	
	public void addButton() {
		
		Insets insets = this.getInsets();
		
		JButton b1 = new JButton();
		JButton b2 = new JButton();
		
		Dimension size = b1.getPreferredSize();
		b1.setBounds(0 + insets.left, 0 + insets.top,120,180);
		b1.setIcon(GUIPrincipal.controlUnit.getBarajaJugador().get(0).getImagen());
		
		size = b2.getPreferredSize();
		b2.setBounds(0+120+10 + insets.left, 0 + insets.top,
		             120,180);
		b2.setIcon(GUIPrincipal.controlUnit.getBarajaJugador().get(1).getImagen());
		
		
		this.add(b1);
		this.add(b2);
	
	}
	
	 public void paintComponent(Graphics g){
	        super.paintComponent(g);
	        
	        Dimension height = getSize();
	        ImageIcon Img = new ImageIcon(getClass().getResource("/imagenes/BK2.png"));  
	    	g.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);
	    	setOpaque(false);
	    	
	    }

}
