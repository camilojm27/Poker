/*
 * INTEGRANTES:
 * CAMILO JOSE MEZU MINA = 1824313
 * SANTIAGO MARTINEZ MESA = 1823107
 */
package poker;

import Fonts.Fuentes;
import java.awt.*;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class PanelCentral extends JPanel {
	
	private JLabel username , player2 = new JLabel("Cortana");
	private int ccx,ccy, c1pcx,c1pcy, c2pcx, c2pcy,csizex,csizey,tempx,tempy;
	private int numPlayers = MenuInicio.getNumPlayers();
	public static JButton p1c1, p1c2,p2c1,p2c2,p3c1,p3c2,
						  c1Pc, c2Pc,p4c1,p4c2,p5c1,p5c2,ptc1,ptc2;
	private static JButton pasar;
	private static JButton subir;
	private static JButton igualar;
	private static JButton jugar;
	private static JButton giveup;
	private final Font bit8 = new Fuentes().fuente(Fuentes.BIT8, Font.BOLD, 8);
	private final Font decored2 = new Fuentes().fuente(Fuentes.DECORED2, Font.BOLD, 24);
	private final Font decored = new Fuentes().fuente(Fuentes.DECORED, Font.BOLD, 24);
	private final Font ibmRegularItalic = new Fuentes().fuente(Fuentes.IBM_REGULAR_ITALIC, Font.PLAIN, 14);


	private JButton cc1;
	private JButton cc2;
	private JButton cc3;
	private JButton cc4;
	private JButton cc5;

	private JTextArea informacion;
	
	MouseAction mouse = new MouseAction();
	private static Jugador jugador = new Jugador();
	private static Pc pc = new Pc();

	public static JPanel pcentral;

	PanelCentral(){
		pcentral = this;
		this.setLayout(null);
		this.setBackground(Color.lightGray);
		this.setPreferredSize(GUIPrincipal.sizeGame);



	}

	public void infoPanelCentral(){

		Insets insets = this.getInsets();
		informacion = new JTextArea("Bienvenido " + Jugador.getUsername() +  "\nEres el jugador #"  + Jugador.getID()  );
		informacion.setEditable(false);
		informacion.setBounds(950 + insets.left, 550 + insets.top,200,120);
		informacion.setFont(ibmRegularItalic);
		player2.setFont(decored2);
		player2.setBounds(20 + insets.left, 140 + insets.top,420,161);
		player2.setForeground(Color.CYAN);
		add(player2);
		add(informacion);
		informacion.setVisible(true);
	}

	public void turnCards(String who){
	    if(who.equals("player")){
	        p1c1.setIcon(new ImageIcon(getClass().getResource("/imagenes/RR.png")));
			p1c2.setIcon(new ImageIcon(getClass().getResource("/imagenes/RR.png")));
        }
	    
	    if(who.equals("pNormal")) {
	    	
	    	p1c1.setIcon(GUIPrincipal.controlUnit.getBarajaJugador().get(0).getImagenRedi());
			p1c2.setIcon(GUIPrincipal.controlUnit.getBarajaJugador().get(1).getImagenRedi());
	    }
	    
	    if(who.equals("pc")) {
	    	
	    	c1Pc.setIcon(new ImageIcon(getClass().getResource("/imagenes/RR.png")));
	    	c2Pc.setIcon(new ImageIcon(getClass().getResource("/imagenes/RR.png")));
	    	
	    }
	    
	    if(who.equals("cNormal")) {
	    	
	    	c1Pc.setIcon(GUIPrincipal.controlUnit.getBarajaPc().get(0).getImagenRedi());
	    	c2Pc.setIcon(GUIPrincipal.controlUnit.getBarajaPc().get(1).getImagenRedi());
	    }
    }

	public void repartirCartas() {

			csizex = 110;
			csizey = 158;

			int c1x = Jugador.getCordx();
			int c1y = Jugador.getCordy();
			Insets insets = this.getInsets();

			p1c1 = new JButton();
			Dimension size = p1c1.getPreferredSize();
			p1c1.setBounds(c1x + insets.left, c1y + insets.top,csizex,csizey);
			p1c1.setIcon(GUIPrincipal.controlUnit.getBarajaJugador().get(0).getImagenRedi());
			p1c1.addMouseListener(mouse);
			this.add(p1c1);
			///////////////////////////////////////////////////////////////////////////////////

			int c2x= c1x+134;
			int c2y = c1y;

			p1c2 = new JButton();
			size = p1c2.getPreferredSize();
			p1c2.setBounds(c2x + insets.left, c2y + insets.top, csizex,csizey);
			p1c2.setIcon(GUIPrincipal.controlUnit.getBarajaJugador().get(1).getImagenRedi());
			p1c2.addMouseListener(mouse);
			this.add(p1c2);

	}

	public void repartirOthers() {

		Insets insets = this.getInsets();

		if(Jugador.getID() != 1){
			setCords(291,523);
			ptc1 = new JButton(); ptc2 = new JButton();
			ptc1.setBounds(tempx + insets.left, tempy+ insets.top, csizex,csizey);
			ptc2.setBounds(tempx+134 + insets.left, tempy + insets.top, csizex,csizey);
			ptc1.setIcon(new ImageIcon(getClass().getResource("/imagenes/RR.png")));
			ptc2.setIcon(new ImageIcon(getClass().getResource("/imagenes/RR.png")));
			this.add(ptc1);
			this.add(ptc2);

		}


		setCords(640,523);
		p2c1 = new JButton(); p2c2 = new JButton();
		p2c1.setBounds(tempx + insets.left, tempy+ insets.top, csizex,csizey);
		p2c2.setBounds(tempx+134 + insets.left, tempy + insets.top, csizex,csizey);
		p2c1.setIcon(new ImageIcon(getClass().getResource("/imagenes/RR.png")));
		p2c2.setIcon(new ImageIcon(getClass().getResource("/imagenes/RR.png")));
		this.add(p2c1);
		this.add(p2c2);

		setCords(940,260);
		p3c1 = new JButton(); p3c2 = new JButton();
		p3c1.setBounds(tempx + insets.left, tempy+ insets.top, csizex,csizey);
		p3c2.setBounds(tempx+134 + insets.left, tempy + insets.top, csizex,csizey);
		p3c1.setIcon(new ImageIcon(getClass().getResource("/imagenes/RR.png")));
		p3c2.setIcon(new ImageIcon(getClass().getResource("/imagenes/RR.png")));
		this.add(p3c1);
		this.add(p3c2);

		setCords(640,12);
		p4c1 = new JButton(); p4c2 = new JButton();
		p4c1.setBounds(tempx + insets.left, tempy+ insets.top, csizex,csizey);
		p4c2.setBounds(tempx+134 + insets.left, tempy + insets.top, csizex,csizey);
		p4c1.setIcon(new ImageIcon(getClass().getResource("/imagenes/RR.png")));
		p4c2.setIcon(new ImageIcon(getClass().getResource("/imagenes/RR.png")));
		this.add(p4c1);
		this.add(p4c2);

		setCords(291,12);
		p5c1 = new JButton(); p5c2 = new JButton();
		p5c1.setBounds(tempx + insets.left, tempy+ insets.top, csizex,csizey);
		p5c2.setBounds(tempx+134 + insets.left, tempy + insets.top, csizex,csizey);
		p5c1.setIcon(new ImageIcon(getClass().getResource("/imagenes/RR.png")));
		p5c2.setIcon(new ImageIcon(getClass().getResource("/imagenes/RR.png")));
		this.add(p5c1);
		this.add(p5c2);







	}

	public void setCords(int wichx, int wichy){
		tempx = wichx;
		tempy = wichy;

	}

	public void addCartasPC() {

		csizex = 110;
		csizey = 158;

		c1pcx = 15;
		c1pcy = 260;
		////////////////////////////////////////
		c2pcx= c1pcx + 135;
		c2pcy = c1pcy;
        ///////////////////////////////////////
		Insets insets = this.getInsets();

		c1Pc = new JButton();
		Dimension size = c1Pc.getPreferredSize();
		c1Pc.setBounds(c1pcx + insets.left, c1pcy + insets.top,csizex,csizey);
		c1Pc.setIcon(GUIPrincipal.controlUnit.getBarajaPc().get(0).getImagenRedi());
        this.add(c1Pc);
		///////////////////////////////////////////////////////////////////////////////////
        c2Pc = new JButton();
		size = c2Pc.getPreferredSize();
		c2Pc.setBounds(c2pcx + insets.left, c2pcy + insets.top, csizex,csizey);
		c2Pc.setIcon(GUIPrincipal.controlUnit.getBarajaPc().get(1).getImagenRedi());
		this.add(c2Pc);
		///////////////////////////////////////////////////////////////////////////////////
	}

	public void addCartasComunitarias(){

		csizex = 111;
		csizey = 158;

	    int cas = 125;
        ccx = 350;
        ccy = 260;
        //////////////////////////////////
        Insets insets = this.getInsets();
        cc1 = new JButton();
        Dimension size = cc1.getPreferredSize();
        cc1.setBounds(ccx + insets.left, ccy + insets.top,csizex,csizey);
        cc1.setIcon(GUIPrincipal.controlUnit.getCartasComunitarias().get(0).getImagenRedi());
        this.add(cc1);
        //////////////////////////////////////////////////////////////////////////////////
        cc2 = new JButton();
        size = cc2.getPreferredSize();
        cc2.setBounds(ccx+cas + insets.left, ccy + insets.top,csizex,csizey);
        cc2.setIcon(GUIPrincipal.controlUnit.getCartasComunitarias().get(1).getImagenRedi());
        this.add(cc2);
        ///////////////////////////////////////////////////////////////////////////////////
        cc3 = new JButton();
        size = cc3.getPreferredSize();
        cc3.setBounds(ccx+(cas*2) + insets.left, ccy + insets.top,csizex,csizey);
        cc3.setIcon(GUIPrincipal.controlUnit.getCartasComunitarias().get(2).getImagenRedi());
        this.add(cc3);
		////////////////////////////////////////////////////////////////////////////////////
		cc4 = new JButton();
		size = cc4.getPreferredSize();
		cc4.setBounds(ccx+(cas*3) + insets.left, ccy + insets.top,csizex,csizey);
		cc4.setIcon(GUIPrincipal.controlUnit.getCartasComunitarias().get(3).getImagenRedi());
		this.add(cc4);
		/////////////////////////////////////////////////////////////////////////////////////
		cc5 = new JButton();
		size = cc5.getPreferredSize();
		cc5.setBounds(ccx+(cas*4) + insets.left, ccy + insets.top,csizex,csizey);
		cc5.setIcon(GUIPrincipal.controlUnit.getCartasComunitarias().get(4).getImagenRedi());
		this.add(cc5);
		cc1.setVisible(false);
		cc2.setVisible(false);
		cc3.setVisible(false);
		cc4.setVisible(false);
		cc5.setVisible(false);
       

    }

    public void showNextCard(int cual){
		if(cual == 1){cc1.setVisible(true);}
		if(cual == 2){cc2.setVisible(true);}
		if(cual == 3){cc3.setVisible(true);}
		if(cual == 4){cc4.setVisible(true);}
		if(cual == 5){cc5.setVisible(true);}
	}

	public void removeCartasComunitarias(){

		this.remove(cc1);
		this.remove(cc2);
		this.remove(cc3);
		this.remove(cc4);
		this.remove(cc5);
		this.updateUI();

	}

	public static Jugador getJugador() {
		return jugador;
	}

	public void addButtons(){

        Insets insets = this.getInsets();

        pasar = new JButton("PASAR");
        igualar = new JButton("IGUALAR");
        subir = new JButton("SUBIR");
        jugar = new JButton("JUGAR");
        giveup = new JButton("RENDIRSE");

        Dimension size = pasar.getPreferredSize();
        pasar.setBounds(1090+ insets.left, 105+ insets.top, 100,40);
        // jugar .setIcon(GUIPrincipal.controlUnit.getBarajaJugador().get(1).getImagen());

        size = igualar.getPreferredSize();
        igualar.setBounds(	1090 + insets.left,  55 + insets.top,100,40);
        //apostar.setIcon(new ImageIcon(getClass().getResource("/imagenes/bill.png")));

        size = subir.getPreferredSize();
        subir.setBounds(1090 + insets.left,  5 + insets.top,100,40);
        //  apostar.setIcon(GUIPrincipal.controlUnit.getBarajaJugador().get(0).getImagen());

		size = jugar.getPreferredSize();
		jugar.setBounds(1090 + insets.left,  10+ insets.top,100,40);
		
		size = giveup.getPreferredSize();
		giveup.setBounds(1090 + insets.left,  155+ insets.top,100,40);

        pasar.setBackground(Color.white);
        igualar.setBackground(Color.white);
        subir.setBackground(Color.WHITE);
        jugar.setBackground(Color.white);
        giveup.setBackground(Color.white);

        pasar.setFont(bit8);
        igualar.setFont(bit8);
        subir.setFont(bit8);
        jugar.setFont(bit8);
        giveup.setFont(bit8);
        
        pasar.addMouseListener(mouse);
        igualar.addMouseListener(mouse);
        subir.addMouseListener(mouse);
        jugar.addMouseListener(mouse);
        giveup.addMouseListener(mouse);
        
        this.add(pasar);
        this.add(igualar);
        this.add(subir);
        //this.add(giveup);
        //this.add(jugar);

    }


	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Dimension height = getSize();
		ImageIcon Img = new ImageIcon(getClass().getResource("/imagenes/tableformat.png"));
		g.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);
		setOpaque(false);
		drawScore(g);


	}

	public void drawScore(Graphics g){
		g.setFont(decored2);
		g.setColor(Color.white);
		g.drawString("Bote = " + + ControlUnit.getApuestaActual(),20,650);

	}

	public void removeAL() {
		
		subir.removeMouseListener(mouse);
		igualar.removeMouseListener(mouse);
		pasar.removeMouseListener(mouse);
		giveup.removeMouseListener(mouse);
		
	}
	
	public void addAL() {
		subir.addMouseListener(mouse);
		igualar.addMouseListener(mouse);
		pasar.addMouseListener(mouse);
		giveup.addMouseListener(mouse);
		
	}

	public void fixName(){

		Insets insets = this.getInsets();
		username = new JLabel(Jugador.getUsername());
		username.setFont(decored2);
		int x,y;

		if(Jugador.getID() == 1){
			x = 260;
			y  = 410;
			username.setBounds(x + insets.left, y + insets.top,400,161);
		}

		if(Jugador.getID() == 2){
			x = 620;
			y  = 415;
			username.setBounds(x + insets.left, y + insets.top,400,161);
		}

		if(Jugador.getID() == 3){
			x = 1000;
			y  = 140;
			username.setBounds(x + insets.left, y + insets.top,400,161);
		}

		if(Jugador.getID() == 4){
			x = 620;
			y  = 120;
			username.setBounds(x + insets.left, y + insets.top,400,161);
		}

		if(Jugador.getID() == 5){
			x = 260;
			y  = 120;
			username.setBounds(x + insets.left, y + insets.top,400,161);
		}



		username.setForeground(Color.ORANGE);
		add(username);
		username.setVisible(true);

	}

	private class MouseAction  implements MouseListener {
	private ArrayList<Carta> jugada;
	private boolean elegirCartas;
		
		@SuppressWarnings("static-access")
		@Override
		public void mouseClicked(java.awt.event.MouseEvent arg0) {

			if(arg0.getSource() == pasar) {
				elegirCartas = false;
				JOptionPane.showMessageDialog(null, "Pierdes esta ronda");
				try {
					GUIPrincipal.salida.writeInt(Jugador.getDinero());
					GUIPrincipal.salida.flush();
					GUIPrincipal.salida.writeInt(0);
					GUIPrincipal.salida.flush();
					//Se le da el turno al otro jugadorM;
					removeAL();


				} catch (IOException e) {
					e.printStackTrace();
				}
				
				/*
				
            	GUIPrincipal.getJugador().setDinero
						((GUIPrincipal.getJugador().getDinero()) - GUIPrincipal.controlUnit.getApuestaActual());

				GUIPrincipal.getPc().setDineroPC
						((GUIPrincipal.getPc().getDineroPC()) + GUIPrincipal.controlUnit.getApuestaActual());

            	GUIPrincipal.controlUnit.newRound();
				GUIPrincipal.getJugador().realizarApuesta(1);
				
				*/
			}

			if(arg0.getSource() == subir) {
				try {
					GUIPrincipal.controlUnit.checkSubir();
					removeAL();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(arg0.getSource() == igualar) {
				try {
					GUIPrincipal.controlUnit.checkIgualar();
					removeAL();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			
			if(arg0.getSource() == giveup) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
	            int dialogResult = JOptionPane.showConfirmDialog(null,"Estas Seguro?", "QUITTING", dialogButton);

	            if(dialogResult == 0){
	            	
	            	JOptionPane.showMessageDialog(null, "Pierdes esta ronda");
					GUIPrincipal.controlUnit.newRound();
	            }

	            else  JOptionPane.showMessageDialog(null,"RETURNING TO GAME");
	            removeAL();
			}
			
			
		}

		@Override
		public void mouseEntered(java.awt.event.MouseEvent arg0) {

			if(arg0.getSource() == p1c1 || arg0.getSource() == p1c2) {

				jugador.turnCards("pNormal");
				GUIPrincipal.getPanelCentral().repaint();
			}
		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent arg0) {

			if(arg0.getSource() == p1c1 || arg0.getSource() == p1c2) {

				jugador.turnCards("player");
				GUIPrincipal.getPanelCentral().repaint();
			}
		}

		@Override
		public void mousePressed(java.awt.event.MouseEvent arg0) {
			if (elegirCartas){
				//System.out.println(arg0.getComponent().createImage().getSource());
			}
		}

		@Override
		public void mouseReleased(java.awt.event.MouseEvent arg0) {
			// TODO Auto-generated method stub
		}
			}
			

}