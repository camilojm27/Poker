package poker;

import javax.swing.JFrame;

public class GUIPrincipal extends JFrame {
    GUIPrincipal(){
        initGUI();
        
        setTitle("Poker");
        setResizable(false);
        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initGUI() {
    }
}
