/*
 * @Autores: Santiago Martinez Mesa - 1823107
 			 Camilo Mezu Mina -	1824313
 */
package poker;

import javax.swing.*;
//https://stackoverflow.com/questions/19125707/simplest-way-to-set-image-as-jpanel-background
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// TODO: Auto-generated Javadoc

public class PanelLateral extends JPanel  {

    private String username = "Camilo y Santiago", computadorUP = "TABLA DE", computadorDown = "PUNTAJES";
    private int puntaje=0;
    private boolean gano, perdio, rendirse;
    private ImageIcon Img = new ImageIcon(getClass().getResource("/imagenes/leftPanel.png"));
    private  JPanel lateralPane = this;
    private  ImageIcon plusButton;
    private JButton giveup;
    private EscuchaRestantes escuchaRestantes = new EscuchaRestantes();
    private static int apuestaJugador,apuestaPC,apuestaActual;

	public PanelLateral(){

        this.setLayout(null);
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(200,1000));
        
    }

    public void addButtons(){

        giveup = new JButton("RETIRARSE");

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
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        g.setColor(Color.WHITE);
        g.drawString(computadorUP, 50, 60);
        g.drawString(computadorDown, 50, 80);
        
        plusButton = new ImageIcon("src/imagenes/plus2.png");
        apuestaJugador = GUIPrincipal.getJugador().getApuesta();
        apuestaPC = GUIPrincipal.getPc().getApuestaPC();
        GUIPrincipal.getJugador();
		apuestaActual = Jugador.getApuestaActual();
      
        g.drawString("La apuesta actual ",10,350);
        g.drawString("es de   =  " + apuestaActual, 10 ,370);
        repaint();
        
    }
    
    

    private class EscuchaRestantes implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {

            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null,"Estas Seguro?", "QUITTING", dialogButton);

            if(dialogResult == 0){

                JOptionPane.showMessageDialog(null,"Sayonara");
                GUIPrincipal.vprincipal.dispose();
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