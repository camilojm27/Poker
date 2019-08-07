package poker;

import java.util.ArrayList;

public class ControlUnit {
    private Baraja baraja;
    private ArrayList<Carta> barajaPc;
    private ArrayList<Carta> barajaJugador;



    ControlUnit(){
        baraja = new Baraja();
        barajaPc = baraja.repartirBaraja();
        barajaJugador = baraja.repartirBaraja();
        baraja.print(barajaJugador);
        System.out.println("      ");
        baraja.print(barajaPc);
    }




}
