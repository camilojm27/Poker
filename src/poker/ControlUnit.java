package poker;

//import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

public class ControlUnit {
    private static Baraja baraja;
    public static ArrayList<Carta> barajaPc;
    public static ArrayList<Carta> barajaJugador;
    public static ArrayList<Carta> cartasComunitarias;
   


    ControlUnit() {

        
        baraja = new Baraja();
        barajaPc = baraja.repartirBaraja();
        barajaJugador = baraja.repartirBaraja();
        cartasComunitarias = baraja.repartirCartasComunitarias();
        baraja.print(barajaJugador);
        System.out.println("      ");
        baraja.print(barajaPc);
        System.out.println("      ");
        baraja.print(cartasComunitarias);
        //compararJugadas();
    compararJugadas(Escaleras.FOUR_OF_A_KIND, Escaleras.FOUR_OF_A_KIND2);
    }
    
    public static void newRound() {
    	
    	
    	GUIPrincipal.getJugador();
    	if(Jugador.getApuestaActual() <= GUIPrincipal.getJugador().getDinero()) {
    		Jugador.setDinero((GUIPrincipal.getJugador().getDinero()) - Jugador.getApuestaActual());
    	}
    	else { 
    		JOptionPane.showMessageDialog(null, "Te has quedado sin dinero, no puedes seguir jugando");
    			System.exit(1);
    	}
        GUIPrincipal.getJugador();
		Jugador.setApuestaActual(0);
		Jugador.setApuesta(0);
		Jugador.setApuestaPC(0);
    	GUIPrincipal.getJugador().turnCards("player");
    	JOptionPane.showMessageDialog(null, "Nueva Ronda");
    	GUIPrincipal.getPanelCentral().removeCartasComunitarias();
        GUIPrincipal.getPanelCentral().updateUI();
    	
        baraja = new Baraja();
        barajaPc = baraja.repartirBaraja();
        barajaJugador = baraja.repartirBaraja();
        cartasComunitarias = baraja.repartirCartasComunitarias();
        baraja.print(barajaJugador);
        System.out.println("      ");
        baraja.print(barajaPc);
        System.out.println("      ");
        baraja.print(cartasComunitarias);

    }


    public ArrayList<Carta> getBarajaPc() {
        return barajaPc;
    }

    public ArrayList<Carta> getBarajaJugador() {
        return barajaJugador;
    }

    public ArrayList<Carta> getCartasComunitarias() {
        return cartasComunitarias;
    }

    @SuppressWarnings("unchecked")
	private int cartaMayor(ArrayList<Carta> jugador1, ArrayList<Carta> jugador2){
        Collections.sort(jugador1);
        Collections.sort(jugador2);
        if (jugador1.get(4).getIdValue() > jugador2.get(4).getIdValue()){
            //return "El jugador 1 tiene una mejor jugada por la carta" + jugador1.get(4).getId() + jugador1.get(4).getTipo();
            return 1;
        }
        else {
            //return "El jugador 2 tiene una mejor jugada por la carta" + jugador2.get(4).getId() + jugador1.get(4).getTipo();
            return 2;
        }

    }

    public int compararJugadas(ArrayList<Carta> jugador1, ArrayList<Carta> jugador2) {

if (ranking(jugador1) > ranking(jugador2)){
    System.out.println("El jugador jugador 1 tiene una mejor jugada que el jugador 2");
}else if (ranking(jugador1) < ranking(jugador2)){
    System.out.println("El jugador jugador 2 tiene una mejor jugada que el jugador 1");
}
else{
    System.out.println("Empate, se elegira la ultima carta de la mano (La mayor) = " );
    if (1 == cartaMayor(jugador1, jugador2)) {


        System.out.print("El jugador 1 tiene mejor jugada");
    }else{
        System.out.print("El jugador 2 tiene mejor jugada");
    }

    return cartaMayor(jugador1, jugador2);
}

/*
        System.out.println("-------ROYAL_FLUSH_STRAIGHT-------");
        ranking(Escaleras.ROYAL_FLUSH_STRAIGHT);
        System.out.println("-------FLUSH_STRAIGHT-------");
        ranking(Escaleras.FLUSH_STRAIGHT);
        System.out.println("-------FOUR_OF_A_KIND-------");
        ranking(Escaleras.FOUR_OF_A_KIND);
        System.out.println("-------FULL_HOUSE-------");
        ranking(Escaleras.FULL_HOUSE);
        System.out.println("-------COLOR_FLUSH-------");
        ranking(Escaleras.COLOR_FLUSH);
        System.out.println("-------ESCALERA_STRAIGHT-------");
        ranking(Escaleras.ESCALERA_STRAIGHT);
        System.out.println("-------THREE_OF_A_KIND-------");
        ranking(Escaleras.THREE_OF_A_KIND);
        System.out.println("-------TWO_PAIR-------");
        ranking(Escaleras.TWO_PAIR);
        System.out.println("-------PAIR-------");
        ranking(Escaleras.PAIR);
        System.out.println("-------HIGH_CARD-------");
        ranking(Escaleras.HIGH_CARD);

 */

        return 0;
    }

    public int ranking(ArrayList<Carta> mano) {
        boolean sameType;
        //Comprueba si es del mismo tipo
        sameType = sameType(mano);
        //System.out.println(sameType);
        //Compara si TODAS las cartas tienen que ser del mismo tipo
        if (sameType) {

            //Si es un ROYAL_FLUSH_STRAIGHT
            if (royalFlushStraight(mano)) {
                System.out.println("ROYAL_FLUSH_STRAIGHT");
                return 10;
                //Comprueba si hay FLUSH_STRAIGHT
            } else if (flushStraight(mano)) {
                System.out.println("FLUSH_STRAIGHT");
                return 9;
            } else if (colorFlush(mano)) {
                System.out.println("COLOR_FLUSH");
                return 6;
            }
        } else {
            if (fourOfaKind(mano)) {
                System.out.println("FOUR_OF_A_KIND");
                return 8;
            } else if (fullHouse(mano)) {
                System.out.println("FULL_HOUSE");
                return 7;
            } else if (escaleraStraight(mano)) {
                System.out.println("ESCALERA_STRAIGHT");
                return 5;
            } else if (threeOfaKind(mano)) {
                System.out.println("THREE_OF_A_KIND");
                return 4;
            } else if (twoPair(mano)) {
                System.out.println("TWO_PAIR");
                return 3;

            } else if (pair(mano)) {
                System.out.println("PAIR");
                return 2;
            } else {
                System.out.println("HIGH_CARD");
                return 1;
            }

        }

        return 0;
    }

    @SuppressWarnings("unchecked")
	private boolean pair(ArrayList<Carta> mano) {
        Collections.sort(mano);
        if (mano.get(0).getId().equals(mano.get(1).getId())) {
            return true;
        } else if (mano.get(1).getId() == mano.get(2).getId()) {
            return true;
        } else if (mano.get(2).getId() == mano.get(3).getId()) {
            return true;
        } else if (mano.get(3).getId() == mano.get(4).getId()) {
            return true;
        }


        return false;
    }

    @SuppressWarnings("unchecked")
	private boolean twoPair(ArrayList<Carta> mano) {
        Collections.sort(mano);

        if (mano.get(0).getId() == mano.get(1).getId() & mano.get(2).getId() == mano.get(3).getId()
                & mano.get(1).getId() != mano.get(3).getId() & mano.get(3).getId() != mano.get(4).getId()) {
            return true;
        }


        return false;
    }

    @SuppressWarnings("unchecked")
	private boolean threeOfaKind(ArrayList<Carta> mano) {
        Collections.sort(mano);

        if (mano.get(0).getId() == mano.get(1).getId() & mano.get(1).getId() == mano.get(2).getId() &
                mano.get(3).getId() != mano.get(4).getId() & mano.get(0).getId() != mano.get(4).getId()) {
            System.out.println("Parte 1");
            return true;
        } else if (mano.get(1).getId() == mano.get(2).getId() & mano.get(2).getId() == mano.get(3).getId() & mano.get(0).getId() != mano.get(4).getId()) {
            System.out.println("Parte 2");
            return true;
        } else if (mano.get(2).getId() == mano.get(3).getId() & mano.get(3).getId() == mano.get(4).getId() & mano.get(0).getId() != mano.get(1).getId()) {
            System.out.println("Parte 3");
            return true;
        }

        return false;
    }

    @SuppressWarnings("unchecked")
	private boolean escaleraStraight(ArrayList<Carta> mano) {
        int escaleraStraightValue = 0;
        Collections.sort(mano);

        for (int i = 0; i < mano.size() - 1; i++) {
            if (mano.get(i).getIdValue() == mano.get(i + 1).getIdValue() - 1) {
                escaleraStraightValue++;
            }
            if (escaleraStraightValue == mano.size() - 1) {
                return true;
            }
        }

        return false;
    }

    //@Contract(pure = true)
    private boolean colorFlush(ArrayList<Carta> mano) {
        //Collections.sort(mano);
        return true;
    }

    @SuppressWarnings("unchecked")
	private boolean fullHouse(ArrayList<Carta> mano) {
        //No se utiliza bucle for, porque se deborda del arraylist en ciertos casos
        Collections.sort(mano);
        Carta threeCardsType;
        boolean threeCards = false;

        Collections.sort(mano);

        for (int i = 0; i < mano.size() - 2; i++) {


            if (mano.get(0).getId() == mano.get(1).getId() & mano.get(1).getId() == mano.get(2).getId() &
                    mano.get(0).getId() != mano.get(3).getId() & mano.get(4).getId() == mano.get(3).getId()) {
                return true;
            } else if (mano.get(2).getId() == mano.get(3).getId() & mano.get(2).getId() == mano.get(4).getId() &
                    mano.get(0).getId() != mano.get(3).getId() & mano.get(0).getId() == mano.get(1).getId()) {

                return true;
            }

        }

        return false;
    }

    @SuppressWarnings("unchecked")
	private boolean fourOfaKind(ArrayList<Carta> mano) {

        Collections.sort(mano);
        if (mano.get(0).getId() == mano.get(1).getId() & mano.get(1).getId() == mano.get(2).getId() &
                mano.get(2).getId() == mano.get(3).getId()) {

            System.out.println("FOUR_OF_A_KIND - Primera");
            return true;
        } else if (mano.get(1).getId() == mano.get(2).getId() & mano.get(2).getId() == mano.get(3).getId() &
                mano.get(3).getId() == mano.get(4).getId()) {
            System.out.println("FOUR_OF_A_KIND - Segunda");
            return true;
        }

        return false;
    }

    @SuppressWarnings("unchecked")
	private boolean royalFlushStraight(ArrayList<Carta> mano) {

        Collections.sort(mano);
        if (mano.get(0).getId() == "10" & mano.get(1).getId() == "J" &
                mano.get(2).getId() == "Q" & mano.get(3).getId() == "K" & mano.get(4).getId() == "A") {

            return true;
        }

        return false;
    }

    @SuppressWarnings("unchecked")
	private boolean flushStraight(ArrayList<Carta> mano) {
        int flushStraightValue = 0;

        Collections.sort(mano);
        for (int i = 0; i < mano.size() - 1; i++) {
            if (mano.get(i).getIdValue() == mano.get(i + 1).getIdValue() - 1)
                flushStraightValue++;
        }
        if (flushStraightValue == mano.size() - 1)
            return true;

        return false;
    }

    private boolean sameType(ArrayList<Carta> mano) {

        int sameValue = 0; //Si el valor da el tamaño del array -1 todas son iguales
        for (int i = 0; i < mano.size() - 1; i++) {
            if (mano.get(i).getTipo() == mano.get(i + 1).getTipo())
                sameValue++;

        }
        if (sameValue == mano.size() - 1)
            return true;
        return false;
    }
    
    public static void checkSubir() {
    	if(GUIPrincipal.getRonda() == 2) {
			
			GUIPrincipal.getJugador().realizarApuesta(3);
			GUIPrincipal.getPanelLateral().updateUI();
			GUIPrincipal.gameStage(2);
			GUIPrincipal.setRonda(3);
			return;
		}
    	
    	if(GUIPrincipal.getRonda() == 3) {
			
    		GUIPrincipal.getJugador().realizarApuesta(3);
			GUIPrincipal.getPanelLateral().updateUI();
			GUIPrincipal.gameStage(3);
			GUIPrincipal.setRonda(4);
			return;
		}
    	
    	if(GUIPrincipal.getRonda() == 4) {
			
    		GUIPrincipal.getJugador().realizarApuesta(3);
			GUIPrincipal.getPanelLateral().updateUI();
			GUIPrincipal.gameStage(4);
			GUIPrincipal.getPanelCentral().removeAL();
			return;
		}
    }
    
    public static void checkIgualar() {
    	
    	if(GUIPrincipal.getRonda() == 2) {
			
    		JOptionPane.showMessageDialog(null, "Apuestas " + Jugador.getApuesta());
    		Jugador.realizarApuestaFija();
        	GUIPrincipal.getPanelLateral().updateUI();
			GUIPrincipal.gameStage(2);
			GUIPrincipal.setRonda(3);
			return;
		}
    	
    	if(GUIPrincipal.getRonda() == 3) {
			
    		JOptionPane.showMessageDialog(null, "Apuestas " + Jugador.getApuesta());
    		Jugador.realizarApuestaFija();
        	GUIPrincipal.getPanelLateral().updateUI();
			GUIPrincipal.gameStage(3);
			GUIPrincipal.setRonda(4);
			return;
		}
    	
    	if(GUIPrincipal.getRonda() == 4) {
			
    		JOptionPane.showMessageDialog(null, "Apuestas " + Jugador.getApuesta());
    		Jugador.realizarApuestaFija();
        	GUIPrincipal.getPanelLateral().updateUI();
			GUIPrincipal.gameStage(4);
			GUIPrincipal.getPanelCentral().removeAL();
			return;
		}
    	
    }
    
    public static void cortanaDecidirJugada() {
    	
    }


}
