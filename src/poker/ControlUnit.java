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

    public int ranking (ArrayList<Carta> mano){
        boolean sameType;
        //Comprueba si es del mismo tipo
        sameType = sameType(mano);
        System.out.println(sameType);
        if (sameType){
            StringBuilder cartas = new StringBuilder();
            for (Carta carta : mano ) {
                cartas.append(carta.getId());
            }
            //Si es un ROYAL_FLUSH_STRAIGHT
            if (cartas.toString().contains("10") & cartas.toString().contains("J")
            & cartas.toString().contains("Q") & cartas.toString().contains("K") & cartas.toString().contains("A")){
                return 10;
            }else{

            }

        }
        return 0;
    }
    private boolean sameType(ArrayList<Carta> mano){
        boolean same = false;
        for (int i =0; i < mano.size()-1 ; i++){
            same = mano.get(i).getTipo() == mano.get(i + 1).getTipo();
        }
        return  same;
    }


}
