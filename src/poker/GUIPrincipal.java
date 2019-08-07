package poker;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;


public class GUIPrincipal extends JFrame {
    public static Dimension tamañoJuego;
	private  ControlUnit controlUnit;
    private PanelCentral panelCentral;
    private PanelLateral panelLateral;
    private JButton boton;
    
    GUIPrincipal(){
    	tamañoJuego = new Dimension(1200,720);
        controlUnit = new ControlUnit();
        panelCentral = new PanelCentral();
        panelLateral = new PanelLateral();
        
        

        initGUI();


        setTitle("Poker");
        setResizable(false);
        //pack();
        setSize(tamañoJuego);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }

    private void initGUI() {
    	add(panelCentral,BorderLayout.CENTER);
    	add(panelLateral,BorderLayout.WEST);
    	panelCentral.updateUI();
    	panelLateral.updateUI();
    	
    	boton = new JButton("Sup");


    	panelLateral.add(boton);
    	
    	
    	
    	
    }
}
