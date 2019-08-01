package poker;

import java.util.ArrayList;

public class ControlUnit {
    private Baraja baraja;
    private ArrayList barajaJugador;
    ControlUnit(){
        baraja = new Baraja();
        barajaJugador = baraja.getClubs();
        baraja.print(barajaJugador);
    }
}
