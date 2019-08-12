package poker;

import java.awt.*;

import java.awt.event.MouseListener;
import javax.swing.*;
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
		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



		add(panelInicio);
	}

	private class PanelInicio extends JPanel {

		private JTextField username;
		 private JButton start;
		 private JButton exit;
		 private JLabel titleBar;

		PanelInicio(){

			this.setLayout(null);
			this.setBackground(Color.lightGray);
			this.setPreferredSize(GUIPrincipal.sizeGame);
			username = new JTextField();
			start = new JButton("START");
			exit = new JButton("EXIT");
			initGUI();

		}

		private void initGUI() {

			Insets insets = this.getInsets();
			username.setBounds(70 + insets.left, 50  + insets.top,150,30);
			start.setBounds(100 + insets.left, 150  + insets.top,100,50);
			exit.setBounds(100 + insets.left, 350 + insets.top, 100,50);
			this.add(start);
			this.add(exit);
			this.add(username);
			username.setOpaque(true);


			start.addMouseListener(new mouseAction());
			exit.addMouseListener(new mouseAction());

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

				if(arg0.getSource() == start) {

					JOptionPane.showMessageDialog(null, "Clicked START");
					if (username.isValid()){
						window.dispose();
						JOptionPane.showMessageDialog(null, "WELCOME");

						guiPrincipal = new GUIPrincipal();
					}


				}

				if(arg0.getSource() == exit) {

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









