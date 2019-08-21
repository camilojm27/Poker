/*
 * INTEGRANTES:
 * CAMILO JOSE MEZU MINA = 1824313
 * SANTIAGO MARTINEZ MESA = 1823107
 */
package poker;

import java.util.ArrayList;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class Baraja.
 */
//Clase de transicion o ayuda para repartir cartas
public class Baraja {
    
    /** The escaleras. */
    private Escaleras escaleras;
    
    /** The all. */
    private ArrayList<Carta> clubs, diamonds, hearts, spades, repartir, all;
    
    /** The valor. */
    private String tipo, valor;
    
    /** The random. */
    private Random random;


    /**
     * Instantiates a new baraja.
     */
    Baraja() {
        escaleras = new Escaleras();
        all = new ArrayList<>();
        clubs = new ArrayList<>();
        diamonds = new ArrayList<>();
        hearts = new ArrayList<>();
        spades = new ArrayList<>();

        for (int carta = 2; carta <= 14; carta++) {

            if (carta >= 11) {
                if (carta == 11) {
                    valor = "J";
                } else if (carta == 12) {
                    valor = "Q";
                } else if (carta == 13) {
                    valor = "K";
                } else if (carta == 14) {
                    valor = "A";
                }
            } else {
                valor = String.valueOf(carta);
            }

            for (int type = 0; type < 4; type++) {
                if (type == 0) {
                    tipo = "C";
                    clubs.add(new Carta(valor, tipo));
                } else if (type == 1) {
                    tipo = "D";
                    diamonds.add(new Carta(valor, tipo));
                } else if (type == 2) {
                    tipo = "H";
                    hearts.add(new Carta(valor, tipo));
                } else if (type == 3) {
                    tipo = "S";
                    spades.add(new Carta(valor, tipo));
                }
                all.add(new Carta(valor, tipo));

            }


        }

    }

    /**
     * Repartir baraja jugadores.
     *
     * @return the array list
     */
    public ArrayList<Carta> repartirBarajaJugadores() {
        ArrayList<Carta> todos = all;
        repartir = new ArrayList<>();
        random = new Random();
        int cualCarta, sizeFichas;


        for (int i = 0; i < 2; i++) {
            sizeFichas = todos.size();
            cualCarta = random.nextInt(sizeFichas);
            repartir.add(todos.get(cualCarta));
            todos.remove(cualCarta);
        }
        return repartir;
    }

    /**
     * Prints the.
     *
     * @param arrayList the array list
     */
    public void print(ArrayList<Carta> arrayList) {
        int f = 1;
        System.out.println("-----Imprimiendo Baraja------");
        for (Carta value : arrayList) {
            System.out.println(value.getImagen().toString()  + " " + f);
            f++;
        }
        System.out.println("-----------------");
    }

    /**
     * Repartir cartas comunitarias.
     *
     * @return the array list
     */
    public ArrayList<Carta> repartirCartasComunitarias(){
        ArrayList<Carta> todos = all;
        repartir = new ArrayList<>();
        random = new Random();
        int cualCarta, sizeFichas;

        for (int i = 0; i < 5; i++) {
            sizeFichas = todos.size();
            cualCarta = random.nextInt(sizeFichas);
            repartir.add(todos.get(cualCarta));
            todos.remove(cualCarta);
        }
        return repartir;

    }


    /**
     * Gets the diamonds.
     *
     * @return the diamonds
     */
    //Getters
    public ArrayList<Carta> getDiamonds() {
        return diamonds;
    }

    /**
     * Gets the hearts.
     *
     * @return the hearts
     */
    public ArrayList<Carta> getHearts() {
        return hearts;
    }

    /**
     * Gets the spades.
     *
     * @return the spades
     */
    public ArrayList<Carta> getSpades() {
        return spades;
    }

    /**
     * Gets the clubs.
     *
     * @return the clubs
     */
    public ArrayList<Carta> getClubs() {
        return clubs;
    }

    /**
     * Gets the all.
     *
     * @return the all
     */
    public ArrayList<Carta> getAll() {
        return all;
    }

}
