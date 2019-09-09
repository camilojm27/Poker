/*
 * INTEGRANTES:
 * CAMILO JOSE MEZU MINA = 1824313
 * SANTIAGO MARTINEZ MESA = 1823107
 */
package poker;

//import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

public class ControlUnit {
    public static int cantidadJugadores;



    private static Baraja baraja;
    public static ArrayList<Carta> barajaPc;
    public static ArrayList<Carta> barajaJugador1,barajaJugador2,barajaJugador3,barajaJugador4,barajaJugador5;
    public static ArrayList<Carta> cartasComunitarias;
    public static int apuestaActual = 0;
    private ArrayList<Integer>puntajeJugador1 ;
    private ArrayList<Integer>puntajeCortana ;
    private static Jugador jugadorActual;


    public ControlUnit() {


        baraja = new Baraja();
        barajaPc = baraja.repartirBarajaJugadores();
        barajaJugador1 = baraja.repartirBarajaJugadores();
        barajaJugador2 = baraja.repartirBarajaJugadores();
        barajaJugador3 = baraja.repartirBarajaJugadores();
        barajaJugador4 = baraja.repartirBarajaJugadores();
        barajaJugador5 = baraja.repartirBarajaJugadores();
        cartasComunitarias = baraja.repartirCartasComunitarias();

        /*
        baraja.print(barajaJugador);
        System.out.println("      ");
        baraja.print(barajaPc);
        System.out.println("      ");
        baraja.print(cartasComunitarias);
        */

        //compararJugadas(Escaleras.FOUR_OF_A_KIND, Escaleras.FOUR_OF_A_KIND2);
    }

    public static void newRound() {


    	if(getApuestaActual() <= Jugador.getDinero(jugadorActual)) {
    		Jugador.setDinero((Jugador.getDinero(jugadorActual)) - getApuestaActual(), jugadorActual);
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "Te has quedado sin dinero, no puedes seguir jugando");
    			System.exit(1);
    	}
    	
		setApuestaActual(0);
		Jugador.setApuesta(0);
		Pc.setApuestaPC(0);
    	jugadorActual.turnCards("player");
    	JOptionPane.showMessageDialog(null, "Nueva Ronda");

    	GUIPrincipal.getPanelCentral().addAL();
    	GUIPrincipal.getPanelCentral().removeCartasComunitarias();
        GUIPrincipal.getPanelCentral().updateUI();

        baraja = new Baraja();
        barajaJugador1 = new ArrayList<>();
        barajaPc = new ArrayList<>();

        barajaPc = baraja.repartirBarajaJugadores();
        barajaJugador1 = baraja.repartirBarajaJugadores();
        barajaJugador2 = baraja.repartirBarajaJugadores();
        barajaJugador3 = baraja.repartirBarajaJugadores();
        barajaJugador4 = baraja.repartirBarajaJugadores();
        barajaJugador5 = baraja.repartirBarajaJugadores();
        cartasComunitarias = baraja.repartirCartasComunitarias();
        GUIPrincipal.getPanelCentral().addCartasComunitarias();
        baraja.print(barajaJugador1);
        System.out.println("      ");
        baraja.print(barajaPc);
        System.out.println("      ");
        baraja.print(cartasComunitarias);


    }

    public ArrayList<Carta> getBarajaPc() {
        return barajaPc;
    }

    public ArrayList<Carta> getBarajaJugador() {
        return barajaJugador1;
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

    private boolean mayorPuntaje(){
        int playerScore = 0, cortanaScore=0;
        for (int puntaje = 0; puntaje < puntajeJugador1.size(); puntaje++) {
            playerScore += puntajeJugador1.get(puntaje);
            cortanaScore += puntajeCortana.get(puntaje);
        }
        System.out.println("Punatje JUgador = " + playerScore);
        System.out.println("Punatje Cortana = " + cortanaScore);
        if (playerScore <= cortanaScore){
            return false;
        }else return true;
    }


    public String winner(){
        puntajeCortana = new ArrayList<>();
        puntajeJugador1 = new ArrayList<>();
        //Jugador2
        Carta aux;
        /*Collections.sort(barajaJugador);
        Collections.sort(barajaPc);
        Collections.sort(cartasComunitarias);
         */

        for (int cartaComunitaria = 0; cartaComunitaria < 5; cartaComunitaria++) {
            aux = cartasComunitarias.get(cartaComunitaria);


            for (int cartaJugador = 0; cartaJugador < 2; cartaJugador++) {
                cartasComunitarias.add(cartaComunitaria, barajaJugador1.get(cartaJugador));
                cartasComunitarias.remove(cartaComunitaria + 1);
                puntajeJugador1.add(ranking(cartasComunitarias));
                cartasComunitarias.remove(cartaComunitaria);
                cartasComunitarias.add(cartaComunitaria, aux);
            }
        }
        for (int cartaComunitaria = 0; cartaComunitaria < 5; cartaComunitaria++) {
            aux = cartasComunitarias.get(cartaComunitaria);


            for (int cartaJugador = 0; cartaJugador < 2; cartaJugador++) {
                cartasComunitarias.add(cartaComunitaria, barajaPc.get(cartaJugador));
                cartasComunitarias.remove(cartaComunitaria + 1);
                puntajeCortana.add(ranking(cartasComunitarias));
                cartasComunitarias.remove(cartaComunitaria);
                cartasComunitarias.add(cartaComunitaria, aux);
            }
        }
        if (mayorPuntaje()){
            return jugadorActual.getName();

        }

        else return  "Cortana" ;



    }
/*
    public int compararJugadas(ArrayList<Carta> jugador1, ArrayList<Carta> jugador2) {

if (ranking(jugador1) > ranking(jugador2)){
    System.out.println("El jugador jugador 1 tiene una mejor jugada que el jugador 2");
    JOptionPane.showMessageDialog(null, "El jugador 1 gana");
    victoria(1);
}else if (ranking(jugador1) < ranking(jugador2)){
    System.out.println("El jugador jugador 2 tiene una mejor jugada que el jugador 1");
    JOptionPane.showMessageDialog(null, "El jugador 2 gana");
    victoria(2);
}
else{
	JOptionPane.showMessageDialog(null, "Empate, se elegira la ultima carta de la mano");
    System.out.println("Empate, se elegira la ultima carta de la mano (La mayor) = " );
    if (1 == cartaMayor(jugador1, jugador2)) {


        System.out.print("El jugador 1 tiene mejor jugada");
        JOptionPane.showMessageDialog(null, "El jugador 1 gana");
        victoria(1);
    }else{
        System.out.print("El jugador 2 tiene mejor jugada");
        JOptionPane.showMessageDialog(null, "El jugador 2 gana");
        victoria(2);
    }

    return cartaMayor(jugador1, jugador2);
}


        return 0;

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

    }
 */


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

            jugadorActual = GUIPrincipal.getJugador();

			jugadorActual.realizarApuesta(3,jugadorActual);
			GUIPrincipal.getPanelLateral().updateUI();
			GUIPrincipal.gameStage(2);
			GUIPrincipal.setRonda(3);
			return;
		}

    	if(GUIPrincipal.getRonda() == 3) {

    		jugadorActual.realizarApuesta(3,jugadorActual);
			GUIPrincipal.getPanelLateral().updateUI();
			GUIPrincipal.gameStage(3);
			GUIPrincipal.setRonda(4);
			return;
		}

    	if(GUIPrincipal.getRonda() == 4) {

    		jugadorActual.realizarApuesta(3,jugadorActual);
			GUIPrincipal.getPanelLateral().updateUI();
			GUIPrincipal.gameStage(4);
			GUIPrincipal.getPanelCentral().removeAL();
			if(GUIPrincipal.controlUnit.winner() == Jugador.getUsername()) {
				JOptionPane.showMessageDialog(null, "El jugador 1 gana");
				GUIPrincipal.getPanelCentral().turnCards("pNormal");
				GUIPrincipal.getPanelCentral().turnCards("cNormal");
				victoria(1);

			}
			else {
				JOptionPane.showMessageDialog(null, "Cortana gana");
				GUIPrincipal.getPanelCentral().turnCards("pNormal");
				GUIPrincipal.getPanelCentral().turnCards("cNormal");
				victoria(2);

			}

			return;
		}
    }

    public static void checkIgualar() {

        jugadorActual = GUIPrincipal.getJugador();

    	if(GUIPrincipal.getRonda() == 2) {

    		JOptionPane.showMessageDialog(null, "Apuestas " + getApuestaActual());
    		Jugador.realizarApuestaFija(GUIPrincipal.getJugador());
        	GUIPrincipal.getPanelLateral().updateUI();
			GUIPrincipal.gameStage(2);
			GUIPrincipal.setRonda(3);
			return;
		}

    	if(GUIPrincipal.getRonda() == 3) {

    		JOptionPane.showMessageDialog(null, "Apuestas " + getApuestaActual());
    		Jugador.realizarApuestaFija(jugadorActual);
        	GUIPrincipal.getPanelLateral().updateUI();
			GUIPrincipal.gameStage(3);
			GUIPrincipal.setRonda(4);
			return;
		}

    	if(GUIPrincipal.getRonda() == 4) {

    		JOptionPane.showMessageDialog(null, "Apuestas " + getApuestaActual());
    		Jugador.realizarApuestaFija(jugadorActual);
        	GUIPrincipal.getPanelLateral().updateUI();
			GUIPrincipal.gameStage(4);
			GUIPrincipal.getPanelCentral().removeAL();
			GUIPrincipal.controlUnit.winner();
			if(GUIPrincipal.controlUnit.winner() == jugadorActual.getName()) {

				GUIPrincipal.getPanelCentral().turnCards("pNormal");
				GUIPrincipal.getPanelCentral().turnCards("cNormal");
				JOptionPane.showMessageDialog(null, "El jugador 1 gana");
				GUIPrincipal.getPanelCentral().turnCards("pc");
				victoria(1);

			}
			else {

				GUIPrincipal.getPanelCentral().turnCards("pNormal");
				GUIPrincipal.getPanelCentral().turnCards("cNormal");
				JOptionPane.showMessageDialog(null, "Cortana gana");
				GUIPrincipal.getPanelCentral().turnCards("pc");
				victoria(2);
			}

			return;
		}

    }

    public static void cortanaDecidirJugada() {

    }

    public static void victoria(int quien) {
    	if(quien == 1) {
			Jugador.setDinero
    		(Jugador.getDinero(jugadorActual) + getApuestaActual(), jugadorActual);
			GUIPrincipal.getPanelLateral().updateUI();
			ControlUnit.newRound();

    	}
    	else {
    		GUIPrincipal.getPc();
    		Pc.setDineroPC
    		(Pc.getDineroPC() + getApuestaActual());
    		GUIPrincipal.getPanelLateral().updateUI();
    		ControlUnit.newRound();

    	}

    }

    public static int getApuestaActual(){
        return apuestaActual;
    }

    public static int setApuestaActual(int valor){
        apuestaActual = valor;
        return apuestaActual;
    }
    public static int getCantidadJugadores() {
        return cantidadJugadores;
    }

    public static void setCantidadJugadores(int cantidadJugadores) {
        ControlUnit.cantidadJugadores = cantidadJugadores;
    }



}
