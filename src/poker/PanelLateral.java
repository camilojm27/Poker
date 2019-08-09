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


    public PanelLateral(){

        this.setLayout(null);
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(200,1000));
        plusButton = new ImageIcon("src/imagenes/plus2.png");
        lateralPane.addMouseListener(escuchaRestantes);
    }

    public void addButtons(){

        giveup = new JButton("RETIRARSE");

        Insets insets = this.getInsets();

        Dimension size = giveup.getPreferredSize();
        giveup.setBounds(40+ insets.left, 600 + insets.top, 120,40);
        // giveup.setIcon(GUIPrincipal.controlUnit.getBarajaJugador().get(1).getImagen());


        this.add(giveup);
    }



    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Dimension height = getSize();
        g.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        g.setColor(Color.WHITE);
        g.drawString(computadorUP, 50, 60);
        g.drawString(computadorDown, 50, 80);
    }

    private class EscuchaRestantes implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {

            JOptionPane.showMessageDialog(null, "Se ha agregado una nueva ficha a tu mazo");
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