package poker;

import Fonts.Fuentes;

import java.awt.*;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class PanelCentral extends JPanel {

	private String username = Jugador.getUsername();

	private int c1x, c2x, c1y, c2y,ccx,ccy, c1pcx,c1pcy, c2pcx, c2pcy;
	public static JButton c1Ju, c2Ju, c1Pc, c2Pc, fJu, fPc;
	private static JButton pasar;
	private static JButton subir;
	private static JButton igualar;
	private static JButton jugar;
	private final Font bit8_2 = new Fuentes().fuente(Fuentes.BIT8, Font.BOLD, 9);
	private JButton cc1;
	private JButton cc2;
	private JButton cc3;
	private JButton cc4;
	private JButton cc5;
	MouseAction mouse = new MouseAction();
	static Jugador jugador = new Jugador();
	public static JPanel pcentral;


	PanelCentral(){
		pcentral = this;
		this.setLayout(null);
		this.setBackground(Color.lightGray);
		this.setPreferredSize(GUIPrincipal.sizeGame);

	}

	public void turnCards(String who){
	    if(who.equals("player")){
	        c1Ju.setIcon(new ImageIcon(getClass().getResource("/imagenes/RR.png")));
	        c2Ju.setIcon(new ImageIcon(getClass().getResource("/imagenes/RR.png")));
        }
	    
	    if(who.equals("pNormal")) {
	    	
	    	c1Ju.setIcon(GUIPrincipal.controlUnit.getBarajaJugador().get(0).getImagen());
	    	c2Ju.setIcon(GUIPrincipal.controlUnit.getBarajaJugador().get(1).getImagen());
	    }
	    
	    if(who.equals("pc")) {
	    	
	    	c1Pc.setIcon(new ImageIcon(getClass().getResource("/imagenes/RR.png")));
	    	c2Pc.setIcon(new ImageIcon(getClass().getResource("/imagenes/RR.png")));
	    	
	    }
    }

	public void addCartasJugador() {

		c1x = 180;
		c1y = 495;
		////////////////////////////////////////
		c2x= 329;
		c2y = 495;
        ///////////////////////////////////////
		Insets insets = this.getInsets();

		c1Ju = new JButton();
		Dimension size = c1Ju.getPreferredSize();
		c1Ju.setBounds(c1x + insets.left, c1y + insets.top,120,180);
		c1Ju.setIcon(GUIPrincipal.controlUnit.getBarajaJugador().get(0).getImagen());
		c1Ju.addMouseListener(mouse);
        this.add(c1Ju);
		///////////////////////////////////////////////////////////////////////////////////
        c2Ju = new JButton();
		size = c2Ju.getPreferredSize();
        c2Ju.setBounds(c2x + insets.left, c2y + insets.top, 120,180);
        c2Ju.setIcon(GUIPrincipal.controlUnit.getBarajaJugador().get(1).getImagen());
        c2Ju.addMouseListener(mouse);
		this.add(c2Ju);
		///////////////////////////////////////////////////////////////////////////////////
	}
	
	public void addCartasPC() {

		c1pcx = 545;
		c1pcy = 16;
		////////////////////////////////////////
		c2pcx= 695;
		c2pcy = 16;
        ///////////////////////////////////////
		Insets insets = this.getInsets();

		c1Pc = new JButton();
		Dimension size = c1Pc.getPreferredSize();
		c1Pc.setBounds(c1pcx + insets.left, c1pcy + insets.top,120,180);
		c1Pc.setIcon(GUIPrincipal.controlUnit.getBarajaPc().get(0).getImagen());
        this.add(c1Pc);
		///////////////////////////////////////////////////////////////////////////////////
        c2Pc = new JButton();
		size = c2Pc.getPreferredSize();
		c2Pc.setBounds(c2pcx + insets.left, c2pcy + insets.top, 120,180);
		c2Pc.setIcon(GUIPrincipal.controlUnit.getBarajaPc().get(1).getImagen());
		this.add(c2Pc);
		///////////////////////////////////////////////////////////////////////////////////
	}

	public void addCartasComunitarias(){

	    int cas = 125;
        ccx = 180;
        ccy = 250;
        //////////////////////////////////
        Insets insets = this.getInsets();
        cc1 = new JButton();
        Dimension size = cc1.getPreferredSize();
        cc1.setBounds(ccx + insets.left, ccy + insets.top,120,180);
        cc1.setIcon(GUIPrincipal.controlUnit.getCartasComunitarias().get(0).getImagen());
        this.add(cc1);
        //////////////////////////////////////////////////////////////////////////////////
        cc2 = new JButton();
        size = cc2.getPreferredSize();
        cc2.setBounds(ccx+cas + insets.left, ccy + insets.top,120,180);
        cc2.setIcon(GUIPrincipal.controlUnit.getCartasComunitarias().get(1).getImagen());
        this.add(cc2);
        ///////////////////////////////////////////////////////////////////////////////////
        cc3 = new JButton();
        size = cc3.getPreferredSize();
        cc3.setBounds(ccx+(cas*2) + insets.left, ccy + insets.top,120,180);
        cc3.setIcon(GUIPrincipal.controlUnit.getCartasComunitarias().get(2).getImagen());
        this.add(cc3);
       

    }
	
	public void add4carta() {
		int cas = 125;
        ccx = 180;
        ccy = 250;
        //////////////////////////////////
        Insets insets = this.getInsets();
		 ////////////////////////////////////////////////////////////////////////////////////
        cc4 = new JButton();
        Dimension size = cc4.getPreferredSize();
        cc4.setBounds(ccx+(cas*3) + insets.left, ccy + insets.top,120,180);
        cc4.setIcon(GUIPrincipal.controlUnit.getCartasComunitarias().get(3).getImagen());
        this.add(cc4);
	}
	
	public void add5carta() {
		int cas = 125;
        ccx = 180;
        ccy = 250;
        //////////////////////////////////
        Insets insets = this.getInsets();
		
		 /////////////////////////////////////////////////////////////////////////////////////
        cc5 = new JButton();
        Dimension size = cc5.getPreferredSize();
        cc5.setBounds(ccx+(cas*4) + insets.left, ccy + insets.top,120,180);
        cc5.setIcon(GUIPrincipal.controlUnit.getCartasComunitarias().get(4).getImagen());
        this.add(cc5);
		
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

        Dimension size = pasar.getPreferredSize();
        pasar.setBounds(490+ insets.left, 640 + insets.top, 100,40);
        // jugar .setIcon(GUIPrincipal.controlUnit.getBarajaJugador().get(1).getImagen());

        size = igualar.getPreferredSize();
        igualar.setBounds(490 + insets.left,  590 + insets.top,100,40);
        //apostar.setIcon(new ImageIcon(getClass().getResource("/imagenes/bill.png")));

        size = subir.getPreferredSize();
        subir.setBounds(490 + insets.left,  540 + insets.top,100,40);
        //  apostar.setIcon(GUIPrincipal.controlUnit.getBarajaJugador().get(0).getImagen());

		size = jugar.getPreferredSize();
		jugar.setBounds(10 + insets.left,  350+ insets.top,100,40);

        pasar.setBackground(Color.white);
        igualar.setBackground(Color.white);
        subir.setBackground(Color.WHITE);
        jugar.setBackground(Color.white);

        pasar.setFont(bit8_2);
        igualar.setFont(bit8_2);
        subir.setFont(bit8_2);
        jugar.setFont(bit8_2);
        
        pasar.addMouseListener(mouse);
        igualar.addMouseListener(mouse);
        subir.addMouseListener(mouse);
        jugar.addMouseListener(mouse);
        

        this.add(pasar);
        this.add(igualar);
        this.add(subir);
        this.add(jugar);

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
		fPc.setIcon(new ImageIcon(getClass().getResource("/imagenes/fichas2.png")));
		fPc.setBorderPainted(false);

		this.add(fJu);
		this.add(fPc);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);


		Dimension height = getSize();
		ImageIcon Img = new ImageIcon(getClass().getResource("/imagenes/Background_final.png"));
		g.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);
		g.drawString(username, 100, 200);
		setOpaque(false);

	}

	private class MouseAction  implements MouseListener {

		@Override
		public void mouseClicked(java.awt.event.MouseEvent arg0) {

			if(arg0.getSource() == pasar) {
				JOptionPane.showMessageDialog(null, "Pierdes esta ronda");
            	GUIPrincipal.getJugador().setDinero
            	((GUIPrincipal.getJugador().getDinero()) - GUIPrincipal.getJugador().getApuestaActual());
            	
            	GUIPrincipal.controlUnit.newRound();
            	//GUIPrincipal.getJugador().turnCards("player");
				GUIPrincipal.getJugador().setApuestaActual();
			}

			if(arg0.getSource() == subir) {
				
				jugador.realizarApuesta(3);
				GUIPrincipal.getPanelLateral().updateUI();
				GUIPrincipal.gameStage(2);

				
			}
			
			if(arg0.getSource() == igualar) {
				
				jugador.realizarApuesta(2);
				GUIPrincipal.getPanelLateral().updateUI();
				GUIPrincipal.gameStage(2);

			}



			
			
			
			
			
			

		}

		@Override
		public void mouseEntered(java.awt.event.MouseEvent arg0) {

			if(arg0.getSource() == c1Ju || arg0.getSource() == c2Ju) {

				jugador.turnCards("pNormal");
				GUIPrincipal.getPanelCentral().repaint();
			}
		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent arg0) {

			if(arg0.getSource() == c1Ju || arg0.getSource() == c2Ju) {

				jugador.turnCards("player");
				GUIPrincipal.getPanelCentral().repaint();
			}
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