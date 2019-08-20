/*
 * INTEGRANTES:
 * CAMILO JOSE MEZU MINA = 1824313
 * SANTIAGO MARTINEZ MESA = 1823107
 */
package poker;

import Fonts.Fuentes;

import java.awt.*;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.*;
// TODO: Auto-generated Javadoc
//import com.sun.glass.ui.Window;



/**
 * The Class MenuInicio.
 */
public class MenuInicio extends JFrame {
	
	/** The fuentes. */
	private Fuentes fuentes;
	
	/** The bit 8. */
	private final Font bit8 = new Fuentes().fuente(Fuentes.BIT8, Font.BOLD, 18);
	
	/** The panel inicio. */
	private PanelInicio panelInicio;
	
	/** The window. */
	private Window window = this;
	
	/** The gui principal. */
	private GUIPrincipal guiPrincipal;
	
	/**
	 * Instantiates a new menu inicio.
	 */
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

	/**
	 * The Class PanelInicio.
	 */
	private class PanelInicio extends JPanel {

		/** The username. */
		private JTextFieldHint username;
		 
 		/** The start. */
 		private JButton start;
		 
 		/** The exit. */
 		private JButton exit;
		 
 		/** The title bar. */
 		private JLabel titleBar;
		 
 		/** The random. */
 		private Random random  = new Random();;
		 
 		/** The sonidos. */
 		private Sonidos sonidos;

		/**
		 * Instantiates a new panel inicio.
		 */
		PanelInicio(){
            //sonidos = new Sonidos(Sonidos.firstSong);
			this.setLayout(null);
			this.setBackground(Color.lightGray);
			this.setPreferredSize(GUIPrincipal.sizeGame);

			initGUI();

		}

		/**
		 * Inits the GUI.
		 */
		private void initGUI() {

			username = new JTextFieldHint();
			//username.setFont();
			//username.setHint("Nombre de usuario");
			start = new JButton("START");
			exit = new JButton("EXIT");
			titleBar = new JLabel(new ImageIcon("src/imagenes/tittle-bar.gif"));

			Insets insets = this.getInsets();
			username.setBounds(70 + insets.left, 270  + insets.top,160,30);
			start.setBounds(100 + insets.left, 150  + insets.top,100,50);
			exit.setBounds(100 + insets.left, 320 + insets.top, 100,50);
			titleBar.setBounds(40 + insets.left, 3  + insets.top,220,37);
			start.setBackground(Color.white);
			exit.setBackground(Color.white);
			this.add(start);
			this.add(exit);
			this.add(username);
			this.add(titleBar);
			username.setFont(bit8);
			titleBar.setBackground(Color.BLACK);

			username.setText("Username");
			username.setForeground(Color.black);
			username.setOpaque(true);



			start.addMouseListener(new mouseAction());
			exit.addMouseListener(new mouseAction());

		}



		/**
		 * Paint component.
		 *
		 * @param g the g
		 */
		public void paintComponent(Graphics g){
			super.paintComponent(g);

			Dimension height = getSize();
			ImageIcon Img = new ImageIcon(getClass().getResource("/imagenes/MenuInicio" +  random.nextInt(5) + ".jpeg"));
			g.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);
			setOpaque(false);

		}


		/**
		 * The Class mouseAction.
		 */
		class mouseAction implements MouseListener{

			/**
			 * Mouse clicked.
			 *
			 * @param arg0 the arg 0
			 */
			@Override
			public void mouseClicked(java.awt.event.MouseEvent arg0) {

				if(arg0.getSource() == start) {
				//	sonidos.stop();
				//	JOptionPane.showMessageDialog(null, "Clicked START");
					if (username.isValid()){
                        Jugador.setUsername(username.getText());
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

			/**
			 * Mouse entered.
			 *
			 * @param arg0 the arg 0
			 */
			@Override
			public void mouseEntered(java.awt.event.MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			/**
			 * Mouse exited.
			 *
			 * @param arg0 the arg 0
			 */
			@Override
			public void mouseExited(java.awt.event.MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			/**
			 * Mouse pressed.
			 *
			 * @param arg0 the arg 0
			 */
			@Override
			public void mousePressed(java.awt.event.MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			/**
			 * Mouse released.
			 *
			 * @param arg0 the arg 0
			 */
			@Override
			public void mouseReleased(java.awt.event.MouseEvent arg0) {
				// TODO Auto-generated method stub

			}




		}

	}
	
	/**
	 * The Class JTextFieldHint.
	 */
	private class JTextFieldHint extends JTextField  {

		/** The font lost. */
		private final Font fontLost = new Font("Monaco", Font.ITALIC, 10);
		
		/** The font gained. */
		private final Font fontGained = new Font("Monaco", Font.PLAIN, 12);
		
		/** The color lost. */
		private final Color colorLost = Color.LIGHT_GRAY;
		
		/** The color gained. */
		private final Color colorGained = Color.BLACK;
		
		/** The hint. */
		private String hint;



	/**
	 * Instantiates a new j text field hint.
	 */
	public JTextFieldHint(){
		//addFocusListener(this);
	}
	
	/**
	 * Sets the hint.
	 *
	 * @param hint the new hint
	 */
	public void setHint(String hint){
		setForeground(colorLost);
		setFont(bit8);
		setText(this.hint);
		this.hint = this.hint;
	}

		/**
		 * Gets the hint.
		 *
		 * @return the hint
		 */
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












