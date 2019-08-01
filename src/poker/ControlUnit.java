package poker;

import java.util.ArrayList;

public class ControlUnit {
    private Baraja baraja;
    private ArrayList<Carta> barajaJugador;
    ControlUnit(){
        baraja = new Baraja();
        barajaJugador = baraja.repartirBaraja();
        baraja.print(barajaJugador);
    }
}
