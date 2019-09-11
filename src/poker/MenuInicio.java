/*
 * INTEGRANTES:
 * CAMILO JOSE MEZU MINA = 1824313
 * SANTIAGO MARTINEZ MESA = 1823107
 */
package poker;

import Fonts.Fuentes;
import Servidor.Servidor;

import java.awt.*;

import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.*;

public class MenuInicio extends JFrame {

	private Fuentes fuentes;
	private final Font bit8 = new Fuentes().fuente(Fuentes.BIT8, Font.BOLD, 18);
	private final Font bit8Peque = new Fuentes().fuente(Fuentes.BIT8, Font.BOLD, 10);
	private PanelInicio panelInicio;
	private Window window = this;
	private GUIPrincipal guiPrincipal;
	private static int numPlayers;
	private Jugador jugadorActual;

	MenuInicio(){

		panelInicio = new PanelInicio();
		setResizable(false);
		setTitle("Bienvenido Al Poker");
		setSize(300, 500);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jugadorActual = GUIPrincipal.getJugador();

		add(panelInicio);
	}

	public static int getNumPlayers() {
		return numPlayers;
	}


	private class PanelInicio extends JPanel {

		private JTextFieldHint username;
 		private JButton start;
 		private JButton exit;
 		private JLabel titleBar;
 		private Random random  = new Random();;
 		private Sonidos sonidos;
 		//private JCheckBox uniseaPartida, crearPartida;

		PanelInicio(){
            //sonidos = new Sonidos(Sonidos.firstSong);
			this.setLayout(null);
			this.setBackground(Color.lightGray);
			this.setPreferredSize(GUIPrincipal.sizeGame);

			initGUI();

		}

		private void initGUI() {

			titleBar = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("imagenes/tittle-bar.gif")));
			username = new JTextFieldHint();
			start = new JButton("START");
			exit = new JButton("EXIT");
			//uniseaPartida = new JCheckBox("Unirse A partida");
			//crearPartida = new JCheckBox("Crear partida");

			Insets insets = this.getInsets();
			username.setBounds(70 + insets.left, 250  + insets.top,160,30);
			//users.setBounds(100 + insets.left, 280  + insets.top,100,30);
			start.setBounds(100 + insets.left, 150  + insets.top,100,50);
			exit.setBounds(100 + insets.left, 320 + insets.top, 100,50);
			titleBar.setBounds(40 + insets.left, 3  + insets.top,220,37);
			//uniseaPartida.setBounds(20 + insets.left, 380  + insets.top,200,20);
			//crearPartida.setBounds(20 + insets.left, 400  + insets.top,200,20);

			start.setBackground(Color.white);
			exit.setBackground(Color.white);
			this.add(start);
			this.add(exit);
			this.add(username);
			//this.add(users);
			this.add(titleBar);
			//this.add(uniseaPartida);
			//this.add(crearPartida);
			username.setFont(bit8);

			//uniseaPartida.setFont(bit8Peque);
			//crearPartida.setFont(bit8Peque);
			titleBar.setBackground(Color.BLACK);


			username.setText("Username");
			username.setForeground(Color.black);
			username.setOpaque(true);


			start.addMouseListener(new mouseAction());
			exit.addMouseListener(new mouseAction());

		}

		public void paintComponent(Graphics g){
			super.paintComponent(g);

			Dimension height = getSize();
			ImageIcon Img = new ImageIcon(getClass().getResource("/imagenes/MenuInicio" +  random.nextInt(5) + ".jpeg"));
			g.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);
			setOpaque(false);

		}


	private class mouseAction implements MouseListener{



			@Override
			public void mouseClicked(java.awt.event.MouseEvent arg0) {
				JComboBox users = new JComboBox();

				users.addItem("2"); users.addItem("3");
				users.addItem("4"); users.addItem("5"); users.addItem("6");
				users.setForeground(Color.black);
				users.setBackground(Color.WHITE);
				users.setFont(bit8);

				if(arg0.getSource() == start) {

					numPlayers = users.getSelectedIndex();
				//	sonidos.stop();
				//	JOptionPane.showMessageDialog(null, "Clicked START");
					if (username.isValid()){
                        Jugador.setUsername(username.getText());
                        System.out.println(Jugador.getUsername());


						int modoDeJUego = JOptionPane.showOptionDialog(null, "Indique el modo de juego, de ser miltijugador indique la cantidad", "Modo de juego", JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, new Object[] {users, "Crear partida", "Unirse a partida", "Jugar 1vs1 contra cortana"}, "Crear partida");

						ControlUnit.setCantidadJugadores(users.getSelectedIndex()  + 2);//Test
						System.out.println(ControlUnit.getCantidadJugadores());
						if(modoDeJUego == 1 && ControlUnit.getCantidadJugadores() != 0){
							EventQueue.invokeLater(new Runnable() {public void run() {

								Servidor servidor = new Servidor();


							}});
						}


						window.dispose();
						guiPrincipal = new GUIPrincipal("127.0.0.1");

						ControlUnit.setCantidadJugadores(users.getSelectedIndex());
						System.out.println(ControlUnit.getCantidadJugadores());

						guiPrincipal.setVisible(true);
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












