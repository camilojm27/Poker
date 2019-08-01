package poker;

import java.util.ArrayList;


public class Baraja {
    private Carta carta;
    private ArrayList<Carta> clubs;
    private ArrayList<Carta> diamonds;
    private ArrayList<Carta> hearts;
    private ArrayList<Carta> spades;

    public ArrayList<Carta> getClubs() {
        return clubs;
    }

    private String tipo;


    Baraja() {

        clubs = new ArrayList<>();
        diamonds = new ArrayList<>();
        hearts = new ArrayList<>();
        spades = new ArrayList<>();

        for (int carta = 2; carta <= 14; carta++) {

            if (carta >= 11) {
                if (carta == 11) {
                    tipo = "A";
                } else if (carta == 12) {
                    tipo = "J";
                } else if (carta == 13) {
                    tipo = "K";
                } else if (carta == 14) {
                    tipo = "Q";
                }
            } else {
                tipo = String.valueOf(carta);
            }

            for (int type = 0; type < 4; type++) {
                if (type == 0) {
                    tipo = "C";
                    clubs.add(new Carta(tipo, tipo));
                } else if (type == 1) {
                    tipo = "D";
                    diamonds.add(new Carta(tipo, tipo));
                } else if (type == 2) {
                    tipo = "H";
                    hearts.add(new Carta(tipo, tipo));
                } else if (type == 3) {
                    tipo = "S";
                    spades.add(new Carta(tipo, tipo));
                }


            }


        }

    }

    public void print(ArrayList<Carta> arrayList) {
        for (int i=0; i < arrayList.size(); i++){
            System.out.println(arrayList.get(i).getImagen());
        }
    }

}
