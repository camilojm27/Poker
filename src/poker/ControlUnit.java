package poker;

import java.util.ArrayList;
import java.util.Collections;

public class ControlUnit  {
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
        ranking(baraja.getClubs());
        ranking(barajaJugador);

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
                for (Carta carta : mano) {

                    System.out.println(carta.getId());
                }
                System.out.println("-----------------");
                Collections.sort(mano);
                for (Carta carta : mano) {

                    System.out.println(carta.getId());
                }
            }

        }
        return 0;
    }
    private boolean sameType(ArrayList<Carta> mano){
        boolean same = false;
        int sameValue = 0; //Si el valor da el tamaño del array -1 todas son iguales
        for (int i =0; i < mano.size()-1 ; i++){
            if (mano.get(i).getTipo() == mano.get(i + 1).getTipo())
                sameValue++;

        }
        if (sameValue == mano.size()-1)
            same = true;
        return  same;
    }


}
