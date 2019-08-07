package poker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class PanelCentral extends JPanel {
	
	PanelCentral(){
		
		this.setLayout(null);
		this.setBackground(Color.lightGray);
		this.setPreferredSize(GUIPrincipal.tamañoJuego);
		
		Insets insets = this.getInsets();
		
		JButton b1 = new JButton(new ImageIcon("/imagenes/AD.png"));
		this.add(b1);
		setSize(120,180);
		
		Dimension size = b1.getPreferredSize();
		b1.setBounds(25 + insets.left, 5 + insets.top,120,180);
		
		JButton b2 = new JButton("two");
		JButton b3 = new JButton("three");

		this.add(b2);
		this.add(b3);
		
		size = b2.getPreferredSize();
		b2.setBounds(55 + insets.left, 40 + insets.top,
		             size.width, size.height);
		size = b3.getPreferredSize();
		b3.setBounds(150 + insets.left, 15 + insets.top,
		             size.width + 50, size.height + 20);
	}
	
	 public void paintComponent(Graphics g){
	        super.paintComponent(g);
	        
	        Dimension height = getSize();
	        ImageIcon Img = new ImageIcon(getClass().getResource("/imagenes/BK2.png"));  
	    	g.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);
	    	setOpaque(false);
	    }

}
