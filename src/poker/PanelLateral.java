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

    /** The computador down. */
    private String  computadorUP = "TABLA DE", computadorDown = "PUNTAJES ";
    
    /** The Img. */
    private ImageIcon Img = new ImageIcon(getClass().getResource("/imagenes/leftPanel.png"));
    
    /** The lateral pane. */
    private  JPanel lateralPane = this;;
    
    /** The giveup. */
    private JButton giveup;
    
    /** The escucha restantes. */
    private EscuchaRestantes escuchaRestantes = new EscuchaRestantes();
    
    /** The apuesta actual. */
    private static int apuestaJugador,apuestaPC,apuestaActual;
    
    /** The cash PC. */
    private static int cashJugador,cashPC;
    
    /** The bit 8. */
    private final Font bit8 = new Fuentes().fuente(Fuentes.BIT8_2, Font.BOLD, 24);
    
    /** The bit 8 2. */
    private final Font bit8_2 = new Fuentes().fuente(Fuentes.BIT8, Font.BOLD, 8);

	/**
	 * Instantiates a new panel lateral.
	 */
	public PanelLateral(){

        this.setLayout(null);
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(200,1000));
        
    }


    /**
     * Adds the buttons.
     */
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



    /**
     * Paint component.
     *
     * @param g the g
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Dimension height = getSize();
        g.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);
        g.setFont(bit8);
        g.setColor(Color.WHITE);

        g.drawString(computadorUP, 50, 60);
        g.drawString(computadorDown, 60, 80);

        apuestaJugador = GUIPrincipal.getJugador().getApuesta();
        apuestaPC = GUIPrincipal.getPc().getApuestaPC();
		apuestaActual = Jugador.getApuestaActual();
		cashJugador = GUIPrincipal.getJugador().getDinero();
		cashPC = GUIPrincipal.getPc().getDineroPC();
		
		g.drawString(Jugador.getUsername()+ ": " + String.valueOf(cashJugador), 8, 120);
        g.drawString("Cortana: " + String.valueOf(cashPC), 8, 150);
        g.drawString("Bote =  " + apuestaActual,10,350);
      //  g.drawString("es de:  " + apuestaActual, 10 ,370);
        repaint();
        
    }
    
    

    /**
     * The Class EscuchaRestantes.
     */
    private class EscuchaRestantes implements MouseListener{

        /**
         * Mouse clicked.
         *
         * @param e the e
         */
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

        /**
         * Mouse pressed.
         *
         * @param e the e
         */
        public void mousePressed(MouseEvent e) {

        }

        /**
         * Mouse released.
         *
         * @param e the e
         */
        @Override
        public void mouseReleased(MouseEvent e) {

        }

        /**
         * Mouse entered.
         *
         * @param e the e
         */
        @Override
        public void mouseEntered(MouseEvent e) {

        }

        /**
         * Mouse exited.
         *
         * @param e the e
         */
        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}