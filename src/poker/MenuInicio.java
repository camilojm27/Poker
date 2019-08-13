package poker;

import Fonts.Fuentes;

import java.awt.*;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import javax.swing.*;
//import com.sun.glass.ui.Window;



public class MenuInicio extends JFrame {
	private Fuentes fuentes;
	private final Font bit8 = new Fuentes().fuente(Fuentes.BIT8, Font.BOLD, 18);
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

		private JTextFieldHint username;
		 private JButton start;
		 private JButton exit;
		 private JLabel titleBar;

		PanelInicio(){

			this.setLayout(null);
			this.setBackground(Color.lightGray);
			this.setPreferredSize(GUIPrincipal.sizeGame);
			username = new JTextFieldHint();
			//username.setFont();
			//username.setHint("Nombre de usuario");
			start = new JButton("START");
			exit = new JButton("EXIT");
			titleBar = new JLabel(new ImageIcon("src/imagenes/tittle-bar.gif"));
			initGUI();

		}

		private void initGUI() {

			Insets insets = this.getInsets();
			username.setBounds(70 + insets.left, 50  + insets.top,150,30);
			start.setBounds(100 + insets.left, 150  + insets.top,100,50);
			exit.setBounds(100 + insets.left, 350 + insets.top, 100,50);
			titleBar.setBounds(30 + insets.left, 0  + insets.top,254,57);
			this.add(start);
			this.add(exit);
			this.add(username);
			this.add(titleBar);
			username.setFont(bit8);

			username.setText("Username");
			username.setForeground(Color.WHITE);
			username.setOpaque(false);



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

						//JOptionPane.showMessageDialog(null, "WELCOME");


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
	private class JTextFieldHint extends JTextField  {

		private final Font fontLost = new Font("Monaco", Font.ITALIC, 10);
		private final Font fontGained = new Font("Monaco", Font.PLAIN, 12);
		private final Color colorLost = Color.LIGHT_GRAY;
		private final Color colorGained = Color.BLACK;
		private String hint;



	public JTextFieldHint(){
		//addFocusListener(this);
	}
	public void setHint(String hint){
		setForeground(colorLost);
		setFont(bit8);
		setText(this.hint);
		this.hint = this.hint;
	}

		public String getHint() {
			return hint;
		}

		/**@Override
		public void focusGained(FocusEvent e) {
			if (getText().equals(getHint())) {
				setText("");
				setFont(fontGained);
				setForeground(colorGained);
			} else {
				setForeground(colorGained);
				setFont(fontGained);
				setText(getText());
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			if (getText().length() <= 0) {
				setHint(getHint());
			} else {
				setForeground(colorGained);
				setFont(fontGained);
				setText(getText());
			}
		}



		*/
	}}












