package poker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class PanelCentral extends JPanel {
	
	private ArrayList<Carta> barajaPc;
    private ArrayList<Carta> barajaJugador = GUIPrincipal.getBarajaJugador();
    private int c1x, c2x, c1y, c2y;
    private JButton c1Ju, c2Ju, c1Pc, c2Pc, fJu, fPc;
	
	PanelCentral(){
		
		this.setLayout(null);
		this.setBackground(Color.lightGray);
		this.setPreferredSize(GUIPrincipal.tamañoJuego);
	
	}
	
	public void addCartas() {
		
		c1x = 180;
		c1y = 495;
		
		c2x= 545;
		c2y = 15;
		
		Insets insets = this.getInsets();
		
		c1Ju = new JButton();
		c1Pc = new JButton();
		
		Dimension size = c1Ju.getPreferredSize();
		c1Ju.setBounds(c1x + insets.left, c1y + insets.top,120,180);
		c1Ju.setIcon(GUIPrincipal.controlUnit.getBarajaJugador().get(0).getImagen());
		
		size = c1Pc.getPreferredSize();
		c1Pc.setBounds(c2x + insets.left, c2y + insets.top,
		             120,180);
		c1Pc.setIcon(GUIPrincipal.controlUnit.getBarajaJugador().get(1).getImagen());
		
		this.add(c1Ju);
		this.add(c1Pc);
	
	}
	
	public void addFichas() {
		
		
		
		Insets insets = this.getInsets();
		fJu = new JButton();
		fPc = new JButton();
		
		Dimension size = fJu.getPreferredSize();
		fJu.setBounds(20 + insets.left, 505 + insets.top,135,161);
		fJu.setIcon(new ImageIcon(getClass().getResource("/imagenes/fichas.png")));
		fJu.setBorderPainted(false);
		
		size = fPc.getPreferredSize();
		fPc.setBounds(840 + insets.left, 25 + insets.top,135,161);
		fPc.setIcon(new ImageIcon(getClass().getResource("/imagenes/fichas.png")));
		
		this.add(fJu);
		this.add(fPc);
		
		
		
	}
	
	 public void paintComponent(Graphics g){
	        super.paintComponent(g);
	        
	        Dimension height = getSize();
	        ImageIcon Img = new ImageIcon(getClass().getResource("/imagenes/Background_final.png"));  
	    	g.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);
	    	setOpaque(false);
	    	
	    }
/*
	static class mouseAction implements MouseListener{

		@Override
		public void mouseClicked(java.awt.event.MouseEvent arg0) {
				
			if(arg0.getSource() == b1) {
					
				JOptionPane.showMessageDialog(null, "Clicked START");
	
			}
				
			if(arg0.getSource() == b2) {
					
				JOptionPane.showMessageDialog(null, "Clicked EXIT");
			}

		}

		@Override
		public void mouseEntered(java.awt.event.MouseEvent arg0) {
			// TODO Auto-generated method stub	
		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent arg0) {
			// TODO Auto-generated method stub		
		}

		@Override
		public void mousePressed(java.awt.event.MouseEvent arg0) {
			// TODO Auto-generated method stub	
		}

		@Override
		public void mouseReleased(java.awt.event.MouseEvent arg0) {
			// TODO Auto-generated method stub		
		}	 
			}
			*/

}
