package poker;

import java.util.ArrayList;
import java.util.Collections;

public class ControlUnit {
    private Baraja baraja;
    private ArrayList<Carta> barajaPc;
    private ArrayList<Carta> barajaJugador;


    ControlUnit() {
        baraja = new Baraja();
        barajaPc = baraja.repartirBaraja();
        barajaJugador = baraja.repartirBaraja();
        baraja.print(barajaJugador);
        System.out.println("      ");
        baraja.print(barajaPc);
        ranking(Escaleras.ROYAL_FLUSH_STRAIGHT);
        ranking(Escaleras.FLUSH_STRAIGHT);
        ranking(Escaleras.FOUR_OF_A_KIND);
        ranking(Escaleras.FULL_HOUSE);
        ranking(barajaJugador);


    }

    public int ranking(ArrayList<Carta> mano) {
        boolean sameType;
        //Comprueba si es del mismo tipo
        sameType = sameType(mano);
        System.out.println(sameType);
        //Compara si TODAS las cartas tienen que ser del mismo tipo
        if (sameType) {

            //Si es un ROYAL_FLUSH_STRAIGHT
            if (royalFlushStraight(mano)) {
                System.out.println("ROYAL_FLUSH_STRAIGHT");
                return 10;
                //Comprueba si hay FLUSH_STRAIGHT
            } else if (flushStraight(mano)){
                System.out.println("FLUSH_STRAIGHT");
                return 9;
            }
        }
        else {
              if (fourOfaKind(mano)){
                System.out.println("FOUR_OF_A_KIND");
                return 8;
            }
              else if (fullHouse(mano)){
                  System.out.println("FULL_HOUSE");
              }
        }

        return 0;
    }

    private boolean fullHouse(ArrayList<Carta> mano) {
        //No se utiliza bucle for, porque se deborda del arraylist en ciertos casos
        Collections.sort(mano);
        Carta threeCardsType;
        boolean threeCards = false;
//Si las tres cartas son iguales
        if (mano.get(0).getId() == mano.get(1).getId() & mano.get(1).getId() == mano.get(2).getId()){
            threeCards = true;
        }
        else if (mano.get(1).getId() == mano.get(2).getId() & mano.get(2).getId() == mano.get(3).getId()){
            threeCards = true;
        }
        else if (mano.get(2).getId() == mano.get(3).getId() & mano.get(3).getId() == mano.get(4).getId()) {
            threeCards = true;
        }
        //Si las tres cartas son iguales buscalas otras 2
        if (threeCards){
            for (int i = 0; i < mano.size() - 1; i++) {
                if (mano.get(i).getId() == mano.get(i++).getId()){
                    return true;
                }
            }
        }

        return false;
    }

    private boolean fourOfaKind(ArrayList<Carta> mano) {
        boolean fourOfaKind = false;
        Collections.sort(mano);
        if (mano.get(0).getId() == mano.get(1).getId() & mano.get(1).getId() == mano.get(2).getId() &
                mano.get(2).getId() == mano.get(3).getId() ){
            fourOfaKind = true;
            System.out.println("FOUR_OF_A_KIND - Primera");
        }
        else if (mano.get(1).getId() == mano.get(2).getId() & mano.get(2).getId() == mano.get(3).getId() &
                mano.get(3).getId() == mano.get(4).getId()){
            fourOfaKind = true;
            System.out.println("FOUR_OF_A_KIND - Segunda");
        }

        return fourOfaKind;
    }

    private boolean royalFlushStraight(ArrayList<Carta> mano) {
        boolean royalFlushStraight = false;
        Collections.sort(mano);
        if (mano.get(0).getId() == "10" & mano.get(1).getId() == "J" &
                mano.get(2).getId() == "Q" & mano.get(3).getId() == "K" & mano.get(4).getId() == "K") {

            royalFlushStraight = true;
        }

        return royalFlushStraight;
    }

    private boolean flushStraight(ArrayList<Carta> mano) {
        boolean flushStraight = false;
        int flushStraightValue = 0;

        Collections.sort(mano);
        for (int i = 0; i < mano.size() - 1; i++) {
            if (mano.get(i).getIdValue() == mano.get(i + 1).getIdValue() - 1)
                flushStraightValue++;
        }
        if (flushStraightValue == mano.size() - 1)
            flushStraight = true;
        return flushStraight;
    }

    private boolean sameType(ArrayList<Carta> mano) {
        boolean same = false;
        int sameValue = 0; //Si el valor da el tamaño del array -1 todas son iguales
        for (int i = 0; i < mano.size() - 1; i++) {
            if (mano.get(i).getTipo() == mano.get(i + 1).getTipo())
                sameValue++;

        }
        if (sameValue == mano.size() - 1)
            same = true;
        return same;
    }


}
