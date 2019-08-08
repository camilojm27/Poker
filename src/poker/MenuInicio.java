package poker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;

import java.awt.event.MouseListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.sun.glass.ui.Window;



public class MenuInicio extends JPanel {
	
	static private JButton b1;
	static private JButton b2;
	
	MenuInicio(){
		
		this.setLayout(null);
		this.setBackground(Color.lightGray);
		this.setPreferredSize(GUIPrincipal.tamañoJuego);
	
	}
	
	public void addButton() {
		
		Insets insets = this.getInsets();
		
		b1 = new JButton("START GAME");
		b2 = new JButton("EXIT");
		
		Dimension size = b1.getPreferredSize();
		b1.setBounds(500 + insets.left, 260  + insets.top,200,100);
		
		size = b2.getPreferredSize();
		b2.setBounds(500 + insets.left, 380 + insets.top,
		             200,100);

		this.add(b1);
		this.add(b2);
		
		b1.addMouseListener(new mouseAction());
		b2.addMouseListener(new mouseAction());
		
	}
	
	
	
	 public void paintComponent(Graphics g){
	        super.paintComponent(g);
	        
	        Dimension height = getSize();
	        ImageIcon Img = new ImageIcon(getClass().getResource("/imagenes/Background3.png"));  
	    	g.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);
	    	setOpaque(false);
	    	
	 }
	 

	static class mouseAction implements MouseListener{

		@Override
		public void mouseClicked(java.awt.event.MouseEvent arg0) {
			
			if(arg0.getSource() == b1) {
				
				JOptionPane.showMessageDialog(null, "Clicked START");
				GUIPrincipal.open();
				
				
				
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
		 
	 }
	 
	 
	 
	 
	 


