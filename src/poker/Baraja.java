package poker;

import java.util.ArrayList;
import java.util.Random;


public class Baraja {
    private Carta carta;
    private ArrayList<Carta> clubs, diamonds, hearts, spades;
    private String tipo, valor;
    private Random random;


    Baraja() {
        clubs = new ArrayList<>();
        diamonds = new ArrayList<>();
        hearts = new ArrayList<>();
        spades = new ArrayList<>();

        for (int carta = 2; carta <= 14; carta++) {

            if (carta >= 11) {
                if (carta == 11) {
                    valor = "A";
                } else if (carta == 12) {
                    valor = "J";
                } else if (carta == 13) {
                    valor = "K";
                } else if (carta == 14) {
                    valor = "Q";
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


            }


        }

    }
/*
    public ArrayList<Carta> darBarajaJugador(){
        random = new Random();

        int valor = random.nextInt(5);
        for (int i =0; i < 5; i++){

        }
    }
*/
    public void print(ArrayList<Carta> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i).getImagen());
        }
    }

    //Getters
    public ArrayList<Carta> getDiamonds() {
        return diamonds;
    }

    public ArrayList<Carta> getHearts() {
        return hearts;
    }

    public ArrayList<Carta> getSpades() {
        return spades;
    }

    public ArrayList<Carta> getClubs() {
        return clubs;
    }

}
