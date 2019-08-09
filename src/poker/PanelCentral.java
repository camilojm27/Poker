package poker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class PanelCentral extends JPanel {

	private int c1x, c2x, c1y, c2y,ccx,ccy;
	private JButton c1Ju, c2Ju, c1Pc, c2Pc, fJu, fPc, pasar,apostar,igualar, cc1, cc2,cc3,cc4,cc5;

	PanelCentral(){

		this.setLayout(null);
		this.setBackground(Color.lightGray);
		this.setPreferredSize(GUIPrincipal.sizeGame);

	}

	public void turnCards(String who){
	    if(who.equals("player")){
	        c1Ju.setIcon(GUIPrincipal.controlUnit.getBarajaJugador().get(0).getImagen());
        }
    }

	public void addCartasJugador() {

		c1x = 180;
		c1y = 495;
		////////////////////////////////////////
		c2x= 545;
		c2y = 15;
        ///////////////////////////////////////
		Insets insets = this.getInsets();

		c1Ju = new JButton();
		Dimension size = c1Ju.getPreferredSize();
		c1Ju.setBounds(c1x + insets.left, c1y + insets.top,120,180);
		c1Ju.setIcon(GUIPrincipal.controlUnit.getBarajaJugador().get(0).getImagen());
        this.add(c1Ju);
		///////////////////////////////////////////////////////////////////////////////////
        c2Ju = new JButton();
		size = c2Ju.getPreferredSize();
        c2Ju.setBounds(c2x + insets.left, c2y + insets.top, 120,180);
        c2Ju.setIcon(GUIPrincipal.controlUnit.getBarajaJugador().get(1).getImagen());
		this.add(c2Ju);
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
        ////////////////////////////////////////////////////////////////////////////////////
        cc4 = new JButton();
        size = cc4.getPreferredSize();
        cc4.setBounds(ccx+(cas*3) + insets.left, ccy + insets.top,120,180);
        cc4.setIcon(GUIPrincipal.controlUnit.getCartasComunitarias().get(3).getImagen());
        this.add(cc4);
        /////////////////////////////////////////////////////////////////////////////////////
        cc5 = new JButton();
        size = cc5.getPreferredSize();
        cc5.setBounds(ccx+(cas*4) + insets.left, ccy + insets.top,120,180);
        cc5.setIcon(GUIPrincipal.controlUnit.getCartasComunitarias().get(4).getImagen());
        this.add(cc5);

    }

	public void addButtons(){

        Insets insets = this.getInsets();

        pasar = new JButton("PASAR");
        apostar = new JButton("SUBIR");
        igualar = new JButton("IGUALAR");

        Dimension size = pasar.getPreferredSize();
        pasar.setBounds(490+ insets.left, 640 + insets.top, 100,40);
        // jugar .setIcon(GUIPrincipal.controlUnit.getBarajaJugador().get(1).getImagen());

        size = apostar.getPreferredSize();
        apostar.setBounds(490 + insets.left,  590 + insets.top,100,40);
        //apostar.setIcon(new ImageIcon(getClass().getResource("/imagenes/bill.png")));



        size = igualar.getPreferredSize();
        igualar.setBounds(490 + insets.left,  540 + insets.top,100,40);
        //  apostar.setIcon(GUIPrincipal.controlUnit.getBarajaJugador().get(0).getImagen());

        pasar.setBackground(Color.white);
        igualar.setBackground(Color.white);
        apostar.setBackground(Color.WHITE);

        this.add(pasar);
        this.add(apostar);
        this.add(igualar);

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