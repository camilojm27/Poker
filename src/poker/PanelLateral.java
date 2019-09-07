/*
 * INTEGRANTES:
 * CAMILO JOSE MEZU MINA = 1824313
 * SANTIAGO MARTINEZ MESA = 1823107
 */
package poker;

import Fonts.Fuentes;

import javax.swing.*;
//https://stackoverflow.com/questions/19125707/simplest-way-to-set-image-as-jpanel-background
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// TODO: Auto-generated Javadoc

/**
 * The Class PanelLateral.
 */
public class PanelLateral extends JPanel  {

    private String  computadorUP = "TABLA DE", computadorDown = "PUNTAJES ";
    private ImageIcon Img = new ImageIcon(getClass().getResource("/imagenes/leftPanel.png"));
    private  JPanel lateralPane = this;;
    private JButton giveup;
    private EscuchaRestantes escuchaRestantes = new EscuchaRestantes();
    private static int apuestaJugador,apuestaPC,apuestaActual;
    private static int cashJugador,cashPC;
    private final Font bit8 = new Fuentes().fuente(Fuentes.BIT8_2, Font.BOLD, 24);
    private final Font bit8_2 = new Fuentes().fuente(Fuentes.BIT8, Font.BOLD, 8);
    private static Jugador jugadorActual;

	public PanelLateral(){

        this.setLayout(null);
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(100,1000));
        jugadorActual = GUIPrincipal.getJugador();
    }

    public void addButtons(){

        giveup = new JButton("RETIRARSE");
        giveup.setFont(bit8_2);
        giveup.setBackground(Color.white);

        Insets insets = this.getInsets();

        Dimension size = giveup.getPreferredSize();
        giveup.setBounds(40+ insets.left, 600 + insets.top, 120,40);
        // giveup.setIcon(GUIPrincipal.controlUnit.getBarajaJugador().get(1).getImagen());


        this.add(giveup);

        giveup.addMouseListener(escuchaRestantes);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Dimension height = getSize();
        g.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);
        g.setFont(bit8);
        g.setColor(Color.WHITE);

        g.drawString(computadorUP, 50, 60);
        g.drawString(computadorDown, 60, 80);

        apuestaJugador = Jugador.getBote();
        apuestaPC = GUIPrincipal.getPc().getApuestaPC();
		apuestaActual = GUIPrincipal.controlUnit.getApuestaActual();
		cashJugador = Jugador.getDinero(jugadorActual);
		cashPC = GUIPrincipal.getPc().getDineroPC();
		
		g.drawString(Jugador.getUsername(jugadorActual)+ ": " + String.valueOf(cashJugador), 8, 120);
        g.drawString("Cortana: " + String.valueOf(cashPC), 8, 150);
        g.drawString("Bote =  " + apuestaActual,10,350);
      //  g.drawString("es de:  " + apuestaActual, 10 ,370);
        repaint();
        
    }
    
   
    private class EscuchaRestantes implements MouseListener{
    	
        @Override
        public void mouseClicked(MouseEvent e) {

            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null,"Estas Seguro?", "QUITTING", dialogButton);

            if(dialogResult == 0){
            	
            	JOptionPane.showMessageDialog(null, "Pierdes esta ronda");
            	ControlUnit.newRound();
          
            }

            else  JOptionPane.showMessageDialog(null,"RETURNING TO GAME");


            lateralPane.updateUI();


        }
        
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }
        
        @Override
        public void mouseEntered(MouseEvent e) {

        }
        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}