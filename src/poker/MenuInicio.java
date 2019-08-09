package poker;

import java.awt.*;


import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
//import com.sun.glass.ui.Window;



public class MenuInicio extends JFrame {

	private PanelInicio panelInicio;
	private Window window = this;
	private GUIPrincipal guiPrincipal;
	MenuInicio(){

		panelInicio = new PanelInicio();
		setResizable(false);
		setTitle("Bienvenido Al Poker");
		setSize(300, 500);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panelInicio.addButton();
		add(panelInicio);
	}

	private class PanelInicio extends JPanel {

		 private JButton b1;
		 private JButton b2;

		PanelInicio(){

			this.setLayout(null);
			this.setBackground(Color.lightGray);
			this.setPreferredSize(GUIPrincipal.sizeGame);

		}

		public void addButton() {

			Insets insets = this.getInsets();

			b1 = new JButton("START GAME");
			b2 = new JButton("EXIT GAME");

			Dimension size = b1.getPreferredSize();
			b1.setBounds(50 + insets.left, 150  + insets.top,200,50);

			size = b2.getPreferredSize();
			b2.setBounds(50 + insets.left, 350 + insets.top, 200,50);

			this.add(b1);
			this.add(b2);

			b1.addMouseListener(new mouseAction());
			b2.addMouseListener(new mouseAction());

		}



		public void paintComponent(Graphics g){
			super.paintComponent(g);

			Dimension height = getSize();
			ImageIcon Img = new ImageIcon(getClass().getResource("/imagenes/MenuInicio1.jpeg"));
			g.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);
			setOpaque(false);

		}


		class mouseAction implements MouseListener{

			@Override
			public void mouseClicked(java.awt.event.MouseEvent arg0) {

				if(arg0.getSource() == b1) {


					window.dispose();
					JOptionPane.showMessageDialog(null, "WELCOME");

					guiPrincipal = new GUIPrincipal();
				}

				if(arg0.getSource() == b2) {

					JOptionPane.showMessageDialog(null, "Bye bye");
					window.dispose();

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
}









